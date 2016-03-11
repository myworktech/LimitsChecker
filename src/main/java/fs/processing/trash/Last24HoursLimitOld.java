package fs.processing.trash;

import fs.processing.model.Payment;
import fs.processing.model.PaymentStatus;

/**
 * Created by pechenkin on 24.09.2015.
 */
public class Last24HoursLimitOld extends OldAbstractLimit {



    public Last24HoursLimitOld(Integer amount) {
        super(amount);


    }

    @Override
    public void checkPayment(Payment payment) { //TODO ��� �������� ��������, � ����� ������ ��� ������� ��������, � �� ��� �� ����� ������ �����?
        Integer sum = 0;
        for (Payment p : paymentList) {
//            if ( p.getAccount().equals(payment.getAccount()) && (DateUtils.dateDiff(p.getPaymentDate(), payment.getPaymentDate())<=24))
                sum += p.getAmount();
//            System.out.println(sum);
        }
        if (sum > amount) {
            payment.setStatus(PaymentStatus.SUBMIT_REQUIRED);
        }
//        System.out.println(payment.toString());
    }
}