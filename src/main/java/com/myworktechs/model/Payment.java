package com.myworktechs.model;

import com.myworktechs.model.subject.Client;
import com.myworktechs.model.subject.SamePayment;
import com.myworktechs.model.subject.Service;
import com.myworktechs.model.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class Payment {
    UUID id;
    Long amount;
    Client client;
    Service service;
    long timestampMillis;

    public Subject getSubject(Class<? extends Subject> clazz) { // todo refactor to map
        if (clazz.isInstance(client))
            return client;
        else if (clazz.isInstance(service))
            return service;
        else if (clazz == SamePayment.class)
            return SamePayment.fromPayment(this);
        else
            throw new IllegalArgumentException();
    }
}