package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://adventofcode.com/2024/day/3
 */
public class Day3 {

    private static final Pattern pattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)");

    public static void solve() {
        String line = String.join("\n", AoCInputReader.read(Day3.class, false));
        long sum2 = Arrays.stream(line.split("do\\(\\)"))
                .map(split -> split.split("don't\\(\\)")[0]) // only first part would be allowed
                .map(Day3::getSum)
                .mapToLong(l -> l)
                .sum();

        System.out.println("Part 1: " + getSum(line)); // 188116424
        System.out.println("Part 2: " + sum2); // 104245808
    }

    private static long getSum(String search) {
        Matcher matcher = pattern.matcher(search);
        return matcher.results()
                .map(res -> (Long.parseLong(res.group(1)) * Long.parseLong(res.group(2))))
                .mapToLong(l -> l).sum();
    }
}
