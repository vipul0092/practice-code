package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 10/07/23
 */
public class WordSearch {

    public static void solve() {
        System.out.println(new WordSearch()
                .exist(new char[][]{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}}, "ABCESEEEF"));
        System.out.println(new WordSearch()
                .exist(new char[][]{{'A','B'}}, "BA"));
    }

    public boolean exist(char[][] board, String word) {
        boolean exists = false;
        int rows = board.length; int cols = board[0].length;
        Set<Integer> starts = new HashSet<>();

        Map<Character, Integer> have = new HashMap<>();
        Map<Character, Integer> want = new HashMap<>();

        for (int i = 0; i <  rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    starts.add(combine(i, j));
                }
                have.merge(board[i][j], 1, (v1, v2) -> v1 + v2);
            }
        }

        for (char ch : word.toCharArray()) {
            want.merge(ch, 1, (v1, v2) -> v1 + v2);
        }

        for (Map.Entry<Character, Integer> entry : want.entrySet()) {
            if (!have.containsKey(entry.getKey()) || have.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }

        for (int start : starts) {
            int i = start / 100;
            int j = start % 100;

            long visited = setBit(0L, combine(i, j));
            boolean res = dive(i, j, 1, visited, board, word);
            if (res) {
                return true;
            }
        }
        return exists;
    }

    private boolean dive(int ii, int jj, int len, long v, char[][] board, String word) {
        if (len == word.length()) {
            return true;
        }

        char check = word.charAt(len);
        int rows = board.length;
        int cols = board[0].length;
        // up
        if (ii > 0 && board[ii-1][jj] == check) {
            int com = combine(ii-1, jj);
            if (!isBitSet(v, com)) {
                boolean res = dive(ii-1, jj, len + 1, setBit(v, com), board, word);
                if (res) {
                    return true;
                }
            }
        }

        // down
        if (ii < rows - 1 && board[ii+1][jj] == check) {
            int com = combine(ii+1, jj);
            if (!isBitSet(v, com)) {
                boolean res = dive(ii+1, jj, len + 1, setBit(v, com), board, word);
                if (res) {
                    return true;
                }
            }
        }

        // left
        if (jj > 0 && board[ii][jj-1] == check) {
            int com = combine(ii,jj-1);
            if (!isBitSet(v, com)) {
                boolean res = dive(ii, jj-1, len + 1, setBit(v, com), board, word);
                if (res) {
                    return true;
                }
            }
        }

        // right
        if (jj < cols-1 && board[ii][jj+1] == check) {
            int com = combine(ii,jj+1);
            if (!isBitSet(v, com)) {
                boolean res = dive(ii, jj+1, len + 1, setBit(v, com), board, word);
                if (res) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isBitSet(long bitSet, int bitPos) {
        return (bitSet & (1L << bitPos)) != 0;
    }

    private static long setBit(long bitSet, int bitPos) {
        return (bitSet | (1L << bitPos));
    }

    private int combine(int i, int j) {
        return (i * 7) + j;
    }
}
