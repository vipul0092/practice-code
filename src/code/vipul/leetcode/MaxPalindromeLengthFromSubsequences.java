package code.vipul.leetcode;

/**
 * Created by vgaur created on 31/07/23
 * https://leetcode.com/problems/maximize-palindrome-length-from-subsequences/
 */
public class MaxPalindromeLengthFromSubsequences {

    public static void solve() {
        System.out.println(new MaxPalindromeLengthFromSubsequences()
                .longestPalindrome("c", "fffaeacefcefeecedeedefecdcbedebebcfadffeacddcffa"));
    }

    public int longestPalindrome(String word1, String word2) {
        String concat = word1 + word2;

        return iterativeDp(concat, word1.length(), word2.length());
    }

    // Without using longest common subsequence method
    private int iterativeDp(String str, int l1, int l2) {
        int[][] dp = new int[str.length()][str.length()];
        int n = str.length();
        int max = 0;

        for (int i = n - 1; i >=0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    boolean match = str.charAt(i) == str.charAt(j);
                    dp[i][j] = match
                            ? 2 + dp[i+1][j-1]
                            : Math.max(dp[i][j-1], dp[i+1][j]);
                    // The only change from iterative dp approach for lps
                    // the split is at length l1 because before that we have word 1 and after that we have word2
                    if (match && (i < l1 && j >= l1)) {
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
        }
        return max;
    }
}
