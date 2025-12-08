package code.vipul.aoc2025;

import java.util.*;

import code.vipul.leetcode.graphs.DisjointSet;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2025/day/8
 */
public class Day8 {

    record Point(long x, long y, long z){}
    record Pwd(long dis, Point p1, Point p2) {}

    public static void solve() {
        // Uses Disjoint Set to find connected components
        solveDisjointSet(false);

        // Uses BFS to find connected components
        solveBfs(false);
    }

    private static void solveDisjointSet(boolean sample) {
        int checkIterations = sample ? 10 : 1000;
        List<String> lines = AoCInputReader.read(Day8.class, sample);

        long s = System.currentTimeMillis();
        Map<Point, Integer> ids = new HashMap<>();

        int id = 0;
        List<Point> points = new ArrayList<>();
        DisjointSet dsu = new DisjointSet(lines.size());
        for (String line: lines) {
            String[] parts = line.split(",");
            Point pt = new Point(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]));
            points.add(pt);

            // Create an id for each point and add it to the Disjoint Set-Union data structure
            ids.put(pt, id++);
            dsu.add(ids.get(pt));
        }

        PriorityQueue<Pwd> pairsWithDistance = new PriorityQueue<>(Comparator.comparingLong(p -> p.dis));
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point p2 = points.get(j);
                long dist = (p1.x - p2.x)*(p1.x - p2.x)  + (p1.y - p2.y)*(p1.y - p2.y) + (p1.z - p2.z)*(p1.z - p2.z);
                pairsWithDistance.add(new Pwd(dist, p1, p2));
            }
        }

        long ans1 = 0, ans2 = 0;
        int iterations = 0;
        while (!pairsWithDistance.isEmpty()) {
            Pwd pwd = pairsWithDistance.remove();
            Point p1 = pwd.p1;
            Point p2 = pwd.p2;
            // Join the two points in the set
            dsu.union(ids.get(p1), ids.get(p2));
            iterations++;

            // Part 1
            if (iterations == checkIterations) {
                Map<Integer, Integer> counts = new HashMap<>();
                for (Point p : points) {
                    int parent = dsu.find(ids.get(p));
                    counts.put(parent, counts.getOrDefault(parent, 0) + 1);
                }

                List<Integer> list = counts.values().stream().sorted(Comparator.reverseOrder()).toList();
                ans1 = (long)list.get(0) * list.get(1) * list.get(2);
            } else if (iterations > checkIterations) {
                // Check if all points are connected
                int parent = -1;
                for (Point p : points) {
                    if (parent == -1) {
                        parent = dsu.find(ids.get(p));
                    } else if (parent != dsu.find(ids.get(p))) {
                        parent = -1;
                        break;
                    }
                }

                if (parent != -1) {
                    ans2 = p1.x * p2.x;
                    break;
                }
            }
        }
        long e = System.currentTimeMillis();

        System.out.println("Part 1: " + ans1); // 75680
        System.out.println("Part 2: " + ans2); // 8995844880
        System.out.println("Time taken (DSU): " + (e - s) + " ms");
    }


    private static Map<Point, Set<Point>> graph;
    private static void solveBfs(boolean sample) {
        int checkIterations = sample ? 10 : 1000;
        List<String> lines = AoCInputReader.read(Day8.class, sample);

        long s = System.currentTimeMillis();
        graph = new HashMap<>();
        List<Point> points = new ArrayList<>();
        for (String line: lines) {
            String[] parts = line.split(",");
            Point pt = new Point(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]));
            points.add(pt);
            graph.put(pt, new HashSet<>());
        }

        PriorityQueue<Pwd> pairsWithDistance = new PriorityQueue<>(Comparator.comparingLong(p -> p.dis));
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point p2 = points.get(j);
                long dist = (p1.x - p2.x)*(p1.x - p2.x)  + (p1.y - p2.y)*(p1.y - p2.y) + (p1.z - p2.z)*(p1.z - p2.z);
                pairsWithDistance.add(new Pwd(dist, p1, p2));
            }
        }

        long ans1 = 0, ans2 = 0;
        int iterations = 0;
        while (!pairsWithDistance.isEmpty()) {
            Pwd pwd = pairsWithDistance.remove();
            Point p1 = pwd.p1;
            Point p2 = pwd.p2;
            graph.get(p1).add(p2); graph.get(p2).add(p1);
            iterations++;

            if (iterations == checkIterations) {
                List<Long> counts = bfs(points);
                counts.sort(Comparator.reverseOrder());
                var iterator = counts.iterator();
                ans1 = iterator.next() * iterator.next() * iterator.next();
            } else if (iterations > checkIterations) {
                List<Long> counts = bfs(points);
                if (counts.size() == 1) {
                    ans2 = p1.x * p2.x;
                    break;
                }
            }
        }
        long e = System.currentTimeMillis();

        System.out.println("Part 1: " + ans1); // 75680
        System.out.println("Part 2: " + ans2); // 8995844880
        System.out.println("Time taken (BFS): " + (e - s) + " ms");
    }

    private static List<Long> bfs(List<Point> points) {
        Set<Point> visited = new HashSet<>();
        List<Long> counts = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();

        for (Point start : points) {
            if (visited.contains(start)) continue;
            q.add(start);
            visited.add(start);
            long count = 0;

            while (!q.isEmpty()) {
                Point p = q.remove();
                count++;

                for (Point neighbor : graph.get(p)) {
                    if (visited.contains(neighbor)) continue;
                    visited.add(neighbor);
                    q.add(neighbor);
                }
            }
            counts.add(count);
        }
        return counts;
    }
}
