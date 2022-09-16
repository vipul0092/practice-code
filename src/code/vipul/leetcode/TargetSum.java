package code.vipul.leetcode;

/**
 * Created by vgaur created on 19/06/22
 * https://leetcode.com/problems/target-sum/
 */
public class TargetSum {

    public static void solve() {
        int[] nums = new int[]{3,4,7};
        int target = 0;

        int answer = findTargetSumWays(nums, target);
        System.out.println(answer);
    }

    private static int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int total = 0;
        for (int num : nums) {
            total += num;
        }

        if (target > total || target < -total) {
            return 0;
        }

        int until = total/2;

        int searchFor = -1;
        for (int i = 0; i <= until; i++) {
            int other = total - i;
            int diff = other - i;

            if (diff == target || diff == -target) {
                searchFor = i;
                break;
            }
        }

        if (searchFor == -1) {
            return 0;
        }

        int[][] t = new int[n+1][total+1];


        int zeroCount = 0;
        for (int i = 0; i <= n; i++) {
            if (i < n && nums[i] == 0) {
                zeroCount++;
            }
            t[i][0] = 1 << zeroCount;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= total; j++) {
                if (nums[i-1] <= j) {
                    t[i][j] = t[i-1][j-nums[i-1]] + t[i-1][j];
                } else {
                    t[i][j] = t[i-1][j];
                }
            }
        }

        return t[n][searchFor];
    }
}
