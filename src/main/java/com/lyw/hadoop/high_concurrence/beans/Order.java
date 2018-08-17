package com.lyw.hadoop.high_concurrence.beans;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by wangxiaowu on 2018/8/17.
 */
public class Order implements Serializable{
    @NotNull
    private Long orderId;
    @NotNull
    private String goodsName;
    @NotNull
    private Integer goodsNum;
    @NotNull
    private Integer goodsId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    @NotNull
    private Integer userId;
    @NotNull
    private BigInteger amount;

    private String callback;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNum=" + goodsNum +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", callback='" + callback + '\'' +
                '}';
    }
}
