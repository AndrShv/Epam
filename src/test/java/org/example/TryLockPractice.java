package org.example;

import lombok.Getter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockPractice {
    private final ReentrantLock lock = new ReentrantLock();
    @Getter
    public int sharedResource = 0;

    public void doWork(String threadName) {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    System.out.println(threadName + " acquired the lock");
                    Thread.sleep(4000);
                    sharedResource++;
                    System.out.println(threadName + " incremented sharedResource to " + sharedResource);
                } finally {
                    System.out.println("sharedResource inside finally: " + sharedResource);
                    lock.unlock();
                    System.out.println(threadName + " released the lock");
                }
            } else {
                System.out.println(threadName + " could not increment sharedResource, doing something else...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TryLockPractice example = new TryLockPractice();

        Runnable task = () -> example.doWork(Thread.currentThread().getName());

        Thread t1 = new Thread(task, "Thread A");
        Thread t2 = new Thread(task, "Thread B");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Выводим финальное значение
        System.out.println("Final value of sharedResource: " + example.getSharedResource());
    }
}
