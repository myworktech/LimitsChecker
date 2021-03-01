package com.myworktechs.service.checkers.collective;

import com.myworktechs.model.Payment;
import lombok.Value;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

@Value
public class CountCollectAndCheck implements CollectAndCheck {
    ConcurrentLinkedQueue<Payment> payments = new ConcurrentLinkedQueue<>();
    AtomicLong total = new AtomicLong(0L);
    long threshold;

    public CountCollectAndCheck(long threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean addPaymentAndCheck(Payment payment) {
        boolean result;

        long oldValue;
        long newValue;
        do {
            oldValue = total.get();
            newValue = oldValue + 1;
            result = newValue >= threshold;
        } while (!total.compareAndSet(oldValue, newValue));

        payments.offer(payment);

        return result;
    }

    @Override
    public void performClean(Payment payment) {
        total.decrementAndGet();
    }
}