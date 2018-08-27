package com.lyw.hadoop.cglib_learn;

/**
 * 类描述：已经启动的线程
 *
 * @author liangyuwu
 * @Time 2018/8/27 14:36
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.err.println("my thread is started !");
    }
}
