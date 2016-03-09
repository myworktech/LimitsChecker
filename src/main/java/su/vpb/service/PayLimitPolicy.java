package su.vpb.service;

import su.vpb.limit.Limit;
import su.vpb.model.Payment;

import java.util.*;

/**
 * Created by pechenkin on 15.09.2015.
 */
public class PayLimitPolicy {

    public static List<Payment> paymentList = new ArrayList<Payment>();

    public static Set<Limit> limits = new HashSet<Limit>();


    public void process(Payment payment) {
        for (Limit limit : limits) {
//            limit.check(payment);
        }

    }

    public void print(){
        for (Payment payment : paymentList) {
            System.out.print(payment+"\n");
        }
    }


}
