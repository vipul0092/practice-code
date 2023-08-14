package code.vipul.leetcode;

/**
 * Created by vgaur created on 30/06/23
 */
public class LongestSubstringWithAtMost2DistinctChars {

    public static void solveTwoDistinct() {
        System.out.println("Answer: " + new LongestSubstringWithAtMost2DistinctChars()
                .twoDistinct("abbbababababababcccmabbbbcccccaaaaaad"));
    }

    //Longest Substring with At Most Two Distinct Characters
    public int twoDistinct(String s) {
        int i = 0, j = 0;
        int distinct = 0;
        int[] chars = new int[200];
        int max = 0;

        while(j < s.length()) {
            char ch = s.charAt(j);
            chars[ch]++;
            if (chars[ch] == 1) {
                distinct++;
            }

            if (distinct > 2) {
                while(distinct > 2) {
                    ch = s.charAt(i);
                    chars[ch]--;
                    // The only way to remove a distinct is to completely remove it
                    if (chars[ch] == 0) {
                        distinct--;
                    }
                    i++;
                }
            }
            max = Math.max(j - i + 1, max);
            j++;
        }
        return max;
    }
}
