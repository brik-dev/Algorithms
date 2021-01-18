package lesson_a;

public class homework {

    /*
    1. Описать простейшие алгоритмы
        1.1. Возведение в степень *используя чётность степени*
        1.2. Поиск минимального элемента в массиве
        1.3. Нахождение среднего арифметического массива
    2. Подсчитать сложность описанных алгоритмов
    3. Какие правила подсчёта применялись, оставьте комментарии в коде
     */

    public static void main(String[] args){
        int[] arr = {9, 1, 5, 8, 7};

        //1.1. Возведение в степень
        // 2. Сложность: O(n)
        System.out.println(pow(2, 4));

        //1.2. Поиск минимального элемента в массиве
        // 2. Сложность: O(n)
        System.out.println(min(arr));

        //1.3. Нахождение среднего арифметического массива
        // 2. Сложность: O(n)
        System.out.println(avr(arr));
    }

    private static int avr(int[] arr) { // 3. Алгоритму нужно выполнить n шагов (пройти по массиву) -> O(n)
        int avr = 0;
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum = sum + arr[i];
        }
        return sum / arr.length;
    }

    private static int min(int[] arr) { // 3. Алгоритму нужно выполнить n шагов (пройти по массиву) -> O(n)
        int min = arr[0];               // ! Здесь, наверное можно выявить минимальное число путем разделения массива пополам и сравнения во отдельности,
                                        // тогда, возможно будет ускорение алгоритма до O(log n).
        for(int num : arr){
            if (num < min){
                min = num;
            }
        }
        return min;
    }



    public static int pow(int value, int powValue){ // 3. Алгоритм выполяет действие n раз -> O(n)
        int result = 1;
        for(int i = 1; i <= powValue; i++){
            result = result*value;
        }
        return result;
    }


}
