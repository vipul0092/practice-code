package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_24;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day24 {

    private static String INPUT = """
            1
            2
            3
            4
            5
            7
            8
            9
            10
            11
            """;

    private static Res[][] dp;
    record Res(int len, long q){}
    private static final Res SENTINEL = new Res(-1, -1);

    public static void solve() {
        INPUT = DAY_24;
        List<Integer> packages = Arrays.stream(INPUT.split("\n")).map(s -> Integer.parseInt(s)).toList();
        packages = packages.stream().sorted(Comparator.reverseOrder()).toList();
        int requiredSum = packages.stream().mapToInt(i -> i).sum() / 3;

        dp = new Res[requiredSum+1][packages.size()+1];
        Res res = recurse(requiredSum, packages, packages.size());
        System.out.println("Part 1: " + res.q); // 11846773891

        dp = new Res[requiredSum+1][packages.size()+1];
        requiredSum = (requiredSum * 3) / 4;
        res = recurse(requiredSum, packages, packages.size());
        System.out.println("Part 2: " + res.q); // 80393059
    }

    // 0-1 knapsack DP solution
    private static Res recurse(int sum, List<Integer> packages, int len) {
        if (sum == 0) {
            return new Res(0, 1);
        }
        if (len == 0) {
            return SENTINEL;
        }

        if (dp[sum][len] != null) {
            return dp[sum][len];
        }

        // take
        Res best = SENTINEL;
        int pkg = packages.get(len-1);
        if (sum >= pkg) {
            Res res = recurse(sum - pkg, packages, len-1);
            if (res != SENTINEL) {
                res = new Res(1 + res.len, pkg * res.q);
            }
            best = res;
        }
        // do not take
        Res res = recurse(sum, packages, len-1);
        if (best == SENTINEL || (res != SENTINEL && (res.len < best.len || res.len == best.len && res.q < best.q))) {
            best = res;
        }
        dp[sum][len] = best;
        return best;
    }
}
