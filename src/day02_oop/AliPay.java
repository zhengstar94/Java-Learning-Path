package src.day02_oop;

public class AliPay extends BasePayment {
    @Override
    public void pay(double amount) {
        super.logTransaction(amount);
        System.out.println("[AliPay] processing amount" + amount);
    }
    
}
