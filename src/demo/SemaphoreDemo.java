package demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author LM
 * @create 2019-09-08 16:33
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 6个停车位  公平锁  来了之后就排队
        Semaphore sp = new Semaphore(6,true);

        // 20辆车
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                try {
                    // 占用车位
                    sp.acquire();
                    System.out.println(Thread.currentThread().getName()+"占到车位！  ");
                    // 停5秒钟
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"----》离开车位！  ");
                    // 释放停车位
                    sp.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
