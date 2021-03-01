package com.myworktechs.oldfilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@RunWith(JUnit4.class)
public class Filter2Test {


    private static final long NOT_IMPORTANT_CLIENT_ID = 0L;
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Test
    public void t1() throws Throwable {

//        OldFilter oldFilter = new OldFilter2();

//        Service service1 = new Service(1L, "service 1");
//        Service service2 = new Service(2L, "service 2");
//
//
//
//        //        long start = System.currentTimeMillis();
//
//        //        while (long now = System.currentTimeMillis() - start < 3_000L ) {
//        //
//        //
//        //        }
//
//        //        for (long now = System.currentTimeMillis();(now - start) < 3_000L; now = System.currentTimeMillis()) {
//        //
//        //            long nowSecondsRound = 1_000L * ((now + 500) / 1000);
//        //            System.out.println(nowSecondsRound);
//        //        }
//
//        CountDownLatch countDownLatch = new CountDownLatch(8);
//
//        executorService.scheduleAtFixedRate(() -> {
//            Payment payment = new Payment(UUID.randomUUID(), 50L, new Client(1,""), service1, System.currentTimeMillis());
//
//            boolean check = oldFilter.check(payment);
//            System.out.println("Result: " + check);
//            countDownLatch.countDown();
//
//
//        }, 0L, 500, TimeUnit.MILLISECONDS);
//
//        countDownLatch.await();

    }
}