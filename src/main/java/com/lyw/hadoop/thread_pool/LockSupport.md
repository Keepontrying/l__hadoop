# LockSupport 源码解读
  
  - 概述：LockSupport是线程阻塞原语，底层调用的是Unsafe native方法。
    - park方法
    ```
        public static void park() {
            //阻塞线程,0表示永远
            /**
            *3种情况唤醒该线程
            *1、其他线程调用unpark,传入的线程是当前线程
            *2、
            */
            UNSAFE.park(false, 0L);
        }
    ```
    - unpark
    ```
        public static void unpark(Thread thread) {
           if (thread != null)
               UNSAFE.unpark(thread);
       }
    ```