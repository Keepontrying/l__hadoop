package com.lyw.hadoop.java_gc;

/**
 * Created by wangxiaowu on 2018/8/29.
 */
public class ReferenceTest {

    public static void main(String[] args) {
        System.gc();
        {
            byte[] a = new byte[1024 * 1024 * 8];
        }
//        int b =0;
        System.gc();
    }
}
