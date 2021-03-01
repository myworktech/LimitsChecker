package com.myworktechs.service;

import com.myworktechs.model.FinalCheckResult;
import com.myworktechs.model.Payment;
import com.myworktechs.model.rule.Rule;
import com.myworktechs.model.subject.Client;
import com.myworktechs.model.subject.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoField.MILLI_OF_DAY;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GeneralTest {

    @Test
    public void test1() throws Throwable {
        Rule rule1 = Rule.m1();
        ProcessingService processingService = ProcessingServiceFactory.createProcessingService(List.of(rule1));
        Client client1 = new Client(1, "1");
        Service service1 = new Service(1, "1");


        long timestamp = LocalTime.of(14, 00).get(MILLI_OF_DAY);
        Payment payment1 = new Payment(UUID.randomUUID(), 10_000L, client1, service1, timestamp);


        FinalCheckResult process = processingService.process(payment1);
        assertTrue(process.isPaymentSuspicious());

    }
}
