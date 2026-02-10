package src.day02_oop;

public abstract class BasePayment implements Payment {
    protected void logTransaction(double amount){
        System.out.println("[System log] processing amount" + amount);
    }
}
