package code.vipul.aoc2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static code.vipul.aoc2021.Inputs.INPUT_3;

/**
 * Created by vgaur created on 24/12/21
 * https://adventofcode.com/2021/day/3
 */
public class Solve3 {

    private static final String INPUT = "00100\n" +
            "11110\n" +
            "10110\n" +
            "10111\n" +
            "10101\n" +
            "01111\n" +
            "00111\n" +
            "11100\n" +
            "10000\n" +
            "11001\n" +
            "00010\n" +
            "01010";

    public static void solve() {
        String[] numbers = INPUT_3.split("\n");
        int bits = numbers[0].length();

        int maxValue = ((int) Math.pow(2, bits)) - 1;
        int count0, count1;

        int gammaRate = 0;
        for (int i = 0; i < bits; i++) {
            count0 = 0; count1 = 0;
            for (String number : numbers) {
                if (number.charAt(i) == '1') count1++;
                else count0++;
            }

            if (count1 > count0) {
                gammaRate += (Math.pow(2, bits - i - 1));
            }
        }
        int epsilonRate = maxValue - gammaRate;

        System.out.println("Answer: " + epsilonRate * gammaRate); // 1092896
    }

    public static void solvePart2() {
        String[] numbers = INPUT_3.split("\n");
        int bits = numbers[0].length();

        List<Integer> o2indices = null;
        List<Integer> co2indices = null;

        List<Integer> indices1 = null;
        List<Integer> indices0 = null;

        int indexo2 = -1, indexco2 = -1;
        for (int i = 0; i < bits; i++) {
            int o2limit = o2indices == null ? numbers.length : o2indices.size();
            if (o2limit == 1) {
                indexo2 = o2indices.get(0);
                break;
            }
            indices0 = new ArrayList<>();
            indices1 = new ArrayList<>();
            List<Integer> finalO2indices = o2indices;
            Function<Integer, Integer> getIndex = (j) -> finalO2indices == null ? j : finalO2indices.get(j);
            for (int j = 0; j < o2limit; j++) {
                int idx = getIndex.apply(j);
                String number = numbers[idx];
                if (number.charAt(i) == '1') {
                    indices1.add(idx);
                } else {
                    indices0.add(idx);
                }
            }

            if (indices1.size() >= indices0.size()) {
                o2indices = indices1;
            } else {
                o2indices = indices0;
            }
        }

        if (indexo2 == -1) {
            indexo2 = o2indices.get(0);
        }

        for (int i = 0; i < bits; i++) {
            int co2limit = co2indices == null ? numbers.length : co2indices.size();
            if (co2limit == 1) {
                indexco2 = co2indices.get(0);
                break;
            }
            indices0 = new ArrayList<>();
            indices1 = new ArrayList<>();
            List<Integer> finalCo2indices = co2indices;
            Function<Integer, Integer> getIndex = (j) -> finalCo2indices == null ? j : finalCo2indices.get(j);
            for (int j = 0; j < co2limit; j++) {
                int idx = getIndex.apply(j);
                String number = numbers[idx];
                if (number.charAt(i) == '1') {
                    indices1.add(idx);
                } else {
                    indices0.add(idx);
                }
            }

            if (indices1.size() < indices0.size()) {
                co2indices = indices1;
            } else {
                co2indices = indices0;
            }
        }
        if (indexco2 == -1) {
            indexco2 = co2indices.get(0);
        }

        int number1 = 0, number2 = 0;
        String num1 = numbers[indexo2];
        String num2 = numbers[indexco2];
        for (int i = bits-1, j = 0; i >=0; i--, j++) {
            number1 += (num1.charAt(i) == '1' ? Math.pow(2, j) : 0);
            number2 += (num2.charAt(i) == '1' ? Math.pow(2, j) : 0);
        }

        System.out.println("Answer: " + number1 * number2); // 4672151
    }
}
