package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.*;

/**
 * https://adventofcode.com/2024/day/18
 */
public class Day18 {

    private static final boolean SAMPLE = false;
    private static int LIMIT = 70;

    record Point(int i, int j) {
        boolean isValid() {
            return i >= 0 && j >= 0 && i <= LIMIT && j <= LIMIT;
        }
    }
    private static final int[][] DIFFS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void solve() {
        int initialFall = 1024;
        List<String> lines = AoCInputReader.read(Day18.class, SAMPLE);
        if (SAMPLE) {
            LIMIT = 6; initialFall = 12;
        }

        List<Point> points = new ArrayList<>();
        for (String line : lines) {
            var p = line.split(",");
            points.add(new Point(Integer.parseInt(p[0]), Integer.parseInt(p[1])));
        }
        Point start = new Point(0, 0);
        Point end = new Point(LIMIT, LIMIT);

        System.out.println("Part 1: " + bfs(start, end, points, initialFall)); // 374

        // Binary search to find the first point where the bfs fails
        int min = initialFall, max = lines.size();
        Point ans = null;
        while (min <= max) {
            int mid = (min + max) / 2;
            int answer = bfs(start, end, points, mid);
            if (answer == -1) {
                ans = points.get(mid - 1);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println("Part 2: " + ans.i + "," + ans.j); // 30,12
    }

    private static int bfs(Point start, Point end, List<Point> points, int fallenPoints) {
        Set<Point> walls = new HashSet<>();
        for (int i = 0; i < fallenPoints; i++) {
            walls.add(points.get(i));
        }

        Queue<Point> queue = new ArrayDeque<>();
        Queue<Integer> lenQueue = new ArrayDeque<>();
        Set<Point> visited = new HashSet<>();
        queue.add(start); visited.add(start); lenQueue.add(0);

        int answer = -1;
        while (!queue.isEmpty()) {
            int curlen = lenQueue.remove();
            Point current = queue.remove();

            if (current.equals(end)) {
                answer = curlen;
                break;
            }

            for (int[] d : DIFFS) {
                Point tmp = new Point(current.i + d[0], current.j + d[1]);
                if (tmp.isValid() && !visited.contains(tmp) && !walls.contains(tmp)) {
                    queue.add(tmp);
                    visited.add(tmp);
                    lenQueue.add(curlen + 1);
                }
            }
        }
        return answer;
    }
}
