package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 26/07/23
 * https://leetcode.com/problems/minimum-speed-to-arrive-on-time/description
 */
public class MinimumSpeedToArriveOnTime {

    private static final int[] arr = new int[]{1,3,2};

    public static void solve() {
        System.out.println(new MinimumSpeedToArriveOnTime().minSpeedOnTime(arr, 2.01));
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int max = 0;
        int min = 1;
        for (int d : dist) {
            max = Math.max(max, d);
        }
        max = Math.max(max, dist[dist.length - 1] * 100);

        double maxTime = getTime(dist, max);
        if (maxTime > hour) {
            return -1;
        }

        return search(dist, min, max, hour);
    }

    private int search(int[] dist, int min, int max, double target) {
        if (min == max) {
            double time = getTime(dist, min);
            return time <= target ? min : -1;
        }
        if (max - min == 1) {
            double time = getTime(dist, min);
            if (time <= target) {
                return min;
            }

            time = getTime(dist, max);
            if (time <= target) {
                return max;
            }
            return -1;
        }
        if (min > max) {
            return -1;
        }

        int mid = (min + max) / 2;
        double midTime = getTime(dist, mid);

        if (midTime > target) {
            return search(dist, mid, max, target);
        } else {
            return search(dist, min, mid, target);
        }
    }

    private double getTime(int[] d, int speed) {
        double time = 0;
        for (int i = 0; i < d.length - 1; i++) {
            time += (d[i] % speed == 0 ? d[i]/speed : (d[i]/speed) + 1);
        }
        time += ((double)d[d.length-1] / (double)speed);
        return time;
    }
}
