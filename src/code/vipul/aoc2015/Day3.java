package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_3;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day3 {

    private static String INPUT = """
            ^v^v^v^v^v
            """;
    record Point(int x, int y){}
    public static void solve() {
        INPUT = DAY_3;
        String line = Arrays.stream(INPUT.split("\n")).toList().get(0);
        Map<Character, int[]> movements = Map.of('^', new int[]{-1, 0}, 'v', new int[]{1, 0}, '>',
                new int[]{0, 1}, '<', new int[]{0, -1});

        Set<Point> delivered = new HashSet<>();
        Point current = new Point(0, 0);
        delivered.add(current);
        for (char dir : line.toCharArray()) {
            var diffs = movements.get(dir);
            current = new Point(current.x + diffs[0], current.y + diffs[1]);
            delivered.add(current);
        }
        System.out.println("Part 1: " + delivered.size());

        delivered = new HashSet<>();
        Point current1 = new Point(0, 0);
        Point current2 = new Point(0, 0);
        delivered.add(current1);
        delivered.add(current2);
        for (int i = 0; i < line.length(); i += 2) {
            char dir1 = line.charAt(i);
            char dir2 = line.charAt(i+1);
            var diffs1 = movements.get(dir1);
            var diffs2 = movements.get(dir2);
            current1 = new Point(current1.x + diffs1[0], current1.y + diffs1[1]);
            current2 = new Point(current2.x + diffs2[0], current2.y + diffs2[1]);
            delivered.add(current1);
            delivered.add(current2);
        }

        System.out.println("Part 2: " + delivered.size());
    }
}
