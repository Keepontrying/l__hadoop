package com.lyw.hadoop.java_io.socket;

import java.net.*;
import java.util.concurrent.BlockingQueue;

/**
 * 类描述：
 *
 * @author liangyuwu
 * @Time 2018/9/12 17:46
 */
public class SocketServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(20000);
        Socket socket = serverSocket.accept();
        SocketAddress address = new InetSocketAddress(InetAddress.getByName("localhost"),20000);

//        socket.connect(address);

        socket.getOutputStream().write("test".getBytes());
    }

}
