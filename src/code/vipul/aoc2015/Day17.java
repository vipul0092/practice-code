package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_17;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day17 {

    private static String INPUT = """
            20
            15
            10
            5
            5
            """;

    record Key(int volume, int taken){};
    private static final Map<Key, Set<Integer>> cache = new HashMap<>();

    public static void solve() {
        INPUT = DAY_17;
        int volume = 150;
        List<Integer> containers = Arrays.stream(INPUT.split("\n")).map(l -> Integer.parseInt(l)).toList();

        int count = 0;
        TreeMap<Integer, Integer> bitWays = new TreeMap<>();
        // Brute force, faster than the DP solution I wrote originally ... ¯\_(ツ)_/¯
        for (int i = 1; i < 1 << containers.size(); i++) {
            int sum = 0, ways = 0;
            for (int j = 0; j < containers.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    sum += containers.get(j);
                    ways++;
                }
                if (sum > volume) {
                    break;
                }
            }
            if (sum == volume) {
                bitWays.merge(ways, 1, Integer::sum);
                count++;
            }
        }
        System.out.println("Part 1: " + count); // 1304
        System.out.println("Part 2: " + bitWays.firstEntry().getValue()); // 18
    }
}
