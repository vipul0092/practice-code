package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.*;

/**
 * https://adventofcode.com/2024/day/6
 */
public class Day6 {

    record Point(int i, int j) {
        Point move(Direction direction) {
            return switch (direction) {
                case UP -> new Point(i - 1, j);
                case DOWN -> new Point(i + 1, j);
                case LEFT -> new Point(i, j - 1);
                default -> new Point(i, j + 1);
            };
        }
    }
    record Pwd(Point p, Direction d) {}
    record Result(Set<Point> visited, StopType stopType) {}
    enum Direction {
        UP, DOWN, LEFT, RIGHT;
        public Direction rotate() {
            return switch (this) {
                case UP -> Direction.RIGHT;
                case DOWN -> Direction.LEFT;
                case LEFT -> Direction.UP;
                default -> Direction.DOWN;
            };
        }
    }
    enum StopType {
        CYCLE, EXIT
    }

    private static Point extraRock = null;

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day6.class, false);

        Point start = null;
        outer:
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if ('^' == get(lines, new Point(i, j))) {
                    start = new Point(i, j);
                    break outer;
                }
            }
        }
        Result result1 =  moveUntilExit(lines, start);
        int count1 = result1.visited.size();

        int count2 = 0;
        for (Point dot : result1.visited) {
            if (get(lines, dot) != '.') continue;
            extraRock = dot;
            var result = moveUntilExit(lines, start);
            if (result.stopType == StopType.CYCLE) count2++;
        }

        System.out.println("Part 1: " + count1); // 4883
        System.out.println("Part 2: " + count2); // 1655
    }

    private static Result moveUntilExit(List<String> lines, Point start) {
        Point location = start;
        Direction direction = Direction.UP;
        char currentValue = '^';
        Set<Point> visited = new HashSet<>();
        Set<Pwd> pointsWithDirection = new HashSet<>();
        pointsWithDirection.add(new Pwd(location, direction));
        visited.add(location);

        StopType stopType;
        while (true) {
            Point prev = location;
            location = location.move(direction);

            currentValue = get(lines, location);
            if (currentValue == '#') {
                direction = direction.rotate();
                location = prev;
            }

            if (currentValue == '\0') {
                stopType = StopType.EXIT;
                break;
            }

            Pwd pwd = new Pwd(location, direction);
            if (pointsWithDirection.contains(pwd)) {
                stopType = StopType.CYCLE;
                break;
            }
            pointsWithDirection.add(pwd);
            visited.add(location);
        }
        return new Result(visited, stopType);
    }

    private static char get(List<String> lines, Point p) {
        if (p.equals(extraRock)) {
            return '#';
        }
        try {
            return lines.get(p.i).charAt(p.j);
        } catch (Exception e) {
            return '\0';
        }
    }
}
