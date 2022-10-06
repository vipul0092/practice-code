package code.vipul.aoc2020;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static code.vipul.aoc2020.Inputs2.INPUT_17;

/**
 * https://adventofcode.com/2020/day/17
 */
public class Solve17 {

    private static final String INPUT = ".#.\n" +
            "..#\n" +
            "###";
    private static Map<CubePos, Integer> cubeStates;
    private static final int ACTIVE = 0;
    private static final int IN_ACTIVE = 1;

    public static void solve() {
        initialize(INPUT_17);
        int cycles = 6;
        while (cycles-- > 0) {
            executeCycle(false);
        }

        long answer = cubeStates.entrySet().stream()
                .filter(e -> e.getValue() == ACTIVE)
                .count();
        System.out.println("Answer: " + answer); //333
    }

    public static void solvePart2() {
        initialize(INPUT_17);
        int cycles = 6;
        while (cycles-- > 0) {
            executeCycle(true);
        }

        long answer = cubeStates.entrySet().stream()
                .filter(e -> e.getValue() == ACTIVE)
                .count();
        System.out.println("Answer: " + answer); //2676
    }

    private static void executeCycle(boolean considerW) {
        Map<CubePos, Integer> updatedCubes = new LinkedHashMap<>();
        Map<CubePos, Integer> newCubes = new LinkedHashMap<>();
        for (Map.Entry<CubePos, Integer> cube : cubeStates.entrySet()) {
            CubePos cubePos = cube.getKey();
            int cubeValue = cube.getValue();

            Set<CubePos> neighbors = getNeighbours(cubePos, considerW);

            // add new cubes to the tiles map
            neighbors.forEach(n -> {
                if (!cubeStates.containsKey(n)) {
                    newCubes.put(n, IN_ACTIVE);
                }
            });

            // get the neighboring active cubes count
            long activeCubesCount = neighbors.stream()
                    .map(p -> cubeStates.getOrDefault(p, IN_ACTIVE))
                    .filter(v -> v == ACTIVE).count();

            if (cubeValue == ACTIVE && !(activeCubesCount == 2 || activeCubesCount == 3)) {
                updatedCubes.put(cubePos, IN_ACTIVE);
            } else if (cubeValue == IN_ACTIVE && activeCubesCount == 3) {
                updatedCubes.put(cubePos, ACTIVE);
            }
        }

        for (Map.Entry<CubePos, Integer> cube : newCubes.entrySet()) {
            CubePos cubePos = cube.getKey();
            int cubeValue = cube.getValue();

            Set<CubePos> neighbors = getNeighbours(cubePos, considerW);

            // get the neighboring active cubes count
            long activeCubesCount = neighbors.stream()
                    .map(p -> cubeStates.getOrDefault(p, IN_ACTIVE))
                    .filter(v -> v == ACTIVE).count();

            if (cubeValue == IN_ACTIVE && activeCubesCount == 3) {
                updatedCubes.put(cubePos, ACTIVE);
            } else {
                updatedCubes.put(cubePos, IN_ACTIVE);
            }
        }
        cubeStates.putAll(updatedCubes);
    }

    private static Set<CubePos> getNeighbours(CubePos pos, boolean considerW) {
        Set<CubePos> neighbours = new HashSet<>();
        int[] waxis = considerW ? new int[]{pos.w, pos.w - 1, pos.w + 1} : new int[]{pos.w};
        int[] xaxis = new int[]{pos.x, pos.x - 1, pos.x + 1};
        int[] yaxis = new int[]{pos.y, pos.y - 1, pos.y + 1};
        int[] zaxis = new int[]{pos.z, pos.z - 1, pos.z + 1};

        for (int w : waxis) {
            for (int i = 0; i < 3; i++) {
                int x = xaxis[i];
                for (int j = 0; j < 3; j++) {
                    int y = yaxis[j];
                    for (int k = 0; k < 3; k++) {
                        int z = zaxis[k];
                        CubePos neighbor = CubePos.of(x, y, z, w);
                        if (!neighbor.equals(pos)) {
                            neighbours.add(neighbor);
                        }
                    }
                }
            }
        }
        return neighbours;
    }

    private static void initialize(String in) {
        String[] lines = in.split("\n");
        cubeStates = new LinkedHashMap<>();

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                CubePos pos = CubePos.of(i, j, 0);
                cubeStates.put(pos, lines[i].charAt(j) == '#' ? ACTIVE : IN_ACTIVE);
            }
        }
    }

    public static class CubePos {
        public final int x;
        public final int y;
        public final int z;
        public final int w;
        private int hash = -1;

        private CubePos(int x, int y, int z, int w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }

        public static CubePos of(int x, int y, int z) {
            return new CubePos(x, y, z, 0);
        }

        public static CubePos of(int x, int y, int z, int w) {
            return new CubePos(x, y, z, w);
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(x);
                hash += (hash << 5) + Objects.hashCode(y);
                hash += (hash << 5) + Objects.hashCode(z);
                hash += (hash << 5) + Objects.hashCode(w);
            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof CubePos && equalTo((CubePos) another);
        }

        @Override
        public String toString() {
            return String.format("x: %s, y: %s, z: %s, w: %s", x, y, z, w);
        }

        private boolean equalTo(CubePos another) {
            return Objects.equals(x, another.x)
                    && Objects.equals(y, another.y)
                    && Objects.equals(z, another.z)
                    && Objects.equals(w, another.w);
        }
    }
}
