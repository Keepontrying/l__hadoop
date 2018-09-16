package com.lyw.hadoop.collections;

import org.mortbay.util.ajax.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类描述：HashMap学习
 *
 * @author liangyuwu
 * @Time 2018/9/14 18:14
 */
public class MapTest<K,V> {

    static class Entity<K,V>{
        Entity<K,V> next,item;
        int hashcode;
        K k;
        V v;
        public Entity(){}
        public Entity(int hashcode,K k,V v,Entity<K,V> next){
            this.hashcode = hashcode;
            this.k=k;
            this.v=v;
            this.next=next;
        }
    }

    class ArrayMap{
        ArrayMap next;
        Entity[] items;
        public ArrayMap(){items = new Entity[16];arrCount=items.length;}
    }

    private ArrayMap head;
    private ArrayMap tail;
    private int arrCount;
    private int itemCount;

    public MapTest(){
        ArrayMap arrayMap = new ArrayMap();
        this.head=arrayMap;
        this.tail=arrayMap;
    }

    public Entity<K, V> put(K k,V v){
        int hashcode = k.hashCode() % arrCount;
        Entity[] its = head.items;
        if(itemCount<arrCount){//可以插入元素
            Entity<K,V> entity =new Entity<>(hashcode,k,v,null);
            if(its[hashcode] == null){
                its[hashcode]=entity;
            }else {
                Entity<K,V> old = its[hashcode];
                for(Entity<K,V> e = old,next=e.next;;){
                    if(e.k.equals(k)){
                        its[hashcode]=entity;
                        return old;
                    }else {
                        if(next==null){
                            e.next=entity;
                            return old;
                        }else {
                            e=next;
                        }
                    }
                }
            }
        }
        itemCount++;
        return null;
    }


    public static void main(String[] args) {
        MapTest<String,Integer> test = new MapTest<>();
        for (int i = 0; i < 20; i++) {
            test.put("tt"+i,10+i);
        }

        System.err.println("++"+ JSON.toString(test));
    }
}
