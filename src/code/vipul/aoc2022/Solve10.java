package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 09/12/22
 */
public class Solve10 {

    private static final String INPUT = "addx 15\n" +
            "addx -11\n" +
            "addx 6\n" +
            "addx -3\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx -8\n" +
            "addx 13\n" +
            "addx 4\n" +
            "noop\n" +
            "addx -1\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx -35\n" +
            "addx 1\n" +
            "addx 24\n" +
            "addx -19\n" +
            "addx 1\n" +
            "addx 16\n" +
            "addx -11\n" +
            "noop\n" +
            "noop\n" +
            "addx 21\n" +
            "addx -15\n" +
            "noop\n" +
            "noop\n" +
            "addx -3\n" +
            "addx 9\n" +
            "addx 1\n" +
            "addx -3\n" +
            "addx 8\n" +
            "addx 1\n" +
            "addx 5\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx -36\n" +
            "noop\n" +
            "addx 1\n" +
            "addx 7\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx 2\n" +
            "addx 6\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx 1\n" +
            "noop\n" +
            "noop\n" +
            "addx 7\n" +
            "addx 1\n" +
            "noop\n" +
            "addx -13\n" +
            "addx 13\n" +
            "addx 7\n" +
            "noop\n" +
            "addx 1\n" +
            "addx -33\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx 2\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx 8\n" +
            "noop\n" +
            "addx -1\n" +
            "addx 2\n" +
            "addx 1\n" +
            "noop\n" +
            "addx 17\n" +
            "addx -9\n" +
            "addx 1\n" +
            "addx 1\n" +
            "addx -3\n" +
            "addx 11\n" +
            "noop\n" +
            "noop\n" +
            "addx 1\n" +
            "noop\n" +
            "addx 1\n" +
            "noop\n" +
            "noop\n" +
            "addx -13\n" +
            "addx -19\n" +
            "addx 1\n" +
            "addx 3\n" +
            "addx 26\n" +
            "addx -30\n" +
            "addx 12\n" +
            "addx -1\n" +
            "addx 3\n" +
            "addx 1\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx -9\n" +
            "addx 18\n" +
            "addx 1\n" +
            "addx 2\n" +
            "noop\n" +
            "noop\n" +
            "addx 9\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx -1\n" +
            "addx 2\n" +
            "addx -37\n" +
            "addx 1\n" +
            "addx 3\n" +
            "noop\n" +
            "addx 15\n" +
            "addx -21\n" +
            "addx 22\n" +
            "addx -6\n" +
            "addx 1\n" +
            "noop\n" +
            "addx 2\n" +
            "addx 1\n" +
            "noop\n" +
            "addx -10\n" +
            "noop\n" +
            "noop\n" +
            "addx 20\n" +
            "addx 1\n" +
            "addx 2\n" +
            "addx 2\n" +
            "addx -6\n" +
            "addx -11\n" +
            "noop\n" +
            "noop\n" +
            "noop";

    private static final List<Integer> CYCLES = List.of(20, 60, 100, 140, 180, 220);

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_10.split("\n")).collect(Collectors.toList());
        // List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int registerX = 1;

        int cycle = 0;
        int sum = 0;
        int row = 0;
        int col = 0;
        char[][] crt = new char[6][40];

        for (String in : inputs) {
            Set<Integer> sprite = new HashSet<>();
            if (in.startsWith("noop")) {
                cycle++;
                if (col == 40) {
                    row++;
                    col = 0;
                }
                sprite.add(registerX);
                sprite.add(registerX - 1);
                sprite.add(registerX + 1);
                if (sprite.contains(col) && row < 6) {
                    crt[row][col] = '#';
                } else if (row < 6) {
                    crt[row][col] = '.';
                }
                col++;
                if (CYCLES.contains(cycle)) {
                    sum += (registerX * cycle);
                }
            } else {
                cycle++;
                if (col == 40) {
                    row++;
                    col = 0;
                }
                sprite.add(registerX);
                sprite.add(registerX - 1);
                sprite.add(registerX + 1);
                if (sprite.contains(col)) {
                    crt[row][col] = '#';
                } else {
                    crt[row][col] = '.';
                }
                col++;
                if (CYCLES.contains(cycle)) {
                    sum += (registerX * cycle);
                }

                if (col == 40) {
                    row++;
                    col = 0;
                }
                sprite.add(registerX);
                sprite.add(registerX - 1);
                sprite.add(registerX + 1);
                if (sprite.contains(col)) {
                    crt[row][col] = '#';
                } else {
                    crt[row][col] = '.';
                }
                col++;
                cycle++;
                if (CYCLES.contains(cycle)) {
                    sum += (registerX * cycle);
                }
                registerX += Integer.parseInt(in.split(" ")[1]);
            }
        }

        // Part 1
        System.out.println(sum);
        // Part 2
        render(crt);
    }

    private static void render(char[][] crt) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 40; j++) {
                System.out.print(crt[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
