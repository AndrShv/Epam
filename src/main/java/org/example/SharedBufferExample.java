package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBufferExample {
    private Integer buffer = null; // буфер с ОДНИМ значением
    private final Lock lock = new ReentrantLock();
    private final Condition bufferFull = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (buffer != null) {
                bufferFull.await(); // ждём, пока буфер освободится
            }
            buffer = value;
            System.out.println(Thread.currentThread().getName() + " произвёл: " + value);
            bufferEmpty.signal(); // сигнализируем потребителю
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer == null) {
                bufferEmpty.await(); // ждём, пока буфер заполнится
            }
            int value = buffer;
            buffer = null;
            System.out.println(Thread.currentThread().getName() + " потребил: " + value);
            bufferFull.signal(); // сигнализируем продюсеру
            return value;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SharedBufferExample buffer = new SharedBufferExample();

        // Продюсер
        Runnable producer = () -> {
            int i = 0;
            try {
                while (true) {
                    buffer.produce(i++);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Консюмер
        Runnable consumer = () -> {
            try {
                while (true) {
                    buffer.consume();
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer, "Producer").start();
        new Thread(consumer, "Consumer").start();
    }
}