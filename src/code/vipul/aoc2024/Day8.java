package code.vipul.aoc2024;

import java.util.*;

import static code.vipul.aoc2024.inputs.Inputs.DAY_8;

/**
 * https://adventofcode.com/2024/day/8
 */
public class Day8 {

    private static String INPUT = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """;

    record Point(int i, int j) {
    }

    public static void solve() {
        INPUT = DAY_8;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<Character, List<Point>> points = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);
                if (ch != '.') {
                    points.putIfAbsent(ch, new ArrayList<>());
                    points.get(ch).add(new Point(i, j));
                }
            }
        }

        Set<Point> answer = new HashSet<>();
        Set<Point> answer2 = new HashSet<>();
        for (List<Point> pts : points.values()) {
            for (int i = 0; i < pts.size(); i++) {
                Point pt1 = pts.get(i);

                for (int j = i + 1; j < pts.size(); j++) {
                    Point pt2 = pts.get(j);
                    int slopey = pt2.j - pt1.j;
                    int slopex = pt2.i - pt1.i;

                    for (int k = 0; k < lines.size(); k++) {
                        for (int l = 0; l < lines.get(0).length(); l++) {
                            int curSlopey = pt1.j - l;
                            int curSlopex = pt1.i - k;

                            boolean same = curSlopex * slopey == curSlopey * slopex;

                            if (same) {
                                answer2.add(new Point(k, l));
                                int dis1 = Math.abs(pt1.i - k) + Math.abs(pt1.j - l);
                                int dis2 = Math.abs(pt2.i - k) + Math.abs(pt2.j - l);

                                if (dis1 == dis2*2 || dis2 == dis1*2) {
                                    answer.add(new Point(k, l));
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Part 1: " + answer.size()); // 269
        System.out.println("Part 2: " + answer2.size()); // 949
    }
}
