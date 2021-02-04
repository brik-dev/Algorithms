package lesson_e.online;

public class Main {

    // 1. итеративный пример
    private static void iterPrint(int i){

        while (i >= 0){
            System.out.print(i + " ");
            i--;
        }
        System.out.print(i + " ");
    }

    // 2. теперь рекурсивно - мы приняли какой-то i и меняем в рамках этой функции какой-то i
    private static void recPrint(int i){
        if (i >= 0){
            System.out.print(i + " ");
            recPrint(--i);
        }
        System.out.print(i + " ");
    }

    private static int op = 0;
    private static void put(int from, int to){
        System.out.printf("%d -> %d | ", from, to);
        if ((++op % 5) == 0 ) System.out.println();
    }

    private static void hanoi(int from, int to, int n){ //  у нас есть три стержня: 1. from, 3, to и промежуточный и n колец

        int temp = from ^ to; // бинарная математика. это второй стержень и если стержней 1, 2 и 3, то это будет работать
        // 01 - 1, 10 - 2, 11 - 3 и оператор XOR возвращает истин, если операнды различаются
        // если наша задача состоит из перемещения одного единственного кольца, то ...
        if(n == 1){
            put(from, to);
        }else { // если колец больше чем 1, то ухидим в рекурсия так как обсуждали чуть раньше и зарисовывали
            // нам нужно взять все верхние кольцы, то есть n-1 колец и переместить их на вспомогательный стержень
            // то есть нам нужно решить задачу о ханойской башни с использованием нашего же метода и передавать параметры с 1 стержня на temp промежуточный и без одного кольцы n -1, чуть меньшего размера
            hanoi(from, temp, n-1);
            put(from, to); //дальше мы перекладываем одно единственное большое кольцо, которое сталось
            hanoi(temp, to, n -1);// и решаем ту же задачу для колец, которые лежат на временном стержне
        }

    }

    private static int[][] moves = { // все возможные ходы изходя из текущей позиции/точки
        {-2, 1}, {-1, 2}, {1, 2}, {2, 1},
        {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    private static boolean isPossible(int[][] desk, int x, int y){ // возможен ли ход сюда. Здесь нужна нам доска по которой мы ходим и координаты, куда хотим поставить
        return x >= 0 && x < desk[0].length &&
                y >= 0 && y < desk.length &&
                desk[y][x] == 0;
    }
    public static boolean knight(int[][] desk, int x, int y, int move){ // Основная задача - обойти шахматным конем шахматную доску, не наступив одну и туже клетку дважды и не оставив ни одну пустую клетку
                                    // Принимаем доску по корой ходим
        // мы узнаем, что мы решили задачу, когда сделаем 64-й ход

        desk[y][x] = move;
        // если ход больше 64, заканчиваем
        if (move > desk.length * desk[0].length - 1) return true;
        // куда мы будем делать ход?
        // в одну из вот этих вот возможных позиций, поэтому мы считаем nextX, nextY
        int nextX;
        int nextY;
        // бежим по массиву moves
        for (int i = 0; i < moves.length - 1; i++) {
            nextX = x + moves[i][0];
            nextY = y + moves[i][1];
            if (isPossible(desk, nextX, nextY) && knight(desk, nextX, nextY, move+1) )
                return true; // в) если мы можем куда-то пойти по доске и след ход вернул истину, то и этот ход вернет истину
        }

        // а) попытаемся его сделать
        // б) ..если сделав все 8 ходов, попытавшись, мы не разу не выбрались с истины за все восемь ходов, мы должны вернуться на доску по текущим координатам
        desk[y][x] = 0; // как будто нас тут нестояло и вернуть лож
        return false; // неуспех этого хода
        // и через этот успех или не успех хода любого, мы достигнем успеха нашего всего мероприятия
        // и мы описываем всю задачу, описывая один ход в этом методе
    }

    // Задача о шахматном короле
    // задача о восьми ферзях

    public static void main(String[] args) {
        iterPrint(5);
        System.out.println();
        recPrint(5);
        System.out.println();
        /**
         * 5 4 3 2 1 0 -1
         * 5 4 3 2 1 0 -1 -1 0 1 2 3 4
         */

        /**
         * Что произошло?
         * итеративный метод
         * -> Стек вызовов: см oneNote
         * -> рекурсия работает за счет этого стека вызовов
         *
         * рекурсивный метод
         * -> проверка условия и рекурсивный вызов
         */

        hanoi(1,3,8);
        System.out.println();
        System.out.println(op); // степень алгоритма хайнойской башни 2^n, два в степень количества колец
        System.out.println();

        int[][] desk = new int[8][8];
        knight(desk, 0,1,1);
        printDesk(desk);

    }

    private static void printDesk(int[][] desk){
        for (int y = 0; y < desk.length; y++){
            for (int x = 0; x < desk[y].length; x++){
                System.out.printf("%3d", desk[y][x]);
            }
            System.out.println();
        }
    }

}
