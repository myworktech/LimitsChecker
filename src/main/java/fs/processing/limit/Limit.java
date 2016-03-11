package fs.processing.limit;

import org.joda.time.DateTime;
import fs.processing.model.Payment;
import fs.processing.model.PaymentStatus;

public class Limit {
    private Integer amount;
    private DateTime boundStart;
    private DateTime boundEnd;
    private String interval;
    private boolean sameService;

    Limit(Builder builder) {
        amount = builder.amount;
        boundStart = builder.boundStart;
        boundEnd = builder.bountEnd;
        interval = builder.interval;
        sameService = builder.sameService;
    }

    public void check(Payment payment) throws Exception {
        if (checkBounds(payment) & checkAmount(payment) & checkSameService(payment))
                    setFailedStatusFor(payment);
        else
                    setOkayStatusFor(payment);


    }

    private boolean checkAmount(Payment payment) {
        return false;
    }

    private boolean checkSameService(Payment payment) {
        return false;
    }

    private void setOkayStatusFor(Payment payment) {
        payment.setStatus(PaymentStatus.ALREADY_CHECKED);
    }

    private void setFailedStatusFor(Payment payment) {
        payment.setStatus(PaymentStatus.SUBMIT_REQUIRED);
    }

    private boolean checkBounds(Payment payment) throws Exception {
        if (boundStart == null & boundEnd == null) return true;
        if (boundStart == null | boundEnd == null ) throw new Exception();


       return(boundStart.isAfter(payment.getPaymentDate()) & (payment.getPaymentDate()).isAfter(boundEnd));
    }
}
