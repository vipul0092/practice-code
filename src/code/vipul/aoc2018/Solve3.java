package code.vipul.aoc2018;

import code.vipul.tree.binaryindexedtree.BIT_1D_PointQuery;
import code.vipul.tree.binaryindexedtree.BIT_1D_RangeQuery;

/**
 * https://adventofcode.com/2018/day/3
 */
public class Solve3 {

    private static String input = Inputs.DAY3;

    public static void solve() {
        String[] selections = input.split("\n");
        BIT_1D_PointQuery[] bits = new BIT_1D_PointQuery[1000];

        for (String selection : selections) {
            String part2 = selection.split("@")[1].trim();
            String[] inputsPart = part2.split(":");

            String[] startingXY = inputsPart[0].split(",");
            String[] widthHeight = inputsPart[1].split("x");

            int startX = Integer.parseInt(startingXY[1].trim());
            int startY = Integer.parseInt(startingXY[0].trim());
            int width = Integer.parseInt(widthHeight[0].trim());
            int height = Integer.parseInt(widthHeight[1].trim());

            for (int i = 0; i < height; i++) {
                if (bits[startX + i] == null) {
                    bits[startX + i] = BIT_1D_PointQuery.ofSize(1000);
                }
                bits[startX + i].range_add(startY + 1, startY + width, 1);
            }
        }

        int answer = 0;
        for (int j = 0; j<1000; j++) {
            for (int i = 1; i < 1000; i++) {
                int val = bits[j].point_query(i);
                if (val >= 2) {
                    answer++;
                }
            }
        }

        System.out.println("Answer: " + answer); // 105071
    }

    public static void solvePart2() {
        String[] selections = input.split("\n");
        BIT_1D_RangeQuery[] bits = new BIT_1D_RangeQuery[1000];

        for (String selection : selections) {
            String part2 = selection.split("@")[1].trim();
            String[] inputsPart = part2.split(":");

            String[] startingXY = inputsPart[0].split(",");
            String[] widthHeight = inputsPart[1].split("x");

            int startX = Integer.parseInt(startingXY[1].trim());
            int startY = Integer.parseInt(startingXY[0].trim());
            int width = Integer.parseInt(widthHeight[0].trim());
            int height = Integer.parseInt(widthHeight[1].trim());

            for (int i = 0; i < height; i++) {
                if (bits[startX + i] == null) {
                    bits[startX + i] = BIT_1D_RangeQuery.ofSize(1000);
                }
                bits[startX + i].range_add(startY + 1, startY + width, 1);
            }
        }

        int answer = -1;
        for (String selection : selections) {
            String part2 = selection.split("@")[1].trim();
            Integer number = Integer.parseInt(selection.split("@")[0].trim().substring(1));
            String[] inputsPart = part2.split(":");

            String[] startingXY = inputsPart[0].split(",");
            String[] widthHeight = inputsPart[1].split("x");

            int startX = Integer.parseInt(startingXY[1].trim());
            int startY = Integer.parseInt(startingXY[0].trim());
            int width = Integer.parseInt(widthHeight[0].trim());
            int height = Integer.parseInt(widthHeight[1].trim());

            final int expectedSum = width * height; // all 1s within the rectangle
            int sum = 0;
            for (int i = 0; i < height; i++) {
                sum += bits[startX + i].range_sum(startY + 1, startY + width);
            }

            if (sum == expectedSum) {
                answer = number;
                break;
            }
        }

        System.out.println("Answer: " + answer); // 222
    }
}
