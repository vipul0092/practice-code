package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 21/08/23
 * https://leetcode.com/problems/count-of-range-sum/description/
 *
 * Really hard
 */
public class CountOfRangeSum {

    public static void solve() {
        System.out.println(new CountOfRangeSum().countRangeSum(new int[]{7,4,2,1,5,3,8}, 7, 12));//ans:10
    }

    // Took me hours to get this right, holy shit
    // Employs the "merge sort" technique, where we divide the array into sub-parts
    // and calculate the result at intermediate steps, summing them over the process
    //
    // Here what we do is that for two parts being merged,
    // we calculate the sorted suffix array of the first part
    // and then search the min and max values for the second part's prefix sum
    // Here we are trying to find the ranges which start in first part and end in second part.
    // We don't care where they start in the first part, hence we sorted the suffix array.
    public int countRangeSum(int[] nums, int lower, int upper) {
        return merge(nums, 0, nums.length-1, lower, upper);
    }

    private int merge(int[] nums, int lo, int hi, int l, int u) {
        if (lo == hi) {
            return nums[lo] >= l && nums[lo] <= u ? 1 : 0;
        }

        int mid = (lo+hi)/2;
        int total = 0;
        total += merge(nums, lo, mid, l, u);
        total += merge(nums, mid+1, hi, l, u);

        int sl = mid-lo+1;
        long[] suffix = new long[sl];
        suffix[0] = nums[mid];
        for (int i = mid-1, j = 1; i >= lo; i--,j++) {
            suffix[j] = suffix[j-1] + nums[i];
        }
        Arrays.sort(suffix);

        long num = 0;
        for (int i = mid+1; i <= hi; i++) {
            num += nums[i];
            long minSearch = l - num;
            int min = searchMin(suffix, minSearch);
            if (min == -1) continue;
            long maxSearch = u - num;
            int max = searchMax(suffix, maxSearch);

            if (max == -1 || max < min) continue;

            int len = max - min + 1;
            total += len;
        }
        return total;
    }

    private int searchMin(long[] arr, long num) {
        int i = 0, j = arr.length - 1;
        if (arr.length == 1) {
            return arr[i] >= num ? 0 : -1;
        }
        while (i < j) {
            if (j - i == 1) {
                if (arr[i] >= num) return i;
                if (arr[j] >= num) return j;
                return -1;
            }

            int mid = (i+j) / 2;
            if (arr[mid] >= num) {
                j = mid;
            } else {
                i = mid;
            }
        }
        return -1;
    }

    private int searchMax(long[] arr, long num) {
        int i = 0, j = arr.length - 1;
        if (arr.length == 1) {
            return arr[i] <= num ? 0 : -1;
        }
        while (i < j) {
            if (j - i == 1) {
                if (arr[j] <= num) return j;
                if (arr[i] <= num) return i;
                return -1;
            }

            int mid = (i+j) / 2;
            if (arr[mid] <= num) {
                i = mid;
            } else {
                j = mid;
            }
        }
        return -1;
    }
}
