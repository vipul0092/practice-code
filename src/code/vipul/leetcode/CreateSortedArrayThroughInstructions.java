package code.vipul.leetcode;

import java.util.*;

/**
 * Created by vgaur created on 22/08/23
 * https://leetcode.com/problems/create-sorted-array-through-instructions/description/
 *
 * Really hard
 */
public class CreateSortedArrayThroughInstructions {

    public static void solve() {
        // Answer: 4
        System.out.println(new CreateSortedArrayThroughInstructions().createSortedArray(new int[]{1,3,3,3,2,4,2,1,2}));
    }

    private static final int MOD = 1_000_000_007;

    private int[] tree;
    // Using segment tree
    public int createSortedArray(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int n = max+1;
        tree = new int[4*n];

        long total = 0;
        for (int num : nums) {
            long nmin = query(0, num-1, 0, n, 0);
            long nmax = query(num+1, n, 0, n, 0);

            total = (total + Math.min(nmax, nmin)) % MOD;
            update(num, 1, 0, 0, n);
        }
        return (int)total;
    }

    private long query(int lo, int hi, int tlo, int thi, int idx) {
        if (hi < tlo || lo > thi) return 0;
        if (lo == tlo && hi == thi) {
            return tree[idx];
        }

        int mid = (thi+tlo)/2;
        return (long)query(lo, Math.min(hi, mid), tlo, mid, (2*idx)+1)
                + query(Math.max(mid+1, lo), hi, mid+1, thi, (2*idx)+2);
    }

    private void update(int idx, int val, int tidx, int lo, int hi) {
        if (idx < lo || idx > hi) return;

        if (lo == hi) {
            tree[tidx] += val;
            return;
        }

        int mid = (lo+hi)/2, left = (2*tidx)+1, right = (2*tidx)+2;
        if (idx <= mid) update(idx, val, left, lo, mid);
        else update(idx, val, right, mid+1, hi);

        tree[tidx] = tree[left] + tree[right];
    }


    // Using merge sort
    public int createSortedArray2(int[] nums) {
        int n = nums.length;

        int[][] numbers = new int[n][3];
        int[][] numbersCopy = new int[n][3];
        for (int i = 0; i < n; i++) {
            numbers[i][0] = nums[i];
            numbersCopy[i][0] = nums[i];
        }
        merge(numbers, numbersCopy, 0, n-1);
        long total = 0;
        for (int i = 0; i < n; i++) {
            total = (total + Math.min(numbers[i][1], numbers[i][2])) % MOD;
        }
        return (int)total;
    }

    private void merge(int[][] nums, int[][] copy, int lo, int hi) {
        if (hi == lo) {
            return;
        }

        int mid = (hi + lo)/2;
        merge(copy, nums, lo, mid);
        merge(copy, nums, mid+1, hi);

        // Smaller
        for (int i = lo, j = mid+1; j <= hi; j++) {
            int checkNum = copy[j][0];
            while(i <= mid && copy[i][0] < checkNum) i++;

            int count = i - lo;
            copy[j][1]+=count;
        }

        // greater
        for (int i = lo, j = mid+1; j <= hi; j++) {
            int checkNum = copy[j][0];
            while(i <= mid && copy[i][0] <= checkNum) i++;

            int count = mid - i + 1;
            copy[j][2]+=count;
        }

        int i = lo, j = mid + 1, k = lo;
        while (k <= hi) {
            if (i <= mid && j <= hi) {
                if (copy[i][0] <= copy[j][0]) {
                    nums[k][0] = copy[i][0];
                    nums[k][1] = copy[i][1];
                    nums[k][2] = copy[i][2];
                    k++; i++;
                } else {
                    nums[k][0] = copy[j][0];
                    nums[k][1] = copy[j][1];
                    nums[k][2] = copy[j][2];
                    k++; j++;
                }
            } else if (i <= mid) {
                nums[k][0] = copy[i][0];
                nums[k][1] = copy[i][1];
                nums[k][2] = copy[i][2];
                k++; i++;
            } else {
                nums[k][0] = copy[j][0];
                nums[k][1] = copy[j][1];
                nums[k][2] = copy[j][2];
                k++; j++;
            }
        }
    }
}
