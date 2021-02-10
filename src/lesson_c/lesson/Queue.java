package lesson_c.lesson;

import java.util.Arrays;

public class Queue {
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
        if (tail >= queue.length - 1)
            tail = -1;
        queue[++tail] = i;
        capacity++;
    }

    public int remove() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        int temp = queue[head++];
        head %= queue.length; //if (head == queue.length) head = 0;
        capacity--;
        return temp;
    }

    public int getSize() {
        return queue.length;
    }
    public int[] getQueue() {
        return queue;
    }
    public int getHead() {
        return head;
    }
    public void setHead(int head) {
        if (head == queue.length) head = 0;
        if (head < 0) head = queue.length - 1;
        this.head = head;
    }
    public int getTail() {
        return tail;
    }
    public void setTail(int tail) {
        if (tail < 0) tail = queue.length - 1;
        if (tail == queue.length) tail = -1;
        this.tail = tail;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public String toString() {
        return Arrays.toString(queue);
    }
}
