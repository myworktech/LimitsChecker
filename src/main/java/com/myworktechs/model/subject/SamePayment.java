package com.myworktechs.model.subject;

import com.myworktechs.model.Payment;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Value(staticConstructor = "fromPayment")
@EqualsAndHashCode
public class SamePayment implements Subject {
    Client client;
    Service service;

    private static final ConcurrentMap<SamePayment, SamePayment> map = new ConcurrentHashMap<>();

    public static Subject fromPayment(Payment payment) {
        SamePayment samePayment = new SamePayment(payment.getClient(), payment.getService());
        return map.putIfAbsent(samePayment, samePayment);
    }
}
