package code.vipul.aoc2025;

import code.vipul.utils.AoCInputReader;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://adventofcode.com/2025/day/7
 */
public class Day7 {

    record Point(int x, int y) {
        Point down() {
            return new Point(x + 1, y);
        }

        Point left() {
            return new Point(x, y - 1);
        }

        Point right() {
            return new Point(x, y + 1);
        }
    }

    private static long[][] cache;

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day7.class, false);

        Point start = new Point(0, lines.getFirst().indexOf('S'));
        Set<Point> points = new HashSet<>();
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);

        int split = 0;
        while (!q.isEmpty()) {
            Point pt = q.remove();
            Point down = pt.down();

            if (get(lines, down) == '^') {
                process(lines, down.left(), points, q);
                process(lines, down.right(), points, q);
                split++;
            } else {
                process(lines, down, points, q);
            }
        }

        System.out.println("Part 1: " + split); // 1638

        cache = new long[lines.size() + 1][lines.getFirst().length() + 1];
        long total2 = recurse(lines, start);
        System.out.println("Part 2: " + total2); // 7759107121385
    }

    private static long recurse(List<String> lines, Point pt) {
        if (pt.x == lines.size() - 1) {
            return 1;
        }

        if (cache[pt.x][pt.y] != 0) {
            return cache[pt.x][pt.y];
        }

        Point down = pt.down();
        if (get(lines, down) == '^') {
            Point ld = down.left();
            Point rd = down.right();
            long lc = !valid(lines, ld) ? 0 : recurse(lines, ld);
            long rc = !valid(lines, rd) ? 0 : recurse(lines, rd);
            cache[pt.x][pt.y] = lc + rc;
        } else {
            long val = !valid(lines, down) ? 0 : recurse(lines, down);
            cache[pt.x][pt.y] = val;
        }
        return cache[pt.x][pt.y];
    }

    private static void process(List<String> lines, Point pt, Set<Point> points, Queue<Point> q) {
        if (valid(lines, pt) && !points.contains(pt)) {
            points.add(pt);
            q.add(pt);
        }
    }

    private static boolean valid(List<String> lines, Point p) {
        return get(lines, p) != '\0';
    }

    private static char get(List<String> lines, Point p) {
        int r = lines.size(), c = lines.getFirst().length(), x = p.x, y = p.y;
        return x >= 0 && x < r && y >= 0 && y < c ? lines.get(x).charAt(y) : '\0';
    }
}
