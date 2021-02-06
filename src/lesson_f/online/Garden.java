package lesson_f.online;

import java.util.Objects;

public class Garden {
    private class Cat{
        int age;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cat cat = (Cat) o;
            return age == cat.age &&
                    Objects.equals(name, cat.name);
        }

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("C(&s,&d)", name, age);
        }
    }

    private static class Tree{
        // никто не знает, что там снутри дерева, поэтому там приватная нода
        private static class TreeNode implements Comparable{
            // и приватная нода будет хранить котика
            private Cat c;
            // и нужны будут еще две ссылки на такие же триноды, которые будут слева и справа
            public TreeNode left;
            public TreeNode right;
            // так будет выглядеть узел нашего дерева
            // понядобится to string для вывода


            public TreeNode(Cat c) {
                this.c = c;
            }

            @Override
            public String toString() {
                return String.format("TN(%s)", c);
            }

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof Cat))
                    throw new ClassCastException("Not a cat!");
                return c.age - ((Cat) o).age;
            }
        }

        // нужен корень
        TreeNode root; // Единственноый рут, все остальное будет плясять от него

        // добавляем ноду с котом, для этого создали конструктор как раз только с котом
        public void insert(Cat c){
            TreeNode node = new TreeNode(c);
            // и вся работа происходит в if
            // есть только одна разницы, когда мы работаем с корнем и когда мы работаем не с корнем
            if(root == null){ // если у  нас нет корня, то корнем становится прилетевший к нам нод
                root = node;
            }else {
                // в противном случае, надо понять как распологать котиков в дереве
                // если потом делать поиск, то надо сравнивать больше или меньше рекурсивно
                // для сравнения нужен интерейс comparable и мы можем сравнивать котиков, переписали метод compareTo

                // тут будем сравнивать котов
                // для сравнения будем смотреть больше или меньша
                // а вот чтобы запомнить, куда этого кота потом положить, нам нужно вклинить это сравнение в поиск. Своего и котов в листьях
                TreeNode current = root; // Нужен карент и начинаем, конечно, с рута всегда. Поэтому все влгоритмы с деревьями работают со сложностью log(n)
                TreeNode parent; // нужек чтобы помнить, к кому привязывать вновь встовляемый
                while (true){ // будут иф, которые будут нас выкидывать, и найдем место в любом случае, куда этот узел вставить
                    // если больше, то вставляем справва, если меньше, то вставляем слева. Если равно, может быть по разному. Обычно узлы с одинаковами размерами не вставляются
                    // в коллекциях - это трисет - деверо множество и там не может быть повторов
                    parent = current;
                    if(c.age < current.c.age){ // если прилетевший кот младше, то нам нужно уходить влево
                        current = current.left;
                        // карент лефт становиться пустой и мы привясываем его к нашему узлу
                        if(current == null) {
                            parent.right = node;
                            return;
                        }
                    }else if(c.age > current.c.age){
                        current = current.right;
                        if(current == null) {
                            parent.right = node;
                            return;
                        }
                    }else {
                        return; // если они равны, то просто ретерним
                    }

                }

            }

        }

        // поиск - можно сделать, чтобы поиск возвращал котика. Или возвращать истину лож по котику или возвращать котика по возрасту
        public Cat find(int age){
            // поиск будет циклический
            TreeNode current = root;
            // и обычным циклом бегу по своему дереву, пока возрас котика не будет равен переданного мной возраста
            while (current.c.age != age){
                // может возникнуть две ситуации - 1. мне нужно уйти карентом вправо или 2

                // что у нас тут - тут у нас уход влево
                current = (age < current.c.age) ? current.left : current.right;
                // 2. искать больше негде
                if(current == null) return null;



            }
            // если мы успешно прошли все, то мы от куда-нибудь брейкнимся
            // Поэтому смело можем возвращать
            return current.c;
        } // в файнд можно использовать симетричный порядок, чтобы дойти до самого меньшего значения, и использовать обратный порядок, чтобы дойти до самого большого значения

        // обход в симетричном порядке: Left -> Root -> Right

        // обход в обратном порядке: Right -> Left -> Root


        // обход в праямом порядке: Root -> Left -> Right
        public void preOrderTraverse(TreeNode currentNode){ // всегда начинается с корня
            if(currentNode != null){ //если карент нод не равен тал,
                System.out.println(currentNode); // то нам его нужно посетить, например, вывести на экран
                preOrderTraverse(currentNode.left); //затем нам нужно посетить левый
                preOrderTraverse(currentNode.right); //затем нам нужно посетить правый
                // сделав это, нам нужно осуществить обход в порядке левый - правый
            }
        }
        // функцмя с применением обхода в прямом порядке
        public void displayTree(){
            // внутри которой будем делать преордертраверс с рута
            // в преордертраверс можно добавить печатание скобок, и он представит нам дерево в одну скобку терминала, в скобочной последовательности
            preOrderTraverse(root);
        }

    }

}
