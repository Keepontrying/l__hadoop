# ReentrantLock重入锁学习

- ReenTrantLock 重入锁（公平锁、非公平锁）
    ![线程池类结构](lock.png)
    
    - ReenTrantLock类解读
    - lock()方法，默认调用非公平锁NonFairSync.lock()方法，compareAndSetState(0,1)调动底层unsafe.compareAndSwapInt(this, stateOffset, expect, update)
        如果stateOffeset和expect相等，就修改stateOffset的值为update,并返回true，否者什么都不做返回false。
        - a.如果返回true:（即state=1 被锁住了），设置当前线程为reentrantLock的排外线程exclusiveOwnerThread
        - b.如果返回false:调用AQS类acquire(1)方法，
        ```
              if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
                selfInterrupt();
              }
        ```
        
        - 条件一：调用NonFairSync.tryAcquire(1)->Sync.nonfairTryAcquire 尝试获取锁
             ```
                  final boolean nonfairTryAcquire(int acquires) { 
                      final Thread current = Thread.currentThread(); 
                      int c = getState(); //获取锁状态  0-未锁  1-被锁住
                      if (c == 0) { 
                          if (compareAndSetState(0, acquires)){ //如果是0，尝试获得该锁
                              setExclusiveOwnerThread(current); //设置该锁的排外线程
                              return true; 
                          } 
                      } 
                      else if (current == getExclusiveOwnerThread()) { //如果排外线程==当前线程
                          int nextc = c + acquires; 
                          if (nextc < 0) // overflow 
                              throw new Error("Maximum lock count exceeded"); 
                              setState(nextc); //一个线程可以多次获得该锁，获取一次+1  （线程重入锁）
                          return true; 
                      } 
                      return false;  //没有获取锁
                  }
             ```
        - 条件二：acquireQueued(addWaiter(Node.EXCLUSIVE), arg)  
                    addWaiter(Node.EXCLUSIVE)将当前线程包装为exclusive node，插入到双向队列中（双向链表方式实现）
                    acquireQueued方法：？？尝试阻断线程
             ```
                    final boolean acquireQueued(final Node node, int arg) {
                            boolean failed = true;
                            try {
                                boolean interrupted = false;
                                for (;;) {
                                    final Node p = node.predecessor();//head释放锁，该线程才有可能得到锁
                                    if (p == head && tryAcquire(arg)) {//如果获取锁了，就不阻塞线程
                                        setHead(node);
                                        p.next = null; // help GC
                                        failed = false;
                                        return interrupted;
                                    }
                                    if (shouldParkAfterFailedAcquire(p, node) //见下面method1
                                        && parkAndCheckInterrupt())//见下面method2
                                        interrupted = true;//阻断当前线程
                                }
                            } finally {
                                if (failed)
                                    cancelAcquire(node);
                            }
                        }
             ```
             ```
                #### method1
                /**
                     * Checks and updates status for a node that failed to acquire.
                     * Returns true if thread should block. This is the main signal
                     * control in all acquire loops.  Requires that pred == node.prev.
                     *
                     * @param pred node's predecessor holding status
                     * @param node the node
                     * @return {@code true} if thread should block
                     */
                    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
                        int ws = pred.waitStatus;
                        if (ws == Node.SIGNAL)
                            /*
                             * This node has already set status asking a release
                             * to signal it, so it can safely park.
                             */
                            return true;
                        if (ws > 0) {
                            /*
                             * Predecessor was cancelled. Skip over predecessors and
                             * indicate retry.
                             */
                            do {
                                node.prev = pred = pred.prev;
                            } while (pred.waitStatus > 0);
                            pred.next = node;
                        } else {
                            /*
                             * waitStatus must be 0 or PROPAGATE.  Indicate that we
                             * need a signal, but don't park yet.  Caller will need to
                             * retry to make sure it cannot acquire before parking.
                             */
                            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
                        }
                        return false;
                    }
             
                #### method2
                /**
                     * Convenience method to park and then check if interrupted
                     *
                     * @return {@code true} if interrupted
                     */
                    private final boolean parkAndCheckInterrupt() {
                        LockSupport.park(this);
                        return Thread.interrupted();
                    }
             ```
    - unlock()方法 -> sync.release(1);
    ```
        public final boolean release(int arg) {
                if (tryRelease(arg)) {//尝试释放锁
                    Node h = head;
                    if (h != null && h.waitStatus != 0)
                        unparkSuccessor(h);
                    return true;
                }
                return false;
            }
            
    ```        
        protected final boolean tryRelease(int releases) {
            int c = getState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {//释放锁，设置排外线程为null
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }
    ```
    ```
        /**
             * Wakes up node's successor, if one exists.
             *
             * @param node the node
             */
            private void unparkSuccessor(Node node) {
                /*
                 * If status is negative (i.e., possibly needing signal) try
                 * to clear in anticipation of signalling.  It is OK if this
                 * fails or if status is changed by waiting thread.
                 */
                int ws = node.waitStatus;
                if (ws < 0)
                    compareAndSetWaitStatus(node, ws, 0);
        
                /*
                 * Thread to unpark is held in successor, which is normally
                 * just the next node.  But if cancelled or apparently null,
                 * traverse backwards from tail to find the actual
                 * non-cancelled successor.
                 */
                Node s = node.next;
                if (s == null || s.waitStatus > 0) {
                    s = null;
                    for (Node t = tail; t != null && t != node; t = t.prev)
                        if (t.waitStatus <= 0)
                            s = t;
                }
                if (s != null)
                    LockSupport.unpark(s.thread);
            }
    ```