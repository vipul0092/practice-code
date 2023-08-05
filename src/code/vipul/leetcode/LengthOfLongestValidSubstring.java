package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 16/07/23
 * https://leetcode.com/problems/length-of-the-longest-valid-substring/description/
 */
public class LengthOfLongestValidSubstring {

    public static void solve() {
        System.out.println(new LengthOfLongestValidSubstring()
                .longestValidSubstring("cbaaaabc", List.of("aaa", "bc")));
    }

    public int longestValidSubstring(String word, List<String> forbidden) {
        Trie root = new Trie();
        for (String f : forbidden) {
            addWord(f, root);
        }

        int max = 0;
        // Coming from back
        // int j = word.length() - 1;
        // for (int i = word.length() - 1; i >= 0; i--) {
        //     while(i <= j && find(word, i, j, root)) {
        //         j--;
        //     }
        //     max = Math.max(max, j - i + 1);
        // }

        // Going from front
        int left = 0;
        for (int i = 0; i < word.length(); i++) {
            for (int j = Math.max(left, i - 10); j < i + 1; j++) {
                if (find(word, j, i, root)) {
                    left = j + 1;
                }
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    private boolean find(String word, int s, int e, Trie root) {
        while(s <= e) {
            char ch = word.charAt(s);
            if (root.links[ch - 'a'] == null) return false;
            root = root.links[ch - 'a'];
            if (root.terminal) return true;
            s++;
        }
        return root != null && root.terminal;
    }

    private void addWord(String word, Trie root) {
        for (char ch : word.toCharArray()) {
            root = root.addLink(ch);
        }
        root.setTerminal();
    }

    public class Trie {

        private final Trie[] links;

        private boolean terminal = false;
        public Trie() {
            this.links = new Trie[26];
        }

        public Trie addLink(char ch) {
            Trie node;
            if (links[ch - 'a'] == null) {
                node = new Trie();
                links[ch - 'a'] = node;
            } else {
                node = links[ch - 'a'];
            }
            return node;
        }

        public void setTerminal() {
            terminal = true;
        }
    }
}
