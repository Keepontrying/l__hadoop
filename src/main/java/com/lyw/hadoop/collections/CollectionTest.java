package com.lyw.hadoop.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.*;


/**
 * 类描述：数组分析、底层实现逻辑
 *
 * @author liangyuwu
 * @Time 2018/8/24 11:32
 */
public class CollectionTest {

    static final Logger log = LoggerFactory.getLogger(CollectionTest.class);

    static ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap();
    static Map<String, Object> hashMap = new HashMap<>();
    Map<String, Object> hashMap2 = new TreeMap<>();
    static Set<Integer> set = new HashSet<>();
    static Map<String,Object> map = new LinkedHashMap();

    Hashtable hashtable = new Hashtable();

    /**
     * 测试collections同步集合的静态方法
     * @param var
     */
   public void collectionsSync(){
       hashMap = Collections.synchronizedMap(hashMap);

   }

    public static void main(String[] args) {

        for (int i = 0; i < 500; i++) {
//            hashMap.put("lyw" + i, i);
        }

        for (int i = 0; i < 500; i++) {
            final int j =i;
            new Thread(() -> {
                log.info("多线程操作hashmap");
                hashMap.put("lyw", j);
//                hashMap.remove("lyw" + j);
            }).start();
        }
    }

   public void threadSafe(Integer var) {
        concurrentHashMap.put("id", concurrentHashMap.get("id")!=null?concurrentHashMap.get("id")+var:var);
        for (Map.Entry s : map.entrySet()) {

        }
    }


    public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K,V>> comparingByValue() {
        return (Comparator<Map.Entry<K, V>> & Serializable)
                (c1, c2) -> c1.getValue().compareTo(c2.getValue());
    }

    public static <String> Runnable test(){

        return null;
    }

    public static <String, Integer> Comparator<Map.Entry<String, Integer>> comparingByKey(Comparator<? super String> cmp) {
        Objects.requireNonNull(cmp);
        return (Comparator<Map.Entry<String, Integer>> & Serializable)
                (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
    }

    public static void main2(String[] args) {
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
