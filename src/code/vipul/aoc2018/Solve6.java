package code.vipul.aoc2018;

import code.vipul.aoc2018.grid.Posxy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import static code.vipul.aoc2018.Inputs3.DAY_6;

/**
 * https://adventofcode.com/2018/day/6
 */
public class Solve6 {

    private static final String INPUT = "1, 1\n" +
            "1, 6\n" +
            "8, 3\n" +
            "3, 4\n" +
            "5, 5\n" +
            "8, 9";
    private static int REGION_DISTANCE_TOTAL = 10000;

    private static int minx;
    private static int miny;
    private static int maxx;
    private static int maxy;

    public static void solve() {
        // REGION_DISTANCE_TOTAL = 32;
        String[] lines = DAY_6.split("\n");
        int extra = 1;

        minx = Integer.MAX_VALUE;
        miny = Integer.MAX_VALUE;
        maxx = Integer.MIN_VALUE;
        maxy = Integer.MIN_VALUE;

        List<Posxy> points = new ArrayList<>();
        Map<Posxy, Integer> mapping = new LinkedHashMap<>();
        Map<Posxy, Integer> grid = new LinkedHashMap<>();

        int value = 1;
        for (String line : lines) {
            String[] ij = line.split(", ");
            Posxy pos = Posxy.of(Integer.parseInt(ij[0]), Integer.parseInt(ij[1]));
            points.add(pos);
            minx = Math.min(pos.x(), minx);
            miny = Math.min(pos.y(), miny);
            maxx = Math.max(pos.x(), maxx);
            maxy = Math.max(pos.y(), maxy);
            int v = value++;
            mapping.put(pos, v);
            grid.put(pos, v);
        }

        minx = minx - extra;
        miny = miny - extra;
        maxx = maxx + extra;
        maxy = maxy + extra;

        // print(grid);

        int regionPoints = 0;
        TreeMap<Integer, Set<Posxy>> distanceCounts;

        Map<Integer, Integer> counts = new HashMap<>();
        for (int x = minx; x <= maxx; x++) {
            for (int y = miny; y <= maxy; y++) {
                distanceCounts = new TreeMap<>();
                Posxy point = Posxy.of(x, y);

                TreeMap<Integer, Set<Posxy>> finalDistanceCounts = distanceCounts;
                AtomicInteger sum = new AtomicInteger();
                points.forEach(p -> {
                    int distance = manhattan(p, point);
                    sum.addAndGet(distance);
                    finalDistanceCounts.putIfAbsent(distance, new LinkedHashSet<>());
                    finalDistanceCounts.get(distance).add(p);
                });

                if (sum.intValue() < REGION_DISTANCE_TOTAL) {
                    regionPoints++;
                }

                int pMapping;
                if (finalDistanceCounts.firstEntry().getValue().size() > 1) {
                    pMapping = 0;
                } else {
                    pMapping = mapping.get(finalDistanceCounts.firstEntry().getValue().iterator().next());
                }
                grid.put(point, pMapping);
                if (pMapping != 0) {
                    counts.putIfAbsent(pMapping, 0);
                    counts.put(pMapping, counts.get(pMapping) + 1);
                }
            }
        }
        // print(grid);

        for (int x = minx; x <= maxx; x++) {
            int minmap = grid.get(Posxy.of(x, miny));
            int maxmap =  grid.get(Posxy.of(x, maxy));

            counts.remove(minmap);
            counts.remove(maxmap);
        }

        for (int y = miny; y <= maxy; y++) {
            int minmap = grid.get(Posxy.of(minx, y));
            int maxmap =  grid.get(Posxy.of(maxx, y));

            counts.remove(minmap);
            counts.remove(maxmap);
        }

        int answer = counts.values().stream().max(Comparator.naturalOrder()).orElseThrow();

        System.out.println("Answer: " + answer); //3449
        System.out.println("Answer Part 2: " + regionPoints); //44868
    }

    private static void print(Map<Posxy, Integer> grid) {
        System.out.println();

        for (int y = miny; y <= maxy; y++) {
            for (int x = minx; x <= maxx; x++) {
                Posxy point = Posxy.of(x, y);
                System.out.print(grid.getOrDefault(point, 0));
            }
            System.out.println();
        }

    }

    private static int manhattan(Posxy p1, Posxy p2) {
        return Math.abs(p1.x() - p2.x()) + Math.abs(p1.y() - p2.y());
    }
}
