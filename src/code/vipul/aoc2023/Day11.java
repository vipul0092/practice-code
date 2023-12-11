package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static code.vipul.aoc2023.inputs.Inputs.DAY_11;

/**
 * Created by vgaur created on 11/12/23
 */
public class Day11 {

    private static String INPUT = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
            """;

    public static void solve() {
        INPUT = DAY_11;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Set<Integer> norows = new HashSet<>();
        Set<Integer> nocols = new HashSet<>();
        List<Point> galaxies = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            boolean rblank = true;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '#') {
                    rblank = false;
                    galaxies.add(new Point(i, j));
                }
            }
            if (rblank) {
                norows.add(i);
            }
        }

        for (int j = 0; j < lines.get(0).length(); j++) {
            boolean cblank = true;
            for (String line : lines) {
                if (line.charAt(j) == '#') {
                    cblank = false;
                    break;
                }
            }
            if (cblank) {
                nocols.add(j);
            }
        }

        System.out.println("Part 1: " + getTotalPath(galaxies, norows, nocols, 2)); // 9608724
        System.out.println("Part 2: " + getTotalPath(galaxies, norows, nocols, 1000000)); // 904633799472
    }

    ;

    private static long getTotalPath(List<Point> galaxies, Set<Integer> norows, Set<Integer> nocols, int times) {
        long sum = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Point g1 = galaxies.get(i);
                Point g2 = galaxies.get(j);

                long diff = Math.abs(g1.x - g2.x) + Math.abs(g1.y - g2.y);
                int sx = Math.min(g1.x, g2.x), ex = Math.max(g1.x, g2.x);
                int sy = Math.min(g1.y, g2.y), ey = Math.max(g1.y, g2.y);

                for (int k = sx + 1; k < ex; k++) {
                    if (norows.contains(k)) diff += (times - 1);
                }
                for (int k = sy + 1; k < ey; k++) {
                    if (nocols.contains(k)) diff += (times - 1);
                }
                sum += diff;
            }
        }
        return sum;
    }

    record Point(int x, int y) {
    }
}
