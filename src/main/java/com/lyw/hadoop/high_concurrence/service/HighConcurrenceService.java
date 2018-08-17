package com.lyw.hadoop.high_concurrence.service;

import com.lyw.hadoop.high_concurrence.beans.Order;

/**
 * Created by wangxiaowu on 2018/8/17.
 */
public interface HighConcurrenceService {
    int submitOrder(Order order);
}
