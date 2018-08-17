package com.lyw.hadoop.high_concurrence.util;

import com.lyw.hadoop.high_concurrence.beans.Order;

/**
 * Created by wangxiaowu on 2018/8/17.
 */
public class OrderThread extends Thread {
    private Order order;

    public OrderThread(){
        super();
    }

    public OrderThread(Order order){
        this.order = order;
    }

    public void run(){
        System.err.println(Thread.currentThread().getName()+"订单参数:"+order.getOrderId());
    }
}
