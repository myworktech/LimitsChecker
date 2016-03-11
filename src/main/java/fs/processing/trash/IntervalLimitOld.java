package fs.processing.trash;

import fs.processing.model.Payment;
import fs.processing.model.PaymentStatus;

/**
 * Created by pechenkin on 23.09.2015.
 */

//�� ����� 3000 � ������� ������ ���� �� ���� ������
public class IntervalLimitOld extends OldAbstractLimit {
    Long timeInterval;


    public IntervalLimitOld(Integer amount, Long timeInterval) {
        super(amount);
        this.timeInterval=timeInterval;

    }

    @Override
    public void checkPayment(Payment payment) { //TODO ��� �������� ��������, � ����� ������ ��� ������� ��������, � �� ��� �� ����� ������ �����?
        Integer sum = 0;
        for (Payment p : paymentList) {
//            if ( p.getService().equals(payment.getService()) && (DateUtils.dateDiff(p.getPaymentDate(), payment.getPaymentDate())<=timeInterval))
                sum += p.getAmount();
//            System.out.println(sum);
        }
        if (sum > amount) {
            payment.setStatus(PaymentStatus.SUBMIT_REQUIRED);
        }
//        System.out.println(payment.toString());
    }
}