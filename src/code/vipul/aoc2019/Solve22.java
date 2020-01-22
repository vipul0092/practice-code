package code.vipul.aoc2019;

import code.vipul.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * https://adventofcode.com/2019/day/22
 */
public class Solve22 {

    private static long MAX_POS = 10006;
    private static long TOTAL = MAX_POS + 1;

    static String input = "cut -9531\n" +
            "deal into new stack\n" +
            "deal with increment 38\n" +
            "cut -8776\n" +
            "deal with increment 18\n" +
            "cut 1410\n" +
            "deal with increment 72\n" +
            "deal into new stack\n" +
            "deal with increment 22\n" +
            "cut -8727\n" +
            "deal into new stack\n" +
            "deal with increment 41\n" +
            "deal into new stack\n" +
            "cut 1001\n" +
            "deal with increment 73\n" +
            "cut -9231\n" +
            "deal with increment 16\n" +
            "cut 8964\n" +
            "deal with increment 12\n" +
            "deal into new stack\n" +
            "deal with increment 28\n" +
            "cut 3730\n" +
            "deal with increment 39\n" +
            "deal into new stack\n" +
            "deal with increment 13\n" +
            "cut 6377\n" +
            "deal with increment 40\n" +
            "cut 8162\n" +
            "deal with increment 68\n" +
            "deal into new stack\n" +
            "cut 7163\n" +
            "deal with increment 56\n" +
            "cut -6800\n" +
            "deal with increment 66\n" +
            "deal into new stack\n" +
            "deal with increment 72\n" +
            "cut 3762\n" +
            "deal with increment 14\n" +
            "deal into new stack\n" +
            "deal with increment 48\n" +
            "cut 7812\n" +
            "deal with increment 29\n" +
            "cut -9781\n" +
            "deal with increment 22\n" +
            "cut -5551\n" +
            "deal into new stack\n" +
            "deal with increment 64\n" +
            "cut -8973\n" +
            "deal with increment 55\n" +
            "cut 8183\n" +
            "deal with increment 36\n" +
            "cut -1453\n" +
            "deal with increment 42\n" +
            "cut -5588\n" +
            "deal with increment 48\n" +
            "deal into new stack\n" +
            "cut -1212\n" +
            "deal with increment 5\n" +
            "cut -1798\n" +
            "deal into new stack\n" +
            "cut 6711\n" +
            "deal with increment 23\n" +
            "cut 4919\n" +
            "deal into new stack\n" +
            "deal with increment 32\n" +
            "cut -1690\n" +
            "deal with increment 51\n" +
            "cut 2250\n" +
            "deal into new stack\n" +
            "deal with increment 60\n" +
            "cut -5228\n" +
            "deal with increment 66\n" +
            "cut 2546\n" +
            "deal with increment 27\n" +
            "deal into new stack\n" +
            "cut 4013\n" +
            "deal into new stack\n" +
            "cut -2059\n" +
            "deal with increment 11\n" +
            "cut 7778\n" +
            "deal with increment 46\n" +
            "deal into new stack\n" +
            "cut 5107\n" +
            "deal with increment 10\n" +
            "cut 8038\n" +
            "deal with increment 71\n" +
            "cut 5993\n" +
            "deal with increment 26\n" +
            "cut -3533\n" +
            "deal with increment 53\n" +
            "cut 7258\n" +
            "deal into new stack\n" +
            "cut -8491\n" +
            "deal with increment 60\n" +
            "deal into new stack\n" +
            "deal with increment 36\n" +
            "deal into new stack\n" +
            "cut 6760\n" +
            "deal with increment 51\n" +
            "cut -302";

    public static void solvePart1() {

        String[] steps = input.split("\n");

        long lastStepIndex = 2019;

        for (int i = 0; i < steps.length; i++) {
            DealType type = getDealType(steps[i]);

            if (type == DealType.CUT) {
                long argument = getArgument(type, steps[i]);
                argument = argument < 0 ? TOTAL + argument : argument;

                if (lastStepIndex >= argument) {
                    lastStepIndex -= argument;
                } else {
                    lastStepIndex += (TOTAL - argument);
                }
            } else if (type == DealType.NEW_STACK) {
                lastStepIndex = MAX_POS - lastStepIndex;
            } else { // increment
                int argument = getArgument(type, steps[i]);
                lastStepIndex = mulmod(lastStepIndex, argument, TOTAL);
            }
        }
        System.out.println("Answer: " + lastStepIndex); // 6638 (for initial value of lastStepIndex = 2019)
    }

    public static void solvePart1Composition() {
        String[] steps = input.split("\n");
        long lastStepIndex = 2019;

        Map<Integer, Pair<DealType, Long>> inputs = getInputMap(steps);
        Pair<Long, Long> ab = getComposedFunctionCoeff(inputs, steps);

        // Now the answer is a * lastStepIndex + b % M
        long answer = mulmod(ab.left(), lastStepIndex, TOTAL);
        answer = (answer + ab.right()) % TOTAL;

        System.out.println("Answer: " + answer); // 6638 (for initial value of lastStepIndex = 2019)
    }

    public static void solvePart2() {
        String[] steps = input.split("\n");

        MAX_POS = 119315717514047L - 1;
        TOTAL = MAX_POS + 1;
        final long applyTimes = 101741582076661L;
        final long lastStepIndex = 2020;

        Map<Integer, Pair<DealType, Long>> inputs = getInputMap(steps);
        Pair<Long, Long> ab = getComposedFunctionCoeff(inputs, steps);

        // Explanation here -> https://codeforces.com/blog/entry/72593

        // Now we have our composed function f(x) = ax + b, where the input has been applied once
        // We need to apply it to itself applyTimes times
        // i.e. we need fn(x) = f(f(f(f(.....(f(x)), f(x) applied to itself n times, n = applyTimes
        // Now, when we apply f to itself, we see that fk(x) = a^k + b(Summation(a^i)[i = 0 -> k-1])
        // fk(x) = a^k + b((a^k - 1) / a - 1), because that summation is a Geometric series in a
        // so fn(x) = a^n + b((a^n - 1) / a - 1) % M
        // Now, this boils down to a^n % M -> modular exponentiation
        // & b (a^n - 1/ a - 1) % M => b % M * (a^n - 1) % M * (a - 1)^M-2 % M
        // The last term comes from modular inverse of a - 1 % M which is (a - 1)^M-2 % M by Fermat's little theorem

        long a_n_mod_m = power(ab.left(), applyTimes, TOTAL); // a^n % M
        long a = a_n_mod_m;
        long b = ab.right() % TOTAL; // b = b % M
        b = mulmod(b, (a_n_mod_m - 1) % TOTAL, TOTAL); // b = b * (a^n - 1) % M
        b = mulmod(b, power(ab.left() - 1, TOTAL - 2, TOTAL), TOTAL); // b = b * (a - 1)^M-2 % M
        b = b % TOTAL;

        // Now we have our function after applying it to itself n times, F(x) = ax + b;
        // This gives us whats the final position of the card whose original position was x
        // But we want that for the card at final position x, whats its original position
        // For this we need to invert the F(x)
        // or x = a.Finv(x) + b => Finv(x) = x - b / a % M;
        // => (x-b) % M * a^M-2 % M , the second term again is due to Fermat's last theorem
        // and ofcourse x = 2020

        long answer = (lastStepIndex - b) % TOTAL; // answer = x - b % M
        answer = answer < 0 ? answer + TOTAL : answer;
        answer = mulmod(answer, power(a, TOTAL - 2, TOTAL), TOTAL); // answer = answer * a^M-2 % M
        answer = answer % TOTAL;

        System.out.println("Answer: " + answer); // 77863024474406
    }

    private static Map<Integer, Pair<DealType, Long>> getInputMap(String[] steps) {
        Map<Integer, Pair<DealType, Long>> inputs = new HashMap<>();
        for (int i = 0; i < steps.length; i++) {
            DealType type = getDealType(steps[i]);
            long argument = 0;
            if (type == DealType.CUT) {
                argument = getArgument(type, steps[i]);
            } else if (type == DealType.INCREMENT) {
                argument = getArgument(type, steps[i]);
            }
            inputs.put(i, Pair.of(type, argument));
        }
        return inputs;
    }

    private static Pair<Long, Long> getComposedFunctionCoeff(Map<Integer, Pair<DealType, Long>> inputs,
                                                             String[] steps) {
        var input1 = inputs.get(0);
        var ab1 = input1.left().getab(input1.right(), TOTAL);

        long a = ab1.left();
        long b = ab1.right();

        // Compose into a single Linear transformation function f(x) = ax + b
        // the a & b variables above are the corresponding coefficients
        for (int i = 1; i < steps.length; i++) {
            var input = inputs.get(i);
            var ab = input.left().getab(input.right(), TOTAL);

            // a1.x + b1 * a2.x + b2 = (a1.a2) x + (b1.a2 + b2)
            a = mulmod(a, ab.left(), TOTAL); // a = a1 . a2 % M
            // b = b1 . a2 + b2 % M
            b = mulmod(b, ab.left(), TOTAL); // b1 . a2 % M
            b = (b + ab.right()) % TOTAL; // above + b2 % M
        }
        return Pair.of(a, b);
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

    private static DealType getDealType(String step) {
        if (step.startsWith("cut")) {
            return DealType.CUT;
        } else if (step.startsWith("deal") && step.endsWith("stack")) {
            return DealType.NEW_STACK;
        } else {
            return DealType.INCREMENT;
        }
    }

    private static int getArgument(DealType type, String step) {
        switch (type) {
            case INCREMENT:
            case CUT:
                return Integer.parseInt(step.substring(step.lastIndexOf(' ') + 1));
            default:
                return -1; // Do nothing
        }
    }

    private enum DealType {
        CUT,
        NEW_STACK,
        INCREMENT;

        public Pair<Long, Long> getab(Long argument, Long total) {
            switch (this) {
                case INCREMENT: // f(x) = n.x + 0
                    return Pair.of(argument % total, 0L);
                case CUT: // f(x) = 1.x + (-n)
                    long mod = (-argument % total);
                    return Pair.of(1L, mod < 0 ? mod + total : mod);
                case NEW_STACK: // f(x) = -1.x + (m - 1)
                    mod = -1 % total;
                    mod = mod < 0 ? mod + total : mod;
                    return Pair.of(mod, total - 1);
                default:
                    return null;
            }
        }
    }
}
