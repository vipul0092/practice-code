package code.vipul.aoc2020;

import java.util.TreeSet;

import static code.vipul.aoc2020.Inputs.INPUT_5;

/**
 * https://adventofcode.com/2020/day/5
 */
public class Solve5 {

    public static void solve() {
        int max = 0;
        var codes = INPUT_5.split("\n");

        for (String code : codes) {
            max = Math.max(max, getSeatId(code));
        }

        System.out.println("Answer: " + max); //888
    }

    public static void solvePart2() {
        int mySeatId = 0;
        var codes = INPUT_5.split("\n");
        TreeSet<Integer> seatIds = new TreeSet<>();

        for (String code : codes) {
            seatIds.add(getSeatId(code));
        }

        int prevSeatId = -1;
        for (Integer seatId: seatIds) {
            if (prevSeatId != -1 && prevSeatId != seatId - 1) {
                mySeatId = seatId - 1;
                break;
            }
            prevSeatId = seatId;
        }

        System.out.println("Answer: " + mySeatId); //522
    }

    private static int getSeatId(String code) {
        int row = 0;
        int column = 0;

        char[] codeChars = code.toCharArray();
        int powerOf2 = 1;
        for (int i = 0; i < 7; i++) {
            if (codeChars[6 - i] == 'B') {
                row += powerOf2;
            }
            powerOf2 *= 2;
        }

        powerOf2 = 1;
        for (int i = 0; i < 3; i++) {
            if (codeChars[9 - i] == 'R') {
                column += powerOf2;
            }
            powerOf2 *= 2;
        }

        return (row * 8) + column;
    }
}
