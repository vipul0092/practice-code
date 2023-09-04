package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 29/08/23
 * https://leetcode.com/problems/burst-balloons/description/
 *
 * Really Hard
 * DP - MCM Type
 */
public class BurstBalloons {

    public static void solve() {
        System.out.println(new BurstBalloons().maxCoins2(new int[]{3,1,5,8}));
    }

    // AC - classic MCM DP implementation, just add 1 to left and right of the original array
    // then bursting a balloon at pos k, gives points equal to the scalar multiplication cost of matrices k-1,k and k,k+1
    // E.g. for the above array, bursting the balloon at `1` gives 3 * 1 * 5 points
    // which is equivalent to scalar multiplication cost of 3 * 1 and 1 * 5 matrices
    public int maxCoins(int[] _nums) {
        int n = _nums.length;
        int[][] dp = new int[n+2][n+2];
        List<Integer> nums = new ArrayList<>(n+2);
        nums.add(1);
        for (int num : _nums) nums.add(num);
        nums.add(1);
        return solve(nums, 1, n+1, dp);
    }

    private int solve(List<Integer> nums, int i, int j, int[][] dp) {
        if (i >= j) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j]-1;
        }

        int max = 0;
        for (int k = i; k <= j-1; k++) {
            int tans = solve(nums, i, k, dp)
                    + solve(nums, k+1, j, dp)
                    + nums.get(i-1) * nums.get(k) * nums.get(j);

            max = Math.max(tans, max);
        }
        dp[i][j] = max+1;
        return max;
    }

    // My own solution that I came up with some minor optimization by reading online
    public int maxCoins2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        return solve(nums, 0, nums.length-1, dp);
    }

    private int solve(int[] nums, int i, int j, int[][] dp) {
        if (j==i) {
            return nums[j] * (i-1 >= 0 ? nums[i-1] : 1) * (j+1 <= nums.length-1 ? nums[j+1] : 1);
        }
        int max = 0;
        if (dp[i][j] != 0) {
            return dp[i][j]-1;
        }

        for (int k = i; k <= j; k++) {
            // fix kth balloon, and burst left and right arrays
            // i.e. the kth balloon will be burst in the end
            int fixed = nums[k];
            int answer = -1;
            if (k == i) {
                answer = solve(nums, k+1, j, dp);
            } else if (k == j) {
                answer = solve(nums, i, k-1,  dp);
            } else {
                int sub1 = solve(nums, k+1, j, dp);
                int sub2 = solve(nums, i, k-1, dp);
                answer = sub1 + sub2;
            }
            answer += (fixed * (i-1 >= 0 ? nums[i-1] : 1) * (j+1 <= nums.length-1 ? nums[j+1] : 1));
            max = Math.max(answer, max);
        }
        dp[i][j]= max+1;
        return max;
    }
}
