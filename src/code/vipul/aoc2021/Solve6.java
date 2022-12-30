package code.vipul.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 27/12/22
 */
public class Solve6 {

    private static final String INPUT = "3,4,3,1,2";

    private static TreeMap<Integer, Long> counts;

    public static void solve() {
        parse(Inputs.INPUT_6);
        System.out.println(solveInternal(80)); // 365862
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_6);
        System.out.println(solveInternal(256)); // 1653250886439
    }

    private static long solveInternal(int days) {
        while (days > 0) {
            int top = counts.firstKey();
            TreeMap<Integer, Long> newCounts = new TreeMap<>();
            if (top + 1 > days) {
                for (var entry : counts.entrySet()) {
                    int num = entry.getKey();
                    long freq = entry.getValue();
                    newCounts.put(num - days, freq);
                }
                days = 0;
            } else {
                newCounts.putIfAbsent(8, 0L);
                long converted = 0;
                for (var entry : counts.entrySet()) {
                    int num = entry.getKey();
                    long freq = entry.getValue();

                    if (num == top) {
                        newCounts.putIfAbsent(6, 0L);
                        newCounts.put(6, newCounts.get(6) + freq);
                        converted = freq;
                    } else {
                        newCounts.putIfAbsent(num - top - 1, 0L);
                        newCounts.put(num - top - 1, newCounts.get(num - top - 1) + freq);
                    }
                }
                newCounts.put(8, newCounts.get(8) + converted);
                days -= (top + 1);
            }
            counts = newCounts;
        }

        return counts.values().stream().mapToLong(s -> s).sum();
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split(",")).collect(Collectors.toList());
        counts = new TreeMap<>();
        for (String in : inputs) {
            int num = Integer.parseInt(in);
            counts.putIfAbsent(num, 0L);
            counts.put(num, counts.get(num) + 1);
        }
    }
}
