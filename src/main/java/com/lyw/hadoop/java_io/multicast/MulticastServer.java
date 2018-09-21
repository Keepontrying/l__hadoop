package com.lyw.hadoop.java_io.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 类描述：组广播 UDP协议
 *
 * @author liangyuwu
 * @Time 2018/9/12 11:14
 */
public class MulticastServer {
    /**
     *广播分类：单播，广播，组播
     *
     *单播：一对一通信，有2中协议实现。1. TCP协议：实现类Socket,ServerSocket。  2. UDP协议：DatagramSocket实现类
     *
     *组播：一对多通信，向一组地址服务器发数据。使用的是UDP协议。实现类是DatagramSocket子类--MulticastSocket，MulticastSocket。
     *      地址是从224.0.0.0到239.255.255.255。组播订阅者需要将本服务器加入到组内，joinGroup
     *
     *广播：广播地址内所有的地址都会接受消息。广播地址是主机位都是1
     *
     *UDP协议发送和传输数据类：DatagramPacket
     *
     *
     *IPv4格式是网络位（网络ID）+主机位，一共有五类IP地址

         A类地址：网络号占1个字节。网络号的第一位固定为0。
         B类地址：网络号占2个字节。网络号的前两位固定为10。
         C类地址：网络号占3个字节。网络号的前三位固定位110。
         D类地址：前四位是1110，用于多播(multicast)，即一对多通信。
         E类地址：前四位是1111，保留为以后使用。
     *
     *D类地址是多播地址，ABC是单播地址
     *本地广播地址：网络位和主机位都是1 11111111,1111....,1111....,1111.... =>255.255.255.255
     *广播地址：是主机位都是1
     */

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
