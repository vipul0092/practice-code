package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 14/08/23
 */
public class SubstringWithConcatenationOfAllWords {

    public static void solve() {
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstring(
                "aaa",
                new String[]{"a","a","a"}
        ));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        int k = words.length * words[0].length();
        int len = words[0].length();

        if (k > s.length()) return List.of();

        Trie root = new Trie();
        Trie[] ends = new Trie[words.length];
        for (int i = 0; i < words.length; i++) {
            ends[i] = root.addWord(words[i]);
        }

        List<Integer> answer = new ArrayList<>();
        Char[] sequences = new Char[words.length];
        Char[] sends = new Char[words.length];
        for (int i = 0; i < words.length; i++) {
            Char sequence = null;
            for (int j = i*len; j < (i+1)*len; j++) {
                Char current = new Char(s.charAt(j));
                if (sequence != null) {
                    sequence.next = current;
                } else {
                    sequences[i]  = current;
                }
                sequence = current;
            }
            sends[i] = sequence;
        }
        if (check(root, sequences, ends)) {
            answer.add(0);
        }

        int start = 1, j = k;
        while(j < s.length()) {

            char ch = s.charAt(j);
            Char node = new Char(ch);
            for (int i = 0; i < sequences.length - 1; i++) {
                if (len == 1) {
                    sequences[i] = new Char(sequences[i+1].val);
                    sends[i] = sequences[i];
                } else {
                    sequences[i] = sequences[i].next;
                    sends[i].next = new Char(sequences[i+1].val);
                    sends[i] = sends[i].next;
                }
            }
            if (len == 1) {
                sequences[sequences.length - 1] = node;
                sends[sequences.length - 1] = node;
                sequences[sequences.length - 1].next = null;
            } else {
                sequences[sequences.length - 1] = sequences[sequences.length - 1].next;
                sends[sequences.length - 1].next = node;
                sends[sequences.length - 1] = node;
            }

            if (check(root, sequences, ends)) {
                answer.add(start);
            }
            start++;
            j++;
        }
        return answer;

    }

    private boolean check(Trie trie, Char[] sequences, Trie[] ends) {
        for (Trie t : ends) t.reset();

        for (Char sequence : sequences) {
            Trie current = trie;
            char first = sequence.val;
            boolean found = true;
            while(sequence != null) {
                if (current.links[sequence.val  - 'a'] == null) {
                    found = false;
                    break;
                }
                current = current.links[sequence.val  - 'a'];
                sequence = sequence.next;
            }

            if (found) {
                current.runningCounts.merge(first, 1, (v1,v2) -> v1 + v2);
            }
        }

        boolean match = true;
        for (Trie t : ends) {
            if (!t.matchCounts()) {
                match = false;
                break;
            }
        }
        return match;
    }

    private class Char {
        private char val;
        private Char next;

        Char(char c) {
            this.val = c;
        }
    }

    private class Trie {
        private Trie[] links;
        private boolean terminal;
        private Map<Character, Integer> counts;

        private Map<Character, Integer> runningCounts;

        private Boolean matched;

        public Trie() {
            this.links = new Trie[26];
            terminal = false;
            counts = new HashMap<>();
        }

        public Trie addWord(String word) {
            Trie current = this;
            char start = word.charAt(0);
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.links[ch - 'a'] == null) {
                    current.links[ch - 'a'] = new Trie();
                }
                current = current.links[ch - 'a'];
            }
            current.terminal = true;
            current.counts.merge(start, 1, (v1, v2) -> v1 + v2);
            return current;
        }

        public void reset() {
            runningCounts = new HashMap<>();
            matched = null;
        }

        public boolean matchCounts() {
            if (matched != null) return matched;
            boolean m = true;

            for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
                if (!runningCounts.containsKey(entry.getKey())
                        || !runningCounts.get(entry.getKey()).equals(entry.getValue())) {
                    m = false;
                    break;
                }
            }
            matched = m;
            return m;
        }
    }

    // Naive substring + sliding window solution, AC
    public List<Integer> findSubstring2(String s, String[] words) {
        Map<String, Integer> all = new HashMap<>();
        for (String w : words) all.merge(w, 1, (v1,v2) -> v1 + v2);
        int k = words.length * words[0].length();
        int len = words[0].length();

        if (k > s.length()) return List.of();

        List<Integer> answer = new ArrayList<>();
        String[] sub = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i*len; j < (i+1)*len; j++) {
                sb.append(s.charAt(j));
            }
            sub[i] = sb.toString();
        }

        if (hasAll(sub, all)) {
            answer.add(0);
        }

        int start = 1, j = k;
        while(j < s.length()) {
            String[] newsub = new String[words.length];

            Set<Integer> found = new HashSet<>();
            for (int i = 0; i < sub.length - 1; i++) {
                String str = (sub[i] + sub[i+1].charAt(0)).substring(1, 1+len);
                newsub[i] = str;
            }
            newsub[newsub.length-1] = (sub[sub.length-1] + s.charAt(j)).substring(1, 1+len);

            if (hasAll(newsub, all)) {
                answer.add(start);
            }
            sub = newsub;
            start++;
            j++;
        }
        return answer;
    }

    private boolean hasAll(String[] current, Map<String, Integer> all) {
        Map<String, Integer> found = new HashMap<>();
        for (int i = 0; i < current.length; i++) {
            found.merge(current[i], 1, (v1,v2) -> v1 + v2);
        }

        for (Map.Entry<String, Integer> entry : all.entrySet()) {
            if (!found.containsKey(entry.getKey())
                    || !found.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
