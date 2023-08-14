package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 11/08/23
 * https://leetcode.com/problems/regular-expression-matching/
 */
public class RegexMatcher {

    public static void solve() {
        System.out.println(new RegexMatcher().isMatch2("abc", "a***abc"));
        System.out.println(new RegexMatcher().isMatch2("aabchgfdfdh", "aa.c.*.g"));
    }

    // Simpler and cleaner top-down DP version
    public boolean isMatch2(String s, String p) {
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        return solve(s, p, 0, 0, dp);
    }

    public boolean solve(String s, String p, int sidx, int pidx, int[][] dp) {
        if (p.length() == pidx) {
            return sidx == s.length();
        }

        if (dp[sidx][pidx] != 0) {
            return dp[sidx][pidx] == 1;
        }

        boolean answer = false;

        // We have a current match if characters match or the pattern is `.`
        boolean currentMatch = sidx < s.length() && (s.charAt(sidx) == p.charAt(pidx) || p.charAt(pidx) == '.');

        // If the next pattern is `*`
        if (pidx + 1 < p.length() && p.charAt(pidx+1) == '*') {
                    // then if we have a current match, then move ahead in the string and try to match more if we can
            answer = (currentMatch && solve(s, p, sidx + 1, pidx, dp))
                    // If we dont have a current match, that means we just need to skip this `*` pattern
                    || solve(s, p, sidx, pidx + 2, dp);
        } else {
            // Simply move ahead in both the string and the pattern
            answer = currentMatch && solve(s, p, sidx + 1, pidx + 1, dp);
        }
        dp[sidx][pidx] = answer ? 1 : -1;
        return answer;
    }

    // Complex recursive version
    public boolean isMatch(String s, String p) {
        List<Matcher> matchers = new ArrayList<>();

        for (int i = 0; i < p.length();) {
            char ch = p.charAt(i);
            Matcher matcher = new Matcher(ch);
            i++;
            int prevI = i;
            while(i < p.length() && p.charAt(i) == '*') i++;
            if (prevI != i) {
                matcher = new Matcher('*', matcher);
            }
            matchers.add(matcher);
        }

        int[][] dp = new int[matchers.size()][s.length()];

        return match(matchers, s, 0, 0, dp);
    }

    private boolean match(List<Matcher> matchers, String s, int midx, int sidx, int[][] dp) {
        if (midx == matchers.size()) return false;
        Matcher matcher = matchers.get(midx);

        // We have exhausted the string, then everything afterwards must be a * matcher
        if (sidx == s.length()) {
            if (matcher.ch != '*') {
                return false;
            }
            return midx == matchers.size() - 1 || match(matchers, s, midx + 1, sidx, dp);
        }

        char ch = s.charAt(sidx);
        if (sidx == s.length() - 1) {
            // If the matcher is `*` and we are at the last character
            if (matcher.ch == '*') {
                // then if we're at the last matcher, we must match
                if (midx == matchers.size() - 1) {
                    return matcher.match(ch);
                } else {
                    // Try to match if we can
                    boolean isMatch = matcher.match(ch);
                    if (isMatch) {
                        // move ahead to next matcher
                        isMatch = match(matchers, s, midx + 1, sidx + 1, dp)
                                // keep the current matcher and try to move ahead
                                || match(matchers, s, midx, sidx + 1, dp);
                    }
                    // If we cant match, then skip this matcher and try moving ahead
                    return isMatch || match(matchers, s, midx + 1, sidx, dp);
                }
            }
            boolean currentMatch = matcher.match(ch);
            if (!currentMatch) return false;
            return midx == matchers.size() - 1 || match(matchers, s, midx + 1, sidx + 1, dp);
        }

        if (dp[midx][sidx] != 0) {
            return dp[midx][sidx] == 1;
        }

        boolean answer = false;
        if (matcher.ch == '*') {
            // Match with the current char
            boolean isMatch = matcher.match(ch);
            if (isMatch) {
                // move ahead to next matcher
                isMatch = match(matchers, s, midx + 1, sidx + 1, dp)
                        // keep the current matcher and try to move ahead
                        || match(matchers, s, midx, sidx + 1, dp);
            }
            // If we cant match, then skip this matcher and move ahead
            answer = isMatch || match(matchers, s, midx + 1, sidx, dp);
        } else if (matcher.ch == '.') { // Matches anything
            answer = match(matchers, s, midx + 1, sidx + 1, dp);
        } else {
            answer = matcher.match(ch);
            if (answer) {
                answer = match(matchers, s, midx + 1, sidx + 1, dp);
            }
        }

        dp[midx][sidx] = answer ? 1 : -1;
        return answer;
    }

    private class Matcher {
        private char ch;
        private Matcher prev;
        private boolean done = false;

        Matcher(char c) {
            this.ch = c;
        }

        Matcher(char c, Matcher p) {
            this.ch = c;
            this.prev = p;
        }

        public boolean match(char c) {
            if (ch == '.') {
                done = true;
                return true; // Matches anything
            } else if (ch == '*') {
                boolean didMatch  = prev.match(c);
                done = !didMatch;
                return didMatch;
            } else {
                done = true;
                return c == ch;
            }
        }
    }
}
