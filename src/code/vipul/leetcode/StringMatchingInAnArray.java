package code.vipul.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/string-matching-in-an-array
 * This^ is easy, but the solution is using Trie which scales when list of strings is large
 */
public class StringMatchingInAnArray {

    public static void solve() {
        String[] words = new String[]{"mass","as","hero","superhero"};
        System.out.println(new StringMatchingInAnArray().stringMatching(words));
    }

    class Trie {
        private Trie[] links;
        private int frequency;

        public Trie() {
            links = new Trie[26];
            frequency = 0;
        }

        public void add(String s, int from) {
            Trie curr = this;
            for (int i = from; i < s.length(); i++) {
                char c = s.charAt(i);
                if (curr.links[c-'a'] == null) {
                    curr.links[c - 'a'] = new Trie();
                }
                curr = curr.links[c-'a'];
                curr.frequency++;
            }
        }

        public boolean search(String s) {
            Trie curr = this;
            for (int i = 0; i < s.length(); i++) {
                curr = curr.links[s.charAt(i)-'a'];
                if (curr == null) break;
            }
            return curr != null && curr.frequency > 1; // frequency > 1 means its part of multiple words
        }
    }

    public List<String> stringMatching(String[] words) {
        List<String> ret = new ArrayList<>();
        Trie trie = new Trie();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                trie.add(word, i);
            }
        }

        for (String word : words) {
            if (trie.search(word))
                ret.add(word);
        }

        return ret;
    }
}
