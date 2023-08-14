package code.vipul.leetcode;

/**
 * Created by vgaur created on 11/08/23
 * https://leetcode.com/problems/coin-change-ii/
 */
public class CoinChangeII {

    public static void solve() {
        System.out.println(new CoinChangeII().change(5, new int[]{1,2,5}));
    }


    public int change(int amount, int[] coins) {
        int[][] dp = new int[amount + 1][coins.length + 1];
        return solve(amount, coins, coins.length, dp);
    }

    private int solve(int amt, int[] coins, int l, int[][] dp) {
        if (l == 0) {
            return 0;
        }
        if (amt == 0) {
            return 1;
        }

        if (dp[amt][l] != 0) {
            return dp[amt][l] - 1;
        }

        int val = coins[l-1];
        int answer;
        if (val > amt) {
            answer = solve(amt, coins, l-1, dp);
        } else if (val == amt) {
            answer = 1;
            // dont include
            int w = solve(amt, coins, l-1, dp);
            answer += w;
        } else {
            //include, reduce
            int w1 = 0; //solve(amt-val, coins, l-1, dp);
            //include, dont reduce
            int w2 = solve(amt-val, coins, l, dp);
            // dont include
            int w3 = solve(amt, coins, l-1, dp);
            answer = w1 + w2 + w3;
        }
        dp[amt][l] = answer + 1;
        return answer;
    }
}
