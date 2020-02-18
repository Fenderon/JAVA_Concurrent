package com.yc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class CinemaReadWrite {

    private static ReentrantReadWriteLock reentrantReadWriteLock
            = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


    private static void read(){
        readLock.lock();

        try{
            System.out.println(Thread.currentThread().getName()
            +"得到了读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()
                    +"释放读锁");
            readLock.unlock();
        }
    }

    private static void write(){
        writeLock.lock();

        try{
            System.out.println(Thread.currentThread().getName()
                    +"得到了写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()
                    +"释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(()->read(),"Thread 1 ").start();
        new Thread(()->read(),"Thread 2 ").start();
        new Thread(()->write(),"Thread 3 ").start();
        new Thread(()->write(),"Thread 4 ").start();

    }
}
