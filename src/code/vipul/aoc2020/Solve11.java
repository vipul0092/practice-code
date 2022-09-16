package code.vipul.aoc2020;

import code.vipul.aoc2019.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

        while(!states.contains(Arrays.deepHashCode(GRID))) {
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
        List<Character> neighbours = getNeighbourValues(currentGrid, pos);
        Character value = cellValue(currentGrid, pos);
        Character evaluatedPosition = null;
        if (value == 'L') {
            var hasOccupiedAdjacentSeats = neighbours.stream().anyMatch(n -> n == '#');
            if (!hasOccupiedAdjacentSeats) {
                evaluatedPosition = '#';
            } else {
                evaluatedPosition = value;
            }
        } else if (value == '#') {
            var occupiedAddjacentSeats = neighbours.stream().filter(n -> n == '#').count();
            if (occupiedAddjacentSeats >= 4) {
                evaluatedPosition = 'L';
            } else {
                evaluatedPosition = value;
            }
        }
        return evaluatedPosition;
    }

    private static List<Character> getNeighbourValues(Character[][] currentGrid, Grid.Pos pos) {
        List<Character> neighbourValues = new ArrayList<>();

        if (!pos.isAtTopBoundary()) { // i - 1 >= 0
            neighbourValues.add(cellValue(currentGrid, pos.moveUp()));
        }

        if (!pos.isAtBottomBoundary()) { // i + 1 < ROWS
            neighbourValues.add(cellValue(currentGrid, pos.moveDown()));
        }

        if (!pos.isAtLeftBoundary()) { // j - 1 >= 0
            neighbourValues.add(cellValue(currentGrid, pos.moveLeft()));
        }

        if (!pos.isAtRightBoundary()) { // j + 1 < COLS
            neighbourValues.add(cellValue(currentGrid, pos.moveRight()));
        }

        if (!pos.isAtTopBoundary() && !pos.isAtRightBoundary()) {
            neighbourValues.add(cellValue(currentGrid, pos.moveNE()));
        }

        if (!pos.isAtTopBoundary() && !pos.isAtLeftBoundary()) {
            neighbourValues.add(cellValue(currentGrid, pos.moveNW()));
        }

        if (!pos.isAtBottomBoundary() && !pos.isAtRightBoundary()) {
            neighbourValues.add(cellValue(currentGrid, pos.moveSE()));
        }

        if (!pos.isAtBottomBoundary() && !pos.isAtLeftBoundary()) {
            neighbourValues.add(cellValue(currentGrid, pos.moveSW()));
        }
        return neighbourValues;
    }

    private static Character cellValue(Character[][] currentGrid, Grid.Pos pos) {
        return currentGrid[pos.i()][pos.j()];
    }

    private static void display() {
        for (int i=0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(cellValue(GRID, Grid.Pos.of(i, j)));
            }
            System.out.println();
        }
        System.out.println();
    }
}
