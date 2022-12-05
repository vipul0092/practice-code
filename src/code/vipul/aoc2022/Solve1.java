package code.vipul.aoc2022;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static code.vipul.aoc2022.inputs.Inputs.INPUT_1;

/**
 * https://adventofcode.com/2022/day/1
 */
public class Solve1 {

    private static final String INPUT = "1000\n" +
            "2000\n" +
            "3000\n" +
            "\n" +
            "4000\n" +
            "\n" +
            "5000\n" +
            "6000\n" +
            "\n" +
            "7000\n" +
            "8000\n" +
            "9000\n" +
            "\n" +
            "10000";

    public static void solve() {
        List<String> numbers = Arrays.stream(INPUT_1.split("\n")).collect(Collectors.toList());
        int max = Integer.MIN_VALUE;
        int current = 0;

        for (String num : numbers) {
            if (num.isEmpty()) {
                max = Math.max(max, current);
                current = 0;
            } else {
                current += Integer.parseInt(num);
            }
        }
        System.out.println(max);
    }

    public static void solvePart2() {
        List<String> numbers = Arrays.stream(INPUT_1.split("\n")).collect(Collectors.toList());
        int max = Integer.MIN_VALUE;
        int current = 0;
        TreeSet<Integer> values = new TreeSet<>(Comparator.reverseOrder());

        for (String num : numbers) {
            if (num.isEmpty()) {
                values.add(current);
                max = Math.max(max, current);
                current = 0;
            } else {
                current += Integer.parseInt(num);
            }
        }
        if (current != 0) {
            values.add(current);
        }
        Iterator<Integer> iterator = values.iterator();
        int v1 = iterator.next();
        int v2 = iterator.next();
        int v3 = iterator.next();
        System.out.println((v1 + v2 + v3));
    }
}
