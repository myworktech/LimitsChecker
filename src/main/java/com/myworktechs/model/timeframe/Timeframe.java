package com.myworktechs.model.timeframe;

import com.myworktechs.model.Payment;

public interface Timeframe {
    boolean check(Payment payment);
}
