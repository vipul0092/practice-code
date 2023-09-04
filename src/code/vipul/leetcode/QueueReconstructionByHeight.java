package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 28/08/23
 * https://leetcode.com/problems/queue-reconstruction-by-height/description/
 *
 * Hard
 * Google
 * Segment tree - find kth element
 */
public class QueueReconstructionByHeight {

    public static void solve() {
        System.out.println(Arrays.deepToString(new QueueReconstructionByHeight()
                .reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}})));
    }

    // Sort the array first by height and then by number
    // Then we need to place the items one by one at the k+1th empty position where `k` is the number for that item
    // So for above test case, the sorted array becomes
    // (4,4) (5,2) (5,0) (6,1) (7,2) (7,0)
    // Why k+1 th empty position? because we want atleast k items having higher heights to be before that item
    // So we leave empty space for them
    //
    // Now, this can be done in O(n^2) by iterating over the returning array again and again
    // to find the k+1th empty space
    //
    // To improve upon this, we can use segment tree
    // The segment will have the count of the empty spaces in the returning array
    // And each time, we'll find the index of the k+1th empty space
    // and then update the tree to fill the empty space
    // A segment tree supports these operations, and can be done in O(log n)
    //
    // So final complexity becomes [sort]O(n logn) + [construct initial tree]O(n) + [iterate and place]O(n logn)
    // i.e. O(n logn)
    private int[] tree;
    private static final Comparator<int[]> SORT = (p1, p2) -> {
        if (p1[0] == p2[0]) return p2[1] - p1[1];
        return p1[0] - p2[0];
    };
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        if (n == 1) return people;
        int nmax = n-1;
        tree = new int[4*nmax];
        build(0, nmax, 0);

        Arrays.sort(people, SORT);

        int[][] ret = new int[n][2];
        for (int i = 0; i < n; i++) {
            int toSearch = people[i][1]+1;
            int idx = queryKth(toSearch, 0, 0, nmax);
            update2(idx, 0, 0, 0, nmax);
            ret[idx][0] = people[i][0];
            ret[idx][1] = people[i][1];
        }
        return ret;
    }

    private void build(int lo, int hi, int tidx) {
        if (lo == hi) {
            tree[tidx] = 1;
            return;
        }
        int mid = (lo+hi)>>1, left = (tidx<<1)+1, right = left+1;
        build(lo, mid, left);
        build(mid+1, hi, right);

        tree[tidx] = tree[left] + tree[right];
    }

    private int queryKth(int pos, int idx, int lo, int hi) {
        if  (hi == lo) {
            return hi;
        }

        int left = (idx<<1)+1, right = left+1, mid = (hi+lo)>>1;
        if (pos <= tree[left]) {
            return queryKth(pos, left, lo, mid);
        } else {
            return queryKth(pos - tree[left], right, mid+1, hi);
        }
    }

    private void update2(int idx, int val, int tidx, int lo, int hi) {
        if (idx < lo || idx > hi) return;

        if (lo == hi) {
            tree[tidx] = val;
            return;
        }

        int mid = (lo+hi)>>1, left = (tidx<<1)+1, right = left+1;
        if (idx <= mid) update2(idx, val, left, lo, mid);
        else update2(idx, val, right, mid+1, hi);

        tree[tidx] = tree[left] + tree[right];
    }

    // My stupidest initial O(n^2 logn) solution - AC
    public int[][] reconstructQueue2(int[][] people) {
        Set<Integer> done = new HashSet<>();
        int n = people.length;
        int nmax = 1_000_001;
        tree = new int[4*nmax];
        int[][] ret = new int[n][2];
        int ridx = 0;

        while(done.size() != n) {
            int minval = Integer.MAX_VALUE;
            int index = -1;
            for (int j = 0; j < n; j++) {
                if (done.contains(j)) continue;

                int[] candidate = people[j];
                int greaterOrEqualToCandidate = query(candidate[0], nmax-1, 0, nmax-1, 0);
                if (greaterOrEqualToCandidate == candidate[1] && minval > candidate[0]) {
                    minval = candidate[0];
                    index = j;
                }
            }
            done.add(index);
            update(minval, 1, 0, 0, nmax-1);
            ret[ridx++] = people[index];
        }
        return ret;
    }

    private int query(int lo, int hi, int tlo, int thi, int idx) {
        if (lo > thi || hi < tlo) return 0;

        if (lo == tlo && thi == hi) return tree[idx];

        int mid = (tlo+thi)/2, left = (2*idx)+1, right = (2*idx)+2;
        return query(lo, Math.min(hi, mid), tlo, mid, left)
                + query(Math.max(mid+1, lo), hi, mid + 1, thi, right);
    }

    private void update(int idx, int val, int tidx, int lo, int hi) {
        if (idx < lo || idx > hi) return;

        if (lo == hi) {
            tree[tidx] += val;
            return;
        }

        int mid = (lo+hi)/2, left = (2*tidx)+1, right = left+1;
        if (idx <= mid) update(idx, val, left, lo, mid);
        else update(idx, val, right, mid+1, hi);

        tree[tidx] = tree[left] + tree[right];
    }
}
