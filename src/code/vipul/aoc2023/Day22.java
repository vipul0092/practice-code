package code.vipul.aoc2023;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static code.vipul.aoc2023.inputs.Inputs.DAY_22;

/**
 * Created by vgaur created on 22/12/23
 */
public class Day22 {

    private static String INPUT = """
            1,0,1~1,2,1
            0,0,2~2,0,2
            0,2,3~2,2,3
            0,0,4~0,2,4
            2,0,5~2,2,5
            0,1,6~2,1,6
            1,1,8~1,1,9
            """;

    public static void solve() {
        INPUT = DAY_22;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        int[][][] space = new int[501][11][11];
        List<Cube> cubes = new ArrayList<>();
        for (String line : lines) {
            var parts = line.split("~");
            var xyz = parts[0].split(",");
            Point p1 = new Point(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));

            xyz = parts[1].split(",");
            Point p2 = new Point(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
            cubes.add(new Cube(p1, p2));
        }

        cubes.sort((c1, c2) -> {
            if (c1.p1.z == c1.p2.z && c2.p1.z == c2.p2.z) {
                return Integer.compare(c1.p1.z, c2.p2.z);
            } else {
                int z1 = Math.min(c1.p1.z, c1.p2.z);
                int z2 = Math.min(c2.p1.z, c2.p2.z);
                return Integer.compare(z1, z2);
            }
        });

        Cube[] arr = cubes.toArray(new Cube[0]);
        for (int i = 0; i < arr.length; i++) {
            Cube cube = arr[i];
            int z1 = getFirstBlockingZ(cube, space), z2;
            if (cube.vertical()) {
                z2 = z1 + (cube.p2.z - cube.p1.z);
            } else {
                z2 = z1;
            }
            updateSpace(i, z1, z2, cube.p1.x, cube.p2.x, cube.p1.y, cube.p2.y, space);
        }

        Map<Integer, Set<Integer>> supports = new HashMap<>(); // key supports set of values
        Map<Integer, Set<Integer>> supportedBy = new HashMap<>(); // key is supported by set of values
        for (int z = 1; z < 500; z++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    // There is a brick here
                    // check if something is above it and its different
                    if (space[z][x][y] != 0 && space[z + 1][x][y] != 0 && space[z + 1][x][y] != space[z][x][y]) {
                        // this means space[z][x][y] supports space[z+1][x][y]
                        supportedBy.putIfAbsent(space[z + 1][x][y], new HashSet<>());
                        supportedBy.get(space[z + 1][x][y]).add(space[z][x][y]);

                        supports.putIfAbsent(space[z][x][y], new HashSet<>());
                        supports.get(space[z][x][y]).add(space[z + 1][x][y]);
                    }
                }
            }
        }

        Set<Integer> singleSupporters = new HashSet<>();
        for (var entry : supportedBy.entrySet()) {
            if (entry.getValue().size() == 1) { // key is just supported by a single value
                int val = entry.getValue().iterator().next();
                singleSupporters.add(val);
            }
        }

        int answer = cubes.size() - singleSupporters.size();
        System.out.println("Part 1: " + answer); // 398

        // Part 2 begins
        int total = 0;
        for (int brick : singleSupporters) {
            Set<Integer> fallen = new HashSet<>();
            fallen.add(brick);
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(brick);

            while (!queue.isEmpty()) {
                Queue<Integer> newq = new ArrayDeque<>();
                while (!queue.isEmpty()) {
                    int current = queue.remove();
                    for (int supportedByCurrent : supports.getOrDefault(current, Set.of())) {
                        boolean haveAllFallen =
                                fallen.containsAll(supportedBy.getOrDefault(supportedByCurrent, Set.of()));
                        if (haveAllFallen && !fallen.contains(supportedByCurrent)) {
                            fallen.add(supportedByCurrent);
                            newq.add(supportedByCurrent);
                        }
                    }
                }
                queue = newq;
            }
            total += (fallen.size() - 1); // -1 because one count is for the originally removed brick itself
        }

        System.out.println("Part 2: " + total); // 70727
    }

    private static int getFirstBlockingZ(Cube cube, int[][][] space) {
        int z = cube.p1.z;
        while (z >= 1) {
            for (int x = cube.p1.x; x <= cube.p2.x; x++) {
                for (int y = cube.p1.y; y <= cube.p2.y; y++) {
                    if (space[z][x][y] != 0) {
                        return ++z; // Move one above because we found blocking at value `z`
                    }
                }
            }
            z--;
        }
        return ++z;
    }

    private static void updateSpace(int i, int z1, int z2, int x1, int x2, int y1, int y2, int[][][] space) {
        for (int z = z1; z <= z2; z++) {
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    space[z][x][y] = i + 1;
                }
            }
        }
    }

    record Point(int x, int y, int z) {
    }

    record Cube(Point p1, Point p2) {
        public boolean vertical() {
            return p1.z != p2.z;
        }
    }
}
