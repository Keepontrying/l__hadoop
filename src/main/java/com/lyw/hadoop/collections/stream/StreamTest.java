package com.lyw.hadoop.collections.stream;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.hadoop.hdfs.web.JsonUtil;
import org.mortbay.util.ajax.JSON;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangxiaowu on 2018/9/6.
 */
public class StreamTest {

    public static List<Node> packageList(){
        List<Node> list = new ArrayList<>();
        list.forEach(x -> {
            x.setNodeId(RandomUtils.nextInt(100));
            x.setNodeName(RandomStringUtils.random(5, "梁玉武中国人名首富测试数据"));
            x.setNodeMessage(RandomStringUtils.random(20));
        });
        return list;
    }

    public static void main(String[] args) {
        List<Node> list = packageList();
        Node node = list.stream().max((x,y) -> x.getNodeId() - y.getNodeId()).get();
        System.err.println("最大的node="+ JSON.toString(node));
    }


    class Node implements Serializable{
        private String nodeName;
        private Integer nodeId;
        private String nodeMessage;

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public Integer getNodeId() {
            return nodeId;
        }

        public void setNodeId(Integer nodeId) {
            this.nodeId = nodeId;
        }

        public String getNodeMessage() {
            return nodeMessage;
        }

        public void setNodeMessage(String nodeMessage) {
            this.nodeMessage = nodeMessage;
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
        {

        }

        private void writeObject(ObjectOutputStream out)  throws IOException
        {

        }
    }
}
