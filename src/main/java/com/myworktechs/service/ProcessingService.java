package com.myworktechs.service;

import com.myworktechs.model.FinalCheckResult;
import com.myworktechs.model.Payment;
import com.myworktechs.model.SingleCheckResult;
import com.myworktechs.service.filter.Filter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Value
@Slf4j
public class ProcessingService {

    List<Filter> filterList;

    public FinalCheckResult process(Payment payment) {
        AtomicBoolean triggeredOnceFlag = new AtomicBoolean(false);
        List<String> triggerReasonList = new ArrayList<>();
        filterList.forEach(filter -> {
            SingleCheckResult process = filter.process(payment);

            if (process.isRuleTriggered()) {
                triggeredOnceFlag.set(true);
                triggerReasonList.add(process.getTriggerReason());
            }

        });

        return new FinalCheckResult(triggeredOnceFlag.get(), Collections.unmodifiableList(triggerReasonList));
    }


}
