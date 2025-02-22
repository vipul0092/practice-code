package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.Arrays;
import java.util.List;

/**
 * https://adventofcode.com/2024/day/2
 */
public class Day2 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day2.class, false);

        int count = 0;
        int count2 = 0;

        for (String line : lines) {
            List<Integer> numbers = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
            if (isValid(numbers, 0, -1, -1, 0, 0)) {
                count++;
                count2++;
            } else if (isValid(numbers, 0, -1, -1, 0, 1)) {
                count2++;
            }
        }

        System.out.println("Part 1: " + count); // 257
        System.out.println("Part 2: " + count2); // 328
    }

    // mode -> 0 means decreasing, 1 means increasing
    // Recursive DP style without memoization (memoization not required for this problem)
    private static boolean isValid(List<Integer> numbers, int idx, int pidx,
                                   int mode, int wrong, int wrongAllowed) {
        if (idx == numbers.size()) {
            return true;
        }

        int num = numbers.get(idx);
        boolean res = false;
        if (pidx != -1) {
            int diff = num - numbers.get(pidx);
            if (mode == 0 && diff < 0 && Math.abs(diff) >= 1 && Math.abs(diff) <= 3) {
                // valid, take
                res = isValid(numbers, idx + 1, idx, mode, wrong, wrongAllowed);
            } else if (mode == 1 && diff > 0 && diff <= 3) {
                // valid, take
                res = isValid(numbers, idx + 1, idx, mode, wrong, wrongAllowed);
            }

            // invalid, skip if possible
            if (!res && wrong < wrongAllowed) {
                res = isValid(numbers, idx + 1, pidx, mode, wrong + 1, wrongAllowed);
            }
        } else {
            // take
            res = isValid(numbers, idx + 1, idx, 1, wrong, wrongAllowed);
            if (!res) {
                res = isValid(numbers, idx + 1, idx, 0, wrong, wrongAllowed);
            }

            // skip
            if (!res && wrong < wrongAllowed) {
                res = isValid(numbers, idx + 1, -1, -1, wrong + 1, wrongAllowed);
            }
        }
        return res;
    }
}
