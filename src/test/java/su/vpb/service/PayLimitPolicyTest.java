package su.vpb.service;


import org.joda.time.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import su.vpb.limit.*;
import su.vpb.model.Account;
import su.vpb.model.Payment;
import su.vpb.model.Service;
import su.vpb.limit.LimitStore;

import java.util.*;


/**
 * Created by pechenkin on 15.09.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class PayLimitPolicyTest {

    Service service1;
    Service service2;
    Service service3;
    Account account101022;
    Account account101033;
    Account account101044;
    Account account101055;
    Account account101066;
    @InjectMocks
    private PayLimitPolicy payLimitPolicy;

//    @Mock
//    public Set<Limit> limits= new HashSet<Limit>();
//
//    @Mock
//    public static List<Payment> paymentList = new ArrayList<Payment>();


    @Before
    public void setUp() throws Exception {

        initLimits();
        initAccounts();
        initServices();
        initPayments();

    }

    @After
    public void clean() {

    }

    @Test
    public void initPayments() throws Exception {
        //4
        new Payment(1500, account101022, service1, DateTime.parse("2016-01-01T08:10"));
        new Payment(1500, account101022, service2, DateTime.parse("2016-01-01T08:20"));
        new Payment(2500, account101033, service1, DateTime.parse("2016-01-01T08:30"));
        new Payment(1500, account101022, service2, DateTime.parse("2016-01-01T08:50"));
        //4+1
        new Payment(1500, account101022, service1, DateTime.parse("2016-01-01T09:10"));
        new Payment(1000, account101033, service1, DateTime.parse("2016-01-01T09:30"));
        new Payment(1000, account101033, service1, DateTime.parse("2016-01-01T10:00"));
        new Payment(2000, account101033, service1, DateTime.parse("2016-01-01T19:30"));
    }

    @Test
    public void newPayment() {
        Payment p = new Payment(1000, account101022, service1, DateTime.parse("2016-01-01T23:10"));
        for (Limit limit:LimitStore.INSTANCE){
            limit.check(p);
        }
    }

    private void initLimits() {

//        // 1. Не более 5000 руб. днем с 9:00 утра до 23:00 за одну услугу(*)
//        new Builder(10,20).build()
//                .setAmount(5000)
//                .setBound("09:00")
//                .setBound("23:00")
//                    .setSameService();

//// 3. Не более 2000 руб. в сутки по одинаковым платежам(**)
//        Builder.build()
//                .setInterval("24:00")
//                .setAmount(2000)
//                .setSamePayment(); //если убрать это, получится, 2000 руб. в сутки вообще по всем платежам
//
//// 4. Не более 3000 руб. в течение одного часа за одну услугу(*)
//        Builder.build()
//                .setAmount(3000)
//                .setInterval("01:00")
//                .setSameService();
//
//// 5. Не более 20 одинаковых платежей(**) в сутки
//        Builder.build()
//                .setSamePaymentCount(20)
//                .perInterval("24:00);
//
//
//// 6. Не более 40 платежей не более чем на 4000 руб.(***) с 10:00 до 17:00 за одну услугу(*)
//        Builder.build()
//                                .setSamePaymentCount(40)
//                                .setAmount(4000)
//                                .setBound("10:00")
//                                .setBound("17:00")
//                                .setSameService();
//
//// 7. Не более 10 платежей не более чем на 3000 руб.(***) в течение двух часов в пользу одного клиента
//        Builder.build()
//                .setPaymentCount(10)
//                .setAmount(3000)
//                .setInterval("02:00")
//                .setSameClient()

        LimitStore.INSTANCE.add(new Builder().setBoundStart("").setBountEnd("").build());
        LimitStore.INSTANCE.add(new Builder().setBoundStart("").setBountEnd("").build());
        LimitStore.INSTANCE.add(new Builder().setBoundStart("").setBountEnd("").build());
    }

    private void initServices() {
       service1 = new Service("Mobile phone");
       service2 = new Service("Bank account");
       service3 = new Service("E-shop acc");
    }

    private void initAccounts() {
        account101022 = new Account("101022");
        account101033 = new Account("101033");
        account101044 = new Account("101044");
        account101055 = new Account("101055");
        account101066 = new Account("101066");
    }
}

