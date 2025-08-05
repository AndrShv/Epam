package org.example;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private final Semaphore semaphore = new Semaphore(3); // 3 разрешения

    public void doWork(String threadName) {
        try {
            System.out.println(threadName + " waiting for a permit...");
            semaphore.acquire(); // получить разрешение
            System.out.println(threadName + " got the permit!");

            Thread.sleep(2000); // работа

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + " releasing the permit.");
            semaphore.release(); // освободить разрешение
        }
    }

    public static void main(String[] args) {
        SemaphoreExample example = new SemaphoreExample();

        for (int i = 1; i <= 6; i++) {
            String name = "Thread " + i;
            new Thread(() -> example.doWork(name)).start();
        }
    }
}
