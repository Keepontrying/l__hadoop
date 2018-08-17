package com.lyw.hadoop.high_concurrence.service.impl;

import com.lyw.hadoop.high_concurrence.beans.Order;
import com.lyw.hadoop.high_concurrence.service.HighConcurrenceService;
import com.lyw.hadoop.high_concurrence.util.OrderThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by wangxiaowu on 2018/8/17.
 */
@Service
public class HighConcurrenceServiceImpl implements HighConcurrenceService{

    private Logger logger = LoggerFactory.getLogger(HighConcurrenceServiceImpl.class);

    static ExecutorService executorService = null;
    static {
        executorService = Executors.newFixedThreadPool(10,Executors.privilegedThreadFactory());
    }

    @Override
    public int submitOrder(Order order) {
        Thread thread = new OrderThread(order);
        try {
            Future future = executorService.submit(thread);
            logger.info("返回处理结果"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return order.getOrderId().intValue();
    }


}
