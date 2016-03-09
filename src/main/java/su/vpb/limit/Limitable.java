package su.vpb.limit;

import su.vpb.model.Payment;

public interface Limitable {
        boolean check(Payment payment);
}
