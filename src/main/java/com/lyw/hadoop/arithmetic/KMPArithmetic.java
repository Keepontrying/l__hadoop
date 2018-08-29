package com.lyw.hadoop.arithmetic;

/**
 * 类描述：字符串匹配算法
 *
 * @author liangyuwu
 * @Time 2018/8/28 15:40
 */
public class KMPArithmetic {

    char[] sub = {'a','b','c','d'};

    int count;
    int next;

    public int indexOf(String chars) {

        while (true) {
            char[] s = getsubChar(chars,next);
            lookup:
            for (int i = 0; i < s.length; i++) {
                for (int j = count; j < sub.length; j++) {
                    if (count == i && s[i] == sub[j]) {
                        count++;
                        continue lookup;
                    }
                    count = 0;
                    break lookup;
                }
            }

            if (count == sub.length) {
                return next-1;
            }

        }
    }

    public char[] getsubChar(String s,int index) {
        next = index + 1;
        if (next > s.length()) {
            return null;
        }
        String ss=s.substring(index, index + sub.length);
        return ss.toCharArray();
    }

    public int next(int j) {
        return j;
    }

    public static void main(String[] args) {
        KMPArithmetic k = new KMPArithmetic();
        char[] chars = "eafdadfasdfsaefasfesabcd".toCharArray();
        int i =  k.indexOf("eafdadfasdfsaefasfesssssssssssabcd");
        System.err.println("index="+i);
    }

}
