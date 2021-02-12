package lesson_h.online;

import javax.print.DocFlavor;
import java.util.Iterator;
import java.util.Objects;
import java.util.SimpleTimeZone;
import java.util.logging.Handler;

public class Main {
    // и будет класс айтем
    public static class Item{
        private int data; // просто какие-то данные

        public Item(int data) {// констуктор, который будет заполнять сразу эту дейта
            this.data = data;
        }

        public int getData() { // геттер
            return data;
        }

        @Override
        public boolean equals(Object o) { // и иквелс . Хеш напишем свой
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return data == item.data;
        }

    }
    public static class HashCat{ // это как хеш тейбл, только хещ котик
        // идем в хештаблицу и понимаем, что тут у нас будут храниться айтемы
        private Item[] hashArray;
        // нам нужен arraysize
        private int arraySize;
        // и понадобиться конструктор, который будет принимать arraySize и создавать для нас новый обьъект hashArray из arraySize
        private static Item nullItem = new Item(-1);

        public HashCat(int arraySize) {
            this.arraySize = arraySize;
            this.hashArray = new Item[arraySize];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arraySize; i++) {
                sb.append((hashArray[i] != null) ? hashArray[i].getData() : "*" ); // в зависимости от хеш i, если она не равняется 0, то мы из нее берем ключ. В противном случае пишем значек звездочки
                if (i > arraySize - 1) // кроме последнего
                    sb.append(",");
            }
            return sb.toString();
        }

        // и какая-то хешфункция понадобиться, мы же все такие про хеш таблицы говорим
        // и мы ее назовем
        private int hashFunc(int key){ // будет принимат она ключ, ключ может быть любой, это может быть data из item, может быть номер айтема
            // в этом случае это будт инт, данные для инт удем брать от куда-нибудь внутри предмета
            return key % arraySize; // хеш функция будет простейшая - это остаток от деления ключа на arraySize
        }

        private boolean isFull(){
            for (int i = 0; i < hashArray.length; i++) {
                if(hashArray[i] == null || hashArray[i] == nullItem){
                    return false;
                }
            }
            return true;
        }

        private void increaseCapacity(){ // increaseCapacity делаем в insert
            arraySize *= 2;
            Item[] oldArr = hashArray;
            hashArray = new Item[arraySize];
            for (int i = 0; i < oldArr.length; i++) {
                insert(oldArr[i]);
                
            }
        }

        // для вставки мы будем пользоваться алгоритмом, который называется открытаю адресация
        // что это такое: у нас есть массив определенного размера. Обычно подразумеваетсяа, что мы должны взять массив в *2 большего размера и мы можем вставлять туду данные
        // соответственно, если у нас массив в два раза большего размера чем у нас данных - как будут располагаться коллизийные данные?

        // есть три основных подхода
        // 1. линейное пробирование linear probe
        // посчитали хеш и если захешированное значение попало в то место, где уже лежит значение, мы линейно берем и кладем в след элемент

        private int linearProbe(int hasVal) {
            ++hasVal;
            hasVal %= arraySize; // обрубаем наш хеш велью по эрейсайзу
            return hasVal;
        }

        // 2. квадратичное пробирование quad probe

        private int quadProbe(int hashVal, int step){
            hashVal += step * step;
            hashVal %= arraySize;
            return hashVal;
        }
        public void insert(Item item){ // будем вставлять item
            // и получим ключ. взяли дейта по ключу
            int key = item.getData();
            int hashVal = hashFunc(key);
            int step = hashFuncDouble(key); // 1;
            if (isFull()) increaseCapacity();
            while (hashArray[hashVal] != null && hashArray[hashVal] != nullItem) {// пока хеш эррей по значению хеш велью не будет равен 0, мы его сдвигаем ++hashVal, то есть увеличиваем на 1
                //hasVal = linearProbe(hashVal);
                //hashVal = quadProbe(hashVal, ++step);
                hashVal += step;
                hashVal %= arraySize;
            }
            hashArray[hashVal] = item;
        }
        // добавим функцию поиска к линейному пробированию
        // будет выглядить очень похожим образом
        public Item find(int key){
            int hashVal = hashFunc(key);
            int startVal = hashVal;
            int step = hashFuncDouble(key); // 1;
            while (hashArray[hashVal] != null){
                if (hashArray[hashVal].getData() == key){
                    return hashArray[hashVal];
                }
                    //hashVal = linearProbe(hashVal);
                    //hashVal = quadProbe(hashVal, ++step);
                     hashVal += step;
                     hashVal %= arraySize;
                if (hashVal == startVal)
                    return null;
            }
            return null;
        }

        public Item delete(int key){
            int hashVal = hashFunc(key);
            int step = hashFuncDouble(key); // 1;
            while (hashArray[hashVal] != null){
                if (hashArray[hashVal].getData() == key){ //как в поиске, но только - если ключи совпали, нам нужно не вернуть, а что-то тут занулить
                    Item temp = hashArray[hashVal];
                    hashArray[hashVal] = nullItem;  // что делать с айтемом, который нашли -> создать пустой айтем и его туда подставлять
                    return temp;
                }
                //hashVal = linearProbe(hashVal);
                //hashVal = quadProbe(hashVal, ++step);
                hashVal += step;
                hashVal %= arraySize;
            }
            return null;
        }

        // 3. двойное хеширование double hash
        // смещение равняется константа минус результат от деления ключа на константу
        // shift = constant - (key % constant); const == prime number < arraySize; - простое число меньшее чем размер массива

        private int hashFuncDouble(int key){
            return 11 - (key % 11);
        }
    }
    public static void main(String[] args) {

        HashCat cat = new HashCat(25);
        cat.insert(new Item(10));
        cat.insert(new Item(20));
        cat.insert(new Item(30));
        cat.insert(new Item(40));
        cat.insert(new Item(50));
        cat.insert(new Item(75));
        cat.insert(new Item(60));
        cat.insert(new Item(70));
        System.out.println(cat);
        cat.delete(75);
        System.out.println(cat);


        /*

        Например, у нас есть какой-то набор из слов:
          апельсин
          картофель
          молоко
          творог
          яйца

          - мы можем взять какой-нибудь алгоритм хеширования, который нам на первый взгляд даст очень хорошие данный


         */
    }
}
