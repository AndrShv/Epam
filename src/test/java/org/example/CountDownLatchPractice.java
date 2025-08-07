package org.example;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchPractice {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(5);
        Runnable doRace = () -> {
            System.out.println(Thread.currentThread().getName() + "started the race");
            try {
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "finished the race");
                latch.countDown(); // уменьшаем счетчик
            }
        };

        new Thread(doRace, "Car 1").start();
        new Thread(doRace, "Car 2").start();
        new Thread(doRace, "Car 3").start();
        new Thread(doRace, "Car 4").start();
        new Thread(doRace, "Car 5").start();

        System.out.println("Main thread is waiting for all cars");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("All cars finished. Main thread proceeds.");
        }



    }
}
