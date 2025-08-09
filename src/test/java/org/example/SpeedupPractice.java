package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SpeedupPractice {

    public static long heavyTask(int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) ((long) Math.sqrt(i) + Math.cos(i));
        }
        return sum;
    }


    public static void main(String[] args) throws Exception {
        int tasks = 8;  // количество задач
        int workSize = 5_000_000; // сложность задачи

        long startSingle = System.currentTimeMillis();
        for (int i = 0; i < tasks; i++) {
            heavyTask(workSize);
        }
        long timeSingle = System.currentTimeMillis() - startSingle;
        System.out.println("Single-thread time: " + timeSingle + " ms");


        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Long>> futures = new ArrayList<>();

        long startMulti = System.currentTimeMillis();
        for (int i = 0; i < tasks; i++) {
            futures.add(executor.submit(() -> heavyTask(workSize)));
        }
        for (Future<Long> f : futures) {
            f.get();
        }
        long timeMulti = System.currentTimeMillis() - startMulti;
        executor.shutdown();

        System.out.println("Multi-thread time: " + timeMulti + " ms");

        // Рассчёт ускорения
        double speedup = (double) timeSingle / timeMulti;
        System.out.printf("Speedup: %.2f%n", speedup);
    }
}