package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_1;

/**
 * Created by vgaur created on 29/12/23
 */
public class Day1 {

    private static String INPUT = """
            )
            """;

    public static void solve() {
        INPUT = DAY_1;
        String line = Arrays.stream(INPUT.split("\n")).toList().get(0);
        int count = 0, basement = -1, idx = 0;
        for (char ch : line.toCharArray()) {
            if (ch == '(') count++;
            else count--;

            if (count == -1 && basement == -1) basement = idx;
            idx++;
        }

        System.out.println("Part 1: " + count); // 74
        System.out.println("Part 1: " + (basement + 1)); // 1795
    }
}
