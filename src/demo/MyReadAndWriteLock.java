package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 资源类
 */
class MyCache{
    // volatile 可见  不保证原子性  禁止指令重排
    private volatile Map<String,Object>  map= new HashMap<>();

    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key,Object value) throws InterruptedException {
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在写入..."+key);
            // 模拟网络拥堵
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成..."+key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwlock.writeLock().unlock();
        }

    }

    public void get(String key) throws InterruptedException {
        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在读...");
            // 模拟网络拥堵
            TimeUnit.MICROSECONDS.sleep(300);
            Object res = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完成..."+res);
        }catch (Exception e){

        }finally {
            rwlock.readLock().unlock();
        }

    }
}
/**
 * @author LM
 * @create 2019-09-02 22:03
 */

public class MyReadAndWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            //
            final int tmp = i;
            new Thread(()->{
                try {
                    myCache.put(tmp+"",tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            //
            final int tmp = i;
            new Thread(()->{
                try {
                    myCache.get(tmp+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
