package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_13;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day13 {

    private static String INPUT = """
            0: 3
            1: 2
            4: 4
            6: 4
            """;

    public static void solve() {
        INPUT = DAY_13;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<Integer, Integer> _depths = new LinkedHashMap<>();
        List<Integer> positions = new ArrayList<>();
        int max = -1;
        for (String line : lines) {
            var parts = line.split(": ");
            _depths.put(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            max = Integer.parseInt(parts[0]);
            positions.add(max);
        }
        int[] depths = new int[max+1];
        _depths.forEach((p, d) -> depths[p] = d);
        System.out.println("Part 1: " + simulateMovement(depths, max, positions));

        int ans2 = -1;
        for (int i = 1; i < 100_000_000; i++) {
            if (simulate(i, depths, positions)) {
                //System.out.println("Seconds: " + i + " successful");
                ans2 = i;
                break;
            }
        }
        System.out.println("Part 2: " + ans2);
    }

    private static boolean simulate(long time,  int[] depths, List<Integer> positions) {
        for (int pos : positions) {
            int n = depths[pos];
            int n_1 = n-1;
            long loc;
            long ctime = time + pos;

            long mod = ctime%n_1;
            long div = ctime/n_1;
            if (mod == 0) {
                loc = div % 2 == 0 ? 0 : n_1;
            } else {
                loc = div % 2 == 0 ? mod : (n_1-mod);
            }
            if (loc == 0) {
                return false;
            }
        }
        return true;
    }

    private static int simulateMovement(int[] depths, int max, List<Integer> positions) {
        int pos = -1;
        long[] locations = new long[max+1];
        boolean[] directions = new boolean[max+1];
        for (int i = 0; i <= max; i++) {
            locations[i] = positions.contains(i) ? 0 : -1;
            directions[i] = true;
        }
        int severity = 0;
        do {
            pos++;
            if (locations[pos] == 0) {
                severity += (pos * depths[pos]);
            }
            for (int p : positions) {
                int depth = depths[p];

                long curloc = locations[p];
                if ((curloc == depth - 1 && directions[p]) || (curloc == 0 && !directions[p])) {
                    directions[p] = !directions[p];
                }

                if (directions[p]) { // Go down
                    locations[p] = curloc + 1;
                } else {
                    locations[p] = curloc - 1;
                }
            }
        } while (pos != max);
        return severity;
    }
}
