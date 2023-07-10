package code.vipul.leetcode;

import code.vipul.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by vgaur created on 07/07/23
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/
 */
public class MaximumProfitJobScheduling {

    public static void solve() {
        System.out.println(new MaximumProfitJobScheduling()
                .jobScheduling(new int[]{1,1,1}, new int[]{2,3,4}, new int[]{5,6,4})
        );
    }

    // Classic 0-1 Knapsack DP - AC
    //
    // Logic:
    // Iterate over sorted end times, for each end time there would be k jobs having that end time
    // Decide whether to include a particular job or not
    // Include the job -> profit of job + max profit from non-overlapping jobs (i.e. jobs ending before or on start)
    // Don't include the job -> max profit until now
    // Take maximum of above two cases
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Map<Integer, Map<Integer, Integer>> endToStart = new HashMap<>();
        TreeSet<Integer> endValuesSet = new TreeSet<>();
        for (int i = 0; i < startTime.length; i++) {
            if (!endToStart.containsKey(endTime[i])) {
                endToStart.put(endTime[i], new HashMap<>());
            }
            endToStart.get(endTime[i]).put(startTime[i], profit[i]);
            endValuesSet.add(endTime[i]);
        }

        int maxUntilPrev = 0;
        TreeMap<Integer, Integer> maxUntil =  new TreeMap<>();

        for (int end : endValuesSet) {
            int max = maxUntilPrev;

            for (Map.Entry<Integer, Integer> entry : endToStart.get(end).entrySet()) {
                int start = entry.getKey();
                int totalProfit = entry.getValue();

                Map.Entry<Integer, Integer> previousNonOverlappingMax = maxUntil.floorEntry(start);
                if (previousNonOverlappingMax != null) {
                    totalProfit += previousNonOverlappingMax.getValue();
                }

                max = Math.max(totalProfit, max);
            }

            maxUntilPrev = max;
            maxUntil.put(end, max);
        }
        return maxUntilPrev;
    }


    // Brute force partitioning DP - TLE
    public int jobSchedulingBruteForceDP(int[] startTime, int[] endTime, int[] profit) {
        Map<Integer, Map<Integer, Integer>> endToStart = new HashMap<>();
        TreeSet<Integer> valueset = new TreeSet<>();
        for (int i = 0; i < startTime.length; i++) {
            if (!endToStart.containsKey(endTime[i])) {
                endToStart.put(endTime[i], new HashMap<>());
            }
            endToStart.get(endTime[i]).put(startTime[i], profit[i]);
            valueset.add(endTime[i]);
            valueset.add(startTime[i]);
        }

        int[] values = new int[valueset.size()];
        int ctr = 0;
        for (int v : valueset) {
            values[ctr++] = v;
        }

        Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();
        int[][] cache = new int[valueset.size() + 1][valueset.size() + 1];
        int ans = solve(values, 0, values.length - 1, cache, endToStart);
        return ans;
    }

    public int solve(int[] values, int i, int j,
                     int[][] memo,
                     Map<Integer, Map<Integer, Integer>> endToStart) {
        if (i >= j) {
            return 0;
        }

        // Pair<Integer, Integer> key = new Pair<>(i, j);
        if (memo[i][j] > 0) {
            return memo[i][j] - 1;
        }

        int max = 0;
        int start = values[i];
        int end = values[j];
        if (endToStart.containsKey(end) && endToStart.get(end).containsKey(start)) {
            max = endToStart.get(end).get(start);
        }

        for (int k = i + 1; k < j; k++) {
            int left = 0;
            if (memo[i][k] > 0) {
                left = memo[i][k] - 1;
            }  else {
                left = solve(values, i, k, memo, endToStart);
            }

            int right = 0;
            if (memo[k][j] > 0) {
                right = memo[k][j] - 1;
            }  else {
                right = solve(values, k, j, memo, endToStart);
            }

            int res = left + right;
            max = Math.max(max, res);
        }

        memo[i][j] = max + 1;
        return max;
    }
}
