package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 22/08/23
 * https://leetcode.com/problems/count-good-triplets-in-an-array/
 *
 * Really hard
 */
public class CountGoodTripletsInArray {

    public static void solve() {
        System.out.println(new CountGoodTripletsInArray().goodTriplets(
                new int[]{2,3,9,8,4,7,0,6,5,1}, new int[]{8,7,9,5,6,2,4,3,1,0}
        ));
    }

    // Segment tree
    private int[] tree;
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] indices1 = new int[n];

        for (int i = 0; i < n; i++) {
            indices1[nums1[i]] = i;
        }

        // This indices array contains the indices of the numbers in nums2
        // according to what it was in nums1
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = indices1[nums2[i]];
        }

        long[] before = new long[n];
        long[] after = new long[n];
        tree = new int[4*n];

        for (int i = 0; i < n; i++) {
            int index = indices[i];
            before[i] = index == 0 ? 0 : query(0, index-1, 0, n, 0);
            update(index, 1, 0, n, 0);
        }

        tree = new int[4*n];
        for (int i = n-1; i >= 0; i--) {
            int index = indices[i];
            after[i] = index == n-1 ? 0 : query(index+1, n, 0, n, 0);
            update(index, 1, 0, n, 0);
        }

        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += (before[i] * after[i]);
        }
        return answer;
    }

    private long query(int lo, int hi, int tlo, int thi, int idx) {
        if (lo > thi || hi < tlo) return 0;

        if (lo == tlo && thi == hi) return tree[idx];

        int mid = (tlo+thi)/2, left = (2*idx)+1, right = (2*idx)+2;
        return (long)query(lo, Math.min(hi, mid), tlo, mid, left)
                + query(Math.max(mid+1, lo), hi, mid + 1, thi, right);
    }

    private void update(int idx, int val, int lo, int hi, int tidx) {
        if (idx < lo || idx > hi) return;
        if (lo == hi) {
            tree[tidx] = val;
            return;
        }

        int mid = (lo+hi)/2, left = (2*tidx)+1, right = (2*tidx)+2;
        if (idx <= mid) update(idx, val, lo, mid, left);
        else update(idx, val, mid+1, hi, right);

        tree[tidx] = tree[right] + tree[left];
    }

    // Extremely tedious to implement! Probably one of the most difficult problems I have ever had to solve
    // We start with finding the indices of numbers in the first array
    // and work with the indices for the numbers in second array corresponding to the first array
    //
    // Why?
    // Because when we will be doing the merge step, then
    // left half is before right half and right half is after left half in the second array by definition
    // and we can simply check the indices of the left and right halves corresponding to the first array
    //
    // What that means is that
    // first array indices in left half can be mapped to first array indices in right half which are greater
    // (Because they are already greater in the second array)
    // Similarly for the right half, the first array indices can be mapped to first array indices in left half which are smaller
    // (Because they are already smaller in the second array)
    //
    // This calculation is made easier at the merge step to calculate
    // because we keep sorting the indices of the first array, so calculating takes O(n) time
    //
    // So we keep summing up the number of ways an index can be mapped to after (left -> right half mapping)
    // and before (right -> left half mapping
    // The final answer is summing up the product of before and after ways for each number
    public long goodTriplets2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] indices1 = new int[n];

        for (int i = 0; i < n; i++) {
            indices1[nums1[i]] = i;
        }

        // This indices array contains the indices of the numbers in nums2 according to what it was in nums1
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = indices1[nums2[i]];
        }

        int[] indicesCopy = Arrays.copyOf(indices, n);
        long[] before = new long[n];
        long[] after = new long[n];

        merge(nums1, indices, indicesCopy, before, after, 0, n-1);

        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += (before[i] * after[i]);
        }
        return answer;
    }

    private void merge(int[] nums, int[] indices, int[] indicesCopy,
                       long[] before, long[] after, int lo, int hi) {
        if (hi == lo) return;

        int mid = (lo+hi) / 2;
        merge(nums, indicesCopy, indices, before, after, lo, mid);
        merge(nums, indicesCopy, indices, before, after, mid+1, hi);

        // for each number in left
        // find count of numbers present after the current number on right
        // that are also after the current number in nums1
        for (int i=lo, j=mid+1; i <= mid; i++) {
            int checkIndex = indicesCopy[i];
            while(j <= hi && indicesCopy[j] <= checkIndex) j++;

            long totalIndices = hi - j + 1;
            after[nums[checkIndex]] += totalIndices;
        }

        // for each index in right
        // find count of numbers present before the current number on left
        // that are also before the current number in nums1
        for (int i=mid+1, j=lo; i <= hi; i++) {
            int checkIndex = indicesCopy[i];
            while(j <= mid && indicesCopy[j] <= checkIndex) j++;

            long totalIndices = j - lo;
            before[nums[checkIndex]] += totalIndices;
        }

        int i = lo, j = mid+1, k = lo;
        while(k <= hi) {
            if (i <= mid && j <= hi) {
                if (indicesCopy[i] < indicesCopy[j]) {
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
