package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPractice {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();




    public void increment(){
        for (int i = 0; i < 100; i++)
           count++;
    }
    public void doWork() {
        lock.lock();
        try {
            increment();
            System.out.println("Count after increment: " + count + " in " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        ReentrantLockPractice practice = new ReentrantLockPractice();

        Runnable task = practice::doWork;

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
