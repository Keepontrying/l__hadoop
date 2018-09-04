package com.lyw.hadoop.java_gc;

import com.lyw.hadoop.sync_thread.ReentrantLockTest;

/**
 * Created by wangxiaowu on 2018/8/29.
 */
public class ReferenceTest {
    int b = 10;
    static  int a = 10; //存放在_metadata InstanceKlass  ReferenceTest.class
    final String hello = "hello2";
    static ReentrantLockTest lock = new ReentrantLockTest();



    public static void main(String[] args) {
        ReferenceTest test = new ReferenceTest();
        test.fid();
        System.err.println("i");
    }

    private void fid() {
    }
}
