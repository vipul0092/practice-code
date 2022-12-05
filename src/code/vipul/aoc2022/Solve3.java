package code.vipul.aoc2022;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static code.vipul.aoc2022.inputs.Inputs.INPUT_3;

/**
 * Created by vgaur created on 03/12/22
 * https://adventofcode.com/2022/day/3
 */
public class Solve3 {

    private static final String INPUT = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
            "PmmdzqPrVvPwwTWBwg\n" +
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
            "ttgJtRGJQctTZtZT\n" +
            "CrZsJsPPZsGzwwsLwLmpwMDw";

    public static void solve() {
        List<String> inputs = Arrays.stream(INPUT_3.split("\n")).collect(Collectors.toList());

        int prio = 0;
        for (String in : inputs) {
            int till = (in.length() / 2);
            String s1 = in.substring(0, till);
            String s2 = in.substring(till);

            Set<Character> inS1 = new HashSet<>();
            for (Character ch : s1.toCharArray()) {
                inS1.add(ch);
            }

            for (Character ch : s2.toCharArray()) {
                if (inS1.contains(ch)) {
                    prio += (getPrio(ch));
                    break;
                }
            }
        }

        System.out.println(prio);
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(INPUT_3.split("\n")).collect(Collectors.toList());

        int prio = 0;
        for (int i = 0; i < inputs.size(); i+=3) {
            String s1 = inputs.get(i);
            String s2 = inputs.get(i + 1);
            String s3 = inputs.get(i + 2);

            Set<Character> inS1 = new HashSet<>();
            for (Character ch : s1.toCharArray()) {
                inS1.add(ch);
            }

            Set<Character> inS1S2 = new HashSet<>();
            for (Character ch : s2.toCharArray()) {
                if (inS1.contains(ch)) {
                    inS1S2.add(ch);
                }
            }

            for (Character ch : s3.toCharArray()) {
                if (inS1S2.contains(ch)) {
                    prio += (getPrio(ch));
                    break;
                }
            }
        }
        System.out.println(prio);
    }

    private static int getPrio(Character ch) {
        return ch - (ch - 96 < 0 ? 38 : 96);
    }
}
