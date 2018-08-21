package com.lyw.hadoop.high_concurrence.util;

import com.lyw.hadoop.high_concurrence.beans.Order;
import com.lyw.hadoop.sync_thread.SyncThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangxiaowu on 2018/8/17.
 */
public class OrderThread extends Thread {
    private Order order;

    static AtomicInteger counter = new AtomicInteger(0);
    final ReentrantLock reentrantLock = new ReentrantLock();

    public OrderThread(){
        super();
    }

    public OrderThread(Order order){
        this.order = order;
    }

    public void run(){
        synchronized (this) {
            System.err.println("into");
            counter.getAndIncrement();
        }
//        System.err.println(Thread.currentThread().getName()+"订单参数:"+order.getOrderId());
//        LockSupport.park();
//        reentrantLock.lock();

//        reentrantLock.unlock();
//        LockSupport.unpark(this);
    }

    public synchronized void syncMe() {
        int a = 5;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Thread thread = new OrderThread();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            final int j = i;
            SyncThread t = new SyncThread();
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    if (j == 0) {
                        t.setTimeout(60000);
//                        t.getAge();
//                        System.err.println("同步方法获取年龄--sleep："+t.getAge());
                        System.err.println("同步代码块--》"+t.syncCodeBlock(""));
                    }else{
                        t.setTimeout(0);
//                        t.getStaticSyncName();
//                        t.getSyncName();
//                        t.getName();
                        System.err.println("同步方法获取名字--》"+t.getSyncName());
//                        System.err.println("同步方法获取年龄--non_sleep"+t.getStaticSyncName());
//                        System.err.println("同步方法获取名字--》"+t.syncCodeBlock(j+""));

                    }
                    countDownLatch.countDown();

                }
            });
//            Thread.sleep(2000);

            //普通方法
            List<Integer> list = new ArrayList<>();
            list.forEach(inta -> {

            });

            /*new Thread(() ->{
                if (j == 0)
                {
                    t.setTimeout(6000);
                    t.notify();
                    System.err.println("普通方法：");
                    System.out.println();}}).start();*/
            //静态方法
            new Thread(() ->{
                if (j == 0)
                {
                    t.setTimeout(6000);
                    System.err.println("静态方法："+t.getStaticName());
                    System.out.println();
                }}).start();

            //非静态同步方法
            /*new Thread(() ->{
                if (j == 0)
                {
                    t.setTimeout(6000);
                    System.err.println("非静态同步方法："+t.syncCodeBlock("test2"));
                    System.out.println();
                }}).start();*/
            //静态同步方法
            /*new Thread(() ->{
                if (j == 0)
                {
                    t.setTimeout(6000);
                    System.err.println("静态同步方法："+t.getStaticSyncName());
                    System.out.println();
                }}).start();*/
        }
//        Thread.sleep(6000);
//        SyncThread.class.notify();
        countDownLatch.await();
        System.err.println("线程都执行结束");
        /**
         * 1.不会锁住普通方法
         * 2.会锁住静态同步方法
         * 3.不会锁住非静态同步方法
         * 4.不会锁住静态方法
         */

    }
}
