package code.vipul.aoc2023;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static code.vipul.aoc2023.inputs.Inputs.DAY_14;

/**
 * Created by vgaur created on 14/12/23
 */
public class Day14 {

    private static final int MAX_CYCLES = 1000000000;
    static Map<String, Integer> mapping = new HashMap<>();
    private static int DIMENSION = 0;
    private static String INPUT = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
            """;

    public static void solve() {
        INPUT = DAY_14;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        DIMENSION = lines.size();
        char[][] grid = new char[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }

        int sum1 = getNorthLoad(tilt(grid, 1));
        System.out.println("Part 1: " + sum1); //107053

        // ---------- Part 2 Begins ---------

        mapping.put(getStr(grid), 0);
        int subtract = 0;
        int cycle = -1;
        for (int i = 0; i < MAX_CYCLES; i++) {
            char[][] newgrid = doCycle(grid);
            String hash = getStr(newgrid);
            grid = newgrid;

            if (mapping.containsKey(hash)) {
                subtract = mapping.get(hash) - 1;
                cycle = i + 1 - mapping.get(hash);
                System.out.println("Found cycle at iteration: " + (i + 1));
                break;
            } else {
                mapping.put(hash, i + 1);
            }
        }

        int todo = ((MAX_CYCLES - subtract) % cycle) - 1;
        while (todo-- > 0) {
            grid = doCycle(grid);
        }
        int sum2 = getNorthLoad(grid);
        System.out.println("Part 2: " + sum2); //88371
    }

    static char[][] doCycle(char[][] grid) {
        grid = tilt(grid, 1);
        grid = tilt(grid, 2);
        grid = tilt(grid, 3);
        grid = tilt(grid, 4);
        return grid;
    }

    // 1 - north, 2 - west, 3 - south, 4 - east
    static char[][] tilt(char[][] grid, int direction) {
        Map<Integer, Stack<Rock>> stacks = new HashMap<>();
        boolean vertical = direction == 1 || direction == 3;
        boolean north = direction == 1, west = direction == 2;

        for (int i = 0; i < DIMENSION; i++) {
            stacks.put(i, new Stack<>());
            for (int j = 0, k = DIMENSION - 1; j < DIMENSION; j++, k--) {
                int idx = north || west ? j : k;
                char ch = vertical ? grid[idx][i] : grid[i][idx];
                if (ch == '#') {
                    stacks.get(i).push(new Rock(ch, idx));
                } else if (ch == 'O') {
                    if (stacks.get(i).isEmpty()) {
                        stacks.get(i).push(new Rock(ch, north || west ? 0 : DIMENSION - 1));
                    } else {
                        var top = stacks.get(i).peek().loc;
                        stacks.get(i).push(new Rock(ch, north || west ? top + 1 : top - 1));
                    }
                }
            }
        }

        char[][] newgrid = new char[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            var stack = stacks.get(i);
            while (!stack.isEmpty()) {
                var rock = stack.pop();
                if (vertical) {
                    newgrid[rock.loc][i] = rock.c;
                } else {
                    newgrid[i][rock.loc] = rock.c;
                }
            }
            for (int j = 0; j < DIMENSION; j++) {
                char ch = vertical ? newgrid[i][j] : newgrid[j][i];
                if (ch != 'O' && ch != '#') {
                    if (vertical) {
                        newgrid[i][j] = '.';
                    } else {
                        newgrid[j][i] = '.';
                    }
                }
            }
        }
        return newgrid;
    }

    private static int getNorthLoad(char[][] grid) {
        int load = 0;
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (grid[i][j] == 'O') {
                    load += (DIMENSION - i);
                }
            }
        }
        return load;
    }

    static String getStr(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sb.append(grid[i][j]);
            }
        }
        return sb.toString();
    }

    record Rock(char c, int loc) {
    }
}
