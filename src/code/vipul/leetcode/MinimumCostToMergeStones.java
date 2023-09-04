package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 28/08/23
 * https://leetcode.com/problems/minimum-cost-to-merge-stones/description/
 *
 * Really Hard
 * DP - MCM Type
 *
 * UNSOLVED
 */
public class MinimumCostToMergeStones {

    public static void solve() {
        System.out.println(new MinimumCostToMergeStones().mergeStones(new int[]{3,5,1,2,6}, 3));
    }

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if (((n-1) % (k-1)) != 0) return -1;
        if (n == 1) return 0;
        int[] prefix = new int[n+1];
        prefix[0] = 0;
        prefix[1] = stones[0];
        for (int i=1; i < n; i++) {
            prefix[i+1] = stones[i] + prefix[i];
        }
        Result res = merge(stones, k, 0, n-1, prefix);
        return res.cost;
    }

    private Result merge(int[] stones, int k, int i, int j, int[] prefix) {
        if (j-i+1 == k) {
            // Add all values as the cost
            Result res = new Result(prefix[j+1]-prefix[i]);
            res.values.add(res.cost);
            return res;
        }

        if (j-i+1 < k) {
            Result res = new Result(0); // 0 cost - means no merging
            return res;
        }

        int minCost = Integer.MAX_VALUE;
        List<Integer> values = new ArrayList<>();

        for (int m = i; m <= j; m++) {
            Result res1 = merge(stones, k, i, m, prefix);
            Result res2 = merge(stones, k, m+1, j, prefix);

            int mergeCost = res1.cost + res2.cost;
            if (mergeCost < minCost) {
                minCost = mergeCost;
                List<Integer> temp = new ArrayList<>();
                if (res1.cost == 0) add(stones, i, m, temp);
                else temp.addAll(res1.values);

                if (res2.cost == 0) add(stones, m+1, j, temp);
                else temp.addAll(res2.values);
                values = temp;
            }
        }

        Result res = new Result(minCost);
        res.values = values;
        return res;
    }

    private class Result {
        private int cost;
        private List<Integer> values;

        Result(int c) {
            this.cost = c;
            this.values = new ArrayList<>(1);
        }
    }

    private void add(int[] arr, int i, int j, List<Integer> list) {
        for (int k = i; k <= j; k++) list.add(arr[k]);
    }
}
