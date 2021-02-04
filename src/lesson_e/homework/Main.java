package lesson_e.homework;

/**
 * 1. Написать программу по возведению числа в степень с помощью рекурсии.
 * 2. * Задача о шахматном короле
 * 3. * Задача о восьми ферзях
 */
public class Main {

    // Возведение в степень.
    public static long exponent(long x, long n) {
        if (n == 1) {
            return x;
        } else {
            return x * exponent(x, n - 1);
        }
    }

    // Задача о шахматном короле
    private static int op = 0;

    private static void put(int x, int y){
        System.out.printf("-> %d, %d | ", x, y);
        if ((++op % 5) == 0 ) System.out.println();
    }

    private static int[][] moves = { // все возможные ходы (вправо и вниз) изходя из текущей позиции/точки
            {0, 1}, {1, 0}
    };

    private static void kingMoves(int x, int y){ //  координаты клетки, куда должен перейти в конецном итоге король

        if(x <= 1 && y <= 1){
            put(y, x);
        }else {
            //..
        }

    }


    public static void main(String[] args) {

        System.out.println(exponent(2, 5));

//        kingMoves(int x, int y);
//        System.out.println();
//        System.out.println(op);
//        System.out.println();

    }
}
