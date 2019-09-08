package demo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author LM
 * @create 2019-09-02 21:30
 * 自旋锁
 */
public class MyLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 枷锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"myLock");
        // 如果失败 一直循环
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"myUnLock");
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        new Thread(()->{
            // 获取锁
            myLock.myLock();
            try {
                Thread.sleep(5000);
                myLock.myUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        // 主线程睡眠5秒
        try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            // 获取锁
            myLock.myLock();
            try {
                Thread.sleep(5000);
                myLock.myUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
