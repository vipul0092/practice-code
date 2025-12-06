package code.vipul.aoc2025;

import code.vipul.utils.AoCInputReader;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://adventofcode.com/2025/day/6
 */
public class Day6 {

    public static void solve() {
        String[] lines = AoCInputReader.read(Day6.class, false).toArray(new String[]{});
        int maxlen = Arrays.stream(lines).map(String::length).max(Comparator.naturalOrder()).orElseThrow();

        // Make all lines of equal length for processing ease
        for (int i = 0; i < lines.length; i++) {
            lines[i] += " ".repeat(maxlen - lines[i].length());
        }

        long total1 = 0, total2 = 0;
        int current = 0, numberlines = lines.length - 1;
        String operators = lines[lines.length - 1];

        for (int i = 0; i < operators.length() && current < operators.length(); i++) {
            maxlen = 1;
            char operator = operators.charAt(current);

            // Figure out the max length of numbers in the current group
            int j = current + 1;
            while (j < operators.length() && operators.charAt(j) == ' ') {
                maxlen++;
                j++;
            }

            // part 1
            long currentTotal1 = operator == '*' ? 1 : 0;
            for (int line = 0; line < numberlines; line++) {
                String rownum = lines[line].substring(current, current + maxlen);
                rownum = rownum.strip();
                if (!rownum.isEmpty()) {
                    long number = Long.parseLong(rownum);
                    currentTotal1 = operator == '*' ? currentTotal1 * number : currentTotal1 + number;
                }
            }
            total1 += currentTotal1;

            // part 2
            long currentTotal2 = operator == '*' ? 1 : 0;
            for (int idx = 0; idx < maxlen; idx++) {
                String colnum = "";
                // Form the number columnwise
                for (int line = 0; line < numberlines; line++) {
                    colnum += lines[line].charAt(current + idx);
                }
                colnum = colnum.strip();
                if (!colnum.isEmpty()) {
                    long number = Long.parseLong(colnum);
                    currentTotal2 = operator == '*' ? currentTotal2 * number : currentTotal2 + number;
                }
            }
            total2 += currentTotal2;

            current += maxlen;
        }

        System.out.println("Part 1: " + total1); // 6100348226985
        System.out.println("Part 2: " + total2); // 12377473011151
    }
}
