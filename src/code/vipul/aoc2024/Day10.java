package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://adventofcode.com/2024/day/10
 */
public class Day10 {

    record Point(int i, int j) {
    }

    private static final int[][] DIFFS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static Set<Point> topsReached;

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day10.class, false);

        int sum = 0, sum2 = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                int num = get(lines, new Point(i, j));
                if (num == 0) {
                    topsReached = new HashSet<>();
                    sum2 += dfs(new Point(i, j), lines);
                    sum += topsReached.size();
                }
            }
        }

        System.out.println("Part 1: " + sum); // 531
        System.out.println("Part 2: " + sum2); // 1210
    }

    private static int dfs(Point current, List<String> lines) {
        int val = get(lines, current);
        if (val == 9) {
            topsReached.add(current);
            return 1;
        }

        int total = 0;
        for (int[] d : DIFFS) {
            Point neighbor = new Point(current.i + d[0], current.j + d[1]);
            int curVal = get(lines, neighbor);
            if (curVal < 0 || curVal > 9) continue;
            if (curVal - val == 1) {
                total += dfs(neighbor, lines);
            }
        }
        return total;
    }

    private static int get(List<String> lines, Point p) {
        try {
            return lines.get(p.i).charAt(p.j) - '0';
        } catch (Exception e) {
            return -1;
        }
    }
}
