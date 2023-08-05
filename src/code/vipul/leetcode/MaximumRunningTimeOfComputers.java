package code.vipul.leetcode;

import java.util.*;

/**
 * Created by vgaur created on 27/07/23
 * https://leetcode.com/problems/maximum-running-time-of-n-computers/description/
 *
 * Explanation: (using binary search)
 * For running 'n' computers for time 't', we need 'n*t' units of energy
 * And for running time 't', each battery can only contribute a maximum of 't' units of energy
 * (because it can only be used for maximum 't' time)
 *
 * Now, the minimum possible runtime is 1, we can take an arbitrarily high number as the top limit, like 10^13
 * and then we can binary search the max runtime in this range
 *
 * For time `midt = min + max / 2`
 * That runtime is possible when max battery energy production >= energy required for runtime midt
 * and max battery energy production = Summation of all battery capacities, with max being midt
 * energy required for runtime midt = n * midt (number of computers * runtime)
 *
 * For the binary search algo, we keep adjusting min and max, according to the runtime midt being possible.
 * If runtime at midt is possible, that means the maximum value lies between midt and max
 * if runtime at midt is impossible, that means the maximum value lies between min and midt
 */
public class MaximumRunningTimeOfComputers {

    public static void solve() {
        System.out.println(new MaximumRunningTimeOfComputers().maxRunTime(12, new int[]{11,89,16,32,70,67,35,35,31,24,41,29,6,53,78,83}));
        System.out.println(new MaximumRunningTimeOfComputers().maxRunTime(5, new int[]{15,14,13,12,11,10,9,8,7,6,5,4,3,2,1}));
    }

    public long maxRunTime(int n, int[] batteries) {
        if (batteries.length < n) {
            return 0;
        }
        int minVal = Arrays.stream(batteries).min().getAsInt();
        if (batteries.length == n) {
            return minVal;
        }

        long min = 1;
        long max = 10_000_000_000_001L;

        while(min < max) {
            if (max - min == 1) {
                if (isPossible(batteries, min, n)) {
                    return min;
                }
                if (isPossible(batteries, max, n)) {
                    return max;
                }
                return 0;
            }

            long mid = (min + max) / 2;
            boolean possible = isPossible(batteries, mid, n);
            if (possible) {
                min = mid;
            } else {
                max = mid;
            }
        }
        return 0;
    }

    private boolean isPossible(int[] batteries, long time, int n) {
        long unitsRequired = time * n;
        long maxPossible = 0;
        for (int b : batteries) {
            maxPossible += (b > time ? time : b);
        }
        return unitsRequired <= maxPossible;
    }
}
