package lesson_c.lesson;

public class Stack {
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
