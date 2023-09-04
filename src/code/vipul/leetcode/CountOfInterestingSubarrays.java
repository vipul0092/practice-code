package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 03/09/23
 * https://leetcode.com/problems/count-of-interesting-subarrays/description/
 */
public class CountOfInterestingSubarrays {

    public static void solve() {
        System.out.println(new CountOfInterestingSubarrays().countInterestingSubarrays(
                List.of(4,6,8,5,8,5,7,5,7), 6, 5
        ));
    }

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] present = new int[n];
        for (int i = 0; i < n; i++) {
            present[i] = nums.get(i) % modulo == k ? 1 : 0;
        }

        long count = 0;
        int prefix = 0;
        Map<Integer, Integer> prefixCounts = new HashMap<>();
        for (int pre : present) {
            prefix += pre;
            // If current prefix is P, we need to find P - (c*modulo + k), where c is a constant

            int curMod = prefix % modulo;
            int required = curMod == k ? 0 : curMod > k
                    ? (curMod - k)
                    : (modulo - (k-curMod));

            count += prefixCounts.getOrDefault(required, 0);
            if (curMod == k) {
                count++;
            }

            prefixCounts.merge(curMod, 1, (v1, v2) -> v1+v2);
        }
        return count;
    }
}
