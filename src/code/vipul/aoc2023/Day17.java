package code.vipul.aoc2023;

import code.vipul.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static code.vipul.aoc2023.inputs.Inputs.DAY_17;

/**
 * Created by vgaur created on 17/12/23
 */
public class Day17 {

    private static String INPUT = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
            """;

    record Point(int x, int y){}
    record Pwd(Point p, int dir){}
    private static final int INF = Integer.MAX_VALUE;

    private static int ROWS = -1, COLS = -1;

    static int[] diff = new int[]{1, 2, 3};
    static int[] diff2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // dir == 1 means hor, == 2 ver
    private static Map<Pwd, Map<Pwd, Integer>> adjacencyListWithWeights;
    private static Map<Pwd, Integer> distanceMap;
    public static void solve() {
        INPUT = DAY_17;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        ROWS = lines.size();
        COLS = lines.get(0).length();
        adjacencyListWithWeights = new LinkedHashMap<>();
        distanceMap = new LinkedHashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                // vertical
                Pwd hor = new Pwd(new Point(i, j), 2);
                distanceMap.put(hor, INF);
                adjacencyListWithWeights.put(hor, new LinkedHashMap<>());
                int distancep = 0;
                int distancen = 0;
                for (int d : diff) {
                    int jj = j + d;
                    if (valid(i, jj, lines)) {
                        distancep += valueAt(i, jj, lines);
                        adjacencyListWithWeights.get(hor).put(new Pwd(new Point(i, jj), 1), distancep);
                    }
                    jj = j - d;
                    if (valid(i, jj, lines)) {
                        distancen += valueAt(i, jj, lines);
                        adjacencyListWithWeights.get(hor).put(new Pwd(new Point(i, jj), 1), distancen);
                    }
                }

                // horizontal
                Pwd ver = new Pwd(new Point(i, j), 1);
                distanceMap.put(ver, INF);
                adjacencyListWithWeights.put(ver, new LinkedHashMap<>());
                distancep = 0; distancen = 0;
                for (int d : diff) {
                    int ii = i + d;
                    if (valid(ii, j, lines)) {
                        distancep += valueAt(ii, j, lines);
                        adjacencyListWithWeights.get(ver).put(new Pwd(new Point(ii, j), 2), distancep);
                    }
                    ii = i - d;
                    if (valid(ii, j, lines)) {
                        distancen += valueAt(ii, j, lines);
                        adjacencyListWithWeights.get(ver).put(new Pwd(new Point(ii, j), 2), distancen);
                    }
                }
            }
        }

        Map<Pwd, Integer> distanceCopy = new HashMap<>(distanceMap);
        Pwd start = new Pwd(new Point(0, 0), 1);
        distanceMap.put(start, 0);
        int min1 = dijkstra(start, new Point(ROWS-1, COLS-1));

        distanceMap = new HashMap<>(distanceCopy);
        start = new Pwd(new Point(0, 0), 2);
        distanceMap.put(start, 0);
        int min2 = dijkstra(start, new Point(ROWS-1, COLS-1));

        System.out.println(Math.min(min1, min2));
        System.out.println();
    }

    public static void solvePart2() {
        INPUT = DAY_17;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        ROWS = lines.size();
        COLS = lines.get(0).length();
        adjacencyListWithWeights = new LinkedHashMap<>();
        distanceMap = new LinkedHashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                // vertical
                Pwd hor = new Pwd(new Point(i, j), 2);
                distanceMap.put(hor, INF);
                adjacencyListWithWeights.put(hor, new LinkedHashMap<>());
                int distancep = 0;
                int distancen = 0;
                for (int d : diff2) {
                    int jj = j + d;
                    if (valid(i, jj, lines)) {
                        distancep += valueAt(i, jj, lines);
                        if (d > 3) {
                            adjacencyListWithWeights.get(hor).put(new Pwd(new Point(i, jj), 1), distancep);
                        }
                    }
                    jj = j - d;
                    if (valid(i, jj, lines)) {
                        distancen += valueAt(i, jj, lines);
                        if (d > 3) {
                            adjacencyListWithWeights.get(hor).put(new Pwd(new Point(i, jj), 1), distancen);
                        }
                    }
                }

                // horizontal
                Pwd ver = new Pwd(new Point(i, j), 1);
                distanceMap.put(ver, INF);
                adjacencyListWithWeights.put(ver, new LinkedHashMap<>());
                distancep = 0; distancen = 0;
                for (int d : diff2) {
                    int ii = i + d;
                    if (valid(ii, j, lines)) {
                        distancep += valueAt(ii, j, lines);
                        if (d > 3) {
                            adjacencyListWithWeights.get(ver).put(new Pwd(new Point(ii, j), 2), distancep);
                        }
                    }
                    ii = i - d;
                    if (valid(ii, j, lines)) {
                        distancen += valueAt(ii, j, lines);
                        if (d > 3) {
                            adjacencyListWithWeights.get(ver).put(new Pwd(new Point(ii, j), 2), distancen);
                        }
                    }
                }
            }
        }

        Map<Pwd, Integer> distanceCopy = new HashMap<>(distanceMap);
        Pwd start = new Pwd(new Point(0, 0), 1);
        distanceMap.put(start, 0);
        int min1 = dijkstra(start, new Point(ROWS-1, COLS-1));

        distanceMap = new HashMap<>(distanceCopy);
        start = new Pwd(new Point(0, 0), 2);
        distanceMap.put(start, 0);
        int min2 = dijkstra(start, new Point(ROWS-1, COLS-1));

        System.out.println(Math.min(min1, min2));
        System.out.println();
    }

    private static int dijkstra(Pwd start, Point end) {
        int length = -1;

        Pwd current = start;

        PriorityQueue<Pair<Integer, Pwd>> minheap = new PriorityQueue<>(Comparator.comparingInt(Pair::left));
        minheap.add(Pair.of(distanceMap.get(current), start));
        boolean[][][] visited = new boolean[ROWS+1][COLS+1][3];

        while (!minheap.isEmpty()) {
            Pair<Integer, Pwd> top = minheap.remove();
            current = top.right();
            visited[current.p.x()][current.p.y()][current.dir] = true;
            int currentDistance = top.left();

            if (current.p.equals(end)) {
                length = currentDistance;
                break;
            }

            Map<Pwd, Integer> neighboursWithLengths = adjacencyListWithWeights.get(current);

            for (Map.Entry<Pwd, Integer> e : neighboursWithLengths.entrySet()) {
                Point p = e.getKey().p;
                int dir = e.getKey().dir;
                if (visited[p.x][p.y][dir]) {
                    continue;
                }
                int neighbourNewDistance = currentDistance + e.getValue();
                int neighbourCurrentDistance = distanceMap.get(e.getKey());

                // assign the distance to the neighbour
                // and update the distance in unvisited map
                if (neighbourNewDistance < neighbourCurrentDistance) {
                    distanceMap.put(e.getKey(), neighbourNewDistance);
                    minheap.add(Pair.of(neighbourNewDistance, e.getKey()));
                }
            }
        }
        return length;
    }

    private static boolean valid(int i, int j, List<String> lines) {
        return i >= 0 && j >= 0 && i < lines.size() && j < lines.get(i).length();
    }

    private static int valueAt(int i, int j, List<String> lines) {
        return lines.get(i).charAt(j) - '0';
    }
}
