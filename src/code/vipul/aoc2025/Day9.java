package code.vipul.aoc2025;

import code.vipul.utils.AoCInputReader;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://adventofcode.com/2025/day/9
 */
public class Day9 {

    record Point(long x, long y) {}
    private static final Set<Point> boundary = new LinkedHashSet<>();

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day9.class, false);

        Point[] points = new Point[lines.size()];
        int idx = 0;
        for (String line : lines) {
            String[] parts = line.split(",");
            points[idx++] = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }

        for (int i = 0; i < points.length; i++) {
            Point p1 = points[i], p2 = points[(i + 1) % points.length];
            int dx = Long.compare(p2.x, p1.x);
            int dy = Long.compare(p2.y, p1.y);
            Point cur = p1;
            boundary.add(cur);
            while (!cur.equals(p2)) {
                cur = new Point(cur.x + dx, cur.y + dy);
                boundary.add(cur);
            }
        }

        long largest = 0, largest2 = 0;
        for (int i = 0; i < points.length; i++) {
            Point p1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point p2 = points[j];
                long area = (Math.abs(p2.x - p1.x) + 1) * (Math.abs(p2.y - p1.y) + 1);
                largest = Math.max(largest, area);

                Point p3 = new Point(p1.x, p2.y);
                Point p4 = new Point(p2.x, p1.y);

                // To check if the area is valid, we need to verify that
                // all 4 points of the rectangle are either on or inside the polygon
                // AND
                // no edges of the rectangle intersect with any edges of the polygon
                // https://www.xjavascript.com/blog/check-if-polygon-is-inside-a-polygon/
                if (area > largest2 && pointOnOrInPolygon(p3, points) && pointOnOrInPolygon(p4, points)) {

                    long x_min = Math.min(p1.x, p2.x);
                    long x_max = Math.max(p1.x, p2.x);
                    long y_min = Math.min(p1.y, p2.y);
                    long y_max = Math.max(p1.y, p2.y);
                    Point[] rectangle = new Point[]{
                            new Point(x_min, y_min), new Point(x_max, y_min),
                            new Point(x_max, y_max), new Point(x_min, y_max)};

                    outer:
                    for (int p = 0; p < points.length; p++) {
                        for (int q = 0; q < rectangle.length; q++) {
                            Point A = points[p], B = points[(p + 1) % points.length];
                            Point C = rectangle[q], D = rectangle[(q + 1) % rectangle.length];

                            if (doSegmentsIntersect(A, B, C, D)) {
                                area = 0;
                                break outer;
                            }
                        }
                    }
                    largest2 = Math.max(largest2, area);
                }
            }
        }

        System.out.println("Part 1: " + largest); // 4750297200
        System.out.println("Part 2: " + largest2); // 1578115935
    }

    private static boolean doSegmentsIntersect(Point A, Point B, Point C, Point D) {
        long o1 = orientation(A, B, C);
        long o2 = orientation(A, B, D);
        long o3 = orientation(C, D, A);
        long o4 = orientation(C, D, B);

        return (o1 != o2) && (o3 != o4)
                // This check makes sure that the edges actually intersect
                // and do not just touch, touching edges are valid according to the problem
                && C.x != A.x && C.y != A.y && D.x != B.x && D.y != B.y;
    }

    // Returns orientation of triplet (p, q, r)
    // 0 (collinear), 1 (clockwise), 2 (counterclockwise)
    private static int orientation(Point p, Point q, Point r) {
        long val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;
        if (val > 0) return 1;
        return 2;
    }

    // Pulled from Advent of Code 2023 Day 10 solution, with a small modification as per the current problem
    private static boolean pointOnOrInPolygon(Point point, Point[] polygon) {
        if (boundary.contains(point)) {
            return true;
        }
        int numVertices = polygon.length;
        double x = point.x, y = point.y;
        boolean inside = false;
        Point p1 = polygon[0];

        for (int i = 1; i <= numVertices; i++) {
            Point p2 = polygon[i % numVertices];
            if (y > Math.min(p1.y, p2.y)) {
                if (y <= Math.max(p1.y, p2.y)) {
                    if (x <= Math.max(p1.x, p2.x)) {
                        double xIntersection = ((y - p1.y) * (p2.x - p1.x)) / (double) (p2.y - p1.y) + p1.x;
                        if (p1.x == p2.x || x <= xIntersection) {
                            inside = !inside;
                        }
                    }
                }
            }
            p1 = p2;
        }
        return inside;
    }
}
