package interview.trees;

import interview.Node;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraverse {

    public static void main(String[] args) {
        Node n10 = new Node(10);
        Node n5 = new Node(5);
        Node n4 = new Node(4);
        Node n6 = new Node(6);
        Node n11 = new Node(11);
        Node n15 = new Node(15);
        Node n14 = new Node(14);

        n5.setLeft(n4);
        n5.setRight(n6);
        n10.setLeft(n5);

        n15.setLeft(n14);
        n11.setRight(n15);
        n10.setRight(n11);

        Queue<Node> q = new LinkedList<Node>();
        ((LinkedList<Node>) q).add(n10);

        bfs(q);
        System.out.println();
        dfsPreorder(n10);
        System.out.println();
        dfsInorder(n10);
        System.out.println();
        dfsPostorder(n10);
    }

    public static void bfs(Queue<Node> q) {
        if (q == null || q.isEmpty()) {
            return;
        } else {
            Node node = q.poll();
            System.out.print(node.getKey() + " ");
            if (node.getLeft() != null)
                q.add(node.getLeft());
            if (node.getRight() != null)
                q.add(node.getRight());

            bfs(q);
        }
    }

    public static void dfsPreorder(Node node) {
        if (node == null) {
            return;
        } else {
            System.out.print(node.getKey() + " ");
            if (node.getLeft() != null)
                dfsPreorder(node.getLeft());
            if (node.getRight() != null)
                dfsPreorder(node.getRight());
        }
    }


    public static void dfsInorder(Node node) {
        if (node == null) {
            return;
        } else {

            if (node.getLeft() != null)
                dfsInorder(node.getLeft());
            System.out.print(node.getKey() + " ");
            if (node.getRight() != null)
                dfsInorder(node.getRight());
        }
    }


    public static void dfsPostorder(Node node) {
        if (node == null) {
            return;
        } else {
            if (node.getLeft() != null)
                dfsPostorder(node.getLeft());
            if (node.getRight() != null)
                dfsPostorder(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

}
