package com.myworktechs.service.checkers.collective;

import com.myworktechs.model.Payment;
import com.myworktechs.service.checkers.Check;

import java.util.concurrent.ConcurrentLinkedQueue;

public interface CollectAndCheck extends Check {
    ConcurrentLinkedQueue<Payment> getPayments();

    boolean addPaymentAndCheck(Payment payment);

    void performClean(Payment p);
}
