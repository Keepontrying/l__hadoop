package com.lyw.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Lhadoop {
    private static Logger logger = LoggerFactory.getLogger(Lhadoop.class);

    static Integer Int = 0;
    public static void main2(String[] args) throws IOException {
        // 创建Configuration对象

        Configuration conf=new Configuration();
        String arg="hdfs://localhost:9000/user/wangxiaowu/out/part-r-00000";
        // 创建FileSystem对象
        FileSystem fs=
                FileSystem.get(URI.create(arg),conf);
        // 需求：查看/user/kevin/passwd的内容
        // args[0] hdfs://1.0.0.5:9000/user/zyh/passwd
        // args[0] file:///etc/passwd
        FSDataInputStream is=
                fs.open(new Path(arg));
        byte[] buff=new byte[1024];
        int length=0;
        while((length=is.read(buff))!=-1){
            System.out.println(
                    new String(buff,0,length));
        }
        System.out.println(
                fs.getClass().getName());
    }


    public void threadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, Integer.MAX_VALUE, 60L,
               TimeUnit.DAYS, new ArrayBlockingQueue(10), new ThreadPoolExecutor.AbortPolicy());

        Thread r = new Thread(()->{
            System.err.println("用户数据"+ Int);
            synchronized (this){
                Int++;
            }});
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(r);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Lhadoop l = new Lhadoop();
        l.threadPool();
        Thread.sleep(1000);
    }
}