package lesson_c.homework;
/*
public class Main {
    private static class Stack {
        private int[] stack;
        private int head;

        public Stack(int size) {
            this.stack = new int[size];
            this.head = -1;
        }

        public boolean isEmpty() {
            return head == -1;
        }

        public boolean isFull() {
            return head == stack.length - 1;
        }

        public boolean push(int i) {
            if (isFull()) return false;
            stack[++head] = i;
            return true;
        }

        public int pop() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head--];
        }

        public int peek() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head];
        }
    }

    private static int checkBrackets(String input) {
        int size = input.length();
        Stack st = new Stack(size);
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            if (ch == '[' || ch == '(' || ch == '<' || ch == '{') {
                st.push(ch);
            } else if (ch == ']' || ch == ')' || ch == '>' || ch == '}') {
                if (st.isEmpty())
                    return i;

                char op = (char) st.pop();
                if (!((op == '[' && ch == ']') ||
                        (op == '{' && ch == '}') ||
                        (op == '(' && ch == ')') ||
                        (op == '<' && ch == '>'))) {
                    return i;
                }
            }
        }
        if (!st.isEmpty()) {
            return size;
        }
        return -1;
    }

<<<<<<< HEAD
=======
    // Homework:
    // 1. Создать программу, которая переворачивает вводимые строки (читает справа налево) при помощи стека.
    private static String reverseString(String input){
        int size = input.length();
        Stack st = new Stack(size);
        char[] output = new char[size];
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            st.push(ch);
        }
        for (int j = 0; j < size; j++) {
            char ch1 = (char) st.pop();
            output[j] = ch1;
        }
        return new String(output);
    }

>>>>>>> homework3
    private static class Queue {
        private int[] queue;
        private int head;
        private int tail;
        private int capacity;

        public Queue(int initial) {
            queue = new int[initial];
            head = 0;
            tail = -1;
            capacity = 0;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        public int length() {
            return capacity;
        }

        public void insert(int i) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            if (tail == queue.length -1)
                tail = -1;
//<<<<<<< HEAD
            queue[++tail] = i;
//=======


            queue[++tail] = i;


//>>>>>>> homework3
            capacity++;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int temp = queue[head++];
            head %= queue.length; //if (head == queue.length) head = 0;
            capacity--;
            return temp;
        }

    }

<<<<<<< HEAD
    public static void main(String[] args) {
        System.out.println(checkBrackets("<> () [] {} {[() <>]}"));
        //Deque
        //Priority Queue
=======
    // Homework:
    // 2. Создать класс для реализации дека.

    private static class Dequeue{
        // доработать
    }

    // Homework:
    // 3. Создать класс с реализацией приоритетной очереди

    private static class PriorityQueue{
        private int[] queue;
        private int head;
        private int tail;
        private int capacity;

        public PriorityQueue(int initial) {
            queue = new int[initial];
            head = 0;
            tail = -1;
            capacity = 0;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        public int length() {
            return capacity;
        }

        public void insert(int i) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            if (tail == queue.length -1)
                tail = -1;

            if (i >= tail){
                queue[++tail] = i;
            } else {
                for (int j = 0; j < queue.length; j++) {
                    if (i < queue[j]){
                        System.arraycopy(queue, j, queue, j++, queue.length + 1);
                        queue[j] = i;
                    }
                }
            }
            capacity++;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int temp = queue[head++];
            head %= queue.length; //if (head == queue.length) head = 0;
            capacity--;
            return temp;
        }
    }


    public static void main(String[] args) {
        //System.out.println(checkBrackets("<> () [] {} {[() <>]}"));

        //Homework 1:
        System.out.println(reverseString("abcd"));

        //Homework 2: not done
        //Deque

        //Homework 3: done
        //Priority Queue

>>>>>>> homework3
    }
}*/
