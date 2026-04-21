class Solution {
    int dp[][];
    private static final int D = 20 * 1000;
    public int findTargetSumWays(int[] nums, int target) {
        dp = new int[nums.length+1][target+(40*1001)];
        return solve(nums, 0, target);
    }

    private int solve(int[] nums, int idx, int target) {
        if (idx == nums.length) {
            return target == 0 ? 1 : 0;
        }

        if (dp[idx][target+D] != 0) {
            return dp[idx][target+D] - 1;
        }

        int num = nums[idx];
        // -ve num
        int total = solve(nums, idx + 1, target+num)
        // +ve num
        + solve(nums, idx+1, target-num);

        dp[idx][target+D] = total + 1;

        return total;
    }
}
