package code.vipul.aoc2018;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static code.vipul.aoc2018.Inputs2.DAY_23;

/**
 * https://adventofcode.com/2018/day/23
 */
public class Solve23 {

    private static final String INPUT = "pos=<0,0,0>, r=4\n" +
            "pos=<1,0,0>, r=1\n" +
            "pos=<4,0,0>, r=3\n" +
            "pos=<0,2,0>, r=1\n" +
            "pos=<0,5,0>, r=3\n" +
            "pos=<0,0,3>, r=1\n" +
            "pos=<1,1,1>, r=1\n" +
            "pos=<1,1,2>, r=1\n" +
            "pos=<1,3,1>, r=1";

    private static final Point ORIGIN = new Point(0, 0, 0);

    public static void solve() {
        String[] lines = DAY_23.split("\n");
        var nanobots = parseInput(lines);
        Nanobot strongest = nanobots.stream().max(Comparator.comparingLong(b -> b.range)).orElseThrow();

        int count = 0;
        for (var bot : nanobots) {
            if (bot.getDistance(strongest) <= strongest.range) {
                count++;
            }
        }
        System.out.println("Part 1: " + count); //906

        Point optimal = findOptimalPosition(nanobots);
        System.out.println("Part 2: " + optimal.getDistance(ORIGIN)); // 121493971
    }

    // This approach divides the space into cubes with a certain cube length, starting with the min x,y,z coordinate diff
    // The cube is defined by the bottom left and top right points, which are initially the least & highest nanobot points
    // Then we iterate over different cubes of the decided cube length and check the number of intersections with nanobots for each cube
    // Then we reduce the search to the cube that has higher number of intersections and least distance to origin (The problem statement)
    // The reduction is done by halving the cube length and changing the bottom left and top right points for the cube
    // This is done by subtracting and adding the cube length respectively from the bottom left point for the cube that has the highest intersections
    // Source C# implementation: https://github.com/stevehjohn/AoC/blob/master/AoC.Solutions/Solutions/2018/23/Part2.cs
    private static Point findOptimalPosition(List<Nanobot> nanobots) {
        var min = new Point(nanobots.stream().min(Comparator.comparingLong(b -> b.x())).orElseThrow().x(),
                nanobots.stream().min(Comparator.comparingLong(b -> b.y())).orElseThrow().y(),
                nanobots.stream().min(Comparator.comparingLong(b -> b.z())).orElseThrow().z()
        );

        var max = new Point(nanobots.stream().max(Comparator.comparingLong(b -> b.x())).orElseThrow().x(),
                nanobots.stream().max(Comparator.comparingLong(b -> b.y())).orElseThrow().y(),
                nanobots.stream().max(Comparator.comparingLong(b -> b.z())).orElseThrow().z()
        );

        var size = Math.min(Math.min(max.x() - min.x(), max.y() - min.y()), max.z() - min.z());

        Point optimal = null;

        while (size > 0) {
            int maxInRange = 0;

            for (var x = min.x(); x <= max.x(); x += size) {
                for (var y = min.y(); y <= max.y(); y += size) {
                    for (var z = min.z(); z <= max.z(); z += size) {
                        var inRange = botsInRange(nanobots, new Point(x, y, z), size);
                        Point p = new Point(x, y, z);
                        if (inRange > maxInRange) {
                            maxInRange = inRange;
                            optimal = p;
                            continue;
                        }

                        if (maxInRange == inRange) {
                            if (optimal == null || p.getDistance(ORIGIN) < optimal.getDistance(ORIGIN)) {
                                optimal = p;
                            }
                        }
                    }
                }
            }

            if (optimal == null) {
                throw new RuntimeException("This shouldn't be possible.");
            }

            min = new Point(optimal.x() - size, optimal.y() - size, optimal.z() - size);
            max = new Point(optimal.x() + size, optimal.y() + size, optimal.z() + size);
            size /= 2;
        }
        return optimal;
    }

    private static int botsInRange(List<Nanobot> nanobots, Point position, long size) {
        int inRange = 0;
        // find the bots that are in range within the cube defined by the point `position` and cube length `size`
        for (var bot : nanobots) {
            if (bot.getDistance(position) - bot.range < size) {
                inRange++;
            }
        }
        return inRange;
    }

    private static List<Nanobot> parseInput(String[] lines) {
        long x = -1, y = -1, z = -1;
        var bots = new ArrayList<Nanobot>(lines.length);
        for (String line : lines) {
            String[] parts = line.split(">, r=");
            long r = Long.parseLong(parts[1]);
            String[] xyz = parts[0].substring(5).split(",");
            x = Long.parseLong(xyz[0]);
            y = Long.parseLong(xyz[1]);
            z = Long.parseLong(xyz[2]);
            bots.add(new Nanobot(x, y, z, r));
        }
        return bots;
    }

    record Nanobot(long x, long y, long z, long range) {
        public long getDistance(Nanobot n) {
            return Math.abs(n.x - x) + Math.abs(n.y - y) + Math.abs(n.z - z);
        }

        public long getDistance(Point n) {
            return Math.abs(n.x - x) + Math.abs(n.y - y) + Math.abs(n.z - z);
        }
    }

    record Point(long x, long y, long z) {
        public long getDistance(Point n) {
            return Math.abs(n.x - x) + Math.abs(n.y - y) + Math.abs(n.z - z);
        }
    }
}
