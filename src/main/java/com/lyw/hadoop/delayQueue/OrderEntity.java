package com.lyw.hadoop.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class OrderEntity implements Delayed {
    private Integer orderId;
    private long delayTime;
    private TimeUnit timeUnit;

    public OrderEntity(){

    }
    public OrderEntity(Integer orderId , long delayTime,TimeUnit timeUnit){
        this.orderId = orderId;
        this.delayTime = timeUnit.convert(delayTime, timeUnit) + System.nanoTime();
        this.timeUnit = timeUnit;
    }
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        OrderEntity orderEntity = (OrderEntity) o;
        return delayTime - orderEntity.delayTime > 0 ? 1 : -1;
    }
}
