package code.vipul.aoc2017;

import static code.vipul.aoc2017.inputs.Inputs.DAY_17;

/**
 * Created by vgaur created on 28/12/23
 */
public class Day17 {

    private static int INPUT = 3;

    public static void solve() {
        INPUT = DAY_17;

        System.out.println("Part 1: " + simulate(2017)); // 1547

        // For part 2, its enough to just track the currently inserted number and its position
        // So that we can record whenever we insert something after '0'
        int pos = 0, len = 1, num = 1;
        int after0 = -1;
        while (num < 50000000) {
            int move = INPUT % len;
            pos = (pos + move) % len;
            if (pos == 0) {
                after0 = num;
                //System.out.println(after0);
            }
            pos++;
            num++;
            len++;
        }

        System.out.println("Part 2: " + after0); // 31154878
    }

    private static int simulate(int insertions) {
        int cur = 1, size = 1;
        Node current = new Node(0);
        current.next = current;
        while (insertions-- > 0) {
            int move = INPUT % size;
            while (move-- > 0) {
                current = current.next;
            }
            Node curnext = current.next;
            Node newnode = new Node(cur);
            newnode.next = curnext;
            current.next = newnode;
            current = newnode;
            size++;
            cur++;
        }
        return current.next.val;
    }

    private static class Node {
        private final int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
