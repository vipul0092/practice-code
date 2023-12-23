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

import static code.vipul.aoc2023.inputs.Inputs.DAY_23;

/**
 * Created by vgaur created on 23/12/23
 */
public class Day23 {

    private static String INPUT = """
            #.#####################
            #.......#########...###
            #######.#########.#.###
            ###.....#.>.>.###.#.###
            ###v#####.#v#.###.#.###
            ###.>...#.#.#.....#...#
            ###v###.#.#.#########.#
            ###...#.#.#.......#...#
            #####.#.#.#######.#.###
            #.....#.#.#.......#...#
            #.#####.#.#.#########v#
            #.#...#...#...###...>.#
            #.#.#v#######v###.###v#
            #...#.>.#...>.>.#.###.#
            #####v#.#.###v#.#.###.#
            #.....#...#...#.#.#...#
            #.#########.###.#.#.###
            #...###...#...#...#.###
            ###.###.#.###v#####v###
            #...#...#.#.>.>.#.>.###
            #.###.###.#.###.#.#v###
            #.....###...###...#...#
            #####################.#
            """;

    record Point(int x, int y){}

    private static final int[][] DIFF = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int[][][][] dp;

    public static void solve() {
        INPUT = DAY_23;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        long s = System.currentTimeMillis();
        int rows = lines.size();
        int cols = lines.get(0).length();
        dp = new int[rows+1][cols+1][rows+1][cols+1];

        int ans = dfs(0, 1, 0, 0, lines.size()-1, lines.get(0).length()-2, lines);
        long e = System.currentTimeMillis();
        System.out.println(ans);
        System.out.println("time taken: " + (e - s) + " ms");
    }

    public static void solvePart2() {
        INPUT = DAY_23;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        long s = System.currentTimeMillis();
        int rows = lines.size();
        int cols = lines.get(0).length();

        Map<Point, Integer> pointsToEvaluate = new HashMap<>();
        Point start = new Point(0, 1);
        Point end = new Point(lines.size()-1, lines.get(0).length()-2);
        int id = 1;
        pointsToEvaluate.put(start, id++);
        pointsToEvaluate.put(end, id++);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (value(i, j, lines) != '#') {
                    int options = 0;
                    for (int[] df : DIFF) {
                        int ni = i + df[0], nj = j + df[1];
                        if (valid(ni, nj, lines)) {
                            options++;
                        }
                    }
                    if (options > 2) {
                        pointsToEvaluate.put(new Point(i, j), id++);
                    }
                }
            }
        }
        int[][] graph = new int[id+1][id+1];

        for (var entry : pointsToEvaluate.entrySet()) {
            Point pt = entry.getKey();
            int ptid = entry.getValue();
            Queue<Point> queue = new ArrayDeque<>();
            Queue<Integer> len = new ArrayDeque<>();
            Set<Point> visited = new HashSet<>();
            queue.add(pt);
            len.add(1);
            visited.add(pt);

            while (!queue.isEmpty()) {
                Point p = queue.remove();
                int distance = len.remove();
                List<Point> options = new ArrayList<>();
                for (int[] df : DIFF) {
                    int ni = p.x + df[0], nj = p.y + df[1];
                    Point n = new Point(ni, nj);
                    if (valid(ni, nj, lines) && !visited.contains(n)) {
                        options.add(n);
                    }
                }
                for (Point option : options) {
                    if (pointsToEvaluate.containsKey(option)) {
                        int opid = pointsToEvaluate.get(option);
                        graph[ptid][opid] = distance;
                        graph[opid][ptid] = distance;
                    } else {
                        queue.add(option);
                        len.add(distance+1);
                    }
                    visited.add(option);
                }
            }
        }

        int answer = dfs2(pointsToEvaluate.get(start), -1, pointsToEvaluate.get(end), graph, 0L);
        long e = System.currentTimeMillis();
        System.out.println(answer);
        System.out.println("time taken: " + (e - s) + " ms");
    }

    private static int dfs2(int current, int prev, int end, int[][] graph, long visited) {
        if (current == end) {
            return 0;
        }

        int max = -1;
        for (int j = 1; j < graph[current].length; j++) {
            if (graph[current][j] == 0) continue;
            int dis = graph[current][j];
            if (j != prev && !isBitSet(visited, j)) {
                int d = dfs2(j, current, end, graph, setBit(visited, j));
                if (d != -1) {
                    max = Math.max(max, d + dis);
                }
            }
        }
        return max;
    }

    private static int dfs(int ci, int cj, int pi, int pj, int ei, int ej, List<String> lines) {
        if (ci == ei && cj == ej) {
            return 0;
        }
        if (dp[ci][cj][pi][pj] != 0) {
            return dp[ci][cj][pi][pj] - 2;
        }
        int max = -1;
        for (int[] df : DIFF) {
            int ni = ci + df[0], nj = cj + df[1];
            if (!(ni == pi && nj == pj) && valid(ni, nj, lines)) {
                char next = value(ni, nj, lines);
                if (next != '.') {
                    if (next == '>') {
                        nj++;
                    } else if (next == '<') {
                        nj--;
                    } else if (next == '^') {
                        ni--;
                    } else { //v
                        ni++;
                    }
                }
                if (ni == pi && nj == pj) {
                    continue;
                }
                int len = dfs(ni, nj, ci, cj, ei, ej, lines);
                if (len != -1) {
                    max = Math.max(max, len + 1);
                }
            }
        }
        dp[ci][cj][pi][pj] = max + 2;
        return max;
    }

    private static char value(int x, int y, List<String> lines) {
        return lines.get(x).charAt(y);
    }

    private static boolean valid(int x, int y, List<String> lines) {
        return x >= 0 && y >= 0 && x < lines.size() && y < lines.get(0).length()
                && value(x, y, lines) != '#';
    }

    private static boolean isBitSet(long bitSet, int bitPos) {
        return (bitSet & (1L << bitPos)) != 0;
    }

    private static long setBit(long bitSet, int bitPos) {
        return (bitSet | (1L << bitPos));
    }
}
