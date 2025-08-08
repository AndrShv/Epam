package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierPractice {
    public static void main(String[] args) {
        Runnable barrierAction = () -> System.out.println("Round is complete. All threads have reached the barrier. Proceeding...");

        CyclicBarrier barrier = new CyclicBarrier(3, barrierAction); // ждем пока 3 потока дойдут до барьера

        Runnable playGame = () -> {
         //   for (int i = 0; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " is preparing for the game...");

                try {
                    Thread.sleep((long) (Math.random() * 3000));
                    System.out.println(Thread.currentThread().getName() + " is ready and waiting at the barrier.");

                    barrier.await();

                    System.out.println(Thread.currentThread().getName() + " continues with the game.");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " has finished its turn and waiting for a new game.");
                }
         //   }
        };

        new Thread(playGame).start();
        new Thread(playGame).start();
        new Thread(playGame).start();
    }
}
