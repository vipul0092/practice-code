package code.vipul.leetcode;

import java.util.Arrays;

/**
 * Created by vgaur created on 11/08/23
 * https://leetcode.com/problems/split-array-largest-sum/description/
 */
public class SplitArrayLargestSum {

    public static void solve() {
        System.out.println(new SplitArrayLargestSum().splitArray(new int[]{10,5,13,4,8,4,5,11,14,9,16,10,20,8}, 8));
    }

    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }

        int[][] dp = new int[nums.length][51];
        for (int[] d : dp) Arrays.fill(d, -1);
        return solve(0, k, nums, prefix, dp);
    }

    private int solve(int start, int k, int[] nums, int[] prefix, int[][] dp) {
        if (k == 1) { // consider all elements
            return prefix[nums.length-1] - (start == 0 ? 0 : prefix[start - 1]);
        }

        if (dp[start][k] != 0) {
            return dp[start][k] - 1;
        }

        int min = Integer.MAX_VALUE;
        int sum = 0;
        // If we have to make k sub-arrays (k > 1)
        // Then we can't move i till the end, otherwise we wont be able to make k sub arrays
        for (int i = start; i < nums.length - k + 1; i++) {
            // The current array sum
            sum += nums[i];
            if (sum >= min) { // If we have crossed mid, there is no way we can find something less than min
                break;
            }
            // consider k - 1 subarrays in rest of the array
            int v = solve(i + 1, k - 1, nums, prefix, dp);

            min = Math.min(min, Math.max(v, sum));
        }
        dp[start][k] = min + 1;
        return min;
    }
}
