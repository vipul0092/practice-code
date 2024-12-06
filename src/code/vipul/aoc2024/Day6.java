package code.vipul.aoc2024;

import java.util.*;

import static code.vipul.aoc2024.inputs.Inputs.DAY_6;

/**
 * https://adventofcode.com/2024/day/6
 */
public class Day6 {

    private static String INPUT = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;

    record Point(int i, int j) {}
    record Pwd(Point p, Direction d) {}
    record Result(int pointsVisited, StopType stopType) {}

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    enum StopType {
        CYCLE, EXIT
    }

    private static Point extraRock = null;

    public static void solve() {
        INPUT = DAY_6;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Point start = null;
        List<Point> dots = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                Point pt = new Point(i, j);
                char ch = get(lines, pt);
                if (ch == '^') {
                    start = pt;
                } else if (ch == '.') {
                    dots.add(pt);
                }
            }
        }
        int count1 = moveUntilExit(lines, start).pointsVisited;

        int count2 = 0;
        for (Point dot : dots) {
            extraRock = dot;
            var result = moveUntilExit(lines, start);
            if (result.stopType == StopType.CYCLE) count2++;
        }


        System.out.println("Part 1: " + count1); // 4883
        System.out.println("Part 2: " + count2); // 1655
    }

    private static Result moveUntilExit(List<String> lines, Point start) {
        Point curr = start;
        Direction direction = Direction.UP;
        char c = '^';
        Set<Point> points = new LinkedHashSet<>();
        Set<Pwd> pwds = new LinkedHashSet<>();
        pwds.add(new Pwd(curr, direction));
        points.add(curr);

        StopType stopType;
        while (true) {
            Point prev = curr;
            curr = move(curr, direction);

            c = get(lines, curr);
            if (c == '#') {
                direction = rotate(direction);
                curr = prev;
            }

            if (c == '\0') {
                stopType = StopType.EXIT;
                break;
            }

            Pwd pwd = new Pwd(curr, direction);
            if (pwds.contains(pwd)) {
                stopType = StopType.CYCLE;
                break;
            }
            pwds.add(pwd);
            points.add(curr);
        }
        return new Result(points.size(), stopType);
    }

    private static char get(List<String> lines, Point p) {
        if (p.equals(extraRock)) {
            return '#';
        }
        try {
            return lines.get(p.i).charAt(p.j);
        } catch (Exception e) {
            return '\0';
        }
    }

    private static Direction rotate(Direction direction) {
        if (direction == Direction.UP) {
            direction = Direction.RIGHT;
        } else if (direction == Direction.DOWN) {
            direction = Direction.LEFT;
        } else if (direction == Direction.LEFT) {
            direction = Direction.UP;
        } else {
            direction = Direction.DOWN;
        }
        return direction;
    }

    private static Point move(Point curr, Direction direction) {
        if (direction == Direction.UP) {
            curr = new Point(curr.i - 1, curr.j);
        } else if (direction == Direction.DOWN) {
            curr = new Point(curr.i + 1, curr.j);
        } else if (direction == Direction.LEFT) {
            curr = new Point(curr.i, curr.j - 1);
        } else {
            curr = new Point(curr.i, curr.j + 1);
        }
        return curr;
    }
}
