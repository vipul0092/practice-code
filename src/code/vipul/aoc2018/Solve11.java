package code.vipul.aoc2018;

import code.vipul.tree.binaryindexedtree.BIT_2D_RangeQuery;

/**
 * https://adventofcode.com/2018/day/11
 */
public class Solve11 {

    private static final int GRID_SIZE = 300;
    private static final int SQ_SIZE = 3;

    private static final int GRID_SERIAL_NUMBER = 9445;

    public static void solve() {
        final int maxTopLeftLimit = GRID_SIZE - SQ_SIZE + 1;
        int x = 0, y = 0, maxValue = 0;

        BIT_2D_RangeQuery bit = BIT_2D_RangeQuery.of(GRID_SIZE, GRID_SIZE);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int powerLevel = getPowerLevel(j + 1, i + 1);
                bit.update(i + 1, j + 1, powerLevel);
            }
        }

        for (int i = 1; i <= maxTopLeftLimit; i++) {
            for (int j = 1; j <= maxTopLeftLimit; j++) {
                int value = bit.query(i, j, i + SQ_SIZE - 1, j + SQ_SIZE - 1);
                if (value > maxValue) {
                    maxValue = value;
                    x = j;
                    y = i;
                }
            }
        }
        System.out.println(String.format("Answer: %s,%s", x, y)); // 233,36
    }

    public static void solvePart2() {
        int x = 0, y = 0, sqSize = 0, maxValue = 0;

        BIT_2D_RangeQuery bit = BIT_2D_RangeQuery.of(GRID_SIZE, GRID_SIZE);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int powerLevel = getPowerLevel(j + 1, i + 1);
                bit.update(i + 1, j + 1, powerLevel);
            }
        }

        for (int k = 1; k <= GRID_SIZE; k++) {
            int maxTopLeftLimit = GRID_SIZE - k + 1;
            for (int i = 1; i <= maxTopLeftLimit; i++) {
                for (int j = 1; j <= maxTopLeftLimit; j++) {
                    int value = bit.query(i, j, i + k - 1, j + k - 1);
                    if (value > maxValue) {
                        maxValue = value;
                        x = j;
                        y = i;
                        sqSize = k;
                    }
                }
            }
        }
        System.out.println(String.format("Answer: %s,%s,%s", x, y, sqSize)); // 231,107,14
    }

    private static int getPowerLevel(int x, int y) {
        int rackId = x + 10;
        int powerLevel = ((y * rackId) + GRID_SERIAL_NUMBER) * rackId;

        int hundredsDigit = powerLevel < 100 ? 0 : (powerLevel / 100) % 10;
        return hundredsDigit - 5;
    }
}
