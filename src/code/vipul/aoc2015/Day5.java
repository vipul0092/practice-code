package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_5;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day5 {

    private static String INPUT = """
            qjhvhtzxzqqjkmpb
            xxyxx
            aaa
            aaaa
            uurcxstgmygtbstg
            ieodomkazucvgmuy
            """;
    private static final Set<String> NOT = Set.of("ab", "pq", "cd", "xy");
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public static void solve() {
        INPUT = DAY_5;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        int count = 0;
        for (String line : lines) {
            boolean freeOfDisallowed = true, twice = false;
            int vowels = 0;
            for (int i = 0; i < line.length()-1; i++) {
                if (VOWELS.contains(line.charAt(i))) vowels++;
                if (NOT.contains(String.valueOf(line.charAt(i)) + line.charAt(i + 1))) {
                    freeOfDisallowed = false;
                    break;
                }
                twice |= (line.charAt(i+1) == line.charAt(i));
            }
            if (VOWELS.contains(line.charAt(line.length()-1))) vowels++;
            if (freeOfDisallowed && twice && vowels >= 3) count++;
        }
        System.out.println("Part 1: " + count); // 236

        count = 0;
        for (String line : lines) {
            Map<String, Integer> doubleIndices = new HashMap<>();
            boolean foundPair = false;
            for (int i = 0; i < line.length()-1; i++) {
                String str = String.valueOf(line.charAt(i)) + line.charAt(i + 1);
                if (doubleIndices.containsKey(str) && i > doubleIndices.get(str) + 1) {
                    foundPair = true;
                    break;
                } else if (!doubleIndices.containsKey(str)) {
                    doubleIndices.put(str, i);
                }
            }

            boolean foundTriplet = false;
            for (int i = 0; i < line.length()-2; i++) {
                if (line.charAt(i) == line.charAt(i+2)) {
                    foundTriplet = true;
                    break;
                }
            }
            if (foundTriplet && foundPair) count++;
        }
        System.out.println("Part 2: " + count); // 51
    }
}
