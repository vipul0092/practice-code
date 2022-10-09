package code.vipul.aoc2018;

import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static code.vipul.aoc2018.Inputs2.DAY_20;

/**
 * https://adventofcode.com/2018/day/20
 */
public class Solve20 {

    private static final String INPUT = "ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN";

    private static final char E = 'E';
    private static final char S = 'S';
    private static final char N = 'N';
    private static final char W = 'W';

    private static Map<Grid.Pos, Set<Grid.Pos>> adjacencyList;
    private static final Grid.Pos INITIAL = Grid.Pos.of(500, 500);

    public static void solve() {
        adjacencyList = new LinkedHashMap<>();
        movements(DAY_20, 0, Set.of(INITIAL));
        print();

        // BFS
        Queue<Grid.Pos> roomsToVisit = new ArrayDeque<>();
        Queue<Integer> length = new ArrayDeque<>();
        roomsToVisit.add(INITIAL);
        length.add(0);
        Set<Grid.Pos> visited = new HashSet<>();

        int maxlen = -1;
        int count = 0;
        while (!roomsToVisit.isEmpty()) {
            Grid.Pos current = roomsToVisit.remove();
            int curlen = length.remove();
            if (curlen >= 1000) {
                count++;
            }
            maxlen = Math.max(maxlen, curlen);
            visited.add(current);
            Set<Grid.Pos> neighbours = adjacencyList.get(current).stream()
                    .filter(n -> !visited.contains(n))
                    .collect(Collectors.toSet());

            for (Grid.Pos n : neighbours) {
                roomsToVisit.add(n);
                length.add(curlen + 1);
            }
        }

        System.out.println("Answer: " + maxlen); // 3545
        System.out.println("Answer part 2: " + count); // 7838

    }

    private static PositionsResult movements(String path, int start, Set<Grid.Pos> initial) {
        Set<Grid.Pos> positions = new HashSet<>();

        Set<Grid.Pos> currentPositions = new HashSet<>(initial);
        for (int i = start; i < path.length(); i++) {
            char ch = path.charAt(i);
            if (ch == '(') {
                PositionsResult pos = movements(path, i + 1, currentPositions);
                currentPositions.addAll(pos.positions);
                i = pos.until;
            } else if (ch == ')') {
                positions.addAll(currentPositions);
                PositionsResult result = new PositionsResult(positions);
                result.until = i;
                return result;
            } else if (ch == '|') {
                positions.addAll(currentPositions);
                currentPositions = new HashSet<>(initial);
            } else {
                Set<Grid.Pos> newCurrent = new HashSet<>();
                for (Grid.Pos current : currentPositions) {
                    Grid.Pos newpos = move(ch, current);
                    link(newpos, current);
                    newCurrent.add(newpos);
                }
                currentPositions = newCurrent;
            }
        }
        positions.addAll(currentPositions);
        PositionsResult result = new PositionsResult(positions);
        result.until = path.length();
        return result;
    }

    private static void print() {
        int rows = (maxI - minI) + 1;
        int cols = (maxJ - minJ) + 1;

        for (int i = 0; i < (2 * rows) + 1; i++) {
            for (int j = 0; j < (2 * cols) + 1; j++) {
                if (i == 0 || j == 0 || i == 2 * rows || j == 2 * cols) {
                    System.out.print("#");
                } else if (i % 2 == 1 && j % 2 == 0) {
                    Grid.Pos p1 = Grid.Pos.of(minI + (i / 2), minJ + ((j - 1) / 2));
                    Grid.Pos p2 = p1.moveRight();
                    if (adjacencyList.containsKey(p1) && adjacencyList.get(p1).contains(p2)) {
                        System.out.print("|");
                    } else {
                        System.out.print("#");
                    }
                } else if (i % 2 == 0 && j % 2 == 1) {
                    Grid.Pos p1 = Grid.Pos.of(minI + ((i - 1) / 2), minJ + (j / 2));
                    Grid.Pos p2 = p1.moveDown();
                    if (adjacencyList.containsKey(p1) && adjacencyList.get(p1).contains(p2)) {
                        System.out.print("-");
                    } else {
                        System.out.print("#");
                    }
                } else if (i % 2 == 1) {
                    Grid.Pos p1 = Grid.Pos.of(minI + (i / 2), minJ + ((j - 1) / 2));
                    System.out.print(p1.equals(INITIAL) ? "X" : ".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }

    private static class PositionsResult {
        private Set<Grid.Pos> positions;
        private int until;

        PositionsResult(Set<Grid.Pos> p) {
            this.positions = p;
        }
    }

    private static int
            minI = Integer.MAX_VALUE, maxI = Integer.MIN_VALUE,
            minJ = Integer.MAX_VALUE, maxJ = Integer.MIN_VALUE;

    private static void link(Grid.Pos p1, Grid.Pos p2) {
        if (!adjacencyList.containsKey(p1)) {
            adjacencyList.putIfAbsent(p1, new LinkedHashSet<>());
        }
        if (!adjacencyList.containsKey(p2)) {
            adjacencyList.putIfAbsent(p2, new LinkedHashSet<>());
        }
        minI = Math.min(minI, p1.i());
        minI = Math.min(minI, p2.i());

        minJ = Math.min(minJ, p1.j());
        minJ = Math.min(minJ, p2.j());

        maxI = Math.max(maxI, p1.i());
        maxI = Math.max(maxI, p2.i());

        maxJ = Math.max(maxJ, p1.j());
        maxJ = Math.max(maxJ, p2.j());

        adjacencyList.get(p1).add(p2);
        adjacencyList.get(p2).add(p1);
    }

    private static Grid.Pos move(char direction, Grid.Pos pos) {
        switch (direction) {
            case E:
                return pos.moveRight();
            case W:
                return pos.moveLeft();
            case S:
                return pos.moveDown();
            case N:
                return pos.moveUp();
            default:
                throw new RuntimeException();
        }
    }
}
