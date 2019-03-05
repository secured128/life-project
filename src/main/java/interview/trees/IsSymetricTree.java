package interview.trees;

public class IsSymetricTree {

    public static boolean isSymetric(Node root) {
        if (root == null) {
            return true;
        } else {
            return checkSymetric(root.getLeft(), root.getRight());
        }
    }


    static boolean checkSymetric(Node st1, Node st2) {
        if (st1 == null && st2 == null) {
            return true;
        }
        if (st1 == null && st2 != null) {
            return false;
        }
        if (st1 != null && st2 == null) {
            return false;
        }
        if (st1.getKey() != st2.getKey()) {
            return false;
        }
        return checkSymetric(st1.getLeft(), st2.getRight()) && checkSymetric(st1.getRight(), st2.getLeft());
    }


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

        System.out.println(isSymetric(n10));

        Node n0 = new Node(0);
        Node n5l = new Node(5);
        Node n5r = new Node(5);
        Node n6l = new Node(6);
        Node n6r = new Node(6);

        n5l.setLeft(n6l);
        n5r.setRight(n6r);

        n0.setLeft(n5l);
        n0.setRight(n5r);
        System.out.println(isSymetric(n0));


    }

}
