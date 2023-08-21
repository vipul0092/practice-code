package code.vipul.leetcode;

/**
 * Created by vgaur created on 18/08/23
 */
public class MaximumProductSubarray {

    public static void solve() {
        System.out.println(new MaximumProductSubarray().maxProduct(new int[]{-1,1,1,-5,3,-4,1,1,1,-1}));
    }

    public int maxProduct(int[] nums) {
        int max = -11, pre = 1, suff = 1;
        for (int i = 0; i < nums.length; i++) {
            pre = pre * nums[i];
            suff = suff * nums[nums.length-1-i];
            max = Math.max(Math.max(pre, suff), max);
            if (pre == 0)
                pre = 1;
            if (suff == 0)
                suff = 1;
        }
        return max;
    }
}
