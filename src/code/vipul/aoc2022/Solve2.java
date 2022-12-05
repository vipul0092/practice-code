package code.vipul.aoc2022;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static code.vipul.aoc2022.inputs.Inputs.INPUT_2;

/**
 * https://adventofcode.com/2022/day/2
 */
public class Solve2 {

    private static final String INPUT = "A Y\n" +
            "B X\n" +
            "C Z";
    private static final Character ROCK = 'A';
    private static final Character ROCK_GUIDE = 'X';
    private static final Character PAPER = 'B';
    private static final Character PAPER_GUIDE = 'Y';
    private static final Character SCISSORS = 'C';
    private static final Character SCISSORS_GUIDE = 'Z';

    // key defeats value
    private static final Map<Character, Character> WIN;
    private static final Map<Character, Character> TO_WIN;
    private static final Map<Character, Integer> VALUE;

    static {
        WIN = new HashMap<>();
        TO_WIN = new HashMap<>();
        WIN.put(ROCK, SCISSORS);
        TO_WIN.put(SCISSORS, ROCK);

        WIN.put(PAPER, ROCK);
        TO_WIN.put(ROCK, PAPER);

        WIN.put(SCISSORS, PAPER);
        TO_WIN.put(PAPER, SCISSORS);

        VALUE = new HashMap<>();
        VALUE.put(ROCK, 1);
        VALUE.put(PAPER, 2);
        VALUE.put(SCISSORS, 3);
    }

    public static void solve() {
        List<String> inputs = Arrays.stream(INPUT_2.split("\n")).collect(Collectors.toList());

        int score = 0;
        for (String in : inputs) {
            Character opp = in.split(" ")[0].charAt(0);
            Character guide = getChar(in.split(" ")[1].charAt(0));

            if (opp == guide) {
                score += 3;
            } else if (WIN.get(guide) == opp) {
                score += 6;
            }

            score += VALUE.get(guide);
        }

        System.out.println(score);
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(INPUT_2.split("\n")).collect(Collectors.toList());

        int score = 0;
        for (String in : inputs) {
            Character opp = in.split(" ")[0].charAt(0);
            Character guide = in.split(" ")[1].charAt(0);
            Character chosen = null;

            if (guide == 'X') { // lose
                chosen = WIN.get(opp);
            } else if (guide == 'Y') { // draw
                chosen = opp;
                score += 3;
            } else if (guide == 'Z') { // win
                chosen = TO_WIN.get(opp);
                score += 6;
            }
            score += VALUE.get(chosen);
        }
        System.out.println(score);
    }

    private static Character getChar(Character guide) {
        if (guide == ROCK_GUIDE) return ROCK;
        else if (guide == PAPER_GUIDE) return PAPER;
        else return SCISSORS;
    }
}
