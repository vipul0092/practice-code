package code.vipul.aoc2019;

import java.util.Arrays;

/**
 * https://adventofcode.com/2019/day/4
 */
public class Solve4 {

    public static void solve() {
        int start = 246540;
        int end = 787419;

        int num = start;
        int count = 0;
        while (num < end) {
            if (verify(num, false)) {
                count++;
                System.out.println("Found valid number:" + num);
            }
            num++;
        }
        System.out.println("Total count: " + count); // 1063
    }

    public static void solvePart2() {
        int start = 246540;
        int end = 787419;

        int num = start;
        int count = 0;
        while (num < end) {
            if (verify(num, true)) {
                count++;
                System.out.println("Found valid number:" + num);
            }
            num++;
        }
        System.out.println("Total count: " + count); // 686
    }

    private static boolean verify(int num, boolean twoDigitRepeatOnly) {
        int temp = num;
        int[] digits = new int[6]; // 6 digit numbers
        int ctr = 0;
        while (temp > 0) {
            digits[ctr++] = temp % 10;
            temp /= 10;
        }

        boolean valid = true;
        // Check that the digit order is correct
        for (int i = 1; i < 6; i++) {
            if (digits[i] > digits[i - 1]) {
                valid = false;
                break;
            }
        }
        if (!valid) return false;

        int[] count = new int[10];
        for (int i = 0; i < 6; i++) {
            count[digits[i]]++;
        }

        return Arrays.stream(count).anyMatch(n -> twoDigitRepeatOnly ? n == 2 : n >= 2);
    }
}
