package com.lyw.hadoop.collections.stream;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.el.stream.Optional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * Created by wangxiaowu on 2018/9/6.
 */
public class StreamTest {

    public static List<Node> packageList(){
        List<Node> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Node d = new Node();
            d.setNodeId(12);
            list.add(d);
        }
        list.forEach(x -> {
            x.setNodeId(RandomUtils.nextInt(100));
            x.setNodeName(RandomStringUtils.random(5, "lywceshi测试数据"));
            x.setNodeMessage(RandomStringUtils.random(10,"中若人民没有一个是不认证的，请你们认清楚。一定会成为首富的"));
        });
        return list;
    }

    public static void main(String[] args) {
        List<Node> list = packageList();
        Node node = list.stream().max((x,y) -> x.getNodeId().compareTo(y.getNodeId())).get();
        System.err.println("最大的node="+ JSON.toJSONString(node));

        String s = list.stream()//.map(Node::getNodeId)
//                .map(Integer::valueOf)
//                .flatMap(x -> list.stream());
                .filter(x -> x.getNodeId() > 50)
//                .peek(System.err::println)
                .map(x -> {
                    try {
                        x.setNodeMessage(new ObjectMapper().writeValueAsString(x));
                    } catch (JsonProcessingException e) {

                    }
                    return x.getNodeMessage();
                })
                .limit(5)
                .collect(() -> new String(),(array, tt) -> array.concat(tt),(array, tt) -> array.contains(tt));
//                .reduce("seed",(x,y) -> {
//                    //自定义操作 seed初始值
//                    return x.substring(0,5);
//                });
//                .forEachOrdered(System.err::println);
//                .sorted()
//                .sorted(Integer::compareTo)
//                .forEach(System.err::println);
        System.err.println("++"+s);
    }


    static class Node implements Serializable{
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
