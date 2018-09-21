package com.lyw.hadoop.java_io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * 类描述：Java nio  同步非阻塞IO
 *
 * @author liangyuwu
 * @Time 2018/9/20 17:06
 */
public class Java_NIO {

    public void fileChannel(File source, File target) throws Exception {
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(target);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        //将输入流的数据直接读取到输出流
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
        in.close();
        out.close();
    }
}
