package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 16/09/23
 * https://www.codingninjas.com/studio/problems/maximum-sum_1467395
 *
 * Hard
 * Segment Tree
 *
 * AC
 */
public class MaximumSubarraySumQueriesII {

    public static void solve() {
        System.out.println(maximumSum(new int[]{1,2,3,4,5}, new int[][]{{2,2,4}, {1,2,6}, {2,2,4}}));
    }

    private static int[][] tree;
    public static ArrayList<Integer> maximumSum(int[] arr, int[][] queries)
    {
        int n = arr.length;
        tree = new int[4*n][2];

        build(arr, 0, 0, n-1);

        ArrayList<Integer> answer = new ArrayList<>(queries.length);

        for (int[] query : queries) {
            if (query[0] == 2) { //find
                int[] val = query(query[1]-1, query[2]-1, 0, 0, n-1);
                answer.add(val[0]+val[1]);
            } else {
                update(query[2], query[1]-1, 0, 0, n-1);
            }
        }
        return answer;
    }

    private static void build(int[] arr, int tidx, int lo, int hi) {

        if (lo == hi) {
            tree[tidx][0] = arr[lo];
            tree[tidx][1] = 0;
            return;
        }

        int mid = (hi+lo)>>1, left = (tidx<<1)+1, right = left + 1;
        build(arr, left, lo, mid);
        build(arr, right, mid+1, hi);
        combine(tree[left], tree[right], tree[tidx]);
    }

    private static void update(int val, int idx, int tidx, int lo, int hi) {
        if (idx < lo || idx > hi) {
            return;
        }

        if (lo == hi) {
            tree[tidx][0] = val;
            tree[tidx][1] = 0;
            return;
        }

        int mid = (hi+lo)>>1, left = (tidx<<1)+1, right = left + 1;
        if (idx <= mid) update(val, idx, left, lo, mid);
        else update(val, idx, right, mid+1, hi);
        combine(tree[right], tree[left], tree[tidx]);
    }

    private static int[] query(int lo, int hi, int tidx, int tlo, int thi) {
        if (hi < tlo || lo > thi) {
            return new int[]{0,0};
        }

        if (lo == tlo && thi == hi) {
            return tree[tidx];
        }

        int mid = (thi+tlo)>>1, left = (tidx<<1)+1, right = left + 1;
        int[] l = query(lo, Math.min(hi, mid), left, tlo, mid);
        int[] r = query(Math.max(lo, mid+1), hi, right, mid+1, thi);

        int[] ret = new int[]{0,0};
        combine(l, r, ret);
        return ret;
    }

    private static void combine(int[] l, int[] r, int[] d) {
        int m1 = l[0], m2 = l[1];
        if (r[0] > m1) {
            m2 = m1;
            m1 = r[0];
        } else if (r[0] > m2) {
            m2 = r[0];
        }

        if (r[1] > m1) {
            m2 = m1;
            m1 = r[1];
        } else if (r[1] > m2) {
            m2 = r[1];
        }
        d[0] = m1;
        d[1] = m2;
    }
}
