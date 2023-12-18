package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2023.inputs.Inputs.DAY_18;

/**
 * Created by vgaur created on 18/12/23
 */
public class Day18 {

    private static final char UP = 'U';
    private static final char DOWN = 'D';
    private static final char LEFT = 'L';
    private static final char RIGHT = 'R';
    private static String INPUT = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
            """;

    public static void solve() {
        INPUT = DAY_18;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Point start = new Point(0, 0);

        Point currentP1 = start;
        Point currentP2 = start;
        List<Point> verticesP1 = new ArrayList<>();
        List<Point> verticesP2 = new ArrayList<>();
        int totalPointsP1 = 0, totalPointsP2 = 0;
        for (String line : lines) {
            // Part 1
            var parts = line.split(" ");
            char dirP1 = parts[0].charAt(0);
            int disP1 = Integer.parseInt(parts[1]);
            currentP1 = currentP1.move(dirP1, disP1);
            totalPointsP1 += disP1;
            verticesP1.add(currentP1);

            // Part 2
            var hex = line.split(" ")[2];
            hex = hex.substring(2, hex.length() - 1);
            char dirP2 = getDirection(hex);
            int disP2 = Integer.parseInt(hex.substring(0, hex.length() - 1), 16);
            currentP2 = currentP2.move(dirP2, disP2);
            totalPointsP2 += disP2;
            verticesP2.add(currentP2);
        }

        Point[] poly = verticesP1.toArray(new Point[0]);
        var ar = (long) getArea(poly);
        ar += (totalPointsP1 - ((totalPointsP1 / 2) - 1)); // Need to adjust because we want to include boundary as well
        System.out.println("Part 1: " + ar); // 74074


        poly = verticesP2.toArray(new Point[0]);
        ar = (long) getArea(poly);
        ar += (totalPointsP2 - ((totalPointsP2 / 2) - 1));
        System.out.println("Part 2: " + ar); // 112074045986829
    }

    private static double getArea(Point[] poly) {
        double res = 0;
        for (int i = 0; i < poly.length; i++) {
            var p = i > 0 ? poly[i - 1] : poly[poly.length - 1];
            var q = poly[i];
            res += (double) (p.x - q.x) * (double) (p.y + q.y);
        }
        return Math.abs(res) / 2;
    }

    private static char getDirection(String hex) {
        int diri = hex.charAt(hex.length() - 1) - '0';
        return switch (diri) {
            case 0 -> 'R';
            case 1 -> 'D';
            case 2 -> 'L';
            case 3 -> 'U';
            default -> throw new RuntimeException("Impossible!");
        };
    }

    record Point(int x, int y) {
        public Point move(char direction, int dis) {
            return switch (direction) {
                case UP -> new Point(this.x - dis, this.y);
                case DOWN -> new Point(this.x + dis, this.y);
                case LEFT -> new Point(this.x, this.y - dis);
                case RIGHT -> new Point(this.x, this.y + dis);
                default -> throw new RuntimeException("Impossible!");
            };
        }
    }
}
