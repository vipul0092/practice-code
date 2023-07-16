package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 15/07/23
 */
public class Permutations {

    public static void solve() {
        System.out.println(new Permutations().permute(new int[]{1,2,3,4,5,6}));
    }

    public List<List<Integer>> permute(int[] nums) {
        return recurse(nums, 0);
    }

    private List<List<Integer>> recurse(int[] nums, int included) {
        List<List<Integer>> perms = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!isIncluded(included, i)) {
                int num = nums[i];
                List<List<Integer>> values = recurse(nums, include(included, i));
                if (values.isEmpty()) {
                    List<Integer> numVal = new ArrayList<>();
                    numVal.add(num);
                    perms.add(numVal);
                } else {
                    for (List<Integer> value : values) {
                        List<Integer> numVal = new ArrayList<>();
                        numVal.add(num);
                        numVal.addAll(value);
                        perms.add(numVal);
                    }
                }
            }
        }
        return perms;
    }

    private boolean isIncluded(int bits, int pos) {
        return (bits & (1 << pos)) != 0;
    }

    private int include(int bits, int pos) {
        return bits | (1 << pos);
    }
}
