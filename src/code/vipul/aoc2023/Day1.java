package code.vipul.aoc2023;

import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2023.inputs.Inputs.DAY_1;


/**
 * Created by vgaur created on 01/12/23
 */
public class Day1 {

    private static final String INPUT_1 = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet""";
    private static final String INPUT_2 =
            """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen""";

    private static final List<String> NOS =
            List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    public static void solve() {
        List<String> lines = Arrays.stream(DAY_1.split("\n")).toList();
        //List<String> lines = Arrays.stream(INPUT_1.split("\n")).toList();

        int sum = 0;
        for (String line : lines) {
            char first = '\0';
            char second = '\0';
            for (char ch : line.toCharArray()) {
                if (ch >= '0' && ch <= '9') {
                    if (first == '\0') {
                        first = ch;
                    }
                    second = ch;
                }
            }
            sum += ((first-'0')*10) + (second-'0');
        }

        System.out.println("Ans: " + sum); // 53334

        solvePart2();
    }

    public static void solvePart2() {
        List<String> lines = Arrays.stream(DAY_1.split("\n")).toList();
        //List<String> lines = Arrays.stream(INPUT_2.split("\n")).toList();

        int sum2 = 0;
        for (String line : lines) {
            // System.out.println(line);
            int lowIdx = -1, highIdx = -1;
            int lowDigit = 0, highDigit = 0;
            int i = 0;
            for (char ch : line.toCharArray()) {
                if (ch >= '0' && ch <= '9') {
                    if (lowIdx == -1) {
                        lowIdx = i;
                        lowDigit = ch - '0';
                    }
                    highIdx = i;
                    highDigit = ch - '0';
                }
                i++;
            }

            i = 1;
            for (String no : NOS) {
                int firstIndex = line.indexOf(no);
                int lastIndex = line.lastIndexOf(no);

                if (lowIdx == -1 && firstIndex != -1) {
                    lowIdx = firstIndex;
                    lowDigit = i;
                } else if (firstIndex != -1 && firstIndex < lowIdx) {
                    lowIdx = firstIndex;
                    lowDigit = i;
                }

                if (highIdx == -1 && lastIndex != -1) {
                    highIdx = lastIndex;
                    highDigit = i;
                } else if (lastIndex != -1 && lastIndex > highIdx) {
                    highIdx = lastIndex;
                    highDigit = i;
                }
                i++;
            }

            int n = ((lowDigit)*10) + (highDigit);
            // System.out.println(n);
            sum2 += n;
        }
        System.out.println("Ans: " + sum2); // 52834
    }
}
