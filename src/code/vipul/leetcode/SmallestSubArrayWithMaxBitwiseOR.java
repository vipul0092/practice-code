package code.vipul.leetcode;

import java.util.Arrays;

/**
 * Created by vgaur created on 07/08/23
 * https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/description/
 */
public class SmallestSubArrayWithMaxBitwiseOR {

    public static void solve() {
        System.out.println(Arrays.toString(new SmallestSubArrayWithMaxBitwiseOR()
                .smallestSubarrays2(new int[]{1,0,2,1,3})));
    }

    private static final int MAX_BITS = 31;

    public int[] smallestSubarrays2(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        int[] lastset = new int [MAX_BITS];

        for (int i = n - 1; i >= 0; i--) {
            int farthest = i;
            for (int j = 0; j < MAX_BITS; j++) {
                if ((nums[i] & (1 << j)) != 0) {
                    lastset[j] = i + 1;
                }
                if (lastset[j] != 0) {
                    farthest = Math.max(farthest, lastset[j] - 1);
                }
            }
            ret[i] = farthest - i + 1;
        }
        return ret;
    }

    // very complicated but works
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[][] prefix = new int[n][MAX_BITS];
        int[][] suffix = new int[n][MAX_BITS];
        fill(prefix[0], null, nums[0]);
        fill(suffix[n-1], null, nums[n-1]);

        for (int i = 1, j = n-2; i < n; i++, j--) {
            fill(prefix[i], prefix[i-1], nums[i]);
            fill(suffix[j], suffix[j+1], nums[j]);
        }

        int[] remove = new int[MAX_BITS];
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int fullor = getNum(suffix[i], null);
            int idx = findIndex(prefix, fullor, remove, n, i);
            ret[i] = idx - i + 1;
            fill(remove, remove, nums[i]);
        }

        return ret;
    }

    private int findIndex(int[][] prefix, int num, int[] remove, int n, int startMin) {
        int start = startMin, end = n-1;
        while(start < end) {
            if (end - start == 1) {
                return getNum(prefix[start], remove) == num
                        ? start
                        : end;
            }

            int mid = (end + start) / 2;
            if (getNum(prefix[mid], remove) == num) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return start;
    }

    private void fill(int[] bits, int[] prev, int num) {
        for (int i = 0; i < MAX_BITS; i++) {
            boolean set = (num & (1 << i)) != 0;
            int prevVal = prev == null ? 0 : prev[i];
            bits[i] = prevVal + (set ? 1 : 0);
        }
    }

    private int getNum(int[] bits, int[] remove) {
        int num = 0;
        for (int i = 0; i < MAX_BITS; i++) {
            int remCnt = remove == null ? 0 : remove[i];
            if (bits[i] - remCnt > 0) {
                num += (1 << i);
            }
        }
        return num;
    }
}
