package code.vipul.aoc2020;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://adventofcode.com/2020/day/23
 */
public class Solve23 {

    private static final String INPUT = "789465123";

    private static TreeMap<Integer, CupNode> cupNodeMap;

    public static void solve() {
        CupNode start = populateCircularList(INPUT, false);
        print(start);
        int iterations = 100;
        while (iterations-- > 0) {
            start = move(start);
            print(start);
        }

        String labels = getFinalLabel(start);
        System.out.println("Answer: " + labels); //98752463
    }

    public static void solvePart2() {
        CupNode start = populateCircularList(INPUT, true);
        int iterations = 10_000_000;
        long s = System. currentTimeMillis();
        while (iterations > 0) {
            start = move(start);
            iterations -= 1;
        }
        long e = System. currentTimeMillis();

        System.out.println("time taken: " + (e - s));

        CupNode temp = start;
        while (temp.value != 1) {
            temp = temp.next;
        }

        long num1 = temp.next.value;
        long num2 = temp.next.next.value;

        System.out.println("Answer: " + (num1 * num2)); //2000455861, 6189
    }

    private static CupNode move(CupNode start) {
        CupNode temp = start;
        int take = 3;
        Set<Integer> taken = new LinkedHashSet<>();
        CupNode firstTaken = temp.next;
        while (take-- > 0) {
            temp = temp.next;
            taken.add(temp.value);
        }
        CupNode lastTaken = temp;
        CupNode nextToLastTaken = lastTaken.next;

        int destination = start.value;
        do {
            destination--;
            if (destination < cupNodeMap.firstKey()) {
                destination = cupNodeMap.lastKey();
            }
        } while (taken.contains(destination));

        CupNode destinationNode = cupNodeMap.get(destination);
        start.next = nextToLastTaken;
        CupNode nextToDestinationNode = destinationNode.next;

        destinationNode.next = firstTaken;
        lastTaken.next = nextToDestinationNode;

        return nextToLastTaken;
    }

    private static String getFinalLabel(CupNode start) {
        CupNode temp = start;
        while (temp.value != 1) {
            temp = temp.next;
        }

        temp = temp.next;
        StringBuilder value = new StringBuilder();
        while (temp.value != 1) {
            value.append(temp.value);
            temp = temp.next;
        }
        return value.toString();
    }

    private static CupNode populateCircularList(String input, boolean toMillion) {
        cupNodeMap = new TreeMap<>();
        CupNode current;
        CupNode start = null;
        CupNode prev = null;
        for (char ch : input.toCharArray()) {
            current = new CupNode(ch - 48);
            cupNodeMap.put(current.value, current);
            if (start == null) {
                start = current;
            }
            if (prev != null) {
                prev.next = current;
            }
            prev = current;
        }
        if (toMillion) {
            int highest = cupNodeMap.lastKey();
            do {
                current = new CupNode(++highest);
                cupNodeMap.put(current.value, current);
                prev.next = current;
                prev = current;
            } while (highest < 1_000_000);
        }
        prev.next = start;
        return start;
    }

    public static class CupNode {
        private int value;
        private CupNode next;

        CupNode(int v) {
            this.value = v;
        }

        public String toString() {
            return String.valueOf(value);
        }
    }

    private static void print(CupNode start) {
        CupNode t = start;
        Set<Integer> printed = new HashSet<>();
        while (!printed.contains(t.value)) {
            if (start.value == t.value) {
                System.out.print("(" + t.value + ") ");
            } else {
                System.out.print(t.value + " ");
            }
            printed.add(t.value);
            t = t.next;
        }
        System.out.println();
    }
}
