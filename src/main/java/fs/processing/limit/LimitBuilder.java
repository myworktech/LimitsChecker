package fs.processing.limit;


import java.time.Duration;
import java.time.LocalDateTime;


public class LimitBuilder {
    private Long amount = 0L;
    private Long samePaymentCount = 0L;
    private LocalDateTime boundStart;
    private LocalDateTime boundEnd;
    private Duration interval;
    private Boolean sameAccount = false;
    private Boolean sameService = false;

    public Limit build() {
        return new Limit(this);
    }

    public LimitBuilder setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public LimitBuilder setSamePaymentCount(Long samePaymentCount) {
        this.samePaymentCount = samePaymentCount;
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

    public LimitBuilder setSameAccount(Boolean sameAccount) {
        this.sameAccount = sameAccount;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public LocalDateTime getBoundStart() {
        return boundStart;
    }

    public LocalDateTime getBoundEnd() {
        return boundEnd;
    }

    public Long getSamePaymentCount() {
        return samePaymentCount;
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
