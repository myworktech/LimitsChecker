package com.myworktechs.service.checkers;

import com.myworktechs.model.Payment;
import lombok.Value;

import java.util.concurrent.atomic.AtomicLong;

@Value
public class SimpleCountCheck implements Check {
    AtomicLong total = new AtomicLong(0L);
    long threshold;

    public SimpleCountCheck(long threshold) {
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

        return result;
    }
}
