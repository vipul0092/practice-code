package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2023.inputs.Inputs.DAY_13;

/**
 * Created by vgaur created on 13/12/23
 */
public class Day13 {

    private static String INPUT = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
                        
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
            """;

    public static void solve() {
        INPUT = DAY_13;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        List<String> pattern = new ArrayList<>();

        int sum = 0;
        int sum2 = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (!line.isEmpty()) {
                pattern.add(line);
            }
            if (line.isEmpty() || i == lines.size() - 1) {
                sum += getScore(pattern, false);
                sum2 += getScore(pattern, true);
                pattern = new ArrayList<>();
            }
        }
        System.out.println("Part 1: " + sum); // 30802
        System.out.println("Part 2: " + sum2); // 37876
    }

    private static int getScore(List<String> patterns, boolean smudge) {
        boolean vertical = false;
        int mid = getMid(getIntegers(patterns, false), smudge); // Horizontal
        if (mid == -1) {
            vertical = true;
            mid = getMid(getIntegers(patterns, true), smudge); // Vertical
        }
        assert mid != -1;
        return vertical ? mid + 1 : 100 * (mid + 1);
    }

    private static int getMid(List<Integer> numbers, boolean smudge) {
        int mid = -1;
        for (int i = 0; i < numbers.size() - 1; i++) {
            int start = i;
            boolean smudgefound = false;
            for (int end = i + 1; start >= 0 && end < numbers.size(); end++, start--) {
                int xor = numbers.get(start) ^ numbers.get(end);
                // During exact match -> xor should be zero i.e. all bits match
                // During smudge match -> xor can be zero or a power of 2 i.e. one bit doesn't match
                boolean valid = (xor == 0 && !smudge) || (smudge && (xor & (xor - 1)) == 0);
                smudgefound |= smudge && valid && xor > 0;
                if (valid) {
                    mid = i;
                } else {
                    mid = -1;
                    break;
                }
            }
            if (mid != -1 && (!smudge || smudgefound)) {
                break;
            }
            mid = -1;
        }
        return mid;
    }

    private static List<Integer> getIntegers(List<String> patterns, boolean vertical) {
        List<Integer> ints = new ArrayList<>(patterns.size());
        if (vertical) {
            for (int j = 0; j < patterns.get(0).length(); j++) {
                int num = 0;
                for (int i = 0; i < patterns.size(); i++) {
                    if (patterns.get(i).charAt(j) == '#') { // consider # as 1
                        num += (1 << i);
                    }
                }
                ints.add(num);
            }
        } else {
            for (String p : patterns) {
                int num = 0;
                for (int i = 0; i < p.length(); i++) {
                    if (p.charAt(i) == '#') { // consider # as 1
                        num += (1 << i);
                    }
                }
                ints.add(num);
            }
        }
        return ints;
    }
}
