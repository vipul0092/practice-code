package code.vipul.aoc2024;

import java.util.*;

import static code.vipul.aoc2024.inputs.Inputs.DAY_1;

/**
 * https://adventofcode.com/2024/day/1
 */
public class Day1 {

    private static String INPUT = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;

    public static void solve() {
        INPUT = DAY_1;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        Map<Integer, Integer> countInSecond = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(" ");
            int n1 = Integer.parseInt(parts[0]);
            int n2 = Integer.parseInt(parts[parts.length-1]);

            countInSecond.merge(n2, 1, (v1, v2) -> v1 + v2);
            first.add(n1); second.add(n2);
        }

        first.sort(Comparator.naturalOrder());
        second.sort(Comparator.naturalOrder());

        long total = 0;
        long total2 = 0;

        for (int i = 0; i < first.size(); i++) {
            int n = first.get(i);
            total += Math.abs(n - second.get(i));
            total2 += ((long) n * countInSecond.getOrDefault(n, 0));
        }

        System.out.println("Part 1: " + total);
        System.out.println("Part 2: " + total2);
    }
}
