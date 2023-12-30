package code.vipul.aoc2017;

import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2017.inputs.Inputs.DAY_25;

/**
 * Created by vgaur created on 30/12/23
 */
public class Day25 {

    private static String INPUT = """
            Begin in state A.
            Perform a diagnostic checksum after 6 steps.
                        
            In state A:
              If the current value is 0:
                - Write the value 1.
                - Move one slot to the right.
                - Continue with state B.
              If the current value is 1:
                - Write the value 0.
                - Move one slot to the left.
                - Continue with state B.
                        
            In state B:
              If the current value is 0:
                - Write the value 1.
                - Move one slot to the left.
                - Continue with state A.
              If the current value is 1:
                - Write the value 1.
                - Move one slot to the right.
                - Continue with state A.
            """;

    public static void solve() {
        INPUT = DAY_25;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        Operation[][] rules = new Operation[26][2];
        int[] values = new int[10000];

        char start = lines.get(0).charAt(lines.get(0).length() - 2);
        int steps = Integer.parseInt(lines.get(1).split(" after ")[1].split(" ")[0]);

        for (int i = 3; i < lines.size(); i += 10) {
            char state = lines.get(i).charAt(lines.get(i).length() - 2);
            var operations = new Operation[]{parseOperation(lines, i + 2), parseOperation(lines, i + 6)};
            rules[state - 'A'] = operations;
        }

        int current = 0;
        char state = start;
        while (steps-- > 0) {
            int curval = values[current + 100];
            var operation = rules[state - 'A'][curval];
            values[current + 100] = operation.write;
            if (operation.left) {
                current--;
            } else {
                current++;
            }
            state = operation.nextState;
        }
        long count = Arrays.stream(values).filter(i -> i == 1).count();

        System.out.println("Answer: " + count); // 4225
    }

    ;

    private static Operation parseOperation(List<String> lines, int idx) {
        var line = lines.get(idx); // write
        int write = line.charAt(line.length() - 2) - '0';

        var parts = lines.get(idx + 1).split(" "); // left or right
        boolean left = parts[parts.length - 1].equals("left.");

        line = lines.get(idx + 2); // next state
        char next = line.charAt(line.length() - 2);

        return new Operation(write, left, next);
    }

    record Operation(int write, boolean left, char nextState) {
    }
}
