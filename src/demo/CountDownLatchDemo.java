package demo;

import java.util.concurrent.CountDownLatch;

import static demo.CountryEnum.forEach_CountryEnum;

/**
 * @author LM
 * @create 2019-09-08 11:52
 * CountDownLatch 例子
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch c = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"灭了！");
                c.countDown();
            },CountryEnum.forEach_CountryEnum(i+1).getRetMessage()).start();
        }
        c.await();
        System.out.println("秦统一！");
    }
}
