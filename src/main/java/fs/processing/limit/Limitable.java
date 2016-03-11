package fs.processing.limit;

import fs.processing.model.Payment;

public interface Limitable {
        boolean check(Payment payment);
}
