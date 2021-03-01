package com.myworktechs.model;

import lombok.Value;

@Value
public class SingleCheckResult {
    boolean isRuleTriggered;
    String triggerReason;

    public static SingleCheckResult filterTriggered(String triggerReason) {
        return new SingleCheckResult(true, triggerReason);
    }

    public static SingleCheckResult filterPassed() {
        return new SingleCheckResult(false, "");
    }

}