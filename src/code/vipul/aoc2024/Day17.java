package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://adventofcode.com/2024/day/17
 */
public class Day17 {

    private static long[] registers = new long[3];
    private static int iptr = 0;
    private static List<Long> outputs = new ArrayList<>();
    private static List<String> lines;
    private static List<Integer> program;

    public static void solve() {
        lines = AoCInputReader.read(Day17.class, false);
        parse();
        process(program);
        System.out.println("Part 1: " + getOutputString()); // 7,6,5,3,6,5,7,0,4

        long answer = find(program, program.size() - 1, "", List.of(0L));
        System.out.println("Part 2: " + answer); // 190615597431823
    }

    // The given set of instructions is taking bits from register A, 3 at a time
    // and doing some manipulations on it, and outputting a value in the end
    // Then it right shifts the register A value by 3 bits, and continues the process
    // This function is reversing that logic by starting with the last output and moving backwards
    // by taking 3 bit numbers at each step and combining them with possibles of the previous steps
    // This combining is done by left shifting the previous values by 3 bits and then ORing the current 3 bit number
    // This ensures that the current output is correct in combination with the previous outputs
    private static long find(List<Integer> program, int digit, String output, List<Long> numbers) {
        if (digit == -1) {
            return numbers.stream().min(Comparator.naturalOrder()).orElseThrow();
        }
        int val = program.get(digit);
        String expectedOutput = output.isEmpty() ? String.valueOf(val) : val + "," + output;

        List<Long> possibles = new ArrayList<>();
        for (long num : numbers) {
            long shifted = num << 3;
            for (int i = 0; i < 8; i++) {
                long rega = shifted | i;
                registers[0] = rega;
                registers[1] = 0;
                registers[2] = 0;
                process(program);

                String out = getOutputString();
                if (out.equals(expectedOutput)) {
                    possibles.add(rega);
                }
            }
        }
        return find(program, digit - 1, expectedOutput, possibles);
    }

    private static void process(List<Integer> program) {
        outputs = new ArrayList<>();
        iptr = 0;
        while (iptr < program.size()) {
            int opcode = program.get(iptr);

            switch (opcode) {
                case 0 -> {
                    long num = registers[0];
                    long operand = combo(program, iptr + 1);
                    long result = num / (long) Math.pow(2, operand);
                    registers[0] = result;
                    iptr += 2;
                }
                case 1 -> {
                    registers[1] = registers[1] ^ program.get(iptr + 1);
                    iptr += 2;
                }
                case 2 -> {
                    registers[1] = combo(program, iptr + 1) % 8;
                    iptr += 2;
                }
                case 3 -> {
                    if (registers[0] == 0) {
                        iptr += 2;
                    } else {
                        iptr = program.get(iptr + 1);
                    }
                }
                case 4 -> {
                    registers[1] = registers[1] ^ registers[2];
                    iptr += 2;
                }
                case 5 -> {
                    outputs.add(combo(program, iptr + 1) % 8);
                    iptr += 2;
                }
                case 6 -> {
                    long num = registers[0];
                    long operand = combo(program, iptr + 1);
                    long result = num / (long) Math.pow(2, operand);
                    registers[1] = result;
                    iptr += 2;
                }
                case 7 -> {
                    long num = registers[0];
                    long operand = combo(program, iptr + 1);
                    long result = num / (long) Math.pow(2, operand);
                    registers[2] = result;
                    iptr += 2;
                }
            }
        }
    }

    private static void parse() {
        int ctr = 0;
        int i;
        for (i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) {
                i++;
                break;
            }
            var parts = line.split(" ");
            registers[ctr] = Integer.parseInt(parts[parts.length - 1]);
            ctr++;
        }
        program = Arrays.stream(lines.get(i).split(" ")[1].split(",")).map(Integer::parseInt).toList();
    }

    private static long combo(List<Integer> program, int ptr) {
        int val = program.get(ptr);
        if (val <= 3) return val;
        return registers[val - 4];
    }

    private static String getOutputString() {
        return String.join(",", outputs.stream().map(String::valueOf).toList());
    }
}
