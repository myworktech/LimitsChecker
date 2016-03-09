package su.vpb.trash;

import su.vpb.model.Payment;
import su.vpb.service.PayLimitPolicy;

import java.util.List;

/**
 * Created by pechenkin on 22.09.2015.
 */
public abstract class OldAbstractLimit {
    protected static List<Payment> paymentList = PayLimitPolicy.paymentList;
    Integer amount;
    public OldAbstractLimit(Integer amount) {
        this.amount = amount;
    }

    public abstract void checkPayment(Payment payment);

    public void check(Payment payment) {

        if (!paymentList.contains(payment)) paymentList.add(payment);

        checkPayment(payment);
    }
}
