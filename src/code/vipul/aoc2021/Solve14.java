package code.vipul.aoc2021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 31/12/22
 * https://adventofcode.com/2021/day/14
 */
public class Solve14 {

    private static final String INPUT = "NNCB\n" +
            "\n" +
            "CH -> B\n" +
            "HH -> N\n" +
            "CB -> H\n" +
            "NH -> C\n" +
            "HB -> C\n" +
            "HC -> B\n" +
            "HN -> C\n" +
            "NN -> C\n" +
            "BH -> H\n" +
            "NC -> B\n" +
            "NB -> B\n" +
            "BN -> B\n" +
            "BB -> N\n" +
            "BC -> B\n" +
            "CC -> N\n" +
            "CN -> C";

    public static void solve() {
        System.out.println(solveInternal(Inputs.INPUT_14, 10)); //3411
        System.out.println(solveInternal(Inputs.INPUT_14, 40)); //7477815755570
    }

    private static long solveInternal(String input, int maxSteps) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        Map<String, Character> rules = new HashMap<>();
        Map<String, Long> pairs = new HashMap<>();

        String polymerStart = inputs.get(0);
        for (int i = 1; i < polymerStart.length(); i++) {
            String pair = String.valueOf(polymerStart.charAt(i - 1)) + polymerStart.charAt(i);
            pairs.putIfAbsent(pair, 0L);
            pairs.put(pair, pairs.get(pair) + 1);
        }

        for (int i = 2; i < inputs.size(); i++) {
            String[] parts = inputs.get(i).split(" -> ");
            rules.put(parts[0], parts[1].charAt(0));
        }

        int steps = 0;
        while (steps++ < maxSteps) {
            Map<String, Long> newPairs = new HashMap<>();
            for (var entry : pairs.entrySet()) {
                long count = entry.getValue();
                String pair = entry.getKey();

                Character toInsert = rules.get(pair);
                if (toInsert != null) {
                    String pair1 = String.valueOf(pair.charAt(0)) + toInsert;
                    String pair2 = toInsert + String.valueOf(pair.charAt(1));
                    newPairs.putIfAbsent(pair1, 0L);
                    newPairs.putIfAbsent(pair2, 0L);

                    newPairs.put(pair1, newPairs.get(pair1) + count);
                    newPairs.put(pair2, newPairs.get(pair2) + count);
                } else {
                    newPairs.put(pair, count);
                }
            }
            pairs = newPairs;
        }

        Map<Character, Long> frequency = new HashMap<>();
        for (var entry : pairs.entrySet()) {
            long count = entry.getValue();
            char ch1 = entry.getKey().charAt(0);
            char ch2 = entry.getKey().charAt(1);

            frequency.putIfAbsent(ch1, 0L);
            frequency.putIfAbsent(ch2, 0L);

            frequency.put(ch1, frequency.get(ch1) + count);
            frequency.put(ch2, frequency.get(ch2) + count);
        }

        List<Long> frequencies = frequency.values().stream()
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        long highestFreq = (frequencies.get(0) + 1) / 2;
        long lowestFreq = (frequencies.get(frequencies.size() - 1) + 1) / 2;
        return highestFreq - lowestFreq;
    }
}
