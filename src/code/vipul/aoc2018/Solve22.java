package code.vipul.aoc2018;

import code.vipul.Pair;
import code.vipul.aoc2018.grid.Posxy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2018/day/22
 */
public class Solve22 {

    private static String INPUT = "depth: 8103\n" +
            "target: 9,758";
    private static final int INF = Integer.MAX_VALUE;

    private static final int ROCK = 0;
    private static final int WET = 1;
    private static final int NARROW = 2;

    private static final int NONE = 1;
    private static final int TORCH = 2;
    private static final int CLIMBING_GEAR = 4;

    private static final List<Integer> ALL_TOOLS = List.of(NONE, TORCH, CLIMBING_GEAR);

    private static final Map<Integer, Integer> ALLOWED_TOOLS_PER_REGION;

    static {
        ALLOWED_TOOLS_PER_REGION = new HashMap<>();
        ALLOWED_TOOLS_PER_REGION.put(ROCK, CLIMBING_GEAR | TORCH);
        ALLOWED_TOOLS_PER_REGION.put(WET, CLIMBING_GEAR | NONE);
        ALLOWED_TOOLS_PER_REGION.put(NARROW, TORCH | NONE);
    }

    private static Map<Posxy, Long> erosionMap;
    private static long depth;
    private static Posxy target;

    public static void solve() {
        erosionMap = new HashMap<>();
        String[] lines = INPUT.split("\n");
        depth = Long.parseLong(lines[0].split(": ")[1]);
        String[] targetcoor = lines[1].split(": ")[1].split(",");

        long maxx = Long.parseLong(targetcoor[0]);
        long maxy = Long.parseLong(targetcoor[1]);
        target = Posxy.of((int) maxx, (int) maxy);

        int answer = 0;
        for (int y = 0; y <= maxy; y++) {
            for (int x = 0; x <= maxx; x++) {
                Posxy point = Posxy.of(x, y);
                answer += getRegionType(point);
            }
        }

        System.out.println("Answer: " + answer); //7743
    }

    private static Map<PointWithTool, Map<PointWithTool, Integer>> adjacencyListWithWeights;
    private static Map<PointWithTool, Integer> distanceMap;
    private static long limitx, limity;


    // Approach inspired by https://www.reddit.com/r/adventofcode/comments/a8jhy0/2018_day_22_part2_did_you_have_to_cap_x_or_y/ecb5zjz/
    public static void solvePart2() {
        erosionMap = new HashMap<>();
        adjacencyListWithWeights = new LinkedHashMap<>();
        distanceMap = new LinkedHashMap<>();
        // INPUT = "depth: 510\ntarget: 10,10";
        String[] lines = INPUT.split("\n");
        depth = Long.parseLong(lines[0].split(": ")[1]);
        String[] targetcoor = lines[1].split(": ")[1].split(",");
        long targetX = Long.parseLong(targetcoor[0]);
        long targetY = Long.parseLong(targetcoor[1]);
        target = Posxy.of((int) targetX, (int) targetY);

        limitx = targetX + 100;
        limity = targetY + 100;

        Posxy start = Posxy.of(0, 0);

        // Populate the adjacency list for the graph
        for (int y = 0; y < limity; y++) {
            for (int x = 0; x < limitx; x++) {
                Posxy point = Posxy.of(x, y);
                int currentRegion = getRegionType(point);
                int toolsSum = ALLOWED_TOOLS_PER_REGION.get(currentRegion);
                Set<Posxy> neighbours = Set.of(point.moveDown(), point.moveUp(), point.moveRight(), point.moveLeft())
                        .stream().filter(p -> p.isValid()).collect(Collectors.toSet());

                List<Integer> tools = new ArrayList<>(2);
                ALL_TOOLS.forEach(tool -> {
                    if ((tool & toolsSum) != 0) {
                        tools.add(tool);
                    }
                });
                PointWithTool pt1 = PointWithTool.of(point, tools.get(0));
                PointWithTool pt2 = PointWithTool.of(point, tools.get(1));

                if (x > 0 || y > 0) {
                    distanceMap.put(pt1, INF);
                    distanceMap.put(pt2, INF);
                } else {
                    if (pt1.tool == TORCH) {
                        distanceMap.put(pt1, 0);
                        distanceMap.put(pt2, INF);
                    } else if (pt2.tool == TORCH) {
                        distanceMap.put(pt1, INF);
                        distanceMap.put(pt2, 0);
                    }
                }

                // Link the same point with different tools with an edge length of 7, because of switching cost
                link(pt1, pt2, 7);
                // Link the neighbours
                for (Posxy neighbour : neighbours) {
                    int nregion = getRegionType(neighbour);
                    int ntools = ALLOWED_TOOLS_PER_REGION.get(nregion);

                    // there is an edge between the neighbour and pt1
                    if ((pt1.tool & ntools) != 0) {
                        PointWithTool npt = PointWithTool.of(neighbour, pt1.tool);
                        link(npt, pt1, 1);
                        if (!neighbour.isOrigin()) {
                            distanceMap.put(npt, INF);
                        }
                    }
                    if ((pt2.tool & ntools) != 0) {
                        PointWithTool npt = PointWithTool.of(neighbour, pt2.tool);
                        link(npt, pt2, 1);
                        if (!neighbour.isOrigin()) {
                            distanceMap.put(npt, INF);
                        }
                    }
                }
            }
        }

        final long startTime = System.nanoTime();
        // Find min distance between the start and target using the weighted graph using Dijkstra's algorithm
        int length = dijkstra(PointWithTool.of(start, TORCH), PointWithTool.of(target, TORCH));
        final long duration = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Runtime(in ms): " + duration);
        System.out.println("Answer: " + length); //1029
    }

    private static int dijkstra(PointWithTool start, PointWithTool end) {
        int length = -1;

        PointWithTool current;
        PriorityQueue<Pair<Integer, PointWithTool>> minheap = new PriorityQueue<>(Comparator.comparingInt(Pair::left));
        minheap.add(Pair.of(distanceMap.get(start), start));

        boolean[][][] visited = new boolean[(int)limitx+1][(int)limity+1][10];

        while (!minheap.isEmpty()) {
            Pair<Integer, PointWithTool> top = minheap.remove();
            current = top.right();
            int currentTool = current.tool;
            Posxy currentLocation = current.location;
            int currentDistance = distanceMap.get(current);
            visited[current.location.x()][current.location.y()][current.tool] = true;

            if (currentLocation.equals(end.location) && currentTool == end.tool) {
                System.out.println("REACHED THE TARGET!");
                length = currentDistance;
                break;
            }

            Map<PointWithTool, Integer> neighboursWithLengths = adjacencyListWithWeights.get(current);

            for (Map.Entry<PointWithTool, Integer> e : neighboursWithLengths.entrySet()) {
                PointWithTool t = e.getKey();
                if (visited[t.location.x()][t.location.y()][t.tool]) {
                    continue;
                }
                int neighbourNewDistance = currentDistance + e.getValue();
                int neighbourCurrentDistance = distanceMap.get(t);

                // assign the distance to the neighbour
                // and update the distance in unvisited map
                if (neighbourNewDistance < neighbourCurrentDistance) {
                    distanceMap.put(t, neighbourNewDistance);
                    minheap.add(Pair.of(neighbourNewDistance, t));
                }

            }
        }
        return length;
    }

    private static void link(PointWithTool p1, PointWithTool p2, int edgeLength) {
        if (!adjacencyListWithWeights.containsKey(p1)) {
            adjacencyListWithWeights.put(p1, new LinkedHashMap<>());
        }

        if (!adjacencyListWithWeights.containsKey(p2)) {
            adjacencyListWithWeights.put(p2, new LinkedHashMap<>());
        }

        adjacencyListWithWeights.get(p1).put(p2, edgeLength);
        adjacencyListWithWeights.get(p2).put(p1, edgeLength);
    }

    private static int getRegionType(Posxy pos) {
        if (pos.equals(target)) return ROCK;
        return (int) (getErosionLevel(pos) % 3L);
    }

    private static long getErosionLevel(Posxy pos) {
        if (erosionMap.containsKey(pos)) {
            return erosionMap.get(pos);
        }
        int x = pos.x();
        int y = pos.y();

        long erosionLevel = depth;
        if ((x == 0 && y == 0) || (x == target.x() && y == target.y())) {
            erosionLevel = depth;
        } else if (y == 0) {
            erosionLevel += (x * 16807);
        } else if (x == 0) {
            erosionLevel += (y * 48271);
        } else {
            erosionLevel += (getErosionLevel(Posxy.of(x - 1, y)) * getErosionLevel(Posxy.of(x, y - 1)));
        }

        erosionLevel = erosionLevel % 20183;
        erosionMap.put(pos, erosionLevel);
        return erosionLevel;
    }

    private static class PointWithTool {
        private final Posxy location;
        private final int tool;
        private int hash = -1;

        PointWithTool(Posxy l, int t) {
            this.location = l;
            this.tool = t;
        }

        static PointWithTool of(Posxy l, int t) {
            return new PointWithTool(l, t);
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + location.hashCode();
                hash += (hash << 5) + Objects.hashCode(getTool(tool));
            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof PointWithTool && equalTo((PointWithTool) another);
        }

        @Override
        public String toString() {
            return String.format("location: %s, tool: %s", location.toString(), getTool(tool).name());
        }

        private boolean equalTo(PointWithTool another) {
            return another.location.equals(location) && another.tool == tool;
        }
    }

    private static ToolType getTool(int value) {
        if (value == TORCH) {
            return ToolType.TORCH;
        } else if (value == NONE) {
            return ToolType.NONE;
        } else {
            return ToolType.CLIMBING_GEAR;
        }
    }


    private enum ToolType {
        TORCH,
        CLIMBING_GEAR,
        NONE
    }
}
