package com.lyw.hadoop.java_io.socket;

import java.net.*;
import java.util.Arrays;

/**
 * 类描述：
 *
 * @author liangyuwu
 * @Time 2018/9/12 18:09
 */
public class SocketClient {

    public static void main(String[] args) throws Exception {
        Socket client = new Socket();

        SocketAddress address = new InetSocketAddress(InetAddress.getByName("127.0.0.1"),20000);

        client.connect(address);

        byte[] b = new byte[10];
        while (true) {
            client.getInputStream().read(b);
            System.err.println("+"+ Arrays.toString(b));
        }
    }
}
