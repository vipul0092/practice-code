package code.vipul.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 04/01/23
 * https://adventofcode.com/2021/day/18
 */
public class Solve18 {

    private static final String INPUT = "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]\n" +
            "[[[5,[2,8]],4],[5,[[9,9],0]]]\n" +
            "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]\n" +
            "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]\n" +
            "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]\n" +
            "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]\n" +
            "[[[[5,4],[7,7]],8],[[8,3],8]]\n" +
            "[[9,3],[[9,9],[6,[4,9]]]]\n" +
            "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]\n" +
            "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_18.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        List<Node> numbers = new ArrayList<>();
        for (String in : inputs) {
            numbers.add(parse(in));
        }

        Node sum = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Node num2 = numbers.get(i);

            Node currentSum = new Node();
            currentSum.setChild(sum);
            currentSum.setChild(num2);

            reduce(currentSum);
            sum = currentSum;
        }
        System.out.println("Part 1: " + sum.getMagnitude());

        long max = Long.MIN_VALUE;
        for (int i = 0; i < inputs.size(); i++) {
            String num1Str = inputs.get(i);
            for (int j = i + 1; j < inputs.size(); j++) {
                // num1 + num2
                Node num1 = parse(num1Str);
                Node num2 = parse(inputs.get(j));
                Node currentSum = new Node();
                currentSum.setChild(num1);
                currentSum.setChild(num2);
                reduce(currentSum);
                max = Math.max(max, currentSum.getMagnitude());

                // num2 + num1
                num1 = parse(num1Str);
                num2 = parse(inputs.get(j));
                currentSum = new Node();
                currentSum.setChild(num2);
                currentSum.setChild(num1);
                reduce(currentSum);
                max = Math.max(max, currentSum.getMagnitude());
            }
        }
        System.out.println("Part 2: " + max);
    }

    private static void reduce(Node node) {
        Node explode;
        Node split;
        do {
            split = null;
            explode = findExplodePair(node, 0);
            if (explode != null) {
                explode(explode);
                continue;
            }
            split = findSplitNode(node);
            if (split != null) {
                split(split);
            }
        } while (!(explode == null && split == null));
    }

    private static void explode(Node node) {
        Node rightValueNode = node.right;
        Node leftValueNode = node.left;

        Node rightSearch = searchValueNodeUpwards(node.parent, node, false);
        Node leftSearch = searchValueNodeUpwards(node.parent, node, true);

        if (rightSearch != null) {
            rightSearch.value += rightValueNode.value;
        }
        if (leftSearch != null) {
            leftSearch.value += leftValueNode.value;
        }

        Node zero = new Node(0);
        node.parent.replaceChild(node, zero);
    }

    private static void split(Node node) {
        int value = node.value;
        Node split1 = new Node(value / 2);
        Node split2 = new Node(value - split1.value);

        Node parent = node.parent;
        Node newNode = new Node();
        newNode.setChild(split1);
        newNode.setChild(split2);
        parent.replaceChild(node, newNode);
    }


    private static Node findExplodePair(Node node, int depth) {
        if (node.isValuePair() && depth >= 4) {
            return node;
        }

        if (node.isValueNode()) {
            return null;
        }

        Node found = null;
        found = findExplodePair(node.left, depth + 1);
        if (found != null) {
            return found;
        }
        return findExplodePair(node.right, depth + 1);
    }

    private static Node findSplitNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.isValueNode() && node.value >= 10) {
            return node;
        }

        Node found = findSplitNode(node.left);
        if (found != null) {
            return found;
        }
        return findSplitNode(node.right);
    }

    private static Node searchValueNodeUpwards(Node node, Node prev, boolean leftSearch) {
        if (node == null) {
            return null;
        }

        Node toCheck = leftSearch ? node.left : node.right;
        if (toCheck != prev) {
            return searchValueNodeDownwards(toCheck, !leftSearch);
        }
        return searchValueNodeUpwards(node.parent, node, leftSearch);
    }

    private static Node searchValueNodeDownwards(Node node, boolean leftFirst) {
        if (node == null) {
            return null;
        }

        if (node.isValueNode()) {
            return node;
        }

        Node found = searchValueNodeDownwards(leftFirst ? node.left : node.right, leftFirst);
        if (found != null) {
            return found;
        }
        return searchValueNodeDownwards(leftFirst ? node.right : node.left, leftFirst);
    }

    private static Node parse(String in) {
        Node current = new Node();
        Stack<Node> stack = new Stack<>();
        for (char ch : in.toCharArray()) {
            if (ch == '[') {
                Node newNode = new Node();
                stack.push(current);
                current = newNode;
            } else if (ch == ']') {
                Node parent = stack.pop();
                parent.setChild(current);
                current = parent;
            } else if (ch >= '0' && ch <= '9') {
                Node node = new Node(ch - '0');
                current.setChild(node);
            }
        }
        return current.left;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;

        private Node(int val) {
            this.value = val;
        }

        private Node() {
            this.value = -1;
        }

        public boolean isValueNode() {
            return this.value != -1;
        }

        public boolean isValuePair() {
            return !isValueNode() && left.isValueNode() && right.isValueNode();
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setChild(Node node) {
            if (left == null) {
                left = node;
            } else {
                right = node;
            }
            node.parent = this;
        }

        public void replaceChild(Node oldChild, Node newChild) {
            oldChild.parent = null;
            if (left == oldChild) {
                left = newChild;
            } else if (right == oldChild) {
                right = newChild;
            }
            newChild.parent = this;
        }

        public long getMagnitude() {
            if (isValueNode()) {
                return this.value;
            } else {
                return (3L * left.getMagnitude()) + (2L * right.getMagnitude());
            }
        }

        @Override
        public String toString() {
            if (isValueNode()) {
                return String.format("%s", value);
            } else {
                return String.format("[%s,%s]",
                        left != null ? left.toString() : "", right != null ? right.toString() : "");
            }
        }
    }
}
