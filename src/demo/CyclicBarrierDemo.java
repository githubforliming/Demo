package demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author LM
 * @create 2019-09-08 16:25
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(6,()->{
            System.out.println("越过屏障！！");
        });

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println("线程"+Thread.currentThread().getName()+"到达屏障");
                try {
                    cb.await();
                    System.out.println("线程"+Thread.currentThread().getName()+"越过屏障");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
