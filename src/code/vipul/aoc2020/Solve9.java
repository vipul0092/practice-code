package code.vipul.aoc2020;

import code.vipul.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs.INPUT_9;

/**
 * https://adventofcode.com/2020/day/9
 */
public class Solve9 {

    //Preamble size = 5
    private static final String INPUT = "35\n" +
            "20\n" +
            "15\n" +
            "25\n" +
            "47\n" +
            "40\n" +
            "62\n" +
            "55\n" +
            "65\n" +
            "95\n" +
            "102\n" +
            "117\n" +
            "150\n" +
            "182\n" +
            "127\n" +
            "219\n" +
            "299\n" +
            "277\n" +
            "309\n" +
            "576";
    private static final int PREAMBLE_SIZE = 25;

    public static void solve() {
        List<Long> numbers = Arrays.stream(INPUT_9.split("\n"))
                .map(in -> Long.parseLong(in))
                .collect(Collectors.toList());
        var answer = solveInternal(numbers);
        System.out.println("Answer: " + answer.left()); //15353384
    }

    public static void solvePart2() {
        List<Long> numbers = Arrays.stream(INPUT_9.split("\n"))
                .map(in -> Long.parseLong(in))
                .collect(Collectors.toList());
        var answer = solveInternal(numbers);

        long requiredSum = answer.left();
        int indexLimit = answer.right();
        long windowStart;
        var windowSet = new TreeSet<Long>();

        for (int window = 2; window < indexLimit; window++) {
            long currentSum = 0;
            windowStart = numbers.get(0);
            windowSet = new TreeSet<>();
            for (int i = 0; i < window; i++) {
                var currentNumber = numbers.get(i);
                currentSum += currentNumber;
                windowSet.add(currentNumber);
            }

            if (currentSum == requiredSum) {
                break;
            } else if (currentSum > requiredSum) {
                continue;
            }

            boolean found = false;
            for (int i = window; i < indexLimit; i++) {
                var currentNumber = numbers.get(i);
                currentSum -= windowStart;
                currentSum += currentNumber;
                windowSet.remove(windowStart);
                windowSet.add(currentNumber);
                windowStart = numbers.get(i - window + 1);

                if (currentSum == requiredSum) {
                    found = true;
                    break;
                } else if (currentSum > requiredSum) {
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        var solution = windowSet.first() + windowSet.last();
        System.out.println("Answer: " + solution); //2466556
    }

    private static Pair<Long, Integer> solveInternal(List<Long> numbers) {
        var currentWindow = new HashSet<Long>();
        int counter = PREAMBLE_SIZE;
        while (counter > 0) {
            currentWindow.add(numbers.get(counter - 1));
            counter--;
        }
        long answer = -1;
        int foundIndex = -1;

        for (int i = PREAMBLE_SIZE; i < numbers.size(); i++) {
            long number = numbers.get(i);
            boolean isContained = false;
            for (var windowNumber : currentWindow) {
                var diff = number - windowNumber;
                if (currentWindow.contains(diff) && (diff != windowNumber)) {
                    isContained = true;
                    break;
                }
            }
            if (!isContained) {
                answer = number;
                foundIndex = i;
                break;
            }
            currentWindow.remove(numbers.get(i - PREAMBLE_SIZE));
            currentWindow.add(number);
        }
        return Pair.of(answer, foundIndex);
    }
}
