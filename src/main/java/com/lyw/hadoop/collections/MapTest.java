package com.lyw.hadoop.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类描述：HashMap学习
 *
 * @author liangyuwu
 * @Time 2018/9/14 18:14
 */
public class MapTest {

    private MapTest test;

    public static void main(String[] args) {
/*        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();

        linkedHashMap.put("test", "cc");
        linkedHashMap.put("he", "boy");*/

        String s = new String("hel");
        String str =s;
        str = s = new String("cctt");
        System.err.println(s == str);

        /**
         * 引用赋值和值赋值
         *1.基本数据类型是值赋值，该赋值可以传递a=b=c
         * 2.对象赋值是引用赋值，A=B,B=C 则A!=C
         */

        int a = 0;
        int c= a ;
        a=10;
        System.err.println(""+a);
    }
}
