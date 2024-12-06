package code.vipul.aoc2024;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static code.vipul.aoc2024.inputs.Inputs.DAY_3;

/**
 * https://adventofcode.com/2024/day/3
 */
public class Day3 {

    private static String INPUT = """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
            """;

    private static final Pattern pattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)");

    public static void solve() {
        INPUT = DAY_3;
        String line = INPUT;
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
                .map(res ->  (Long.parseLong(res.group(1)) * Long.parseLong(res.group(2))))
                .mapToLong(l -> l).sum();
    }
}
