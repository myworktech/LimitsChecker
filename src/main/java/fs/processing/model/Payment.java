package fs.processing.model;

import java.time.LocalDateTime;

public class Payment {

    private Long id;
    private Long amount;
    private PaymentStatus status;
    private Account account;
    private Service service;
    private LocalDateTime paymentDate;

    public Payment(Long amount, Account account, Service service, LocalDateTime paymentDate) {
        this.amount = amount;
        this.status = PaymentStatus.NEW_PAYMENT;
        this.account = account;
        this.service = service;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
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

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public boolean isSamePayment(Payment payment) {
        return this.getAccount().equals(payment.getAccount()) && this.getService().equals(payment.getService());
    }

    @Override
    public String toString() {
        return String.format("Payment {id=%s, amount=%s, status=%s, account=%s, service=%s, paymentDate=%s}"
                , id<10 ? "0"+id : id, amount, status, account, service, paymentDate);

//        return "Payment{ " +
//                "id=" + id +
//                ", amount=" + amount +
//                ", status=" + status +
//                ", account=" + account +
//                ", service=" + service +
//                ", paymentDate=" + paymentDate +
//                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return id.equals(payment.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}