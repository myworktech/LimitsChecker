package com.myworktechs.oldfilter;

/**
 * Не более 5000 руб. днем с 9:00 утра до 23:00 за одну услугу(*)
 */
public class OldFilter1  {
//
//    String name = "f1";
//    Long thresholdAmount = 5_000L;
//    long boundStart;
//    long boundEnd;
//
//    Map<Service, AtomicLong> amountPerService = new HashMap<>();
//
//    public OldFilter1() {
//        long now = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).getLong(ChronoField.MILLI_OF_DAY);
//        this.boundStart = now + TimeUnit.HOURS.toMillis(9L);
//        this.boundEnd = now + TimeUnit.HOURS.toMillis(23L);
//    }
//
//    /**
//     * @return true means payment is suspicious, false means payment is ok
//     */
//    @Override
//    public boolean check(Payment payment) {
//        boolean timeFilterMatch = boundStart <= payment.getTimestampMillis()
//                && boundEnd >= payment.getTimestampMillis();
//
//        if (timeFilterMatch) {
//            long accumulatedAmount = amountPerService.getOrDefault(payment.getService(), new AtomicLong(0L))
//                    .addAndGet(payment.getAmount());
//
//            return accumulatedAmount >= this.thresholdAmount;
//        } else
//            return false;
//    }
//
//    @Override
//    public int getWeight() {
//        return 100;
//    }
}
