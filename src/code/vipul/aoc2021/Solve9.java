package code.vipul.aoc2021;

import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 30/12/22
 * https://adventofcode.com/2021/day/9
 */
public class Solve9 {

    private static final String INPUT = "2199943210\n" +
            "3987894921\n" +
            "9856789892\n" +
            "8767896789\n" +
            "9899965678";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_9.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        Grid.setMaxRowsCols(inputs.size(), inputs.get(0).length());

        int[][] grid = new int[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            for (int j = 0; j < in.length(); j++) {
                int num = in.charAt(j) - '0';
                grid[i][j] = num;
            }
        }

        int sum = 0;
        Set<Grid.Pos> lowPoints = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int height = grid[i][j];
                Grid.Pos pos = Grid.Pos.of(i, j);
                boolean isLower = Set.of(pos.moveRight(), pos.moveLeft(), pos.moveUp(), pos.moveDown()).stream()
                        .filter(p -> p.isValid())
                        .allMatch(p -> grid[p.i()][p.j()] > height);
                if (isLower) {
                    lowPoints.add(pos);
                    sum += (height + 1);
                }
            }
        }
        System.out.println("Part 1: " + sum); //591

        Map<Grid.Pos, Integer> basins = new LinkedHashMap<>();
        for (Grid.Pos start : lowPoints) {
            Queue<Grid.Pos> q = new ArrayDeque<>();
            q.add(start);
            Set<Grid.Pos> visited = new HashSet<>();
            visited.add(start);
            while(!q.isEmpty()) {
                Grid.Pos current = q.remove();
                int currentHeight = grid[current.i()][current.j()];
                Set<Grid.Pos> neighbours =
                        Set.of(current.moveRight(), current.moveLeft(), current.moveUp(), current.moveDown()).stream()
                        .filter(p -> !visited.contains(p)
                                && p.isValid()
                                && grid[p.i()][p.j()] != 9
                                && grid[p.i()][p.j()] > currentHeight)
                        .collect(Collectors.toSet());
                q.addAll(neighbours);
                visited.addAll(neighbours);
            }
            basins.put(start, visited.size());
        }

        var basinLengths = basins.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println("Part 2: " + (basinLengths.get(0) * basinLengths.get(1) * basinLengths.get(2))); //1113424
    }
}
