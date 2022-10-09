package code.vipul.aoc2018;

import code.vipul.aoc2019.Grid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static code.vipul.aoc2018.Inputs2.DAY_18;

/**
 * https://adventofcode.com/2018/day/18
 */
public class Solve18 {

    private static final Character OPEN = '.';
    private static final Character LUMBER = '#';
    private static final Character TREES = '|';

    private static final String INPUT = ".#.#...|#.\n" +
            ".....#|##|\n" +
            ".|..|...#.\n" +
            "..|#.....#\n" +
            "#.#|||#|#|\n" +
            "...#.||...\n" +
            ".|....|...\n" +
            "||...#|.#|\n" +
            "|.||||..|.\n" +
            "...#.|..|.";

    private static char[][] area;

    public static void solve() {
        initialize(DAY_18);
        int minutes = 10;
        while (minutes-- > 0) {
            transform();
        }

        int lumber = 0;
        int trees = 0;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                if (area[i][j] == TREES) {
                    trees++;
                } else if (area[i][j] == LUMBER) {
                    lumber++;
                }
            }
        }

        System.out.println("Answer: " + (lumber * trees));
    }

    public static void solvePart2() {
        initialize(DAY_18);
        int minutes = 1000000000;

        Map<Integer, Integer> states = new LinkedHashMap<>();

        int counter = 0;
        int beforeFirstCount = 0;
        int cycleCount = 0;
        while (counter < minutes) {
            // print();
            int hash = Arrays.deepHashCode(area);
            if (states.containsKey(hash)) {
                beforeFirstCount = states.get(hash);
                cycleCount = counter - beforeFirstCount;
                System.out.println("Found repeated state after looping " + counter + " times.");
                break;
            }
            states.put(hash, counter);
            transform();
            counter++;
        }


        int newCounter = (minutes - beforeFirstCount) % cycleCount;
        System.out.println("Looping for " + newCounter + " more times.");

        while (newCounter-- > 0) {
            transform();
        }

        int lumber = 0;
        int trees = 0;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                if (area[i][j] == TREES) {
                    trees++;
                } else if (area[i][j] == LUMBER) {
                    lumber++;
                }
            }
        }

        System.out.println("Answer: " + (lumber * trees)); //189720
    }

    private static void transform() {
        char[][] copy = new char[area.length][area[0].length];
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                copy[i][j] = area[i][j];
                Grid.Pos pos = Grid.Pos.of(i, j);
                int current = value(pos);
                Set<Grid.Pos> neighbours = neighbors(pos);

                long treecount = neighbours.stream().filter(n -> value(n) == TREES).count();
                long lumbercount = neighbours.stream().filter(n -> value(n) == LUMBER).count();

                if (current == OPEN && treecount >= 3) {
                    copy[i][j] = TREES;
                } else if (current == TREES && lumbercount >= 3) {
                    copy[i][j] = LUMBER;
                } else if (current == LUMBER && !(treecount >= 1 && lumbercount >= 1)) {
                    copy[i][j] = OPEN;
                }
            }
        }
        area = copy;
    }

    private static void initialize(String in) {
        String[] lines = in.split("\n");
        area = new char[lines.length][lines[0].length()];

        Grid.setMaxRowsCols(area.length, area[0].length);
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                area[i][j] = lines[i].charAt(j);
            }
        }
    }

    private static int value(Grid.Pos pos) {
        return area[pos.i()][pos.j()];
    }

    private static Map<Grid.Pos, Set<Grid.Pos>> neighboursMap = new HashMap<>();

    private static Set<Grid.Pos> neighbors(Grid.Pos pos) {
        if (neighboursMap.containsKey(pos)) {
            return neighboursMap.get(pos);
        }
        var n = Set.of(pos.moveUp(), pos.moveDown(), pos.moveRight(), pos.moveLeft(), pos.moveNE(), pos.moveNW(),
                pos.moveSE(), pos.moveSW()).stream().filter(p -> p.isValid()).collect(Collectors.toSet());
        neighboursMap.put(pos, n);
        return n;
    }

    private static void print() {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                System.out.print(area[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
