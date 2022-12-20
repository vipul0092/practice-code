package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;
import code.vipul.linkedlist.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 20/12/22
 */
public class Solve20 {

    private static final String INPUT = "1\n" +
            "2\n" +
            "-3\n" +
            "3\n" +
            "-2\n" +
            "0\n" +
            "4";

    private static final long KEY = 811589153;

    private static List<Node> nodes;
    private static Node zeroNode;

    public static void solve() {
        //PART 1
        parse(Inputs.INPUT_20);
        mix(false);
        sum(false);

        //PART 2
        parse(Inputs.INPUT_20);
        int times = 10;
        while (times-- > 0) {
            mix(true);
        }
        sum(true);
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        nodes = new ArrayList<>();
        zeroNode = null;

        Node prev = null;
        for (String in : inputs) {
            long num = Long.parseLong(in);
            Node current = Node.of(num);
            nodes.add(current);
            if (prev != null) {
                prev.next = current;
                current.prev = prev;
            }
            prev = current;
            if (num == 0) {
                zeroNode = current;
            }
        }

        nodes.get(0).prev = nodes.get(nodes.size() - 1);
        nodes.get(nodes.size() - 1).next = nodes.get(0);
    }

    private static void mix(boolean withKey) {
        for (Node numnode : nodes) {
            if (numnode.longVal == 0) {
                continue;
            }
            numnode.prev.next = numnode.next;
            numnode.next.prev = numnode.prev;

            Node move = numnode;
            long times = (Math.abs(numnode.longVal) * (withKey ? KEY : 1)) % (nodes.size() - 1);
            while (times-- > 0) {
                if (numnode.longVal < 0) {
                    move = move.prev;
                } else {
                    move = move.next;
                }
            }

            if (numnode.longVal > 0) {
                Node movedsNext = move.next;
                move.next = numnode;
                numnode.prev = move;
                numnode.next = movedsNext;
                movedsNext.prev = numnode;
            } else {
                Node movedsPrev = move.prev;
                move.prev = numnode;
                numnode.next = move;
                numnode.prev = movedsPrev;
                movedsPrev.next = numnode;
            }
        }
    }

    private static void sum(boolean withKey) {
        Node move = zeroNode;
        long sum = 0;
        for (int i = 0; i < 3000; i++) {
            move = move.next;
            if (i == 999 || i == 1999 || i == 2999) {
                //System.out.println((move.longVal * (withKey ? KEY : 1)));
                sum += (move.longVal * (withKey ? KEY : 1));
            }
        }
        System.out.println(sum);
    }

}
