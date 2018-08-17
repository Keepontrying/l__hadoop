package com.lyw.hadoop.high_concurrence.util;

import com.lyw.hadoop.high_concurrence.beans.Order;

import java.util.concurrent.Callable;

/**
 * Created by wangxiaowu on 2018/8/17.
 */
public class OrderCallable implements Callable {

    private Order order;

    public OrderCallable(){
        super();
    }

    public OrderCallable(Order order){
        this.order = order;
    }

    @Override
    public Object call() throws Exception {
        order.setCallback("处理中");
        return order;
    }
}
