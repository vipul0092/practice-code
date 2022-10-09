package code.vipul.aoc2018;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static code.vipul.aoc2018.Inputs2.DAY_19;
import static code.vipul.aoc2018.Inputs2.DAY_19_2;
import static code.vipul.aoc2018.Solve16.EVALUATORS;
import static code.vipul.aoc2018.Solve16.registers;
import static java.util.stream.Collectors.toList;

/**
 * https://adventofcode.com/2018/day/19
 */
public class Solve19 {

    private static final String INPUT = "#ip 0\n" +
            "seti 5 0 1\n" +
            "seti 6 0 2\n" +
            "addi 0 1 0\n" +
            "addr 1 2 3\n" +
            "setr 1 0 0\n" +
            "seti 8 0 4\n" +
            "seti 9 0 5";

    private static int intPtrRegister = -1;

    public static void solve() {
        registers = new long[6];
        solveInternal(DAY_19, false);
        System.out.println("Answer: " + registers[0]); //1824
    }

    public static void solvePart2() {
        registers = new long[6];
        registers[0] = 1;
        solveInternal(DAY_19_2, true);
    }

    private static void solveInternal(String in, boolean factorize) {
        List<String> inputs = Arrays.stream(in.split("\n")).collect(toList());
        intPtrRegister = Integer.parseInt(inputs.get(0).split(" ")[1]);

        inputs = inputs.subList(1, inputs.size());

        long intPtr = 0;
        long prevLinkedRegisterValue = getIntPtrLinkedRegisterValue();

        long val = -1;
        while (intPtr < inputs.size()) {
            String input = inputs.get((int) intPtr);
            String[] parts = input.split(" ");
            String opcode = parts[0];
            int a = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            int c = Integer.parseInt(parts[3]);

            EVALUATORS.get(opcode).execute(a, b, c);

//            System.out.println("Register values: " + getRegistersValues());
//            System.out.println();

            if (factorize && registers[0] == 0) {
                System.out.println("Register values: " + getRegistersValues());
                TreeSet<Long> set = new TreeSet<>(Comparator.reverseOrder());
                for (long l : registers) {
                    set.add(l);
                }
                val = set.iterator().next();
                break;
            }

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


        if (val != -1) {
            System.out.println("Number to factorize: " + val);

            long answer = 0;
            int sqrt = (int) Math.sqrt(val);
            for (int i = 1; i <= sqrt; i++) {
                if (val % i == 0) {
                    System.out.println("Factor found: " + i);
                    System.out.println("Factor found: " + (val/i));
                    answer += i;
                    answer += (val/i);
                }
            }

            System.out.println("Sum of factors: " + answer);
        }
    }

    private static long getIntPtrLinkedRegisterValue() {
        return registers[intPtrRegister];
    }

    private static void setLinkedRegisterValue(long value) {
        registers[intPtrRegister] = value;
    }

    private static String getRegistersValues() {
        StringBuilder s = new StringBuilder();
        for (long r : registers) {
            s.append(r).append(" ");
        }
        return s.toString();
    }
}
