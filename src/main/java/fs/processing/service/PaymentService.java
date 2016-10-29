package fs.processing.service;

import fs.processing.limit.Limit;
import fs.processing.model.Payment;
import fs.processing.model.PaymentStatus;

import java.util.*;

public class PaymentService {

    public final List<Payment> paymentList = new ArrayList<>();

    private List<Limit> limitList;

    public void process(Payment payment) {
        payment.setId(paymentList.size()+1L); // may be wrong. must count payments separately.

        savePayment(payment);

        for (Limit limit : limitList) {
            limit.check(payment, paymentList);
        }


    }

    private void savePayment(Payment payment) {
        paymentList.add(payment);
    }

    public void print() {
        for (Payment payment : paymentList) {
            System.out.print(payment + "\n");
        }
        System.out.println("\n");
    }

    public void addLimits(List<Limit> limitList) {
        this.limitList = limitList;
    }

}
