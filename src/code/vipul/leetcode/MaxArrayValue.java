package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 24/07/23
 */
public class MaxArrayValue {

    public static void solve() {
        System.out.println(
                new MaxArrayValue().maxArrayValue(new int[] {5,3,3})
        );
        System.out.println(
                new MaxArrayValue().maxArrayValue(new int[] {5,25,13,15})
        );
    }

    public long maxArrayValue(int[] nums) {
        Stack<Long> values = new Stack<>();

        for (int num : nums) {
            values.push((long) num);
        }

        long max = 0;
        while(!values.isEmpty()) {
            long val = values.pop();
            while (!values.isEmpty() && values.peek() <= val) {
                val += values.pop();
            }
            max = Math.max(max, val);
        }
        return max;
    }
}
