package code.vipul.leetcode;

/**
 * Created by vgaur created on 30/06/23
 * Works on the same concept as the sub string problems
 */
public class MinimumSizeSubarraySum {
    public static void solve() {
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(11, new int[]{1,2,3,4,5}));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int end = 0, start = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;

        while(end < nums.length) {
            sum += nums[end];

            while (sum >= target) {
                if (minLen > end - start + 1) {
                    minLen = end - start + 1;
                }
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
