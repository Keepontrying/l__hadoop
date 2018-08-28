package com.lyw.hadoop.sync_thread;

import javax.management.openmbean.CompositeDataSupport;
import java.lang.management.MemoryPoolMXBean;
import java.util.Collection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述：重入锁自定义实现
 *
 * @author liangyuwu
 * @Time 2018/8/28 10:38
 */
public class ReentrantLockTest {

    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final Object[] cache = new Object[8];
    int putptr,takeptr;
    volatile int count;

   public void put(Object x) {
        try {
            lock.lock();
            while (cache.length == count) {
                System.err.println("cache is full,please wait");
                notFull.await();
            }
            cache[putptr] = x;
            if (++putptr == cache.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        try{
            lock.lock();
            while (count == 0) {
                System.err.println("cache is empty...please wait");
                notEmpty.await();
            }
            Object x = cache[takeptr];
            if (++takeptr == cache.length) {
                takeptr = 0;
            }
            --count;
            notFull.signal();
            return x;
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
        return null;
    }


    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        Thread.currentThread().interrupt();
        new Thread(()->{

            for (int i = 0; i < 10; i++) {
                test.take();
            }
        }).start();

        /*new Thread(()->{
            for (int i = 0; i < 10; i++) {
                test.put(i);
            }
        }).start();*/
    }



    class InnerReentrantLock extends ReentrantLock{
        /**
         * 获取所有等待中的线程集合
         * 1、因为getQueuedThreads 是protected
         */
        public Collection<Thread> getAllQueuedThreads() {
            return getQueuedThreads();
        }

        /**
         * 获取持有当前锁的线程
         * 1、因为getOwner 是protected
         */
        public Thread getLockThread() {
            return getOwner();
        }

    }


}
