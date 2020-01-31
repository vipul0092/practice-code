package code.vipul.aoc2018;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://adventofcode.com/2018/day/12
 */
public class Solve12 {
    static String input =
            "initial state: ##..##..#.##.###....###.###.#.#.######.#.#.#.#.##.###.####..#.###...#######.####.##...#######.##..#\n" +
                    "##... => .\n" +
                    "....# => .\n" +
                    "#.##. => .\n" +
                    "..... => .\n" +
                    "..### => .\n" +
                    "###.. => .\n" +
                    "#..#. => #\n" +
                    "##.## => .\n" +
                    "...## => #\n" +
                    "#..## => .\n" +
                    "#.### => .\n" +
                    "#.#.# => #\n" +
                    "####. => .\n" +
                    ".###. => #\n" +
                    ".##.# => .\n" +
                    "##.#. => #\n" +
                    "...#. => .\n" +
                    ".#.#. => .\n" +
                    "#...# => #\n" +
                    "##### => #\n" +
                    "..#.. => .\n" +
                    "..#.# => #\n" +
                    "..##. => .\n" +
                    "###.# => #\n" +
                    ".#### => #\n" +
                    "#.... => .\n" +
                    ".#..# => #\n" +
                    ".##.. => #\n" +
                    "#.#.. => #\n" +
                    "##..# => .\n" +
                    ".#... => #\n" +
                    ".#.## => #";

    private static Map<Integer, Integer> bitmapConverter;
    private static TreeSet<Integer> pots;

    public static void solve() {
        int generations = 20;
        bitmapConverter = new HashMap<>();
        pots = new TreeSet<>();
        parseInputAndInitialize();

        while (generations-- > 0) {
            int start = pots.first() - 2;
            int end = pots.last() + 2;
            TreeSet<Integer> tmpPots = new TreeSet<>();

            for (int i = start; i < end; i++) {
                int result = bitmapConverter.get(getBitmap(i));
                if (result == 1) {
                    tmpPots.add(i);
                }
            }
            pots = tmpPots;
        }

        AtomicInteger sum = new AtomicInteger(0);
        pots.forEach(n -> sum.addAndGet(n));

        System.out.println("Answer: " + sum.intValue()); // 1816
    }

    public static void solvePart2() {
        bitmapConverter = new HashMap<>();
        pots = new TreeSet<>();
        parseInputAndInitialize();

        int prevDiff = -1;
        int prevSum = 0;
        int currentSum = 0;
        int cnt = 0;

        do {
            cnt++;
            int start = pots.first() - 2;
            int end = pots.last() + 2;
            TreeSet<Integer> tmpPots = new TreeSet<>();

            for (int i = start; i < end; i++) {
                int result = bitmapConverter.get(getBitmap(i));
                if (result == 1) {
                    tmpPots.add(i);
                }
            }
            pots = tmpPots;
            AtomicInteger s = new AtomicInteger(0);
            pots.forEach(n -> s.addAndGet(n));
            currentSum = s.intValue();

            int diff = currentSum - prevSum;
            System.out.println(String.format("Generation: %s, Sum: %s, Diff: %s", cnt, currentSum, diff));
            if (prevDiff == diff) {
                break;
            }
            prevDiff = diff;
            prevSum = currentSum;
        } while (cnt != 1000);

        long total = (currentSum) + ((50000000000L - cnt) * prevDiff);
        System.out.println("Answer: " + total); // 399999999957
    }

    private static void parseInputAndInitialize() {
        String[] inputLines = input.split("\n");
        String initialState = inputLines[0].split(" ")[2].trim();

        for (int i = 1; i < inputLines.length; i++) {
            String[] parts = inputLines[i].split("=>");
            int bitmap = 0;
            int result = getCharBit(parts[1].trim().charAt(0));
            String pattern = parts[0].trim();
            for (int j = 0; j < pattern.length(); j++) {
                if (getCharBit(pattern.charAt(j)) == 1) {
                    bitmap |= (1 << j);
                }
            }
            bitmapConverter.put(bitmap, result);
        }

        for (int i = 0; i < initialState.length(); i++) {
            if (initialState.charAt(i) == '#') {
                pots.add(i);
            }
        }
    }

    private static int getCharBit(char ch) {
        return ch == '#' ? 1 : 0;
    }

    private static int getBitmap(int index) {
        return (pots.contains(index - 2) ? 1 : 0)
                + (pots.contains(index - 1) ? 2 : 0)
                + (pots.contains(index) ? 4 : 0)
                + (pots.contains(index + 1) ? 8 : 0)
                + (pots.contains(index + 2) ? 16 : 0);
    }
}
