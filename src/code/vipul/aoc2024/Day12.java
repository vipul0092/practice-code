package code.vipul.aoc2024;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static code.vipul.aoc2024.inputs.Inputs.DAY_12;

/**
 * https://adventofcode.com/2024/day/12
 */
public class Day12 {

    private static String INPUT = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """;

    record Point(int i, int j){}
    enum EdgeDirection {LEFT_VERTICAL, RIGHT_VERTICAL, ABOVE_HORIZONTAL, BELOW_HORIZONTAL }
    record PointOnEdge(Point p, EdgeDirection dir){}

    private static final int[][] DIFFS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void solve() {
        INPUT = DAY_12;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Set<Point> allVisited = new HashSet<>();
        int total = 0;
        int total2 = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                Point pt = new Point(i, j);
                if (allVisited.contains(pt)) continue;
                char ch = get(lines,pt);

                // Start from current and visit each point with same char
                Set<Point> visited = bfs(pt,
                        neighbor -> get(lines, neighbor) == ch,
                        cur -> Arrays.stream(DIFFS).map(d -> new Point(cur.i + d[0], cur.j + d[1])).toList());
                int area = visited.size();

                // Check around each visited point to find all boundary points
                // Note that corners won't be found this way in the boundary
                int perimeter = 0;
                Set<Point> boundary = new HashSet<>();
                for (Point p : visited) {
                    for (int[] d : DIFFS) {
                        Point n = new Point(p.i + d[0], p.j + d[1]);
                        if (get(lines, n) != ch) {
                            perimeter++;
                            boundary.add(n);
                        }
                    }
                }

                // Create set of all boundary points as being present on an edge
                // This ties a boundary point to edge(s)
                Set<PointOnEdge> allEdgePoints = new HashSet<>();
                for (Point point : boundary) {
                    for (int[] d : DIFFS) {
                        Point n = new Point(point.i + d[0], point.j + d[1]);
                        if (visited.contains(n)) {
                            if (d[0] == 0) { // `i` is same for both, then the edge is vertical
                                allEdgePoints.add(new PointOnEdge(point,
                                        d[1] == -1 ? EdgeDirection.RIGHT_VERTICAL : EdgeDirection.LEFT_VERTICAL));
                            }
                            if (d[1] == 0) { // Otherwise its horizontal
                                allEdgePoints.add(new PointOnEdge(point,
                                        d[0] == -1 ? EdgeDirection.BELOW_HORIZONTAL: EdgeDirection.ABOVE_HORIZONTAL));
                            }
                        }
                    }
                }

                // Do a BFS on edge points found above to eliminate edges one by one
                int edges = 0;
                while(!allEdgePoints.isEmpty()) {
                    PointOnEdge ptOnEdge = allEdgePoints.iterator().next();
                    Set<PointOnEdge> visitedPts = bfs(ptOnEdge,
                            neighbor -> allEdgePoints.contains(neighbor),
                            cur -> Arrays.stream(DIFFS)
                                    .map(d -> new PointOnEdge(new Point(cur.p.i + d[0], cur.p.j + d[1]), cur.dir))
                                    .toList());

                    allEdgePoints.removeAll(visitedPts);
                    edges++;
                }

                allVisited.addAll(visited);
                total += (area * perimeter);
                total2 += (area * edges);
            }
        }

        System.out.println("Part 1: " + total); // 1381056
        System.out.println("Part 2: " + total2); // 834828
    }

    private static <T> Set<T> bfs(T start, Predicate<T> additionalNeighborCheck,
                                  Function<T, List<T>> neighborCreator) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new ArrayDeque<>();
        visited.add(start);
        queue.add(start);

        while(!queue.isEmpty()) {
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

    private static char get(List<String> lines, Point p) {
        try {
            return lines.get(p.i).charAt(p.j);
        } catch (Exception e) {
            return '\0';
        }
    }
}
