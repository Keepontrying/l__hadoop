package com.lyw.hadoop.java_io.in_out_stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 类描述：管道流
 * output持有input对象，通过connect()连接
 * pipedOutputStrea将数据写入到input 缓存buffer，input从buffer读取数据
 *
 * @author liangyuwu
 * @Time 2018/9/20 15:32
 */
public class PipedStream {

    public static void main(String[] args) {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        try {
            //连接2个管道
            pipedInputStream.connect(pipedOutputStream);

            new Thread(() -> {
                try {
                    pipedOutputStream.write("test".getBytes());
                } catch (IOException e) {

                }
            }).start();

            new Thread(() -> {
                try {
                    int red=pipedInputStream.read();
                    System.err.println("从另外一个线程中读取数据"+red);
                } catch (IOException e) {

                }
            }).start();

        } catch (IOException e) {

        }
    }
}
