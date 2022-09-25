package code.vipul.aoc2020;

import code.vipul.aoc2019.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static code.vipul.aoc2020.Inputs.INPUT_11;

/**
 * https://adventofcode.com/2020/day/11
 */
public class Solve11 {

    private static Character[][] GRID;
    private static int ROWS = -1;
    private static int COLUMNS = -1;

    private static final String INPUT = "L.LL.LL.LL\n" +
            "LLLLLLL.LL\n" +
            "L.L.L..L..\n" +
            "LLLL.LL.LL\n" +
            "L.LL.LL.LL\n" +
            "L.LLLLL.LL\n" +
            "..L.L.....\n" +
            "LLLLLLLLLL\n" +
            "L.LLLLLL.L\n" +
            "L.LLLLL.LL";

    public static void solve() {
        parseInput(INPUT_11);
        // display();
        var states = new HashSet<Integer>();
        var iterations = 0;

        while (!states.contains(Arrays.deepHashCode(GRID))) {
            states.add(Arrays.deepHashCode(GRID));
            var currentGrid = new Character[ROWS][COLUMNS];
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    if (GRID[i][j] == '.') {
                        currentGrid[i][j] = '.';
                        continue;
                    }
                    Grid.Pos pos = Grid.Pos.of(i, j);
                    var evaluatedValue = evaluatePosition(GRID, pos);
                    currentGrid[i][j] = evaluatedValue;
                }
            }
            GRID = currentGrid;
            iterations++;
            // display();
        }

        var occupiedSeats = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (GRID[i][j] == '#') {
                    occupiedSeats++;
                }
            }
        }
        System.out.println("Answer: " + occupiedSeats); // 2427
    }

    public static void solvePart2() {
        parseInput(INPUT_11);
        // display();
        var states = new HashSet<Integer>();
        var iterations = 0;

        while (!states.contains(Arrays.deepHashCode(GRID))) {
            states.add(Arrays.deepHashCode(GRID));
            var currentGrid = new Character[ROWS][COLUMNS];
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    if (GRID[i][j] == '.') {
                        currentGrid[i][j] = '.';
                        continue;
                    }
                    Grid.Pos pos = Grid.Pos.of(i, j);
                    var evaluatedValue = evaluatePositionWithDirection(GRID, pos);
                    currentGrid[i][j] = evaluatedValue;
                }
            }
            GRID = currentGrid;
            iterations++;
            // display();
        }

        var occupiedSeats = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (GRID[i][j] == '#') {
                    occupiedSeats++;
                }
            }
        }
        System.out.println("Answer: " + occupiedSeats); // 2427
    }

    private static void parseInput(String input) {
        String[] rows = input.split("\n");
        ROWS = rows.length;
        COLUMNS = rows[0].length();
        GRID = new Character[ROWS][COLUMNS];
        Grid.setMaxRowsCols(ROWS, COLUMNS);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                GRID[i][j] = rows[i].charAt(j);
            }
        }
    }

    private static Character evaluatePosition(Character[][] currentGrid, Grid.Pos pos) {
        int occupiedNeighbours = getDirectionallyOccupiedSeatCount(currentGrid, pos, false);
        Character value = cellValue(currentGrid, pos);
        Character evaluatedPosition = null;
        if (value == 'L') {
            if (occupiedNeighbours == 0) {
                evaluatedPosition = '#';
            } else {
                evaluatedPosition = value;
            }
        } else if (value == '#') {
            if (occupiedNeighbours >= 4) {
                evaluatedPosition = 'L';
            } else {
                evaluatedPosition = value;
            }
        }
        return evaluatedPosition;
    }

    private static Character evaluatePositionWithDirection(Character[][] currentGrid, Grid.Pos pos) {
        int occupiedNeighbours = getDirectionallyOccupiedSeatCount(currentGrid, pos, true);
        Character value = cellValue(currentGrid, pos);
        Character evaluatedPosition = null;
        if (value == 'L') {
            if (occupiedNeighbours == 0) {
                evaluatedPosition = '#';
            } else {
                evaluatedPosition = value;
            }
        } else if (value == '#') {
            if (occupiedNeighbours >= 5) {
                evaluatedPosition = 'L';
            } else {
                evaluatedPosition = value;
            }
        }
        return evaluatedPosition;
    }

    private static int getDirectionallyOccupiedSeatCount(Character[][] currentGrid, Grid.Pos pos, boolean recurse) {
        int count = 0;
        count += (didFindAnOccupiedSeat(currentGrid, pos, p -> p.moveUp(), recurse) ? 1 : 0);
        count += (didFindAnOccupiedSeat(currentGrid, pos, p -> p.moveDown(), recurse) ? 1 : 0);
        count += (didFindAnOccupiedSeat(currentGrid, pos, p -> p.moveLeft(), recurse) ? 1 : 0);
        count += (didFindAnOccupiedSeat(currentGrid, pos, p -> p.moveRight(), recurse) ? 1 : 0);
        count += (didFindAnOccupiedSeat(currentGrid, pos, p -> p.moveNE(), recurse) ? 1 : 0);
        count += (didFindAnOccupiedSeat(currentGrid, pos, p -> p.moveNW(), recurse) ? 1 : 0);
        count += (didFindAnOccupiedSeat(currentGrid, pos, p -> p.moveSE(), recurse) ? 1 : 0);
        count += (didFindAnOccupiedSeat(currentGrid, pos, p -> p.moveSW(), recurse) ? 1 : 0);

        return count;
    }

    private static boolean didFindAnOccupiedSeat(Character[][] currentGrid, Grid.Pos startingPos,
                                                 Function<Grid.Pos, Grid.Pos> nextPosGetter,
                                                 boolean recurse) {
        Grid.Pos pos = nextPosGetter.apply(startingPos);
        while (pos.isValid() && cellValue(currentGrid, pos) == '.' && recurse) {
            pos = nextPosGetter.apply(pos);
        }

        return pos.isValid() && cellValue(currentGrid, pos) == '#';
    }

    private static Character cellValue(Character[][] currentGrid, Grid.Pos pos) {
        return currentGrid[pos.i()][pos.j()];
    }

    private static void display() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(cellValue(GRID, Grid.Pos.of(i, j)));
            }
            System.out.println();
        }
        System.out.println();
    }
}
