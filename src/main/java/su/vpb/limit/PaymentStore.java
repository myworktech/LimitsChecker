package su.vpb.limit;

import su.vpb.model.Payment;

import java.util.*;

public class PaymentStore {
    public static final List<Payment> INSTANCE = new ArrayList<>();

    public static Integer getTotalAmount() { //нужно лениво инициализировать
        Integer totalAmount = 0;
        for (Payment p:INSTANCE) {
            totalAmount = totalAmount + p.getAmount();
        }
        return totalAmount;
    }


}
