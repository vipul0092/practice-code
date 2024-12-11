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

    record Key(long n, int b) {}
    private static Map<Key, Long> cache;

    public static void solve() {
        INPUT = DAY_11;
        String line = Arrays.stream(INPUT.split("\n")).toList().get(0);
        cache = new HashMap<>();

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

        Key key = new Key(num, blinksLeft);
        if (cache.containsKey(key)) return cache.get(key);

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
        cache.put(key, cnt);
        return cnt;
    }

    private static int digits(long num) {
        return ((int)Math.log10(num) + 1);
    }
}
