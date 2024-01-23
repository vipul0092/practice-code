package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_20;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day20 {

    private static String INPUT = """
            
            """;

    public static void solve() {
        INPUT = DAY_20;
        int limit = Integer.parseInt(Arrays.stream(INPUT.split("\n")).toList().get(0));

        // Sending an arbitrarily high number for divisor limit in p1
        System.out.println("Part 1: " + getAnswer(10, limit, Integer.MAX_VALUE)); // 831600
        System.out.println("Part 1: " + getAnswer(11, limit, 50)); // 884520
    }

    private static int getAnswer(int multiplier, int limit, int divisorLimit) {
        int answer = -1;
        for (int i = 2500; ; i++) {
            if (divisorSum(i, divisorLimit) * multiplier >= limit) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    private static int divisorSum(int n, int divisorLimit) {
        int until = (int) Math.sqrt(n);
        int sum = n + ((n > divisorLimit) ? 0 : 1);
        for (int i = 2; i <= until+1; i++) {
            if (n%i == 0) {
                int d2 = (n/i);
                if (d2 > divisorLimit && i > divisorLimit) continue;
                if (d2 > divisorLimit && i < divisorLimit) {
                    sum += d2;
                } else {
                    sum += (d2 != i ? (i + d2) : i);
                }
            }
        }
        return sum;
    }
}
