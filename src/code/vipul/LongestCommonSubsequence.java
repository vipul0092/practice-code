package code.vipul;

/**
 * Created by vgaur created on 15/06/22
 */
public class LongestCommonSubsequence {

    private static Integer[][] dp;

    public static void solve() {
        dp = new Integer[100][100];

        String s1 = "sabccdefabc";
        String s2 = "fccdambfabbas";

        int answer = dive(s1, s2, 0, 0, 0);

        System.out.println(answer);
    }

    private static int dive(String s1, String s2, int i1, int i2, int prefix) {
        if ((i1 == s1.length()) || (i2 == s2.length())) {
            return prefix;
        }

        int m = -1;
        if (s1.charAt(i1) == s2.charAt(i2)) {
            m = dive(s1, s2, i1 + 1, i2 + 1, prefix + 1);
        }
        int val1 = dive(s1, s2, i1 + 1, i2, 0);
        int val2 = dive(s1, s2, i1, i2 + 1, 0);
        m = Math.max(m, Math.max(val1, val2));
        // dp[i1][i2] = m;
        return m;

    }
}
