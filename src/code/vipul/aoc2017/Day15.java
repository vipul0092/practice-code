package code.vipul.aoc2017;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static code.vipul.aoc2017.inputs.Inputs.DAY_15;

/**
 * Created by vgaur created on 28/12/23
 */
public class Day15 {

    private static final long MULTIPLIER_A = 16807L, MULTIPLIER_B = 48271L;
    private static final long MASK_16_BITS = 0xFFFFL;
    private static final long MOD = 2147483647;
    private static String INPUT = """
            Generator A starts with 65
            Generator B starts with 8921
            """;

    public static void solve() {
        INPUT = DAY_15;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        long ca = Long.parseLong(lines.get(0).split("with ")[1]);
        long cb = Long.parseLong(lines.get(1).split("with ")[1]);

        long TOTAL = 40_000_000;
        int part1 = getCount(ca, cb, TOTAL, Day15::generate, Day15::generate);
        System.out.println("Part 1: " + part1); // 597

        long PAIRS = 5_000_000;
        int part2 = getCount(ca, cb, PAIRS,
                (s, m) -> generate(s, m, 4), (s, m) -> generate(s, m, 8));
        System.out.println("Part 2: " + part2); // 303
    }

    private static long generate(long current, long multiplier) {
        return (current * multiplier) % MOD;
    }

    private static long generate(long current, long multiplier, int mod) {
        do {
            current = generate(current, multiplier);
        } while (current % mod != 0);
        return current;
    }

    private static int getCount(long starta, long startb, long limit,
                                BiFunction<Long, Long, Long> generatorA,
                                BiFunction<Long, Long, Long> generatorB) {
        int pairs = 0, count = 0;
        long ca = starta, cb = startb;
        do {
            ca = generatorA.apply(ca, MULTIPLIER_A);
            cb = generatorB.apply(cb, MULTIPLIER_B);
            pairs++;

            long mask = MASK_16_BITS;
            long ra = mask & ca;
            long rb = mask & cb;

            if (ra == rb) count++;
//            if (pairs % 1_000_000 == 0) {
//                System.out.println("Processed " + pairs + " pairs");
//            }
        } while (pairs != limit);
        return count;
    }
}
