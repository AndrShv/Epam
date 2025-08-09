package org.example;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

// Задача для параллельной суммы массива
class SumTask extends RecursiveTask<Long> {
    private final int[] array; // Массив для суммирования
    private final int start, end;   // Начало и конец диапазона для суммирования [start, end)
    private static final int THRESHOLD = 5; // Порог для разделения задачи

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            // Базовый случай: считаем напрямую
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i]; // Суммируем элементы в диапазоне
            }
            return sum;
        }
        // Разделяем задачу
        int mid = start + length / 2; // Находим середину диапазона
        // Создаем подзадачи для левой и правой половин
        SumTask leftTask = new SumTask(array, start, mid); //считаем левую половину не включая mid
        SumTask rightTask = new SumTask(array, mid, end); //считаем правую половину начиная с mid и не включая end

        // Запускаем подзадачи
        leftTask.fork();
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();

        // Объединяем результаты
        return leftResult + rightResult;
    }
}

public class DivideAndConquerDemo {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        ForkJoinPool pool = new ForkJoinPool();
        long sum = pool.invoke(new SumTask(array, 0, array.length));
        System.out.println("Sum = " + sum); // Должно быть 55
    }
}