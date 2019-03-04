package com.lyw.hadoop.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wangxiaowu on 2018/11/2.
 */
public class PhanttomJS {

    static String file_path = "/Users/wangxiaowu/Downloads/551385082.html";
    static String img_path = "/Users/wangxiaowu/Downloads/551385082.png";
    private static String BLANK = " ";

    static String bin_path = "/Users/wangxiaowu/Desktop/phantomjs-2.1.1-macosx/bin/phantomjs";
    static String js_path = "/Users/wangxiaowu/Desktop/phantomjs-2.1.1-macosx/bin/ex.jd";

    public static void main(String[] args) {



    }

    // 执行cmd命令
    public static String cmd(String imgagePath, String url) {
        return bin_path + BLANK + js_path + BLANK + url + BLANK + imgagePath;
    }

    //关闭命令
    public static void close(Process process, BufferedReader bufferedReader) throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (process != null) {
            process.destroy();
            process = null;
        }
    }
    /**
     * @param userId
     * @param url
     * @throws IOException
     */
    public static void printUrlScreen2jpg(String url) throws IOException{
        String imgagePath = img_path;//图片路径
        //Java中使用Runtime和Process类运行外部程序
        Process process = Runtime.getRuntime().exec(cmd(imgagePath,url));
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String tmp = "";
        while ((tmp = reader.readLine()) != null) {
            close(process,reader);
        }
        System.out.println("success");
    }

    public static void main2(String[] args) throws IOException {
        String url = "https://www.baidu.com/";//以百度网站首页为例
        PhanttomJS.printUrlScreen2jpg(url);
    }
}
