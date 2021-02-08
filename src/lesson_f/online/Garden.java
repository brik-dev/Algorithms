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
        // никто не знает, что там внутри дерева, поэтому там приватная нода
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

        // Алгоритм удаления из дерева
        public Cat delete(int age){
            // наш понадобиться current Treenode текущий
            TreeNode current = root; // выставим его на корень
            TreeNode parent = root; // Для привязки
            boolean isLeftChild = true;
            while (current.c.age != age) { // вобежим по нашему дереву, пока кота в текущем узле не совпадет у кота возраст с переданным возрастом
                // теперь бегаем
                // нам нужно переключаться, поэтому
                parent = current;
                // и переключаться мы будет либо влево, либо вправо c использованием уже известного компоратора
                 if (age < current.c.age){
                     // если меньше то мы уходим влево
                     // и здесь нам важна одна оговорка: нам бы переключать перента и нам бы как то учесть, в какую сторону мы ушли
                     // если мы ушли в какой-то узел, то мы знаем что это за родитель. Но мы незнаем в случае удаления этого узла, родителю важно привязать левый узел или правый
                     // поэтому мы придумаем какой-то boolean с названием isLeftChild - признак направления
                     // и здесь, когда мы ушли влево, мы говорим isLeftChild true, в обратном случае false
                     current = current.left;
                     isLeftChild = true;

                 }else {
                     // в противном случае вдругую сторону
                     current = current.right;
                     isLeftChild = false;

                 }
                 // если мы ушли настолько, что карень ноль, то значит кот не найден
                 if (current == null){
                     return null;
                 }
            }

            // когда мы нашли нужного кота по возрасту, нам нужно его удалить
            // самы простой вариант - это удаление листа
            // leaf - здаесь мы говорим, если у текущего лефт и райт равны нулю
            if (current.left == null && current.right == null){
                // тут мы находимся в узле и тут у нас есть 3 варианта развития событий
                // 1. мы как узел можем быть рутом, тогда мы зануляем рут
                if (current == root){
                    root = null;
                }else if (isLeftChild){ // 2. мы можем быть left child, тогда у перента нужно занулить левую ссылку
                    current.left = null;
                }else { // 3. мы можем быть не левым child - тогда зануляем у паранта правую ссылку
                    current.right = null;
                }
            }
            // one ancestor
            else if (current.right == null) {
                if (isLeftChild)
                    parent.left = current.left;
                else
                    parent.right = current.left;
            }
            else if (current.left == null) {
                if (isLeftChild)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
            // two ancestors
            else {
                TreeNode successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
                successor.left = current.left;
            }

            // то есть в конце мы возвращаем current кота
            return current.c;
        }

        private TreeNode getSuccessor(TreeNode node) {
            TreeNode current = node.right;
            TreeNode parent = node;
            TreeNode successor = node;
            while (current != null) {
                parent = successor;
                successor = current;
                current = current.left;
            }

            if (successor != node.right) {
                parent.left = successor.right;
                successor.right = node.right;
            }
            return successor;
        }
    }

    public static void main(String[] args) {

    }
}
