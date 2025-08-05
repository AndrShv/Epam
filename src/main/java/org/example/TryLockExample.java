package org.example;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class TryLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void tryLockTask(String threadName) {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) { // пытаемся захватить lock
                try {
                    System.out.println(threadName + " acquired the lock");
                    Thread.sleep(3000); // держим блокировку
                } finally {
                    lock.unlock();
                    System.out.println(threadName + " released the lock");
                }
            } else {
                System.out.println(threadName + " could not acquire the lock, doing something else...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TryLockExample example = new TryLockExample();

        Runnable task = () -> example.tryLockTask(Thread.currentThread().getName());

        new Thread(task, "Thread A").start();
        new Thread(task, "Thread B").start();
    }
}
