package com.lyw.hadoop.sync_thread;

import org.mortbay.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;
import sun.reflect.Reflection;

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


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        LockThread thread = new LockThread();
        for (int i = 0; i < 500; i++) {
            final int j = i;
            Thread runnable = new Thread(()->{
                thread.getLockName("lyw"+j);
            });
            runnable.start();
//            executorService.submit(runnable);
//            LockSupport.unpark(runnable);
//            runnable.interrupt();

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
