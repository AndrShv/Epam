package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void printWithLock(String threadName) {
        lock.lock(); // захватываем блокировку
        try {
            System.out.println(threadName + " acquired the lock");
            Thread.sleep(1000); // имитируем работу
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // важно освободить lock
            System.out.println(threadName + " released the lock");
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        Runnable task = () -> example.printWithLock(Thread.currentThread().getName());

        new Thread(task, "Thread A").start();
        new Thread(task, "Thread B").start();
    }
}



