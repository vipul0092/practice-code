package code.vipul.leetcode;

import java.util.Arrays;

/**
 * Created by vgaur created on 21/08/23
 */
public class MergeSort {

    public static void solve() {
        System.out.println(Arrays.toString(new MergeSort().sort(new int[]{4, 19, 6, 4, 3, 9, 10, 15, 1, 0, 7, 3})));
    }

    public int[] sort(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        merge(copy, arr, 0, arr.length-1);
        return copy;
    }

    // Sorted data is put into arr using copy
    private void merge(int[] arr, int[] copy, int lo, int hi) {
        if (hi == lo) {
            return;
        }

        int mid = (lo+hi)/2;
        // Populate copy using arr
        merge(copy, arr, lo, mid);
        merge(copy, arr, mid+1, hi);

        // Populate arr from copy
        int i = lo, j = mid+1, k = lo;
        while(k <= hi) {
            if (i <= mid && j <= hi) {
                int num = Math.min(copy[i], copy[j]);
                arr[k++] = num;
                if (num == copy[i]) i++;
                else j++;
            } else if (i <= mid) {
                arr[k++] = copy[i];
                i++;
            } else {
                arr[k++] = copy[j];
                j++;
            }
        }
    }
}
