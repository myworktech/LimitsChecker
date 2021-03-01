package com.myworktechs.model.subject;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(of = "id")
public class Service implements Subject {
    long id;
    String name;
}
