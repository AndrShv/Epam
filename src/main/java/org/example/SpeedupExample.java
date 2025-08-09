package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SpeedupExample {

    // Имитация тяжёлой задачи
    public static long heavyTask(int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) Math.sqrt(i); // "тяжёлая" математика
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        int tasks = 8;  // количество задач
        int workSize = 50_000_000; // сложность задачи

        // Однопоточное выполнение
        long startSingle = System.currentTimeMillis();
        for (int i = 0; i < tasks; i++) {
            heavyTask(workSize);
        }
        long timeSingle = System.currentTimeMillis() - startSingle;
        System.out.println("Single-thread time: " + timeSingle + " ms");

        // Многопоточное выполнение
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());// пул потоков = число ядер CPU
        List<Future<Long>> futures = new ArrayList<>(); // список для хранения объектов-обещанией: "Результат будет в будущем".

        long startMulti = System.currentTimeMillis();
        for (int i = 0; i < tasks; i++) {
            futures.add(executor.submit(() -> heavyTask(workSize))); // отправляем задачу на выполнение к потокам
        }
        for (Future<Long> f : futures) {
            f.get(); // ждём пока вся работа будет выполнена
        }
        long timeMulti = System.currentTimeMillis() - startMulti;
        executor.shutdown(); // завершаем работу пула потоков

        System.out.println("Multi-thread time: " + timeMulti + " ms");

        // Рассчёт ускорения
        double speedup = (double) timeSingle / timeMulti;
        System.out.printf("Speedup: %.2f%n", speedup);
    }
}
