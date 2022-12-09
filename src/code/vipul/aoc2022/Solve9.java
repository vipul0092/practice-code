package code.vipul.aoc2022;

import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 08/12/22
 */
public class Solve9 {

    private static final String INPUT = "R 5\n" +
            "U 8\n" +
            "L 8\n" +
            "D 3\n" +
            "R 17\n" +
            "D 10\n" +
            "L 25\n" +
            "U 20";

    private static String INPUT_1 = "R 4\n" +
            "U 4\n" +
            "L 3\n" +
            "D 1\n" +
            "R 4\n" +
            "D 1\n" +
            "L 5\n" +
            "R 2";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_9.split("\n")).collect(Collectors.toList());
        // List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        Grid.Pos head = Grid.Pos.of(0, 0);
        Grid.Pos tail = Grid.Pos.of(0, 0);

        Set<Grid.Pos> positions = new LinkedHashSet<>();
        positions.add(tail);

        for (String in : inputs) {
            String[] split = in.split(" ");
            int move = Integer.parseInt(split[1]);
            char dir = split[0].charAt(0);

            int t = move;
            if (dir == 'R') {
                while (t-- > 0) {
                    head = head.moveRight(1);
                    tail = moveTail(head, tail);
                    positions.add(tail);
                }
            } else if (dir == 'L') {
                while (t-- > 0) {
                    head = head.moveLeft(1);
                    tail = moveTail(head, tail);
                    positions.add(tail);
                }
            } else if (dir == 'U') {
                while (t-- > 0) {
                    head = head.moveUp(1);
                    tail = moveTail(head, tail);
                    positions.add(tail);
                }
            } else if (dir == 'D') {
                while (t-- > 0) {
                    head = head.moveDown(1);
                    tail = moveTail(head, tail);
                    positions.add(tail);
                }
            }
        }

        System.out.println(positions.size());
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_9.split("\n")).collect(Collectors.toList());
        // List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        Grid.Pos[] rope = new Grid.Pos[10];
        for (int i = 0; i < 10; i++) {
            rope[i] = Grid.Pos.of(0, 0);
        }

        Set<Grid.Pos> positions = new LinkedHashSet<>();
        positions.add(rope[9]);

        for (String in : inputs) {
            String[] split = in.split(" ");
            int move = Integer.parseInt(split[1]);
            Character dir = split[0].charAt(0);

            int t = move;
            if (dir == 'R') {
                while (t-- > 0) {
                    rope[0] = rope[0].moveRight(1);
                    for (int i = 1; i < 10; i++) {
                        rope[i] = moveTail(rope[i - 1], rope[i]);
                    }
                    positions.add(rope[9]);
                }
            } else if (dir == 'L') {
                while (t-- > 0) {
                    rope[0] = rope[0].moveLeft(1);
                    for (int i = 1; i < 10; i++) {
                        rope[i] = moveTail(rope[i - 1], rope[i]);
                    }
                    positions.add(rope[9]);
                }
            } else if (dir == 'U') {
                while (t-- > 0) {
                    rope[0] = rope[0].moveUp(1);
                    for (int i = 1; i < 10; i++) {
                        rope[i] = moveTail(rope[i - 1], rope[i]);
                    }
                    positions.add(rope[9]);
                }
            } else if (dir == 'D') {
                while (t-- > 0) {
                    rope[0] = rope[0].moveDown(1);
                    for (int i = 1; i < 10; i++) {
                        rope[i] = moveTail(rope[i - 1], rope[i]);
                    }
                    positions.add(rope[9]);
                }
            }
        }

        System.out.println(positions.size());
    }

    private static Grid.Pos moveTail(Grid.Pos head, Grid.Pos tail) {
        if (head.i() == tail.i() && Math.abs(head.j() - tail.j()) > 1) {
            if (head.j() > tail.j()) {
                return tail.moveUp(1);
            } else {
                return tail.moveDown(1);
            }
        } else if (head.j() == tail.j() && Math.abs(head.i() - tail.i()) > 1) {
            if (head.i() > tail.i()) {
                return tail.moveRight(1);
            } else {
                return tail.moveLeft(1);
            }
        } else if (Math.abs(head.j() - tail.j()) > 1 && Math.abs(head.i() - tail.i()) > 1) {
            Grid.Pos t = tail.moveDown(1);
            Grid.Pos tl = t.moveRight(1);

            if (head.j() == tl.j() || head.i() == tl.i()
                    || ((Math.abs(head.j() - tl.j()) + Math.abs(head.i() - tl.i())) == 2)) {
                return tl;
            }

            tl = t.moveLeft(1);
            if (head.j() == tl.j() || head.i() == tl.i()
                    || ((Math.abs(head.j() - tl.j()) + Math.abs(head.i() - tl.i())) == 2)) {
                return tl;
            }

            t = tail.moveUp(1);
            tl = t.moveRight(1);

            if (head.j() == tl.j() || head.i() == tl.i()
                    || ((Math.abs(head.j() - tl.j()) + Math.abs(head.i() - tl.i())) == 2)) {
                return tl;
            }

            tl = t.moveLeft(1);
            if (head.j() == tl.j() || head.i() == tl.i()
                    || ((Math.abs(head.j() - tl.j()) + Math.abs(head.i() - tl.i())) == 2)) {
                return tl;
            }
        } else if (Math.abs(head.j() - tail.j()) > 1 || Math.abs(head.i() - tail.i()) > 1) {
            Grid.Pos t = tail.moveDown(1);
            t = catchup(t, head);
            if (t != null) {
                return t;
            }

            t = tail.moveUp(1);
            t = catchup(t, head);
            if (t != null) {
                return t;
            }

            t = tail.moveRight(1);
            t = catchup(t, head);
            if (t != null) {
                return t;
            }

            t = tail.moveLeft(1);
            t = catchup(t, head);
            if (t != null) {
                return t;
            }
        }
        return tail;
    }

    private static Grid.Pos catchup(Grid.Pos t, Grid.Pos head) {
        if (t.j() == head.j()) {
            if (head.i() > t.i()) {
                return t.moveRight(1);
            } else {
                return t.moveLeft(1);
            }
        } else if (head.i() == t.i()) {
            if (head.j() > t.j()) {
                return t.moveUp(1);
            } else {
                return t.moveDown(1);
            }
        }
        return null;
    }
}
