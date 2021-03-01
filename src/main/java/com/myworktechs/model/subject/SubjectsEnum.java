package com.myworktechs.model.subject;

import lombok.Getter;

public enum SubjectsEnum {
    SAME_CLIENT(Client.class),
    SAME_SERVICE(Service.class),
    SAME_PAYMENT(SamePayment.class);

    @Getter
    private final Class<? extends Subject> clazz;

    SubjectsEnum(Class<? extends Subject> clazz) {
        this.clazz = clazz;
    }
}
