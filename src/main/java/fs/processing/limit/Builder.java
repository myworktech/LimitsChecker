package fs.processing.limit;

import org.joda.time.DateTime;


public class Builder {
    protected Integer amount;
    protected DateTime boundStart;
    protected DateTime bountEnd;
    protected int samePaymentCount = 0;
    public String interval;
    public boolean sameService;

    public Builder() {

    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Builder setBoundStart(String val) {
//        boundStart = val;
        return this;
    }

    public Builder setBountEnd(String val) {
//        bountEnd = val;
        return this;

    }

    public Builder setSamePaymentCount(int val) {
        samePaymentCount = val;
        return this;
    }

    public Limit build() {
        return new Limit(this);
    }


}
