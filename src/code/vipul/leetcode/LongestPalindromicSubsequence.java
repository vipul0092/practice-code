package code.vipul.leetcode;

/**
 * Created by vgaur created on 01/08/23
 */
public class LongestPalindromicSubsequence {

    public static void solve() {
        System.out.println(new LongestPalindromicSubsequence().longestPalindromeSubseq("bbbab"));
    }
    public int longestPalindromeSubseq(String s) {
        return iterativeDp(s);
    }

    // Without using longest common subsequence method
    private int iterativeDp(String str) {
        int[][] dp = new int[str.length()][str.length()];
        int n = str.length();

        for (int i = n - 1; i >=0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = str.charAt(i) == str.charAt(j)
                                ? 2 + dp[i+1][j-1]
                                : Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }

    // Without using longest common subsequence method
    private int lpsRecursiveDp(String str, int s, int e, int[][] dp) {
        if (s > e) {
            return 0;
        }

        if (s == e) {
            return 1;
        }

        if (dp[s][e] != 0) {
            return dp[s][e];
        }

        int ans;
        if (str.charAt(s) == str.charAt(e)) {
            ans = 2 + lpsRecursiveDp(str, s + 1, e - 1, dp);
        } else {
            ans = Math.max(lpsRecursiveDp(str, s, e - 1, dp), lpsRecursiveDp(str, s + 1, e, dp));
        }
        dp[s][e] = ans;
        return ans;
    }


}
