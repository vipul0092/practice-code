package code.vipul.aoc2025;

import code.vipul.utils.AoCInputReader;

import java.util.Arrays;
import java.util.List;

/**
 * https://adventofcode.com/2025/day/3
 */
public class Day3 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day3.class, false);

        long max1 = 0, max2 = 0;
        long m1 = 0, m2 = 0;
        long[][] largest;
        for (String line : lines) {
            int[] values = new int[line.length()];
            for (int i = 0; i < line.length(); i++) {
                values[i] = line.charAt(i) - '0';
            }

            // Tabular DP (Bottom up approach)
            largest = new long[13][line.length()+1];
            for (int digits = 1; digits <= 12; digits++) {
                populate(values, largest, digits);
            }

            max1 += Arrays.stream(largest[2]).max().getAsLong();
            max2 += Arrays.stream(largest[12]).max().getAsLong();

            // Recursive + memoization DP (Top down approach)
            largest = new long[13][line.length()+1];
            m1 += recurse(largest, values, 0, 2);
            m2 += recurse(largest, values, 0, 12);
        }
        System.out.println("Part 1: " + max1); // 17087
        System.out.println("Part 2: " + max2); // 169019504359949

        System.out.println("Part 1: " + m1); // 17087
        System.out.println("Part 1: " + m2); // 169019504359949
    }

    private static long recurse(long[][] largest, int[] values, int start, int digits) {
        if (start == values.length) {
            return 0; // sentinel value because `0` is not a valid digit as per the input
        }
        if (digits == 1) {
            return Math.max(values[start], recurse(largest, values, start + 1, digits));
        }

        if (largest[digits][start] != 0) {
            return largest[digits][start];
        }

        long pow = (long) Math.pow(10, digits-1);
        // Include the current digit
        long maxrest = recurse(largest, values, start + 1, digits - 1);
        maxrest = maxrest == 0 ? 0 : maxrest + values[start] * pow;
        // do not include the current digit
        maxrest = Math.max(maxrest, recurse(largest, values, start + 1, digits));
        largest[digits][start] = maxrest;
        return maxrest;
    }

    private static void populate(int[] values, long[][] largest, int digits) {
        int n = values.length;
        long pow = (long) Math.pow(10, digits - 1);
        for (int i = n - digits; i >= 0; i--) {
            // We move backwards in the array
            // largest number until index `i` having `digits` digits is
            // Max of
            // largest number until index `i+1` having `digits` digits
            // and
            // digit at `i` + largest number until index `i+1` having `digits-1` digits
            largest[digits][i] = Math.max(largest[digits - 1][i + 1] + (pow * values[i]), largest[digits][i + 1]);
        }
    }
}
