package src.day03_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService bankHall = Executors.newFixedThreadPool(3);
        System.out.println("银行开始营业...");

        for (int i = 1; i <= 5; i++) {
            final int customerId = i;

            bankHall.submit(() -> {
                String tellerName = Thread.currentThread().getName();
                System.out.println("柜员 [" + tellerName + "] 正在接待客户 " + customerId);

                try {
                    // 模拟办理业务耗时 1 秒
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("柜员 [" + tellerName + "] --> 客户 " + customerId + " 办理完毕");
            });
        }
        
        bankHall.shutdown();
        System.out.println("银行停止取号，等待现有业务处理完毕...");
    }
}
