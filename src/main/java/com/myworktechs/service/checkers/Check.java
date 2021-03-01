package com.myworktechs.service.checkers;

import com.myworktechs.model.Payment;

public interface Check {
    boolean addPaymentAndCheck(Payment payment);
}
