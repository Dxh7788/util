package com.jelly.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongxiaohong
 * @date 2019/1/2 09:37
 */
public class Trehred {
    /**使用volatile刷新值*/
    private volatile int sfk = 2000;
    /**
     * 使用synchronized同步保持原子性,不需要volatile
     * 使用synchronized的话,只有在程序线程运行完或者异常时,JVM才能释放锁.如果程序中有IO等待或者其他线程等待的问题.则其他线程就需要长久的等待下去
     * */
    public synchronized void add(){
        sfk++;
        System.out.println(sfk);
    }
    /**使用锁的方式*/
    public synchronized void addLock(){
        Lock lock = new ReentrantLock();
        lock.lock();
        sfk++;
        System.out.println(sfk);
        lock.unlock();
    }
}
