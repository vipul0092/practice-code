package code.vipul.aoc2018;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static code.vipul.aoc2018.Inputs2.DAY_25;
import static java.util.stream.Collectors.toSet;

/**
 * https://adventofcode.com/2018/day/25
 */
public class Solve25 {

    private static final String INPUT = "1,-1,-1,-2\n" +
            "-2,-2,0,1\n" +
            "0,2,1,3\n" +
            "-2,3,-2,1\n" +
            "0,2,3,-2\n" +
            "-1,-1,1,-2\n" +
            "0,-2,-1,0\n" +
            "-2,2,3,-1\n" +
            "1,2,2,0\n" +
            "-1,-2,0,-2";

    public static void solve() {
        String[] lines = DAY_25.split("\n");

        List<Point> points = new ArrayList<>();

        int currentConstellationNumber = 1;
        Map<Integer, Integer> constellationGroup = new LinkedHashMap<>();

        for (String line : lines) {
            points.add(Point.of(line));
        }

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                int distance = p1.manhattan(p2);

                if (distance > 3) {
                    continue;
                }

                int group = -1;
                if (!constellationGroup.containsKey(i) && !constellationGroup.containsKey(j)) {
                    group = currentConstellationNumber++;
                } else if (constellationGroup.containsKey(i) && constellationGroup.containsKey(j)) {
                    int group2 = constellationGroup.get(j);
                    int group1 = constellationGroup.get(i);
                    // Set the constellation id to be the same for the other group as well
                    Set<Integer> otherGroupPoints = constellationGroup.entrySet().stream()
                            .filter(e -> e.getValue() == group2)
                            .map(e -> e.getKey())
                            .collect(toSet());
                    otherGroupPoints.forEach(p -> constellationGroup.put(p, group1));
                } else if (constellationGroup.containsKey(i)) {
                    group = constellationGroup.get(i);
                } else if (constellationGroup.containsKey(j)) {
                    group = constellationGroup.get(j);
                }

                if (group > 0) {
                    constellationGroup.put(i, group);
                    constellationGroup.put(j, group);
                }
            }
        }

        int constellations =
                new HashSet<>(constellationGroup.values()).size() + (points.size() - constellationGroup.size());

        System.out.println("Answer: " + constellations); //383
    }

    private static class Point {
        private final int x;
        private final int y;
        private final int z;
        private final int w;

        Point(int x, int y, int z, int w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }

        static Point of(String line) {
            String[] xyzw = line.split(",");
            int x = Integer.parseInt(xyzw[0]);
            int y = Integer.parseInt(xyzw[1]);
            int z = Integer.parseInt(xyzw[2]);
            int w = Integer.parseInt(xyzw[3]);

            return new Point(x, y, z, w);
        }

        int manhattan(Point p) {
            return Math.abs(p.x - x) + Math.abs(p.y - y) + Math.abs(p.z - z) + Math.abs(p.w - w);
        }

        public String toString() {
            return String.format("%s,%s,%s,%s", x, y, z, w);
        }
    }
}
