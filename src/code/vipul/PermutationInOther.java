package code.vipul;

import java.util.HashMap;
import java.util.Map;

/**
 * solve() ascertains whether a permutation of the small string is present in the bigger string
 * Created by vgaur on 04/12/18.
 */
public class PermutationInOther {

    public static void solve(String small, String big) {

        Map<Character, Integer> charCountSmall = new HashMap<>();
        Map<Character, Integer> charCountBig = new HashMap<>();
        int lengthSmall = small.length();
        int bigLength = big.length();

        // O(lengthSmall) * O(1) [Amortized]
        for (int i = 0; i < lengthSmall; i++) {
            char character = small.charAt(i);
            if (!charCountSmall.containsKey(character)) {
                charCountSmall.put(character, 0);
            }
            Integer count = charCountSmall.get(character);
            charCountSmall.put(character, count + 1);
        }

        // O(lengthSmall) * O(1) [Amortized]
        for (int i = 0; i < lengthSmall; i++) {
            char character = big.charAt(i);
            if (!charCountBig.containsKey(character)) {
                charCountBig.put(character, 0);
            }
            Integer count = charCountBig.get(character);
            charCountBig.put(character, count + 1);
        }

        // O(1)
        if (doMapsMatch(charCountSmall, charCountBig)) {
            System.out.println("Found at position 0");
        }

        // O(bigLength)
        for (int j = lengthSmall; j < bigLength; j++) {
            char inclusion = big.charAt(j);
            char excluded = big.charAt(j - lengthSmall);

            if (charCountBig.containsKey(inclusion)) {
                charCountBig.put(inclusion, charCountBig.get(inclusion) + 1);
            } else {
                charCountBig.put(inclusion, 1);
            }
            if (charCountBig.get(excluded) == 1) {
                charCountBig.remove(excluded);
            } else {
                charCountBig.put(excluded, charCountBig.get(excluded) - 1);
            }

            if (doMapsMatch(charCountSmall, charCountBig)) {
                System.out.println("Found at position " + j);
            }
        }

    }

    private static boolean doMapsMatch(Map<Character, Integer> charCountSmall,
                                       Map<Character, Integer> charCountBig) {
        boolean doMapsMatch = true;
        for (Map.Entry<Character, Integer> entry : charCountSmall.entrySet()) {
            if (!charCountBig.containsKey(entry.getKey())
                    || !charCountBig.get(entry.getKey()).equals(entry.getValue())) {
                doMapsMatch = false;
                break;
            }
        }
        return doMapsMatch;
    }

}
