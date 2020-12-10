package code.vipul.aoc2020;

import code.vipul.aoc2019.Grid;

import java.util.Arrays;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs.INPUT_3;

/**
 * https://adventofcode.com/2020/day/3
 */
public class Solve3 {

    static int[][] grid;
    static int maxCols = -1;
    static int maxRows = -1;

    public static void solve() {
        parse();
        int count = treeCountOnSlopeTraversal(3, 1);
        System.out.println("Answer: " + count); //148
    }

    public static void solvePart2() {
        parse();
        int count = treeCountOnSlopeTraversal(1, 1)
                * treeCountOnSlopeTraversal(3, 1)
                * treeCountOnSlopeTraversal(5, 1)
                * treeCountOnSlopeTraversal(7, 1)
                * treeCountOnSlopeTraversal(1, 2);
        System.out.println("Answer: " + count); //727923200
    }

    private static int treeCountOnSlopeTraversal(int right, int down) {
        Grid.Pos currentPos = adjustPos(Grid.Pos.of(0, 0), right, down);

        int count = 0;
        while (currentPos.i() < maxRows) {
            boolean isTree = getGridValue(currentPos) == 1;
            if (isTree) {
                count++;
            }
            currentPos = adjustPos(currentPos, right, down);
        }
        return count;
    }

    private static void parse() {
        var rows = Arrays.stream(INPUT_3.split("\n")).collect(Collectors.toList());
        maxCols = rows.get(0).length();
        maxRows = rows.size();

        grid = new int[maxRows][maxCols];

        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                grid[i][j] = rows.get(i).charAt(j) == '.' ? 0 : 1;
            }
        }
    }

    private static Grid.Pos adjustPos(Grid.Pos currentPos, int right, int down) {
        return Grid.Pos.of(currentPos.i() + down, currentPos.j() + right);
    }

    private static int getGridValue(Grid.Pos pos) {
        int jPos = pos.j();
        if (jPos >= maxCols) {
            jPos %= maxCols;
        }
        return grid[pos.i()][jPos];
    }
}