package com.lyw.hadoop.sync_thread;

import org.mortbay.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;
import sun.reflect.Reflection;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述：ReenTrantLock重入锁
 *
 * @author liangyuwu
 * @Time 2018/8/22 10:13
 */
public class LockThread {

    final ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private static final Unsafe unsafe = UnsafeUtils.getUnsafe();
    static final Logger log = LoggerFactory.getLogger(LockThread.class);
    static volatile int count = 0;

    void getLockName(String lockName) {//普通方式++ 模拟：线程不安全

        try {
            log.error("操作前count："+count);

            int a = count;
            if ("lyw50".equals(lockName)) {
                Thread.sleep(1000);
            }
            count = a + 1;
            log.error("操作之后count："+ count);
        } catch (Exception e) {

        }finally {

        }
    }

    //ReentrantLock方式   问题：如果当前调用了lock方法，未调用unlock方法，其他线程无法后去lock,怎么解决当前线程unlock
    void shareCount(String var1){
        log.info("未lock之前："+count);
        lock.lock();
        int a = count;
        log.info("锁住后，未操作："+a);
        if ("lyw50".equals(var1)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count = a + 1;
        log.info("操作之后count="+count);
        lock.unlock();
    }

    //LockSupport方式
    void lockSupport(String var1) {//不适用线程同步，适用线程阻塞
        LockSupport.park();//阻塞当前线程
        int a = count;
        log.info("锁住后，未操作："+a);
        if ("lyw50".equals(var1)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count = a + 1;
        log.info("操作之后count="+count);

    }

    //synchronized锁方法 方式
    synchronized void synchronizedLock(String var1) {
        int a = count;
        log.info("锁住后，未操作："+a);
        if ("lyw50".equals(var1)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count = a + 1;
        log.info("操作之后count="+count);
    }

    //synchronized锁对象 方式
     void synchronizedBlockLock(String var1) {
         System.err.println("进入对象，未获取monitor等待中....");
         synchronized (LockThread.class) {//如果是类型类的话LockThread.class，不同的对象也会锁住    如果是this,相同的对象话，会锁住，不同对象锁不住
             int a = count;
             log.info("锁住后，未操作："+a);
             if ("lyw50".equals(var1)) {
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
             count = a + 1;
             log.info("操作之后count="+count);
         }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        LockThread thread = new LockThread();
        for (int i = 0; i < 500; i++) {
            final int j = i;
            Thread runnable = new Thread(()->{
                thread.synchronizedBlockLock("lyw"+j);
            });
            runnable.start();
//            executorService.submit(runnable);
//            LockSupport.unpark(runnable);
//            runnable.interrupt();
            LockSupport.unpark(runnable);

//            System.err.println(runnable.getName()+"  不阻断主线程:"+runnable.isAlive());
        }

        new Thread(()->{
            for(;;) {
                if(count == 500){
                    System.err.println(count);
                    break;
                }
            }
        }).start();

    }

}
