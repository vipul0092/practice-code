package code.vipul.aoc2017;

import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2017.inputs.Inputs.DAY_19;

/**
 * Created by vgaur created on 29/12/23
 */
public class Day19 {

    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private static final int[][] DIFFS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0},};
    private static String INPUT = """
                 |         \s
                 |  +--+   \s
                 A  |  C   \s
             F---|----E|--+\s
                 |  |  |  D\s
                 +B-+  +--+\s
            """;

    public static void solve() {
        INPUT = DAY_19;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Point start = null;
        for (int j = 0; j < lines.get(0).length(); j++) {
            char ch = lines.get(0).charAt(j);
            if (ch == '|') {
                start = new Point(0, j);
                break;
            }
        }

        Point current = start;
        int direction = DOWN;
        StringBuilder letters = new StringBuilder();
        int steps = 0;
        while (true) {
            char ch = value(current, lines);
            if (ch >= 'A' && ch <= 'Z') {
                letters.append(ch);
            }
            while (ch != '+' && ch != ' ') {
                current = current.move(direction);
                steps++;
                ch = value(current, lines);
                if (ch >= 'A' && ch <= 'Z') {
                    letters.append(ch);
                }
            }

            if (ch != '+') { // reached the end
                break;
            }

            Pwd pwd;
            if (direction == DOWN || direction == UP) {
                pwd = getNextPoint(DIFFS[0], LEFT, DIFFS[1], RIGHT, lines, current);
            } else {
                pwd = getNextPoint(DIFFS[2], UP, DIFFS[3], DOWN, lines, current);
            }
            steps++;
            current = pwd.p;
            direction = pwd.dir;
        }


        System.out.println("Part 1: " + letters.toString()); // GINOWKYXH
        System.out.println("Part 2: " + steps); // 16636
    }

    private static char value(Point p, List<String> lines) {
        return lines.get(p.i).charAt(p.j);
    }

    private static boolean valid(Point p, List<String> lines) {
        return p.i >= 0 && p.j >= 0 && p.i < lines.size() && p.j < lines.get(0).length();
    }

    private static Pwd getNextPoint(int[] f, int fd, int[] s, int sd, List<String> lines, Point p) {
        Point n = new Point(p.i + f[0], p.j + f[1]);
        if (valid(n, lines) && value(n, lines) != ' ') {
            return new Pwd(n, fd);
        }
        n = new Point(p.i + s[0], p.j + s[1]);
        if (valid(n, lines) && value(n, lines) != ' ') {
            return new Pwd(n, sd);
        }
        throw new RuntimeException("Cant find next point");
    }

    record Point(int i, int j) {
        public Point move(int dir) {
            return switch (dir) {
                case UP -> new Point(i - 1, j);
                case DOWN -> new Point(i + 1, j);
                case LEFT -> new Point(i, j - 1);
                case RIGHT -> new Point(i, j + 1);
                default -> throw new RuntimeException("Impossivel");
            };
        }
    }

    record Pwd(Point p, int dir) {
    }
}
