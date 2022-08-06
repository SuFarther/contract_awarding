package com.onecbuying.blockdeque;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName BlockingQueueDemo
 * @company 公司
 * @Description 使用并发阻塞队列实现生产和消费者
 * 阻塞队列的poll从头开始,会删除到尾部,消费者消费完就直接删除掉了
 * @createTime 2022年08月07日 00:11:11
 */
class ProducerThread extends Thread {
    private BlockingQueue queue;

    private volatile boolean flag = true;

    //保证数据的有序性
    private static AtomicInteger count = new AtomicInteger();

    ProducerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("生产者线程启动...");
        try {
            while (flag) {
                System.out.println("正在生产队列");
                String data = count.incrementAndGet() + "";
                //添加队列
                boolean offer = queue.offer(data);
                if (offer) {
                    System.out.println("生产者添加队列" + data + "成功!");
                } else {
                    System.out.println("生产者添加队列" + data + "失败!");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            System.out.println("生产者线程停止...");
        }
    }
    public void stopThread() {
        this.flag = false;
    }
}

class  ConsumerThread extends Thread {
    private BlockingQueue queue;

    private volatile boolean flag = true;

    //保证数据的有序性
    private static AtomicInteger count = new AtomicInteger();

    ConsumerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("消费者线程启动...");
        try{
            while(flag) {
                //获取完毕,队列会被删除掉
                String data = (String) queue.poll(2, TimeUnit.SECONDS);
                if(data!=null){
                    System.out.println("消费者获取 data:"+data+"成功...");
                }else{
                    System.out.println("消费者获取 data:"+data+"失败...");
                    this.flag= false;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("消费线程停止...");
        }
    }
}

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //
        BlockingQueue<String> queue = new LinkedBlockingDeque<String>(10);
        ProducerThread p1 = new ProducerThread(queue);
//        ProducerThread p2 = new ProducerThread(queue);
        ConsumerThread c1 = new ConsumerThread(queue);
        p1.start();
//        p2.start();
        c1.start();

        //执行10s
        Thread.sleep(10 * 1000);
        p1.stopThread();
//        p2.stopThread();
    }
}
