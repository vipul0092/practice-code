package code.vipul.aoc2021;

import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 30/12/22
 * https://adventofcode.com/2021/day/11
 */
public class Solve11 {

    private static final String INPUT = "5483143223\n" +
            "2745854711\n" +
            "5264556173\n" +
            "6141336146\n" +
            "6357385478\n" +
            "4167524645\n" +
            "2176841721\n" +
            "6882881134\n" +
            "4846848554\n" +
            "5283751526";

    private static int[][] grid;

    public static void solve() {
        parse(Inputs.INPUT_11);
        System.out.println(solveInternal(100)); //1647
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_11);
        System.out.println(solveInternal(500)); //348
    }

    private static int solveInternal(int maxSteps) {
        int steps = 0;
        int answer = 0;
        int totalCells = grid.length * grid[0].length;
        while(steps++ < maxSteps) {
            Queue<Grid.Pos> flashQ = new ArrayDeque<>();
            Set<Grid.Pos> flashers = new HashSet<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j]++;
                    if (grid[i][j] == 10) {
                        flashQ.add(Grid.Pos.of(i, j));
                        flashers.add(Grid.Pos.of(i, j));
                    }
                }
            }

            while(!flashQ.isEmpty()) {
                answer++;
                Grid.Pos pos = flashQ.remove();
                Set<Grid.Pos> neighbours = Set.of(pos.moveRight(), pos.moveLeft(), pos.moveUp(), pos.moveDown(),
                        pos.moveNE(), pos.moveNW(), pos.moveSW(), pos.moveSE()).stream()
                        .filter(p -> p.isValid() && !flashers.contains(p))
                        .collect(Collectors.toSet());

                for (Grid.Pos n : neighbours) {
                    int val = grid[n.i()][n.j()];
                    if (val == 10 && !flashers.contains(n)) {
                        flashQ.add(n);
                        flashers.add(n);
                    } else if (val < 10) {
                        grid[n.i()][n.j()]++;
                        if (val == 9) { // it'll flash
                            flashQ.add(n);
                            flashers.add(n);
                        }
                    }
                }
            }

            for (var pos : flashers) {
                grid[pos.i()][pos.j()] = 0;
            }
            if (flashers.size() == totalCells) {
                answer = steps;
                break;
            }
        }
        return answer;
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        Grid.setMaxRowsCols(inputs.size(), inputs.get(0).length());
        grid = new int[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            for (int j = 0; j < in.length(); j++) {
                int num = in.charAt(j) - '0';
                grid[i][j] = num;
            }
        }
    }
}
