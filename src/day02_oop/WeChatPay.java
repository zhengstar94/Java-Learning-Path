package src.day02_oop;

public class WeChatPay extends BasePayment{

    @Override
    public void pay(double amount) {
        super.logTransaction(amount);
        System.out.println("[WeChatPay] processing amount" + amount);
    }
    
}
