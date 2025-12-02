package code.vipul.aoc2025;

import java.util.*;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2025/day/2
 */
public class Day2 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day2.class, false);

        String[] parts = lines.getFirst().split(",");
        long sum2 = 0;
        long sum1 = 0;

        for (String part : parts) {
            String[] p = part.split("-");

            sum2 += execute(p[0], p[1], null);
            sum1 += execute(p[0], p[1], 2);
        }

        System.out.println("Part 1: " + sum1); // 30608905813
        System.out.println("Part 2: " + sum2); // 31898925685
    }

    private static long execute(String n1, String n2, Integer maxAppends) {
        long from = Long.parseLong(n1), to = Long.parseLong(n2);
        Set<Long> found = new HashSet<>();
        long sum = 0;
        for (int i = 1; i <= 9; i++) {
            for (int digitCount = 1; digitCount <= Math.max(n1.length()/2, n2.length()/2); digitCount++) {
                // 1 - 9, 10 -> 99, 100 -> 999 etc
                long start = (long)Math.pow(10, digitCount-1);
                long end = (start * 10) - 1;
                long power = (long) Math.pow(10, digitCount);

                for (long ss = start; ss <= end; ss++) {
                    long currentNum = ss * power + ss; // equivalent to String(currentNum) + String(currentNum)
                    int numberOfAppends = 2; // By default, we are appending twice

                    while(currentNum <= to) {
                        if (maxAppends != null && numberOfAppends > maxAppends) break;
                        if (currentNum >= from && !found.contains(currentNum)) {
                            // System.out.println("Found: " + currentNum);
                            sum += currentNum;
                            found.add(currentNum);
                        }
                        currentNum = currentNum * power + ss;
                        numberOfAppends++;
                    }
                }
            }
        }
        return sum;
    }
}
