package code.vipul.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 30/12/22
 * https://adventofcode.com/2021/day/10
 */
public class Solve10 {

    private static final String INPUT = "[({(<(())[]>[[{[]{<()<>>\n" +
            "[(()[<>])]({[<{<<[]>>(\n" +
            "{([(<{}[<>[]}>{[]{[(<()>\n" +
            "(((({<>}<{<{<>}{[]{[]{}\n" +
            "[[<[([]))<([[{}[[()]]]\n" +
            "[{[{({}]{}}([{[{{{}}([]\n" +
            "{<[[]]>}<{[{[{[]{()[[[]\n" +
            "[<(<(<(<{}))><([]([]()\n" +
            "<{([([[(<>()){}]>(<<{{\n" +
            "<{([{{}}[<[[[<>{}]]]>[]]";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_10.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int score = 0;
        List<Long> part2Scores = new ArrayList<>();
        for (String in : inputs) {
            Stack<Character> stack = new Stack<>();

            boolean invalid = false;
            for (char ch : in.toCharArray()) {
                if (ch == '>') {
                    if (stack.pop() != '<') {
                        score += 25137;
                        invalid = true;
                        break;
                    }
                } else if (ch == ']') {
                    if (stack.pop() != '[') {
                        score += 57;
                        invalid = true;
                        break;
                    }
                } else if (ch == '}') {
                    if (stack.pop() != '{') {
                        score += 1197;
                        invalid = true;
                        break;
                    }
                } else if (ch == ')') {
                    if (stack.pop() != '(') {
                        score += 3;
                        invalid = true;
                        break;
                    }
                } else {
                    stack.push(ch);
                }
            }

            if (!invalid) {
                long score2 = 0;
                while (!stack.isEmpty()) {
                    char ch = stack.pop();
                    if (ch == '[') {
                        score2 = ((score2 * 5) + 2);
                    } else if (ch == '(') {
                        score2 = ((score2 * 5) + 1);
                    } else if (ch == '{') {
                        score2 = ((score2 * 5) + 3);
                    } else if (ch == '<') {
                        score2 = ((score2 * 5) + 4);
                    }
                }
                part2Scores.add(score2);
            }
        }

        System.out.println("Part 1: " + score); //366027
        part2Scores = part2Scores.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println("Part 2: " + (part2Scores.get(part2Scores.size() / 2))); //1118645287
    }
}
