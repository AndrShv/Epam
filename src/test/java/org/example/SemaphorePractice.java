package org.example;

import java.util.concurrent.Semaphore;

public class SemaphorePractice {
    private final Semaphore semaphore = new Semaphore(2);


    public void parkCar(String threadName) {
        try {
            System.out.println(threadName + " waiting for a parking space...");
            semaphore.acquire();
            System.out.println(threadName + " found a parking space!");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + " leave a parking space!");
            semaphore.release();
        }
        }
        public static void main(String[] args) {
            SemaphorePractice practice = new SemaphorePractice();

            Runnable car = () -> {
                for (int i = 0; i < 2; i++) {
                    practice.parkCar(Thread.currentThread().getName());
                }
            };

            for (int i = 1; i <= 4; i++) {
                String name = "Car " + i;
                new Thread(car, name).start();
            }
        }

    }
