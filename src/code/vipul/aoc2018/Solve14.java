package code.vipul.aoc2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://adventofcode.com/2018/day/14
 */
public class Solve14 {

    private static final int[] POWERS_OF_TEN;

    static {
        POWERS_OF_TEN = new int[6];
        POWERS_OF_TEN[0] = 1;
        POWERS_OF_TEN[1] = 10;
        POWERS_OF_TEN[2] = 100;
        POWERS_OF_TEN[3] = 1000;
        POWERS_OF_TEN[4] = 10000;
        POWERS_OF_TEN[5] = 100000;
    }

    private static int position1 = 0;
    private static int position2 = 1;
    private static int recipesAfter = 864801;
    private static int maxDigits = 6;
    private static int lastAddedNumber = -1;
    private static int lastIndexDone;
    private static Map<Integer, Integer> numbers = new HashMap<>();
    private static ArrayList<Integer> recipes = new ArrayList<>();

    public static void solve() {
        recipes.add(3);
        recipes.add(7);
        int recipesToInclude = 10;

        while (recipesToInclude + recipesAfter > recipes.size()) {
            updateRecipes();
            movePositions();
        }

        StringBuilder ans = new StringBuilder();
        for (int i = recipesAfter, j = 0; j < recipesToInclude; i++, j++) {
            ans.append(recipes.get(i));
        }

        System.out.println("Answer: " + ans.toString()); // 1611732174
    }

    public static void solvePart2() {
        recipes.add(3);
        recipes.add(7);

        while (recipes.size() < maxDigits) {
            updateRecipes();
            movePositions();
        }
        updateNumberSet();

        while (!numbers.containsKey(recipesAfter)) {
            updateRecipes();
            movePositions();
            updateNumberSet();
        }

        int index = numbers.get(recipesAfter);
        System.out.println("Answer: " + index); // 20279772
    }

    private static void updateRecipes() {
        int sum = recipes.get(position1) + recipes.get(position2);
        if (sum < 10) {
            recipes.add(sum);
        } else {
            recipes.add(sum / 10);
            recipes.add(sum % 10);
        }
    }

    private static void updateNumberSet() {
        int num = 0;
        if (lastAddedNumber == -1) { // the first time
            for (int i = 0; i < maxDigits; i++) {
                num += (POWERS_OF_TEN[maxDigits - 1 - i] * recipes.get(i));
            }
            lastAddedNumber = num;
            lastIndexDone = maxDigits - 1;
            numbers.put(num, 0);
        } else {
            numbers.clear();
            for (int i = lastIndexDone + 1; i < recipes.size(); i++) {
                num = lastAddedNumber % POWERS_OF_TEN[maxDigits - 1];
                num *= 10;
                num += recipes.get(i);
                numbers.put(num, i - maxDigits + 1);
                lastAddedNumber = num;
            }
            lastIndexDone = recipes.size() - 1;
        }
    }

    private static void movePositions() {
        int toMove = recipes.get(position1) + 1;
        position1 = (position1 + toMove) % recipes.size();

        toMove = recipes.get(position2) + 1;
        position2 = (position2 + toMove) % recipes.size();
    }

}
