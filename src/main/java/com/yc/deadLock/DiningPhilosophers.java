package com.yc.deadLock;

/**
 * 演示哲学家就餐问题导致的死锁
 *
 * @version 1.0 create at 2020/1/31
 * @auther yangchuan
 */
public class DiningPhilosophers {

    public static void main(String[] args) {
        Philosoper[] philosopers = new Philosoper[5];
        Object[] chopsticks = new Object[philosopers.length];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        for (int i = 0; i < philosopers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
            if (i == philosopers.length - 1) {
                //避免死锁
                philosopers[i] = new Philosoper(rightChopstick, leftChopstick);
            } else {
                philosopers[i] = new Philosoper(leftChopstick, rightChopstick);
            }
            new Thread(philosopers[i], "哲学家" + (i + 1) + "号").start();
        }
    }

    public static class Philosoper implements Runnable {

        private Object lestChopstick;
        private Object rightChopstick;


        public Philosoper(Object lestChopstick, Object rightChopstick) {
            this.lestChopstick = lestChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (lestChopstick) {
                        doAction("Picked up left chopstick");
                        synchronized (rightChopstick) {
                            doAction("Picked up right chopstick - eating");
                            doAction("Put down right chopstick");
                        }
                        doAction("Put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep((long) (Math.random() * 10));
        }
    }
}
