package com.onecbuying.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName CountDownLatchDemo
 * @company 公司
 * @Description CountDownLatch是jdk1.5包位于java.util.concurrent包，利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，
 * 此时就可以利用CountDownLatch来实现这种功能了
 * @createTime 2022年08月05日 19:42:42
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("等待子线程执行完毕...");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程,"+Thread.currentThread().getName()+"开始执行...");
                countDownLatch.countDown(); //每次减一
                System.out.println("子线程,"+Thread.currentThread().getName()+"结束执行...");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程,"+Thread.currentThread().getName()+"开始执行...");
                countDownLatch.countDown(); //每次减一
                System.out.println("子线程,"+Thread.currentThread().getName()+"结束执行...");
            }
        }).start();
        countDownLatch.await();
        System.out.println("两个子线程执行完毕...");
        System.out.println("继续主线程执行...");
    }
}
