package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPractice {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    @Getter
    @Setter
    private double configValue = 0;

    public void readConfig(String threadName) {
        rwLock.readLock().lock();
        try {
            System.out.println(threadName + " reading config: " + configValue);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
    public void writeConfig(String threadName, double newConfigValue){
        rwLock.writeLock().lock();
        try{
            System.out.println(threadName + " writing config: " + newConfigValue);
            newConfigValue = 2*2;
            Thread.sleep(1000);
            configValue = newConfigValue;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
            System.out.println(configValue);
        }
    }

    public static void main(String[] args) {
        ReadWriteLockPractice practice = new ReadWriteLockPractice();

        Runnable reader = () -> {
            for (int i = 0; i < 3; i++)
                practice.readConfig(Thread.currentThread().getName());
        };

        Runnable writer = () -> practice.writeConfig(Thread.currentThread().getName(), 42);

        new Thread(reader, "Reader 1").start();
        new Thread(reader, "Reader 2").start();
        new Thread(writer, "Writer").start();
    }
}