class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        if (s1.length() == 0) return s2.equals(s3);
        if (s2.length() == 0) return s1.equals(s3);

        return solve(s1, s2, s3, 0, 0);
    }

    private boolean solve(String s1, String s2, String s3, int idx1, int idx2) {
        if (idx1 == s1.length() && idx2 == s2.length()) {
            return true;
        }

        boolean res = false;
        // Select from s1 if we can
        if (idx1 < s1.length() && s3.charAt(idx1+idx2) == s1.charAt(idx1)) {
            res = solve(s1, s2, s3, idx1+1, idx2);
        }
        if (res) return true;

        if (idx2 < s2.length() && s3.charAt(idx1+idx2) == s2.charAt(idx2)) {
            res = solve(s1, s2, s3, idx1, idx2+1);
        }
        if (res) return true;

        return false;
    }
}
