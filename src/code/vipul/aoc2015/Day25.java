package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_25;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day25 {

    private static String INPUT = """
            To continue, please consult the code grid in the manual.  Enter the code at row 6, column 1.
            """; // Answer is 33071741
    private static final long INITIAL = 20151125L;
    private static final long MUL = 252533L;
    private static final long MOD = 33554393L;

    public static void solve() {
        INPUT = DAY_25;
        String line = Arrays.stream(INPUT.split("\n")).toList().get(0);
        int r = Integer.parseInt(line.split("at row ")[1].split(", ")[0]);
        int c = Integer.parseInt(line.split("column ")[1].split("\\.")[0]);
        int row = 1, col = 1;
        int rows = 1;

        int diff = 0;
        do {
            if (row == 1) {
                rows++;
                row = rows;
                col = 1;
            } else {
                row--;
                col++;
            }
            diff++;
        } while (row != r || col != c);

        // Answer is initial * multiply ^ diff % mod
        System.out.println("Answer: " + mulmod(INITIAL, power(MUL, diff, MOD), MOD)); // 8997277
    }

    // To compute x^y under modulo m, Complexity = O(log(y))
    private static long power(long x, long y, long m) {
        if (y == 0)
            return 1;

        long p = power(x, y / 2, m) % m;
        p = mulmod(p, p, m);

        if (y % 2 == 0)
            return p;
        else
            return mulmod(x, p, m);
    }

    // To compute (a * b) % mod,  Complexity = O(log(b))
    private static long mulmod(long a, long b, long mod) {
        long res = 0; // Initialize result
        a = a % mod;
        while (b > 0) {
            // If b is odd, add 'a' to result
            if (b % 2 == 1) {
                res = (res + a) % mod;
            }

            // Multiply 'a' with 2
            a = (a * 2) % mod;

            // Divide b by 2
            b /= 2;
        }

        // Return result
        return res % mod;
    }
}
