package code.vipul.aoc2015;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static code.vipul.aoc2015.Inputs.INPUT_7;

/**
 * Created by vgaur created on 10/09/22
 * https://adventofcode.com/2015/day/7
 *
 * 'char' type has been used to solve this problem because we need to deal with unsigned 16-bit numbers
 * for which there is no other easy way in Java to properly work with except using the 'char' type
 * Ugly but works!
 */
public class Solve7 {

    private static final String NOT = "NOT";
    private static final String AND = "AND";
    private static final String OR = "OR";
    private static final String LSHIFT = "LSHIFT";
    private static final String RSHIFT = "RSHIFT";

    public static Map<String, Gate> wiresInput;
    public static Map<String, Character> wireValues;

    public static String INPUT = "123 -> x\n" +
            "456 -> y\n" +
            "x AND y -> d\n" +
            "x OR y -> e\n" +
            "x LSHIFT 2 -> f\n" +
            "y RSHIFT 2 -> g\n" +
            "NOT x -> h\n" +
            "NOT y -> i";

    public static void solve() {
        wireValues = new HashMap<>();
        wiresInput = new HashMap<>();

        parseInput();

        char result = evaluate("a");
        System.out.println((int) result); //3176
    }

    public static void solvePart2() {
        wireValues = new HashMap<>();
        wiresInput = new HashMap<>();
        parseInput();

        char result = evaluate("a");

        // Reset wire values
        wireValues = new HashMap<>();
        // Override b with a's value
        wiresInput.put("b", new Gate(Integer.toString((int) result), null, null, "b"));

        result = evaluate("a");
        System.out.println((int) result); //14710
    }

    private static char evaluate(String wire) {
        if (wireValues.containsKey(wire)) {
            return wireValues.get(wire);
        }
        Gate input = wiresInput.get(wire);

        char result = 0;
        if (input.operator == null) {
            result = getOperandValue(input.op1);
        } else if (input.operator.equals(NOT)) {
            result = (char) ~getOperandValue(input.op1);
        } else if (input.operator.equals(AND)) {
            char op1 = getOperandValue(input.op1);
            char op2 = getOperandValue(input.op2);
            result = (char) (op1 & op2);
        } else if (input.operator.equals(OR)) {
            char op1 = getOperandValue(input.op1);
            char op2 = getOperandValue(input.op2);
            result = (char) (op1 | op2);
        } else if (input.operator.equals(LSHIFT)) {
            char op1 = getOperandValue(input.op1);
            char op2 = getOperandValue(input.op2);
            result = (char) (op1 << op2);
        } else if (input.operator.equals(RSHIFT)) {
            char op1 = getOperandValue(input.op1);
            char op2 = getOperandValue(input.op2);
            result = (char) (op1 >> op2);
        }

        wireValues.put(input.resultOp, result);
        return result;
    }

    private static char getOperandValue(String operand) {
        return wiresInput.containsKey(operand) ? evaluate(operand) : (char) Integer.parseInt(operand);
    }

    private static void parseInput() {
        Arrays.stream(INPUT_7.split("\n")).forEach(operation -> {
            String[] parts = operation.split(" -> ");
            String result = parts[1];
            String[] lhsParts = parts[0].split(" ");
            if (lhsParts.length == 1) { // Either a number or another wire is directly assigned
                wiresInput.put(result, new Gate(lhsParts[0], null, null, result));
            } else if (lhsParts.length == 2) { // NOT operator
                wiresInput.put(result, new Gate(lhsParts[1], null, lhsParts[0], result));
            } else if (lhsParts.length == 3) { // AND, OR, LSHIFT, RSHIFT operators
                wiresInput.put(result, new Gate(lhsParts[0], lhsParts[2], lhsParts[1], result));
            }
        });
    }


    public static class Gate {
        public final String op1;
        public final String op2;
        public final String operator;
        public final String resultOp;

        public Gate(String op1, String op2, String operator, String resultOp) {
            this.op1 = op1;
            this.op2 = op2;
            this.operator = operator;
            this.resultOp = resultOp;
        }

        @Override
        public String toString() {
            return op1 + (operator == null ? "" : operator) + (op2 == null ? "" : op2) + " -> " + resultOp;
        }
    }
}
