package code.vipul.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static code.vipul.aoc2018.Inputs3.DAY_10;

/**
 * https://adventofcode.com/2018/day/10
 */
public class Solve10 {

    private static final String INPUT = "position=< 9,  1> velocity=< 0,  2>\n" +
            "position=< 7,  0> velocity=<-1,  0>\n" +
            "position=< 3, -2> velocity=<-1,  1>\n" +
            "position=< 6, 10> velocity=<-2, -1>\n" +
            "position=< 2, -4> velocity=< 2,  2>\n" +
            "position=<-6, 10> velocity=< 2, -2>\n" +
            "position=< 1,  8> velocity=< 1, -1>\n" +
            "position=< 1,  7> velocity=< 1,  0>\n" +
            "position=<-3, 11> velocity=< 1, -2>\n" +
            "position=< 7,  6> velocity=<-1, -1>\n" +
            "position=<-2,  3> velocity=< 1,  0>\n" +
            "position=<-4,  3> velocity=< 2,  0>\n" +
            "position=<10, -3> velocity=<-1,  1>\n" +
            "position=< 5, 11> velocity=< 1, -2>\n" +
            "position=< 4,  7> velocity=< 0, -1>\n" +
            "position=< 8, -2> velocity=< 0,  1>\n" +
            "position=<15,  0> velocity=<-2,  0>\n" +
            "position=< 1,  6> velocity=< 1,  0>\n" +
            "position=< 8,  9> velocity=< 0, -1>\n" +
            "position=< 3,  3> velocity=<-1,  1>\n" +
            "position=< 0,  5> velocity=< 0, -1>\n" +
            "position=<-2,  2> velocity=< 2,  0>\n" +
            "position=< 5, -2> velocity=< 1,  2>\n" +
            "position=< 1,  4> velocity=< 2,  1>\n" +
            "position=<-2,  7> velocity=< 2, -2>\n" +
            "position=< 3,  6> velocity=<-1, -1>\n" +
            "position=< 5,  0> velocity=< 1,  0>\n" +
            "position=<-6,  0> velocity=< 2,  0>\n" +
            "position=< 5,  9> velocity=< 1, -2>\n" +
            "position=<14,  7> velocity=<-2,  0>\n" +
            "position=<-3,  6> velocity=< 2, -1>";

    class Point {
        private int x;
        private int y;
        private final int vx;
        private final int vy;

        public Point(String raw) {
            // position=< 52484, -20780> velocity=<-5, 2>
            // position=<-52068, 31483> velocity=< 5, -3>
            String parts[] = raw.split("<");
            String posParts[] = parts[1].substring(0, parts[1].indexOf('>')).split(",");
            x = Integer.parseInt(posParts[0].trim());
            y = Integer.parseInt(posParts[1].trim());
            String velParts[] = parts[2].substring(0, parts[2].length() - 1).split(",");
            vx = Integer.parseInt(velParts[0].trim());
            vy = Integer.parseInt(velParts[1].trim());
        }

        public void tick() {
            x += vx;
            y += vy;
        }

        public void reverseTick() {
            x -= vx;
            y -= vy;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean atCoords(int x, int y) {
            return this.x == x && this.y == y;
        }
    }

    private void main() {
        List<String> input = Arrays.stream(DAY_10.split("\n")).collect(Collectors.toList());


        List<Point> points = new ArrayList<>();
        for (String line : input) {
            points.add(new Point(line));
        }

        int minX = Integer.MIN_VALUE, maxX = Integer.MAX_VALUE, minY = Integer.MIN_VALUE, maxY = Integer.MAX_VALUE;
        int xDiff = Integer.MAX_VALUE, yDiff = Integer.MAX_VALUE;
        int seconds = 0;
        boolean first = true;

        // To have the lights spell out something, points have to be close together. We warp time while both dimensions keep decreasing and stop when we
        // detect an increase on either X or Y coordinate.
        do {
            if (first) {
                first = false;
            } else {
                xDiff = maxX - minX;
                yDiff = maxY - minY;
            }

            minX = Integer.MAX_VALUE;
            maxX = Integer.MIN_VALUE;
            minY = Integer.MAX_VALUE;
            maxY = Integer.MIN_VALUE;

            for (Point point : points) {
                point.tick();

                if (point.getX() < minX) {
                    minX = point.getX();
                }
                if (point.getX() > maxX) {
                    maxX = point.getX();
                }

                if (point.getY() < minY) {
                    minY = point.getY();
                }
                if (point.getY() > maxY) {
                    maxY = point.getY();
                }
            }
            seconds++;
        } while ((maxX - minX) < xDiff && (maxY - minY) < yDiff);

        // Since we detected an increase on either X or Y axis, we're 1 step too far. So we back off 1 step to get the message.
        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE;
        maxY = Integer.MIN_VALUE;
        for (Point point : points) {
            if (point.getX() < minX) {
                minX = point.getX();
            }
            if (point.getX() > maxX) {
                maxX = point.getX();
            }

            if (point.getY() < minY) {
                minY = point.getY();
            }
            if (point.getY() > maxY) {
                maxY = point.getY();
            }

            point.reverseTick();
        }
        seconds--;

        // Display the message
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                boolean found = false;
                for (Point point : points) {
                    if (point.atCoords(x, y)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }

        // Part 2 -- display the seconds we warped
        System.out.println(String.format("Seconds: %d", seconds));
    }

    public static void main(String[] args) {
        new Solve10().main();
    }
}
