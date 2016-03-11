package fs.processing.trash;

import fs.processing.model.Payment;
import fs.processing.service.PayLimitPolicy;

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
