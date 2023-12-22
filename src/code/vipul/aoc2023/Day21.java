package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static code.vipul.aoc2023.inputs.Inputs.DAY_21;

/**
 * Created by vgaur created on 21/12/23
 */
public class Day21 {

    private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    private static final List<Integer> DIRECTIONS = List.of(UP, DOWN, LEFT, RIGHT);
    private static String INPUT = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
            """;

    public static void solveQuadratic() {

        INPUT = DAY_21;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        Map<Point, List<Point>> neighbors = new HashMap<>();
        Map<Point, List<Pwd>> loopedNeighbors = new HashMap<>();
        Point start = null;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (valueAt(i, j, lines) == 'S' || valueAt(i, j, lines) == '.') {
                    Point p = new Point(i, j);
                    if (valueAt(i, j, lines) == 'S') {
                        start = p;
                    }
                    neighbors.put(p, new ArrayList<>());
                    loopedNeighbors.put(p, new ArrayList<>());

                    for (int dir : DIRECTIONS) {
                        Point pn = p.move(dir);
                        if (!valid(pn, lines)) { // lies outside the grid, so loop it back
                            Pwd looped = getLoopedPoint(pn, lines);
                            if (looped != null) { // no need to check validSign because looped points are always valid
                                loopedNeighbors.get(p).add(looped);
                            }
                        } else if (valid(pn, lines) && validSign(pn, lines)) {
                            neighbors.get(p).add(pn);
                        }
                    }
                }
            }
        }

        System.out.println("Part 1: " + getStepPoints(64, start, neighbors, loopedNeighbors)); // 3605


        // Explanation for Quadratic solution
        // The no. of steps to calculate in part 2 is 26501365 = (202300*131) + 65
        // If the no. of points after 131*X + 65 steps is f(X), then f(X) is quadratic
        // This is hinted via the example as well
        // see https://old.reddit.com/r/adventofcode/comments/18nevo3/2023_day_21_solutions/keaiiq7/ as to why
        //
        // To calc final answer, we can put X = 202300
        // now f(X) = aX^2 + bX + c, f(X) gives number of pts after 65, 65 + 131, 65 + 131*2 steps and so on
        // To find the quadratic, we have evaluate the f(X) at X=0,1,2
        // f(0) = pts after 65 steps, f(1) = ptr after 65 + 131 steps, f(2) = 65 + 131*2 steps
        // Using these 3 points, we can calculate a, b & c in the quadratic
        // and hence calculate f(202300) which is the answer

        var f0 = getStepPoints(65, start, neighbors, loopedNeighbors);
        var f1 = getStepPoints(65 + 131, start, neighbors, loopedNeighbors);
        var f2 = getStepPoints(65 + (131 * 2), start, neighbors, loopedNeighbors);

        // f(X) = aX^2 + bX + c
        // f(0) = c; f(1) = a + b + c; f(2) = 4a + 2b + c
        // a = ((f(2) - c)/2) - (f(1) - c)
        // b = f(1) - c - a
        long c = f0;
        long a = ((f2 - c) / 2) - (f1 - c);
        long b = f1 - c - a;

        long X = 202300;
        long answer = (a * X * X) + (b * X) + c; // f(202300)

        System.out.println("Part 2: " + answer); //596734624269210
    }

    private static long getStepPoints(int totalSteps, Point start,
                                      Map<Point, List<Point>> neighbors, Map<Point, List<Pwd>> loopedNeighbors) {
        Map<Point, Set<Point>> pointsPerGrid = new HashMap<>();
        Point grid = new Point(0, 0);
        ;
        Point gridPos = grid;
        // Always start from the base
        pointsPerGrid.put(gridPos, new HashSet<>());
        pointsPerGrid.get(gridPos).add(start);

        int steps = 0;
        do {
            Map<Point, Set<Point>> newGridPoints = new HashMap<>();
            for (var entry : pointsPerGrid.entrySet()) {
                gridPos = entry.getKey();
                newGridPoints.putIfAbsent(gridPos, new HashSet<>());
                Set<Point> gridPoints = entry.getValue();
                for (Point p : gridPoints) {
                    for (Point n : neighbors.get(p)) {
                        newGridPoints.get(gridPos).add(n);
                    }
                    for (Pwd n : loopedNeighbors.get(p)) {
                        Point newGrid = gridPos.move(n.dir);
                        newGridPoints.putIfAbsent(newGrid, new HashSet<>());
                        newGridPoints.get(newGrid).add(n.p);
                    }
                }
            }
            steps++;
            pointsPerGrid = newGridPoints;
        } while (steps != totalSteps);

        return pointsPerGrid.values().stream().mapToLong(i -> i.size()).sum();
    }

    private static char valueAt(int i, int j, List<String> lines) {
        return lines.get(i).charAt(j);
    }

    private static boolean valid(Point p, List<String> lines) {
        return p != null && p.x >= 0 && p.y >= 0 && p.x < lines.size() && p.y < lines.get(0).length();
    }

    private static boolean validSign(Point p, List<String> lines) {
        return valueAt(p.x, p.y, lines) == '.' || valueAt(p.x, p.y, lines) == 'S';
    }

    private static Pwd getLoopedPoint(Point p, List<String> lines) {
        if (p.x < 0) {
            return new Pwd(new Point(lines.size() - 1, p.y), UP); // loop back down
        }
        if (p.y < 0) {
            return new Pwd(new Point(p.x, lines.get(0).length() - 1), LEFT); // loop back right
        }
        if (p.x >= lines.size()) {
            return new Pwd(new Point(0, p.y), DOWN); // loop back up
        }
        if (p.y >= lines.get(0).length()) {
            return new Pwd(new Point(p.x, 0), RIGHT); // loop back left
        }
        return null;
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

    record Pwd(Point p, int dir) {
    }
}
