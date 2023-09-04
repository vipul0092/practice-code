package code.vipul.leetcode;


import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by vgaur created on 25/08/23
 * https://leetcode.com/problems/count-increasing-quadruplets/
 *
 * Really Hard
 */
public class CountIncreasingQuadruplets {

    public static void solve() {
        System.out.println(new CountIncreasingQuadruplets().countQuadruplets(new int[]{1,3,2,4,5}));
    }

    public long countQuadruplets(int[] nums) {
        int n = nums.length;

        int[][] after = new int[n][n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = n-2; j>=i; j--) {
                after[i][j] = after[i][j+1];
                if (nums[j+1] > num) {
                    after[i][j]++;
                }
            }
        }

        int[][] before = new int[n][n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j<=i; j++) {
                before[i][j] = before[i][j-1];
                if (nums[j-1] < num) {
                    before[i][j]++;
                }
            }
        }

        long total = 0;
        for (int i=0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (after[i][j] == 0 || before[j][i] == 0 || nums[i] < nums[j]) continue;
                total += ((long)after[i][j] * before[j][i]);
            }
        }
        return total;
    }
}
