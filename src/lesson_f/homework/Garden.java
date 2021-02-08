package lesson_f.homework;

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
        private static class TreeNode implements Comparable{

            private Cat c;
            public TreeNode left;
            public TreeNode right;

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

        TreeNode root;
        
        public void insert(Cat c){
            TreeNode node = new TreeNode(c);
            if(root == null){
                root = node;
            }else {
                TreeNode current = root;
                TreeNode parent;
                while (true){
                    parent = current;
                    if(c.age < current.c.age){
                        current = current.left;
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
                        return;
                    }
                }
            }
        }

        public Cat find(int age){
            TreeNode current = root;
            while (current.c.age != age){
                current = (age < current.c.age) ? current.left : current.right;
                if(current == null) return null;
            }
            return current.c;
        }

        // обход в симетричном порядке: Left -> Root -> Right
        // обход в обратном порядке: Right -> Left -> Root

        // обход в праямом порядке: Root -> Left -> Right
        public void preOrderTraverse(TreeNode currentNode){
            if(currentNode != null){
                System.out.println(currentNode);
                preOrderTraverse(currentNode.left);
                preOrderTraverse(currentNode.right);
            }
        }

        public void displayTree(){
            preOrderTraverse(root);
        }

        public Cat delete(int age){

            TreeNode current = root;
            TreeNode parent = root;
            boolean isLeftChild = true;
            while (current.c.age != age) {

                parent = current;

                 if (age < current.c.age){
                     current = current.left;
                     isLeftChild = true;
                 }else {
                     current = current.right;
                     isLeftChild = false;
                 }
                 if (current == null){
                     return null;
                 }
            }

            if (current.left == null && current.right == null){

                if (current == root){
                    root = null;
                }else if (isLeftChild){
                    current.left = null;
                }else {
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
