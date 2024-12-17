package code.vipul.aoc2024;

import code.vipul.Pair;
import code.vipul.utils.AoCInputReader;

import java.util.*;

/**
 * https://adventofcode.com/2024/day/16
 */
public class Day16 {

    record Point(int i, int j) {

        Point move(Direction dir) {
            return switch (dir) {
                case UP -> new Point(i - 1, j);
                case DOWN -> new Point(i + 1, j);
                case LEFT -> new Point(i, j - 1);
                case RIGHT -> new Point(i, j + 1);
            };
        }
    }

    private static int ROWS = -1, COLS = -1;

    enum Direction {
        UP, DOWN, LEFT, RIGHT;

        Set<Direction> getValidDirections() {
            return switch (this) {
                case UP -> Set.of(Direction.UP, Direction.LEFT, Direction.RIGHT);
                case DOWN -> Set.of(Direction.DOWN, Direction.LEFT, Direction.RIGHT);
                case RIGHT -> Set.of(Direction.UP, Direction.DOWN, Direction.RIGHT);
                case LEFT -> Set.of(Direction.UP, Direction.DOWN, Direction.LEFT);
            };
        }

        boolean isRotated(Direction dir) {
            return switch (this) {
                case UP, DOWN -> dir == Direction.LEFT || dir == Direction.RIGHT;
                case RIGHT, LEFT -> dir == Direction.UP || dir == Direction.DOWN;
            };
        }

        int getIndex() {
            return switch (this) {
                case UP -> 0;
                case DOWN -> 1;
                case RIGHT -> 2;
                case LEFT -> 3;
            };
        }
    }

    private static final Set<Direction> ALL = Set.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
    record Pwd(Point p, Direction dir) {}

    private static Map<Pwd, Map<Pwd, Integer>> adjacencyListWithWeights; // this contains start -> blah -> end
    private static Map<Pwd, Integer> distanceMap;
    private static Map<Pwd, Set<Pwd>> previousLinks;
    private static Point start = null, end = null;

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day16.class, false);
        parse(lines);

        // Find the min score
        int minimumScore = dijkstra(new Pwd(start, Direction.RIGHT), end);
        System.out.println("Part 1: " + minimumScore); // 101492

        // Start from the end, and do a bfs following the previous links to the start
        // so that all min path points are visited
        Queue<Pwd> queue = new ArrayDeque<>();
        Set<Pwd> visited = new HashSet<>();
        for (Direction direction : ALL) {
            queue.add(new Pwd(end, direction));
            visited.add(new Pwd(end, direction));
        }
        Set<Point> allPointsOnMinPath = new HashSet<>();
        while (!queue.isEmpty()) {
            Pwd pwd = queue.remove();
            allPointsOnMinPath.add(pwd.p);
            for (Pwd prev : previousLinks.getOrDefault(pwd, new HashSet<>())) {
                if (!visited.contains(prev)) {
                    queue.add(prev);
                    visited.add(prev);
                }
            }
        }
        System.out.println("Part 2: " + allPointsOnMinPath.size()); // 543
    }

    private static int dijkstra(Pwd start, Point end) {
        int length = -1;
        previousLinks = new HashMap<>();
        distanceMap.put(start, 0);
        Pwd current = start;

        PriorityQueue<Pair<Integer, Pwd>> minheap = new PriorityQueue<>(Comparator.comparingInt(Pair::left));
        minheap.add(Pair.of(distanceMap.get(current), start));
        boolean[][][] visited = new boolean[ROWS + 1][COLS + 1][4];

        while (!minheap.isEmpty()) {
            Pair<Integer, Pwd> top = minheap.remove();
            current = top.right();
            visited[current.p.i][current.p.j][current.dir.getIndex()] = true;
            int currentDistance = top.left();

            if (current.p.equals(end)) {
                length = currentDistance;
                break;
            }

            Map<Pwd, Integer> neighboursWithLengths = adjacencyListWithWeights.get(current);

            for (Map.Entry<Pwd, Integer> e : neighboursWithLengths.entrySet()) {
                Point p = e.getKey().p;
                var dir = e.getKey().dir;
                if (visited[p.i][p.j][dir.getIndex()]) {
                    continue;
                }
                int neighbourNewDistance = currentDistance + e.getValue();
                int neighbourCurrentDistance = distanceMap.get(e.getKey());

                // assign the distance to the neighbour
                // and update the distance in unvisited map
                if (neighbourNewDistance < neighbourCurrentDistance) {
                    distanceMap.put(e.getKey(), neighbourNewDistance);
                    minheap.add(Pair.of(neighbourNewDistance, e.getKey()));

                    // If new distance is less than current, simply reset the existing previous linking
                    previousLinks.put(e.getKey(), new HashSet<>());
                    previousLinks.get(e.getKey()).add(current);
                }

                // If distances are equal, then add a previous link to existing list
                if (neighbourNewDistance == neighbourCurrentDistance) {
                    previousLinks.putIfAbsent(e.getKey(), new HashSet<>());
                    previousLinks.get(e.getKey()).add(current);
                }
            }
        }
        return length;
    }

    private static void parse(List<String> lines) {
        ROWS = lines.size();
        COLS = lines.get(0).length();
        adjacencyListWithWeights = new LinkedHashMap<>();
        distanceMap = new LinkedHashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                Point pt = new Point(i, j);
                if (get(lines, pt) == 'S') {
                    start = pt;
                } else if (get(lines, pt) == 'E') {
                    end = pt;
                }

                if (get(lines, pt) != '#') {
                    for (Direction pointDirection : ALL) {
                        Pwd pwd = new Pwd(pt, pointDirection);
                        adjacencyListWithWeights.put(pwd, new LinkedHashMap<>());
                        distanceMap.put(pwd, Integer.MAX_VALUE);
                        for (Direction movingDirection : pointDirection.getValidDirections()) {
                            Point move = pt.move(movingDirection);
                            if (get(lines, move) == '#') continue;
                            Pwd npwd = new Pwd(move, movingDirection);
                            int len = 1;
                            if (movingDirection.isRotated(pointDirection)) {
                                len += 1000;
                            }
                            adjacencyListWithWeights.get(pwd).put(npwd, len);
                        }
                    }
                }
            }
        }
    }

    private static char get(List<String> lines, Point p) {
        try {
            return lines.get(p.i).charAt(p.j);
        } catch (Exception e) {
            return '\0';
        }
    }
}
