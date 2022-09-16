package code.vipul.aoc2018;

import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

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
    private static Set<Grid.Pos> sources = new HashSet<>();

    public static void solve() {
//        input = "x=495, y=2..7\n" +
//                "y=7, x=495..501\n" +
//                "x=501, y=3..7\n" +
//                "x=498, y=2..4\n" +
//                "x=506, y=1..2\n" +
//                "x=498, y=10..13\n" +
//                "x=504, y=10..13\n" +
//                "y=13, x=498..504";

//        input = "x=495, y=5..25\n" +
//                "y=25, x=495..525\n" +
//                "x=525, y=5..25\n" +
//                "x=505, y=10..20\n" +
//                "y=20, x=505..515\n" +
//                "x=515, y=10..20";

//        input = "x=496, y=5..8\n" +
//                "x=504, y=5..8\n" +
//                "y=8, x=496..504\n" +
//                "x=499, y=12..15\n" +
//                "x=493, y=12..15\n" +
//                "y=15, x=493..499\n" +
//                "x=502, y=11..14\n" +
//                "x=508, y=10..14\n" +
//                "y=14, x=502..508\n" +
//                "x=496, y=20..24\n" +
//                "x=504, y=19..24\n" +
//                "y=24, x=496..504\n" +
//                "x=490, y=30..32\n" +
//                "x=508, y=30..32\n" +
//                "y=32, x=490..508";

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
//        print();
//        waitForInput();

        Queue<Grid.Pos> water = new ArrayDeque<>();
        water.add(startingPos);
        sources.add(startingPos);

        while (!water.isEmpty()) {
            Grid.Pos waterPos = water.remove();
            sources.remove(waterPos);
            fill(waterPos, water);
            waitForInput();
            print();
        }
        // waitForInput();
        print();
        System.out.println("Answer: " + count);
    }

    private static void fill(Grid.Pos waterPos, Queue<Grid.Pos> waterSource) {
        Grid.Pos pos = waterPos.moveDown();

        while (valueAt(pos) != CLAY && pos.i() <= maxY) {
            setValue(pos, WATER);
            if (pos.i() >= minY && valueAt(pos) != WATER) {
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
        while (true) {
            if (valueAt(pos) != WATER) {
                setValue(pos, WATER);
                count++;
            }

            Grid.Pos rightSide = pos.moveRight();
            boolean foundSourceRight = false;
            // Go right
            while (valueAt(rightSide) != CLAY) {
                if (valueAt(rightSide) != WATER) {
                    setValue(rightSide, WATER);
                    count++;
                }
                if ((valueAt(rightSide.moveDown()) == WATER && valueAt(rightSide.moveDown().moveLeft()) == CLAY)
                        || sources.contains(rightSide) || valueAt(rightSide.moveDown()) == SAND) {
                    foundSourceRight = true;
                    // System.out.println("Found source at right at: " + rightSide.toString());
                    break;
                }
                rightSide = rightSide.moveRight();
            }

            if (foundSourceRight && valueAt(rightSide.moveDown()) == SAND && !sources.contains(rightSide)) {
                waterSource.add(rightSide);
                sources.add(rightSide);
            }

            Grid.Pos leftSide = pos.moveLeft();
            boolean foundSourceLeft = false;

            // Go left
            while (valueAt(leftSide) != CLAY) {
                if (valueAt(leftSide) != WATER) {
                    setValue(leftSide, WATER);
                    count++;
                }
                if ((valueAt(leftSide.moveDown()) == WATER && valueAt(leftSide.moveDown().moveRight()) == CLAY)
                        || sources.contains(leftSide) || valueAt(leftSide.moveDown()) == SAND) {
                    // System.out.println("Found source at left at: " + leftSide.toString());
                    foundSourceLeft = true;
                    break;
                }
                leftSide = leftSide.moveLeft();
            }

            if (foundSourceLeft && valueAt(leftSide.moveDown()) == SAND && !sources.contains(leftSide)) {
                waterSource.add(leftSide);
                sources.add(leftSide);
            }

            if (foundSourceLeft || foundSourceRight || pos.i() == 0) {
                break;
            }
            pos = pos.moveUp();
        }

        // Check if we can rise up altogether
        Grid.Pos temp = waterPos.copy();
        boolean fullFilled = valueAt(temp) == WATER;
        while (valueAt(temp) != WATER) {
            temp = temp.moveRight();
        }
        if (valueAt(temp) != CLAY) {
            fullFilled = false;
        }

        temp = waterPos.copy();
        while (fullFilled && valueAt(temp) != WATER) {
            temp = temp.moveLeft();
        }
        if (valueAt(temp) != CLAY) {
            fullFilled = false;
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

    private static void waitForInput() {
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}
