package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_12;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day12 {

    private static String INPUT = """
            [{"d":"red","e":[1,2,3,4],"f":5}]
            """;
    private static final String SKIP_CHECK = "red";
    private static boolean skip = true;

    public static void solve() {
        INPUT = DAY_12;
        String json = Arrays.stream(INPUT.split("\n")).toList().get(0);

        int num = 0;
        boolean positive = true;

        Stack<Node> nodes = new Stack<>();
        Node current = Node.arrayNode();
        for (int i = 0; i < json.length();) {
            char ch = json.charAt(i);
            if (ch == '[') {
                nodes.push(current);
                current = Node.arrayNode();
            } else if (ch == ']' || ch == '}') {
                Node parent = nodes.pop(); // get the parent
                parent.add(current);
                current = parent;
            } else if (ch == '{') {
                nodes.push(current);
                current = Node.propNode();
            } else if (ch == '"') {
                i++;
                StringBuilder sb = new StringBuilder();
                while (json.charAt(i) != '"') {
                    sb.append(json.charAt(i));
                    i++;
                }
                Node strNode = Node.create(sb.toString());
                if (current.isArray()) {
                    current.add(strNode);
                } else if (current.isObject()) {
                    if (!current.isKeyAdded) {
                        current.insertKey(strNode.string);
                    } else {
                        current.add(strNode);
                    }
                }
            } else if (ch == '-' || (ch >= '0' && ch <= '9')) {
                positive = ch != '-';
                num = 0;
                if (ch == '-') i++;
                while (json.charAt(i) >= '0' && json.charAt(i) <= '9') {
                    num = (num*10) + (json.charAt(i) - '0');
                    i++;
                }
                Node numNode = Node.create(positive ? num : -num);
                if (current.isArray()) {
                    current.add(numNode);
                } else if (current.isObject()) {
                    current.add(numNode);
                }
                i--;
            }
            i++;
        }

        Node node = current.children.get(0);

        skip = false;
        System.out.println("Part 1: " + node.getCount()); // 191164
        skip = true;
        System.out.println("Part 2: " + node.getCount()); // 87842
    }

    private static class Node {
        private List<Node> children;
        private String string;
        private Integer number;
        private Map<String, Node> properties;

        public void add(Node node) {
            if (isArray()) {
                this.children.add(node);
            } else if (isObject()) {
                this.addToLastKey(node);
            } else {
                throw new RuntimeException("Cant add bruv, not an array or an object bruv");
            }
        }

        public int getCount() {
            if (this.isNumber()) {
                return this.number;
            } else if (this.isString()) {
                return 0;
            } else if (this.isArray()) {
                int sum = 0;
                for (var child : this.children) {
                    sum += child.getCount();
                }
                return sum;
            } else { // Object
                int sum = 0;
                for (var entry : this.properties.entrySet()) {
                    if (skip && entry.getValue().isString() && entry.getValue().string.equals(SKIP_CHECK)) {
                        return 0;
                    }
                    sum += entry.getValue().getCount();
                }
                return sum;
            }
        }

        public static Node arrayNode() {
            Node node = new Node();
            node.children = new ArrayList<>();
            return node;
        }

        public static Node create(String str) {
            Node node = new Node();
            node.string = str;
            return node;
        }

        public static Node create(int num) {
            Node node = new Node();
            node.number = num;
            return node;
        }

        public static Node propNode() {
            Node node = new Node();
            node.properties = new HashMap<>();
            return node;
        }

        public boolean isArray() {
            return children != null;
        }

        public boolean isObject() {
            return properties != null;
        }

        public boolean isNumber() {
            return number != null;
        }

        public boolean isString() {
            return string != null;
        }

        @Override
        public String toString() {
            if (isArray()) {
                return this.children.toString();
            } else if (isObject()) {
                return this.properties.toString();
            } else if (number != null) {
                return String.valueOf(number);
            } else if (string != null) {
                return string;
            }
            return "";
        }

        private String lastKey;
        private boolean isKeyAdded = false;
        public void insertKey(String key) {
            this.lastKey = key;
            properties.put(lastKey, Node.propNode());
            isKeyAdded = true;
        }

        public void addToLastKey(Node node) {
            if (!isKeyAdded) throw new RuntimeException("last key is null bruv");
            properties.put(lastKey, node);
            isKeyAdded = false;
        }
    }
}
