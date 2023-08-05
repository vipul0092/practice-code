package code.vipul.codegladitor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 30/07/23
 */
public class CG2023_Final {

    public static void solve1() {
        Scanner in = new Scanner(System.in);
        String nmstr = in.nextLine();
        List<Integer> nm = Arrays.stream(nmstr.split(" "))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        int n = nm.get(0);
        int m = nm.get(1);

        char[][] grid = new char[n][m];
        int flags = 0;
        Set<Integer> flagsPos = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String row = in.nextLine();
            for (int j = 0; j < row.length(); j++) {
                grid[i][j] = row.charAt(j);
                if (row.charAt(j) == 'F') {
                    flags++;
                    flagsPos.add(encode(i,j));
                }
            }
        }

        int answer = 5000;
        for (int floc : flagsPos) {
            int val = bfs(floc, grid, n, m, flags);
            if (val == -1) {
                answer = -1;
                break;
            }
            answer = Math.min(answer, val);
        }
        System.out.println(answer);
    }

    private static int bfs(int floc, char[][] grid, int n, int m, int flags) {
        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Integer> coints = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        int flagsVisited = 0;
        int minCoins = 0;
        visited.add(floc);
        queue.add(floc);
        coints.add(0);
        while(!queue.isEmpty()) {
            int pos = queue.remove();
            int i = pos/100, j = pos%100;
            int c = coints.remove();
            if (grid[i][j] == 'F') {
                flagsVisited++;
            }
            boolean addedNeighbor = false;
            int nl = grid[i][j] == 'B' ? c + 1 : c;
            int ii = i-1, jj = j;
            int encoded = encode(ii, jj);
            if(i > 0 && !visited.contains(encoded) && grid[ii][jj] != 'O') {
                queue.add(encoded);
                visited.add(encoded);
                coints.add(nl);
                addedNeighbor = true;
            }

            ii = i+1; jj=j;
            encoded = encode(ii, jj);
            if(i < n-1 && !visited.contains(encoded) && grid[ii][jj] != 'O') {
                queue.add(encoded);
                visited.add(encoded);
                coints.add(nl);
                addedNeighbor = true;
            }

            ii = i; jj=j-1;
            encoded = encode(ii, jj);
            if(j > 0 && !visited.contains(encoded) && grid[ii][jj] != 'O') {
                queue.add(encoded);
                visited.add(encoded);
                coints.add(nl);
                addedNeighbor = true;
            }

            ii = i; jj=j+1;
            encoded = encode(ii, jj);
            if(j < m-1 && !visited.contains(encoded) && grid[ii][jj] != 'O') {
                queue.add(encoded);
                visited.add(encoded);
                coints.add(nl);
                addedNeighbor = true;
            }

            if (!addedNeighbor) {
                minCoins += c;
            }
        }
        if (flagsVisited != flags) {
            return -1;
        }
        return minCoins;
    }

    private static int encode(int r, int c) {
        return (r * 100) + c;
    }
}
