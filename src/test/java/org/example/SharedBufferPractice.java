package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBufferPractice {
    private Integer buffer = null;
    private final Lock lock = new ReentrantLock();
    private final Condition bufferFull = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();

    private int producerTurn = 0; // 0 или 1 — чей сейчас ход
    private int consumerTurn = 0; // 0 или 1 — чей сейчас ход

    public void produce(int value, int producerId) throws InterruptedException {
        lock.lock();
        try {
            while (buffer != null || producerTurn != producerId) {
                bufferFull.await();
            }
            buffer = value;
            System.out.println("Producer-" + producerId + " произвёл: " + value);
            producerTurn = (producerTurn + 1) % 2;
            bufferEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int consume(int consumerId) throws InterruptedException {
        lock.lock();
        try {
            while (buffer == null || consumerTurn != consumerId) {
                bufferEmpty.await();
            }
            int value = buffer;
            buffer = null;
            System.out.println("Consumer-" + consumerId + " потребил: " + value);
            consumerTurn = (consumerTurn + 1) % 2;
            bufferFull.signalAll();
            return value;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SharedBufferPractice buffer = new SharedBufferPractice();

        for (int i = 0; i < 2; i++) {
            final int producerId = i;
            new Thread(() -> {
                int value = producerId * 100;
                try {
                    while (true) {
                        buffer.produce(value++, producerId);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Producer-" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            final int consumerId = i;
            new Thread(() -> {
                try {
                    while (true) {
                        buffer.consume(consumerId);
                        Thread.sleep(800);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Consumer-" + i).start();
        }
    }
}
