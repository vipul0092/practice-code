package code.vipul.aoc2023;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static code.vipul.aoc2023.inputs.Inputs.DAY_16;

/**
 * Created by vgaur created on 16/12/23
 */
public class Day16 {

    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    private static String INPUT = """
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....
            """;

    public static void solve() {
        INPUT = DAY_16;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        var start = new Pwd(new Point(0, 0), RIGHT);
        System.out.println("Part 1: " + getEnergisedPoints(start, lines)); // 8146

        int max = 0;
        for (int i = 0; i < lines.size(); i++) {
            max = Math.max(getEnergisedPoints(new Pwd(new Point(i, 0), RIGHT), lines), max);
            max = Math.max(getEnergisedPoints(new Pwd(new Point(i, lines.get(i).length()-1), LEFT), lines), max);
        }

        for (int j = 0; j < lines.get(0).length(); j++) {
            max = Math.max(getEnergisedPoints(new Pwd(new Point(0, j), DOWN), lines), max);
            max = Math.max(getEnergisedPoints(new Pwd(new Point(lines.size()-1, j), UP), lines), max);
        }

        System.out.println("Part 2: " + max); // 8358
    }

    private static int getEnergisedPoints(Pwd start, List<String> lines) {
        Queue<Pwd> queue = new ArrayDeque<>();
        Set<Point> energised = new HashSet<>();
        Set<Pwd> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Pwd curr = queue.remove();
            int direction = curr.direction;
            Point point = curr.p;
            energised.add(point);

            char ch = value(point, lines);
            if (ch == '.') {
                enqueue(new Pwd(point.move(direction), direction), queue, visited, lines);
            } else if (ch == '\\') {
                int newdirection = direction == UP || direction == DOWN
                        ? rotate90(direction, false)
                        : rotate90(direction, true);
                enqueue(new Pwd(point.move(newdirection), newdirection), queue, visited, lines);
            } else if (ch == '/') {
                int newdirection = direction == UP || direction == DOWN
                        ? rotate90(direction, true)
                        : rotate90(direction, false);
                enqueue(new Pwd(point.move(newdirection), newdirection), queue, visited, lines);
            } else if (ch == '|') {
                if (direction == LEFT || direction == RIGHT) {
                    enqueue(new Pwd(point.move(UP), UP), queue, visited, lines);
                    enqueue(new Pwd(point.move(DOWN), DOWN), queue, visited, lines);
                } else {
                    enqueue(new Pwd(point.move(direction), direction), queue, visited, lines);
                }
            } else if (ch == '-') {
                if (direction == UP || direction == DOWN) {
                    enqueue(new Pwd(point.move(LEFT), LEFT), queue, visited, lines);
                    enqueue(new Pwd(point.move(RIGHT), RIGHT), queue, visited, lines);
                } else {
                    enqueue(new Pwd(point.move(direction), direction), queue, visited, lines);
                }
            }
        }
        return energised.size();
    }

    private static void enqueue(Pwd pwd, Queue<Pwd> queue, Set<Pwd> visited, List<String> lines) {
        if (valid(pwd.p, lines) && !visited.contains(pwd)) {
            queue.add(pwd);
            visited.add(pwd);
        }
    }

    private static boolean valid(Point p, List<String> lines) {
        return p.x >= 0 && p.y >= 0 && p.x < lines.size() && p.y < lines.get(0).length();
    }

    private static int rotate90(int direction, boolean clockwise) {
        return switch (direction) {
            case UP -> clockwise ? RIGHT : LEFT;
            case DOWN -> clockwise ? LEFT : RIGHT;
            case LEFT -> clockwise ? UP : DOWN;
            case RIGHT -> clockwise ? DOWN : UP;
            default -> throw new RuntimeException("Impossible!");
        };
    }

    private static char value(Point p, List<String> lines) {
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

    record Pwd(Point p, int direction) {
    }
}
