package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_23;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day23 {

    private static String INPUT = """
            inc a
            jio a, +2
            tpl a
            inc a
            """;

    private static long[] registers;

    public static void solve() {
        INPUT = DAY_23;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        registers = new long[2];
        System.out.println("Part 1: " + run(lines)); // 170

        registers = new long[]{1, 0};
        System.out.println("Part 2: " + run(lines)); // 247
    }

    private static long run(List<String> instructions) {
        int idx = 0;
        while (idx < instructions.size()) {
            String instruction = instructions.get(idx);
            var parts = instruction.split(" ");
            int register = parts[1].charAt(0) - 'a';
            switch (parts[0]) {
                case "hlf" -> registers[register] /= 2;
                case "tpl" -> registers[register] *= 3;
                case "inc" -> registers[register] += 1;
                case "jmp" -> {
                    int offset = Integer.parseInt(parts[1].substring(1));
                    if (parts[1].charAt(0) == '+') {
                        idx += offset;
                    } else {
                        idx -= offset;
                    }
                    idx--;
                }
                case "jie" -> {
                    int offset = Integer.parseInt(parts[2].substring(1));
                    if (registers[register] % 2 != 0) break;
                    if (parts[2].charAt(0) == '+') {
                        idx += offset;
                    } else {
                        idx -= offset;
                    }
                    idx--;
                }
                case "jio" -> {
                    int offset = Integer.parseInt(parts[2].substring(1));
                    if (registers[register] != 1) break;
                    if (parts[2].charAt(0) == '+') {
                        idx += offset;
                    } else {
                        idx -= offset;
                    }
                    idx--;
                }
            }
            // System.out.println(registers[0] + ", " + registers[1]);
            idx++;
        }
        return registers[1];
    }
}
