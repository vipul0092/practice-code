package code.vipul.aoc2017;

import code.vipul.leetcode.graphs.DisjointSet;

import java.util.*;

import static code.vipul.aoc2017.Day10.getHash;
import static code.vipul.aoc2017.inputs.Inputs.DAY_14;

/**
 * Created by vgaur created on 28/12/23
 */
public class Day14 {

    private static String INPUT = """
            flqrgnkx
            """;
    record Point(int i, int j){}

    private static final int[][] DIFFS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static Map<Point, Integer> ids;
    private static int id = 0;
    private static DisjointSet dsu;

    public static void solve() {
        INPUT = DAY_14;
        String input = Arrays.stream(INPUT.split("\n")).toList().get(0);

        int count = 0;
        char[][] grid = new char[128][128];

        for (int i = 0; i < 128; i++) {
            String hash = getHash(input + "-" + i);
            int j = 0;
            for (char ch : hash.toCharArray()) {
                int n = Integer.parseInt(String.valueOf(ch), 16);
                count += countSetBits(n, i, j, grid);
                j+=4;
            }
        }

        ids = new HashMap<>();
        dsu = new DisjointSet(count);

        // Find connected components through Disjoint set Union
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 128; j++) {
                if (grid[i][j] != '#') continue;
                int curid = getOrCreateId(new Point(i, j));

                for (int[] d : DIFFS) {
                    int ii = i + d[0], jj = j + d[1];
                    if (ii >= 0 && jj >= 0 && ii < 128 && jj < 128 && grid[ii][jj] == '#') {
                        dsu.union(getOrCreateId(new Point(ii ,jj)), curid);
                    }
                }
            }
        }

        Set<Integer> regions = new HashSet<>();
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 128; j++) {
                if (grid[i][j] != '#') continue;
                regions.add(dsu.find(getOrCreateId(new Point(i, j))));
            }
        }

        System.out.println("Part 1: " + count);
        System.out.println("Part 2: " + regions.size());
    }

    private static int countSetBits(int n, int i, int j, char[][] grid) {
        int count = 0;
        for (int k = 3, a = 0; k >= 0; k--, a++) {
            if ((n & (1<<k)) != 0) {
                grid[i][j+a] = '#';
                count++;
            }
        }
        return count;
    }

    private static int getOrCreateId(Point p) {
        if (!ids.containsKey(p)) {
            ids.put(p, id++);
            dsu.add(ids.get(p));
        }
        return ids.get(p);
    }
}
