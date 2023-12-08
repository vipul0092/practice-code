package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
    private static final Map<List<Integer>, Integer> HAND_PRIORITIES =
            Map.of(List.of(5), 7, List.of(4, 1), 6, List.of(3, 2), 5, List.of(3,1,1), 4,
                    List.of(2,2,1), 3, List.of(2,1,1,1), 2, List.of(1,1,1,1,1), 1);

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

        System.out.println("Part 1: " + getSum(hands, handMap, false)); // 246409899
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

        int compare = Integer.compare(getHandPriority(chars1, considerJ), getHandPriority(chars2, considerJ));
        if (compare != 0) {
            return compare;
        }
        // if same hand
        for (int i = 0; i < 5; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return Integer.compare(getCardPriority(s1.charAt(i), considerJ),
                        getCardPriority(s2.charAt(i), considerJ));
            }
        }
        throw new RuntimeException("Not expected to come here");
    }

    private static int getHandPriority(Map<Character, Integer> chars, boolean considerJ) {
        int jcount = considerJ ? chars.getOrDefault('J', 0) : 0;
        if (considerJ) {
            chars.remove('J');
        }
        List<Integer> vals = new ArrayList<>(chars.values().stream().sorted(Comparator.reverseOrder()).toList());
        if (jcount > 0) {
            if (vals.isEmpty()) {
                vals = List.of(5);
            } else {
                vals.set(0, vals.get(0) + jcount);
            }
        }
        return HAND_PRIORITIES.get(vals);
    }

    private static int getCardPriority(char card, boolean considerJ) {
        int priority;
        switch (card) {
            case 'A' -> priority = 13;
            case 'K' -> priority = 12;
            case 'Q' -> priority = 11;
            case 'J' -> priority = considerJ ? 0 : 10;
            case 'T' -> priority = 9;
            default -> priority = card - '0' - 1;
        }
        return priority;
    }
}
