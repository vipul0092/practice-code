package code.vipul.aoc2025;

import code.vipul.utils.AoCInputReader;

import java.util.List;

/**
 * https://adventofcode.com/2025/day/1
 */
public class Day1 {

    private static final int TOTAL = 100;

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day1.class, false);

        int dial = 50, count = 0, prev = dial, change = 0;
        for (String l : lines) {
            char c = l.charAt(0);
            int num = Integer.parseInt(l.substring(1));

            int rounds = num / 100;
            int move = num % 100;
            if (c == 'L') {
                dial -= move;
            } else {
                dial += move;
            }

            change += rounds;
            if ((dial <= 0 || dial >= TOTAL) && prev != 0) {
                change++;
            }

            if (dial < 0) dial += TOTAL;
            dial %= TOTAL;

            if (dial == 0) {
                count++;
            }
            prev = dial;
        }

        System.out.println("Part 1: " + count); // 1018
        System.out.println("Part 2: " + change); // 5815
    }
}
