package code.vipul.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by vgaur created on 06/06/21
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3769/
 * AC
 */
public class LongestConsecutiveSequence {

    public static void test() {
        int[] nums = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int answer = longestConsecutiveTry2(nums);
        System.out.println(answer);
    }

    //O n
    public static int longestConsecutiveTry2(int[] nums) {
        int largestLength = 0;
        Set<Integer> numbers = new HashSet<>(nums.length);
        for (int num : nums) {
            numbers.add(num);
        }

        for (int num : numbers) {
            if (!numbers.contains(num - 1)) {
                int curlen = 1;
                int curnum = num + 1;
                while (numbers.contains(curnum)) {
                    curlen++;
                    curnum++;
                }
                largestLength = Math.max(largestLength, curlen);
            }
        }
        return largestLength;
    }

    //O n logn
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numbers = new TreeSet<>();
        for (int num : nums) {
            numbers.add(num);
        }

        int largestLength = 0;
        int currentLength = 0;
        Integer prevNum = null;

        for (int num : numbers) {
            if (prevNum == null) {
                prevNum = num;
                currentLength = 1;
            } else if (prevNum == num - 1) {
                currentLength++;
                prevNum = num;
            } else {
                prevNum = num;
                largestLength = Math.max(largestLength, currentLength);
                currentLength = 1;
            }
        }
        largestLength = Math.max(largestLength, currentLength);

        return largestLength;
    }
}
