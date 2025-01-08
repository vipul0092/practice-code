package code.vipul.aoc2018Part2;

import java.util.*;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2018/day/14
 */
public class Day14 {

    public static void solve() {
        int count = Integer.parseInt(AoCInputReader.read(Day14.class, false).getFirst());

        List<Integer> numbers = new ArrayList<>();
        numbers.add(3); numbers.add(7);
        int first = 0, second = 1;

        while(numbers.size() < count + 10) {
            int num1 = numbers.get(first), num2 = numbers.get(second);
            int sum = num1 + num2;
            if (sum > 9) {
                numbers.add(sum/10);
                numbers.add(sum % 10);
            } else {
                numbers.add(sum);
            }

            first = (first + num1 + 1) % numbers.size();
            second = (second + num2 + 1) % numbers.size();
        }


        StringBuilder num = new StringBuilder();
        int ds = 0;
        int idx = count;
        while(ds < 10) {
            num.append(numbers.get(idx));
            idx++;
            ds++;
        }

        System.out.println("Part 1: " + num);


        int digits = (int)Math.log10(count) + 1;
        int divisor = (int)Math.pow(10, digits-1);
        numbers = new ArrayList<>();
        numbers.add(3); numbers.add(7);
        first = 0; second = 1;
        Map<Integer, Integer> numberPerIndex = new HashMap<>();
        int prevNumber = -1;
        while(!numberPerIndex.containsKey(count)) {
            int sum = numbers.get(first) + numbers.get(second);
            int generated;
            if (sum > 9) {
                numbers.add(sum / 10);
                numbers.add(sum % 10);
                generated = 2;
            } else {
                numbers.add(sum);
                generated = 1;
            }

            first = (first + numbers.get(first) + 1) % numbers.size();
            second = (second + numbers.get(second) + 1) % numbers.size();

            if (numbers.size() >= 6) {
                if (prevNumber == -1) {
                    while (generated-- > 0) {
                        int number = 0;
                        int i = numbers.size() - 1 - generated, j = 0;
                        for (; j < digits && i >= 0; i--, j++) {
                            number += numbers.get(i) * (int) Math.pow(10, j);
                        }
                        if (j == digits) {
                            numberPerIndex.put(number, i + 1);
                            prevNumber = number;
                        }
                    }
                } else {
                    while (generated-- > 0) {
                        int digit = numbers.get(numbers.size() - 1 - generated);
                        int newNum = ((prevNumber % divisor) * 10) + digit;
                        if (!numberPerIndex.containsKey(newNum)) {
                            numberPerIndex.put(newNum, numbers.size() - generated - digits);
                        }
                        prevNumber = newNum;
                    }
                }
            }
        }
        System.out.println("Part 2: " + numberPerIndex.get(count));
    }
}
