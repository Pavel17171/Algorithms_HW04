package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LeftRedBlackTree node = new LeftRedBlackTree();
        Scanner sc = new Scanner(System.in);
        LeftRedBlackTree.Node root = null;
        String answer;
        do {
            System.out.println("Введите целое число: ");
            int num = sc.nextInt();
            root = node.insert(root, num);

            node.inorder(root);
            System.out.println("\nЕщё? (если да, введите Д или Y) ");
            answer = sc.next().toLowerCase();
        } while (answer.equals("y") || answer.equals("д"));

    }
}

//----BinaryTree------------------------------

class LeftRedBlackTree {
    Node root = null;

    class Node {
        int value;
        Node left;
        Node right;
        boolean color; // true - RED; false - BLACK

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
            color = true;
        }
    }


//*************************
//*-------Home-Work-------*
//*************************


    /**
     * Левый поворот
     *
     * @param cur наше значение
     * @return вернет дочерний элемент
     */
    Node leftRotate(Node cur) {
        System.out.println("Поворот влево ");
        Node child = cur.right;
        Node childLeft = cur.left;
        child.left = cur;
        cur.right = childLeft;
        return child;
    }


    /**
     * Правый поворот
     *
     * @param cur наше значение
     * @return вернет дочерний элемент
     */
    Node rightRotate(Node cur) {
        System.out.println("Поворот вправо ");
        Node child = cur.left;
        Node childRight = child.right;
        child.right = cur;
        cur.left = childRight;

        return child;
    }

    /**
     * Проверка является ли узел красным
     *
     * @param cur наше значение
     * @return true - RED, false - BLACK or cur = null
     */
    boolean isRed(Node cur) {
        if (cur == null) {
            return false;
        }
        return (cur.color);
    }

    /**
     * Замена цвета
     *
     * @param node1 первый Node для замены
     * @param node2 второй Node для замены
     */
    void swapColors(Node node1, Node node2) {
        boolean temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    /**
     * Вставка в левостороннее красно-черное дерево
     *
     * @param cur   вставляемый Node
     * @param value значение
     * @return Node
     */
    Node insert(Node cur, int value) {
        if (cur == null) {
            return new Node(value);
        }
        if (value < cur.value) {
            cur.left = insert(cur.left, value);
        } else if (value > cur.value) {
            cur.right = insert(cur.right, value);
        } else {
            return cur;
        }


        // Правый дочерний черный, левый дочерний красный или не существует
        if (isRed(cur.right) && !isRed(cur.left)) {
            cur = leftRotate(cur);
            swapColors(cur, cur.left);
        }

        // Левый дочерний красный, левый внук красный
        if (isRed(cur.left) && isRed(cur.left.left)) {
            cur = rightRotate(cur);
            swapColors(cur, cur.right);
        }

        // Левый и правый дочерние красные
        if (isRed(cur.left) && isRed(cur.right)) {
            cur.color = !cur.color;
            cur.left.color = false;
            cur.right.color = false;
        }

        return cur;
    }

    /**
     * Обход по порядку
     *
     * @param node node
     */
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            char c = 'R';
            if (!node.color) {
                c = 'B';
            }
            System.out.print(node.value + "-" + c + " ");
            inorder(node.right);
        }
    }
}
