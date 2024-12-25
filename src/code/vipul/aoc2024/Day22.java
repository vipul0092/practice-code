package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.*;

/**
 * https://adventofcode.com/2024/day/22
 */
public class Day22 {

    private static final long MOD = 16777216L;
    private static final int GENERATE = 2000;

    record Diff(int val, int diff) {}

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day22.class, false);
        long sum = 0;
        Map<String, Integer> totals = new HashMap<>();

        for (String line : lines) {
            long secret = Long.parseLong(line);
            int times = GENERATE;

            int prevVal = (int) (secret % 10);
            List<Diff> diffs = new ArrayList<>();
            while (times-- > 0) {
                secret = calc(secret);
                int curval = (int) (secret % 10);
                diffs.add(new Diff(curval, curval - prevVal));
                prevVal = curval;
            }
            sum += secret;

            Map<String, Integer> curTotal = new HashMap<>();
            for (int i = 0; i < diffs.size() - 4; i++) {
                String key = String.valueOf(diffs.get(i).diff) +
                        diffs.get(i + 1).diff +
                        diffs.get(i + 2).diff +
                        diffs.get(i + 3).diff;
                int val = diffs.get(i + 3).val;
                if (!curTotal.containsKey(key)) {
                    curTotal.put(key, val);
                }
            }

            for (var entry : curTotal.entrySet()) {
                totals.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }
        System.out.println("Part 1: " + sum); // 14082561342

        int max = totals.values().stream().max(Comparator.naturalOrder()).orElseThrow();
        System.out.println("Part 2: " + max); // 1568
    }

    private static long calc(long secret) {
        secret = ((secret * 64) ^ secret) % MOD;
        secret = ((secret / 32) ^ secret) % MOD;
        secret = ((secret * 2048) ^ secret) % MOD;
        return secret;
    }
}
