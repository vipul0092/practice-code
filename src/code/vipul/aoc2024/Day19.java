package code.vipul.aoc2024;

import java.util.*;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2024/day/19
 */
public class Day19 {

    private static final Map<String, Long> cache = new HashMap<>();

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day19.class, false);

        List<String> types = Arrays.stream(lines.get(0).split(", ")).toList();

        int count = 0;
        long count2 = 0;
        for (int i = 2; i < lines.size(); i++) {
            String pattern = lines.get(i);
            long totalPossible = findPossibleCount(types, pattern);
            if (totalPossible > 0) {
                count++;
            }
            count2 += totalPossible;
        }

        System.out.println("Part 1: " + count);  // 209
        System.out.println("Part 2: " + count2); // 777669668613191
    }

    private static long findPossibleCount(List<String> types, String pattern) {
        if (pattern.isEmpty()) {
            return 1;
        }
        if (cache.containsKey(pattern)) {
            return cache.get(pattern);
        }
        long val = 0;
        for (String type : types) {
            if (pattern.startsWith(type)) {
                val += findPossibleCount(types, pattern.substring(type.length()));
            }
        }
        cache.put(pattern, val);
        return val;
    }
}
