package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 25/08/23
 * https://leetcode.com/problems/longest-substring-of-one-repeating-character/description/
 *
 * Really Hard
 * Segment Tree
 *
 * Hardest
 */
public class LongestSubstringOfOneRepeatingCharacter {

    public static void solve() {
        // Output = [1,1,2,2,2,2,2,2,2,1]
        System.out.println(Arrays.toString(new LongestSubstringOfOneRepeatingCharacter()
                .longestRepeating("geuqjmt", "bgemoegklm", new int[]{3,4,2,6,5,6,5,4,3,2})));
    }

    private int[][] tree; // 0 - count, 1 - prefix, 2 - suffix
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();

        tree = new int[4*n][3];

        build(s, 0, 0, n-1);

        int[] ret = new int[queryIndices.length];
        for (int i = 0; i < queryIndices.length; i++) {
            update(queryCharacters.charAt(i), queryIndices[i], 0, 0, n-1);
            ret[i] = tree[0][0];
        }
        return ret;
    }

    private void build(String s, int tidx, int lo, int hi) {
        if (hi == lo) {
            tree[tidx][0] = 1;
            tree[tidx][1] = 30 + (int)(s.charAt(lo) - 'a');
            tree[tidx][2] = 30 + (int)(s.charAt(lo) - 'a');
            return;
        }

        int mid = (lo+hi)/2, left = (2*tidx)+1, right = left+1;
        build(s, left, lo, mid);
        build(s, right, mid+1, hi);

        int leftLen = mid - lo + 1, rightLen = hi - mid, fullLen = hi - lo + 1;
        combine(tidx, left, right, rightLen, leftLen, fullLen);
    }

    private void update(char val, int idx, int tidx, int lo, int hi) {
        if (idx < lo || idx > hi) return;

        if (lo == hi) {
            tree[tidx][0] = 1;
            tree[tidx][1] = 30 + (int)(val - 'a');
            tree[tidx][2] = 30 + (int)(val - 'a');
            return;
        }

        int mid = (lo+hi)/2, left = (2*tidx)+1, right = left+1;
        if (idx <= mid) update(val, idx, left, lo, mid);
        else update(val, idx, right, mid+1, hi);

        int leftLen = mid - lo + 1, rightLen = hi - mid, fullLen = hi - lo + 1;
        combine(tidx, left, right, rightLen, leftLen, fullLen);
    }

    private void combine(int tidx, int left, int right, int rightLen, int leftLen, int fullLen) {
        int leftSuffixCount = tree[left][2] / 30, leftChar = tree[left][2] % 30;
        int rightPrefixCount = tree[right][1] / 30, rightChar = tree[right][1] % 30;
        tree[tidx][0] = Math.max(tree[left][0], tree[right][0]);
        if (leftChar == rightChar) { // Characters match, i.e. combine
            tree[tidx][0] = Math.max(tree[tidx][0], leftSuffixCount + rightPrefixCount);
            if (leftSuffixCount == leftLen && rightPrefixCount == rightLen) { // Everything is the same
                tree[tidx][1] = (30 * tree[tidx][0]) + leftChar;
                tree[tidx][2] = (30 * tree[tidx][0]) + rightChar;
            } else if (leftSuffixCount == leftLen) {
                tree[tidx][0] = Math.max(tree[tidx][0], leftSuffixCount + rightPrefixCount);
                tree[tidx][1] = (30 * (leftSuffixCount + rightPrefixCount)) + leftChar;
                tree[tidx][2] = tree[right][2];
            } else if (rightPrefixCount == rightLen) {
                tree[tidx][0] = Math.max(tree[tidx][0], leftSuffixCount + rightPrefixCount);
                tree[tidx][1] = tree[left][1];
                tree[tidx][2] = (30 * (leftSuffixCount + rightPrefixCount)) + rightChar;
            } else {
                tree[tidx][1] = tree[left][1];
                tree[tidx][2] = tree[right][2];
            }
        } else {
            tree[tidx][1] = tree[left][1];
            tree[tidx][2] = tree[right][2];
        }
    }
}
