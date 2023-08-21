package code.vipul.leetcode;
import java.util.* ;

/**
 * Created by vgaur created on 19/08/23
 * https://www.codingninjas.com/studio/problem-of-the-day/hard
 */
public class ReversePairs {

    public static void solve() {
        System.out.println(new ReversePairs().reversePairs(new int[]{6,4,8,2,1,3}));
    }

    public int reversePairs(int[] nums) {
        int[][] pairs = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, (v1, v2) -> {
            if (v1[0] == v2[0]) return v1[1] - v2[1];
            return v1[0] - v2[0];
        });

        int total = 0;
        for (int i = 1; i < nums.length; i++) {
            int found = search(pairs, (2 * nums[i]) + 1, 0, i-1);
            if (found != -1) {
                total += (pairs.length - found);
            }
        }
        return total;
    }

    private int search(int[][] pairs, int number, int minIndex, int maxIndex) {
        return -1;
    }
}
