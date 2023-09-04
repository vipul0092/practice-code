package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 21/08/23
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 * Really hard
 */
public class CountOfSmallerNumbersAfterSelf {

    public static void solve() {
        System.out.println(new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{10,5,6,3,13,10,8,5,2,13,4}));
    }

    private int[] tree;
    private static final int BASE = 10_001;
    // Uses segment tree
    public List<Integer> countSmaller(int[] nums) {
        int n = 2 * BASE;
        tree = new int[4*n];

        Stack<Integer> values = new Stack<>();
        for (int i = nums.length-1; i>=0; i--) {
            int actual = nums[i] + BASE;
            int less = query(0, actual-1, 0, n, 0);
            values.push(less);
            update(actual, 1, 0, n, 0);
        }
        List<Integer> ret = new ArrayList<>();
        while(!values.isEmpty()) ret.add(values.pop());
        return ret;
    }

    private int query(int lo, int hi, int tlo, int thi, int idx) {
        if (lo > thi || hi < tlo) return 0;

        if (lo == tlo && thi == hi) return tree[idx];

        int mid = (tlo+thi)/2, left = (2*idx)+1, right = (2*idx)+2;
        return query(lo, Math.min(hi, mid), tlo, mid, left)
                + query(Math.max(mid+1, lo), hi, mid + 1, thi, right);
    }

    private void update(int idx, int val, int lo, int hi, int tidx) {
        if (idx < lo || idx > hi) return;
        if (lo == hi) {
            tree[tidx] += val;
            return;
        }

        int mid = (lo+hi)/2, left = (2*tidx)+1, right = (2*tidx)+2;
        if (idx <= mid) update(idx, val, lo, mid, left);
        else update(idx, val, mid+1, hi, right);

        tree[tidx] = tree[right] + tree[left];
    }

    // Uses merge sort
    public List<Integer> countSmaller2(int[] nums) {
        int[] count = new int[nums.length];

        int[] indices = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indices[i] = i;
        }
        int[] indicesCopy = Arrays.copyOf(indices, indices.length);
        merge(nums, count, indices, indicesCopy, 0, nums.length-1);
        return Arrays.stream(count).boxed().toList();
    }

    private void merge(int[] arr, int[] count, int[] indices, int[] indicesCopy, int lo, int hi) {
        if (lo == hi) {
            return;
        }

        int mid = (lo+hi)/2;
        merge(arr, count, indicesCopy, indices, lo, mid);
        merge(arr, count, indicesCopy, indices,mid+1, hi);

        int i = lo, j = mid+1, k = lo;
        while(k <= hi) {
            if (i <= mid && j <= hi) {
                if (arr[indicesCopy[i]] > arr[indicesCopy[j]]) {
                    if (indicesCopy[i] < indicesCopy[j]) {
                        // Since there would be hi - j + 1 values greater
                        count[indicesCopy[i]]+=(hi - j + 1);
                    }
                    indices[k++] = indicesCopy[i++];
                } else {
                    indices[k++] = indicesCopy[j++];
                }
            } else if (i <= mid) {
                indices[k++] = indicesCopy[i++];
            } else {
                indices[k++] = indicesCopy[j++];
            }
        }
    }
}
