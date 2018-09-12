package com.lyw.hadoop.java_io.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 类描述：广播
 *
 * @author liangyuwu
 * @Time 2018/9/12 11:14
 */
public class MulticastServer {

    public static void main(String[] args) {
        try {
            MulticastSocket multicastSocket = new MulticastSocket(10000);
            multicastSocket.joinGroup(InetAddress.getByName("230.0.0.1"));
            multicastSocket.setLoopbackMode(false);

            DatagramPacket send = new DatagramPacket("hellow".getBytes(),5);

            multicastSocket.send(new DatagramPacket("hellow".getBytes() ,0,InetAddress.getByName("230.0.0.1"),10000));

        } catch (IOException e) {

        }
    }
}
