package code.vipul.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs.INPUT_1;

/**
 * https://adventofcode.com/2020/day/1
 */
public class Solve1 {

    public static void solve() {
        List<Integer> numbers = Arrays.stream(INPUT_1.split("\n"))
                .map(in -> Integer.parseInt(in))
                .collect(Collectors.toList());

        int answer = -1;
        Set<Integer> set = new HashSet<>();

        for (Integer number : numbers) {
            int complement = 2020 - number;
            if (set.contains(complement)) {
                answer = number * complement;
                break;
            } else {
                set.add(number);
            }
        }

        System.out.println("Answer: " + answer); // 955584
    }

    public static void solvePart2() {
        List<Integer> numbers = Arrays.stream(INPUT_1.split("\n"))
                .map(in -> Integer.parseInt(in))
                .collect(Collectors.toList());

        Map<Integer, List<Set<Integer>>> doubleSumMap = new HashMap<>();

        // O(n^2)
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                int complement = 2020 - (numbers.get(i) + numbers.get(j));
                if (complement > 0) {
                    doubleSumMap.putIfAbsent(complement, new ArrayList<>());
                    doubleSumMap.get(complement).add(Set.of(i, j));
                }
            }
        }

        int answer = -1;
        for (int i = 0; i < numbers.size(); i++) {
            final int index = i;
            if (doubleSumMap.containsKey(numbers.get(i))) {
                var pair = doubleSumMap.get(numbers.get(i)).stream()
                        .filter(pairs -> !pairs.contains(index)).findFirst();
                if (pair.isPresent()) {
                    answer = numbers.get(i);
                    for (int in: pair.get()) {
                        answer *= numbers.get(in);
                    }
                    break;
                }
            }
        }
        System.out.println("Answer: " + answer); // 287503934
    }
}
