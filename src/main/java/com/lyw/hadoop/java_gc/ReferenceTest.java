package com.lyw.hadoop.java_gc;

import com.lyw.hadoop.collections.LinkedQueueTest;

/**
 * Created by wangxiaowu on 2018/8/29.
 */
public class ReferenceTest {
    /*int b = 10;
    static  int a = 10; //存放在_metadata InstanceKlass  ReferenceTest.class
    final String hello = "hello2";
    static ReentrantLockTest lock = new ReentrantLockTest();*/



    public static void main(String[] args) throws Exception, IllegalAccessException, InstantiationException {
//        String s = ClassLayout.parseClass(ReentrantLockTest.class).toPrintable();
//        Class klass =ClassLoader.getSystemClassLoader().loadClass("com.lyw.hadoop.sync_thread.ReentrantLockTest");
//        Object o =klass.newInstance();
//        String ins = ClassLayout.parseInstance(o).toPrintable();
//        System.err.println(s);
//        System.err.println(ins);

        LinkedQueueTest<String> queueTest = new LinkedQueueTest<>();
//        queueTest.offer("test");
        queueTest.offer("t");
        queueTest.offer("c");
        queueTest.offer("cc");
        queueTest.peek();
    }
}
