package com.lyw.hadoop.java_io.socket;

import com.lyw.hadoop.design.singleton.SingletonEnum;

import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：
 *
 * @author liangyuwu
 * @Time 2018/9/12 18:09
 */
public class SocketClient {

    public static void main2(String[] args) throws Exception {
        Socket client = new Socket();

        SocketAddress address = new InetSocketAddress(InetAddress.getByName("127.0.0.1"),20000);

        client.connect(address);

        byte[] b = new byte[10];
        while (true) {
            client.getInputStream().read(b);
            System.err.println("+"+ Arrays.toString(b));
        }
    }

    public static void main(String[] args) {
        try {
            SocketClient.class.getClassLoader().loadClass("com.lyw.hadoop.design.singleton.SingletonEnum");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Map map = new HashMap(9);
        map.put("tst", "sff");
    }
}
