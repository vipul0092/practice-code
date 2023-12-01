package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 13/09/23
 * https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/description/
 *
 * Really Hard
 * DP bitmask
 */
public class MinimumCostToConnect2GroupsOfPoints {

    public static void solve() {
        System.out.println(new MinimumCostToConnect2GroupsOfPoints().connectTwoGroups(
                List.of(List.of(1,3,5), List.of(4,1,1), List.of(1,5,3))
        ));
        System.out.println(new MinimumCostToConnect2GroupsOfPoints().connectTwoGroups(
                List.of(List.of(15,96), List.of(36,2))
        ));
    }

    private int m;
    private int[][] dp;

    private int full;
    public int connectTwoGroups(List<List<Integer>> cost) {
        int n = cost.size();
        m = cost.get(0).size();
        full = (1<<m)-1;
        dp = new int[n +1][(1<<m)+ 1];

        return solve(cost, 0, 0);
    }

    private int solve(List<List<Integer>> cost, int i, int cmask) {
        if (i == cost.size()) {
            return 0;
        }

        if (dp[i][cmask] != 0) {
            return dp[i][cmask]-1;
        }

        int min = Integer.MAX_VALUE;
        // If its the last item then we must connect to all free nodes, if they havent been connected to already
        boolean shouldConnectToAllFree = i == cost.size() - 1 && cmask != full;
        // I can connect to only one already connected node
        // or a subset of unconnected (free) nodes
        List<Integer> free = new ArrayList<>();
        for (int j = 0; j < m; j++) {
            boolean isConnected = (cmask & (1<<j)) != 0;
            if (!shouldConnectToAllFree && isConnected) { // already connected
                int tcost = cost.get(i).get(j) + solve(cost, i+1, cmask);
                min = Math.min(min, tcost);
            } else if (!isConnected) { // not connected
                free.add(j);
            }
        }

        int freecount = free.size();
        int until = (1 << freecount)-1;

        // Iterate on the unconnected items and
        // connect the current item to a subset of them
        for (int j = (shouldConnectToAllFree ? until : 1); j <= until; j++) {
            int tcost = 0;
            int newmask = 0;
            for (int k = 0; k < freecount; k++) {
                if (((1 << k) & j) != 0) {
                    tcost += cost.get(i).get(free.get(k));
                    newmask |= (1 << free.get(k));
                }
            }
            int fcost = tcost + solve(cost, i+1, cmask | newmask);
            min = Math.min(fcost, min);
        }
        //System.out.println(String.format("MIN COST: %s, for - i:%s, mask:%s", min, i, cmask));
        dp[i][cmask] = min + 1;
        return min;
    }
}
