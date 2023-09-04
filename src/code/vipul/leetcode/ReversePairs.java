package code.vipul.leetcode;
import java.util.* ;

/**
 * Created by vgaur created on 19/08/23
 * https://leetcode.com/problems/reverse-pairs/
 *
 * Really hard
 */
public class ReversePairs {

    public static void solve() {
        System.out.println(new ReversePairs().reversePairs(new int[]{1,3,2,3,1}));
    }

    public int reversePairs(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        return merge(copy, nums, 0, nums.length-1);
    }

    private int merge(int[] arr, int[] copy, int lo, int hi) {
        if (hi == lo) {
            return 0;
        }

        int mid = (lo+hi)/2;

        int total = 0;
        total += merge(copy, arr, lo, mid);
        total += merge(copy, arr, mid+1, hi);

        int i = lo, j = mid+1, k = lo;

        while (i <= mid && j <= hi) {
            if ((long) copy[i] > 2L * copy[j]) {
                total += (mid - i + 1);
                j++;
            } else {
                i++;
            }
        }
        i = lo; j = mid+1;
        while(k <= hi) {
            if (i <= mid && j <= hi) {
                int num =  Math.min(copy[i], copy[j]);
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
        return total;
    }
}
