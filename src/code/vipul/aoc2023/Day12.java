package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2023.inputs.Inputs.DAY_12;

/**
 * Created by vgaur created on 11/12/23
 * DP
 */
public class Day12 {

    private static String INPUT = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
            """;
    private static long[][][] dp;

    public static void solve() {
        INPUT = DAY_12;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        long sum = 0;
        long sum2 = 0;
        for (String line : lines) {
            String[] parts = line.split(" ");
            List<Integer> nums = Arrays.stream(parts[1].split(",")).map(Integer::parseInt).toList();
            sum += getAnswer(1, nums, parts[0]);
            sum2 += getAnswer(5, nums, parts[0]);
        }

        System.out.println("Part 1: " + sum); // 7694
        System.out.println("Part 2: " + sum2); // 5071883216318
    }

    private static long getAnswer(int times, List<Integer> nums, String pattern) {
        List<Integer> allnums = new ArrayList<>(nums.size() * times);
        StringBuilder sb = new StringBuilder();
        while (times-- > 0) {
            sb.append(pattern);
            if (times != 0) {
                sb.append('?');
            }
            allnums.addAll(nums);
        }
        String string = sb.toString();
        dp = new long[string.length() + 2][allnums.size() + 2][string.length() + 2];
        return recurse(string, 0, allnums, 0, 0);
    }

    static long recurse(String str, int idx, List<Integer> numbers, int nidx, int hashLen) {
        if (idx == str.length()) {
            if ((nidx == numbers.size() - 1 && numbers.get(nidx) == hashLen)
                    || (nidx == numbers.size() && hashLen == 0)) {
                return 1;
            }
            return 0;
        }

        if (dp[idx][nidx][hashLen] != 0) {
            return dp[idx][nidx][hashLen] - 1;
        }

        char ch = str.charAt(idx);
        long sum = 0;
        if (ch == '?') {
            // place a '#' here and move ahead
            sum += recurse(str, idx + 1, numbers, nidx, hashLen + 1);
            // replace with '.'
            // if hashLen > 0, that means we're creating a group, hence check if the group is valid
            if (hashLen > 0 && nidx < numbers.size() && numbers.get(nidx) == hashLen) {
                sum += recurse(str, idx + 1, numbers, nidx + 1, 0);
            }
            // place a '.'
            if (hashLen == 0) {
                sum += recurse(str, idx + 1, numbers, nidx, 0);
            }
        } else {
            if (ch == '.') {
                // if hashLen > 0, that means we're creating a group, hence check if the group is valid
                if (hashLen > 0 && nidx < numbers.size() && numbers.get(nidx) == hashLen) {
                    sum += recurse(str, idx + 1, numbers, nidx + 1, 0);
                }
                if (hashLen == 0) {
                    sum += recurse(str, idx + 1, numbers, nidx, 0);
                }
            } else { // ch == '#'
                sum += recurse(str, idx + 1, numbers, nidx, hashLen + 1);
            }
        }
        dp[idx][nidx][hashLen] = sum + 1;
        return sum;
    }
}
