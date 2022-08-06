package com.onecbuying.deque;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author 速冻
 * @version 1.0
 * @ClassName ConcurrentLinkedDeque
 * @company 公司
 * @Description 无界限队列
 * ConcurrentLinkedDeque和BlockingQueue区别？
 *
 * BlockingQueue是可以阻塞、有界（什么是阻塞、什么是有界）
 * ConcurrentLinkedDeque是无界、无阻塞
 * @createTime 2022年08月06日 23:55:55
 */
public class ConcurrentLinkedDequeDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();
        concurrentLinkedDeque.offer("张三");
        concurrentLinkedDeque.offer("李四");
//        System.out.println(concurrentLinkedDeque.size());
//        System.out.println(concurrentLinkedDeque.poll());
//        System.out.println(concurrentLinkedDeque.poll());
//        System.out.println(concurrentLinkedDeque.peek());
//        System.out.println(concurrentLinkedDeque.peek());
//        System.out.println(concurrentLinkedDeque.size());
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.add("张三");
        arrayBlockingQueue.add("李四");
        arrayBlockingQueue.add("王五");
        //可以阻塞的队列
        arrayBlockingQueue.offer("王麻子",2, TimeUnit.SECONDS);
        System.out.println(arrayBlockingQueue.size());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
    }
}
