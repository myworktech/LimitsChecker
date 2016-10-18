package fs.processing.limit;

import fs.processing.model.Payment;
import fs.processing.model.PaymentStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Limit {
    private Long amountThreshold = 0L;
    private Long samePaymentCountThreshold = 0L;
    private LocalDateTime boundStart;
    private LocalDateTime boundEnd;
    private Duration interval;
    private Boolean sameAccount;
    private Boolean sameService;

    Limit(LimitBuilder limitBuilder) {
        amountThreshold = limitBuilder.getAmount();
        samePaymentCountThreshold = limitBuilder.getSamePaymentCount();
        boundStart = limitBuilder.getBoundStart();
        boundEnd = limitBuilder.getBoundEnd();
        interval = limitBuilder.getInterval();
        sameAccount = limitBuilder.getSameAccount();
        sameService = limitBuilder.getSameService();
    }

    public void check(Payment payment, List<Payment> paymentList) {
        if (amountThreshold.equals(0L) && samePaymentCountThreshold.equals(0L)) {
            setOkayStatusFor(payment);
            return;
        }

//        List<Payment> result = checkBounds(paymentList)
//                .checkInterval(paymentList)
//                .checkSameAccount(paymentList)
//                .checkSameService(paymentList);

        List<Payment> result = paymentList.stream()
                            .filter(p -> checkBounds(p))
                            .filter(p -> checkInterval(p, payment))
                            .filter(p -> checkSameService(p, payment))
                            .filter(p -> checkSameAccount(p, payment))

                            .collect(Collectors.toList());

        Long count = result.stream().count();
        Long sum = result.stream().mapToLong(p -> p.getAmount()).sum();


        if ((sum > amountThreshold ) && checkBounds(payment)) {
            setFailedStatusFor(payment);
            return;
        }

        if (payment.getStatus() != PaymentStatus.SUBMIT_REQUIRED)
        setOkayStatusFor(payment);
    }

    private boolean checkSameAccount(Payment p, Payment payment) {
        if (!sameAccount) return true;
        return p.getAccount().equals(payment.getAccount());
    }

    private boolean checkSameService(Payment p, Payment payment) {
        if (!sameService) return true;
        return p.getService().equals(payment.getService());
    }


    private void setOkayStatusFor(Payment payment) {
        payment.setStatus(PaymentStatus.READY_TO_PROCESS);
    }

    private void setFailedStatusFor(Payment payment) {
        payment.setStatus(PaymentStatus.SUBMIT_REQUIRED);
    }

    private boolean checkBounds(Payment payment) {
        if (boundStart == null || boundEnd == null) return true;

        return boundStart.isBefore(payment.getPaymentDate()) && boundEnd.isAfter(payment.getPaymentDate());
    }

    private boolean checkInterval(Payment payment, Payment paymentOrig) {
        Duration diff = Duration.between(payment.getPaymentDate(), paymentOrig.getPaymentDate());

       int result = interval.compareTo(diff);

        return result > 0;
    }


}
