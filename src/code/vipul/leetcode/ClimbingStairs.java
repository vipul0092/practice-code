package code.vipul.leetcode;

/**
 * Created by vgaur created on 07/06/21
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3770/
 * AC
 */
public class ClimbingStairs {

    public static void test() {
        int[] cost = new int[]{0,6,7,9,1};
        int answer = minCostClimbingStairs(cost);
        System.out.println(answer);
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] mincost = new int[n];

        mincost[0] = 0;
        mincost[1] = 0;

        for (int i = 2; i < n; i++) {
            mincost[i] = Math.min(mincost[i-1] + cost[i-1], mincost[i-2] + cost[i-2]);
        }

        return Math.min(mincost[n-1] + cost[n-1], mincost[n-2] + cost[n-2]);
    }
}
