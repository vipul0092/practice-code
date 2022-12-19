package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 19/12/22
 */
public class Solve18 {

    private static final String INPUT = "2,2,2\n" +
            "1,2,2\n" +
            "3,2,2\n" +
            "2,1,2\n" +
            "2,3,2\n" +
            "2,2,1\n" +
            "2,2,3\n" +
            "2,2,4\n" +
            "2,2,6\n" +
            "1,2,5\n" +
            "3,2,5\n" +
            "2,1,5\n" +
            "2,3,5";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_18.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        Set<Cube> cubes = new HashSet<>();

        for (String in : inputs) {
            String[] parts = in.split(",");
            cubes.add(Cube.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        int count = 0;
        for (Cube cube : cubes) {
            if (!cubes.contains(Cube.of(cube.x, cube.y, cube.z + 1))) {
                count++;
            }
            if (!cubes.contains(Cube.of(cube.x, cube.y, cube.z - 1))) {
                count++;
            }
            if (!cubes.contains(Cube.of(cube.x + 1, cube.y, cube.z))) {
                count++;
            }
            if (!cubes.contains(Cube.of(cube.x - 1, cube.y, cube.z))) {
                count++;
            }
            if (!cubes.contains(Cube.of(cube.x, cube.y + 1, cube.z))) {
                count++;
            }
            if (!cubes.contains(Cube.of(cube.x, cube.y - 1, cube.z))) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_18.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        Set<Cube> cubes = new HashSet<>();

        int minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE;
        int miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
        int minz = Integer.MAX_VALUE, maxz = Integer.MIN_VALUE;

        for (String in : inputs) {
            String[] parts = in.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int z = Integer.parseInt(parts[2]);
            cubes.add(Cube.of(x, y, z));

            minx = Math.min(minx, x);
            maxx = Math.max(maxx, x);
            miny = Math.min(miny, y);
            maxy = Math.max(maxy, y);
            minz = Math.min(minz, z);
            maxz = Math.max(maxz, z);
        }

        minx--; maxx++;
        miny--; maxy++;
        minz--; maxz++;

        Cube start = Cube.of(minx, miny, minz);
        Set<Cube> visited = new HashSet<>();
        Queue<Cube> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            Cube cube = queue.remove();
            Cube neighbor = Cube.of(cube.x, cube.y, cube.z + 1);
            if (!cubes.contains(neighbor)
                    && !visited.contains(neighbor)
                    && neighbor.x >= minx && neighbor.x <= maxx
                    && neighbor.y >= miny && neighbor.y <= maxy
                    && neighbor.z >= minz && neighbor.z <= maxz) {
                visited.add(neighbor);
                queue.add(neighbor);
            }

            neighbor = Cube.of(cube.x, cube.y, cube.z - 1);
            if (!cubes.contains(neighbor)
                    && !visited.contains(neighbor)
                    && neighbor.x >= minx && neighbor.x <= maxx
                    && neighbor.y >= miny && neighbor.y <= maxy
                    && neighbor.z >= minz && neighbor.z <= maxz) {
                visited.add(neighbor);
                queue.add(neighbor);
            }

            neighbor = Cube.of(cube.x + 1, cube.y, cube.z);
            if (!cubes.contains(neighbor)
                    && !visited.contains(neighbor)
                    && neighbor.x >= minx && neighbor.x <= maxx
                    && neighbor.y >= miny && neighbor.y <= maxy
                    && neighbor.z >= minz && neighbor.z <= maxz) {
                visited.add(neighbor);
                queue.add(neighbor);
            }

            neighbor = Cube.of(cube.x - 1, cube.y, cube.z);
            if (!cubes.contains(neighbor)
                    && !visited.contains(neighbor)
                    && neighbor.x >= minx && neighbor.x <= maxx
                    && neighbor.y >= miny && neighbor.y <= maxy
                    && neighbor.z >= minz && neighbor.z <= maxz) {
                visited.add(neighbor);
                queue.add(neighbor);
            }

            neighbor = Cube.of(cube.x, cube.y + 1, cube.z);
            if (!cubes.contains(neighbor)
                    && !visited.contains(neighbor)
                    && neighbor.x >= minx && neighbor.x <= maxx
                    && neighbor.y >= miny && neighbor.y <= maxy
                    && neighbor.z >= minz && neighbor.z <= maxz) {
                visited.add(neighbor);
                queue.add(neighbor);
            }

            neighbor = Cube.of(cube.x, cube.y - 1, cube.z);
            if (!cubes.contains(neighbor)
                    && !visited.contains(neighbor)
                    && neighbor.x >= minx && neighbor.x <= maxx
                    && neighbor.y >= miny && neighbor.y <= maxy
                    && neighbor.z >= minz && neighbor.z <= maxz) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }

        int count = 0;

        for (Cube cube : cubes) {
            if (visited.contains(Cube.of(cube.x, cube.y, cube.z + 1))) {
                count++;
            }
            if (visited.contains(Cube.of(cube.x, cube.y, cube.z - 1))) {
                count++;
            }
            if (visited.contains(Cube.of(cube.x + 1, cube.y, cube.z))) {
                count++;
            }
            if (visited.contains(Cube.of(cube.x - 1, cube.y, cube.z))) {
                count++;
            }
            if (visited.contains(Cube.of(cube.x, cube.y + 1, cube.z))) {
                count++;
            }
            if (visited.contains(Cube.of(cube.x, cube.y - 1, cube.z))) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static class Cube {
        public final int x;
        public final int y;
        public final int z;
        private int hash = -1;

        private Cube(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static Cube of(int x, int y, int z) {
            return new Cube(x, y, z);
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(x);
                hash += (hash << 5) + Objects.hashCode(y);
                hash += (hash << 5) + Objects.hashCode(z);
            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof Cube && equalTo((Cube) another);
        }

        @Override
        public String toString() {
            return String.format("x: %s, y: %s, z: %s", x, y, z);
        }

        private boolean equalTo(Cube another) {
            return Objects.equals(x, another.x)
                    && Objects.equals(y, another.y)
                    && Objects.equals(z, another.z);
        }
    }
}
