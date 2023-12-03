package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static code.vipul.aoc2023.inputs.Inputs.DAY_3;

/**
 * Created by vgaur created on 02/12/23
 */
public class Day3 {

    private static final String INPUT = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
            """;
    private static final int[] DIFFS = new int[]{-1, 0, 1};

    public static void solve() {
        List<String> lines = Arrays.stream(DAY_3.split("\n")).toList();
        //List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Set<Point> chars = new HashSet<>();
        Set<Point> gears = new HashSet<>();

        List<Num> numbers = new ArrayList<>();
        Map<Point, Integer> pointToNums = new HashMap<>();

        int l = 0;
        for (String line : lines) {
            int curnum = 0;
            Set<Point> pts = new HashSet<>();
            Set<Point> npts = new HashSet<>();
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    curnum = (curnum * 10) + (ch - '0');
                    npts.add(new Point(l, i));
                    for (int dx : DIFFS) {
                        for (int dy : DIFFS) {
                            Point tp = new Point(l + dx, i + dy);
                            pts.add(tp);
                        }
                    }
                } else {
                    if (ch != '.') chars.add(new Point(l, i));
                    if (ch == '*') gears.add(new Point(l, i));
                    if (curnum > 0) {
                        numbers.add(new Num(curnum, pts));
                        for (var npt : npts) {
                            pointToNums.put(npt, curnum);
                        }
                    }
                    curnum = 0;
                    pts = new HashSet<>();
                    npts = new HashSet<>();
                }
            }

            if (curnum != 0) {
                numbers.add(new Num(curnum, pts));
                for (var npt : npts) {
                    pointToNums.put(npt, curnum);
                }
            }
            l++;
        }

        int sum = 0;
        for (Num num : numbers) {
            boolean possible = false;
            for (Point p : num.neighbors()) {
                if (chars.contains(p)) {
                    possible = true;
                    break;
                }
            }

            if (possible) sum += num.number();
        }
        System.out.println(sum); // 540131

        // Part 2 Begins
        long sum2 = 0;
        for (Point gear : gears) {
            Set<Integer> found = new HashSet<>();
            for (int dx : DIFFS) {
                for (int dy : DIFFS) {
                    if (dx == 0 && dy == 0) continue;
                    Point t = new Point(gear.x() + dx, gear.y() + dy);
                    if (pointToNums.containsKey(t)) {
                        found.add(pointToNums.get(t));
                    }
                }
            }
            if (found.size() == 2) {
                long prod = 1;
                for (Integer f : found) {
                    prod *= f;
                }
                sum2 += prod;
            }
        }
        System.out.println(sum2); // 86879020
    }

    record Point(int x, int y) {
    }

    record Num(int number, Set<Point> neighbors) {
    }
}
