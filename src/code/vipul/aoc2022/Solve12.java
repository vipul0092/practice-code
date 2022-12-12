package code.vipul.aoc2022;

import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 12/12/22
 * https://adventofcode.com/2022/day/12
 */
public class Solve12 {

    private static final String INPUT =
            "Sabqponm\n" +
                    "abcryxxl\n" +
                    "accszExk\n" +
                    "acctuvwj\n" +
                    "abdefghi";


    private static Map<Grid.Pos, Set<Grid.Pos>> adjacencyList;
    private static char[][] grid;

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_12.split("\n")).collect(Collectors.toList());

        grid = new char[inputs.size()][inputs.get(0).length()];
        adjacencyList = new HashMap<>();
        Grid.setMaxRowsCols(grid.length, grid[0].length);
        Set<Grid.Pos> as = new HashSet<>();
        Grid.Pos start = null;
        Grid.Pos end = null;

        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.get(i).length(); j++) {
                char ch = inputs.get(i).charAt(j);
                if (ch == 'S') {
                    start = Grid.Pos.of(i, j);
                    ch = 'a';
                } else if (ch == 'E') {
                    end = Grid.Pos.of(i, j);
                    ch = 'z';
                }

                if (ch == 'a') {
                    as.add(Grid.Pos.of(i, j));
                }
                grid[i][j] = ch;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Grid.Pos current = Grid.Pos.of(i, j);
                Set<Grid.Pos> neighbours =
                        Set.of(current.moveDown(), current.moveUp(), current.moveRight(), current.moveLeft())
                                .stream()
                                .filter(neighbor -> neighbor.isValid() && value(current) - value(neighbor) >= -1)
                                .collect(Collectors.toSet());
                adjacencyList.put(current, neighbours);
            }
        }

        // Part 1
        System.out.println(bfs(start, end));

        int min = Integer.MAX_VALUE;
        for (Grid.Pos a : as) {
            min = Math.min(bfs(a, end), min);
        }
        // Part 2
        System.out.println(min);
    }

    private static int bfs(Grid.Pos start, Grid.Pos end) {
        Map<Grid.Pos, Integer> distanceMap = new HashMap<>();
        Set<Grid.Pos> visited = new HashSet<>();
        Queue<Grid.Pos> queue = new ArrayDeque<>();

        visited.add(start);
        distanceMap.put(start, 0);
        queue.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            Grid.Pos current = queue.remove();
            for (Grid.Pos n : adjacencyList.get(current)) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    distanceMap.put(n, distanceMap.get(current) + 1);
                    queue.add(n);
                    if (n.equals(end)) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                break;
            }
        }
        return !found ? Integer.MAX_VALUE : distanceMap.get(end);
    }

    private static char value(Grid.Pos pos) {
        return grid[pos.i()][pos.j()];
    }
}
