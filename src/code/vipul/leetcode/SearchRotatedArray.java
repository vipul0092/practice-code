package code.vipul.leetcode;

/**
 * Created by vgaur created on 07/07/23
 */
public class SearchRotatedArray {

    public static void solve() {
        System.out.println(new SearchRotatedArray().search(new int[]{6,7,8,9,10,4,5}, 6));
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int pivot = -1;
        int lo = 0, hi = n-1;
        while (lo <= hi) {
            int mid = lo + ((hi-lo)/2);
            int next = (mid+1) % n;
            int prev = (mid+n-1) % n;

            if (nums[mid] < nums[next] && nums[mid] < nums[prev]) {
                pivot = mid;
                break;
            }

            if (nums[mid] > nums[lo]) {
                lo = mid + 1;
            } else if (nums[hi] > nums[mid]) {
                hi = mid - 1;
            } else {
                break;
            }
        }

        if (pivot == -1) {
            return find(nums, target, 0, nums.length - 1);
        } else {
            int firstRes = find(nums, target, 0, pivot - 1);
            return firstRes == -1 ? find(nums, target, pivot, nums.length - 1) : firstRes;
        }
    }

    int find(int[] nums, int target, int start, int end) {
        int lo = start, hi = end;

        while (lo <= hi) {
            int mid = lo + ((hi-lo)/2);

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > target) {
                hi = mid-1;
            } else {
                lo = mid+1;
            }
        }
        return -1;
    }
}
