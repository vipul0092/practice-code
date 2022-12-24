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
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 24/12/22
 * https://adventofcode.com/2022/day/24
 */
public class Solve24 {

    private static final String INPUT =
            "#.######\n" +
            "#>>.<^<#\n" +
            "#.<..<<#\n" +
            "#>v.><>#\n" +
            "#<^v^^>#\n" +
            "######.#";

    private static Set<Grid.Pos> walls;
    private static Map<Grid.Pos, List<Character>> cyclones;
    private static int maxRows;
    private static int maxCols;

    public static void solve() {
        parse(Inputs.INPUT_24);

        Grid.Pos start = Grid.Pos.of(0, 1);
        Grid.Pos target = Grid.Pos.of(maxRows - 1, maxCols - 2);

        int sum = move(start, target);
        System.out.println("Part 1: " + sum);

        sum += move(target, start);
        sum += move(start, target);
        System.out.println("Part 2: " + sum);
    }

    private static int move(Grid.Pos start, Grid.Pos target) {
        Set<Grid.Pos> currentPossible = new HashSet<>();
        currentPossible.add(start);
        int currentMinute = 0;
        while(!currentPossible.isEmpty()) {
            if (currentPossible.contains(target)) {
                break;
            }
            Map<Grid.Pos, List<Character>> movedCyclones = moveCyclones();
            Set<Grid.Pos> newPossible = new HashSet<>();
            for (var position : currentPossible) {
                Set<Grid.Pos> neighbors = getNeighbours(position, movedCyclones);
                newPossible.addAll(neighbors);
                if (!movedCyclones.containsKey(position)) {
                    newPossible.add(position);
                }
            }
            currentPossible = newPossible;
            cyclones = movedCyclones;
            currentMinute++;
        }
        return currentMinute;
    }

    private static Set<Grid.Pos> getNeighbours(Grid.Pos pos, Map<Grid.Pos, List<Character>> cyclones) {
        return Set.of(pos.moveRight(), pos.moveLeft(), pos.moveUp(), pos.moveDown()).stream()
                .filter(p -> p.isValid() && !cyclones.containsKey(p) && !walls.contains(p))
                .collect(Collectors.toSet());

    }

    private static Map<Grid.Pos, List<Character>> moveCyclones() {
        Map<Grid.Pos, List<Character>> moved = new HashMap<>();
        for (var entry : cyclones.entrySet()) {
            Grid.Pos pos = entry.getKey();
            List<Character> directions = entry.getValue();
            for (char direction : directions) {
                Grid.Pos newPos = moveCyclone(pos, direction);
                if (walls.contains(newPos)) {
                    newPos = reappear(pos, direction);
                }
                moved.putIfAbsent(newPos, new ArrayList<>());
                moved.get(newPos).add(direction);
            }
        }
        return moved;
    }

    private static Grid.Pos moveCyclone(Grid.Pos cyclone, char direction) {
        switch (direction) {
            case '>' : return cyclone.moveRight();
            case '<' : return cyclone.moveLeft();
            case 'v' : return cyclone.moveDown();
            case '^' : return cyclone.moveUp();
            default: throw new RuntimeException();
        }
    }

    private static Grid.Pos reappear(Grid.Pos cyclone, char direction) {
        switch (direction) {
            case '>' : return Grid.Pos.of(cyclone.i(), 1);
            case '<' : return Grid.Pos.of(cyclone.i(), maxCols - 2);
            case 'v' : return Grid.Pos.of(1, cyclone.j());
            case '^' : return Grid.Pos.of(maxRows - 2,  cyclone.j());
            default: throw new RuntimeException();
        }
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        cyclones = new HashMap<>();
        walls = new HashSet<>();
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            for (int j = 0; j < in.length(); j++) {
                char ch = in.charAt(j);
                var pos = Grid.Pos.of(i, j);
                if (ch == '#') {
                    walls.add(pos);
                } else if (ch != '.') {
                    cyclones.put(pos, List.of(ch));
                }
            }
        }
        maxRows = inputs.size();
        maxCols = inputs.get(0).length();
        Grid.setMaxRowsCols(maxRows, maxCols);
    }
}
