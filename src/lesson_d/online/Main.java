package lesson_d.online;
// связные списки - linked List
// приверы из жизни: батарея, звенья цепи, состав поезда
// динамическая структура данный, в отличие, например, от массива
// состоит из узлов, узел это объект с данными node и внутри данные и метаинформация. А метаинформации содержиться ссылка на следующую node

import java.util.Objects;

public class Main {
    private static class Cat {
        int age;
        String name;

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Cat(a=%d,n=%s)", age, name);
        }
    }
    private static class SingleLinkedList {
        //here is your path...
        private class Node {
            Cat c;
            Node next;
            public Node(Cat c) {
                this.c = c;
            }
            @Override
            public String toString() {
                return String.format("Node(c=%s)", c);
            }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return Objects.equals(c, node.c) &&
                        Objects.equals(next, node.next);
            }
        }
        private Node head;
        public SingleLinkedList() {
            this.head = null;
        }
        public boolean isEmpty() {
            return head == null;
        }
        public void push(Cat c) {
            Node n = new Node(c);
            n.next = head;
            head = n;
        }
        public Cat pop() {
            if (isEmpty()) return null;
            Cat temp = head.c;
            head = head.next;
            return temp;
        }
        public boolean contains(Cat c) {
            Node n = new Node(c);
            Node current = head;
            while (!current.equals(n)) {
                if (current.next == null) return false;
                else current = current.next;
            }
            return true;
        }
        public void delete(Cat c) {
            Node n = new Node(c);
            Node current = head;
            Node previous = null;
            while (!current.equals(n)) {
                if (current.next == null) return;
                else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current == head) {
                head = head.next;
            } else {
                previous.next = current.next;
            }

            // return current.c

        }
    }

    public static void main(String[] args) {
        // Что онам нужно для связных списков - узлы с данными и ссылками. Последняя ссылка ссылается в никуда

    }
}
