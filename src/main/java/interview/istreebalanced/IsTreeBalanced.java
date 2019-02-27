package interview.istreebalanced;


import interview.Node;

public class IsTreeBalanced {

//    public static boolean isTreeBalanced(interview.Node.java node) {
////        new BalanceInf()
//
//        if()
//
//        return true;
//    }

    public static BalanceInf getBalanceInf(Node node) {
        if (node == null) {
            return new BalanceInf(true, -1);
        } else {
            BalanceInf leftInf = getBalanceInf(node.getLeft());
            BalanceInf rightInf = getBalanceInf(node.getRight());
            if (!leftInf.isBalanced) {
                return leftInf;

            }
            if (!rightInf.isBalanced) {
                return rightInf;

            }

            int dif = Math.abs(leftInf.height - rightInf.height);

            if (dif <= 1) {
                return new BalanceInf(true, Math.max(leftInf.height, rightInf.height) + 1);
            } else {
                return new BalanceInf(false, Math.max(leftInf.height, rightInf.height) + 1);
            }
        }

    }

    public static void main(String[] args) {
        Node n10 = new Node(10);
        Node n5 = new Node(5);
        Node n4 = new Node(4);
        Node n6 = new Node(6);
        Node n12 = new Node(12);
        Node n11 = new Node(11);
        Node n15 = new Node(15);
        Node n14 = new Node(14);
        Node n13 = new Node(13);

        n5.setLeft(n4);
        n5.setRight(n6);
        n10.setLeft(n5);

        n12.setLeft(n11);
        n12.setRight(n13);
        n10.setRight(n12);

        n15.setLeft(n14);
        n13.setRight(n15);


        System.out.println(getBalanceInf(n10).isBalanced);
    }

    static class BalanceInf {
        boolean isBalanced;
        int height;

        public BalanceInf(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;

        }

    }


}
