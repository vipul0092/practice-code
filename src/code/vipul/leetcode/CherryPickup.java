package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 27/08/23
 * https://leetcode.com/problems/cherry-pickup/
 *
 * Really Hard
 */
public class CherryPickup {

    public static void solve() {
        System.out.println(new CherryPickup().cherryPickup(
                new int[][]{{0,1,1,0,0},{1,1,1,1,0},{-1,1,1,1,-1},{0,1,1,1,0},{1,0,-1,0,0}}));
    }

    public int cherryPickup(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][][][] dp = new int[50][50][50][50];
        int ans = move(r-1, c-1, r-1, c-1, grid, dp);
        return ans == -1 ? 0 : ans;
    }

    private int move(int r1, int c1, int r2,  int c2, int[][] grid, int[][][][] dp) {
        int r = grid.length, c = grid[0].length;
        if (r1 == 0 && c1 ==0 && r2 == 0 && c2 == 0) {
            return grid[0][0];
        }
        if (r1 < 0 || r1 >= r || c1 < 0 || c1 >= c ||
                r2 < 0 || r2 >= r || c2 < 0 || c2 >= c ||
                grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return -1;
        }
        if (dp[r1][c1][r2][c2] != 0) {
            return dp[r1][c1][r2][c2] == -1 ? -1 : dp[r1][c1][r2][c2]-1;
        }

        int max = Math.max(
                Math.max(move(r1-1, c1, r2-1, c2, grid, dp), move(r1, c1-1, r2, c2-1, grid, dp)),
                Math.max(move(r1-1, c1, r2, c2-1, grid, dp), move(r1, c1-1, r2-1, c2, grid, dp))
        );
        if (max == -1) {
            dp[r1][c1][r2][c2] = -1;
            return -1;
        }
        int answer = r1 == r2 && c1 == c2 ? grid[r1][c1]+max : grid[r1][c1]+grid[r2][c2]+max;
        dp[r1][c1][r2][c2] = answer+1;
        return answer;
    }
    //[69,39,79,78,16,6,36,97,79,27,14,31,4] 2
}
