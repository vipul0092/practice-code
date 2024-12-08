package code.vipul.aoc2024;

import java.util.*;

import static code.vipul.aoc2024.inputs.Inputs.DAY_7;

/**
 * https://adventofcode.com/2024/day/7
 */
public class Day7 {

    private static String INPUT = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """;

    public static void solve() {
        INPUT = DAY_7;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        long sum1 = 0;
        long sum2 = 0;
        for (String line : lines) {
            String[] parts = line.split(": ");
            long sum = Long.parseLong(parts[0]);
            List<Long> numbers = Arrays.stream(parts[1].split(" ")).map(Long::parseLong).toList();

            if (possible(numbers, 1, numbers.get(0), sum, false)) {
                sum1 += sum;
            }
            if (possible(numbers, 1, numbers.get(0), sum, true)) {
                sum2 += sum;
            }
        }

        System.out.println("Part 1: " + sum1); // 1298103531759
        System.out.println("Part 2: " + sum2); // 140575048428831
    }

    private static boolean possible(List<Long> numbers, int index, long totalUntilNow, long requiredTotal,
                                    boolean considerOr) {
        if (index == numbers.size()) {
            return totalUntilNow == requiredTotal;
        }

        if (totalUntilNow > requiredTotal) {
            return false;
        }

        long num = numbers.get(index);
        return possible(numbers, index + 1, totalUntilNow + num, requiredTotal, considerOr)
                || possible(numbers, index + 1, totalUntilNow * num, requiredTotal, considerOr)
                || (considerOr
                    && possible(numbers, index + 1, append(totalUntilNow, num), requiredTotal, considerOr));

    }

    private static long append(long n1, long n2) {
        long second = n2;
        while(second > 0) {
            second /= 10;
            n1 *= 10;
        }
        return n1 + n2;
    }
}
