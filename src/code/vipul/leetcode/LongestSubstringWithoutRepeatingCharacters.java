package code.vipul.leetcode;

/**
 * Created by vgaur created on 05/08/23
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void solve() {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0;
        int max = 0;

        int[] counts = new int[500];
        int duplicates = 0;

        while(j < s.length()) {
            char ch = s.charAt(j);
            counts[ch]++;
            if (counts[ch] == 2) { // earlier it was unqiue, now its not
                duplicates++;
            }

            if (duplicates != 0) {
                while (duplicates > 0) {
                    ch = s.charAt(i);
                    counts[ch]--;
                    if (counts[ch] == 1) {
                        duplicates--;
                    }
                    i++;
                }

            }
            max = Math.max(j - i + 1, max);
            j++;
        }
        return max;
    }
}
