package src.day02_oop;

public class PaymentDemo {
    public static void main(String[] args) {
        Payment myWallet = new AliPay();
        Payment yourWallet = new WeChatPay();
        myWallet.pay(99.0);
        yourWallet.pay(100.0);
    }
}
