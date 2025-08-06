package org.example;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Runnable worker = () -> {
            System.out.println(Thread.currentThread().getName() + " is working...");
            try {
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
            latch.countDown();
        };

        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();

        System.out.println("Main thread is waiting...");
        latch.await(); // ждем, пока счетчик не станет 0
        System.out.println("All workers finished. Main thread proceeds.");
    }
}
