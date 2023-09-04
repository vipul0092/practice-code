package code.vipul.leetcode;

import java.util.Arrays;

/**
 * Created by vgaur created on 04/09/23
 * https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
 *
 * Hard
 * Trie with bits
 */
public class MaximizeXorWithAnElementFromArray {

    public static void solve() {
        System.out.println(Arrays.toString(new MaximizeXorWithAnElementFromArray().maximizeXor(
                new int[]{536870912, 0, 534710168, 330218644, 142254206},
                new int[][]{{558240772, 1000000000}, {307628050, 1000000000}, {3319300, 1000000000},
                        {2751604, 683297522}, {214004, 404207941}}
        )));
        System.out.println(Arrays.toString(new MaximizeXorWithAnElementFromArray().maximizeXor(
                new int[]{5,2,4,6,6,3},
                new int[][]{{12,4}, {8,1}, {6,3}}
        )));
    }

    private static final int MAX_BITS = 30;
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Trie root = new Trie();

        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            Trie current = root;
            for (int i = MAX_BITS; i >= 0; i--) {
                boolean set = (num & (1<<i)) != 0;
                int bit = set ? 1 : 0;
                if (current.links[bit] == null) {
                    current.links[bit] = new Trie();
                }
                current = current.links[bit];
                current.minValue = Math.min(current.minValue, num);
            }
        }

        int[] maxor = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int limit = queries[i][1];
            int value = queries[i][0];
            if (limit < min) {
                maxor[i] = -1;
                continue;
            } else if (limit == 0) {
                maxor[i] = value;
                continue;
            }
            maxor[i] = getXor(root, value, limit);
        }
        return maxor;
    }

    private int getXor(Trie root, int value, int limit) {
        Trie current = root;
        int xor = 0;
        for (int j = MAX_BITS; j >= 0; j--) {
            boolean setValue = (value & (1<<j)) != 0;
            int checkBitValue = setValue ? 0 : 1;
            int otherBitValue = setValue ? 1 : 0;

            if (current.links[checkBitValue] != null) {
                if (current.links[checkBitValue].minValue <= limit) {
                    xor |= (1<<j);
                    current = current.links[checkBitValue];
                } else {
                    if (setValue)  xor |= (1<<j);
                    current = current.links[0];
                }
            } else {
                current = current.links[otherBitValue];
            }
        }
        return xor;
    }

    private class Trie {
        private Trie[] links;
        private int minValue;

        public Trie() {
            this.links = new Trie[2];
            this.minValue = Integer.MAX_VALUE;
        }
    }
}
