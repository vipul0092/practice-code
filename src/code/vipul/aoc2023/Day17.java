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

    private static final int VERTICAL = 1, HORIZONTAL = 0;

    private static Map<Pwd, Map<Pwd, Integer>> adjacencyListWithWeights;
    private static Map<Pwd, Integer> distanceMap;
    public static void solve() {
        INPUT = DAY_17;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        populateGraph(lines, 0, 3);
        Map<Pwd, Integer> distanceCopy = new HashMap<>(distanceMap);
        int min1 = dijkstra(new Pwd(new Point(0, 0), HORIZONTAL), new Point(ROWS-1, COLS-1));
        distanceMap = new HashMap<>(distanceCopy);
        int min2 = dijkstra(new Pwd(new Point(0, 0), VERTICAL), new Point(ROWS-1, COLS-1));
        System.out.println("Part 1: " + Math.min(min1, min2)); // 638

        // -------- Part 2 Begins --------
        populateGraph(lines, 3, 10);
        distanceCopy = new HashMap<>(distanceMap);
        min1 = dijkstra(new Pwd(new Point(0, 0), HORIZONTAL), new Point(ROWS-1, COLS-1));
        distanceMap = new HashMap<>(distanceCopy);
        min2 = dijkstra(new Pwd(new Point(0, 0), VERTICAL), new Point(ROWS-1, COLS-1));
        System.out.println("Part 2: " + Math.min(min1, min2)); // 748
    }

    private static int dijkstra(Pwd start, Point end) {
        int length = -1;
        distanceMap.put(start, 0);
        Pwd current = start;

        PriorityQueue<Pair<Integer, Pwd>> minheap = new PriorityQueue<>(Comparator.comparingInt(Pair::left));
        minheap.add(Pair.of(distanceMap.get(current), start));
        boolean[][][] visited = new boolean[ROWS+1][COLS+1][2];

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

    private static void populateGraph(List<String> lines, int mindis, int maxdis) {
        ROWS = lines.size();
        COLS = lines.get(0).length();
        adjacencyListWithWeights = new LinkedHashMap<>();
        distanceMap = new LinkedHashMap<>();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // vertical has horizontal neighbors
                Pwd ver = new Pwd(new Point(i, j), VERTICAL);
                distanceMap.put(ver, INF);
                adjacencyListWithWeights.put(ver, new LinkedHashMap<>());
                int distancep = 0;
                int distancen = 0;
                for (int d = 1; d <= maxdis; d++) {
                    int jj = j + d;
                    if (valid(i, jj)) {
                        distancep += valueAt(i, jj, lines);
                        if (d > mindis) {
                            adjacencyListWithWeights.get(ver).put(new Pwd(new Point(i, jj), HORIZONTAL), distancep);
                        }
                    }
                    jj = j - d;
                    if (valid(i, jj)) {
                        distancen += valueAt(i, jj, lines);
                        if (d > mindis) {
                            adjacencyListWithWeights.get(ver).put(new Pwd(new Point(i, jj), HORIZONTAL), distancen);
                        }
                    }
                }

                // horizontal has vertical neighbors
                Pwd hor = new Pwd(new Point(i, j), HORIZONTAL);
                distanceMap.put(hor, INF);
                adjacencyListWithWeights.put(hor, new LinkedHashMap<>());
                distancep = 0; distancen = 0;
                for (int d = 1; d <= maxdis; d++) {
                    int ii = i + d;
                    if (valid(ii, j)) {
                        distancep += valueAt(ii, j, lines);
                        if (d > mindis) {
                            adjacencyListWithWeights.get(hor).put(new Pwd(new Point(ii, j), VERTICAL), distancep);
                        }
                    }
                    ii = i - d;
                    if (valid(ii, j)) {
                        distancen += valueAt(ii, j, lines);
                        if (d > mindis) {
                            adjacencyListWithWeights.get(hor).put(new Pwd(new Point(ii, j), VERTICAL), distancen);
                        }
                    }
                }
            }
        }
    }

    private static boolean valid(int i, int j) {
        return i >= 0 && j >= 0 && i < ROWS && j < COLS;
    }

    private static int valueAt(int i, int j, List<String> lines) {
        return lines.get(i).charAt(j) - '0';
    }
}
