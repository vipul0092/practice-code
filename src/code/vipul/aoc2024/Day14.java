package code.vipul.aoc2024;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2024/day/14
 */
public class Day14 {

    private static int XMAX = 101, YMAX = 103;
    private static final int[][] DIFFS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int THRESHOLD = 200;

    record Point(int px, int py, int vx, int vy){}

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day14.class, false);
        List<Point> points = parse(lines);

        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (Point p : points) {

            int px100 = p.px + p.vx*100, py100 = p.py + p.vy*100;
            px100 = px100 % XMAX; py100 = py100 % YMAX;
            if (px100 < 0) px100 += XMAX;
            if (py100 < 0) py100 += YMAX;

            if (px100 >= 0 && px100 < XMAX/2 && py100 >= 0 && py100 < YMAX/2) {
                q1++;
            } else if (px100 > XMAX/2 && py100 >= 0 && py100 > YMAX/2) {
                q2++;
            } else if (px100 >= 0 && px100 < XMAX/2 && py100 > YMAX/2) {
                q3++;
            } else if (px100 > XMAX/2 && py100 >= 0 && py100 < YMAX/2) {
                q4++;
            }
        }
        System.out.println("Part 1: " + (q1 * q2 * q3 * q4)); // 231782040


        int rounds = 0;
        record Pt(int x, int y){}
        char[][] ch;
        outer:
        while(true) {
            // System.out.println("Round#: " +  rounds);
            ch = new char[YMAX+1][XMAX+1];

            Set<Pt> pts = new HashSet<>();
            for (Point p : points) {
                int px100 = p.px + p.vx*rounds; int py100 = p.py + p.vy*rounds;
                px100 = px100 % XMAX; py100 = py100 % YMAX;
                if (px100 < 0) px100 += XMAX;
                if (py100 < 0) py100 += YMAX;

                ch[py100][px100] ='#';
                pts.add(new Pt(px100, py100));
            }

            while(!pts.isEmpty()) {
                Set<Pt> visited = bfs(pts.iterator().next(), n -> true,
                        cur -> Arrays.stream(DIFFS).map(d -> new Pt(cur.x + d[0], cur.y + d[1]))
                                .filter(pts::contains).toList());

                // If we are able to visit `THRESHOLD` number of points, that means the points are close to each other
                // which is most probably our answer
                if (visited.size() >= THRESHOLD) {
                    break outer;
                }
                pts.removeAll(visited);
            }
            rounds++;
        }

        System.out.println("Part 2: " + rounds); // 6475
        display(ch);
    }

    private static <T> Set<T> bfs(T start, Predicate<T> additionalNeighborCheck,
                                  Function<T, List<T>> neighborCreator) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new ArrayDeque<>();
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            T cp = queue.remove();
            for (T neighbor : neighborCreator.apply(cp)) {
                if (additionalNeighborCheck.test(neighbor) && !visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visited;
    }

    private static void display(char[][] ch) {
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < ch[i].length; j++) {
                System.out.print(ch[i][j] == '\0' ? '.' : ch[i][j]);
            }
            System.out.println();
        }
    }

    private static List<Point> parse(Collection<String> lines) {
        List<Point> pts = new ArrayList<>();

        for (String line : lines) {
            var parts = line.split(" ");
            var pp = parts[0].split("=")[1].split(",");
            var vp = parts[1].split("=")[1].split(",");

            int px = Integer.parseInt(pp[0]), py = Integer.parseInt(pp[1]);
            int vx = Integer.parseInt(vp[0]), vy = Integer.parseInt(vp[1]);
            pts.add(new Point(px, py, vx, vy));
        }
        return pts;
    }
}
