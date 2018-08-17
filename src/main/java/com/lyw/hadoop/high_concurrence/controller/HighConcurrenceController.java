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

/**
 * Created by wangxiaowu on 2018/8/17.
 */

@Controller
@RequestMapping("/order")
public class HighConcurrenceController {

    static Logger logger = LoggerFactory.getLogger(HighConcurrenceController.class);

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

}
