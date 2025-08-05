package org.example;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int value = 0;

    public void readValue(String threadName) {
        rwLock.readLock().lock();
        try {
            System.out.println(threadName + " reading: " + value);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void writeValue(String threadName, int newValue) {
        rwLock.writeLock().lock();
        try {
            System.out.println(threadName + " writing: " + newValue);
            Thread.sleep(1000);
            value = newValue;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();

        Runnable reader = () -> {
            for (int i = 0; i < 3; i++)
                example.readValue(Thread.currentThread().getName());
        };

        Runnable writer = () -> example.writeValue(Thread.currentThread().getName(), 42);

        new Thread(reader, "Reader 1").start();
        new Thread(reader, "Reader 2").start();
        new Thread(writer, "Writer").start();
    }
}

