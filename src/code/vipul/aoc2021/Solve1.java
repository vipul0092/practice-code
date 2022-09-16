package code.vipul.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static code.vipul.aoc2021.Inputs.INPUT_1;

/**
 * Created by vgaur created on 24/12/21
 * https://adventofcode.com/2021/day/1
 */
public class Solve1 {

    // Answer: 7
    private static final String INPUT = "199\n" +
            "200\n" +
            "208\n" +
            "210\n" +
            "200\n" +
            "207\n" +
            "240\n" +
            "269\n" +
            "260\n" +
            "263";

    public static void solve() {
        List<Integer> numbers = Arrays.stream(INPUT_1.split("\n"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int answer = 0;

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i - 1) < numbers.get(i)) answer++;
        }

        System.out.println("Answer: " + answer); // 1266
    }

    private static final int WINDOW_SIZE = 3;
    public static void solvePart2() {
        List<Integer> numbers = Arrays.stream(INPUT_1.split("\n"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int answer = 0;
        for (int i = WINDOW_SIZE; i < numbers.size(); i++) {
            if (numbers.get(i - WINDOW_SIZE) < numbers.get(i)) answer++;
        }

        System.out.println("Answer: " + answer); // 1217
    }
}
