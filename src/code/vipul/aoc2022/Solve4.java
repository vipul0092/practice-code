package code.vipul.aoc2022;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static code.vipul.aoc2022.inputs.Inputs.INPUT_4;

/**
 * https://adventofcode.com/2022/day/4
 */
public class Solve4 {

    private static final String INPUT = "2-4,6-8\n" +
            "2-3,4-5\n" +
            "5-7,7-9\n" +
            "2-8,3-7\n" +
            "6-6,4-6\n" +
            "2-6,4-8";

    public static void solve() {
        List<String> inputs = Arrays.stream(INPUT_4.split("\n")).collect(Collectors.toList());

        int count = 0;
        for (String in : inputs) {
            String[] ranges = in.split(",");
            int s1 = Integer.parseInt(ranges[0].split("-")[0]);
            int e1 = Integer.parseInt(ranges[0].split("-")[1]);
            int s2 = Integer.parseInt(ranges[1].split("-")[0]);
            int e2 = Integer.parseInt(ranges[1].split("-")[1]);

            // s1 - s2 - e2 - e1
            if ((s1 <= s2 && e2 <= e1 && s2 <= e1)
                    || (s2 <= s1 && e1 <= e2 && s1 <= e2)) { // s2 - s1 - e1 - e2
                count++;
            }
        }
        System.out.println(count);
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(INPUT_4.split("\n")).collect(Collectors.toList());

        int count = 0;
        for (String in : inputs) {
            String[] ranges = in.split(",");
            int s1 = Integer.parseInt(ranges[0].split("-")[0]);
            int e1 = Integer.parseInt(ranges[0].split("-")[1]);
            int s2 = Integer.parseInt(ranges[1].split("-")[0]);
            int e2 = Integer.parseInt(ranges[1].split("-")[1]);

            // s1 - s2 - e2 - e1
            if ((s1 <= s2 && e2 <= e1 && s2 <= e1)
                    || (s2 <= s1 && e1 <= e2 && s1 <= e2) // s2 - s1 - e1 - e2
                    || (s1 <= s2 && e1 <= e2 && e1 >= s2) // s1 - s2 - e1 - e2
                    || (s2 <= s1 && e2 <= e1 && e2 >= s1)) { // s2 - s1 - e2 - e1
                count++;
            }
        }
        System.out.println(count);
    }
}
