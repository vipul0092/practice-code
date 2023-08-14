package code.vipul.leetcode;

/**
 * Created by vgaur created on 13/08/23
 * https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/
 */
public class MinimumWhiteTilesAfterCoveringWithCarpet {

    public static void solve() {
        System.out.println(new MinimumWhiteTilesAfterCoveringWithCarpet().minimumWhiteTiles(
                "110000", 1, 1
        ));
    }

    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int[] prefix = new int[floor.length()];
        prefix[0] = floor.charAt(0) == '1' ? 1 : 0;
        for (int i = 1; i < floor.length(); i++) {
            prefix[i] = (floor.charAt(i) == '1' ? 1 : 0) + prefix[i-1];
        }
        int[][] dp = new int[numCarpets+1][floor.length()+1];
        return solve(floor, numCarpets, carpetLen, prefix, 0, dp);
    }

    private int solve(String floor, int numCarpets, int carpetLen,
                      int[] prefix, int idx, int[][] dp) {
        if (idx == prefix.length) {
            return 0;
        }
        if (numCarpets == 0) {
            return prefix[prefix.length-1] - prefix[idx-1];
        }

        if (dp[numCarpets][idx] != 0) {
            return dp[numCarpets][idx] - 1;
        }

        char ch = floor.charAt(idx);
        int answer;
        if (ch == '0') { // no point starting from here
            answer = solve(floor, numCarpets, carpetLen, prefix, idx + 1, dp);
        } else {
            // dont put the carpet here, move ahead one tile
            int a1 = 1 + solve(floor, numCarpets, carpetLen, prefix, idx + 1, dp);
            // Put the carpet from here, and move ahead until after the carpet end
            int a2 = solve(floor, numCarpets - 1, carpetLen, prefix,
                    Math.min(idx + carpetLen, prefix.length), dp);
            answer = Math.min(a1, a2);
        }
        dp[numCarpets][idx] = answer + 1;
        return answer;
    }
}
