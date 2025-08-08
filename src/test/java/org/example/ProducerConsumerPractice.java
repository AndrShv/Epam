package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumerPractice {
    public static void main(String[] args) {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);
        AtomicInteger itemCount = new AtomicInteger(0);

        Runnable produce = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String item = "Item " + itemCount.incrementAndGet();
                    queue.put(item);// блокирует, если очередь полна
                    System.out.println("Produced: " + item);
                    Thread.sleep(500); // имитируем время производства
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            finally {
                System.out.println(Thread.currentThread().getName() + " finished producing.");
            }
        };


        Runnable consume = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String item = queue.take(); //освобождает очередь
                    System.out.println("Consumed: " + item);
                    Thread.sleep(1000); // имитируем время потребления
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                System.out.println(Thread.currentThread().getName() + " finished consuming.");
            }
        };


        for (int i = 0; i < 2; i++) {
            new Thread(produce, "Producer-" + i).start();
            new Thread(consume, "Consumer-" + i).start();

        }
    }
}
