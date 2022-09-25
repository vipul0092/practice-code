package code.vipul.aoc2020;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs2.INPUT_15;

/**
 * https://adventofcode.com/2020/day/15
 */
public class Solve15 {

    private static final String INPUT = "3,1,2";

    private static List<Integer> numbers;
    private static Map<Integer, Turns> turnMap;

    public static void solve() {
        numbers = Arrays.stream(INPUT_15.split(",")).map(n -> Integer.parseInt(n))
                .collect(Collectors.toList());
        turnMap = new LinkedHashMap<>();

        int answer = speak(2020);

        System.out.println("Answer: " + answer); //276
    }

    public static void solvePart2() {
        numbers = Arrays.stream(INPUT_15.split(",")).map(n -> Integer.parseInt(n))
                .collect(Collectors.toList());
        turnMap = new LinkedHashMap<>();

        int answer = speak(30000000);

        System.out.println("Answer: " + answer); //31916
    }

    private static int speak(int turns) {
        boolean wasLastNumberSpokenFirstTime = false;
        int lastNumber = -1;
        for (int i = 1; i <= turns; i++) {
            if (i <= numbers.size()) {
                lastNumber = numbers.get(i - 1);
            } else if (wasLastNumberSpokenFirstTime) {
                lastNumber = 0;
            } else {
                lastNumber = turnMap.get(lastNumber).getTurnDiff();
            }
            wasLastNumberSpokenFirstTime = !turnMap.containsKey(lastNumber);
            turnMap.putIfAbsent(lastNumber, new Turns());
            turnMap.get(lastNumber).setLastTurn(i);
        }

        return lastNumber;
    }

    public static class Turns {
        private Integer prevTurn;
        private Integer prevToPrevTurn;

        public int getTurnDiff() {
            return prevTurn - prevToPrevTurn;
        }

        public void setLastTurn(int turn) {
            if (prevToPrevTurn == null) {
                prevToPrevTurn = turn;
            } else if (prevTurn == null) {
                prevTurn = turn;
            } else {
                prevToPrevTurn = prevTurn;
                prevTurn = turn;
            }
        }
    }
}
