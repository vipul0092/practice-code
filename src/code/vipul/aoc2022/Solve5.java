package code.vipul.aoc2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static code.vipul.aoc2022.inputs.Inputs.INPUT_5;

/**
 * Created by vgaur created on 05/12/22
 * https://adventofcode.com/2022/day/5
 */
public class Solve5 {


    private static final String INPUT =
            "    [D]    \n" +
                    "[N] [C]    \n" +
                    "[Z] [M] [P]\n" +
                    " 1   2   3 \n" +
                    "\n" +
                    "move 1 from 2 to 1\n" +
                    "move 3 from 1 to 3\n" +
                    "move 2 from 2 to 1\n" +
                    "move 1 from 1 to 2";

    public static void solve() {
        List<String> inputs = Arrays.stream(INPUT_5.split("\n")).collect(Collectors.toList());
        int count = inputs.stream()
                .filter(in -> in.charAt(1) == '1')
                .map(in -> in.charAt(in.length() - 2) - 48)
                .findFirst().orElseThrow();


        List<Stack<Character>> stacks = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            stacks.add(new Stack<>());
        }

        for (String in : inputs) {
            if (in.isEmpty()) {
                continue;
            } else if (in.contains("[")) {
                int stackNum = 0;
                for (int i = 0; i < in.length(); i += 4) {
                    if (in.charAt(i) == '[') {
                        stacks.get(stackNum).push(in.charAt(i + 1));
                    }
                    stackNum++;
                }
            } else if (in.charAt(1) == '1') {
                List<Stack<Character>> tstacks = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    tstacks.add(new Stack<>());
                }
                for (int i = 0; i < count; i++) {
                    Stack<Character> s = stacks.get(i);
                    while (!s.isEmpty()) {
                        tstacks.get(i).push(s.pop());
                    }
                }
                stacks = tstacks;
            } else if (in.startsWith("move")) {
                String[] parts = in.split(" from ");
                int moveCount = Integer.parseInt(parts[0].split(" ")[1]);
                String[] toMove = parts[1].split(" to ");
                int from = Integer.parseInt(toMove[0]);
                int to = Integer.parseInt(toMove[1]);

                while (moveCount-- > 0) {
                    stacks.get(to - 1).push(stacks.get(from - 1).pop());
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Stack<Character> s : stacks) {
            sb.append(s.peek());
        }

        System.out.println(sb.toString());
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(INPUT_5.split("\n")).collect(Collectors.toList());
        int count = inputs.stream()
                .filter(in -> in.charAt(1) == '1')
                .map(in -> in.charAt(in.length() - 2) - 48)
                .findFirst().orElseThrow();


        List<Stack<Character>> stacks = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            stacks.add(new Stack<>());
        }

        for (String in : inputs) {
            if (in.isEmpty()) {
                continue;
            } else if (in.contains("[")) {
                int stackNum = 0;
                for (int i = 0; i < in.length(); i += 4) {
                    if (in.charAt(i) == '[') {
                        stacks.get(stackNum).push(in.charAt(i + 1));
                    }
                    stackNum++;
                }
            } else if (in.charAt(1) == '1') {
                List<Stack<Character>> tstacks = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    tstacks.add(new Stack<>());
                }
                for (int i = 0; i < count; i++) {
                    Stack<Character> s = stacks.get(i);
                    while (!s.isEmpty()) {
                        tstacks.get(i).push(s.pop());
                    }
                }
                stacks = tstacks;
            } else if (in.startsWith("move")) {
                String[] parts = in.split(" from ");
                int moveCount = Integer.parseInt(parts[0].split(" ")[1]);
                String[] toMove = parts[1].split(" to ");
                int from = Integer.parseInt(toMove[0]);
                int to = Integer.parseInt(toMove[1]);

                Stack<Character> temp = new Stack<>();
                while (moveCount-- > 0) {
                    temp.push(stacks.get(from - 1).pop());
                }
                while (!temp.isEmpty()) {
                    stacks.get(to - 1).push(temp.pop());
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Stack<Character> s : stacks) {
            sb.append(s.peek());
        }

        System.out.println(sb.toString());
    }
}
