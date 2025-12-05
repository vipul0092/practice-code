package code.vipul.aoc2025;

import code.vipul.utils.AoCInputReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://adventofcode.com/2025/day/5
 */
public class Day5 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day5.class, false);

        solveIntervalMerge(lines);
        solveLineSweep(lines);
    }

    // interval merge algorithm
    private static void solveIntervalMerge(List<String> lines) {
        int i = 0;
        List<long[]> intervals = new ArrayList<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) break;

            String[] parts = line.split("-");
            long s = Long.parseLong(parts[0]), e = Long.parseLong(parts[1]);
            intervals.add(new long[]{s, e});
        }
        i++;
        Set<Long> nums = new HashSet<>();
        for (; i < lines.size(); i++) {
            long num = Long.parseLong(lines.get(i));
            nums.add(num);
        }

        intervals.sort((l1, l2) -> {
            if (l1[0] < l2[0]) return -1;
            if (l1[0] == l2[0]) {
                return Long.compare(l1[1], l2[1]);
            }
            return 1;
        });
        List<long[]> merged = new ArrayList<>();

        long start = intervals.getFirst()[0], end = intervals.getFirst()[1];
        for (long[] interval : intervals) {
            long cs = interval[0], ce = interval[1];

            if (cs > end) { // mutually exclusive, new interval
                merged.add(new long[]{start, end});
                start = cs;
                end = ce;
            } else if (cs >= start && ce <= end) { // within, swallowed
                continue;
            } else { // overlapping, merge
                start = Math.min(start, cs);
                end = Math.max(end, ce);
            }
        }
        merged.add(new long[]{start, end});

        long total = merged.stream()
                .map(in -> in[1]-in[0]+1)
                .reduce(0L, Long::sum);
        int count1 = (int) nums.stream()
                .filter(num -> merged.stream().anyMatch(in -> num >= in[0] && num <= in[1]))
                .count();

        System.out.println("Part 1: " + count1); // 744
        System.out.println("Part 2: " + total); // 347468726696961
    }

    // uses line sweep algorithm to figure out the count of unique values
    private static void solveLineSweep(List<String> lines) {
        int i = 0;
        TreeMap<Long, Integer> sweep = new TreeMap<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) break;

            String[] parts = line.split("-");
            long s = Long.parseLong(parts[0]), e = Long.parseLong(parts[1]);

            sweep.merge(s, 1, (c, o) -> c + 1);
            sweep.merge(e + 1, -1, (c, o) -> c - 1);
        }

        i++;
        Set<Long> nums = new HashSet<>();
        for (; i < lines.size(); i++) {
            long num = Long.parseLong(lines.get(i));
            nums.add(num);
        }

        // Line sweep algo using the treemap we created above
        int cursum = sweep.firstEntry().getValue(), prevsum;
        long prev = sweep.firstKey();
        sweep.remove(prev);

        long total = 0;
        int count1 = 0;
        for (var entry : sweep.entrySet()) {
            prevsum = cursum;
            cursum += entry.getValue();
            if (cursum > 0) {
                if (prevsum != 0) {
                    total += (entry.getKey() - prev);
                    long finalPrev = prev;
                    count1 += (int) nums.stream().filter(n -> n >= finalPrev && n < entry.getKey()).count();
                }
                prev = entry.getKey();
            } else if (cursum == 0) {
                total += (entry.getKey() - prev);
                long finalPrev = prev;
                count1 += (int) nums.stream().filter(n -> n >= finalPrev && n < entry.getKey()).count();
            }
        }


        System.out.println("Part 1: " + count1); // 744
        System.out.println("Part 2: " + total); // 347468726696961
    }
}
