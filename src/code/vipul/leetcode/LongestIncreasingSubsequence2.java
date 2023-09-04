package code.vipul.leetcode;

import code.vipul.tree.SegmentTree;

import java.util.Arrays;

/**
 * Created by vgaur created on 25/08/23
 * https://leetcode.com/problems/longest-increasing-subsequence-ii/description/
 *
 * Segment Tree
 * Hard
 */
public class LongestIncreasingSubsequence2 {

    public static void solve() {
        System.out.println(new LongestIncreasingSubsequence2().lengthOfLIS(new int[]{4,2,1,4,3,4,5,8,15}, 3));
    }

    public int lengthOfLIS(int[] nums, int k) {
        SegmentTree t = new SegmentTree(1_00_001);
        int max = 0;
        for (int num : nums) {
            int lower = Math.max(0, num - k);
            int len = 1 + t.query(lower, num-1);
            t.update(num, len);
            if (len > max) {
                max = len;
            }
        }
        return max;
    }
}
