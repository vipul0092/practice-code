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
        Set<Grid.Pos> validSensors = sensors.stream()
                .filter(s -> Math.abs(s.j() - ycoordinateToTest) <= sensorDistance.get(s))
                .collect(Collectors.toSet());

        for (Grid.Pos s : validSensors) {
            int ydis = Math.abs(s.j() - ycoordinateToTest);
            int diff = sensorDistance.get(s) - ydis;

            minX = Math.min(minX, s.i() + diff);
            minX = Math.min(minX, s.i() - diff);
            maxX = Math.max(maxX, s.i() + diff);
            maxX = Math.max(maxX, s.i() - diff);
        }

        for (int x = minX; x <= maxX; x++) {
            Grid.Pos p = Grid.Pos.of(x, ycoordinateToTest);
            if (beacons.contains(p)) {
                continue;
            }
            boolean beaconExists = true;
            for (Grid.Pos sensor : validSensors) {
                int distance = distance(sensor, p);
                if (distance <= sensorDistance.get(sensor)) {
                    beaconExists = false;
                    break;
                }
            }

            if (!beaconExists) {
                part1++;
            }
        }

        System.out.println(part1);
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_15, 2000000, 4000000);
        //parse(INPUT, 10, 20);

        Grid.Pos ans = null;
        for (int y = 0; y <= ymax; y++) {
            //System.out.println("y: " + y);

            // For the given value of y
            // Calculate all the ranges of x where the beacon can't be present for each sensor
            // i.e. the range of x that each sensor covers for the given y
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

                ranges.add(Pair.of(Math.max(curMinX, 0), Math.min(curMaxX, ymax)));
            }

            // Sort the ranges found
            ranges = ranges.stream().sorted((p1, p2) -> {
                if (p1.left().equals(p2.left())) {
                    return p1.right() - p2.right();
                } else {
                    return p1.left() - p2.left();
                }
            }).collect(Collectors.toList());


            // Now we try to merge the ranges one by one
            // All the ranges must merge into a single range
            // If we find that's not the case, we've got our answer
            // because that means that a particular value of x is not detectable by any of the beacons
            // Here, we're exploiting the fact there is only one answer
            Pair<Integer, Integer> range = ranges.get(0);
            boolean disjoint = false;
            int ansx = 0;
            for (int i = 1; i < ranges.size(); i++) {
                Pair<Integer, Integer> r = ranges.get(i);
                if ((r.right() - range.left() < -1)) {
                    ansx = r.right() + 1;
                    disjoint = true;
                    break;
                } else if ((r.left() - range.right() > 1)) {
                    ansx = r.left() - 1;
                    disjoint = true;
                    break;
                }
                range = Pair.of(Math.min(r.left(), range.left()), Math.max(r.right(), range.right()));
            }

            if (disjoint) {
                ans = Grid.Pos.of(ansx, y);
                break;
            }

        }


        System.out.println(ans);
        long a = (4000000L * ans.i()) + ans.j();
        System.out.println(a);
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
