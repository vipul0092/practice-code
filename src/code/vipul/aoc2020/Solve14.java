package code.vipul.aoc2020;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static code.vipul.aoc2020.Inputs2.INPUT_14;

/**
 * https://adventofcode.com/2020/day/14
 */
public class Solve14 {

    private static final String INPUT = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
            "mem[8] = 11\n" +
            "mem[7] = 101\n" +
            "mem[8] = 0";

    private static final String INPUT_2 = "mask = 000000000000000000000000000000X1001X\n" +
            "mem[42] = 100\n" +
            "mask = 00000000000000000000000000000000X0XX\n" +
            "mem[26] = 1";

    public static void solve() {
        String[] lines = INPUT_14.split("\n");

        Map<Long, Long> memory = new LinkedHashMap<>();

        long zeroMask = 0;
        long oneMask = 0;

        for (String line : lines) {
            if (line.startsWith("mask")) {
                String mask = line.split(" = ")[1];
                zeroMask = getMask(mask, 0);
                oneMask = getMask(mask, 1);
            } else {
                String[] parts = line.split(" = ");
                long number = Long.parseLong(parts[1]);
                long memoryLocation = Long.parseLong(parts[0].substring(4, parts[0].length() - 1));

                memory.put(memoryLocation, transform(number, zeroMask, oneMask));
            }
        }

        long sum = memory.values().stream()
                .filter(val -> val != 0)
                .reduce(0L, Long::sum);

        System.out.println("Answer: " + sum); //14925946402938
    }

    public static void solvePart2() {
        String[] lines = INPUT_14.split("\n");

        Map<Long, Long> memory = new LinkedHashMap<>();

        String currentMask = null;
        for (String line : lines) {
            if (line.startsWith("mask")) {
                currentMask = line.split(" = ")[1];
            } else {
                String[] parts = line.split(" = ");
                long number = Long.parseLong(parts[1]);
                long memoryLocation = Long.parseLong(parts[0].substring(4, parts[0].length() - 1));

                List<Long> perms = getMemoryLocationPermutations(currentMask, memoryLocation);

                for (Long location : perms) {
                    memory.put(location, number);
                }
            }
        }

        long sum = memory.values().stream()
                .filter(val -> val != 0)
                .reduce(0L, Long::sum);

        System.out.println("Answer: " + sum);
    }

    private static long transform(long number, long zeroMask, long oneMask) {
        return (number | zeroMask) & oneMask;
    }

    // private static int mostx = 0;
    private static long getMask(String mask, int xbit) {
        mask = reverse(mask);
        long m = 0;
        // int x = 0;
        for (int i = 0; i < mask.length(); i++) {
            int num = mask.charAt(i) == '0'
                    ? 0
                    : (mask.charAt(i) == '1' ? 1 : xbit);
            // if (mask.charAt(i) == 'X') x++;
            if (num != 0) {
                long pow = 1L << ((long) i);
                m |= pow;
            }
        }

        // mostx = Math.max(mostx, x);
        return m;
    }

    private static List<Long> getMemoryLocationPermutations(String mask, long number) {
        mask = reverse(mask);
        List<Long> permutations = new ArrayList<>();
        permutations.add(0L);

        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == 'X') {
                List<Long> newPerms = new ArrayList<>(permutations);
                for (Long perm : permutations) {
                    long pow = 1L << ((long) i);
                    newPerms.add(perm | pow);
                }
                permutations = newPerms;
            } else {
                int num = mask.charAt(i) == '0'
                        ? ((number & (1L << ((long) i))) != 0 ? 1 : 0)
                        : 1;
                if (num != 0) {
                    List<Long> newPerms = new ArrayList<>();
                    for (Long perm : permutations) {
                        long pow = 1L << ((long) i);
                        newPerms.add(perm | pow);
                    }
                    permutations = newPerms;
                }
            }
        }
        return permutations;
    }

    private static String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return sb.toString();
    }
}
