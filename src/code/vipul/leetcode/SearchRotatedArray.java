package code.vipul.leetcode;

/**
 * Created by vgaur created on 07/07/23
 */
public class SearchRotatedArray {

    public static void solve() {
        System.out.println(new SearchRotatedArray().search(new int[]{4,5,6,7,8,9,10}, 6));
    }

    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        if (nums.length == 2) {
            if (nums[0] == target || nums[1] == target) {
                return nums[0] == target ? 0 : 1;
            }
            return -1;
        }

        int pivot = findPivot(nums, 0, nums.length - 1);

        if (pivot == -1) {
            return find(nums, target, 0, nums.length - 1);
        } else {
            int firstRes = find(nums, target, 0, pivot - 1);
            return firstRes == -1 ? find(nums, target, pivot, nums.length - 1) : firstRes;
        }
    }

    int findPivot(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }
        if (end - start == 1) {
            return nums[start] > nums[end] ? end : -1;
        }

        int mid = (start + end) / 2;
        if (nums[mid] > nums[end]) {
            return findPivot(nums, mid, end);
        } else if (nums[mid] < nums[end]) {
            return findPivot(nums, start, mid);
        }
        return -1;
    }

    int find(int[] nums, int target, int start, int end) {
        if (start == end) {
            return nums[start] == target ? start : -1;
        }
        if (end - start == 1) {
            if (nums[start] == target || nums[end] == target) {
                return nums[start] == target ? start : end;
            }
            return -1;
        }

        int mid = (start + end) / 2;
        if (nums[mid] > target) {
            return find(nums, target, start, mid);
        } else if (nums[mid] < target) {
            return find(nums, target, mid, end);
        } else {
            return mid;
        }

    }
}
