package code.vipul.leetcode;

/**
 * Created by vgaur created on 19/08/23
 * https://www.codingninjas.com/studio/problems/number-of-distinct-substring_1465938
 */
public class DistinctSubstring {

    public static void solve() {
        System.out.println(distinctSubstring("abcabc"));
    }

    public static int distinctSubstring(String word) {
        Trie root = new Trie();
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            Trie current = root;
            for (int j = i; j < word.length(); j++) {
                char ch = word.charAt(j);
                if (current.links[ch - 'a'] == null) {
                    current.links[ch - 'a'] = new Trie();
                }
                current = current.links[ch - 'a'];
                if (!current.terminal) {
                    count++;
                }
                current.terminal = true;
            }
        }
        return count;
    }

    public static class Trie {
        private Trie[] links;
        private boolean terminal;

        public Trie() {
            this.links = new Trie[26];
            this.terminal = false;
        }
    }
}
