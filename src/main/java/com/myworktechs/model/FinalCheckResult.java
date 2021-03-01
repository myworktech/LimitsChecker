package com.myworktechs.model;

import lombok.Value;

import java.util.List;

@Value
public class FinalCheckResult {
    boolean isPaymentSuspicious;
    List<String> triggerReasonList;
}
