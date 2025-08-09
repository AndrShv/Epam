package org.example;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;



class FindEvenNums extends RecursiveTask<List<Integer>> {
    private final List<Integer> array; // Список чисел для поиска четных
    private final int start, end;
    private static int THRESHOLD = 5; // Порог для разделения задачи

    public FindEvenNums(List<Integer> array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<Integer> compute() {
        List <Integer> result = new ArrayList<>();
        int length = end - start;
        int mid = start + length / 2;
        if (length <= THRESHOLD) { // Базовый случай: считаем напрямую
            for (int i = start; i < end; i++) {
                if (array.get(i) % 2 == 0) {
                    result.add(array.get(i));
                }

            }
            return result;
        }

        // Создаем подзадачи для левой и правой половин
        FindEvenNums leftTask = new FindEvenNums(array, start, mid);
        FindEvenNums rightTask = new FindEvenNums(array, mid, end);
        // Запускаем подзадачу в другом потоке
        leftTask.fork();

        List<Integer> rightAndLeftResult = new ArrayList<>();


        // Вычисляем правую часть
        // и ждем завершения левой части
        List<Integer> rightResult = rightTask.compute();
        List<Integer> leftResult = leftTask.join();


        // Объединяем результаты
        rightAndLeftResult.addAll(rightResult);
        rightAndLeftResult.addAll(leftResult);


        return rightAndLeftResult;
    }
}

public class DivideAndConquerPractice {
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }
        Collections.shuffle(array);
        ForkJoinPool pool = new ForkJoinPool();
        List<Integer> result= pool.invoke(new FindEvenNums(array, 0, array.size()));

        System.out.println("Even numbers: " + result);
    }

}