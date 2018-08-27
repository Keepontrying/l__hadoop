package com.lyw.hadoop.cglib_learn;

/**
 * 类描述：
 *
 * @author liangyuwu
 * @Time 2018/8/27 16:23
 */
public class JDKProxyImpl implements JDKProxy {
    @Override
    public void sayJDKProxy() {
        System.err.println("I am jdk Proxy,implemented the interface JDKProxy");
    }
}
