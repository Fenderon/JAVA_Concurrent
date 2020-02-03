package com.yc.deadLock;

/**
 * 转账时候遇到死锁，一旦打开注释，便会发生死锁
 *
 * @version 1.0 create at 2020/1/31
 * @auther yangchuan
 */
public class TransferMoney implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);
    static Account lock = new Account(500);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("a的余额：" + a.balance);
        System.out.println("b的余额：" + b.balance);


    }

    public static class Account {

        public Account(int balance) {
            this.balance = balance;
        }

        int balance;

    }

    @Override
    public void run() {

        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {

        class Helper {
            public void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败.");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账：" + amount);
            }
        }

        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            //哈希冲突时避免死锁
            synchronized (lock) {
                synchronized (from) {
                    synchronized (to) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
