package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.*;

/**
 * https://adventofcode.com/2024/day/15
 */
public class Day15 {

    record Point(int i, int j) {}
    record Box(Point p1, Point p2) {  // Represents a box, for part 1, p1 and p2 are the same

        // Recursively checks whether the given box can move or not
        // by finding adjoining boxes, and checking if they can move or not, populates `boxesToMove` along the way
        boolean canMove(char dir, Set<Box> boxesToMove) {
            Point move1 = move(dir, p1);
            Point move2 = move(dir, p2);
            if (walls.contains(move1) || walls.contains(move2)) {
                return false;
            }
            // move1 may be p2 and move2 may be p1 when trying to move sideways
            // there we assume we can move that point, the actual result will be defined by the other point
            boolean canMove1 = !pointToBoxMap.containsKey(move1)
                    || move1.equals(p2) || pointToBoxMap.get(move1).canMove(dir, boxesToMove);
            boolean canMove2 = !pointToBoxMap.containsKey(move2)
                    || move2.equals(p1) || pointToBoxMap.get(move2).canMove(dir, boxesToMove);
            boolean move = canMove1 && canMove2;
            if (move) {
                boxesToMove.add(this);
            }
            return move;
        }
    }

    private static int limit;
    private static List<String> lines;
    private static Set<Point> walls;
    private static Map<Point, Box> pointToBoxMap;
    private static Point robot;
    private static int maxi;
    private static String operations;

    public static void solve() {
        lines = AoCInputReader.read(Day15.class, false);

        limit = 1;
        parseInput();
        moveRobot();
        int sum1 = new HashSet<>(pointToBoxMap.values()).stream()
                .map(b -> b.p1.i * 100 + b.p1.j)
                .mapToInt(i -> i).sum();

        limit = 2;
        parseInput();
        moveRobot();
        int sum2 = new HashSet<>(pointToBoxMap.values()).stream()
                .map(b -> b.p1.i * 100 + b.p1.j)
                .mapToInt(i -> i).sum();

        System.out.println("Part 1: " + sum1); // 1490942
        System.out.println("Part 2: " + sum2); // 1519202
    }

    private static void moveRobot() {
        Point curPoint = robot;

        for (char op : operations.toCharArray()) {
            Point nextPoint = move(op, curPoint);

            if (walls.contains(nextPoint)) {
                continue;
            }

            if (pointToBoxMap.containsKey(nextPoint)) {
                Box currentBox = pointToBoxMap.get(nextPoint);
                Set<Box> boxesToMove = new HashSet<>();
                boolean canMove = currentBox.canMove(op, boxesToMove);

                // Evaluation is done only when we found that we can move
                // Move all the boxes that we tracked to move
                // update their positions properly by doing a delete + insert
                if (canMove) {
                    curPoint = nextPoint;
                    for (Box box : boxesToMove) { // remove all to be moved box previous data
                        pointToBoxMap.remove(box.p1);
                        pointToBoxMap.remove(box.p2);
                    }
                    for (Box box : boxesToMove) { // Move the boxes, and update the set and map with new moved points
                        Box newBox = new Box(move(op, box.p1), move(op, box.p2));
                        pointToBoxMap.put(newBox.p1, newBox);
                        pointToBoxMap.put(newBox.p2, newBox);
                    }
                }
            } else {
                curPoint = nextPoint;
            }
//             System.out.println("Move: " + op);
//             display();
        }
    }

    private static void display() {
        for (int i = 0; i < maxi; i++) {
            for (int j = 0; j < lines.get(0).length() * limit; j++) {
                Point p = new Point(i, j);
                if (walls.contains(p)) {
                    System.out.print("#");
                } else if (pointToBoxMap.containsKey(p)) {
                    System.out.print("X");
                } else if (robot.equals(p)) {
                    System.out.print("@");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static Point move(char op, Point cur) {
        return switch (op) {
            case '>' -> new Point(cur.i, cur.j + 1);
            case '<' -> new Point(cur.i, cur.j - 1);
            case '^' -> new Point(cur.i - 1, cur.j);
            default -> new Point(cur.i + 1, cur.j);
        };
    }

    private static void parseInput() {
        walls = new HashSet<>();
        pointToBoxMap = new HashMap<>();
        robot = null;
        int i;
        for (i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) break;
            for (int j = 0, jcoord = 0; j < lines.get(i).length(); j++) {
                Point pt = new Point(i, j);
                char ch = get(lines, pt);
                if (ch == '#') {
                    for (int k = 0; k < limit; k++) {
                        Point p = new Point(i, jcoord + k);
                        walls.add(p);
                    }
                } else if (ch == 'O') {
                    Point p1 = new Point(i, jcoord);
                    Point p2 = new Point(i, limit == 2 ? jcoord + 1 : jcoord);
                    Box box = new Box(p1, p2);
                    pointToBoxMap.put(p1, box);
                    pointToBoxMap.put(p2, box);
                } else if (ch == '@') {
                    robot = new Point(i, jcoord);
                }
                jcoord += limit;
            }
        }

        maxi = i;
        operations = "";
        i++;
        for (; i < lines.size(); i++) {
            operations += lines.get(i);
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
