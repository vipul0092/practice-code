package code.vipul.aoc2018;

import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://adventofcode.com/2018/day/17
 */
public class Solve17 {

    private static boolean ENABLE_PRINTING = true;

    private static final int WATER_SOURCE = -1;
    private static final int SAND = 0;
    private static final int CLAY = 1;
    private static final int WATER = 2;
    private static final int WATER_FLOWING = 3;

    private static String input = Inputs2.INPUT_17_2;
    private static int[][] scan;
    private static int[][] scanCopy;
    private static Grid.Pos startingPos = Grid.Pos.of(0, 500);
    private static int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
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

//        input = "x=475, y=5..25\n" +
//                "y=25, x=475..525\n" +
//                "x=525, y=5..25\n" +
//                "x=490, y=10..20\n" +
//                "y=20, x=490..500\n" +
//                "x=500, y=10..20";

        scan = new int[2500][2500];
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
            maxYWater = maxY;
        }

        Grid.setMaxRowsCols(maxY + 1, maxX + 1);
        print();

        Queue<Grid.Pos> water = new ArrayDeque<>();
        water.add(startingPos);
        sources.add(startingPos);
        ENABLE_PRINTING = false;
        while (!water.isEmpty()) {
            Grid.Pos waterPos = water.remove();
            sources.remove(waterPos);
            fill(waterPos, water);
        }
        ENABLE_PRINTING = false;

        scanCopy = new int[2500][2500];
        for (int i = 0; i < scanCopy.length; i++) {
            for (int j = 0; j < scanCopy[i].length; j++) {
                scanCopy[i][j] = scan[i][j];
            }
        }

        populateFlow(startingPos);

        int totalWater = 0;
        int staticWater = 0;
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX - 1; j <= maxX + 1; j++) {
                if (scanCopy[i][j] == WATER || scanCopy[i][j] == WATER_FLOWING) {
                    totalWater++;
                    if (scanCopy[i][j] == WATER) {
                        staticWater++;
                    }
                }
            }
        }
        ENABLE_PRINTING = true;
        printCopy();
        System.out.println("Answer Pt. 1: " + totalWater); //31861
        System.out.println("Answer Pt. 2: " + staticWater); //26030
    }

    private static void fill(Grid.Pos waterPos, Queue<Grid.Pos> waterSource) {
        Grid.Pos pos = waterPos.moveDown();

        while (valueAt(pos) != CLAY && pos.i() <= maxY) {
            setValue(pos, WATER);
            pos = pos.moveDown();
        }

        // We reached the limit
        if (pos.i() > maxY) {
            return;
        }

        pos = pos.moveUp();

        Set<Grid.Pos> sourcesFound = new TreeSet<>();
        Set<Grid.Pos> sf = new TreeSet<>();
        // Now move water sideways and start moving up, until we find a point where the water starts going down
        boolean foundSource = false;
        while (true) {
            if (valueAt(pos) != WATER) {
                setValue(pos, WATER);
            }

            Grid.Pos rightSide = pos.moveRight();
            boolean foundSourceRight = false;
            // Go right
            while (valueAt(rightSide) != CLAY) {
                if (valueAt(rightSide) != WATER) {
                    setValue(rightSide, WATER);
                }
                if ((valueAt(rightSide.moveDown()) == WATER && valueAt(rightSide.moveDown().moveLeft()) == CLAY)
                        || sources.contains(rightSide) || valueAt(rightSide.moveDown()) == SAND) {
                    foundSourceRight = true;
                    sf.add(rightSide);
                    // System.out.println("Found source at right at: " + rightSide.toString());
                    break;
                }
                rightSide = rightSide.moveRight();
            }

            if (foundSourceRight && valueAt(rightSide.moveDown()) == SAND && !sources.contains(rightSide)) {
                sourcesFound.add(rightSide);
                sources.add(rightSide);
            }

            Grid.Pos leftSide = pos.moveLeft();
            boolean foundSourceLeft = false;

            // Go left
            while (valueAt(leftSide) != CLAY) {
                if (valueAt(leftSide) != WATER) {
                    setValue(leftSide, WATER);
                }
                if ((valueAt(leftSide.moveDown()) == WATER && valueAt(leftSide.moveDown().moveRight()) == CLAY)
                        || sources.contains(leftSide) || valueAt(leftSide.moveDown()) == SAND) {
                    // System.out.println("Found source at left at: " + leftSide.toString());
                    sf.add(leftSide);
                    foundSourceLeft = true;
                    break;
                }
                leftSide = leftSide.moveLeft();
            }

            if (foundSourceLeft && valueAt(leftSide.moveDown()) == SAND && !sources.contains(leftSide)) {
                sourcesFound.add(leftSide);
                sources.add(leftSide);
            }

            if (foundSourceLeft || foundSourceRight || pos.i() == 0) {
                foundSource = foundSourceLeft || foundSourceRight;
                break;
            }
            pos = pos.moveUp();
        }
        // boolean foundSource = !sourcesFound.isEmpty();
        waterSource.addAll(sourcesFound);

        foundSource = foundSource && sf.stream().anyMatch(p -> p.i() != waterPos.i());
        if (waterSource.stream().anyMatch(p -> p.i() == waterPos.i())) {
            foundSource = true;
        }

        print();
        // Check if we can rise up altogether
        Grid.Pos ws = null;
        if (waterSource.isEmpty() || !foundSource) {
            pos = pos.moveUp();
            Grid.Pos src = Grid.Pos.of(pos.i(), waterPos.j());

            Grid.Pos t1 = src.copy();
            Grid.Pos t2 = src.copy();
            while (t1.isValid() && !(valueAt(t1) == CLAY || valueAt(t1) == WATER)) {
                t1 = t1.moveLeft();
            }
            while (t2.isValid() && !(valueAt(t2) == CLAY || valueAt(t2) == WATER)) {
                t2 = t2.moveRight();
            }
            ws = valueAt(t1) == WATER ? t1 : (valueAt(t2) == WATER ? t2 : null);
        }

        if (ws == null) {
            return;
        }

        pos = ws;
        while (true) {
            if (valueAt(pos) != WATER) {
                setValue(pos, WATER);
            }

            Grid.Pos rightSide = pos.moveRight();
            boolean foundSourceRight = false;
            // Go right
            while (valueAt(rightSide) != CLAY) {
                if (valueAt(rightSide) != WATER) {
                    setValue(rightSide, WATER);
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
                sourcesFound.add(rightSide);
                sources.add(rightSide);
            }

            Grid.Pos leftSide = pos.moveLeft();
            boolean foundSourceLeft = false;

            // Go left
            while (valueAt(leftSide) != CLAY) {
                if (valueAt(leftSide) != WATER) {
                    setValue(leftSide, WATER);
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
                sourcesFound.add(leftSide);
                sources.add(leftSide);
            }

            if (foundSourceLeft || foundSourceRight || pos.i() == 0) {
                break;
            }
            pos = pos.moveUp();
        }
        waterSource.addAll(sourcesFound);
        print();
    }

    private static void populateFlow(Grid.Pos start) {
        Queue<Grid.Pos> flow = new ArrayDeque<>();
        flow.add(start);

        while (!flow.isEmpty()) {
            Grid.Pos current = flow.remove();
            current = current.moveDown();

            // Keep moving down until we can go horizontal
            while (current.isValid()
                    && valueAt(current.moveRight()) != WATER
                    && valueAt(current.moveLeft()) != WATER) {
                setValueCopy(current, WATER_FLOWING);
                current = current.moveDown();
            }

            if (!current.isValid()) {
                continue;
            }

            setValueCopy(current, WATER_FLOWING);
            // Try going horizontal -> right and left
            Grid.Pos left = current.moveLeft();
            Grid.Pos right = current.moveRight();

            while (left.isValid() && valueAt(left) == WATER) {
                setValueCopy(left, WATER_FLOWING);
                left = left.moveLeft();
            }

            if (left.isValid() && valueAt(left) != CLAY) {
                flow.add(left.moveRight());
            }

            while (right.isValid() && valueAt(right) == WATER) {
                setValueCopy(right, WATER_FLOWING);
                right = right.moveRight();
            }

            if (right.isValid() && valueAt(right) != CLAY) {
                flow.add(right.moveLeft());
            }
        }

        Queue<Grid.Pos> sq = new ArrayDeque<>();
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX - 1; j <= maxX + 1; j++) {
                if (scanCopy[i][j] == WATER_FLOWING && scanCopy[i - 1][j] == WATER) {
                    sq.add(Grid.Pos.of(i - 1, j));
                }
            }
        }

        while (!sq.isEmpty()) {
            Grid.Pos pos = sq.remove();
            Grid.Pos current = pos.copy();
            while (valueAtCopy(current) == WATER) {
                setValueCopy(current, WATER_FLOWING);
                current = current.moveUp();
            }

            Grid.Pos t = current.moveDown().copy();
            current = t.copy();
            // go sideways
            do {
                setValueCopy(current, WATER_FLOWING);
                current = current.moveLeft();
                if (valueAtCopy(current.moveUp()) == WATER) {
                    sq.add(current.moveUp());
                }
            } while (valueAtCopy(current) == WATER);

            current = t.copy();
            do {
                setValueCopy(current, WATER_FLOWING);
                current = current.moveRight();
                if (valueAtCopy(current.moveUp()) == WATER) {
                    sq.add(current.moveUp());
                }
            } while (valueAtCopy(current) == WATER);

            printCopy();
        }
    }

    private static int valueAt(Grid.Pos pos) {
        return scan[pos.i()][pos.j()];
    }

    private static int valueAtCopy(Grid.Pos pos) {
        return scanCopy[pos.i()][pos.j()];
    }

    private static int maxYWater = 0;

    private static void setValue(Grid.Pos pos, int value) {
        scan[pos.i()][pos.j()] = value;
        if (value == WATER) {
            maxYWater = maxYWater == maxY ? pos.i() : Math.max(maxYWater, pos.i() + 5);
        }
    }

    private static void setValueCopy(Grid.Pos pos, int value) {
        scanCopy[pos.i()][pos.j()] = value;
    }

    private static void print() {
        if (!ENABLE_PRINTING) {
            return;
        }
        for (int i = 0; i <= maxYWater; i++) {
            for (int j = minX - 1; j <= maxX + 1; j++) {
                if (scan[i][j] == WATER_SOURCE) {
                    System.out.print("+");
                } else if (scan[i][j] == WATER) {
                    System.out.print("~");
                } else if (scan[i][j] == CLAY) {
                    System.out.print("#");
                } else if (scan[i][j] == WATER_FLOWING) {
                    System.out.print("|");
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("Row# " + i);
            System.out.println();
        }
        System.out.println();
    }

    private static void printCopy() {
        if (!ENABLE_PRINTING) {
            return;
        }
        for (int i = 0; i <= maxYWater; i++) {
            for (int j = minX - 1; j <= maxX + 1; j++) {
                if (scanCopy[i][j] == WATER_SOURCE) {
                    System.out.print("+");
                } else if (scanCopy[i][j] == WATER) {
                    System.out.print("~");
                } else if (scanCopy[i][j] == CLAY) {
                    System.out.print("#");
                } else if (scanCopy[i][j] == WATER_FLOWING) {
                    System.out.print("|");
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("Row# " + i);
            System.out.println();
        }
        System.out.println();
    }
}
