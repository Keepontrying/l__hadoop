package com.lyw.hadoop.sync_thread;

import org.mortbay.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
//    private static final Unsafe unsafe = Unsafe.getUnsafe();
    static final Logger log = LoggerFactory.getLogger(LockThread.class);

    void getLockName(String lockName) {

        try {
            log.error("锁编号1："+lock.toString());
            lock.lock();
            LockSupport.park();
            log.info("锁编号2："+lock.toString());
//            log.info("进入获取lock的方法,但未执行getLockName方法");
            log.error("等待线程数："+ JSON.toString(lock)+"="+lock.getQueueLength());
//            condition.await();
//            Thread.sleep(60000);
        } catch (Exception e) {

        }finally {
//            log.error("执行getLockName方法，打印name=" + lockName);
            lock.unlock();
        }

    }


    public static void main2(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        LockThread thread = new LockThread();
        for (int i = 0; i < 1; i++) {
            final int j = i;
            executorService.submit(()->{
                thread.getLockName("lyw"+j);
            });
        }
    }


    public static void main(String[] args) {
        System.err.println("lock之前");
        LockSupport.park();
        System.err.println("第一次park已经完成，进行第二次");
        System.err.println("o");
    }

}
