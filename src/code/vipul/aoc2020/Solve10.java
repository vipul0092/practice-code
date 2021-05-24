package code.vipul.aoc2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs.INPUT_10;

/**
 * https://adventofcode.com/2020/day/10
 */
public class Solve10 {

    private static final String INPUT = "28\n" +
            "33\n" +
            "18\n" +
            "42\n" +
            "31\n" +
            "14\n" +
            "46\n" +
            "20\n" +
            "48\n" +
            "47\n" +
            "24\n" +
            "23\n" +
            "49\n" +
            "45\n" +
            "19\n" +
            "38\n" +
            "39\n" +
            "11\n" +
            "1\n" +
            "32\n" +
            "25\n" +
            "35\n" +
            "8\n" +
            "17\n" +
            "7\n" +
            "9\n" +
            "4\n" +
            "2\n" +
            "34\n" +
            "10\n" +
            "3";

    public static void solve() {
        List<Integer> joltages = Arrays.stream(INPUT_10.split("\n"))
                .map(in -> Integer.parseInt(in))
                .collect(Collectors.toList());
        var joltageSet = new HashSet<>(joltages);

        var currentJoltage = 0;
        var diff1 = 0;
        var diff3 = 0;

        while (!joltageSet.isEmpty()) {
            var diff1Exists = joltageSet.contains(currentJoltage + 1);
            var diff2Exists = joltageSet.contains(currentJoltage + 2);
            var diff3Exists = joltageSet.contains(currentJoltage + 3);

            if (diff1Exists) {
                diff1++;
                joltageSet.remove(currentJoltage + 1);
                currentJoltage += 1;
            } else if (diff2Exists) {
                joltageSet.remove(currentJoltage + 2);
                currentJoltage += 2;
            } else if (diff3Exists) {
                diff3++;
                joltageSet.remove(currentJoltage + 3);
                currentJoltage += 3;
            }
        }
        diff3++;

        var answer = diff1 * diff3;
        System.out.println("Answer: " + answer); //2414
    }

    private static Map<Integer, Long> waysPastItem = new HashMap<>();
    public static void solvePart2() {
        List<Integer> joltages = Arrays.stream(INPUT_10.split("\n"))
                .map(in -> Integer.parseInt(in))
                .collect(Collectors.toList());
        var joltageSet = new HashSet<>(joltages);

        long answer = dive(0, joltageSet);
        System.out.println("Answer: " + answer); //21156911906816
    }

    private static long dive(int currentJoltage, Set<Integer> currentJoltageSet) {
        if (currentJoltageSet.isEmpty()) {
            waysPastItem.put(currentJoltage, 1L);
            return 1;
        }

        if (waysPastItem.containsKey(currentJoltage)) {
            return waysPastItem.get(currentJoltage);
        }

        var diff1Exists = currentJoltageSet.contains(currentJoltage + 1);
        var diff2Exists = currentJoltageSet.contains(currentJoltage + 2);
        var diff3Exists = currentJoltageSet.contains(currentJoltage + 3);

        long waysPastCurrentJoltage = 0;
        var waysPast1 = getWays(diff1Exists, currentJoltage + 1, currentJoltageSet);
        if (waysPast1.isPresent()) {
            waysPastCurrentJoltage += waysPast1.get();
        }

        var waysPast2 = getWays(diff2Exists, currentJoltage + 2, currentJoltageSet);
        if (waysPast2.isPresent()) {
            waysPastCurrentJoltage += waysPast2.get();
        }

        var waysPast3 = getWays(diff3Exists, currentJoltage + 3, currentJoltageSet);
        if (waysPast3.isPresent()) {
            waysPastCurrentJoltage += waysPast3.get();
        }

        waysPastItem.put(currentJoltage, waysPastCurrentJoltage);
        return waysPastCurrentJoltage;
    }

    private static Optional<Long> getWays(boolean diffExists, int joltage, Set<Integer> currentJoltageSet) {
        if (diffExists && !waysPastItem.containsKey(joltage)) {
            var joltageSet = new HashSet<>(currentJoltageSet);
            joltageSet.remove(joltage);
            return Optional.of(dive(joltage, joltageSet));
        } else if (diffExists && waysPastItem.containsKey(joltage)) {
            return Optional.of(waysPastItem.get(joltage));
        }
        return Optional.empty();
    }
}
