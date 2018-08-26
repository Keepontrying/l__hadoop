package com.lyw.hadoop.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 类描述：数组分析、底层实现逻辑
 *
 * @author liangyuwu
 * @Time 2018/8/24 11:32
 */
public class CollectionTest {

    static final Logger log = LoggerFactory.getLogger(CollectionTest.class);

    static ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap();
    Map<String, Object> hashMap = new HashMap<>();
    Set<Integer> set = new HashSet<>();


    public void threadSafe(Integer var) {
        concurrentHashMap.put("id", concurrentHashMap.get("id")!=null?concurrentHashMap.get("id")+var:var);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CollectionTest test = new CollectionTest();
        for (int i = 1; i < 101; i++) {
            final int j = i;
            executorService.execute(()->{
                test.threadSafe(j);
            });
        }


        new Thread(() ->{
            concurrentHashMap.get("id");
        }).start();
    }

}
