package code.vipul.leetcode;

import java.util.TreeSet;

/**
 * Created by vgaur created on 24/08/23
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 *
 * Important!
 */
public class LongestIncreasingSubsequence {

    public static void solve() {
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

    // O(n logn) Greedy with binary search
    // https://leetcode.com/problems/longest-increasing-subsequence/solutions/1326308/c-python-dp-binary-search-bit-segment-tree-solutions-picture-explain-o-nlogn/
    // Basically keep forming the increasing subsequence, then if a lower value than the subsequence end comes up
    // then replace the `ciel` item in the subsequence corresponding to that value, with the value itself
    // the logic is that if a higher value comes it can always be attached to the end of the subsequence anyway
    // because the new value is lower than the previous value, so it'll accept whatever the previous value accepted
    // only now it'll also accept lower values that the previous value might not have accepted
    // This can be understood using this array -> [2, 6, 8, 3, 4, 5, 1]
    // We go: 2 -> 2-6 -> 2-6-8 -> 2-3-8 -> 2-3-4 -> 2-3-4-5
    public int lengthOfLIS(int[] nums) {
        // we can use a treeset because of strictly increasing condition
        // Otherwise we'll need to implement our own binary search logic
        TreeSet<Integer> lis = new TreeSet<>();
        lis.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lis.last()) {
                lis.add(nums[i]);
            } else {
                Integer ciel = lis.ceiling(nums[i]);
                lis.remove(ciel);
                lis.add(nums[i]);
            }
        }
        return lis.size();
    }

    // O(n^2) DP
    public int lengthOfLIS2(int[] nums) {
        int[] max = new int[]{1};
        int[][] dp = new int[nums.length+1][nums.length+1];
        solve(nums, 0, 0, max, dp);
        return max[0];
    }

    private int solve(int[] nums, int idx, int prev, int[] max, int[][] dp) {
        if (idx == nums.length-1) {
            if (prev == 0) return 1;
            return nums[prev-1] < nums[idx] ? 1 : 0;
        }

        if (dp[idx][prev] != 0) {
            return dp[idx][prev] - 1;
        }

        //include
        int a1 = 1 + solve(nums, idx+1, idx+1, max, dp);
        // dont include
        int a2 = solve(nums, idx+1, prev, max, dp);
        max[0] = Math.max(max[0], Math.max(a1, a2));

        if (prev != 0 && nums[prev-1] >= nums[idx]) {
            a1 = 0;
        }

        int answer = Math.max(a1, a2);
        dp[idx][prev] = answer + 1;
        return answer;
    }
}
