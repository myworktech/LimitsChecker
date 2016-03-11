package fs.processing.model;

import org.joda.time.DateTime;
import fs.processing.limit.PaymentStore;

import java.io.Serializable;

/**
 * Created by pechenkin on 15.09.2015.
 */
public class Payment implements Serializable {

    private Integer amount;

    private PaymentStatus status;

    private Account account;

    private Service service;

    private DateTime paymentDate;

    public Payment(Integer amount, Account account, Service service, DateTime paymentDate) {
        this.amount = amount;
        this.status = PaymentStatus.NEW_POLICY;
        this.account = account;
        this.service = service;
        this.paymentDate = paymentDate;
        //хитрец
        PaymentStore.INSTANCE.add(this);
    }

    public Integer getAmount() {
        return amount;
    }



    public Account getAccount() {
        return account;
    }


    public Service getService() {
        return service;
    }



    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public DateTime getPaymentDate() {
        return paymentDate;
    }




    @Override
    public String toString() {
        return "Payment{ " +
                "amount=" + amount +
                ", status=" + status +
                ", account=" + account +
                ", service=" + service +
                ", paymentDate=" + paymentDate +
                '}';
    }
}