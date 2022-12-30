package code.vipul.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 27/12/22
 * https://adventofcode.com/2021/day/7
 */
public class Solve7 {

    private static final String INPUT = "16,1,2,0,4,2,7,1,2,14";

    public static void solve() {
        List<Long> inputs = Arrays.stream(Inputs.INPUT_7.split(",")).map(s -> Long.parseLong(s)).collect(Collectors.toList());
        //List<Long> inputs = Arrays.stream(INPUT.split(",")).map(s -> Long.parseLong(s)).sorted().collect(Collectors.toList());

        TreeMap<Long, Long> counts = new TreeMap<>();
        for (long in : inputs) {
            counts.putIfAbsent(in, 0L);
            counts.put(in, counts.get(in) + 1);
        }

        long min = Long.MAX_VALUE;

        for (var entry : counts.entrySet()) {
            long num = entry.getKey();
            long n = getDiff(num, counts, false);
            min = Math.min(min, n);
        }
        System.out.println(min); //352254
    }

    public static void solvePart2() {
        List<Long> inputs = Arrays.stream(Inputs.INPUT_7.split(",")).map(s -> Long.parseLong(s)).collect(Collectors.toList());
        //List<Long> inputs = Arrays.stream(INPUT.split(",")).map(s -> Long.parseLong(s)).sorted().collect(Collectors.toList());

        TreeMap<Long, Long> counts = new TreeMap<>();
        for (long in : inputs) {
            counts.putIfAbsent(in, 0L);
            counts.put(in, counts.get(in) + 1);
        }

        long minVal = counts.firstKey();
        long maxVal = counts.lastKey();

        long min = Long.MAX_VALUE;

        for (long i = minVal; i <= maxVal; i++) {
            long n = getDiff(i, counts, true);
            min = Math.min(min, n);
        }

        System.out.println(min); //99053143
    }

    private static long getDiff(long val, TreeMap<Long, Long> counts, boolean square) {
        long sum = 0;
        for (var entry : counts.entrySet()) {
            long n = Math.abs(entry.getKey() - val);
            n = square ? (n * (n + 1))/2 : n;
            sum += (n * entry.getValue());
        }
        return sum;
    }
}
