package com.lyw.hadoop.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZkClient {
    private static Integer NUMBER=10;

    public static void getNumber(){
        System.err.println("获取共享资源:"+NUMBER);
        NUMBER--;

        System.err.println("剩余资源个数："+NUMBER);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.56.111:2181")
                .retryPolicy(retryPolicy)
                .build();
        client.start();

        final InterProcessMutex lock = new InterProcessMutex(client,"/mylock2");

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.acquire();
                        getNumber();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }

}
