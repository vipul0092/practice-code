package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 09/09/23
 * https://leetcode.com/problems/combination-sum-iv
 *
 * Tricky
 * DP
 */
public class CombinationSum4 {

    public static void solve() {
        System.out.println(new CombinationSum4().combinationSum4(new int[]{1, 2, 3}, 5));
    }

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        return solve(nums, target);
    }

    private int solve(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        if (target < nums[0]) {
            return 0;
        }

        int answer = 0;
        for (int num : nums) {
            if (num > target) {
                break;
            }
            answer += solve(nums, target - num);
        }
        return answer;
    }
}
