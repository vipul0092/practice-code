package code.vipul.aoc2018;

import code.vipul.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://adventofcode.com/2018/day/7
 */
public class Solve7 {

    static String input =
            "Step O must be finished before step W can begin.\n" +
                    "Step S must be finished before step V can begin.\n" +
                    "Step Z must be finished before step B can begin.\n" +
                    "Step F must be finished before step R can begin.\n" +
                    "Step I must be finished before step D can begin.\n" +
                    "Step W must be finished before step P can begin.\n" +
                    "Step J must be finished before step E can begin.\n" +
                    "Step P must be finished before step N can begin.\n" +
                    "Step Q must be finished before step V can begin.\n" +
                    "Step D must be finished before step K can begin.\n" +
                    "Step X must be finished before step N can begin.\n" +
                    "Step E must be finished before step B can begin.\n" +
                    "Step L must be finished before step H can begin.\n" +
                    "Step A must be finished before step T can begin.\n" +
                    "Step U must be finished before step R can begin.\n" +
                    "Step M must be finished before step T can begin.\n" +
                    "Step V must be finished before step R can begin.\n" +
                    "Step N must be finished before step C can begin.\n" +
                    "Step T must be finished before step C can begin.\n" +
                    "Step Y must be finished before step B can begin.\n" +
                    "Step H must be finished before step B can begin.\n" +
                    "Step B must be finished before step C can begin.\n" +
                    "Step C must be finished before step K can begin.\n" +
                    "Step R must be finished before step K can begin.\n" +
                    "Step G must be finished before step K can begin.\n" +
                    "Step Q must be finished before step K can begin.\n" +
                    "Step U must be finished before step Y can begin.\n" +
                    "Step L must be finished before step G can begin.\n" +
                    "Step S must be finished before step D can begin.\n" +
                    "Step E must be finished before step R can begin.\n" +
                    "Step Z must be finished before step M can begin.\n" +
                    "Step U must be finished before step K can begin.\n" +
                    "Step Q must be finished before step H can begin.\n" +
                    "Step T must be finished before step B can begin.\n" +
                    "Step J must be finished before step Q can begin.\n" +
                    "Step X must be finished before step V can begin.\n" +
                    "Step Q must be finished before step U can begin.\n" +
                    "Step T must be finished before step K can begin.\n" +
                    "Step S must be finished before step B can begin.\n" +
                    "Step L must be finished before step C can begin.\n" +
                    "Step Q must be finished before step D can begin.\n" +
                    "Step E must be finished before step K can begin.\n" +
                    "Step N must be finished before step G can begin.\n" +
                    "Step L must be finished before step T can begin.\n" +
                    "Step E must be finished before step L can begin.\n" +
                    "Step A must be finished before step N can begin.\n" +
                    "Step V must be finished before step C can begin.\n" +
                    "Step D must be finished before step L can begin.\n" +
                    "Step O must be finished before step S can begin.\n" +
                    "Step V must be finished before step Y can begin.\n" +
                    "Step N must be finished before step T can begin.\n" +
                    "Step I must be finished before step H can begin.\n" +
                    "Step U must be finished before step N can begin.\n" +
                    "Step O must be finished before step Y can begin.\n" +
                    "Step J must be finished before step C can begin.\n" +
                    "Step Y must be finished before step C can begin.\n" +
                    "Step W must be finished before step A can begin.\n" +
                    "Step M must be finished before step C can begin.\n" +
                    "Step X must be finished before step E can begin.\n" +
                    "Step S must be finished before step J can begin.\n" +
                    "Step U must be finished before step C can begin.\n" +
                    "Step H must be finished before step K can begin.\n" +
                    "Step Q must be finished before step B can begin.\n" +
                    "Step E must be finished before step G can begin.\n" +
                    "Step N must be finished before step H can begin.\n" +
                    "Step I must be finished before step J can begin.\n" +
                    "Step P must be finished before step B can begin.\n" +
                    "Step Z must be finished before step T can begin.\n" +
                    "Step J must be finished before step M can begin.\n" +
                    "Step C must be finished before step G can begin.\n" +
                    "Step I must be finished before step B can begin.\n" +
                    "Step D must be finished before step G can begin.\n" +
                    "Step X must be finished before step T can begin.\n" +
                    "Step O must be finished before step F can begin.\n" +
                    "Step A must be finished before step Y can begin.\n" +
                    "Step S must be finished before step G can begin.\n" +
                    "Step X must be finished before step K can begin.\n" +
                    "Step L must be finished before step M can begin.\n" +
                    "Step A must be finished before step H can begin.\n" +
                    "Step D must be finished before step H can begin.\n" +
                    "Step U must be finished before step T can begin.\n" +
                    "Step B must be finished before step K can begin.\n" +
                    "Step S must be finished before step C can begin.\n" +
                    "Step W must be finished before step R can begin.\n" +
                    "Step M must be finished before step G can begin.\n" +
                    "Step M must be finished before step H can begin.\n" +
                    "Step J must be finished before step D can begin.\n" +
                    "Step W must be finished before step Y can begin.\n" +
                    "Step S must be finished before step Y can begin.\n" +
                    "Step A must be finished before step G can begin.\n" +
                    "Step P must be finished before step M can begin.\n" +
                    "Step C must be finished before step R can begin.\n" +
                    "Step Q must be finished before step Y can begin.\n" +
                    "Step O must be finished before step H can begin.\n" +
                    "Step O must be finished before step R can begin.\n" +
                    "Step Q must be finished before step M can begin.\n" +
                    "Step V must be finished before step B can begin.\n" +
                    "Step H must be finished before step G can begin.\n" +
                    "Step J must be finished before step V can begin.\n" +
                    "Step M must be finished before step R can begin.\n" +
                    "Step R must be finished before step G can begin.";

    public static void solve() {
        String[] steps = input.split("\n");
        Set<Character> allCharacters = new TreeSet<>();
        Map<Character, Set<Character>> dagEdges = new HashMap<>();
        Map<Character, Set<Character>> backEdges = new HashMap<>();

        for (String step : steps) {
            char parent = step.split(" ")[1].trim().charAt(0);
            char child = step.split(" ")[7].trim().charAt(0);
            allCharacters.add(parent);
            allCharacters.add(child);

            dagEdges.putIfAbsent(parent, new HashSet<>());
            backEdges.putIfAbsent(child, new HashSet<>());

            dagEdges.get(parent).add(child);
            backEdges.get(child).add(parent);
        }

        StringBuilder answer = new StringBuilder();

        // Topological Sort of the DAG, or atleast thats what I think I implemented
        while (!dagEdges.isEmpty()) {

            Set<Character> selectedChars = new TreeSet<>();
            dagEdges.forEach((parent, children) -> {
                if (!backEdges.containsKey(parent)) {
                    selectedChars.add(parent);
                }
            });

            char selectedChar = selectedChars.iterator().next();
            answer.append(selectedChar);
            allCharacters.remove(selectedChar);

            Set<Character> neighbours = dagEdges.get(selectedChar);
            dagEdges.remove(selectedChar);
            neighbours.forEach(n -> {
                backEdges.get(n).remove(selectedChar);
                if (backEdges.get(n).size() == 0) {
                    backEdges.remove(n);
                }
            });
        }

        allCharacters.forEach(ch -> answer.append(ch));
        System.out.println("Answer: " + answer.toString()); // IOFSJQDUWAPXELNVYZMHTBCRGK
    }

    public static void solvePart2() {
        final int maxWorkers = 5;

        String[] steps = input.split("\n");
        Set<Character> allCharacters = new TreeSet<>();
        Map<Character, Set<Character>> dagEdges = new HashMap<>();
        Map<Character, Set<Character>> backEdges = new HashMap<>();

        for (String step : steps) {
            char parent = step.split(" ")[1].trim().charAt(0);
            char child = step.split(" ")[7].trim().charAt(0);
            allCharacters.add(parent);
            allCharacters.add(child);

            dagEdges.putIfAbsent(parent, new HashSet<>());
            backEdges.putIfAbsent(child, new HashSet<>());

            dagEdges.get(parent).add(child);
            backEdges.get(child).add(parent);
        }

        int[] currentLengths = new int[maxWorkers];
        Set<Character> underProcessing = new LinkedHashSet<>();
        Map<Integer, Character> characterAtPosition = new HashMap<>();

        while (!dagEdges.isEmpty()) {
            var minValueWithIndices = getMinimumValueAndIndices(currentLengths);
            if (minValueWithIndices.isPresent()) {
                subtractValueFromArray(currentLengths, minValueWithIndices.get().left());
                for (int completedWorker : minValueWithIndices.get().right()) {
                    char completedChar = characterAtPosition.get(completedWorker);
                    allCharacters.remove(completedChar);
                    underProcessing.remove(completedChar);

                    // Update the dag edges
                    Set<Character> neighbours = dagEdges.get(completedChar);
                    dagEdges.remove(completedChar);
                    neighbours.forEach(n -> {
                        backEdges.get(n).remove(completedChar);
                        if (backEdges.get(n).size() == 0) {
                            backEdges.remove(n);
                        }
                    });
                }
            }

            List<Character> selectedChars = new ArrayList<>();
            dagEdges.forEach((parent, children) -> {
                if (!backEdges.containsKey(parent) && !underProcessing.contains(parent)) {
                    selectedChars.add(parent);
                }
            });
            selectedChars.sort(Comparator.naturalOrder());
            int charIndex = 0;

            for (int i = 0; i < maxWorkers && charIndex < selectedChars.size(); i++) {
                if (currentLengths[i] == 0) {
                    char selectedChar = selectedChars.get(charIndex++);
                    currentLengths[i] = selectedChar - 'A' + 61;
                    characterAtPosition.put(i, selectedChar);
                    underProcessing.add(selectedChar);
                }
            }
        }

        answer += (allCharacters.iterator().next() - 'A' + 61);
        System.out.println("Answer: " + answer); // 931
    }

    private static int answer = 0;

    private static Optional<Pair<Integer, List<Integer>>> getMinimumValueAndIndices(int[] currentLengths) {
        Map<Integer, List<Integer>> minValueIndicesMap = new TreeMap<>();
        for (int i = 0; i < currentLengths.length; i++) {
            if (currentLengths[i] != 0) {
                minValueIndicesMap.putIfAbsent(currentLengths[i], new ArrayList<>());
                minValueIndicesMap.get(currentLengths[i]).add(i);
            }
        }
        if (minValueIndicesMap.isEmpty()) {
            return Optional.empty();
        }
        var firstEntry = minValueIndicesMap.entrySet().iterator().next();
        return Optional.of(Pair.of(firstEntry.getKey(), firstEntry.getValue()));
    }

    private static void subtractValueFromArray(int[] currentLengths, int value) {
        for (int i = 0; i < currentLengths.length; i++) {
            if (currentLengths[i] != 0) {
                currentLengths[i] -= value;
            }
        }
        answer += value;
    }
}
