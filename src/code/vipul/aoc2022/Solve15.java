package code.vipul.aoc2022;

import code.vipul.Pair;
import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 15/12/22
 * https://adventofcode.com/2022/day/15
 */
public class Solve15 {

    private static final String INPUT = "Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n" +
            "Sensor at x=9, y=16: closest beacon is at x=10, y=16\n" +
            "Sensor at x=13, y=2: closest beacon is at x=15, y=3\n" +
            "Sensor at x=12, y=14: closest beacon is at x=10, y=16\n" +
            "Sensor at x=10, y=20: closest beacon is at x=10, y=16\n" +
            "Sensor at x=14, y=17: closest beacon is at x=10, y=16\n" +
            "Sensor at x=8, y=7: closest beacon is at x=2, y=10\n" +
            "Sensor at x=2, y=0: closest beacon is at x=2, y=10\n" +
            "Sensor at x=0, y=11: closest beacon is at x=2, y=10\n" +
            "Sensor at x=20, y=14: closest beacon is at x=25, y=17\n" +
            "Sensor at x=17, y=20: closest beacon is at x=21, y=22\n" +
            "Sensor at x=16, y=7: closest beacon is at x=15, y=3\n" +
            "Sensor at x=14, y=3: closest beacon is at x=15, y=3\n" +
            "Sensor at x=20, y=1: closest beacon is at x=15, y=3";

    private static int minX = Integer.MAX_VALUE, maxX = 0;
    private static Set<Grid.Pos> sensors;
    private static Map<Grid.Pos, Integer> sensorDistance;
    private static Set<Grid.Pos> beacons;
    private static int ycoordinateToTest;
    private static int ymax;

    public static void solve() {
        parse(Inputs.INPUT_15, 2000000, 4000000);
        //parse(INPUT, 10, 20);

        int part1 = 0;
        List<Pair<Integer, Integer>> ranges =
                getSensorCoveredMergedRanges(ycoordinateToTest, Integer.MIN_VALUE, Integer.MAX_VALUE);

        for (Pair<Integer, Integer> range : ranges) {
            part1 += (range.right() - range.left() + 1);

            int beaconsInRange = (int) beacons.stream()
                    .filter(b -> b.j() == ycoordinateToTest && (b.i() >= range.left() && b.i() <= range.right()))
                    .count();
            part1 -= (beaconsInRange);
        }
        System.out.println(part1);
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_15, 2000000, 4000000);
        //parse(INPUT, 10, 20);

        Grid.Pos ans = null;
        for (int y = 0; y <= ymax; y++) {

            // For the given value of y
            // Calculate all the merged ranges of x where the beacon can't be present for each sensor
            // i.e. the ranges of x that each sensor covers for the given y
            // All the ranges must merge into a single range
            // If we find that's not the case, we've got our answer
            // because that means that a particular value of x is not detectable by any of the beacons
            // Here, we're exploiting the fact there is only one answer
            List<Pair<Integer, Integer>> ranges = getSensorCoveredMergedRanges(y, 0, ymax);

            if (ranges.size() > 1) {
                ans = Grid.Pos.of(ranges.get(0).right() + 1, y);
                break;
            }
        }

        System.out.println(ans);
        long a = (4000000L * ans.i()) + ans.j();
        System.out.println(a);
    }

    private static List<Pair<Integer, Integer>> getSensorCoveredMergedRanges(int y, int minX, int maxX) {
        List<Pair<Integer, Integer>> ranges = new ArrayList<>();
        for (Grid.Pos sensor : sensors) {
            int ydiff = Math.abs(sensor.j() - y);
            int detectionDistance = sensorDistance.get(sensor);

            // if the diff of y coordinates is greater than what the sensor can detect
            // there is no value in moving forward with this sensor
            if (detectionDistance < ydiff) {
                continue;
            }
            int diff = Math.abs(detectionDistance - ydiff);

            int curMinX = Integer.MAX_VALUE;
            int curMaxX = 0;

            curMinX = Math.min(curMinX, sensor.i() + diff);
            curMinX = Math.min(curMinX, sensor.i() - diff);
            curMaxX = Math.max(curMaxX, sensor.i() + diff);
            curMaxX = Math.max(curMaxX, sensor.i() - diff);

            ranges.add(Pair.of(Math.max(curMinX, minX), Math.min(curMaxX, maxX)));
        }
        return merge(ranges);
    }

    private static List<Pair<Integer, Integer>> merge(List<Pair<Integer, Integer>> ranges) {
        List<Pair<Integer, Integer>> merged = new ArrayList<>();
        ranges = ranges.stream().sorted((p1, p2) -> {
            if (p1.left().equals(p2.left())) {
                return p1.right() - p2.right();
            } else {
                return p1.left() - p2.left();
            }
        }).collect(Collectors.toList());

        Pair<Integer, Integer> current = ranges.get(0);
        for (int i = 1; i < ranges.size(); i++) {
            Pair<Integer, Integer> r = ranges.get(i);
            if ((r.right() - current.left() < -1) || (r.left() - current.right() > 1)) {
                merged.add(current);
                current = r;
            } else {
                current = Pair.of(Math.min(r.left(), current.left()), Math.max(r.right(), current.right()));
            }
        }
        merged.add(current);
        return merged;
    }

    private static void parse(String input, int yval, int ymaxval) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        ycoordinateToTest = yval;
        ymax = ymaxval;
        sensors = new HashSet<>();
        sensorDistance = new HashMap<>();
        beacons = new HashSet<>();

        for (String in : inputs) {
            String[] sb = in.split(": closest beacon is at ");

            Grid.Pos beacon = get(sb[1]);
            Grid.Pos sensor = get(sb[0].split("Sensor at ")[1]);
            beacons.add(beacon);

            sensors.add(sensor);
            sensorDistance.put(sensor, distance(sensor, beacon));

            minX = Math.min(minX, sensor.i());
            minX = Math.min(minX, beacon.i());
            maxX = Math.max(maxX, sensor.i());
            maxX = Math.max(maxX, beacon.i());
        }
    }

    private static Grid.Pos get(String str) {
        String[] xy = str.split(", ");
        int x = Integer.parseInt(xy[0].substring(2));
        int y = Integer.parseInt(xy[1].substring(2));

        return Grid.Pos.of(x, y);
    }

    private static int distance(Grid.Pos p1, Grid.Pos p2) {
        return  Math.abs(p1.i() - p2.i()) + Math.abs(p1.j() - p2.j());
    }
}
