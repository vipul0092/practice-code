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
                var sums = getSums(pattern);
                sum += sums.part1;
                sum2 += sums.part2;
                pattern = new ArrayList<>();
            }

        }
        System.out.println("Part 1: " + sum); // 30802
        System.out.println("Part 2: " + sum2); // 37876
    }

    private static Sums getSums(List<String> patterns) {
        var result = getResult(patterns, null, false);
        var result2 = getResult(patterns, result, true);
        return new Sums(result.getScore(), result2.getScore());
    }

    private static Result getResult(List<String> patterns, Result previousResult, boolean smudge) {
        int mid = getMid(patterns, false, previousResult, smudge);
        if (mid != -1) {
            return new Result(mid + 1, false);
        }

        List<String> vertical = new ArrayList<>();
        for (int j = 0; j < patterns.get(0).length(); j++) {
            StringBuilder sb = new StringBuilder();
            for (String p : patterns) {
                sb.append(p.charAt(j));
            }
            vertical.add(sb.toString());
        }
        mid = getMid(vertical, true, previousResult, smudge);
        if (mid != -1) {
            return new Result(mid + 1, true);
        }

        throw new RuntimeException("NOT POSSIBLE!");
    }

    private static int getMid(List<String> pattern, boolean vertical, Result previousResult, boolean smudge) {
        int mid = -1;
        for (int i = 0; i < pattern.size() - 1; i++) {
            int start = i;
            boolean smudgetaken = false;
            for (int end = i + 1; start >= 0 && end < pattern.size(); end++, start--) {
                String p1 = pattern.get(start);
                String p2 = pattern.get(end);
                if (p1.equals(p2)) {
                    mid = i;
                } else {
                    int diff = 0;
                    for (int k = 0; diff <= 1 && smudge && k < p1.length(); k++) {
                        if (p1.charAt(k) != p2.charAt(k)) {
                            diff++;
                        }
                    }

                    if (diff == 1 && !smudgetaken) {
                        mid = i;
                        smudgetaken = true;
                    } else {
                        mid = -1;
                        break;
                    }
                }
            }
            if (mid != -1 &&
                (previousResult == null || previousResult.vertical != vertical || previousResult.val != mid + 1)) {
                break;
            }
            mid = -1;
        }
        return mid;
    }

    record Sums(int part1, int part2) {
    }

    record Result(int val, boolean vertical) {
        public int getScore() {
            return vertical ? val : 100 * val;
        }
    }
}
