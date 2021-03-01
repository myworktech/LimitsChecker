package com.myworktechs.oldfilter;

/**
 * Не более 3000 руб. в течение одного часа за одну услугу(*)
 */
public class OldFilter2 {

//    private static final long HOUR = TimeUnit.SECONDS.toMillis(2L);
//
//    String name = "f2";
//    Long thresholdAmount = 150L;
//    Map<Service, PaymentsAmountPair> paymentsPerService = new ConcurrentHashMap<>();
//
//
//    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//
//    {
//        executorService.scheduleAtFixedRate(new Cleaner(paymentsPerService), 0L, 1_000, TimeUnit.MILLISECONDS);
//    }
//
//    private static class Cleaner implements Runnable {
//        private final Map<Service, PaymentsAmountPair> paymentsPerService;
//
//        public Cleaner(Map<Service, PaymentsAmountPair> paymentsPerService) {
//            this.paymentsPerService = paymentsPerService;
//        }
//
//        @Override
//        public void run() {
//            log.info("Cleaner starting...");
//
//            long nowMillis = System.currentTimeMillis();
//            long now = 1_000L * ((nowMillis + 500) / 1000);
//
//            AtomicBoolean smthngWasRemoved = new AtomicBoolean(false);
//            paymentsPerService.values().forEach(paymentsAmountPair -> {
//                ConcurrentLinkedQueue<Payment> payments = paymentsAmountPair.getPayments();
//                boolean smthngWasRemove = payments.removeIf(p -> {
//                    boolean b = (now - p.getTimestampMillis()) > HOUR;
//                    if (b) {
//                        paymentsAmountPair.getTotalAmount().addAndGet(p.getAmount() * (-1));
//                    }
//                    return b;
//                });
//                smthngWasRemoved.set(smthngWasRemove);
//
//
//            });
//            log.info("Cleaner finished with: {}, in, ms: {}", smthngWasRemoved.get(), System.currentTimeMillis() - nowMillis);
//        }
//    }
//
//    /**
//     * @return true means decline payment, false means payment is ok
//     */
//    @Override
//    public boolean check(Payment payment) {
//
//        PaymentsAmountPair paymentsAmountPair = paymentsPerService.get(payment.getService());
//        if (paymentsAmountPair == null) {
//            paymentsAmountPair = new PaymentsAmountPair();
//            PaymentsAmountPair previous = paymentsPerService.putIfAbsent(payment.getService(), paymentsAmountPair);
//            paymentsAmountPair = previous != null ? previous : paymentsAmountPair;
//        }
//        paymentsAmountPair.getPayments().offer(payment);
//
//        AtomicLong totalAmount = paymentsAmountPair.getTotalAmount();
//
//        boolean result;
//
//        long oldValue;
//        long newValue;
//        do {
//            oldValue = totalAmount.get();
//            newValue = oldValue + payment.getAmount();
//            result = newValue >= thresholdAmount;
//        } while (!totalAmount.compareAndSet(oldValue, newValue));
//
//        return result;
//    }
//
//    @Override
//    public int getWeight() {
//        return 0;
//    }
//
//
//    @Getter
//    public static class PaymentsAmountPair {
//        private final ConcurrentLinkedQueue<Payment> payments = new ConcurrentLinkedQueue<>();
//        private final AtomicLong totalAmount = new AtomicLong(0L);
//    }

}
