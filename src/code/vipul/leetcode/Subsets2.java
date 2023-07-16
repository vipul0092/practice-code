package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 16/07/23
 */
public class Subsets2 {

    public static void solve() {
        System.out.println(new Subsets2().subsetsWithDup(new int[]{1,2,2}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int[] freq = new int[25];
        for (int num : nums) {
            freq[num+10]++;
        }

        List<Integer> unique = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            if (freq[i] > 0) {
                unique.add(i - 10);
            }
        }
        unique.sort(Comparator.naturalOrder());
        return recurse(unique, unique.size(), freq);
    }

    private List<List<Integer>> recurse(List<Integer> nums, int len, int[] freq) {
        if (len == 0) {
            return List.of(List.of());
        }

        List<List<Integer>> priors = recurse(nums, len - 1, freq);

        int num = nums.get(len - 1);
        List<List<Integer>> current = generate(num, freq[num + 10]);

        List<List<Integer>> retval = new ArrayList<>();
        for (List<Integer> prior : priors) {
            for (List<Integer> c : current) {
                List<Integer> temp = new ArrayList<>(prior);
                temp.addAll(c);
                retval.add(temp);
            }
        }
        return retval;
    }

    private List<List<Integer>> generate(int num, int times) {
        List<List<Integer>> perms = new ArrayList<>();
        for (int i = 0; i <= times; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                cur.add(num);
            }
            perms.add(cur);
        }
        return perms;
    }
}
