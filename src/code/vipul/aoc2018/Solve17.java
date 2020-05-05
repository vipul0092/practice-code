package code.vipul.aoc2018;

import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://adventofcode.com/2018/day/17
 */
public class Solve17 {

    private static final int WATER_SOURCE = -1;
    private static final int SAND = 0;
    private static final int CLAY = 1;
    private static final int WATER = 2;

    private static String input = Inputs2.DAY17;
    private static int[][] scan;
    private static Grid.Pos startingPos = Grid.Pos.of(0, 500);
    private static int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
    private static int count = 0;

    public static void solve() {
        input = "x=495, y=2..7\n" +
                "y=7, x=495..501\n" +
                "x=501, y=3..7\n" +
                "x=498, y=2..4\n" +
                "x=506, y=1..2\n" +
                "x=498, y=10..13\n" +
                "x=504, y=10..13\n" +
                "y=13, x=498..504";

        input = "x=495, y=5..25\n" +
                "y=25, x=495..525\n" +
                "x=525, y=5..25\n" +
                "x=505, y=10..20\n" +
                "y=20, x=505..515\n" +
                "x=515, y=10..20";

        scan = new int[1700][1700];
        scan[0][500] = WATER_SOURCE;

        String[] inputs = input.split("\n");

        for (String in : inputs) {
            String lhs = in.split(",")[0].trim();
            String rhs = in.split(",")[1].trim();

            String[] lhsSplit = lhs.split("=");
            boolean isIFixed = lhsSplit[0].equals("y"); // i is fixed when the depth is fixed
            int fixedValue = Integer.parseInt(lhsSplit[1].trim());

            if (isIFixed) {
                minY = Math.min(minY, fixedValue);
                maxY = Math.max(maxY, fixedValue);
            } else {
                minX = Math.min(minX, fixedValue);
                maxX = Math.max(maxX, fixedValue);
            }

            String[] rhsRange = rhs.split("=")[1].split("\\.\\.");
            int rangeStart = Integer.parseInt(rhsRange[0].trim());
            int rangeEnd = Integer.parseInt(rhsRange[1].trim());

            for (int k = rangeStart; k <= rangeEnd; k++) {
                if (isIFixed) {
                    scan[fixedValue][k] = CLAY;
                    minX = Math.min(minX, k);
                    maxX = Math.max(maxX, k);
                } else {
                    scan[k][fixedValue] = CLAY;
                    minY = Math.min(minY, k);
                    maxY = Math.max(maxY, k);
                }
            }
        }
        // print();

        Queue<Grid.Pos> water = new ArrayDeque<>();
        water.add(startingPos);

        while (!water.isEmpty()) {
            Grid.Pos waterPos = water.remove();
            fill(waterPos, water);
            print();
        }
        Scanner in = new Scanner(System.in);
        in.nextLine();
        print();
        System.out.println("Answer: " + count);
    }

    private static void fill(Grid.Pos waterPos, Queue<Grid.Pos> waterSource) {
        Grid.Pos pos = waterPos.moveDown();

        while (valueAt(pos) == SAND && pos.i() <= maxY) {
            setValue(pos, WATER);
            if (pos.i() >= minY) {
                count++;
            }
            pos = pos.moveDown();
        }

        // We reached the limit
        if (pos.i() > maxY) {
            return;
        }

        pos = pos.moveUp();

        // Now move water sideways and start moving up, until we find a point where the water starts going down
        while(true) {
            Grid.Pos rightSide = pos.moveRight();
            boolean foundSourceRight = false;
            // Go right
            while(valueAt(rightSide) == SAND) {
                setValue(rightSide, WATER);
                count++;
                if (valueAt(rightSide.moveDown()) == SAND) {
                    foundSourceRight = true;
                    break;
                }
                rightSide = rightSide.moveRight();
            }

            if (foundSourceRight) {
                waterSource.add(rightSide);
            }

            Grid.Pos leftSide = pos.moveLeft();
            boolean foundSourceLeft = false;

            // Go left
            while(valueAt(leftSide) == SAND) {
                setValue(leftSide, WATER);
                count++;
                if (valueAt(leftSide.moveDown()) == SAND) {
                    foundSourceLeft = true;
                    break;
                }
                leftSide = leftSide.moveLeft();
            }

            if (foundSourceLeft) {
                waterSource.add(leftSide);
            }

            if (foundSourceLeft || foundSourceRight || pos.i() == 0) {
                break;
            }
            pos = pos.moveUp();
        }
    }

    private static int valueAt(Grid.Pos pos) {
        return scan[pos.i()][pos.j()];
    }

    private static void setValue(Grid.Pos pos, int value) {
        scan[pos.i()][pos.j()] = value;
    }

    private static void print() {
        for (int i = 0; i <= maxY; i++) {
            for (int j = minX - 1; j <= maxX + 1; j++) {
                if (scan[i][j] == WATER_SOURCE) {
                    System.out.print("+");
                } else if (scan[i][j] == WATER) {
                    System.out.print("~");
                } else if (scan[i][j] == CLAY) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
