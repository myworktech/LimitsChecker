package fs.processing.limit;


import java.time.Duration;
import java.time.LocalDateTime;


public class LimitBuilder {
    private Long amount = 0L;
    private Long paymentCount = 0L;

    private LocalDateTime boundStart;
    private LocalDateTime boundEnd;
    private Duration interval = Duration.ZERO;
    private Boolean sameAccount = false;
    private Boolean sameService = false;

    public Limit build() {
        return new Limit(this);
    }

    public LimitBuilder setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public LimitBuilder setPaymentCount(Long paymentCount) {
        this.paymentCount = paymentCount;
        return this;
    }

    public LimitBuilder setSamePaymentCount(Long samePaymentCount) {
        this.paymentCount = samePaymentCount;
        this.sameAccount = true;
        this.sameService = true;
        return this;
    }


    public LimitBuilder setBoundStart(LocalDateTime boundStart) {
        this.boundStart = boundStart;
        return this;
    }

    public LimitBuilder setBoundEnd(LocalDateTime boundEnd) {
        this.boundEnd = boundEnd;
        return this;
    }

    public LimitBuilder setInterval(Duration interval) {
        this.interval = interval;
        return this;
    }

    public LimitBuilder setSameService() {
        this.sameService = true;
        return this;
    }

    public LimitBuilder setSameAccount() {
        this.sameAccount = true;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getPaymentCount() {
        return paymentCount;
    }

    public LocalDateTime getBoundStart() {
        return boundStart;
    }

    public LocalDateTime getBoundEnd() {
        return boundEnd;
    }

    public Duration getInterval() {
        return interval;
    }

    public Boolean getSameService() {
        return sameService;
    }

    public Boolean getSameAccount() {
        return sameAccount;
    }
}
