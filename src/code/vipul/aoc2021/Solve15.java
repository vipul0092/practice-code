package code.vipul.aoc2021;

import code.vipul.Pair;
import code.vipul.aoc2019.Grid;

import java.util.*;
import java.util.stream.Collectors;

import static code.vipul.aoc2019.Grid.COLS;
import static code.vipul.aoc2019.Grid.ROWS;
import static java.util.stream.Collectors.toMap;

/**
 * Created by vgaur created on 31/12/22
 * https://adventofcode.com/2021/day/15
 */
public class Solve15 {

    private static final String INPUT =
            "1163751742\n" +
                    "1381373672\n" +
                    "2136511328\n" +
                    "3694931569\n" +
                    "7463417111\n" +
                    "1319128137\n" +
                    "1359912421\n" +
                    "3125421639\n" +
                    "1293138521\n" +
                    "2311944581";

    private static int[][] grid;
    private static Grid.Pos start;
    private static Grid.Pos end;

    private static final int INF = Integer.MAX_VALUE;
    private static Map<Grid.Pos, Map<Grid.Pos, Integer>> adjacencyListWithWeights;
    private static Map<Grid.Pos, Integer> distanceMap;
    private static int lastCellValue;

    public static void solve() {
        parse(Inputs.INPUT_15, 1);
        int length = dijkstra(start, end);
        System.out.println("Answer: " + length); //698
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_15, 5);
        final long startTime = System.nanoTime();
        int length = dijkstra(start, end);
        final long duration = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Runtime(in ms): " + duration);

        System.out.println("Answer: " + length); //3022
    }

    private static int dijkstra(Grid.Pos start, Grid.Pos end) {
        int length = -1;

        Grid.Pos current = start;

        PriorityQueue<Pair<Integer, Grid.Pos>> minheap = new PriorityQueue<>(Comparator.comparingInt(Pair::left));
        minheap.add(Pair.of(distanceMap.get(current), start));
        boolean[][] visited = new boolean[ROWS+1][COLS+1];

        while (!minheap.isEmpty()) {
            Pair<Integer, Grid.Pos> top = minheap.remove();
            current = top.right();
            visited[current.i()][current.j()] = true;
            int currentDistance = top.left();

            if (current.equals(end)) {
                length = currentDistance + lastCellValue - grid[start.i()][start.j()];
                break;
            }

            Map<Grid.Pos, Integer> neighboursWithLengths = adjacencyListWithWeights.get(current);

            for (Map.Entry<Grid.Pos, Integer> e : neighboursWithLengths.entrySet()) {
                if (visited[e.getKey().i()][e.getKey().j()]) {
                    continue;
                }
                int neighbourNewDistance = currentDistance + e.getValue();
                int neighbourCurrentDistance = distanceMap.get(e.getKey());

                // assign the distance to the neighbour
                // and update the distance in unvisited map
                if (neighbourNewDistance < neighbourCurrentDistance) {
                    distanceMap.put(e.getKey(), neighbourNewDistance);
                    minheap.add(Pair.of(neighbourNewDistance, e.getKey()));
                }
            }
        }
        return length;
    }

    private static void parse(String input, int repeatTileSize) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        adjacencyListWithWeights = new HashMap<>();
        distanceMap = new HashMap<>();
        grid = new int[inputs.size()][inputs.get(0).length()];
        int tileSize = inputs.size();
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            for (int j = 0; j < in.length(); j++) {
                int num = in.charAt(j) - '0';
                grid[i][j] = num;
            }
        }
        start = Grid.Pos.of(0, 0);
        end = Grid.Pos.of((inputs.size() * repeatTileSize) - 1, (inputs.get(0).length() * repeatTileSize) - 1);
        Grid.setMaxRowsCols(inputs.size() * repeatTileSize, inputs.get(0).length() * repeatTileSize);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int origLen = grid[i][j];
                for (int ti = 0; ti < repeatTileSize; ti++) {
                    int startLen = origLen;
                    for (int tj = 0; tj < repeatTileSize; tj++) {
                        Grid.Pos pos = Grid.Pos.of(i + (tileSize * ti), j + (tileSize * tj));
                        int finalStartLen = startLen;
                        Map<Grid.Pos, Integer> neighbours =
                                Set.of(pos.moveRight(), pos.moveLeft(), pos.moveUp(), pos.moveDown()).stream()
                                        .filter(p -> p.isValid())
                                        .collect(toMap(p -> p, p -> finalStartLen));
                        adjacencyListWithWeights.put(pos, neighbours);
                        distanceMap.put(pos, INF);
                        if (pos.equals(end)) {
                            lastCellValue = finalStartLen;
                        }
                        startLen++;
                        startLen = startLen == 10 ? 1 : startLen;
                    }
                    origLen++;
                    origLen = origLen == 10 ? 1 : origLen;
                }
            }
        }
        distanceMap.put(start, 0);
    }
}
