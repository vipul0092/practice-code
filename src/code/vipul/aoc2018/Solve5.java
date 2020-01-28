package code.vipul.aoc2018;

import java.util.Stack;

/**
 * https://adventofcode.com/2018/day/5
 */
public class Solve5 {

    public static void solve() {
        String input = Inputs.DAY5;
        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            char oppositePolarity = ch >= 'A' && ch <= 'Z'
                    ? (char) (ch + 32)
                    : (char) (ch - 32);
            if (!stack.empty()) {
                if (stack.peek().equals(oppositePolarity)) {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            } else {
                stack.push(ch);
            }
        }

        System.out.println("Answer: " + stack.size()); // 10496
    }

    public static void solvePart2() {
        String input = Inputs.DAY5;
        Stack[] stacks = new Stack[26];

        for (char ch : input.toCharArray()) {
            char oppositePolarity = ch >= 'A' && ch <= 'Z'
                    ? (char) (ch + 32)
                    : (char) (ch - 32);
            int index = ch >= 'A' && ch <= 'Z' ? ch - 'A' : ch - 'a';

            for (int i = 0; i < 26; i++) {
                if (index == i) {
                    continue;
                }
                if (stacks[i] == null) {
                    stacks[i] = new Stack<Character>();
                }

                if (!stacks[i].empty()) {
                    if (stacks[i].peek().equals(oppositePolarity)) {
                        stacks[i].pop();
                    } else {
                        stacks[i].push(ch);
                    }
                } else {
                    stacks[i].push(ch);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (stacks[i] != null) {
                min = Math.min(min, stacks[i].size());
            }
        }
        System.out.println("Answer: " + min); // 5774
    }
}
