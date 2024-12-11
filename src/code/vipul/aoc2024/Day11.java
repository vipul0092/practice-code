package code.vipul.aoc2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static code.vipul.aoc2024.inputs.Inputs.DAY_11;

/**
 * https://adventofcode.com/2024/day/11
 */
public class Day11 {

    private static String INPUT = """
            125 17
            """;

    private static int index = 1;
    private static Map<Long, Integer> ids;
    private static long[][] dp;

    public static void solve() {
        INPUT = DAY_11;
        String line = Arrays.stream(INPUT.split("\n")).toList().get(0);
        ids = new HashMap<>();
        dp = new long[5000][76];

        long total1 = 0, total2 = 0;
        long s = System.currentTimeMillis();
        for (String part : line.split(" ")) {
            long num = Long.parseLong(part);
            total1 += count(num, 25);
            total2 += count(num, 75);
        }
        System.out.println("Took: " + (System.currentTimeMillis() - s) + "ms");

        System.out.println("Part 1: " + total1); // 228668
        System.out.println("Part 2: " + total2); // 270673834779359
    }

    private static long count(long num, int blinksLeft) {
        if (blinksLeft == 0) {
            return 1;
        }

        int id = getId(num);
        if (dp[id][blinksLeft] != 0) {
            return dp[id][blinksLeft] - 1;
        }

        long cnt = 0;
        if (num == 0) {
            cnt = count(1, blinksLeft - 1);
        } else if (digits(num) % 2 == 0) {
            int d = digits(num);
            long mod = (long) Math.pow(10, d / 2);
            long left = num / mod, right = num % mod;
            cnt += count(left, blinksLeft - 1);
            cnt += count(right, blinksLeft - 1);
        } else {
            cnt = count(num * 2024, blinksLeft - 1);
        }
        dp[id][blinksLeft] = cnt + 1;
        return cnt;
    }

    private static int getId(long num) {
        if (!ids.containsKey(num)) {
            ids.put(num, index++);
        }
        return ids.get(num);
    }

    private static int digits(long num) {
        return ((int) Math.log10(num) + 1);
    }
}
