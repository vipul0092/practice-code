package code.vipul.aoc2018;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import static code.vipul.aoc2018.Solve16.EVALUATORS;
import static code.vipul.aoc2018.Solve16.registers;
import static code.vipul.aoc2018.Solve19.getIntPtrLinkedRegisterValue;
import static code.vipul.aoc2018.Solve19.setLinkedRegisterValue;
import static java.util.stream.Collectors.toList;

/**
 * https://adventofcode.com/2018/day/21
 */
public class Solve21 {

    // part 1: 9107763; part 2: 7877093
    private static final String INPUT = "#ip 2\n" +
            "seti 123 0 3\n" +
            "bani 3 456 3\n" +
            "eqri 3 72 3\n" +
            "addr 3 2 2\n" +
            "seti 0 0 2\n" +
            "seti 0 6 3\n" +
            "bori 3 65536 4\n" +
            "seti 7041048 8 3\n" +
            "bani 4 255 5\n" +
            "addr 3 5 3\n" +
            "bani 3 16777215 3\n" +
            "muli 3 65899 3\n" +
            "bani 3 16777215 3\n" +
            "gtir 256 4 5\n" +
            "addr 5 2 2\n" +
            "addi 2 1 2\n" +
            "seti 27 6 2\n" +
            "seti 0 1 5\n" +
            "addi 5 1 1\n" +
            "muli 1 256 1\n" +
            "gtrr 1 4 1\n" +
            "addr 1 2 2\n" +
            "addi 2 1 2\n" +
            "seti 25 1 2\n" +
            "addi 5 1 5\n" +
            "seti 17 8 2\n" +
            "setr 5 2 4\n" +
            "seti 7 9 2\n" +
            "eqrr 3 0 5\n" +
            "addr 5 2 2\n" +
            "seti 5 3 2";

    // part 1: 11474091; part 2: 4520776
    private static final String INPUT_2 = "#ip 2\n" +
            "seti 123 0 3\n" +
            "bani 3 456 3\n" +
            "eqri 3 72 3\n" +
            "addr 3 2 2\n" +
            "seti 0 0 2\n" +
            "seti 0 6 3\n" +
            "bori 3 65536 4\n" +
            "seti 2176960 8 3\n" +
            "bani 4 255 1\n" +
            "addr 3 1 3\n" +
            "bani 3 16777215 3\n" +
            "muli 3 65899 3\n" +
            "bani 3 16777215 3\n" +
            "gtir 256 4 1\n" +
            "addr 1 2 2\n" +
            "addi 2 1 2\n" +
            "seti 27 7 2\n" +
            "seti 0 9 1\n" +
            "addi 1 1 5\n" +
            "muli 5 256 5\n" +
            "gtrr 5 4 5\n" +
            "addr 5 2 2\n" +
            "addi 2 1 2\n" +
            "seti 25 7 2\n" +
            "addi 1 1 1\n" +
            "seti 17 2 2\n" +
            "setr 1 7 4\n" +
            "seti 7 9 2\n" +
            "eqrr 3 0 1\n" +
            "addr 1 2 2\n" +
            "seti 5 9 2";

    // Part 1: 7224964, Part 2: 13813247
    private static final String INPUT_3 = "#ip 1\n" +
            "seti 123 0 5\n" +
            "bani 5 456 5\n" +
            "eqri 5 72 5\n" +
            "addr 5 1 1\n" +
            "seti 0 0 1\n" +
            "seti 0 3 5\n" +
            "bori 5 65536 4\n" +
            "seti 13284195 4 5\n" +
            "bani 4 255 3\n" +
            "addr 5 3 5\n" +
            "bani 5 16777215 5\n" +
            "muli 5 65899 5\n" +
            "bani 5 16777215 5\n" +
            "gtir 256 4 3\n" +
            "addr 3 1 1\n" +
            "addi 1 1 1\n" +
            "seti 27 1 1\n" +
            "seti 0 5 3\n" +
            "addi 3 1 2\n" +
            "muli 2 256 2\n" +
            "gtrr 2 4 2\n" +
            "addr 2 1 1\n" +
            "addi 1 1 1\n" +
            "seti 25 2 1\n" +
            "addi 3 1 3\n" +
            "seti 17 1 1\n" +
            "setr 3 7 4\n" +
            "seti 7 3 1\n" +
            "eqrr 5 0 3\n" +
            "addr 3 1 1\n" +
            "seti 5 3 1";

    // part 1 - 13970209, part 2 - 6267260
    private static final String INPUT_4 = "#ip 4\n" +
            "seti 123 0 2\n" +
            "bani 2 456 2\n" +
            "eqri 2 72 2\n" +
            "addr 2 4 4\n" +
            "seti 0 0 4\n" +
            "seti 0 8 2\n" +
            "bori 2 65536 5\n" +
            "seti 2238642 0 2\n" +
            "bani 5 255 3\n" +
            "addr 2 3 2\n" +
            "bani 2 16777215 2\n" +
            "muli 2 65899 2\n" +
            "bani 2 16777215 2\n" +
            "gtir 256 5 3\n" +
            "addr 3 4 4\n" +
            "addi 4 1 4\n" +
            "seti 27 3 4\n" +
            "seti 0 8 3\n" +
            "addi 3 1 1\n" +
            "muli 1 256 1\n" +
            "gtrr 1 5 1\n" +
            "addr 1 4 4\n" +
            "addi 4 1 4\n" +
            "seti 25 4 4\n" +
            "addi 3 1 3\n" +
            "seti 17 2 4\n" +
            "setr 3 9 5\n" +
            "seti 7 9 4\n" +
            "eqrr 2 0 3\n" +
            "addr 3 4 4\n" +
            "seti 5 0 4";

    // part 1 - 15690445; part 2 - 936387
    private static final String INPUT_5 = "#ip 1\n" +
            "seti 123 0 4\n" +
            "bani 4 456 4\n" +
            "eqri 4 72 4\n" +
            "addr 4 1 1\n" +
            "seti 0 0 1\n" +
            "seti 0 4 4\n" +
            "bori 4 65536 3\n" +
            "seti 12670166 8 4\n" +
            "bani 3 255 2\n" +
            "addr 4 2 4\n" +
            "bani 4 16777215 4\n" +
            "muli 4 65899 4\n" +
            "bani 4 16777215 4\n" +
            "gtir 256 3 2\n" +
            "addr 2 1 1\n" +
            "addi 1 1 1\n" +
            "seti 27 6 1\n" +
            "seti 0 0 2\n" +
            "addi 2 1 5\n" +
            "muli 5 256 5\n" +
            "gtrr 5 3 5\n" +
            "addr 5 1 1\n" +
            "addi 1 1 1\n" +
            "seti 25 6 1\n" +
            "addi 2 1 2\n" +
            "seti 17 8 1\n" +
            "setr 2 5 3\n" +
            "seti 7 2 1\n" +
            "eqrr 4 0 2\n" +
            "addr 2 1 1\n" +
            "seti 5 8 1";

    public static void solve() {
        registers = new long[6];

        solveInternal21(INPUT_5, false);
    }

    public static void solvePart2() {
        registers = new long[6];

        solveInternal21(INPUT_5, true);
    }

    private static void solveInternal21(String in, boolean isPart2) {
        List<String> inputs = Arrays.stream(in.split("\n")).collect(toList());
        Solve19.intPtrRegister = Integer.parseInt(inputs.get(0).split(" ")[1]);

        inputs = inputs.subList(1, inputs.size());

        long intPtr;
        long prevLinkedRegisterValue;

        long zeroval = 0;

        registers[0] = zeroval;
        intPtr = 0;
        prevLinkedRegisterValue = getIntPtrLinkedRegisterValue();
        Set<Long> possibles = new LinkedHashSet<>();
        long prev = -1;
        int divregister = -1;
        int otherregister = -1;
        while (intPtr < inputs.size()) {
            String input = inputs.get((int) intPtr);
            String[] parts = input.split(" ");
            String opcode = parts[0];
            int a = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            int c = Integer.parseInt(parts[3]);

            // System.out.println("Executing " + input);

            if (isPart2) {
                if (opcode.equals("muli") && b == 256 && registers[c] == 4) {
                    if (divregister == -1) {
                        TreeMap<Long, Integer> ts = new TreeMap<>(Comparator.reverseOrder());
                        for (int i = 0; i < 6; i++) {
                            ts.put(registers[i], i);
                            if (registers[i] == 3) {
                                otherregister = i;
                            }
                        }
                        ts.remove(ts.firstKey());
                        divregister = ts.get(ts.firstKey());
                    }


                    long div = registers[divregister] / 256;
                    registers[c] = div + 1;
                    registers[otherregister] = div;
                } else if (input.startsWith("eqrr")) {
                    if (possibles.contains(registers[a])) {
                        System.out.println("Answer Part 2: " + prev);
                        break;
                    }
                    possibles.add(registers[a]);
                    prev = registers[a];
                    // System.out.println("EXPECTED: " + registers[3]);
                }
            } else if (input.startsWith("eqrr")) {
                System.out.println("Answer Part 1: " + registers[a]);
                break;
            }

            EVALUATORS.get(opcode).execute(a, b, c);

//                System.out.println("Register values: " + getRegistersValues());
//                System.out.println();

            long currentLinkedRegisterValue = getIntPtrLinkedRegisterValue();
            if (prevLinkedRegisterValue != currentLinkedRegisterValue) {
                intPtr = currentLinkedRegisterValue;
            }
            intPtr++;
            if (intPtr >= inputs.size()) {
                break;
            }
            setLinkedRegisterValue(intPtr);
            prevLinkedRegisterValue = intPtr;
        }
    }
}
