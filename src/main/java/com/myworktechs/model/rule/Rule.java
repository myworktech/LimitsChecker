package com.myworktechs.model.rule;

import com.myworktechs.model.subject.SubjectsEnum;
import com.myworktechs.model.subject.ThresholdType;
import lombok.Builder;
import lombok.Value;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Builder
@Value
public class Rule {
    String description;
    Long thresholdValue;
    ThresholdType thresholdType;

    SubjectsEnum subjectField;

    Duration collectiveForInterval;

    LocalTime timeframeStart;
    LocalTime timeframeEnd;

    public boolean isCollective() {
        return collectiveForInterval != null;
    }

    public boolean hasTimeframe() {
        return timeframeStart != null && timeframeEnd != null;
    }

    private static LocalTime parse(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static Rule m1() {
        return Rule.builder()
                .thresholdValue(5_000L)
                .thresholdType(ThresholdType.AMOUNT)
                .timeframeStart(parse("09:00"))
                .timeframeEnd(parse("23:00"))
                .subjectField(SubjectsEnum.SAME_SERVICE)
                .build();
    }

    public static Rule m3() {
        return Rule.builder()
                .thresholdValue(2_000L)
                .thresholdType(ThresholdType.AMOUNT)
                .timeframeStart(parse("0:00"))
                .timeframeEnd(parse("24:00"))
                .subjectField(SubjectsEnum.SAME_PAYMENT)
                .build();
    }

    public static Rule m4() {
        return Rule.builder()
                .thresholdValue(3_000L)
                .thresholdType(ThresholdType.AMOUNT)
                .collectiveForInterval(Duration.parse("PT1H"))
                .subjectField(SubjectsEnum.SAME_SERVICE)
                .build();
    }

    public static Rule m5() {
        return Rule.builder()
                .thresholdValue(20L)
                .thresholdType(ThresholdType.COUNT)
                .timeframeStart(parse("0:00"))
                .timeframeEnd(parse("24:00"))
                .subjectField(SubjectsEnum.SAME_PAYMENT)
                .build();
    }

    public static Rule m6_1() {
        return Rule.builder()
                .thresholdValue(40L)
                .thresholdType(ThresholdType.COUNT)
                .timeframeStart(parse("10:00"))
                .timeframeEnd(parse("17:00"))
                .subjectField(SubjectsEnum.SAME_SERVICE)
                .build();
    }

    public static Rule m6_2() {
        return Rule.builder()
                .thresholdValue(4_000L)
                .thresholdType(ThresholdType.AMOUNT)
                .timeframeStart(parse("10:00"))
                .timeframeEnd(parse("17:00"))
                .subjectField(SubjectsEnum.SAME_SERVICE)
                .build();
    }
}

// 1. Не более 5000 руб. днем с 9:00 утра до 23:00 за одну услугу(*)
// 2. Не более 5000 руб. вечером с 18:00 до 23:00 за одну услугу(*)
// 3. Не более 2000 руб. в сутки по одинаковым платежам(**) -- "в сутки" -- это значит с полуночи до полуночи, а не за последние 24 часа
// 4. Не более 3000 руб. в течение одного часа за одну услугу(*)
// 5. Не более 20 одинаковых платежей(**) в сутки
// 6. Не более 40 платежей не более чем на 4000 руб.(***) с 10:00 до 17:00 за одну услугу(*)
// 7. Не более 10 платежей не более чем на 3000 руб.(***) в течение двух часов в пользу одного клиента