package fs.processing.limit;

import fs.processing.model.Payment;
import fs.processing.model.PaymentStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Limit {
    private Long amountThreshold;
    private Long paymentCountThreshold;

    private LocalDateTime boundStart;
    private LocalDateTime boundEnd;
    private Duration interval;
    private Boolean sameAccount;
    private Boolean sameService;

    Limit(LimitBuilder limitBuilder) {
        amountThreshold = limitBuilder.getAmount();
        paymentCountThreshold = limitBuilder.getPaymentCount();
        boundStart = limitBuilder.getBoundStart();
        boundEnd = limitBuilder.getBoundEnd();
        interval = limitBuilder.getInterval();
        sameAccount = limitBuilder.getSameAccount();
        sameService = limitBuilder.getSameService();
    }

    public void check(Payment payment, List<Payment> paymentList) {
        if (amountThreshold.equals(0L) && paymentCountThreshold.equals(0L)) {
            setOkayStatusFor(payment);
            return;
        }

        List<Payment> result = paymentList.stream()
                .filter(
                        p -> checkBounds(p)
                                && checkInterval(p, payment)
                                && checkSameService(p, payment)
                                && checkSameAccount(p, payment)
                )
                .collect(Collectors.toList());

        Long count = result.stream().count();
        Long sum = result.stream().mapToLong(p -> p.getAmount()).sum();

        if (((checkAmountThreshold(sum)) || checkCountThreshold(count))) {
            setFailedStatusFor(getLastPayment(result));
            if (payment.getStatus() == PaymentStatus.NEW_PAYMENT)
                setOkayStatusFor(payment);
            return;
        }

        if (payment.getStatus() != PaymentStatus.SUBMIT_REQUIRED)
            setOkayStatusFor(payment);
    }

    private Payment getLastPayment(List<Payment> paymentList) {
        return paymentList.get(paymentList.size()-1);
    }

    private boolean checkAmountThreshold(Long sum) {
        return amountThreshold != 0 && sum > amountThreshold;
    }

    private boolean checkCountThreshold(Long count) {
        return paymentCountThreshold != 0 && count > paymentCountThreshold;
    }

    private boolean checkSameAccount(Payment p, Payment payment) {
        return !sameAccount || p.getAccount().equals(payment.getAccount());
    }

    private boolean checkSameService(Payment p, Payment payment) {
        return !sameService || p.getService().equals(payment.getService());
    }

    private void setOkayStatusFor(Payment payment) {
        payment.setStatus(PaymentStatus.READY_TO_PROCESS);
    }

    private void setFailedStatusFor(Payment payment) {
        payment.setStatus(PaymentStatus.SUBMIT_REQUIRED);
    }

    private boolean checkBounds(Payment payment) {
        if (boundStart.equals(LocalDateTime.MIN) || boundEnd.equals(LocalDateTime.MIN)) return true;

        return (boundStart.isBefore(payment.getPaymentDate()) && boundEnd.isAfter(payment.getPaymentDate()));
    }

    private boolean checkInterval(Payment payment, Payment paymentOrig) {
        if (interval.equals(Duration.ZERO)) return true;

        Duration diff = Duration.between(payment.getPaymentDate(), paymentOrig.getPaymentDate());

        return interval.compareTo(diff) > 0;
    }

}
