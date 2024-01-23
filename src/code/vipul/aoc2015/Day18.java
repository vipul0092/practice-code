package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_18;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day18 {

    private static String INPUT = """
            .#.#.#
            ...##.
            #....#
            ..#...
            #.#..#
            ####..
            """;

    record Point(int x, int y){}

    private static int rows = -1, cols = -1;

    private static final List<List<Integer>> DIFFS = List.of(
            List.of(-1, 0), List.of(1, 0), List.of(0, 1), List.of(0, -1),
            List.of(1, 1), List.of(1, -1), List.of(-1, -1), List.of(-1, 1));

    public static void solve() {
        INPUT = DAY_18;
        int steps = 100;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        rows = lines.size();
        cols = lines.get(0).length();
        Set<Point> on = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (lines.get(i).charAt(j) == '#') {
                    on.add(new Point(i, j));
                }
            }
        }
        System.out.println("Part 1: " + getOnCount(on, steps, false)); // 814
        System.out.println("Part 2: " + getOnCount(on, steps, true)); // 924
    }

    private static int getOnCount(Set<Point> initialOnState, int steps, boolean turnOnCorners) {
        Set<Point> on = initialOnState;
        if (turnOnCorners) {
            on.add(new Point(0, 0));
            on.add(new Point(0, cols-1));
            on.add(new Point(rows-1, 0));
            on.add(new Point(rows-1, cols-1));
        }
        while(steps-- > 0) {
            Set<Point> newOn = new HashSet<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Point p = new Point(i, j);
                    Set<Point> finalOn = on;
                    long onCount = DIFFS.stream().map(d -> new Point(d.get(0) + p.x, d.get(1) + p.y))
                            .filter(pt -> valid(pt) && finalOn.contains(pt))
                            .count();
                    boolean turnOn;
                    if (on.contains(p)) {
                        turnOn = onCount == 2 || onCount == 3;
                    } else {
                        turnOn = onCount == 3;
                    }

                    if (turnOn) {
                        newOn.add(p);
                    }
                }
            }
            if (turnOnCorners) {
                newOn.add(new Point(0, 0));
                newOn.add(new Point(0, cols-1));
                newOn.add(new Point(rows-1, 0));
                newOn.add(new Point(rows-1, cols-1));
            }
            on = newOn;
        }
        return on.size();
    }

    private static boolean valid(Point p) {
        return p.x >= 0 && p.y >= 0 && p.x < rows && p.y < cols;
    }
}
