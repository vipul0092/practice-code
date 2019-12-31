package code.vipul.aoc2019;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://adventofcode.com/2019/day/10
 */
public class Solve10 {

    static String input =
            ".#......##.#..#.......#####...#..\n" +
                    "...#.....##......###....#.##.....\n" +
                    "..#...#....#....#............###.\n" +
                    ".....#......#.##......#.#..###.#.\n" +
                    "#.#..........##.#.#...#.##.#.#.#.\n" +
                    "..#.##.#...#.......#..##.......##\n" +
                    "..#....#.....#..##.#..####.#.....\n" +
                    "#.............#..#.........#.#...\n" +
                    "........#.##..#..#..#.#.....#.#..\n" +
                    ".........#...#..##......###.....#\n" +
                    "##.#.###..#..#.#.....#.........#.\n" +
                    ".#.###.##..##......#####..#..##..\n" +
                    ".........#.......#.#......#......\n" +
                    "..#...#...#...#.#....###.#.......\n" +
                    "#..#.#....#...#.......#..#.#.##..\n" +
                    "#.....##...#.###..#..#......#..##\n" +
                    "...........#...#......#..#....#..\n" +
                    "#.#.#......#....#..#.....##....##\n" +
                    "..###...#.#.##..#...#.....#...#.#\n" +
                    ".......#..##.#..#.............##.\n" +
                    "..###........##.#................\n" +
                    "###.#..#...#......###.#........#.\n" +
                    ".......#....#.#.#..#..#....#..#..\n" +
                    ".#...#..#...#......#....#.#..#...\n" +
                    "#.#.........#.....#....#.#.#.....\n" +
                    ".#....#......##.##....#........#.\n" +
                    "....#..#..#...#..##.#.#......#.#.\n" +
                    "..###.##.#.....#....#.#......#...\n" +
                    "#.##...#............#..#.....#..#\n" +
                    ".#....##....##...#......#........\n" +
                    "...#...##...#.......#....##.#....\n" +
                    ".#....#.#...#.#...##....#..##.#.#\n" +
                    ".#.#....##.......#.....##.##.#.##\n";
    private static int answer = 0;
    private static Grid.Pos asteroidPos = null;

    public static void solve() {
        String[] asteroidRows = input.split("\n");
        answer = 0;

        Grid.Pos[] asteroids = new Grid.Pos[1000];
        int ctr = 0;

        for (int y = 0; y < asteroidRows.length; y++) {
            String asteroid = asteroidRows[y];
            for (int x = 0; x < asteroid.length(); x++) {
                if (asteroid.charAt(x) == '#') {
                    asteroids[ctr++] = Grid.Pos.of(x, y);
                }
            }
        }

        for (int i = 0; i < ctr; i++) {
            Grid.Pos asteroid = asteroids[i];
            run(asteroid, ctr, asteroids);
        }

        System.out.println("Answer: " + answer); // 256
        System.out.println("Asteroid Position - x: " + asteroidPos.i() + ", y: " + asteroidPos.j());
    }

    public static void solvePart2() {
        String[] asteroidRows = input.split("\n");
        answer = 0;

        Set<Grid.Pos> asteroids = new HashSet<>();

        for (int y = 0; y < asteroidRows.length; y++) {
            String asteroid = asteroidRows[y];
            for (int x = 0; x < asteroid.length(); x++) {
                if (asteroid.charAt(x) == '#') {
                    asteroids.add(Grid.Pos.of(x, y));
                }
            }
        }

        Grid.Pos[] asteroidArr = asteroids.toArray(new Grid.Pos[0]);
        for (int i = 0; i < asteroids.size(); i++) {
            Grid.Pos asteroid = asteroidArr[i];
            run(asteroid, asteroids.size(), asteroidArr);
        }

        Grid.Pos baseAsteroid = asteroidPos;
        asteroids.remove(baseAsteroid);

        int count = 0;
        Grid.Pos answer = null;
        while (asteroids.size() > 0) {
            asteroidArr = asteroids.toArray(new Grid.Pos[0]);
            run(baseAsteroid, asteroids.size(), asteroidArr);

            if (down != null) {  // Start from down
                count++;
                asteroids.remove(down);
            }

            if (count == 200) {
                answer = down;
                break;
            }

            // Sweep Quadrant 4 i.e. increasing negative slopes
            List<Grid.Pos> bottomNeg = new ArrayList<>(slopesDownNeg.values());
            if (count + bottomNeg.size() >= 200) {
                answer = bottomNeg.get(200 - count - 1);
                break;
            } else {
                count += bottomNeg.size();
                asteroids.removeAll(bottomNeg);
            }

            if (right != null) {  // Right
                asteroids.remove(right);
                count++;
            }

            if (count == 200) {
                answer = right;
                break;
            }

            // Sweep Quadrant 1, i.e. increasing positive slope
            List<Grid.Pos> topPos = new ArrayList<>(slopesUpPos.values());
            if (count + topPos.size() >= 200) {
                answer = topPos.get(200 - count - 1);
                break;
            } else {
                count += topPos.size();
                asteroids.removeAll(topPos);
            }

            if (up != null) {  // Up
                asteroids.remove(up);
                count++;
            }

            if (count == 200) {
                answer = up;
                break;
            }

            // Sweep Quadrant 2, i.e. increasing negative slopes
            List<Grid.Pos> topNeg = new ArrayList<>(slopesUpNeg.values());
            if (count + topNeg.size() >= 200) {
                answer = topNeg.get(200 - count - 1);
                break;
            } else {
                count += topNeg.size();
                asteroids.removeAll(topNeg);
            }

            if (left != null) {  // Left
                asteroids.remove(left);
                count++;
            }

            if (count == 200) {
                answer = left;
                break;
            }

            // Sweep Quadrant 3, i.e. increasing positive slope
            List<Grid.Pos> bottomPos = new ArrayList<>(slopesDownPos.values());
            if (count + bottomPos.size() >= 200) {
                answer = bottomPos.get(200 - count - 1);
                break;
            } else {
                count += bottomPos.size();
                asteroids.removeAll(bottomPos);
            }
        }

        System.out.println("Asteroid Position - x: " + answer.i() + ", y: " + answer.j());
        int value = (answer.i() * 100) + answer.j();
        System.out.println("Answer: " + value); // 1707
    }

    private static Grid.Pos up;
    private static Grid.Pos down;
    private static Grid.Pos left;
    private static Grid.Pos right;
    private static Map<BigDecimal, Grid.Pos> slopesUpPos;
    private static Map<BigDecimal, Grid.Pos> slopesUpNeg;
    private static Map<BigDecimal, Grid.Pos> slopesDownPos;
    private static Map<BigDecimal, Grid.Pos> slopesDownNeg;

    private static void run(Grid.Pos asteroid, int ctr, Grid.Pos[] asteroids) {
        Set<BigDecimal> slopesUp = new HashSet<>();
        Set<BigDecimal> slopesDown = new HashSet<>();
        slopesUpPos = new TreeMap<>();
        slopesUpNeg = new TreeMap<>();
        slopesDownPos = new TreeMap<>();
        slopesDownNeg = new TreeMap<>();

        up = null;
        down = null;
        left = null;
        right = null;
        int count = 0;
        for (int j = 0; j < ctr; j++) {
            Grid.Pos asteroidToCheck = asteroids[j];

            if (asteroidToCheck.equals(asteroid)) {
                continue;
            }

            if (asteroidToCheck.j() > asteroid.j()) {
                if (asteroidToCheck.i() == asteroid.i()) {
                    if (up == null) {
                        count++;
                        up = asteroidToCheck;
                    } else {
                        up = asteroidToCheck.j() < up.j() ? asteroidToCheck : up;
                    }
                } else {
                    BigDecimal slope = getSlope(asteroidToCheck, asteroid);
                    if (!slopesUp.contains(slope)) {
                        count++;
                    }
                    slopesUp.add(slope);
                    if (slope.compareTo(BigDecimal.ZERO) < 0) {
                        slopesUpNeg.put(slope, getPosToSave(asteroid, asteroidToCheck, slope, slopesUpNeg));
                    } else {
                        slopesUpPos.put(slope, getPosToSave(asteroid, asteroidToCheck, slope, slopesUpPos));
                    }
                }
            } else if (asteroidToCheck.j() < asteroid.j()) {
                if (asteroidToCheck.i() == asteroid.i()) {
                    if (down == null) {
                        count++;
                        down = asteroidToCheck;
                    } else {
                        down = asteroidToCheck.j() > down.j() ? asteroidToCheck : down;
                    }
                } else {
                    BigDecimal slope = getSlope(asteroidToCheck, asteroid);
                    if (!slopesDown.contains(slope)) {
                        count++;
                    }
                    slopesDown.add(slope);
                    if (slope.compareTo(BigDecimal.ZERO) < 0) {
                        slopesDownNeg.put(slope, getPosToSave(asteroid, asteroidToCheck, slope, slopesDownNeg));
                    } else {
                        slopesDownPos.put(slope, getPosToSave(asteroid, asteroidToCheck, slope, slopesDownPos));
                    }
                }
            } else { // same y coordinate
                if (asteroidToCheck.i() > asteroid.i()) {
                    if (right == null) {
                        count++;
                        right = asteroidToCheck;
                    } else {
                        right = asteroidToCheck.i() < right.i() ? asteroidToCheck : right;
                    }
                } else {
                    if (left == null) {
                        count++;
                        left = asteroidToCheck;
                    } else {
                        left = asteroidToCheck.i() > left.i() ? asteroidToCheck : left;
                    }
                }
            }
        }
        if (answer < count) {
            answer = count;
            asteroidPos = asteroid;
        }
    }

    private static Grid.Pos getPosToSave(Grid.Pos asteroid, Grid.Pos asteroidToCheck, BigDecimal slope,
                                         Map<BigDecimal, Grid.Pos> slopeMap) {
        return slopeMap.containsKey(slope)
                ? closer(asteroid, asteroidToCheck, slopeMap.get(slope))
                : asteroidToCheck;
    }

    private static Grid.Pos closer(Grid.Pos origin, Grid.Pos point1, Grid.Pos point2) {
        int distance1 = distanceSq(origin, point1);
        int distance2 = distanceSq(origin, point2);
        return distance1 < distance2 ? point1 : point2;
    }

    private static int distanceSq(Grid.Pos point1, Grid.Pos point2) {
        return ((point1.i() - point2.i()) * (point1.i() - point2.i())
                + (point1.j() - point2.j()) * (point1.j() - point2.j()));
    }

    private static BigDecimal getSlope(Grid.Pos a1, Grid.Pos a2) {
        return BigDecimal.valueOf(a1.j() - a2.j())
                .divide(BigDecimal.valueOf(a1.i() - a2.i()), 10, RoundingMode.HALF_UP);
    }
}
