package com.lyw.hadoop.java_io.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 类描述：广播客户端
 *
 * @author liangyuwu
 * @Time 2018/9/12 11:21
 */
public class MulticastClient {

    public static void main(String[] args) {
        try {
            MulticastSocket client = new MulticastSocket(10000);
            client.joinGroup(InetAddress.getByName("230.0.0.1"));
            byte[] buf = new byte[100];
            DatagramPacket packet = new DatagramPacket(buf, 50);
            while (true) {
                client.receive(packet);
                System.err.println("@@@"+new String(buf,0,packet.getLength()));
            }
        } catch (IOException e) {

        }

    }
}
