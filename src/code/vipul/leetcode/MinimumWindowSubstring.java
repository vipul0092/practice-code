package code.vipul.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by vgaur created on 29/06/23
 */
public class MinimumWindowSubstring {

    public static void solve() {
        System.out.println("Answer: " + new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        Map<Character, Integer> tcount = new HashMap<>();

        for (char ch : t.toCharArray()) {
            tcount.merge(ch, 1, (v1, v2) -> v1 + v2);
        }

        int start = 0, end = 0, counter = t.length();
        int minStart = 0, len = Integer.MAX_VALUE;

        while (end < s.length()) {
            char endChar = s.charAt(end);

            if (tcount.containsKey(endChar)) { // if its a character of interest
                if (tcount.get(endChar) > 0) { // We still need more of that character in the window
                    counter--;
                }

                // decrement count of the character
                // count = 0 -> means window has exact count
                // count > 0 -> means window still needs the character
                // count < 0 -> means window has extra character counts
                tcount.put(endChar, tcount.get(endChar) - 1);
            }

            // counter will be zero when all characters of t are within the window
            // so now we try to shrink the window by increasing "start"
            while (counter == 0) {
                if (end - start < len) {
                    minStart = start;

                    // Make sure that the minLength is the actual length of the window
                    len = end - start + 1;
                }

                // We'll be removing start from the window, so increase its count
                if (tcount.containsKey(s.charAt(start))) {
                    tcount.put(s.charAt(start), tcount.get(s.charAt(start)) + 1);

                    // if the count of character is greater than zero
                    // that means that the next window wont have the correct number of characters, hence adjust counter
                    if (tcount.get(s.charAt(start)) > 0) {
                        counter++;
                    }
                }
                start++;
            }
            end++;
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + len);
    }


    public String minWindowBruteForce(String s, String t) {

        if (s.length() == 1 && t.length() == 1) {
            return s.charAt(0) != t.charAt(0) ? "" : s;
        }

        if (t.length() > s.length()) {
            return "";
        }

        Set<CacheKey> evaluated = new HashSet<>();
        Map<Character, Integer> toCheckCounts = new HashMap<>();
        for (char ch : t.toCharArray()) {
            toCheckCounts.put(ch, toCheckCounts.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> testStringCounts = new HashMap<>();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (toCheckCounts.containsKey(ch)) {
                testStringCounts.put(ch, testStringCounts.getOrDefault(ch, 0) + 1);
                indices.add(i);
            }
        }

        if (!isValid(testStringCounts, toCheckCounts)) {
            return "";
        }

        int start = indices.get(0);
        int end = indices.get(indices.size() - 1);

        for (int i = start, j = end; i < j;) {
            char st = s.charAt(i);
            char en = s.charAt(j);

            boolean canRemoveStart = toCheckCounts.get(st) > testStringCounts.get(st);
        }

        Res res = new Res();
        res.fStart = -1;
        res.fEnd = -1;
        findMin(s, 0, s.length() - 1, testStringCounts, evaluated, toCheckCounts, res);

        return res.fStart == -1 && res.fEnd == -1 ? "" : s.substring(res.fStart, res.fEnd + 1);
    }

    private void findMin(String s, int starti, int endi, Map<Character, Integer> counts,
                         Set<CacheKey> evaluated, Map<Character, Integer> toCheckCounts, Res res) {
        if (starti > endi) {
            return;
        }

        CacheKey key = new CacheKey(starti, endi, counts);
        if (evaluated.contains(key)) {
            return;
        }

        if (!isValid(counts, toCheckCounts)) {
            evaluated.add(key);
            return;
        }

        int curlen = endi - starti;

        if (!res.hasValue() || (res.foundLen() > curlen)) {
            res.fStart = starti; res.fEnd = endi;
        }

        char start = s.charAt(starti);
        char end = s.charAt(endi);
        if (toCheckCounts.containsKey(start) && toCheckCounts.containsKey(end)) {
            Map<Character, Integer> reduced = new HashMap<>(counts);
            if (reduced.get(start) == 1) {
                reduced.remove(start);
            } else {
                reduced.put(start, reduced.get(start) - 1);
            }
            findMin(s, starti + 1, endi, reduced, evaluated, toCheckCounts, res);

            Map<Character, Integer> reduced2 = new HashMap<>(counts);
            if (reduced2.get(end) == 1) {
                reduced2.remove(end);
            } else {
                reduced2.put(end, reduced2.get(end) - 1);
            }
            findMin(s, starti, endi - 1, reduced2, evaluated, toCheckCounts, res);
        } else if (toCheckCounts.containsKey(start)) {
            Map<Character, Integer> reduced = new HashMap<>(counts);
            if (reduced.get(start) == 1) {
                reduced.remove(start);
            } else {
                reduced.put(start, reduced.get(start) - 1);
            }
            findMin(s, starti + 1, endi - 1, reduced, evaluated, toCheckCounts, res);

            findMin(s, starti, endi - 1, counts, evaluated, toCheckCounts, res);
        } else if (toCheckCounts.containsKey(end)) {
            Map<Character, Integer> reduced2 = new HashMap<>(counts);
            if (reduced2.get(end) == 1) {
                reduced2.remove(end);
            } else {
                reduced2.put(end, reduced2.get(end) - 1);
            }
            findMin(s, starti + 1, endi - 1, reduced2, evaluated, toCheckCounts, res);

            findMin(s, starti + 1, endi, counts, evaluated, toCheckCounts, res);
        } else {
            findMin(s, starti + 1, endi - 1, counts, evaluated, toCheckCounts, res);
        }

        evaluated.add(key);
    }

    public class Res {
        private int fStart;
        private int fEnd;

        public boolean hasValue() {
            return fStart != -1;
        }

        public int foundLen() {
            return fEnd - fStart;
        }
    }

    private static boolean isValid(Map<Character, Integer> toCheck, Map<Character, Integer> toCheckCounts) {
        for (Map.Entry<Character, Integer> entry : toCheckCounts.entrySet()) {
            if (!(toCheck.containsKey(entry.getKey()) && toCheck.get(entry.getKey()) >= entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public class CacheKey {
        private final int start;
        private final int end;
        private final Map<Character, Integer> counts;

        private int hash = -1;

        private CacheKey(int start, int end, Map<Character, Integer> counts) {
            this.start = start;
            this.end = end;
            this.counts = counts;
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(start);
                hash += (hash << 5) + Objects.hashCode(end);
                hash += (hash << 5) + counts.hashCode();
            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof CacheKey && equalTo((CacheKey) another);
        }

        private boolean equalTo(CacheKey another) {
            return Objects.equals(start, another.start)
                    && Objects.equals(end, another.end)
                    && Objects.equals(counts, another.counts);
        }
    }
}
