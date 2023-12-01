package code.vipul.leetcode;

/**
 * Created by vgaur created on 05/09/23
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 *
 * Hard
 */
public class LongestIncreasingPathInMatrix {

    public static void solve() {
        System.out.println(new LongestIncreasingPathInMatrix()
                .longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
    }

    private static final int[][] DIR = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int longestIncreasingPath(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;

        int max = 1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int answer = solve(matrix, i, j);
                max = Math.max(answer, max);
            }
        }
        return max;
    }

    private int solve(int[][] matrix, int i, int j) {
        int r = matrix.length;
        int c = matrix[0].length;

        int max = 1;
        int val = matrix[i][j];
        for (int[] d : DIR) {
            int ii = i+d[0], jj = j+d[1];
            if (ii >= 0 && ii < r && jj >= 0 && jj < c && matrix[ii][jj] > val) {
                int answer = 1 + solve(matrix, ii, jj);
                max = Math.max(max, answer);
            }
        }
        return max;
    }
}
