package code.vipul.leetcode;

/**
 * Created by vgaur created on 30/08/23
 * https://leetcode.com/problems/minimum-replacements-to-sort-the-array/
 *
 * Hard
 * Google
 */
public class MinimumReplacementsToSortArray {

    public static void solve() {
        System.out.println(new MinimumReplacementsToSortArray()
                .minimumReplacement(new int[] {821,881,275})); //6
        System.out.println(new MinimumReplacementsToSortArray()
                .minimumReplacement(new int[] {3,10,3})); //4
    }

    public long minimumReplacement(int[] nums) {
        int n = nums.length;

        long total = 0;
        int prev = nums[n-1];
        for (int i = n-2; i >= 0; i--) {
            int current = nums[i];
            if (current <= prev) {
                prev = current;
                continue;
            }
            int divisions = current/prev;
            if (current % prev == 0) {
                total += (divisions - 1);
            } else {
                int next = current/(divisions+1);
                total += divisions;
                prev = next;
            }
        }
        return total;
    }
}
