package code.vipul.aoc2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static code.vipul.aoc2017.inputs.Inputs.DAY_21;

/**
 * Created by vgaur created on 29/12/23
 */
public class Day21 {

    private static final char[][] START = new char[][]{
            {'.', '#', '.'},
            {'.', '.', '#'},
            {'#', '#', '#'}
    };
    private static String INPUT = """
            ../.# => ##./#../...
            .#./..#/### => #..#/..../..../#..#
            """;
    private static Map<List<Integer>, String> transformations;

    public static void solve() {
        INPUT = DAY_21;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        transformations = new HashMap<>();
        for (String line : lines) {
            populateTransforms(line);
        }

        System.out.println("Part 1:" + runIterationsAndGetCount(START, 5)); // 117
        System.out.println("Part 2:" + runIterationsAndGetCount(START, 18)); // 2026963
    }

    private static int runIterationsAndGetCount(char[][] grid, int iterations) {
        while (iterations-- > 0) {
            //System.out.println("Interations left: " + iterations);
            int len = grid.length;
            if (len % 2 == 0) {
                grid = transformGrid(grid, 3, 2);
            } else {
                grid = transformGrid(grid, 4, 3);
            }
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '#') count++;
            }
        }
        return count;
    }

    private static char[][] transformGrid(char[][] grid, int newBlock, int oldBlock) {
        int len = grid.length;
        int groupSize = len / oldBlock;
        char[][] newgrid = new char[groupSize * newBlock][groupSize * newBlock];
        for (int i = 0, ni = 0; i < len; i += oldBlock, ni += newBlock) {
            for (int j = 0, nj = 0; j < len; j += oldBlock, nj += newBlock) {
                var hash = getSubHash(grid, i, j, oldBlock);
                String output = transformations.get(hash);
                if (output == null) {
                    throw new RuntimeException("COULDN'T TRANSFORM");
                }
                var orows = output.split("/");
                for (int x = 0; x < orows.length; x++) {
                    for (int y = 0; y < orows[x].length(); y++) {
                        newgrid[ni + x][nj + y] = orows[x].charAt(y);
                    }
                }
            }
        }
        return newgrid;
    }

    private static void populateTransforms(String str) {
        var parts = str.split(" => ");
        var input = parts[0].split("/");
        char[][] grid = new char[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                grid[i][j] = input[i].charAt(j);
            }
        }
        transformations.put(getHash(grid), parts[1]);
        transformations.put(getHash(flip(grid)), parts[1]);
        grid = rotate90(grid);
        transformations.put(getHash(grid), parts[1]);
        transformations.put(getHash(flip(grid)), parts[1]);
        grid = rotate90(grid);
        transformations.put(getHash(grid), parts[1]);
        transformations.put(getHash(flip(grid)), parts[1]);
        grid = rotate90(grid);
        transformations.put(getHash(grid), parts[1]);
        transformations.put(getHash(flip(grid)), parts[1]);
    }

    static List<Integer> getHash(char[][] grid) {
        return getSubHash(grid, 0, 0, grid.length);
    }

    static List<Integer> getSubHash(char[][] grid, int x, int y, int size) {
        List<Integer> hash = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int num = 0;
            for (int j = size - 1, k = 0; j >= 0; j--, k++) {
                if (grid[x + i][y + j] == '#') {
                    num += (1 << k);
                }
            }
            hash.add(num);
        }
        return hash;
    }

    // flips the grid horizontally
    static char[][] flip(char[][] current) {
        int size = current.length;
        char[][] afterTransform = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                afterTransform[size - 1 - i][j] = current[i][j];
            }
        }
        return afterTransform;
    }

    // rotates clockwise
    static char[][] rotate90(char[][] current) {
        int size = current.length;
        char[][] afterTransform = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                afterTransform[i][size - 1 - j] = current[j][i];
            }
        }
        return afterTransform;
    }
}
