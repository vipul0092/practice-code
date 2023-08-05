package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 04/08/23
 * https://leetcode.com/problems/word-break/description/
 */
public class WordBreak {

    public static void solve() {
        System.out.println(new WordBreak().wordBreak("abc", List.of("a", "b", "c")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {

        Trie root = new Trie();
        for (String w : wordDict) {
            addWord(w, root);
        }
        return solve(s, 0, root, new int[s.length()]);
    }

    private boolean solve(String str, int s, Trie root, int[] dp) {
        if (s == str.length()) {
            return false;
        }

        if (dp[s] != 0) {
            return dp[s] == 1;
        }

        Trie current = root;
        boolean result = false;
        for (int i = s; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (current.links[ch - 'a'] == null) {
                break;
            }
            current = current.links[ch - 'a'];
            if (current.terminal) { // found a word
                result = i == str.length() - 1 || solve(str, i + 1, root, dp);
            }

            if (result) {
                break;
            }
        }
        dp[s] = result ? 1 : -1;
        return result;
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
