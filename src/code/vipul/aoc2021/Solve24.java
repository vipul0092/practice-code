package code.vipul.aoc2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static code.vipul.aoc2021.Inputs.INPUT_24;
import static code.vipul.aoc2021.Inputs.INPUT_24_2;
import static code.vipul.aoc2021.Inputs.INPUT_24_3;

/**
 * Created by vgaur created on 11/01/23
 * https://adventofcode.com/2021/day/24
 */
public class Solve24 {

    private static List<Variables> perDigit;

    public static void solve() {
        parse(INPUT_24); // Answers: 92928914999991, 91811211611981
        //parse(INPUT_24_2); // Answers: 69914999975369, 14911675311114
        //parse(INPUT_24_3); //Answers: 29989297949519, 19518121316118

        System.out.println("Part 1: " +
                evaluateFromEnd(perDigit.size() - 1, Map.of(0, 0L), 1, true));
        System.out.println("Part 2: " +
                evaluateFromEnd(perDigit.size() - 1, Map.of(0, 0L), 1, false));
    }

    private static long evaluateFromEnd(int digitNum, Map<Integer, Long> prevPossible, long multiplier, boolean max) {
        if (digitNum < 0) {
            return max
                    ? prevPossible.values().stream().mapToLong(l -> l).max().orElseThrow()
                    : prevPossible.values().stream().mapToLong(l -> l).min().orElseThrow();
        }
        Map<Integer, Long> optionsMap = new HashMap<>();
        Variables variables = perDigit.get(digitNum);
        for (var entry : prevPossible.entrySet()) {
            int currentReqd = entry.getKey();
            long number = entry.getValue();
            for (int digit = 1; digit <= 9; digit++) {
                int mod_26 = digit - variables.addx;
                // To_find / divz = currentReqd, if to_find % 26 = digit - addx
                // that means To_find has a range of [ currentReqd * divz, currentReqd * divz++ )

                Set<Integer> validOptions = new HashSet<>();
                int lowerLimit = currentReqd * variables.divz;
                int higherLimit = lowerLimit + variables.divz;
                // mod of anything has to be zero or higher
                if (mod_26 >= 0) {
                    for (int i = lowerLimit; i < higherLimit; i++) {
                        if (i % 26 == mod_26) {
                            validOptions.add(i);
                        }
                    }
                }

                // digit + addy + (26 * (To_find / divz)) = currentReqd, if to_find % 26 != digit - addx
                // => (26 * (To_find / divz)) = currentReqd - digit - addy
                // => 26 * To_find has a range of [ (currentReqd - digit - addy) * divz, currentReqd - digit - addy * divz++ )
                // => Only the values in the range which are multiples of 26 would be valid
                lowerLimit = (currentReqd - digit - variables.addy) * variables.divz;
                higherLimit = lowerLimit + variables.divz;
                // if the lower limit goes -ve, that means currentReqd - digit - addy is negative
                // which means there is no option possible from here
                if (lowerLimit >= 0) {
                    for (int i = lowerLimit; i < higherLimit; i++) {
                        if (i % 26 != mod_26 && i % 26 == 0) {
                            validOptions.add(i / 26);
                        }
                    }
                }

                long currentNum = (digit * multiplier) + number;
                for (var option : validOptions) {
                    if (optionsMap.containsKey(option)) {
                        if (max && optionsMap.get(option) < currentNum) {
                            optionsMap.put(option, currentNum);
                        } else if (!max && optionsMap.get(option) > currentNum) {
                            optionsMap.put(option, currentNum);
                        }
                    } else {
                        optionsMap.put(option, currentNum);
                    }
                }
            }
        }
        return evaluateFromEnd(digitNum - 1, optionsMap, multiplier * 10L, max);
    }

    private static void parse(String input) {
        String[] in = input.split("\n");
        perDigit = new ArrayList<>();
        Variables current = null;
        for (int i = 0; i < in.length; i++) {
            String line = in[i];
            if (line.equals("inp w")) {
                var newvar = new Variables();
                if (current != null) {
                    perDigit.add(current);
                }
                current = newvar;
            } else {
                if (line.startsWith("div z")) {
                    current.divz = Integer.parseInt(line.split("div z ")[1]);
                } else if (line.startsWith("add x") && !line.contains("z")) {
                    current.addx = Integer.parseInt(line.split("add x ")[1]);
                } else if (i != 0 && line.startsWith("add y") && in[i - 1].startsWith("add y w")) {
                    current.addy = Integer.parseInt(line.split("add y ")[1]);
                }
            }
        }
        if (current != null) {
            perDigit.add(current);
        }
    }

    private static final class Variables {
        private int addx;
        private int divz;
        private int addy;

        public Variables() {
        }
    }
}
