package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 27/07/23
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {

    public static void solve() {
        System.out.println(new PacificAtlanticWaterFlow()
                .pacificAtlantic(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}}));
        System.out.println(new PacificAtlanticWaterFlow()
                .pacificAtlantic(new int[][]{{1}}));
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int r = heights.length;
        int c = heights[0].length;
        int[][] ocean = new int[r][c]; //0-no, 1-pacific,2-atlantic,3-both
        populate(0, 0, 0, c-2, ocean, heights, 1 << 1);
        populate(0, r-2, 0, 0, ocean, heights, 1 << 1);

        populate(r-1, r-1, 1, c-1, ocean, heights, 1 << 2);
        populate(1, r-1, c-1, c-1, ocean, heights, 1 << 2);

        populate(0, 0, c-1, c-1, ocean, heights, (1 << 1) | (1 << 2));
        populate(r-1, r-1, 0, 0, ocean, heights, (1 << 1) | (1 << 2));

        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (ocean[i][j] == 6) {
                    ret.add(List.of(i,j));
                }
            }
        }
        return ret;
    }

    private void populate(int rs, int re, int cs, int ce, int[][] ocean, int[][] heights, int val) {
        int r = heights.length;
        int c = heights[0].length;
        Set<List<Integer>> visited = new HashSet<>();
        Queue<List<Integer>> queue = new ArrayDeque<>();
        for (int i = rs; i <= re; i++) {
            for (int j = cs; j <= ce; j++) {
                if (i == 0 || i == r-1 || j == 0 || j == c-1) {
                    List<Integer> cur = List.of(i,j);
                    if (visited.contains(cur)) {
                        continue;
                    }
                    queue = new ArrayDeque<>();
                    queue.add(cur);
                    visited.add(cur);

                    while(!queue.isEmpty()) {
                        List<Integer> cr = queue.remove();
                        int ii = cr.get(0), jj = cr.get(1);
                        ocean[ii][jj] = ocean[ii][jj] | val;
                        int h = heights[ii][jj];

                        //up
                        List<Integer> n =  List.of(ii-1, jj);
                        if (ii > 0 && heights[n.get(0)][n.get(1)] >= h && !visited.contains(n)) {
                            visited.add(n);
                            queue.add(n);
                        }

                        //down
                        n = List.of(ii+1, jj);
                        if (ii < r-1 && heights[n.get(0)][n.get(1)] >= h && !visited.contains(n)) {
                            visited.add(n);
                            queue.add(n);
                        }

                        //left
                        n = List.of(ii, jj-1);
                        if (jj > 0 && heights[n.get(0)][n.get(1)] >= h && !visited.contains(n)) {
                            visited.add(n);
                            queue.add(n);
                        }

                        //right
                        n = List.of(ii, jj+1);
                        if (jj < c-1 && heights[n.get(0)][n.get(1)] >= h && !visited.contains(n)) {
                            visited.add(n);
                            queue.add(n);
                        }
                    }
                }
            }
        }
    }

}
