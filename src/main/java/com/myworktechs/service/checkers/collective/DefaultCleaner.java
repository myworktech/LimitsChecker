package com.myworktechs.service.checkers.collective;

import com.myworktechs.model.Payment;
import com.myworktechs.model.subject.Subject;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class DefaultCleaner implements Cleaner {
    private final Map<Subject, CollectAndCheck> collectivePerSubject;
    private final long timeToCleanMillis;

    public DefaultCleaner(Map<Subject, CollectAndCheck> collectivePerSubject, Duration intervalToClean) {
        this.collectivePerSubject = collectivePerSubject;
        this.timeToCleanMillis = intervalToClean.toMillis();
    }

    @Override
    public void run() {
        log.info("Cleaner starting...");

        long nowMillis = System.currentTimeMillis();
        long now = 1_000L * ((nowMillis + 500) / 1000);

        AtomicBoolean smthngWasRemoved = new AtomicBoolean(false);
        collectivePerSubject.values().forEach(paymentsAmountPair -> {
            ConcurrentLinkedQueue<Payment> payments = paymentsAmountPair.getPayments();
            boolean smthngWasRemove = payments.removeIf(p -> {
                boolean needToClean = (now - p.getTimestampMillis()) > timeToCleanMillis;
                if (needToClean) {
                    paymentsAmountPair.performClean(p);
                }
                return needToClean;
            });
            smthngWasRemoved.set(smthngWasRemove);


        });
        log.info("Cleaner finished with: {}, in, ms: {}", smthngWasRemoved.get(), System.currentTimeMillis() - nowMillis);
    }
}