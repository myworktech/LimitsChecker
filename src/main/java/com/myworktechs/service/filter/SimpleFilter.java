package com.myworktechs.service.filter;

import com.myworktechs.model.Payment;
import com.myworktechs.model.SingleCheckResult;
import com.myworktechs.model.rule.Rule;
import com.myworktechs.model.subject.Subject;
import com.myworktechs.model.subject.ThresholdType;
import com.myworktechs.model.timeframe.DailyTimeframe;
import com.myworktechs.service.checkers.Check;
import com.myworktechs.service.checkers.CheckFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Value
@Getter(value = AccessLevel.NONE)
public class SimpleFilter implements Filter {

    Map<Subject, Check> checkPerSubject = new ConcurrentHashMap<>();
    String checkDescription;
    Class<? extends Subject> subjectsImplementationClazz;
    ThresholdType thresholdType;
    long thresholdValue;
    boolean hasTimeFrame;
    DailyTimeframe dailyTimeframe;

    public SimpleFilter(Rule rule) {
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
    }

    @Override
    public SingleCheckResult process(Payment payment) {
        if (hasTimeFrame) {
            boolean check = dailyTimeframe.check(payment);
            if (!check)
                return SingleCheckResult.filterPassed();
        }

        Subject subject = payment.getSubject(subjectsImplementationClazz);

        Check check = checkPerSubject.get(subject);
        if (check == null) {
            check = CheckFactory.fromThresholdType(thresholdType.getClazz(), thresholdValue);
            Check previous = checkPerSubject.putIfAbsent(subject, check);
            check = previous != null ? previous : check;
        }

        boolean result = check.addPaymentAndCheck(payment);
        return result ? SingleCheckResult.filterTriggered(checkDescription) : SingleCheckResult.filterPassed();
    }
}
