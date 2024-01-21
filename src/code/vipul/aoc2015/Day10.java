package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_10;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day10 {

    private static String INPUT = "1";

    record Pair(int count, int num){}

    public static void solve() {
        INPUT = DAY_10;
        String start = Arrays.stream(INPUT.split("\n")).toList().get(0);

        System.out.println("Part 1: " + getLengthAfterRounds(start, 40)); // 329356
        System.out.println("Part 2: " + getLengthAfterRounds(start, 50)); // 4666278
    }

    private static int getLengthAfterRounds(String start, int times) {
        List<Pair> counts = new ArrayList<>();
        int num = -1, count = 0;
        for (char ch : start.toCharArray()) {
            int digit = ch - '0';
            if (num == -1) {
                num = digit;
                count = 1;
            } else if (num == digit) {
                count++;
            } else {
                counts.add(new Pair(count, num));
                num = digit;
                count = 1;
            }
        }
        counts.add(new Pair(count, num));
        times--; // One iteration is this^


        while (times-- > 0) {
            List<Pair> newCounts = new ArrayList<>();
            num = -1; count = 0;
            for (var pair : counts) {
                int n1 = pair.count, n2 = pair.num;
                if (num == -1) {
                    num = n1;
                    count = 1;
                } else if (num == n1) {
                    count++;
                } else {
                    newCounts.add(new Pair(count, num));
                    num = n1;
                    count = 1;
                }

                if (num == n2) {
                    count++;
                } else {
                    newCounts.add(new Pair(count, num));
                    num = n2;
                    count = 1;
                }
            }
            newCounts.add(new Pair(count, num));
            counts = newCounts;
        }
        return counts.size() * 2; // * 2 because one is the number, other is the count
    }
}
