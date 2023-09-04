package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 29/08/23
 */
public class MinimumPossibleSum {

    public static void solve() {
        System.out.println(new MinimumPossibleSum().minimumPossibleSum(63623 ,82276));
    }

    public long minimumPossibleSum(int n, int target) {
        if (n == 1) return 1;

        long min = ((long)n*(n+1))/2;
        long max = Long.MAX_VALUE-1;
        if (possible(n, target, min)) return min;

        while (min <= max) {
            if (max - min == 1) {
                if (possible(n, target, min)) return min;
                return max;
            }

            if (max <= 300) {
                System.out.println("LOL");
            }

            long mid = min + (max-min)/2;
            if (possible(n, target, mid)) {
                max = mid;
            } else {
                min = mid;
            }
        }
        return 0;
    }

    private boolean possible(int n, int target, long sum) {
        Set<Long> numbers = new HashSet<>();
        long num = 1;
        long lastAddedNum = -1;
        for (int i = 0; i < n-1; i++) {
            if (num < target) {
                while(numbers.contains(target-num)) {
                    num++;
                }
                sum -= num;
                numbers.add(num);
                lastAddedNum = num;
                num++;
            } else {
                sum -= num;
                lastAddedNum = num;
                num++;
            }
            if (sum <= 0) return false;
        }

        if ((lastAddedNum != -1 && sum <= lastAddedNum) || numbers.contains(target-sum)) return false;
        return true;
    }
}
