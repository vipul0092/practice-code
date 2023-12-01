package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 17/09/23
 * https://www.codingninjas.com/studio/problems/longest-repeating-substring_980523
 *
 * Sliding Window
 */
public class LongestRepeatingSubstring {

    public static void solve() {
        System.out.println(longestRepeatingSubstring("LLLLGAAAAAKKKAASSSSSYHHHHHTTTTTNYYYXX", 5));
    }

    public static int longestRepeatingSubstring(String str, int k) {
        int i = 0, j = 0;

        Map<Character, Integer> counts = new HashMap<>();
        TreeMap<Integer, Set<Character>> revcount =
                new TreeMap<>(Comparator.reverseOrder());
        int total = 0;

        int maxlen = 0;

        while(j < str.length()) {
            char ch = str.charAt(j);
            total++;
            change(counts, revcount, ch, true);

            if (total - revcount.firstKey() <= k) {
                maxlen = Math.max(maxlen, total);
            }

            while(total - revcount.firstKey() > k) {
                ch = str.charAt(i);
                total--;
                change(counts, revcount, ch, false);
                i++;
            }
            j++;
        }
        return maxlen;
    }

    private static void change(Map<Character, Integer> counts,
                               TreeMap<Integer, Set<Character>> revcount, char ch, boolean add) {
        int oldCount = counts.getOrDefault(ch, 0);
        int newCount = add ? oldCount + 1 : oldCount - 1;
        counts.put(ch, newCount);

        if (revcount.containsKey(oldCount)) {
            revcount.get(oldCount).remove(ch);
            if (revcount.get(oldCount).isEmpty()) {
                revcount.remove(oldCount);
            }
        }

        if (!revcount.containsKey(newCount)) {
            revcount.put(newCount, new HashSet<>());
        }
        revcount.get(newCount).add(ch);
    }
}
