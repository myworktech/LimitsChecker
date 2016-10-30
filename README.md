# LimitsChecker
Test quiz, given during employment to the bank.

In solution it is important to show good experience using OOP and Java Core, this is the main points we are estimating.
In the task expecting some abstract system of payment processing. When payment received by the system it is passed to the processing.
Payments accepting by some amount, from an account to a service.

The task is to add subsystem of payment limits to prevent fraud.
The limits may be tuning by all fields.
Just for example:

1. Not more $5000, from 9:00AM to 11:00PM by same service.
2. Not more $2000, per day by same payments. (Same payment means the same account and service)
3. Not more $3000, per hour by same service.
4. Not more 20 same payments per day.
5. Not more 30 payments not more $4000 from 10:00AM to 17:00AM per service.
6. Not more 10 payments not more $3000 per two hours by same account.

In case of exceed one of limits payment have to be marked as "Submit required". If payment passes limits it should be marked as "Ready to process".
Solution must have at least one unit test for every limit and at least one test with mixed limits, so primitive payment acceptance system must be implemented.
For unit tests systems should "know" about a couple of clients and services.
Solution must satisfy next requirements:
1. Use Java, preferably Java 8.
2. JUnit test.
3. Using OOP and design patterns is important.
4. All necessary external system must be simple Java objects, no technologies like spring or jpa.
5. All external libraries (for datetime transformations, for example) must be referenced using maven or gradle.
6. Do not use GUI, databassess, file or network input/output, concurrency.

##
My solution can be improved by grouping limit restrictions in two groups: filtrating and restricting.
Filtrating would be such criterias as time bounds, time limits, same services or accounts. Restricting would be money amount and payments count.
That way logic of Limit class can be separated into some fine-grained smaller classes.
The client would be
```java
new LimitBuilder()
                .setFilters(Filters.create().setBoundStart(LocalDateTime.parse("2016-01-01T09:30")).setBoundEnd(LocalDateTime.parse("2016-01-01T19:51"))
                .setRestrictions(Restriction.create().setPaymentCount(2L).setAmount(1000L))
                .build())
```
Also money should be `BigDecimal` rather then long`