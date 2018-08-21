Classfile /E:/ideaWorkSpace/merchant/abs/l__hadoop/target/classes/com/lyw/hadoop/high_concurrence/util/OrderThread.class
  Last modified 2018-8-21; size 2097 bytes
  MD5 checksum 122dc58bbf90dec48330405aa469db86
  Compiled from "OrderThread.java"
public class com.lyw.hadoop.high_concurrence.util.OrderThread extends java.lang.Thread
  SourceFile: "OrderThread.java"
  InnerClasses:
       static #16; //class com/lyw/hadoop/high_concurrence/util/OrderThread$1
  //表示class文件版本
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #20.#59        //  java/lang/Thread."<init>":()V
   #2 = Class              #60            //  java/util/concurrent/locks/ReentrantLock
   #3 = Methodref          #2.#59         //  java/util/concurrent/locks/ReentrantLock."<init>":()V
   #4 = Fieldref           #12.#61        //  com/lyw/hadoop/high_concurrence/util/OrderThread.reentrantLock:Ljava/util/concurrent/locks/ReentrantLock;
   #5 = Fieldref           #12.#62        //  com/lyw/hadoop/high_concurrence/util/OrderThread.order:Lcom/lyw/hadoop/high_concurrence/beans/Order;
   #6 = Fieldref           #63.#64        //  java/lang/System.err:Ljava/io/PrintStream;
   #7 = String             #65            //  into
   #8 = Methodref          #66.#67        //  java/io/PrintStream.println:(Ljava/lang/String;)V
   #9 = Fieldref           #12.#68        //  com/lyw/hadoop/high_concurrence/util/OrderThread.counter:Ljava/util/concurrent/atomic/AtomicInteger;
  #10 = Methodref          #18.#69        //  java/util/concurrent/atomic/AtomicInteger.getAndIncrement:()I
  #11 = Methodref          #70.#71        //  java/util/concurrent/Executors.newFixedThreadPool:(I)Ljava/util/concurrent/ExecutorService;
  #12 = Class              #72            //  com/lyw/hadoop/high_concurrence/util/OrderThread
  #13 = Methodref          #12.#59        //  com/lyw/hadoop/high_concurrence/util/OrderThread."<init>":()V
  #14 = Integer            100000
  #15 = InterfaceMethodref #73.#74        //  java/util/concurrent/ExecutorService.submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
  #16 = Class              #75            //  com/lyw/hadoop/high_concurrence/util/OrderThread$1
  #17 = Methodref          #16.#59        //  com/lyw/hadoop/high_concurrence/util/OrderThread$1."<init>":()V
  #18 = Class              #76            //  java/util/concurrent/atomic/AtomicInteger
  #19 = Methodref          #18.#77        //  java/util/concurrent/atomic/AtomicInteger."<init>":(I)V
  #20 = Class              #78            //  java/lang/Thread
  #21 = Utf8               InnerClasses
  #22 = Utf8               order
  #23 = Utf8               Lcom/lyw/hadoop/high_concurrence/beans/Order;
  #24 = Utf8               counter
  #25 = Utf8               Ljava/util/concurrent/atomic/AtomicInteger;
  #26 = Utf8               reentrantLock
  #27 = Utf8               Ljava/util/concurrent/locks/ReentrantLock;
  #28 = Utf8               <init>
  #29 = Utf8               ()V
  #30 = Utf8               Code
  #31 = Utf8               LineNumberTable
  #32 = Utf8               LocalVariableTable
  #33 = Utf8               this
  #34 = Utf8               Lcom/lyw/hadoop/high_concurrence/util/OrderThread;
  #35 = Utf8               (Lcom/lyw/hadoop/high_concurrence/beans/Order;)V
  #36 = Utf8               MethodParameters
  #37 = Utf8               run
  #38 = Utf8               StackMapTable
  #39 = Class              #72            //  com/lyw/hadoop/high_concurrence/util/OrderThread
  #40 = Class              #79            //  java/lang/Object
  #41 = Class              #80            //  java/lang/Throwable
  #42 = Utf8               syncMe
  #43 = Utf8               a
  #44 = Utf8               I
  #45 = Utf8               main
  #46 = Utf8               ([Ljava/lang/String;)V
  #47 = Utf8               i
  #48 = Utf8               args
  #49 = Utf8               [Ljava/lang/String;
  #50 = Utf8               executorService
  #51 = Utf8               Ljava/util/concurrent/ExecutorService;
  #52 = Utf8               thread
  #53 = Utf8               Ljava/lang/Thread;
  #54 = Class              #81            //  java/util/concurrent/ExecutorService
  #55 = Class              #78            //  java/lang/Thread
  #56 = Utf8               <clinit>
  #57 = Utf8               SourceFile
  #58 = Utf8               OrderThread.java
  #59 = NameAndType        #28:#29        //  "<init>":()V
  #60 = Utf8               java/util/concurrent/locks/ReentrantLock
  #61 = NameAndType        #26:#27        //  reentrantLock:Ljava/util/concurrent/locks/ReentrantLock;
  #62 = NameAndType        #22:#23        //  order:Lcom/lyw/hadoop/high_concurrence/beans/Order;
  #63 = Class              #82            //  java/lang/System
  #64 = NameAndType        #83:#84        //  err:Ljava/io/PrintStream;
  #65 = Utf8               into
  #66 = Class              #85            //  java/io/PrintStream
  #67 = NameAndType        #86:#87        //  println:(Ljava/lang/String;)V
  #68 = NameAndType        #24:#25        //  counter:Ljava/util/concurrent/atomic/AtomicInteger;
  #69 = NameAndType        #88:#89        //  getAndIncrement:()I
  #70 = Class              #90            //  java/util/concurrent/Executors
  #71 = NameAndType        #91:#92        //  newFixedThreadPool:(I)Ljava/util/concurrent/ExecutorService;
  #72 = Utf8               com/lyw/hadoop/high_concurrence/util/OrderThread
  #73 = Class              #81            //  java/util/concurrent/ExecutorService
  #74 = NameAndType        #93:#94        //  submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
  #75 = Utf8               com/lyw/hadoop/high_concurrence/util/OrderThread$1
  #76 = Utf8               java/util/concurrent/atomic/AtomicInteger
  #77 = NameAndType        #28:#95        //  "<init>":(I)V
  #78 = Utf8               java/lang/Thread
  #79 = Utf8               java/lang/Object
  #80 = Utf8               java/lang/Throwable
  #81 = Utf8               java/util/concurrent/ExecutorService
  #82 = Utf8               java/lang/System
  #83 = Utf8               err
  #84 = Utf8               Ljava/io/PrintStream;
  #85 = Utf8               java/io/PrintStream
  #86 = Utf8               println
  #87 = Utf8               (Ljava/lang/String;)V
  #88 = Utf8               getAndIncrement
  #89 = Utf8               ()I
  #90 = Utf8               java/util/concurrent/Executors
  #91 = Utf8               newFixedThreadPool
  #92 = Utf8               (I)Ljava/util/concurrent/ExecutorService;
  #93 = Utf8               submit
  #94 = Utf8               (Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
  #95 = Utf8               (I)V
{
  static java.util.concurrent.atomic.AtomicInteger counter;
    flags: ACC_STATIC

  final java.util.concurrent.locks.ReentrantLock reentrantLock;
    flags: ACC_FINAL

  public com.lyw.hadoop.high_concurrence.util.OrderThread();
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Thread."<init>":()V
         4: aload_0
         5: new           #2                  // class java/util/concurrent/locks/ReentrantLock
         8: dup
         9: invokespecial #3                  // Method java/util/concurrent/locks/ReentrantLock."<init>":()V
        12: putfield      #4                  // Field reentrantLock:Ljava/util/concurrent/locks/ReentrantLock;
        15: return
      LineNumberTable:
        line 21: 0
        line 18: 4
        line 22: 15
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      16     0  this   Lcom/lyw/hadoop/high_concurrence/util/OrderThread;

  public com.lyw.hadoop.high_concurrence.util.OrderThread(com.lyw.hadoop.high_concurrence.beans.Order);
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=2, args_size=2
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Thread."<init>":()V
         4: aload_0
         5: new           #2                  // class java/util/concurrent/locks/ReentrantLock
         8: dup
         9: invokespecial #3                  // Method java/util/concurrent/locks/ReentrantLock."<init>":()V
        12: putfield      #4                  // Field reentrantLock:Ljava/util/concurrent/locks/ReentrantLock;
        15: aload_0
        16: aload_1
        17: putfield      #5                  // Field order:Lcom/lyw/hadoop/high_concurrence/beans/Order;
        20: return
      LineNumberTable:
        line 24: 0
        line 18: 4
        line 25: 15
        line 26: 20
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      21     0  this   Lcom/lyw/hadoop/high_concurrence/util/OrderThread;
               0      21     1 order   Lcom/lyw/hadoop/high_concurrence/beans/Order;
      MethodParameters: length = 0x5
       01 00 16 00 00

  public void run();
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter
         4: getstatic     #6                  // Field java/lang/System.err:Ljava/io/PrintStream;
         7: ldc           #7                  // String into
         9: invokevirtual #8                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        12: getstatic     #9                  // Field counter:Ljava/util/concurrent/atomic/AtomicInteger;
        15: invokevirtual #10                 // Method java/util/concurrent/atomic/AtomicInteger.getAndIncrement:()I
        18: pop
        19: aload_1
        20: monitorexit
        21: goto          29
        24: astore_2
        25: aload_1
        26: monitorexit
        27: aload_2
        28: athrow
        29: return
      Exception table:
         from    to  target type
             4    21    24   any
            24    27    24   any
      LineNumberTable:
        line 29: 0
        line 30: 4
        line 31: 12
        line 32: 19
        line 39: 29
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      30     0  this   Lcom/lyw/hadoop/high_concurrence/util/OrderThread;
      StackMapTable: number_of_entries = 2
           frame_type = 255 /* full_frame */
          offset_delta = 24
          locals = [ class com/lyw/hadoop/high_concurrence/util/OrderThread, class java/lang/Object ]
          stack = [ class java/lang/Throwable ]
           frame_type = 250 /* chop */
          offset_delta = 4


  public synchronized void syncMe();
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=1, locals=2, args_size=1
         0: iconst_5
         1: istore_1
         2: return
      LineNumberTable:
        line 42: 0
        line 43: 2
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0       3     0  this   Lcom/lyw/hadoop/high_concurrence/util/OrderThread;
               2       1     1     a   I

  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=4, args_size=1
         0: bipush        10
         2: invokestatic  #11                 // Method java/util/concurrent/Executors.newFixedThreadPool:(I)Ljava/util/concurrent/ExecutorService;
         5: astore_1
         6: new           #12                 // class com/lyw/hadoop/high_concurrence/util/OrderThread
         9: dup
        10: invokespecial #13                 // Method "<init>":()V
        13: astore_2
        14: iconst_0
        15: istore_3
        16: iload_3
        17: ldc           #14                 // int 100000
        19: if_icmpge     36
        22: aload_1
        23: aload_2
        24: invokeinterface #15,  2           // InterfaceMethod java/util/concurrent/ExecutorService.submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        29: pop
        30: iinc          3, 1
        33: goto          16
        36: aload_1
        37: new           #16                 // class com/lyw/hadoop/high_concurrence/util/OrderThread$1
        40: dup
        41: invokespecial #17                 // Method com/lyw/hadoop/high_concurrence/util/OrderThread$1."<init>":()V
        44: invokeinterface #15,  2           // InterfaceMethod java/util/concurrent/ExecutorService.submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        49: pop
        50: return
      LineNumberTable:
        line 47: 0
        line 48: 6
        line 49: 14
        line 50: 22
        line 49: 30
        line 54: 36
        line 65: 50
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
              16      20     3     i   I
               0      51     0  args   [Ljava/lang/String;
               6      45     1 executorService   Ljava/util/concurrent/ExecutorService;
              14      37     2 thread   Ljava/lang/Thread;
      StackMapTable: number_of_entries = 2
           frame_type = 254 /* append */
             offset_delta = 16
        locals = [ class java/util/concurrent/ExecutorService, class java/lang/Thread, int ]
           frame_type = 250 /* chop */
          offset_delta = 19

      MethodParameters: length = 0x5
       01 00 30 00 00

  static {};
    flags: ACC_STATIC
    Code:
      stack=3, locals=0, args_size=0
         0: new           #18                 // class java/util/concurrent/atomic/AtomicInteger
         3: dup
         4: iconst_0
         5: invokespecial #19                 // Method java/util/concurrent/atomic/AtomicInteger."<init>":(I)V
         8: putstatic     #9                  // Field counter:Ljava/util/concurrent/atomic/AtomicInteger;
        11: return
      LineNumberTable:
        line 17: 0
}
