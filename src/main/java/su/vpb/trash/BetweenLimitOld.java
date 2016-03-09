package su.vpb.trash;

import su.vpb.model.*;

import java.util.Date;

/**
 * Created by pechenkin on 16.09.2015.
 */
public class BetweenLimitOld extends OldAbstractLimit {
    Date startTime;
    Date endTime;

    public BetweenLimitOld(Integer amount, Date startTime, Date endTime) { //� ������ ������������ �������� ���� � ���� "9:00" ��� "23:00" ��� ����� ����� �����
        super(amount);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void checkPayment(Payment payment) {
        Integer sum = 0;
        for (Payment p : paymentList) {
            //if (p.getService()==payment.getService() && DateUtils.between(payment.getPaymentDate(), p.getPaymentDate())) //��� ��� ���������, �� ���� ����������)
//            if (p.getService().equals(payment.getService()) && DateUtils.between(DateUtils.getBoundOfInterval(payment.getPaymentDate(), startTime), p.getPaymentDate(), DateUtils.getBoundOfInterval(payment.getPaymentDate(), endTime)))
                sum += p.getAmount();
        }
        if (sum > amount) {
            payment.setStatus(PaymentStatus.SUBMIT_REQUIRED);
        }
//        System.out.println(payment.toString());
    }
}
