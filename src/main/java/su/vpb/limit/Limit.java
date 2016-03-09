package su.vpb.limit;

import org.joda.time.DateTime;
import su.vpb.model.*;

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

    public void check(Payment payment) {
        if (checkBounds(payment) & checkAmount(paymfent) & checkSameService(payment))
                    setFailedStatusFor(payment);
        else
                    setOkayStatusFor(payment);


    }

    private void setOkayStatusFor(Payment payment) {
        payment.setStatus(PaymentStatus.ALREADY_CHECKED);
    }

    private void setFailedStatusFor(Payment payment) {
        payment.setStatus(PaymentStatus.SUBMIT_REQUIRED);
    }

    private boolean checkBounds(Payment payment)  {
       return(boundStart.isAfter(payment.getPaymentDate()) & (payment.getPaymentDate()).isAfter(boundEnd));
    }
}
