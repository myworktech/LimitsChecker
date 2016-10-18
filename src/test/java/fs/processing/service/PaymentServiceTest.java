package fs.processing.service;


import fs.processing.limit.LimitBuilder;
import fs.processing.limit.Limit;
import fs.processing.model.Account;
import fs.processing.model.Payment;
import fs.processing.model.PaymentStatus;
import fs.processing.model.Service;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.junit.Assert.assertEquals;


public class PaymentServiceTest {

    Service service1;
    Service service2;
    Service service3;
    Account account22;
    Account account33;
    Account account44;
    Account account55;
    Account account66;

    private PaymentService paymentService = new PaymentService();

    private List<Payment> paymentListSource = new ArrayList<>();

    private List<Limit> limitList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        initAccounts();
        initServices();
        initPayments();
    }

    private void initPayments() throws Exception {
        paymentListSource.add(new Payment(1500L, account22, service1, LocalDateTime.parse("2016-01-01T08:10")));
        paymentListSource.add(new Payment(1500L, account22, service2, LocalDateTime.parse("2016-01-01T08:20")));
        paymentListSource.add(new Payment(2500L, account33, service1, LocalDateTime.parse("2016-01-01T08:30")));
        paymentListSource.add(new Payment(1500L, account22, service2, LocalDateTime.parse("2016-01-01T08:50")));
        paymentListSource.add(new Payment(1500L, account22, service1, LocalDateTime.parse("2016-01-01T09:10")));
        paymentListSource.add(new Payment(1000L, account33, service1, LocalDateTime.parse("2016-01-01T09:30")));
        paymentListSource.add(new Payment(3000L, account33, service1, LocalDateTime.parse("2016-01-01T10:00")));
        paymentListSource.add(new Payment(2000L, account33, service1, LocalDateTime.parse("2016-01-01T19:30")));
    }

    @Test
    public void testNoLimits() throws Exception {
        Limit limit = new LimitBuilder().build();
        limitList.add(limit);
        paymentService.addLimits(limitList);

        for (Payment payment : paymentListSource)
            paymentService.process(payment);


        for (Payment p : paymentService.paymentList) {
            assertEquals(p.getStatus(), PaymentStatus.READY_TO_PROCESS);
        }
    }

    @Test
    public void testFirstLimit() throws Exception {

//        limitList.add(new LimitBuilder()
//                .setBoundStart(LocalDateTime.parse("2016-01-01T08:15"))
//                .setBoundEnd(LocalDateTime.parse("2016-01-01T09:00"))
//                .setAmount(2000L)
//                .build());



        limitList.add(new LimitBuilder()
                .setInterval(Duration.of(40, MINUTES ))
                .setAmount(3500L)
                .build());

        paymentService.addLimits(limitList);

        for (Payment payment : paymentListSource)
            paymentService.process(payment);

        paymentService.print();
    }

    @Test
    public void testSecondLimit() throws Exception {

//        limitList.add(new LimitBuilder()
//                .setBoundStart(LocalDateTime.parse("2016-01-01T08:15"))
//                .setBoundEnd(LocalDateTime.parse("2016-01-01T09:00"))
//                .setAmount(2000L)
//                .build());



        limitList.add(new LimitBuilder()
                .setInterval(Duration.of(60, MINUTES ))
                .setAmount(3000L)
                .setSameService()
                .build());

        paymentService.addLimits(limitList);

        for (Payment payment : paymentListSource)
            paymentService.process(payment);

        paymentService.print();
    }

    private void initServices() {
        service1 = new Service(1L, "Mobile phone");
        service2 = new Service(2L, "Bank account");
        service3 = new Service(3L, "E-shop acc");
    }

    private void initAccounts() {
        account22 = new Account(1, "101022");
        account33 = new Account(2, "101033");
        account44 = new Account(3, "101044");
        account55 = new Account(4, "101055");
        account66 = new Account(5, "101066");
    }
}

