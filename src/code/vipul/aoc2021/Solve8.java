package code.vipul.aoc2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 29/12/22
 * https://adventofcode.com/2021/day/8
 */
public class Solve8 {

    private static final String INPUT =
            "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe\n" +
                    "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc\n" +
                    "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg\n" +
                    "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb\n" +
                    "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea\n" +
                    "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb\n" +
                    "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe\n" +
                    "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef\n" +
                    "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb\n" +
                    "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce";

    private static final Map<Integer, Character> CHARACTER_MAPPING;
    private static final Map<Set<Integer>, Integer> SEGMENTS_TO_DIGIT_MAPPING;

    static {
        CHARACTER_MAPPING = new LinkedHashMap<>();
        for (char c = 'a'; c <= 'h'; c++) {
            CHARACTER_MAPPING.put(1 << (c - 'a'), c);
        }
        SEGMENTS_TO_DIGIT_MAPPING = new HashMap<>();
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(0, 1, 2, 4, 5, 6), 0);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(2, 5), 1);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(0, 2, 3, 4, 6), 2);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(0, 2, 3, 5, 6), 3);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(1, 2, 3, 5), 4);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(0, 1, 3, 5, 6), 5);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(0, 1, 3, 4, 5, 6), 6);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(0, 2, 5), 7);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(0, 1, 2, 3, 4, 5, 6), 8);
        SEGMENTS_TO_DIGIT_MAPPING.put(Set.of(0, 1, 2, 3, 5, 6), 9);
    }

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_8.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int count = 0;
        for (String in : inputs) {
            String[] digits = in.split(" \\| ")[1].split(" ");

            for (String digit : digits) {
                var chars = new HashSet<>();
                for (char ch : digit.toCharArray()) {
                    chars.add(ch);
                }

                int size = chars.size();
                if (size == 2 || size == 4 || size == 3 || size == 7) {
                    count++;
                }
            }
        }
        System.out.println(count); //397
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_8.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int count = 0;
        for (String in : inputs) {
            Map<Character, Integer> mapping = new HashMap<>();
            Map<Integer, Integer> allDigits = Arrays.stream(in.split(" \\| ")[0].split(" "))
                    .collect(Collectors.toMap(s -> getInt(s), s -> countSetBits(getInt(s))));
            List<String> digitsToConsider = Arrays.stream(in.split(" \\| ")[1].split(" "))
                    .collect(Collectors.toList());

            // The segments have been modelled like this
            //   0
            // 1  2
            //   3
            // 4  5
            //   6
            // So a 2_5 -> means the digit one being lit up
            // a 0_2_5 -> means the digit 7 being lit up etc.

            int two_five = getBitsSetCombination(allDigits, 2);
            int zero_two_five = getBitsSetCombination(allDigits, 3);

            int zero_ = zero_two_five - two_five;
            mapping.put(CHARACTER_MAPPING.get(zero_), 0);

            int one_two_three_five = getBitsSetCombination(allDigits, 4);
            int one_three = one_two_three_five - two_five;

            List<Integer> fiveLengths = allDigits.entrySet().stream()
                    .filter(e -> e.getValue() == 5)
                    .map(e -> e.getKey()).collect(Collectors.toList());

            int zero_three_six = fiveLengths.get(0) & fiveLengths.get(1) & fiveLengths.get(2);
            int three_six = zero_three_six - zero_;

            int three_ = one_three & three_six;
            mapping.put(CHARACTER_MAPPING.get(three_), 3);

            int six_ = three_six - three_;
            mapping.put(CHARACTER_MAPPING.get(six_), 6);

            int one_ = one_three - three_;
            mapping.put(CHARACTER_MAPPING.get(one_), 1);

            int zero_one_three_six = zero_ | one_ | three_ | six_;
            int five_ = fiveLengths.stream()
                    .filter(f -> CHARACTER_MAPPING.containsKey(f - zero_one_three_six))
                    .map(f -> f - zero_one_three_six).findFirst().orElseThrow();
            mapping.put(CHARACTER_MAPPING.get(five_), 5);

            int two_ = two_five - five_;
            mapping.put(CHARACTER_MAPPING.get(two_), 2);

            int eight = getBitsSetCombination(allDigits, 7);
            int four_ = eight - (zero_one_three_six | two_ | five_);
            mapping.put(CHARACTER_MAPPING.get(four_), 4);


            // Calculate the num from RHS
            int num = 1000 * getNum(digitsToConsider.get(0), mapping);
            num += (100 * getNum(digitsToConsider.get(1), mapping));
            num += (10 * getNum(digitsToConsider.get(2), mapping));
            num += getNum(digitsToConsider.get(3), mapping);

            count += num;
        }
        System.out.println(count); //1027422
    }

    private static int getBitsSetCombination(Map<Integer, Integer> allDigits, int setBits) {
        return allDigits.entrySet().stream().filter(e -> e.getValue() == setBits).findFirst().orElseThrow().getKey();
    }

    private static int getInt(String segments) {
        int v = 0;
        for (char ch : segments.toCharArray()) {
            v |= (1 << (ch - 'a'));
        }
        return v;
    }

    private static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    private static int getNum(String signals, Map<Character, Integer> mapping) {
        Set<Integer> segments = new HashSet<>();
        for (char ch : signals.toCharArray()) {
            segments.add(mapping.get(ch));
        }
        return SEGMENTS_TO_DIGIT_MAPPING.get(segments);
    }
}
