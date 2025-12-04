package code.vipul.aoc2025;

import code.vipul.utils.AoCInputReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://adventofcode.com/2025/day/4
 */
public class Day4 {

    record Point(int x, int y) {}
    private static final int[] DIFF = new int[]{-1, 0, 1};

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day4.class, false);

        int r = lines.size(), c = lines.getFirst().length(), count1 = 0, iterations = 0;

        Set<Point> removed = new HashSet<>();
        Set<Point> curRemoved;
        do {
            curRemoved = new HashSet<>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    Point p = new Point(i, j);
                    if (get(lines, p) != '@' || removed.contains(p)) continue;

                    int adjacent = 0;
                    for (int d1 : DIFF) {
                        for (int d2 : DIFF) {
                            if (d1 == 0 && d2 == 0) continue;

                            Point tmp = new Point(i + d1, j + d2);
                            if (!removed.contains(tmp) && get(lines, tmp) == '@') {
                                adjacent++;
                            }
                        }
                    }
                    if (adjacent < 4) {
                        curRemoved.add(p);
                    }
                }
            }
            if (iterations == 0) count1 = curRemoved.size();
            removed.addAll(curRemoved);
            iterations++;
        } while (!curRemoved.isEmpty());

        System.out.println("Part 1: " + count1); // 1527
        System.out.println("Part 2: " + removed.size()); // 8690
    }

    private static char get(List<String> lines, Point p) {
        int r = lines.size(), c = lines.getFirst().length(), x = p.x, y = p.y;
        return x >= 0 && x < r && y >= 0 && y < c ? lines.get(x).charAt(y) : '\0';
    }
}
