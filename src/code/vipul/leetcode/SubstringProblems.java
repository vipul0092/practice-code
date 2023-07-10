package code.vipul.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vgaur created on 30/06/23
 */
public class SubstringProblems {

    public static void solveTwoDistinct() {
        System.out.println("Answer: " + new SubstringProblems()
                .twoDistinct("abbbababababababcccmabbbbcccccaaaaaad"));
    }

    public static void solveLongestWithoutRepeatingCharacters() {
        System.out.println("Answer: " + new SubstringProblems()
                .longestWithoutRepeatingCharacters("annhgddffgbnhdytaagdbbgdhhgtdygaasdeer"));
    }

    public String longestWithoutRepeatingCharacters(String s) {
        int start = 0, end = 0, counter = 0;
        int maxStart = 0, len = 0;
        Map<Character, Integer> count = new HashMap<>();
        for (char ch : s.toCharArray()) {
            count.putIfAbsent(ch, 0);
        }
        while(end < s.length()) {
            char endChar = s.charAt(end);
            if (count.get(endChar) > 0) {
                counter++;
            }
            count.put(endChar, count.get(endChar) + 1);

            while(counter > 0) {
                char startChar = s.charAt(start);
                count.put(startChar, count.get(startChar) - 1);
                if (count.get(startChar) >= 1) {
                    counter--;
                }
                start++;
            }

            if (end - start + 1 > len) {
                maxStart = start;
                len = end - start + 1;
            }
            end++;
        }
        return len == 0 ? "" : s.substring(maxStart, maxStart + len);
    }

    //Longest Substring with At Most Two Distinct Characters
    public String twoDistinct(String s) {
        Map<Character, Integer> count = new HashMap<>();
        int start = 0, end = 0, counter = 0;
        int maxStart = 0, len = 0;

        for (char ch : s.toCharArray()) {
            count.putIfAbsent(ch, 0);
        }

        while (end < s.length()) {
            char endChar = s.charAt(end);
            if (count.get(endChar) == 0) {
                counter++;
            }
            count.merge(endChar, 1, (v1, v2) -> v1 + v2);

            while (counter > 2) {
                char startChar = s.charAt(start);
                count.put(startChar, count.get(startChar) - 1);
                if (count.get(startChar) == 0) {
                    counter--;
                }
                start++;
            }

            if (end - start + 1 > len) {
                maxStart = start;
                len = end - start + 1;
            }
            end++;
        }
        return len == 0 ? "" : s.substring(maxStart, maxStart + len);
    }
}
