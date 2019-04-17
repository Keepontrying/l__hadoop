package com.lyw.hadoop.arithmetic;

import java.util.Arrays;

/**
 * 类描述：字符串匹配算法
 *
 * @author liangyuwu
 * @Time 2018/8/28 15:40
 */
public class KMPArithmetic {

    char[] sub = {'s','a','b','c','d'};

    int count;
    int next;
    char[] match = new char[sub.length];

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

    /**
     * 子串匹配，返回索引位置KMP算法
     * <p>1. 主串回溯位置next,next=i-j+1,模式串匹配</p>
     * <p>2. 记录已经匹配的字符串</p>
     * <p>3. 根据配置字符串找到next（主串跳转的位置）</p>
     * @param source
     * @param target
     * @return
     */
    int matchStr(String source, String target) {
        byte[] s_bytes = source.getBytes();
        byte[] t_bytes = target.getBytes();
        StringBuilder match_str = new StringBuilder();
        int next = 0;
        int j = 0;
        int index = 0;
        int[] next_arr = next(match_str);

        lookup :
        for (int i = 0; i <s_bytes.length ; i++) {
            byte s = s_bytes[i];
            for (j = 0; j < t_bytes.length; j++) {
                byte t = t_bytes[j];
                if (s == t) {
                    match_str.append(s);
                    continue lookup;
                }
                //不相等，决定主串移动位置
                next = next_arr[j];
                j = j - next;
            }
            if (j == t_bytes.length) {
                index = i - j + 1;
                return index;
            }
        }
        return -1;
    }

    /**
     * 获取前缀、后缀的最大匹配长度
     * @param match_str
     * @return
     */
    public int[] next(StringBuilder match_str) {
        int[] next = new int[match_str.length()];
        byte[] n_byte = match_str.toString().getBytes();
        next[0] = -1;//循环查找
        next[1] = 0;
        int k = 0;
        for (int i = 2; i < n_byte.length; i++) {
            k = next[i-1];//获取前一个子字符串的最大匹配长度
            while (k != -1) {
                if (n_byte[i-1] == n_byte[k]) {//位置向右移动一位
                    next[i] = k + 1;
                    break;
                }else {
                    k = next[k];//查找子字串的匹配长度，和子串的末尾比较
                }
                next[i] = 0;
            }
        }
        return next;
    }

    public int searchIndexOf(String text, String pattern) {
        int i = 0,j=0;
        lookup:
        for (i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            for (j = 0; j < pattern.length(); j++) {
                char sh = pattern.charAt(j);
                if (ch == sh) {
                    continue lookup;
                }
            }
        }
        return 0;
    }

    public char[] getsubChar(String s,int index) {
        next = index + 1;
        if (next > s.length()) {
            return null;
        }
        String ss=s.substring(index, index + sub.length);
        return ss.toCharArray();
    }


    public static void main(String[] args) {
        KMPArithmetic k = new KMPArithmetic();
        char[] chars = "eafdae".toCharArray();
//        int i =  k.indexOf("eafdadfasdfsaefasfesssssssssssabcd");
//        System.err.println("index="+i);
        int[] c = k.next(new StringBuilder("abcabcab"));
        System.err.println(Arrays.toString(c));
    }

}
