package com.myworktechs.model.subject;

import com.myworktechs.service.checkers.collective.CollectAndCheck;
import com.myworktechs.service.checkers.collective.AmountCollectAndCheck;
import com.myworktechs.service.checkers.collective.CountCollectAndCheck;
import lombok.Getter;

public enum ThresholdType {
    AMOUNT(AmountCollectAndCheck.class),
    COUNT(CountCollectAndCheck.class);

    @Getter
    private final Class<? extends CollectAndCheck> clazz;

    ThresholdType(Class<? extends CollectAndCheck> clazz) {
        this.clazz = clazz;
    }
}
