package code.vipul.leetcode;

/**
 * Created by vgaur created on 03/07/23
 */
public class LongestPalindromicSubstring {

    public static void solve() {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd"));
    }

    // Expand sideways from each index (treating it as center) O(n^2) runtime
    public String longestPalindrome(String s) {
        int maxlen = -1;
        int startIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            int maxCenteredOnI = expand(i, i, s);
            int maxCenteredBetweenINext = expand(i, i + 1, s);

            if (maxCenteredOnI > maxlen) {
                maxlen = maxCenteredOnI;
                startIndex = i - (maxlen / 2);
            }

            if (maxCenteredBetweenINext > maxlen) {
                maxlen = maxCenteredBetweenINext;
                startIndex = i - (maxlen / 2) + 1;
            }
        }

        return s.substring(startIndex, maxlen + startIndex);
    }

    public int expand(int left, int right, String s) {
        int matched = 0;
        while(left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            if (left == right) {
                matched++;
            } else {
                matched += 2;
            }

            left--;
            right++;
        }
        return matched;
    }

}
