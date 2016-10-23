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
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.junit.Assert.assertEquals;

public class PaymentServiceTest {

    Service service1;
    Service service2;
    Account account11;
    Account account22;

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
        paymentListSource.add(new Payment(1000L, account11, service1, LocalDateTime.parse("2016-01-01T08:10")));
        paymentListSource.add(new Payment(1000L, account22, service1, LocalDateTime.parse("2016-01-01T08:20")));
        paymentListSource.add(new Payment(2000L, account11, service2, LocalDateTime.parse("2016-01-01T08:30")));
        paymentListSource.add(new Payment(2000L, account22, service2, LocalDateTime.parse("2016-01-01T08:40")));

        paymentListSource.add(new Payment(3000L, account11, service1, LocalDateTime.parse("2016-01-01T08:50")));
        paymentListSource.add(new Payment(3000L, account22, service1, LocalDateTime.parse("2016-01-01T09:00")));
        paymentListSource.add(new Payment(4000L, account11, service2, LocalDateTime.parse("2016-01-01T10:00")));
        paymentListSource.add(new Payment(4000L, account22, service2, LocalDateTime.parse("2016-01-01T12:30")));

        paymentListSource.add(new Payment(5000L, account11, service1, LocalDateTime.parse("2016-01-01T18:00")));
        paymentListSource.add(new Payment(5000L, account22, service1, LocalDateTime.parse("2016-01-01T18:30")));
        paymentListSource.add(new Payment(6000L, account11, service2, LocalDateTime.parse("2016-01-01T19:00")));
        paymentListSource.add(new Payment(6000L, account22, service2, LocalDateTime.parse("2016-01-01T19:30")));
    }

    @Test
    public void testNoLimits() throws Exception {

        limitList.add(new LimitBuilder()
                .setBoundStart(LocalDateTime.parse("2016-01-01T08:09"))
                .setBoundEnd(LocalDateTime.parse("2016-01-01T19:10"))
                .setInterval(Duration.of(30L, MINUTES))
                .build());
        paymentService.addLimits(limitList);

        for (Payment payment : paymentListSource)
            paymentService.process(payment);

        for (Payment p : paymentService.getPaymentList()) {
            assertEquals(p.getStatus(), PaymentStatus.READY_TO_PROCESS);
        }
        paymentService.print();
    }

    @Test
    public void testTimeBoundsAndSamePaymentCount() throws Exception {
        limitList.add(new LimitBuilder()
                .setBoundStart(LocalDateTime.parse("2016-01-01T08:09"))
                .setBoundEnd(LocalDateTime.parse("2016-01-01T19:10"))
                .setSamePaymentCount(2L)
                .build());
        paymentService.addLimits(limitList);

        for (Payment payment : paymentListSource)
            paymentService.process(payment);

        List<Long> ids = Arrays.asList(9L, 10L, 11L);
        paymentService.getPaymentList().stream()
                                .filter(p -> ids.contains(p.getId()))
                                .forEach(p -> assertEquals(p.getStatus(), PaymentStatus.SUBMIT_REQUIRED));
        paymentService.print();
    }

    @Test
    public void testTimeBoundsAndAmount() throws Exception {
        limitList.add(new LimitBuilder()
                .setBoundStart(LocalDateTime.parse("2016-01-01T08:09"))
                .setBoundEnd(LocalDateTime.parse("2016-01-01T19:10"))
                .setAmount(3000L)
                .build());
        paymentService.addLimits(limitList);

        for (Payment payment : paymentListSource)
            paymentService.process(payment);

        List<Long> ids = Arrays.asList(1L, 2L, 12L);
        paymentService.getPaymentList().stream()
                                .filter(p -> ids.contains(p.getId()))
                                .forEach(p -> assertEquals(p.getStatus(), PaymentStatus.READY_TO_PROCESS));
        paymentService.print();
    }

    @Test
    public void testTimeBoundComplex() throws Exception {

        limitList.add(new LimitBuilder()
                .setBoundStart(LocalDateTime.parse("2016-01-01T08:00"))
                .setBoundEnd(LocalDateTime.parse("2016-01-01T19:00"))
                .setPaymentCount(2L)
                .setAmount(1L)
                .build());

        limitList.add(new LimitBuilder()
                .setBoundStart(LocalDateTime.parse("2016-01-01T08:05"))
                .setBoundEnd(LocalDateTime.parse("2016-01-01T08:51"))
                .setPaymentCount(2L)
                .build());

        paymentService.addLimits(limitList);

        for (Payment payment : paymentListSource)
            paymentService.process(payment);

        paymentService.print();
    }

    private void initServices() {
        service1 = new Service(1L, "Mobile phone");
        service2 = new Service(2L, "Bank account");
    }

    private void initAccounts() {
        account11 = new Account(1L, "101011");
        account22 = new Account(2L, "101022");
    }
}

