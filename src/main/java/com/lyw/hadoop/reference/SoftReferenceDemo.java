package com.lyw.hadoop.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftReferenceDemo {
    public static void main1(String[] args) {

        String user = new String("username");
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        SoftReference<String> softReference = new SoftReference<>(user,referenceQueue) ;

        user = softReference.get();
        System.err.println(user);
        user = null;
        System.gc();
        System.err.println(referenceQueue.poll());
    }

    public static void main2(String[] args) {
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        String user = new String("lwy");
        WeakReference<String> weakReference = new WeakReference<>(user, referenceQueue);
        user = weakReference.get();
        System.err.println(user);
        user = null;
        System.gc();
        System.err.println(referenceQueue.poll());
    }


    public static void main(String[] args) {
        final Object o = new Object();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(()->{
                synchronized (o) {
                    System.err.println("多线程释放锁"+ finalI);
                    try {
                        o.wait();
                        Thread.interrupted();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println("线程等待限定时间");

                }


            }).start();

        }
    }
}
