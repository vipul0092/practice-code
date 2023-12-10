package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static code.vipul.aoc2023.inputs.Inputs.DAY_10;

/**
 * Created by vgaur created on 10/12/23
 */
public class Day10 {

    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    private static final List<Integer> DIRECTIONS = List.of(UP, DOWN, LEFT, RIGHT);
    private static final Map<Character, Set<Integer>> CAN_GO = Map.of('|', Set.of(UP, DOWN),
            '-', Set.of(LEFT, RIGHT), 'F', Set.of(DOWN, RIGHT), '7', Set.of(DOWN, LEFT),
            'L', Set.of(RIGHT, UP), 'J', Set.of(LEFT, UP));
    private static final Map<Integer, Set<Character>> INITIAL_GO = Map.of(UP, Set.of('|', '7', 'F'),
            DOWN, Set.of('|', 'L', 'J'), LEFT, Set.of('-', 'F', 'L'), RIGHT, Set.of('-', '7', 'J'));
    private static String INPUT = """
            FF7FSF7F7F7F7F7F---7
            L|LJ||||||||||||F--J
            FL-7LJLJ||||||LJL-77
            F--JF--7||LJLJIF7FJ-
            L---JF-JLJIIIIFJLJJ7
            |F|F-JF---7IIIL7L|7|
            |FFJF7L7F-JF7IIL---7
            7-L-JL7||F7|L7F-7F7|
            L.L7LFJ|||||FJL7||LJ
            L7JLJL-JLJLJL--JLJ.L
            """;

    public static void solve() {
        INPUT = DAY_10;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        Point sPos = null;
        List<Point> allPoints = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                Point pt = new Point(i, j);
                if (valueAt(pt, lines) == 'S') {
                    sPos = pt;
                }
                allPoints.add(pt);
            }
        }

        var pointsToStart = getStartingPoints(sPos, lines);

        Point prev = sPos;
        List<Point> vertices = new ArrayList<>();
        Set<Point> polygonPoints = new LinkedHashSet<>();
        Point current = pointsToStart.get(0).pt;
        int direction = pointsToStart.get(0).direction;
        polygonPoints.add(current);
        vertices.add(sPos);
        int totalDistance = 0;
        while (valueAt(current, lines) != 'S') {
            char curr = valueAt(current, lines);
            // System.out.println(curr + ", " + current);

            for (int nextDirection : CAN_GO.get(curr)) {
                Point moved = current.move(nextDirection);
                if (valid(moved, lines) && !moved.equals(prev)) {
                    polygonPoints.add(moved);
                    prev = current;
                    current = moved;
                    if (direction != nextDirection) { // Add a vertex when changing direction
                        direction = nextDirection;
                        vertices.add(moved);
                    }
                    totalDistance++;
                    break;
                }
            }
        }

        Point[] poly = vertices.toArray(new Point[0]);
        int insideCount = 0;
        for (Point pt : allPoints) {
            if (!polygonPoints.contains(pt) && pointInPolygon(pt, poly)) {
                insideCount++;
            }
        }

        int max = totalDistance % 2 == 0 ? totalDistance / 2 : (totalDistance / 2) + 1;
        System.out.println(max);
        System.out.println(insideCount);
    }

    // Copied from https://www.codingninjas.com/studio/library/check-if-a-point-lies-in-the-interior-of-a-polygon
    private static boolean pointInPolygon(Point point, Point[] polygon) {
        int numVertices = polygon.length;
        double x = point.x, y = point.y;
        boolean inside = false;
        Point p1 = polygon[0];

        for (int i = 1; i <= numVertices; i++) {
            Point p2 = polygon[i % numVertices];
            if (y > Math.min(p1.y, p2.y)) {
                if (y <= Math.max(p1.y, p2.y)) {
                    if (x <= Math.max(p1.x, p2.x)) {
                        double xIntersection = (y - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x;
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

    private static List<PointWithDirection> getStartingPoints(Point current, List<String> lines) {
        List<PointWithDirection> points = new ArrayList<>();
        for (int direction : DIRECTIONS) {
            Point moved = current.move(direction);
            if (valid(moved, lines) && INITIAL_GO.get(direction).contains(valueAt(moved, lines))) {
                points.add(new PointWithDirection(moved, direction));
            }
        }
        return points;
    }

    private static boolean valid(Point p, List<String> lines) {
        return !(p.x < 0 || p.y < 0 || p.x >= lines.size() || p.y >= lines.get(0).length())
                && lines.get(p.x).charAt(p.y) != '.';
    }

    private static char valueAt(Point p, List<String> lines) {
        return lines.get(p.x).charAt(p.y);
    }

    record Point(int x, int y) {
        public Point move(int direction) {
            return switch (direction) {
                case UP -> new Point(this.x - 1, this.y);
                case DOWN -> new Point(this.x + 1, this.y);
                case LEFT -> new Point(this.x, this.y - 1);
                case RIGHT -> new Point(this.x, this.y + 1);
                default -> throw new RuntimeException("Impossible!");
            };
        }
    }

    record PointWithDirection(Point pt, int direction) {
    }
}
