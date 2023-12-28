package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_5;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day5 {

    private static String INPUT = """
            0
            3
            0
            1
            -3
            """;

    public static void solve() {
        INPUT = DAY_5;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        System.out.println("Part 1: " + solveInternal(lines, false)); // 339351
        System.out.println("Part 2: " + solveInternal(lines, true)); // 24315397
    }

    private static int solveInternal(List<String> lines, boolean considerOffset) {
        List<Integer> nos = lines.stream().map(i -> Integer.parseInt(i)).toList();
        Integer[] arr = nos.toArray(new Integer[0]);
        int len = arr.length;
        int current = 0;
        int steps = 0;
        while (current < len && current >= 0) {
            //System.out.println(current);
            int next = current + arr[current];
            if (considerOffset && arr[current] >= 3) {
                arr[current]--;
            } else {
                arr[current]++;
            }
            current = next;
            steps++;
        }
        return steps;
    }
}
