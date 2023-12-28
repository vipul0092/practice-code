package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_8;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day8 {

    private static String INPUT = """
            b inc 5 if a > 1
            a inc 1 if b < 5
            c dec -10 if a >= 1
            c inc -20 if c == 10
            """;

    private static final List<String> CHECKS = List.of(" < ", " > ", " <= ", " >= ", " == ", " != ");

    public static void solve() {
        INPUT = DAY_8;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<String, Integer> registers = new HashMap<>();
        int maxduring = 0;
        for (String line : lines) {
            var parts = line.split(" if ");
            var firstOperation = parts[0].contains("inc") ? " inc " : " dec ";
            var operation = parts[0].split(firstOperation);
            var register = operation[0];
            registers.putIfAbsent(register, 0);
            int operand = Integer.parseInt(operation[1]);

            var check = "";
            for (String c : CHECKS) {
                if (parts[1].contains(c)) {
                    check = c;
                    break;
                }
            }
            operation = parts[1].split(check);
            var registerToCheck = operation[0];
            registers.putIfAbsent(registerToCheck, 0);
            int valueToCheck = Integer.parseInt(operation[1]);

            if ((check.equals(" > ") && registers.get(registerToCheck) > valueToCheck)
                || (check.equals(" < ") && registers.get(registerToCheck) < valueToCheck)
                || (check.equals(" <= ") && registers.get(registerToCheck) <= valueToCheck)
                || (check.equals(" >= ") && registers.get(registerToCheck) >= valueToCheck)
                || (check.equals(" == ") && registers.get(registerToCheck) == valueToCheck)
                || (check.equals(" != ") && registers.get(registerToCheck) != valueToCheck)) {
                updateRegister(register, operand, firstOperation, registers);
            }
            maxduring = Math.max(maxduring, registers.values().stream().mapToInt(i -> i).max().getAsInt());
        }

        int max = registers.values().stream().mapToInt(i -> i).max().getAsInt();

        System.out.println("Part 1: " + max); // 4066
        System.out.println("Part 1: " + maxduring); // 4829
    }

    private static void updateRegister(String register, int operand, String operation, Map<String, Integer> registers) {
        registers.put(register, registers.get(register) + ((operation.equals(" inc ") ? 1 : -1)*operand));
    }
}
