package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierExample {
    public static void main(String[] args) {
        Runnable barrierAction = () -> System.out.println("All threads reached the barrier. Proceeding...");
        CyclicBarrier barrier = new CyclicBarrier(3, barrierAction);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is doing some work...");
            try {
                Thread.sleep((long) (Math.random() * 3000));
                System.out.println(Thread.currentThread().getName() + " reached the barrier.");
                barrier.await(); // ждем других
                System.out.println(Thread.currentThread().getName() + " continues execution.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " has finished execution.");
            }
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}

