package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 08/12/22
 */
public class Solve8 {

    private static final int LIMIT = 101;
    private static final String INPUT = "30373\n" +
            "25512\n" +
            "65332\n" +
            "33549\n" +
            "35390";

    private static int getUniquePos(int i, int j) {
        return (i * LIMIT) + j;
    }

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_8.split("\n")).collect(Collectors.toList());
        // List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int[][] grid = new int[inputs.size()][inputs.size()];
        int lastIndex = grid.length - 1;

        int i = 0;
        for (String in : inputs) {
            int j = 0;
            for (Character ch : in.toCharArray()) {
                grid[i][j] = ch - 48;
                j++;
            }
            i++;
        }

        int part1 = (grid.length * 2) + ((grid.length - 2) * 2);

        Set<Integer> internal = new HashSet<>();
        // row wise iteration
        for (i = 1; i < lastIndex; i++) {
            int min1 = grid[i][0];
            int min2 = grid[i][lastIndex];

            // j -> row start to end, k -> row end to start
            for (int j = 1, k = lastIndex - 1; j < lastIndex; j++, k--) {
                int pos1 = getUniquePos(i, j);
                if (grid[i][j] > min1) {
                    internal.add(pos1);
                    min1 = grid[i][j];
                }

                int pos2 = getUniquePos(i, k);
                if (grid[i][k] > min2) {
                    internal.add(pos2);
                    min2 = grid[i][k];
                }
            }
        }

        // column wise iteration
        for (i = 1; i < lastIndex; i++) {
            int min1 = grid[0][i];
            int min2 = grid[lastIndex][i];

            // j -> column start to end, k -> column end to start
            for (int j = 1, k = lastIndex - 1; j < lastIndex; j++, k--) {
                int pos1 = getUniquePos(j, i);
                if (grid[j][i] > min1) {
                    internal.add(pos1);
                    min1 = grid[j][i];
                }

                int pos2 = getUniquePos(k, i);
                if (grid[k][i] > min2) {
                    internal.add(pos2);
                    min2 = grid[k][i];
                }
            }
        }

        part1 += internal.size();
        System.out.println(part1);

        int part2 = 0;
        // iterate on each internal point
        for (i = 1; i < lastIndex; i++) {
            for (int j = 1; j < lastIndex; j++) {
                int val = grid[i][j];

                int vis = 1;
                // go left
                int temp = 0;
                for (int k = j - 1; k >= 0; k--) {
                    if (val > grid[i][k]) {
                        temp++;
                    } else if (val <= grid[i][k]) {
                        temp++;
                        break;
                    }
                }
                vis *= (temp == 0 ? 1 : temp);

                // go right
                temp = 0;
                for (int k = j + 1; k <= lastIndex; k++) {
                    if (val > grid[i][k]) {
                        temp++;
                    } else if (val <= grid[i][k]) {
                        temp++;
                        break;
                    }
                }
                vis *= (temp == 0 ? 1 : temp);

                // go up
                temp = 0;
                for (int k = i - 1; k >= 0; k--) {
                    if (val > grid[k][j]) {
                        temp++;
                    } else if (val <= grid[k][j]) {
                        temp++;
                        break;
                    }
                }
                vis *= (temp == 0 ? 1 : temp);

                //go down
                temp = 0;
                for (int k = i + 1; k <= lastIndex; k++) {
                    if (val > grid[k][j]) {
                        temp++;
                    } else if (val <= grid[k][j]) {
                        temp++;
                        break;
                    }
                }
                vis *= (temp == 0 ? 1 : temp);

                part2 = Math.max(vis, part2);
            }
        }
        System.out.println(part2);
    }
}
