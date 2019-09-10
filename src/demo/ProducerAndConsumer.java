package demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number = 0;
    Lock lock  = new ReentrantLock();
    // Condition :线程间通信
    private Condition condition  = lock.newCondition();
    // ++
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while(number != 0) {
                // 等待不能生产
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"number++");
            // 通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    // --
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while(number == 0) {
                // 等待不能生产
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"number--");
            // 通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
/**
 * @author LM
 * @create 2019-09-08 20:16
 * 生产者 消费者  传统
 * 题目： 初始值为0的变量，两个线程对其交替操作，一个加1 一个减1 来5轮
 *
 * 1. 线程  操作(方法)  资源类
 * 2. 判断  干活   通知
 * 3. 防止虚假唤醒机制  什么叫虚假唤醒 ？？
 *
 *
 * 注意： 判断要用while
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
