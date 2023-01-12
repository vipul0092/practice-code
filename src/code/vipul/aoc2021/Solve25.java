package code.vipul.aoc2021;

import code.vipul.aoc2019.Grid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 12/01/23
 * https://adventofcode.com/2021/day/25
 */
public class Solve25 {

    private static final String INPUT =
                            "v...>>.vv>\n" +
                            ".vv>>.vv..\n" +
                            ">>.>v>...v\n" +
                            ">>v>>.>.v.\n" +
                            "v>v.vv.v..\n" +
                            ">.>>..v...\n" +
                            ".vv..>.>v.\n" +
                            "v.v..>>v.v\n" +
                            "....v..v.>";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_25.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        Map<Direction, Set<Grid.Pos>> grid = new HashMap<>();

        grid.put(Direction.EAST, new HashSet<>());
        grid.put(Direction.SOUTH, new HashSet<>());
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            for (int j = 0; j < in.length(); j++) {
                char ch = in.charAt(j);
                if (ch == '>') {
                    grid.get(Direction.EAST).add(Grid.Pos.of(i, j));
                } else if (ch == 'v') {
                    grid.get(Direction.SOUTH).add(Grid.Pos.of(i, j));
                }
            }
        }
        Grid.setMaxRowsCols(inputs.size(), inputs.get(0).length());

        int steps = 0;
        do {
            Map<Direction, Set<Grid.Pos>> newGrid = new HashMap<>();
            newGrid.put(Direction.EAST, new HashSet<>());
            newGrid.put(Direction.SOUTH, new HashSet<>());
            boolean anyMoved = false;

            // first move east ones
            for(var pos : grid.get(Direction.EAST)) {
                Grid.Pos newPos = pos.moveRight();
                if (!newPos.isValid()) {
                    newPos = Grid.Pos.of(pos.i(), 0);
                }
                if (!grid.get(Direction.EAST).contains(newPos) && !grid.get(Direction.SOUTH).contains(newPos)) {
                    anyMoved = true;
                    newGrid.get(Direction.EAST).add(newPos);
                } else {
                    newGrid.get(Direction.EAST).add(pos);
                }
            }

            // then move south ones
            for(var pos : grid.get(Direction.SOUTH)) {
                Grid.Pos newPos = pos.moveDown();
                if (!newPos.isValid()) {
                    newPos = Grid.Pos.of(0, pos.j());
                }
                if (!newGrid.get(Direction.EAST).contains(newPos) && !grid.get(Direction.SOUTH).contains(newPos)) {
                    anyMoved = true;
                    newGrid.get(Direction.SOUTH).add(newPos);
                } else {
                    newGrid.get(Direction.SOUTH).add(pos);
                }
            }
            steps++;
            grid = newGrid;
            if (!anyMoved) {
                break;
            }
        } while(true);

        System.out.println(steps); //579
    }

    private enum Direction {
        EAST,
        SOUTH
    }
}
