package code.vipul.leetcode;

import code.vipul.tree.binaryindexedtree.BIT_1D_PointQuery;

/**
 * Created by vgaur created on 16/07/23
 * https://leetcode.com/contest/weekly-contest-354/problems/maximum-beauty-of-an-array-after-applying-operation/
 * AC
 */
public class MaxBeautyOfArray {

    public static void solve() {
        System.out.println(new MaxBeautyOfArray().maximumBeauty(new int[]{1,1,1,1}, 1));
    }

    public int maximumBeauty(int[] nums, int k) {
        int shift = 1_00_005;
        int max_size = (3 * shift) + 1;

        BIT_1D_PointQuery bit = BIT_1D_PointQuery.ofSize(max_size);

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int num : nums) {
            int actual = num + shift;
            bit.range_add(actual - k, actual + k, 1);
            min = Math.min(min, actual - k);
            max = Math.max(max, actual + k);
        }

        int maxVal = 0;
        for (int i = min; i <= max; i++) {
            int queried = bit.point_query(i);
            if (maxVal < queried) {
                maxVal = queried;
            }
        }
        return maxVal;
    }
}
