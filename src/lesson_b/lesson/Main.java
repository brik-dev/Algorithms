package lesson_b.lesson;

import java.util.Arrays;

public class Main {

    //** 2. реализация своего собственного динамического массыва
    private static class Array{
        //внутри массива должен быть массив, где все это будет храниться
        // a) NB! später generics kommen ins Spiel: Array<T>, чтобы было проще на данный момент
        private int arr[];
        // массив должен быть какого-то размера изначально и мы этот размер при описании класса не знаем, но он будет
        private int size;

        // теперь пишем конструкторы
        // конструктор No 1 пустой
        // b) -> нет, поэтому мы его заприватим и удалим из него все остальное. Пригодиться

        private Array(){
            //this.size = 0;
            //this.arr = new int[0]; // new T[] не может так сделать -> тут мы приходим к тому, что нам пока лучше без generics a)
        }

        // конструктор No 2 с интовым сайзом, размером
        public Array(int size){
            this();
            this.size = size;
            this.arr = new int[size];

        }

        // конструктор No 3 - копирования
        public Array(int... args){
            this();
            this.size = args.length;
            this.arr = args;
        }

        // b) теперь внезапно возник вопрос, а нужен ли нам пустой конструктор?
        // -> нет, поэтому мы его заприватим и удалим из него все остальное. Пригодиться

        // c) тут мы повторили стандартное поведение
        // 1. создаем массив только с size
        // int[] arr = new int[5];
        // 2. сразу копируем туда новые решения
        // int[] arr = new int[]{1,2,3,4,5,6,7};

        // d) гетеры и сетеры мы умеем с массивами - гетеры это достать элемент. сетер это положить элемент
        public int get(int index){ // get который будет принимать инт индекс
            // по индексу мы должны получить доступ
            // -> e) 2.
            if (index >= size || index < 0){
                throw new IndexOutOfBoundsException("Your index is not correct: " + index);
            }
            return arr[index];

            // хорошее решение? -> нехватает проверки на size -> a зачем, если и так вылетит стандартное исключение -> хорошо, когда что.то случается по моему ведому. и кастомным решением говорю, пользователям моего класса, что я ожидал такого поведения
            // -> e)1.
        }

        public void set(int index, int value){
            arr[index] = value;
        }

        //f) что еще можно делать с нашим массивом ? было бы хорошо уметь делать что.то еще, например удалять елемент
        // в стандартном массиве нельзя удалять элемент ./ но мы можем ситуацию исправить. у нас динамический массив
        //-> написать публичный булеван возвращиющий метод delete без параметров
        // ! и договориться, что это будет удаления посделнего элемента

        public boolean delete(){ // last element
            if(size == 0) return false; // если размер равен нулю, то ничего не делать
            size--;
            return true;
        }

        //тут встает вопросик, а если мы можем удалять последний, может мы можем удалять луюбой -> можно и это сделать
        // возьмем стандартный более оптимизированный метод shift, чтобы не делать цикт с поэлементным копированием

        public boolean delete(int index){ //O(1) - arraycopy работает за один шаг, так как он копирует указателями области памяти
            if (index >= size || index < 0){    // при оценке О большое нужно либо смотреть внутрь (как точно работает arraycopy) либо исходить из худшего варианта worse case O(n)
                throw new IndexOutOfBoundsException("Your index is not correct: " + index);
            }
            System.arraycopy(arr, index+1, arr, index, size-index-1); // начинаем с arr, index + 1, ставим на место arr, index и задаем новую длину
            size--;
            return true;
        }

        @Override
        public String toString() {
            if (arr == null)
                return "null";
            int iMax = size - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(arr[i]);
                if (i == iMax)
                    return b.append(']').toString();
                b.append(", ");
            }
        }

        // дополнять массив
        public void append(int value){
            // a) -> b)
            if (size >= arr.length){
                int[] temp = arr;
                arr = new int[size * 2]; // тут можно выбрать любую стратегию, можно увеличивать массив на 1 или на 2 или еще как-то, преподу по этому курсу нравится удваивать :)
                System.arraycopy(temp, 0, arr, 0, size); // у интового массива на пустых элементах всегда буду нули. но сайзом мы ограничиваем как бы обрубаем длину массива на ту, которая нужна
            }
            arr[size++] = value; // все вроде хорошо, но массив не захочет выходить за свои пределы, которые мы определили изначально -> поэтому нужно создать новый массив a) ->
        }




        // homework

        //public boolean deleteAll(int value){   } //by value

        //public boolean deleteAll(){} // clear

        void insert(int index, int value){ // shift the tail
                int a1Len = index;
                int a2Len = size;
                int[] a1 = new int[a1Len];
                int[] a2 = new int[a2Len];
                System.arraycopy(arr, 0, a1, 0, a1Len);
                System.arraycopy(arr, index, a2,0, a2Len);
                size++;
                arr = new int[size];
                System.arraycopy(a1, 0, arr, 0, a1Len);
                arr[index] = value;
                int h = index++;
                a2Len++;
                System.arraycopy(a2, 0, arr, h, a2Len);

        }

        //** 2. линейный и двоичный поиск
        // линейный поск - поочередное сравнение всех элементов с искомым

        public boolean isInArray(int value){
            for (int i = 0; i < this.size; i++) {
                if (this.arr[i] == value){
                    return true;
                }
            }
            return false;
        }

        public boolean hasValue(int value){
            return false;
        }

        // три вида сортировки O(n^3) -> homework оптимировать один из способов сортировки
        // 1. Пузырьковая сортировка: за счет вложенного внутреннего цикла у нее сложность n^2 - верхнеуровнего: простейшая сортировка сравнения соседних элементов
        // берем первый элемент и последовательно идем по всему массиву и сравниваем со следующим. В случае необъодимости меняем местами. Таким образом большие элементы ползум вверх, меньшие вниз по массиву
        // для этого нужен вспомагателный метод

        private void swap(int a, int b){
            int tmp =  this.arr[a];
            this.arr[a] = this.arr[b];
            this.arr[b] = tmp;
        }

        public void sortBubble(){
            for (int i = 0; i < size; i++) {
                if (this.arr[i] > this.arr[i+]){
                    swap(i, i++);
                }
                
            }
        }

    }

    public static void main(String[] args) {

        //** 1. массивы - простейшая структура данных. в джава основа для создания большинства более сложных структур данных
        //standardArrayThings();
        Array array = new Array(1,2,3,4,5,6,7,8,9);
        System.out.println(array);
        array.delete();
        array.delete();
        System.out.println(array);
        array.delete(2);
        System.out.println(array);
        array.append(8);
        System.out.println(array);

        //homework to check again
//        array.insert(2, 3);
//        System.out.println(array);


        //** 2. линейный и двоичный поиск
        // линейный поск - поочередное сравнение всех элементов с искомым


    }

    private static void standardArrayThings() {
        // как храняться в памяти в первом уроке
        // что можно с ними делать:

        int[] arr = new int[5];
        ; // определить ссылку
        int arr2[]; // аналог . сишный стиль c-style
        // выделить память под массив и вернуть ссылку
        //arr2 = new int[5];
        //arr = new int[]{1,2,3,4,5,6,7}; //перегруженный конструктор

        //узнать длину массыва
        System.out.println(arr.length); // length - это поле, которое находится внутри сущности __Array__. оно publich final int и оно создается, когда мы инициализируем новый массыв
        // класс Array для работы с массивами + методы, чтобы превращать его в строку, в лист, чтобы сравнивать массивы
        System.out.println(Arrays.toString(arr)); // чтобы превращать его в строку

        // минусы массывов: из него нельзя полностью удалить элемент
        //arr = new int[]{1,2,3,0/'_'/,5,6,7}; // передвигать значения, или массыв большим размером с указателем
    }


}
