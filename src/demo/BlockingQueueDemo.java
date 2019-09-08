package demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author LM
 * @create 2019-09-08 17:00
 *
 * ArrayBlockingQueue :是一个基于数组结构的有界阻塞队列，此队列按FIFO 原则对元素进行排序
 *
 * LinkedBlockingQueue 一个基于链表结构的阻塞队列，FIFO ,吞吐量高于ArrayBlockingQueue
 *
 * SynchronousQueue 一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态,吞吐量高于Lincked..
 *
 * PriorityBlockingQueue  优先级排序的无界阻塞队列
 *
 * DelayQueue 优先级实现的延迟无界阻塞队列
 *
 * LinkedTransferQueue 链表组成的无界阻塞队列
 *
 * LinkedBlockingDeque 链表组成的双向阻塞队列
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        bq.offer("aa",2L,TimeUnit.SECONDS);
        bq.offer("bb",2L,TimeUnit.SECONDS);
        // 这里就会阻塞
        bq.offer("cc",2L,TimeUnit.SECONDS);


        System.out.println(bq.poll(2L,TimeUnit.SECONDS));
        System.out.println(bq.poll(2L,TimeUnit.SECONDS));
        // 如果没有数据 就阻塞
        System.out.println(bq.poll(2L,TimeUnit.SECONDS));

    }
}
