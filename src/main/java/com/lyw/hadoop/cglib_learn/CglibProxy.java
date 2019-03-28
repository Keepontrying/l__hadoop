package com.lyw.hadoop.cglib_learn;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.management.ManagementFactoryHelper;
import sun.management.MemoryUsageCompositeData;
import sun.management.StackTraceElementCompositeData;

import javax.management.openmbean.CompositeData;
import java.lang.management.MemoryPoolMXBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 类描述：cglib代理
 *
 * @author liangyuwu
 * @Time 2018/8/27 14:27
 */
public class CglibProxy implements MethodInterceptor,InvocationHandler{

    /**
     *                      cglib代理
     * 1、被代理对象：MyThread
     * 2、代理回调类，CglibProxy实现MethodInterceptor的intercept方法
     * 3、Enhancer设置superClass(被代理类),callback(代理回调类）
     * 4、创建代理类（被代理类的子类,继承--所有final类无法代理） enhancer.create()
     * 5、调用子类被代理的方法
     */

    /**
     *                     JDK动态代理
     *  1、只能代理接口，切接口必须有实现类
     *  2、代理类CglibProxy实现InvocationHandler接口，invoke方法传入target
     *  3、Proxy获取动态代理类，调用代理目标方法
     */

    public CglibProxy() {
        super();
    }

    public CglibProxy(Object target) {
        this.target = target;
    }

    private Object target;
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.err.println("cglib开始代理 my thread....." + method.getName()+ " obj="+obj.getClass().getName());
        Object o = proxy.invokeSuper(obj, args);
        System.err.println("cglib代理结束。。。"+method.getName());
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("jdk代理开始......"+proxy.getClass().getName());
        Object rs = method.invoke(target, args);
        System.err.println("jdk代理结束.....");
        return rs;
    }

    //代理 MemoryPoolImpl
    public void getMBeans() {
        List<MemoryPoolMXBean> helper = ManagementFactoryHelper.getMemoryPoolMXBeans();
        /*Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MemoryUsageCompositeData.class);
        enhancer.setCallback(new CglibProxy());
        CompositeData compositeData = (CompositeData) enhancer.create();*/
        helper.forEach(h ->{
            System.err.println(""+h.getObjectName());
            CompositeData memory = MemoryUsageCompositeData.toCompositeData(h.getUsage());
//            CompositeData memory = StackTraceElementCompositeData.toCompositeData(
//                    StackTraceElementCompositeData.from(memory1));

            memory.values().forEach(x ->{
                System.err.println("@@@"+x);
            });
        });
    }

    public static void main3(String[] args) throws InterruptedException {
        CglibProxy cglibProxy = new CglibProxy();
        LockSupport.park();
        cglibProxy.getMBeans();
    }


   public static void main2(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyThread.class);
        enhancer.setCallback(new CglibProxy());
        MyThread myThread = (MyThread) enhancer.create();
        myThread.start();
    }

    public static void main(String[] args) {
        JDKProxyImpl jdkProxy = new JDKProxyImpl();
        JDKProxy jdkProxy1 = (JDKProxy) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(),
                                                                jdkProxy.getClass().getInterfaces(),
                                                                new CglibProxy(jdkProxy));
        jdkProxy1.sayJDKProxy();


    }


}
