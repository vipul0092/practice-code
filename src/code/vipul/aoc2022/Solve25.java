package code.vipul.aoc2022;

import code.vipul.Pair;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 25/12/22
 * https://adventofcode.com/2022/day/25
 */
public class Solve25 {

    private static final String INPUT =
            "1=-0-2\n" +
                    "12111\n" +
                    "2=0=\n" +
                    "21\n" +
                    "2=01\n" +
                    "111\n" +
                    "20012\n" +
                    "112\n" +
                    "1=-1=\n" +
                    "1-12\n" +
                    "12\n" +
                    "1=\n" +
                    "122";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_25.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());
        String num = inputs.get(0);
        for (int i = 1; i < inputs.size(); i++) {
            num = add(num, inputs.get(i));
        }
        System.out.println(num);
    }

    private static String add(String n1, String n2) {
        StringBuilder sb = new StringBuilder();

        String num1 = new StringBuilder(n1).reverse().toString();
        String num2 = new StringBuilder(n2).reverse().toString();

        int maxlen = Math.max(num1.length(), num2.length());

        char carryDigit = '0';
        for (int i = 0; i < maxlen; i++) {
            Character d1 = null;
            Character d2 = null;
            if (num1.length() > i) {
                d1 = num1.charAt(i);
            }
            if (num2.length() > i) {
                d2 = num2.charAt(i);
            }

            if (d1 != null && d2 != null) {
                var result = add(d1, carryDigit);
                char carry1 = result.right();
                result = add(d2, result.left());
                char carry2 = result.right();
                sb.append(result.left());

                var carryRes = add(carry1, carry2);
                carryDigit = carryRes.left();
            } else {
                var result = add(d1 == null ? d2 : d1, carryDigit);
                sb.append(result.left());
                carryDigit = result.right();
            }
        }
        if (carryDigit != '0') {
            sb.append(carryDigit);
        }
        return sb.reverse().toString();
    }

    private static Pair<Character, Character> add(char a1, char a2) {
        if (a1 == '=' && a2 == '=') {
            return Pair.of('1', '-');
        } else if ((a1 == '=' && a2 == '-') || (a1 == '-' && a2 == '=')) {
            return Pair.of('2', '-');
        } else if ((a1 == '=' && a2 == '0') || (a1 == '0' && a2 == '=')) {
            return Pair.of('=', '0');
        } else if ((a1 == '=' && a2 == '1') || (a1 == '1' && a2 == '=')) {
            return Pair.of('-', '0');
        } else if ((a1 == '=' && a2 == '2') || (a1 == '2' && a2 == '=')) {
            return Pair.of('0', '0');
        } else if (a1 == '-' && a2 == '-') {
            return Pair.of('=', '0');
        } else if ((a1 == '-' && a2 == '0') || (a1 == '0' && a2 == '-')) {
            return Pair.of('-', '0');
        } else if ((a1 == '-' && a2 == '1') || (a1 == '1' && a2 == '-')) {
            return Pair.of('0', '0');
        } else if ((a1 == '-' && a2 == '2') || (a1 == '2' && a2 == '-')) {
            return Pair.of('1', '0');
        } else if (a1 == '0' && a2 == '0') {
            return Pair.of('0', '0');
        } else if ((a1 == '0' && a2 == '1') || (a1 == '1' && a2 == '0')) {
            return Pair.of('1', '0');
        } else if ((a1 == '0' && a2 == '2') || (a1 == '2' && a2 == '0')) {
            return Pair.of('2', '0');
        } else if ((a1 == '1' && a2 == '2') || (a1 == '2' && a2 == '1')) {
            return Pair.of('=', '1');
        } else if (a1 == '1' && a2 == '1') {
            return Pair.of('2', '0');
        } else if (a1 == '2' && a2 == '2') {
            return Pair.of('-', '1');
        }
        throw new RuntimeException(String.format("Dont know how to handle %s and %s", a1, a2));
    }
}
