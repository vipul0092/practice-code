// https://neetcode.io/problems/longest-increasing-path-in-matrix/question?list=neetcode150
class Solution {

    int[][] dp;

    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        dp = new int[row+1][col+1];
        int len = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int curlen = solve(matrix, row, col, i, j);
                len = Math.max(len, curlen);
            }
        }
        return len;
    }

    private static final int[][] DIFFS = new int[][]{{-1,0}, {1,0}, {0,1}, {0,-1}};

    private int solve(int[][] matrix, int row, int col, int i, int j) {
        int val = matrix[i][j];

        if (dp[i][j] != 0) return dp[i][j];

        int len = 1;
        for (int[] d : DIFFS) {
            int ni = i + d[0], nj = j + d[1];
            if (ni < 0 || nj < 0 || ni == row || nj == col || matrix[ni][nj] <= val) continue;

            int curlen = 1 + solve(matrix, row, col, ni, nj);
            len = Math.max(len, curlen);
        }
        dp[i][j] = len;
        return len;
    }
}
