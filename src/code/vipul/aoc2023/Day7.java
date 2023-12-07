package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static code.vipul.aoc2023.inputs.Inputs.DAY_7;

/**
 * Created by vgaur created on 06/12/23
 */
public class Day7 {

    private static String INPUT = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
            """;
    public static final int FIVE_OF_KIND = 7;
    public static final int FOUR_OF_KIND = 6;
    public static final int FULL_HOUSE = 5;
    public static final int THREE_OF_KIND = 4;
    public static final int TWO_PAIR = 3;
    public static final int ONE_PAIR = 2;
    public static final int HIGH_CARD = 1;

    public static void solve() {
        INPUT = DAY_7;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<String, Integer> handMap = new HashMap<>();
        List<String> hands = new ArrayList<>();
        for (String line : lines) {
            String[] str = line.split(" ");
            hands.add(str[0]);
            handMap.put(str[0], Integer.parseInt(str[1]));
        }

        System.out.println("Part 1: " + getSum(hands, handMap, false)); // 246394540
        System.out.println("Part 2: " + getSum(hands, handMap, true)); // 244848487
    }

    private static int getSum(List<String> hands, Map<String, Integer> handMap, boolean considerJ) {
        hands.sort((c1, c2) -> compare(c1, c2, considerJ));

        int sum = 0, rank = 1;
        for (String h : hands) {
            sum += (rank * handMap.get(h));
            rank++;
        }
        return sum;
    }

    private static int compare(String s1, String s2, boolean considerJ) {
        Map<Character, Integer> chars1 = new HashMap<>();
        Map<Character, Integer> chars2 = new HashMap<>();

        for (char c : s1.toCharArray()) chars1.merge(c, 1, Integer::sum);
        for (char c : s2.toCharArray()) chars2.merge(c, 1, Integer::sum);

        int hand1 = getHand(chars1, considerJ);
        int hand2 = getHand(chars2, considerJ);
        if (hand1 < hand2) {
            return -1;
        } else if (hand1 > hand2) {
            return 1;
        }
        // if same hand
        for (int i = 0; i < 5; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                int c1 = getCard(s1.charAt(i), considerJ);
                int c2 = getCard(s2.charAt(i), considerJ);
                return c1 < c2 ? -1 : 1;
            }
        }
        throw new RuntimeException("Not expected to come here");
    }

    private static int getHand(Map<Character, Integer> chars,  boolean considerJ) {
        int jcount = considerJ ? chars.getOrDefault('J', 0) : 0;
        List<Integer> vals = chars.values().stream().toList();
        switch (chars.size()) {
            case 1 -> {  // 5 of a kind
                return FIVE_OF_KIND;
            }
            case 2 -> {
                if (vals.get(0) == 4 || vals.get(1) == 4) { // 4 of a kind
                    if (jcount > 0) return FIVE_OF_KIND; // 5 of a kind XXXXJ
                    return FOUR_OF_KIND;
                } else { // full house
                    if (jcount == 2 || jcount == 3) return FIVE_OF_KIND; // 5 of a kind JJXXX or XXJJJ
                    return FULL_HOUSE;
                }
            }
            case 3 -> {
                if (vals.get(0) == 3 || vals.get(1) == 3 || vals.get(2) == 3) { // three of a kind
                    if (jcount == 3 || jcount == 1) return FOUR_OF_KIND; // 4 of a kind XXXJB or JJJAB
                    return THREE_OF_KIND;
                } else { // 2 pair
                    if (jcount == 2) return FOUR_OF_KIND; // four of a kind XXJJZ
                    if (jcount == 1) return FULL_HOUSE; // full house XXYYJ
                    return TWO_PAIR;
                }
            }
            case 4 -> {  // one pair
                if (jcount == 2 || jcount == 1) return THREE_OF_KIND; // 3 of a kind XXJBC or JJABC
                return ONE_PAIR;
            }
        }
        if (jcount > 0) {
            return ONE_PAIR; // 1 pair ABCDJ
        }
        return HIGH_CARD; // high card
    }

    private static int getCard(char card, boolean considerJ) {
        int priority;
        switch (card) {
            case 'A' -> priority = 13;
            case 'K' -> priority = 12;
            case 'Q' -> priority = 11;
            case 'T' -> priority = 10;
            case 'J' -> priority = considerJ ? 1 : 9;
            default -> priority = card - '0' - (considerJ ? 0 : 1);
        }
        return priority;
    }
}
