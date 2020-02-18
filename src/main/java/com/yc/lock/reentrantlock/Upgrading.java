package com.yc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description TODO
 *
 * @version 1.0 create at 2020/2/5
 * @auther yangchuan
 */
public class Upgrading {

    private static ReentrantReadWriteLock reentrantReadWriteLock
            = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void readUpgrade(){
        readLock.lock();

        try{
            System.out.println(Thread.currentThread().getName()
                    +"得到了读锁，正在读取");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"升级会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"获取到了写锁，升级成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()
                    +"释放读锁");
            readLock.unlock();
        }
    }
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
    private static void writeDowngrading(){
        writeLock.lock();

        try{
            System.out.println(Thread.currentThread().getName()
                    +"得到了写锁，正在写入");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"在不释放写锁的情况写获取读锁，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()
                    +"释放写锁");
            writeLock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("先演示降级时可以的");
        Thread t1 = new Thread(()->writeDowngrading());
        t1.start();
        t1.join();
        System.out.println("演示升级时不可以的");
        new Thread(()->readUpgrade()).start();

    }
}
