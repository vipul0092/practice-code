package code.vipul.aoc2020;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static code.vipul.aoc2020.Inputs.INPUT_7;

/**
 * https://adventofcode.com/2020/day/7
 */
public class Solve7 {

    private static final String SHINY_GOLD = "shiny gold";
    static final Map<String, Node> NODES = new HashMap<>();

    public static void solve() {
        parse();
        var colorSet = new HashSet<String>();

        Queue<String> colors = new ArrayDeque<>();
        colors.add(SHINY_GOLD);

        while(!colors.isEmpty()) {
            String color = colors.poll();
            NODES.get(color).parent.forEach(parentConnection -> {
                if (!colorSet.contains(parentConnection.color)) {
                    colors.add(parentConnection.color);
                    colorSet.add(parentConnection.color);
                }
            });
        }
        System.out.println("Answer: " + colorSet.size()); //103
    }

    public static void solvePart2() {
        parse();
        int count = dive(SHINY_GOLD, 1);
        System.out.println("Answer: " + count); //1469
    }

    private static int dive(String currentColor, int multiplier) {
        if (NODES.get(currentColor).child.size() == 0) {
            return multiplier;
        }

        int total = currentColor.equals(SHINY_GOLD) ? 0 : multiplier;
        for (Connection connection: NODES.get(currentColor).child) {
            total += dive(connection.color, connection.quantity * multiplier);
        }
        return total;
    }

    private static void parse() {
        var rows = INPUT_7.split("\n");

        for (var row : rows) {
            var parentWithContents = row.split("contain");
            var parentColor = parentWithContents[0].trim().replaceAll(" bags", "");
            putIfAbsent(parentColor);

            var colorContents = parentWithContents[1].trim().split(",");

            boolean hasNoChild = colorContents.length == 1 && colorContents[0].startsWith("no");
            if (!hasNoChild) {
                for (var colorContent : colorContents) {
                    int quantity = colorContent.trim().charAt(0) - 48;
                    String childColor = colorContent.trim()
                            .replaceAll(" bags\\.", "")
                            .replaceAll(" bags", "")
                            .replaceAll(" bag\\.", "")
                            .replaceAll(" bag", "")
                            .substring(2);
                    putIfAbsent(childColor);

                    NODES.get(parentColor).addChild(new Connection(childColor, quantity));
                    NODES.get(childColor).addParent(new Connection(parentColor, quantity));
                }
            }
        }
    }

    private static void putIfAbsent(String parentColor) {
        if (!NODES.containsKey(parentColor)) {
            NODES.put(parentColor, new Node(parentColor));
        }
    }

    static class Node {
        private final String color;
        private final List<Connection> parent;
        private final List<Connection> child;
        private int childQuantities = 0;

        Node(String color) {
            this.color = color;
            this.parent = new ArrayList<>();
            this.child = new ArrayList<>();
        }

        void addParent(Connection connection) {
            this.parent.add(connection);
        }

        void addChild(Connection connection) {
            this.child.add(connection);
            childQuantities += connection.quantity;
        }

        @Override
        public String toString() {
            return color;
        }
    }

    static class Connection {
        private final String color;
        private final int quantity;

        Connection(String color, int quantity) {
            this.color = color;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("Color: %s, Quantity: %s", color, quantity);
        }
    }
}
