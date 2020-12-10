package code.vipul.aoc2020;

import java.util.Arrays;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs.INPUT_2;

/**
 * https://adventofcode.com/2020/day/2
 */
public class Solve2 {

    public static void solve() {
        var inputs = Arrays.stream(INPUT_2.split("\n")).collect(Collectors.toList());

        int valid = 0;
        for (String in: inputs) {
            String[] firstBreak = in.split(":");
            String[] countChar = firstBreak[0].split(" ");

            String[] minMaxCounts = countChar[0].split("-");
            int minCount = Integer.parseInt(minMaxCounts[0]);
            int maxCount = Integer.parseInt(minMaxCounts[1]);
            char character = countChar[1].charAt(0);
            String password = firstBreak[1].trim();

            int actualCount = 0;
            for(char ch: password.toCharArray()){
                if (character == ch) {
                    actualCount++;
                }
            }

            if (actualCount >= minCount && actualCount <= maxCount) {
                valid++;
            }
        }
        System.out.println("Answer: " + valid); // 536
    }

    public static void solvePart2() {
        var inputs = Arrays.stream(INPUT_2.split("\n")).collect(Collectors.toList());

        int valid = 0;
        for (String in: inputs) {
            String[] firstBreak = in.split(":");
            String[] countChar = firstBreak[0].split(" ");

            String[] minMaxCounts = countChar[0].split("-");
            int minCount = Integer.parseInt(minMaxCounts[0]);
            int maxCount = Integer.parseInt(minMaxCounts[1]);
            char character = countChar[1].charAt(0);
            String password = firstBreak[1].trim();

            boolean isCharAtFirstPos = password.charAt(minCount - 1) == character;
            boolean isCharAtSecondPos = password.charAt(maxCount - 1) == character;

            if ((isCharAtFirstPos || isCharAtSecondPos) && !(isCharAtFirstPos && isCharAtSecondPos)) {
                valid++;
            }
        }
        System.out.println("Answer: " + valid); // 558
    }
}
