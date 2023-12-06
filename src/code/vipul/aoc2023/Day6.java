package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2023.inputs.Inputs.DAY_6;

/**
 * Created by vgaur created on 05/12/23
 */
public class Day6 {

    private static String INPUT = """
            Time:      7  15   30
            Distance:  9  40  200
            """;

    public static void solve() {
        INPUT = DAY_6;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        String[] tstr = lines.get(0).split(":")[1].split(" ");
        for (String ts : tstr) {
            if (ts.isEmpty()) continue;
            times.add(Integer.parseInt(ts));
        }
        String[] dstr = lines.get(1).split(":")[1].split(" ");
        for (String ds : dstr) {
            if (ds.isEmpty()) continue;
            distances.add(Integer.parseInt(ds));
        }

        StringBuilder ts = new StringBuilder();
        for (char ch : lines.get(0).toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                ts.append(ch);
            }
        }
        long t = Long.parseLong(ts.toString());

        ts = new StringBuilder();
        for (char ch : lines.get(1).toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                ts.append(ch);
            }
        }
        long d = Long.parseLong(ts.toString());

        long total = 1;
        for (int i = 0; i < times.size(); i++) {
            long ways = getWays(times.get(i), distances.get(i));
            total *= ways;
        }

        System.out.println("Part 1: " + total); // 5133600
        System.out.println("Part 2: " + getWays(t, d)); // 40651271
    }

    // Binary search on answer
    private static long getWays(long time, long distance) {
        long t1 = 0;

        long min = 1, max = time;
        while (min <= max) {
            long mid = (min + max) >> 1;
            long left = time - mid;
            long covered = left * mid;

            if (covered > distance) {
                t1 = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        long t2 = 0;
        min = time / 2;
        max = time;
        while (min <= max) {
            long mid = (min + max) >> 1;
            long left = time - mid;
            long covered = left * mid;

            if (covered > distance) {
                t2 = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return t2 - t1 + 1;
    }
}
