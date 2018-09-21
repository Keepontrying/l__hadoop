# Java IO
  - 同步、异步、阻塞、非阻塞
    - 同步、异步是指的系统事件之间协调，在时间上的一致性。<font color=red></font>
    - 阻塞、非阻塞：是否阻断，线程挂起

  - Unix I/O模型
    - 阻塞IO
    - 非阻塞IO
    - IO复用
    - 信号驱动异步IO
    - 异步IO

  - IO分为两步：1.应用程序IO请求内核函数（IO请求）</br>
              2.内核函数执行真正的IO（IO操作）
              
    - 同步IO、异步IO的区别是第二步，IO操作是否阻塞，如果阻塞就是同步IO,如果不阻塞就是异步IO
    - 阻塞IO、非阻塞IO的区别是第一步，如果IO请求是立即返回，那就是非阻塞IO，如果是等操作完成后再返回，就是阻塞IO
    
    
  - Java IO
    - BIO 同步阻塞IO
    - NIO 同步非阻塞IO
        1. 主要通道：DatagramChannel、
                    SocketChannel、
                    FileChannel、
                    ServerSocketChannel 
        2. 核心缓冲：CharBuffer、
                    DoubleBuffer、
                    IntBuffer、
                    LongBuffer、
                    ByteBuffer、
                    ShortBuffer、
                    FloatBuffer
    - AIO 异步IO