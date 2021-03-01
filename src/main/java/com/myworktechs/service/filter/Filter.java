package com.myworktechs.service.filter;

import com.myworktechs.model.SingleCheckResult;
import com.myworktechs.model.Payment;

public interface Filter {
    SingleCheckResult process(Payment payment);
}
