package code.vipul.leetcode;
import java.util.* ;

/**
 * Created by vgaur created on 26/08/23
 */
public class ChocolatePickup {

    public static void solve() {
        System.out.println(maximumChocolates(3,2, new int[][]{{4,5}, {3,7}, {4,2}}));
    }

    public static int maximumChocolates(int r, int c, int[][] grid) {

        Map<Integer, Set<Integer>> pointsPerRow1 = new HashMap<>();
        walkDown(0, r, c, pointsPerRow1);

        Map<Integer, Set<Integer>> pointsPerRow2 = new HashMap<>();
        walkDown(encode(0, c-1), r, c, pointsPerRow2);

        int max = 0;
        int[][][] dp = new int[r][c][c];
        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) {
                if (j1 == j2) continue;

                int ans =
                        moveUp(r-1, j1, j2, dp, grid, pointsPerRow1, pointsPerRow2);
                max = Math.max(max, ans);
            }
        }
        return max;
    }

    private static int moveUp(int r, int c1, int c2,
                              int[][][] dp,
                              int[][] grid,
                              Map<Integer, Set<Integer>> pointsPerRow1,
                              Map<Integer, Set<Integer>> pointsPerRow2) {
        int rmax = grid.length;
        int cmax = grid[0].length;
        if (r==0) {
            return grid[0][0]+grid[0][cmax-1];
        }

        if (dp[r][c1][c2] != 0) {
            return dp[r][c1][c2]-1;
        }

        int max = 0;
        int[] c1pts = new int[]{c1,c1-1,c1+1};
        int[] c2pts = new int[]{c2,c2-1,c2+1};
        for (int c1i : c1pts) {
            if (!pointsPerRow1.get(r-1).contains(encode(r-1, c1i))) continue;
            for (int c2i: c2pts) {
                if (c1i == c2i || !pointsPerRow2.get(r-1).contains(encode(r-1, c2i))) continue;

                int ans =
                        moveUp(r-1, c1i, c2i, dp, grid, pointsPerRow1, pointsPerRow2);
                if (ans+grid[r][c1] + grid[r][c2] > max) {
                    max = ans+grid[r][c1] + grid[r][c2];
                }
            }
        }
        dp[r][c1][c2] = max+1;
        return max;
    }

    private static void walkDown(int start, int r, int c,
                                 Map<Integer, Set<Integer>> pointsPerRow) {
        Set<Integer> points = new HashSet<>();
        points.add(start);
        int sr = start/100, sc = start%100;
        pointsPerRow.put(sr, points);

        for (int i = 0; i < r; i++) {
            Set<Integer> next = new HashSet<>();
            for (int point : points) {
                int pr = point/100, pc = point%100;
                next.add(encode(pr+1, pc));
                if (pc-1 >= 0) next.add(encode(pr+1, pc-1));
                if (pc+1 < c) next.add(encode(pr+1, pc+1));
            }
            pointsPerRow.put(i+1, next);
            points = next;
        }
    }

    private static int encode(int r, int c) {
        return (r*100)+c;
    }
}
