package com.myworktechs.service.filter;

import com.myworktechs.model.Payment;
import com.myworktechs.model.SingleCheckResult;
import com.myworktechs.model.rule.Rule;
import com.myworktechs.model.subject.Subject;
import com.myworktechs.model.subject.ThresholdType;
import com.myworktechs.model.timeframe.DailyTimeframe;
import com.myworktechs.service.checkers.CheckFactory;
import com.myworktechs.service.checkers.collective.CollectAndCheck;
import com.myworktechs.service.checkers.collective.DefaultCleaner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Value
@Getter(value = AccessLevel.NONE)
public class CollectiveFilter implements Filter {

    Map<Subject, CollectAndCheck> collectivePerSubject = new ConcurrentHashMap<>();
    String checkDescription;
    Class<? extends Subject> subjectsImplementationClazz;
    ThresholdType thresholdType;
    long thresholdValue;
    boolean hasTimeFrame;
    DailyTimeframe dailyTimeframe;

    ScheduledExecutorService executorService;

    public CollectiveFilter(Rule rule) {
        this.checkDescription = rule.getDescription();
        this.subjectsImplementationClazz = rule.getSubjectField().getClazz();
        this.thresholdType = rule.getThresholdType();
        this.thresholdValue = rule.getThresholdValue();

        if (rule.hasTimeframe()) {
            this.dailyTimeframe = new DailyTimeframe(rule.getTimeframeStart(), rule.getTimeframeEnd());
            this.hasTimeFrame = true;
        } else {
            this.dailyTimeframe = null;
            this.hasTimeFrame = false;
        }

        this.executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new DefaultCleaner(collectivePerSubject, rule.getCollectiveForInterval()), 0L, 1_000, TimeUnit.MILLISECONDS);
    }

    @Override
    public SingleCheckResult process(Payment payment) {
        if (hasTimeFrame) {
            boolean check = dailyTimeframe.check(payment);
            if (!check)
                return SingleCheckResult.filterPassed();
        }

        Subject subject = payment.getSubject(subjectsImplementationClazz);

        CollectAndCheck collectAndCheck = collectivePerSubject.get(subject);
        if (collectAndCheck == null) {
            collectAndCheck = CheckFactory.fromThresholdTypeAccumulative(thresholdType.getClazz(), thresholdValue);
            CollectAndCheck previous = collectivePerSubject.putIfAbsent(subject, collectAndCheck);
            collectAndCheck = previous != null ? previous : collectAndCheck;
        }

        boolean result = collectAndCheck.addPaymentAndCheck(payment);
        return result ? SingleCheckResult.filterTriggered(checkDescription) : SingleCheckResult.filterPassed();
    }
}
