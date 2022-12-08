package code.vipul.aoc2022;

import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 08/12/22
 */
public class Solve8 {

    private static final String INPUT = "30373\n" +
            "25512\n" +
            "65332\n" +
            "33549\n" +
            "35390";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_8.split("\n")).collect(Collectors.toList());
        // List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        List<List<Integer>> grid = new ArrayList<>();

        for (String in : inputs) {
            List<Integer> row = new ArrayList<>();
            for (Character ch : in.toCharArray()) {
                row.add(ch - 48);
            }
            grid.add(row);
        }

        int count = (grid.size() * 2) + ((grid.get(0).size() - 2) * 2);

        Set<Grid.Pos> internal = new HashSet<>();
        for (int i = 1; i < grid.size() - 1; i++) {
            int min = grid.get(i).get(0);
            for (int j = 1; j < grid.get(i).size() - 1; j++) {
                Grid.Pos pos = Grid.Pos.of(i, j);
                if (grid.get(i).get(j) > min) {
                    internal.add(pos);
                    min = grid.get(i).get(j);
                }
            }

            min = grid.get(i).get(grid.get(i).size() - 1);
            for (int j = grid.get(i).size() - 2; j > 0; j--) {
                Grid.Pos pos = Grid.Pos.of(i, j);
                if (grid.get(i).get(j) > min) {
                    internal.add(pos);
                    min = grid.get(i).get(j);
                }
            }
        }

        for (int i = 1; i < grid.size() - 1; i++) {
            int min = grid.get(0).get(i);
            for (int j = 1; j < grid.get(0).size() - 1; j++) {
                Grid.Pos pos = Grid.Pos.of(j, i);
                if (grid.get(j).get(i) > min) {
                    internal.add(pos);
                    min = grid.get(j).get(i);
                }
            }

            min = grid.get(grid.get(i).size() - 1).get(i);
            for (int j = grid.get(0).size() - 2; j > 0; j--) {
                Grid.Pos pos = Grid.Pos.of(j, i);
                if (grid.get(j).get(i) > min) {
                    internal.add(pos);
                    min = grid.get(j).get(i);
                }
            }
        }

        count += internal.size();
        System.out.println(count);

        int ans = 0;
        for (int i = 1; i < grid.size() - 1; i++) {
            for (int j = 1; j < grid.size() - 1; j++) {
                int val = grid.get(i).get(j);

                int vis = 1;
                // go left
                int temp = 0;
                for (int k = j - 1; k >=0; k--) {
                    if (val > grid.get(i).get(k)) {
                        temp++;
                    } else if (val <= grid.get(i).get(k)) {
                        temp++;
                        break;
                    }
                }
                vis *= (temp == 0 ? 1 : temp);

                // go right
                temp = 0;
                for (int k = j + 1; k < grid.get(i).size(); k++) {
                    if (val > grid.get(i).get(k)) {
                        temp++;
                    } else if (val <= grid.get(i).get(k)) {
                        temp++;
                        break;
                    }
                }
                vis *= (temp == 0 ? 1 : temp);

                // go up
                temp = 0;
                for (int k = i - 1; k >=0; k--) {
                    if (val > grid.get(k).get(j)) {
                        temp++;
                    } else if (val <= grid.get(k).get(j)) {
                        temp++;
                        break;
                    }
                }
                vis *= (temp == 0 ? 1 : temp);

                //go down
                temp = 0;
                for (int k = i + 1; k < grid.get(i).size(); k++) {
                    if (val > grid.get(k).get(j)) {
                        temp++;
                    } else if (val <= grid.get(k).get(j)) {
                        temp++;
                        break;
                    }
                }
                vis *= (temp == 0 ? 1 : temp);

                ans = Math.max(vis, ans);
            }
        }
        System.out.println(ans);
    }
}
