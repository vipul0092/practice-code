package code.vipul.aoc2021;

import code.vipul.aoc2019.Grid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 08/01/23
 * https://adventofcode.com/2021/day/20
 */
public class Solve20 {

    private static final String INPUT =
            "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#\n" +
                    "\n" +
                    "#..#.\n" +
                    "#....\n" +
                    "##..#\n" +
                    "..#..\n" +
                    "..###";

    private static int minx, maxx, miny, maxy;
    private static char[] enhance;
    private static Set<Grid.Pos> grid;

    public static void solve() {
        parse(Inputs.INPUT_20);
        solveInternal(2);
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_20);
        solveInternal(50);
    }

    public static void solveInternal(int maxTimes) {
        minx -= (2 * maxTimes);
        miny -= (2 * maxTimes);
        maxx += (2 * maxTimes);
        maxy += (2 * maxTimes);


        int times = 0;
        //display();
        while (times < maxTimes) {
            times++;
            Set<Grid.Pos> enhancedGrid = new HashSet<>();
            for (int i = minx; i <= maxx; i++) {
                String top = null;
                String mid = null;
                String bot = null;
                for (int j = miny; j <= maxy; j++) {
                    Grid.Pos current = Grid.Pos.of(i, j);

                    top = getStr(current.moveNW());
                    mid = getStr(current.moveLeft());
                    bot = getStr(current.moveSW());

                    int num = number(top, mid, bot);
                    char newVal = enhance[num];
                    if (newVal == '#') {
                        enhancedGrid.add(current);
                    }
                }
            }
            grid = enhancedGrid;
            // remove boundary because lit pixels on the boundary get turned off in the next round
            // and then turned on in the next round and so on
            if (times % 2 == 0) {
                for (int i = minx; i <= maxx; i++) {
                    grid.remove(Grid.Pos.of(i, miny));
                    grid.remove(Grid.Pos.of(i, maxy));
                }
                for (int j = miny; j <= maxy; j++) {
                    grid.remove(Grid.Pos.of(minx, j));
                    grid.remove(Grid.Pos.of(maxx, j));
                }
            }
            //display();
        }
        //display();
        System.out.println(grid.size());
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        enhance = inputs.get(0).toCharArray();
        minx = 2; maxx = Integer.MIN_VALUE; miny = 0; maxy = Integer.MIN_VALUE;
        grid = new HashSet<>();
        for (int i = 2; i < inputs.size(); i++) {
            String in = inputs.get(i);
            maxx = Math.max(maxx, i);
            for (int j = 0; j < in.length(); j++) {
                maxy = Math.max(maxy, j);
                if (in.charAt(j) == '#') {
                    grid.add(Grid.Pos.of(i, j));
                }
            }
        }
    }

    private static void display() {
        System.out.println();

        for (int i = minx - 2; i <= maxx + 2; i++) {
            for (int j = miny - 2; j <= maxy + 2; j++) {
                Grid.Pos current = Grid.Pos.of(i, j);
                if (grid.contains(current)) {
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }

    private static String getStr(Grid.Pos pos) {
        return String.valueOf(grid.contains(pos) ? '1' : '0') +
                (grid.contains(pos.moveRight()) ? '1' : '0') +
                (grid.contains(pos.moveRight().moveRight()) ? '1' : '0');
    }

    private static int number(String top, String mid, String bot) {
        int power = 0;
        int num = 0;
        for (int i = 2; i >= 0; i--) {
            num += (bot.charAt(i) == '1' ? 1 << power : 0);
            power++;
        }
        for (int i = 2; i >= 0; i--) {
            num += (mid.charAt(i) == '1' ? 1 << power : 0);
            power++;
        }
        for (int i = 2; i >= 0; i--) {
            num += (top.charAt(i) == '1' ? 1 << power : 0);
            power++;
        }
        return num;
    }
}
