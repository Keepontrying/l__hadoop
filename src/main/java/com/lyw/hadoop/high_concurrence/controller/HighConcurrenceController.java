package com.lyw.hadoop.high_concurrence.controller;

import com.lyw.hadoop.high_concurrence.beans.Order;
import com.lyw.hadoop.high_concurrence.service.HighConcurrenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangxiaowu on 2018/8/17.
 */

@Controller
@RequestMapping("/order")
public class HighConcurrenceController {

    static Logger logger = LoggerFactory.getLogger(HighConcurrenceController.class);
    AtomicInteger count = new AtomicInteger(0);
    final ReentrantLock reentrantLock = new ReentrantLock();
    HashSet<String> set = new HashSet<>();
    @Autowired
    HighConcurrenceService highConcurrenceService;

    @RequestMapping("/submit")
    @ResponseBody
    public String submitOrder(Order order, HttpServletRequest request, HttpServletResponse response) {
//        logger.info("订单参数：" + order.toString());

        try {
            response.setHeader("Content-Type"," text/html; charset=utf-8");
            return highConcurrenceService.submitOrder(order)+"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 多线程计数器
     */
    @RequestMapping("/online_count")
    @ResponseBody
    public void onlineCount(HttpServletRequest request, HttpServletResponse response) {
//        System.err.println("线程编号："+Thread.currentThread().getId());
        set.add(Thread.currentThread().getName());
        logger.info("请求参数："+request.getParameter("id"));
//        reentrantLock.lock();
        count.getAndIncrement();
//        reentrantLock.unlock();
    }


    /**
     * 获取在线线程数
     */
    @RequestMapping("/get_online")

    @ResponseBody
    public Integer get_online(HttpServletRequest request, HttpServletResponse response) {
        return count.get();
    }

}
