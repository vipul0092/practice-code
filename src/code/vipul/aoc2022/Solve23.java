package code.vipul.aoc2022;

import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 23/12/22
 */
public class Solve23 {

    private static final String INPUT =
                    "....#..\n" +
                    "..###.#\n" +
                    "#...#.#\n" +
                    ".#...##\n" +
                    "#.###..\n" +
                    "##.#.##\n" +
                    ".#..#..";

    private static Set<Grid.Pos> grid;
    private static final Map<Integer, Function<Grid.Pos, Grid.Pos>> movements;
    private static Map<Integer, List<Function<Grid.Pos, Grid.Pos>>> orderedLists;

    static {
        movements = new HashMap<>();
        movements.put(0, (pos) -> tryMove(Set.of(pos.moveUp(), pos.moveNE(), pos.moveNW()), () -> pos.moveUp()));
        movements.put(1, (pos) -> tryMove(Set.of(pos.moveDown(), pos.moveSE(), pos.moveSW()), () -> pos.moveDown()));
        movements.put(2, (pos) -> tryMove(Set.of(pos.moveLeft(), pos.moveNW(), pos.moveSW()), () -> pos.moveLeft()));
        movements.put(3, (pos) -> tryMove(Set.of(pos.moveRight(), pos.moveNE(), pos.moveSE()), () -> pos.moveRight()));
    }


    public static void solve() {
        parse(Inputs.INPUT_23);
        int rounds = 10;
        playRounds(rounds);
        System.out.println(getEmptyCount());
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_23);
        int rounds = 1500;
        int roundsPlayed = playRounds(rounds);
        System.out.println(roundsPlayed);
    }

    private static int playRounds(int rounds) {
        int r = -1;
        for (int i = 0; i < rounds; i++) {
            int id = i % movements.size();
            Set<Grid.Pos> newGrid = new HashSet<>();

            Map<Grid.Pos, Integer> proposedCounts = new HashMap<>();
            Map<Grid.Pos, Grid.Pos> proposalPerElf = new HashMap<>();
            List<Function<Grid.Pos, Grid.Pos>> order = orderedTries(id);
            boolean atLeastOneElfMoved = false;
            for (Grid.Pos elf : grid) {
                boolean isAtLeastOneElfFound = isAtLeastOneElfNearby(elf);
                if (!isAtLeastOneElfFound) { // this elf will not move
                    newGrid.add(elf);
                } else {
                    Grid.Pos finalProposed = null;
                    for (Function<Grid.Pos, Grid.Pos> proposalFunc : order) {
                        Grid.Pos proposed = proposalFunc.apply(elf);
                        if (proposed != null) {
                            finalProposed = proposed;
                            break;
                        }
                    }

                    if (finalProposed != null) {
                        proposedCounts.putIfAbsent(finalProposed, 0);
                        proposedCounts.put(finalProposed, proposedCounts.get(finalProposed) + 1);
                        proposalPerElf.put(elf, finalProposed);
                    } else {
                        // The elf doesnt move
                        newGrid.add(elf);
                    }
                }
            }

            for (var entry : proposalPerElf.entrySet()) {
                Grid.Pos elf = entry.getKey();
                Grid.Pos proposed = entry.getValue();
                if (proposedCounts.get(proposed) > 1) { // this elf wont move
                    newGrid.add(elf);
                } else { // move the elf
                    atLeastOneElfMoved = true;
                    newGrid.add(proposed);
                }
            }

            // If no elf moved in this round, stop the rounds evaluation cycle and return
            if (!atLeastOneElfMoved) {
                r = i;
                break;
            }
            grid = newGrid;
        }
        return r + 1;
    }

    private static Grid.Pos tryMove(Set<Grid.Pos> checks, Supplier<Grid.Pos> validMove) {
        boolean canMoveAdjacent = checks.stream().noneMatch(p -> grid.contains(p));
        if (!canMoveAdjacent) {
            return null;
        }
        return validMove.get();
    }

    private static List<Function<Grid.Pos, Grid.Pos>> orderedTries(int start) {
        if (orderedLists.containsKey(start)) {
            orderedLists.get(start);
        }
        var order = new ArrayList<Function<Grid.Pos, Grid.Pos>>();

        order.add(movements.get(start % movements.size()));
        order.add(movements.get((start + 1) % movements.size()));
        order.add(movements.get((start + 2) % movements.size()));
        order.add(movements.get((start + 3) % movements.size()));
        orderedLists.put(start, order);
        return order;
    }

    private static boolean isAtLeastOneElfNearby(Grid.Pos pos) {
        return getNeighbours(pos).stream().anyMatch(p -> grid.contains(p));
    }

    private static Set<Grid.Pos> getNeighbours(Grid.Pos pos) {
        return Set.of(pos.moveRight(), pos.moveLeft(), pos.moveDown(), pos.moveUp(),
                pos.moveNE(), pos.moveNW(), pos.moveSW(), pos.moveSE());
    }

    private static int getEmptyCount() {
        int countEmpty = 0;

        AtomicInteger mini =  new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger maxi = new AtomicInteger(Integer.MIN_VALUE);
        AtomicInteger minj = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger maxj = new AtomicInteger(Integer.MIN_VALUE);

        grid.forEach(p -> {
            mini.set(Math.min(mini.get(), p.i()));
            minj.set(Math.min(minj.get(), p.j()));
            maxj.set(Math.max(maxj.get(), p.j()));
            maxi.set(Math.max(maxi.get(), p.i()));
        });

        for (int i = mini.get(); i <= maxi.get(); i++) {
            for (int j = minj.get(); j <= maxj.get(); j++) {
                Grid.Pos pos = Grid.Pos.of(i,j);
                if (!grid.contains(pos)) {
                    countEmpty++;
                }
            }
        }
        return countEmpty;
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        grid = new HashSet<>();
        orderedLists = new HashMap<>();
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            for (int j = 0; j < in.length(); j++) {
                char ch = in.charAt(j);
                if (ch == '#') {
                    Grid.Pos pos = Grid.Pos.of(i, j);
                    grid.add(pos);
                }
            }
        }
    }
}
