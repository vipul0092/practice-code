package code.vipul.leetcode;

import java.util.*;

/**
 * Created by vgaur created on 03/07/23
 */
public class FindAnagrams {

    public static void solve() {
        System.out.println(new FindAnagrams().findAnagrams("cbaebabacd", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) {
            return List.of();
        }

        Map<Character, Integer> need = new HashMap<>();
        for (char ch : p.toCharArray()) {
            need.merge(ch, 1, (v1, v2) -> v1 + v2);
        }


        Map<Character, Integer> have = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            have.merge(ch, 1, (v1, v2) -> v1 + v2);
        }


        List<Integer> answer = new ArrayList<>();
        if (have.equals(need)) {
            answer.add(0);
        }

        for (int j = p.length(); j < s.length(); j++) {
            char removed = s.charAt(j - p.length());
            char added = s.charAt(j);

            if (have.get(removed) == 1) {
                have.remove(removed);
            } else {
                have.put(removed, have.get(removed) - 1);
            }
            have.merge(added, 1, (v1, v2) -> v1 + v2);

            if (have.equals(need)) {
                answer.add(j - p.length() + 1);
            }
        }

        return answer;
    }
}
