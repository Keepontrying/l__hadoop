package com.lyw.hadoop.java_gc;

import com.lyw.hadoop.cglib_learn.CglibProxy;

/**
 * Created by wangxiaowu on 2018/8/29.
 */
public class ReferenceTest {

    public static void main(String[] args) {
        ReferenceTest test = new ReferenceTest();
        test.fid();
        System.err.println("i");
    }

    private void fid() {
    }
}
