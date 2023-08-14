package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 11/08/23
 * https://leetcode.com/problems/minimum-cost-to-split-an-array/
 *
 * DP MCM type
 */
public class MinimumCostToSplitArray {

    public static void solve() {
        System.out.println(new MinimumCostToSplitArray().minCost(new int[]{1,2,1,2,1,3,3}, 2));
    }

    public int minCost(int[] nums, int k) {
        int[][] trim = new int[nums.length+1][nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> numbers = new HashSet<>();
            Set<Integer> repeated = new HashSet<>();
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                if (repeated.contains(nums[j])) {
                    count++;
                } else {
                    if (numbers.contains(nums[j])) {
                        repeated.add(nums[j]);
                        numbers.remove(nums[j]);
                        count += 2;
                    } else {
                        numbers.add(nums[j]);
                    }
                }
                trim[i][j] = count;
            }
        }

        int[] dp = new int[nums.length + 1];
        int ans = solve(0, nums.length-1, k, dp, trim);
        return ans;
    }

    public int solve(int i, int n, int k, int[] dp, int[][] trim) {
        if (i == n) {
            return k;
        }

        if (dp[i] != 0) {
            return dp[i] - 1;
        }

        int min = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            int v = k + trim[i][j] + solve(j + 1, n, k, dp, trim);
            min = Math.min(min, v);
        }

        dp[i] = min + 1;
        return min;
    }
}
