package com.lyw.hadoop.sync_thread;

/**
 * 类描述：
 *
 * @author liangyuwu
 * @Time 2018/8/21 11:49
 */
public class SyncThread {

    private long timeout;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public SyncThread() {
        super();
        System.out.println("初始化");
    }

    public synchronized String getSyncName() {
        System.out.println(this.hashCode()+" 进入getSyncName方法");
        try {
            SyncThread.class.notify();
        } catch (Exception e) {

        }
        return "getSyncName方法未被锁住";
    }

    public synchronized static String getStaticSyncName() {
        System.out.println("进入getStaticSyncName方法");
        Object  c = SyncThread.class;
        System.err.println("唤醒对象="+c.toString());
        c.notify();
        return "getStaticSyncName方法未被锁住";
    }

    public String getName() {
        System.out.println(this.hashCode()+" 进入getName方法");
        return "getName非同步方法未被锁住";
    }

    public synchronized int getAge() {
        try {
            System.out.println(" 锁住getAge()方法");
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.getMessage();
        }

        return 20;
    }

    public static String getStaticName() {
        System.out.println("进入getStaticName方法");
        return "getStaticName方法未被锁住";
    }

    public String syncCodeBlock(String var1) {
        synchronized (SyncThread.class) {
            try {
                System.out.println("进入syncCodeBlock方法->同步代码块"+var1);
                System.err.println(this.toString()+"=="+SyncThread.class.toString());
                System.err.println(this.getClass()==SyncThread.class);
                SyncThread.class.wait();
                System.err.println("唤醒了，该线程");
//                Thread.sleep(10000);
            } catch (InterruptedException e) {

            }
            return "syncCodeBlock方法未被锁住"+var1;
        }
    }
}
