package code.vipul.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2020/day/13
 */
public class Solve13 {

    private static final String SMALLINPUT = "939\n" +
            "7,13,x,x,59,x,31,19";

    private static final String INPUT = "1000495\n" +
            "19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,521,x,x,x,x,x,x,x,23,x,x,x,x,x,x,x,x," +
            "17,x,x,x,x,x,x,x,x,x,x,x,29,x,523,x,x,x,x,x,37,x,x,x,x,x,x,13";

    public static void solve() {
        String[] rows = INPUT.split("\n");
        int earliestTime = Integer.parseInt(rows[0]);
        List<Integer> busIds = Arrays.stream(rows[1].split(","))
                .filter(id -> !id.equals("x"))
                .map(id -> Integer.parseInt(id))
                .collect(Collectors.toList());

        int minDiff = Integer.MAX_VALUE;
        int selectedBusId = -1;
        for (int busId : busIds) {
            int mod = earliestTime % busId;
            int nextTime = earliestTime - mod + busId;

            if (minDiff > (nextTime - earliestTime)) {
                minDiff = nextTime - earliestTime;
                selectedBusId = busId;
            }
        }

        System.out.println("Answer: " + (selectedBusId * minDiff)); //2092
    }

    public static void solvePart2() {
        String[] rows = INPUT.split("\n");

        String[] ids = rows[1].split(",");
        Map<Long, Long> offsets = new LinkedHashMap<>();
        List<Long> values = new ArrayList<>();

        long start = Long.parseLong(ids[0]);
        offsets.put(start, 0L);

        long offset = 1;
        for (String id : ids) {
            if (!id.equals("x")) {
                long val = Long.parseLong(id);
                values.add(val);
                offsets.put(val, offset);
                offset = 1;
            } else {
                offset++;
            }
        }

        long ans = find(values.get(0), 1, values.get(0), offsets, values);
        ans -= (ids.length - 1);
        System.out.println("Answer: " + ans); //702970661767766
    }

    private static long find(long start, int cmpIdx, long currentLcm, Map<Long, Long> offsets,  List<Long> values) {
        if (cmpIdx >= values.size()) {
            return start;
        }

        long valueToCompare = values.get(cmpIdx);
        long currentOffset = offsets.get(valueToCompare);
        long num = start;
        while ((num + currentOffset) % valueToCompare != 0) {
            num += currentLcm;
        }

        num += currentOffset;
        currentLcm = currentLcm * valueToCompare;

        return find(num, cmpIdx + 1, currentLcm, offsets, values);
    }
}
