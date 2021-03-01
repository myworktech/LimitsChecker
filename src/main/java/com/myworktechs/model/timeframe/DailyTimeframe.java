package com.myworktechs.model.timeframe;

import com.myworktechs.model.Payment;
import lombok.Value;

import java.time.LocalTime;

import static java.time.temporal.ChronoField.MILLI_OF_DAY;

@Value
public class DailyTimeframe implements Timeframe {
    long timeframeStart;
    long timeframeEnd;

    public DailyTimeframe(LocalTime timeframeStart, LocalTime timeframeEnd) {
        this.timeframeStart = timeframeStart.getLong(MILLI_OF_DAY);
        this.timeframeEnd = timeframeEnd.getLong(MILLI_OF_DAY);
    }

    @Override
    public boolean check(Payment payment) {
        return timeframeStart <= payment.getTimestampMillis()
                && timeframeEnd >= payment.getTimestampMillis();
    }
}
