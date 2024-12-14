package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.List;
import java.util.Set;

/**
 * https://adventofcode.com/2024/day/4
 */
public class Day4 {

    private static final int[][] DIFFS =
            new int[][]{{-1, 1}, {-1, -1}, {1, 1}, {1, -1}, {0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    private static final Set<String> VALID_MAS = Set.of("SAM", "MAS");

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day4.class, false);

        int count1 = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char x = get(lines, i, j);
                if (x != 'X') continue;
                for (int[] diff : DIFFS) {
                    char[] set = new char[4];
                    set[0] = 'X';
                    for (int k = 1; k < 4; k++) {
                        set[k] = get(lines, i + diff[0] * k, j + diff[1] * k);
                    }
                    if (String.valueOf(set).equals("XMAS")) {
                        count1++;
                    }
                }
            }
        }

        int count2 = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char a = get(lines, i, j);
                if (a != 'A') continue;
                char[] set1 = new char[]{get(lines, i - 1, j - 1), 'A', get(lines, i + 1, j + 1)};
                char[] set2 = new char[]{get(lines, i + 1, j - 1), 'A', get(lines, i - 1, j + 1)};

                String s1 = String.valueOf(set1), s2 = String.valueOf(set2);
                if (VALID_MAS.contains(s1) && VALID_MAS.contains(s2)) {
                    count2++;
                }
            }
        }

        System.out.println("Part 1: " + count1); // 2454
        System.out.println("Part 2: " + count2); // 1858
    }

    private static char get(List<String> lines, int i, int j) {
        try {
            return lines.get(i).charAt(j);
        } catch (Exception e) { // Swallow the exception to get rid of boundary checks
            return '\0';
        }
    }
}
