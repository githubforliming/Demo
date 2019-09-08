package demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author LM
 * @create 2019-09-08 20:05
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() +"\t" +"put 1");
                bq.put("1");
                System.out.println(Thread.currentThread().getName() +"\t" +"put 2");
                bq.put("2");
                System.out.println(Thread.currentThread().getName() +"\t" +"put 3");
                bq.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者").start();

        new Thread(()->{
            try {
                // 5秒之后 开始拿
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() +"\t" +bq.take());
                // 5秒之后 开始拿
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() +"\t" +bq.take());
                // 5秒之后 开始拿
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() +"\t" +bq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者").start();

    }
}
