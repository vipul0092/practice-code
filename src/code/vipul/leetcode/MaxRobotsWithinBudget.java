package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 08/08/23
 * https://leetcode.com/problems/maximum-number-of-robots-within-budget/
 */
public class MaxRobotsWithinBudget {

    public static void solve() {
        System.out.println(new MaxRobotsWithinBudget().maximumRobots(
                new int[]{11,12,74,67,37,87,42,34,18,90,36,28,34,20},
                new int[]{18,98,2,84,7,57,54,65,59,91,7,23,94,20},
                937
        )); //Answer = 4
    }

    public int maximumRobots(int[] ch, int[] run, long budget) {
        long prefix = 0;
        long cost = 0;
        int i = 0, j = 0, n = ch.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int max = 0;

        while (j < n) {
            prefix += run[j];
            while(!dq.isEmpty() && dq.peekLast() < ch[j]) {
                dq.removeLast();
            }
            dq.addLast(ch[j]);
            long len = j - i + 1;
            cost = dq.peek() + (len * prefix);

            while (len > 1 && cost > budget) {
                prefix -= run[i]; // Adjust sum
                if (dq.peek() == ch[i]) { // Adjust max
                    dq.remove();
                }
                i++;
                len = j - i + 1;
                cost = dq.peek() + (len * prefix);
            }
            if (cost <= budget) {
                max = Math.max(max, (int) len);
            }
            j++;
        }
        return max;
    }
}
