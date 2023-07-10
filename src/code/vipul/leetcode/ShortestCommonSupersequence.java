package code.vipul.leetcode;

/**
 * Created by vgaur created on 04/07/23
 */
public class ShortestCommonSupersequence {

    public static void solve() {
        System.out.println(new ShortestCommonSupersequence().shortestCommonSupersequence("HELLO", "GEEKO"));
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();

        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder scsrev = new StringBuilder();
        int i = l1, j = l2;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                scsrev.append(str1.charAt(i - 1));
                i--; j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                scsrev.append(str1.charAt(i - 1));
                i--;
            } else {
                scsrev.append(str2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            scsrev.append(str1.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            scsrev.append(str2.charAt(j - 1));
            j--;
        }

        return scsrev.reverse().toString();
    }
}
