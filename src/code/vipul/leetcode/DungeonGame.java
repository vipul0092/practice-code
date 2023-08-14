package code.vipul.leetcode;

/**
 * Created by vgaur created on 13/08/23
 * https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {

    public static void solve() {
        System.out.println(new DungeonGame().calculateMinimumHP(
                new int[][]{{1,-3,3},{0,-2,0},{-3,-3,-3}}
        ));
    }

    // AC, Move from bottom right to top left, calculating the max HP required at each step
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m-1 && j == n-1) {
                    dp[i][j] = dungeon[i][j] < 0 ? (-dungeon[i][j] + 1) : 1;
                } else {
                    int sum1 = Integer.MAX_VALUE;
                    int sum2 = Integer.MAX_VALUE;
                    if (i < m-1) {
                        sum1 = Math.max(1, dp[i+1][j] - dungeon[i][j]);
                    }

                    if (j < n-1) {
                        sum2 = Math.max(1, dp[i][j+1] - dungeon[i][j]);
                    }

                    dp[i][j] = Math.min(sum1, sum2);
                }
            }
        }
        return dp[0][0];
    }

    // Iterative DP from top left to bottom right, WA
    // We cant go from top left to bottom right because
    // https://leetcode.com/problems/dungeon-game/solutions/1500016/why-you-can-t-go-from-top-left-to-bottom-right-explained/
    public int calculateMinimumHP2(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][][] dp = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j][0] = dungeon[i][j];
                    dp[i][j][1] = dungeon[i][j];
                } else {
                    int sum1 = Integer.MIN_VALUE;
                    int sum2 = Integer.MIN_VALUE;
                    int option1 = -1001;
                    int option2 = -1001;
                    if (i > 0) {
                        sum1 = dungeon[i][j] + dp[i-1][j][0];
                        option1 = Math.min(sum1, dp[i-1][j][1]);
                    }

                    if (j > 0) {
                        sum2 = dungeon[i][j] + dp[i][j-1][0];
                        option2 = Math.min(sum2, dp[i][j-1][1]);
                    }

                    if (option1 > option2) {
                        dp[i][j][0] = sum1;
                        dp[i][j][1] = option1;
                    } else if (option2 > option1) {
                        dp[i][j][0] = sum2;
                        dp[i][j][1] = option2;
                    } else {
                        dp[i][j][0] = Math.max(sum1, sum2);
                        dp[i][j][1] = option2;
                    }
                }
            }
        }

        int minHealth = dp[m-1][n-1][1];
        return minHealth >= 0 ? 1 : (-minHealth) + 1;
    }
}
