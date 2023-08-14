package code.vipul.leetcode;

/**
 * Created by vgaur created on 12/08/23
 * https://leetcode.com/problems/wildcard-matching/description/
 */
public class WildcardMatching {

    public static void solve() {
        System.out.println(new WildcardMatching().isMatch("aachadfbjhduehfbsdc", "*?*b**c*"));
    }

    public boolean isMatch(String s, String p) {
        int[][] dp = new int [s.length()+1][p.length()+1];
        return match(s, p, 0, 0, dp);
    }

    private boolean match(String s, String p, int sidx, int pidx, int[][] dp) {
        if (pidx == p.length()) {
            return sidx == s.length();
        }

        if (dp[sidx][pidx] != 0) {
            return dp[sidx][pidx] == 1;
        }

        boolean answer = false;

        if (sidx == s.length()) {
            answer = p.charAt(pidx) == '*' && match(s, p, sidx, pidx + 1, dp);
            dp[sidx][pidx] = answer ? 1 : -1;
            return answer;
        }


        if (sidx < s.length() && p.charAt(pidx) == '*') {
            answer = match(s, p, sidx + 1, pidx, dp)
                    || match(s, p, sidx, pidx + 1, dp)
                    || match(s, p, sidx + 1, pidx + 1, dp);
        } else {
            boolean currentMatch = sidx < s.length()
                    && (s.charAt(sidx) == p.charAt(pidx) || p.charAt(pidx) == '?');
            answer = currentMatch && match(s, p, sidx + 1, pidx + 1, dp);
        }
        dp[sidx][pidx] = answer ? 1 : -1;
        return answer;
    }
}
