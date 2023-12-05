package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static code.vipul.aoc2023.inputs.Inputs.DAY_5;

/**
 * Created by vgaur created on 04/12/23
 */
public class Day5 {

    private static String INPUT = """
            seeds: 79 14 55 13
                        
            seed-to-soil map:
            50 98 2
            52 50 48
                        
            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15
                        
            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4
                        
            water-to-light map:
            88 18 7
            18 25 70
                        
            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13
                        
            temperature-to-humidity map:
            0 69 1
            1 0 69
                        
            humidity-to-location map:
            60 56 37
            56 93 4
            """;

    record Ranges(long start, long end, long dest, long count, String deststr) implements Comparable<Ranges> {
        @Override
        public int compareTo(Ranges r) {
            if (r.start == this.start) {
                return Long.compare(this.end, r.end);
            }
            return Long.compare(this.start, r.start);
        }
    };
    public static void solve() {
        INPUT = DAY_5;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<String, Set<Ranges>> rangeMap = new LinkedHashMap<>();
        List<Long> seeds = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) continue;

            if (line.startsWith("seeds")) {
                String[] seedstr = line.split(": ")[1].split(" ");
                seeds = Arrays.stream(seedstr).map(s -> Long.parseLong(s)).toList();
            } else {
                if (!(line.charAt(0) >= '0' && line.charAt(0) <= '9')) {
                    String[] srcdest = line.split(" map")[0].split("-");
                    String source = srcdest[0], dest = srcdest[2];
                    rangeMap.put(source, new TreeSet<>());
                    i++;
                    while(i < lines.size()) {
                        line = lines.get(i);
                        if (line.isEmpty()) break;
                        String[] rangestr = line.split(" ");
                        long start = Long.parseLong(rangestr[1]);
                        long count = Long.parseLong(rangestr[2]);
                        long destmapping = Long.parseLong(rangestr[0]);
                        Ranges r = new Ranges(start, start+count-1, destmapping, count, dest);
                        rangeMap.get(source).add(r);
                        i++;
                    }
                }
            }
        }

        long lowest = Long.MAX_VALUE;
        for (long seed : seeds) {
            long tmp = findMin(new Range(seed, seed), "seed", rangeMap);
            lowest = Math.min(lowest, tmp);
        }

        long lowest2 = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i+=2) {
            long start = seeds.get(i);
            long range = seeds.get(i+1);

            long tmp = findMin(new Range(start, start+range-1), "seed", rangeMap);
            lowest2 = Math.min(lowest2, tmp);
        }

        System.out.println("Part 1: " + lowest); // 226172555
        System.out.println("Part 2: " + lowest2); // 47909639
    }

    record Range(long start, long end){};

    static long findMin(Range range, String source, Map<String, Set<Ranges>> rangeMap) {
        Set<Ranges> ranges = rangeMap.get(source);

        if (source.equals("location")) {
            return range.start;
        }

        long min = Long.MAX_VALUE;

        boolean went = false;
        String dest = "";
        for (Ranges currentRange : ranges) {
            dest = currentRange.deststr;
            // * - [ - ] - *
            if (range.start <= currentRange.start && range.end >= currentRange.end) {
                long tmp = findMin(new Range(map(currentRange.start, currentRange), map(currentRange.end, currentRange)),
                        currentRange.deststr, rangeMap);
                min = Math.min(tmp, min);
                went = true;
                // * - [ - * - ]
            } else if (range.start <= currentRange.start && range.end <= currentRange.end
                    && range.end >= currentRange.start) {
                long tmp = findMin(new Range(map(currentRange.start, currentRange), map(range.end, currentRange)),
                        currentRange.deststr, rangeMap);
                min = Math.min(tmp, min);
                went = true;
                // [ - * - ] - *
            } else if (range.start >= currentRange.start && range.end >= currentRange.end
                    && range.start <= currentRange.end) {
                long tmp = findMin(new Range(map(range.start, currentRange), map(currentRange.end, currentRange)),
                        currentRange.deststr, rangeMap);
                min = Math.min(tmp, min);
                went = true;
                // [ - * - * - ]
            } else if (range.start >= currentRange.start && range.end <= currentRange.end) {
                long tmp = findMin(new Range(map(range.start, currentRange), map(range.end, currentRange)),
                        currentRange.deststr, rangeMap);
                min = Math.min(tmp, min);
                went = true;
            }
        }

        if (!went) {
            min = findMin(new Range(range.start, range.end), dest, rangeMap);
        }
        return min;
    }

    private static long map(long val, Ranges range) {
        return range.dest + (val - range.start);
    }
}
