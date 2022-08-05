package com.onecbuying.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName SemaphoreDemo
 * @company 公司
 * @Description 计数信号量
 * Semaphore是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。Semaphore可以用来构建一些对象池，资源池之类的，比如数据库连接池，我们也可以创建计数为1的Semaphore，将其作为一种类似互斥锁的机制，这也叫二元信号量，表示两种互斥状态。它的用法如下：
 * availablePermits函数用来获取当前可用的资源数量
 * wc.acquire(); //申请资源
 * wc.release();// 释放资源
 * 需求: 一个厕所只有3个坑位，但是有10个人来上厕所，那怎么办？假设10的人的编号分别为1-10，
 * 并且1号先到厕所，10号最后到厕所。那么1-3号来的时候必然有可用坑位，顺利如厕，
 * 4号来的时候需要看看前面3人是否有人出来了，如果有人出来，进去，否则等待。同样的道理，
 * 4-10号也需要等待正在上厕所的人出来后才能进去，并且谁先进去这得看等待的人是否有素质，
 * 是否能遵守先来先上的规则
 * @createTime 2022年08月06日 02:57:57
 */
class Parent implements Runnable{
    private  String name;
    private Semaphore semaphore;

    public Parent(String name,Semaphore semaphore){
        this.name = name;
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
       try{
           // 剩下的资源(剩下的坑位)
           int availablePermits = semaphore.availablePermits();
           if(availablePermits > 0) {
               System.out.println(name+"天助我也,终于有茅坑了...");
           }else{
               System.out.println(name+"怎么没有有茅坑了...");
           }
           // 申请茅坑,如果资源达到3次，就等待
           semaphore.acquire();
           System.out.println(name+"终于轮到我上厕所了...爽呀");
           //模拟上厕所时间
           Thread.sleep(new Random().nextInt(1000));
           System.out.println("厕所上完了...");
           semaphore.release();
       }catch (Exception e) {
           e.printStackTrace();
       }
    }
}


public class SemaphoreDemo {
    public static void main(String[] args) {
        // 一个厕所只有3个坑位，但是有10个人来上厕所，那怎么办？假设10的人的编号分别为1-10，并且1号先到厕所，
        // 10号最后到厕所。那么1-3号来的时候必然有可用坑位，顺利如厕，4号来的时候需要看看前面3人是否有人出来了，
        // 如果有人出来，进去，否则等待。同样的道理，4-10号也需要等待正在上厕所的人出来后才能进去，
        // 并且谁先进去这得看等待的人是否有素质，是否能遵守先来先上的规则。
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10 ; i++) {
            Parent parent = new Parent("第"+i+"个人",semaphore);
            new Thread(parent).start();
        }
    }
}
