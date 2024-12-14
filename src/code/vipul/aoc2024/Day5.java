package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.*;

/**
 * https://adventofcode.com/2024/day/5
 */
public class Day5 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day5.class, false);

        Map<Integer, Set<Integer>> before = new HashMap<>();
        Map<Integer, Set<Integer>> after = new HashMap<>();

        int i = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                break;
            }
            String[] parts = line.split("\\|");
            int n1 = Integer.parseInt(parts[0]), n2 = Integer.parseInt(parts[1]);

            before.putIfAbsent(n2, new HashSet<>());
            before.get(n2).add(n1);
            after.putIfAbsent(n1, new HashSet<>());
            after.get(n1).add(n2);
            i++;
        }

        int sum1 = 0, sum2 = 0;
        for (int j = i + 1; j < lines.size(); j++) {
            var numbers = Arrays.stream(lines.get(j).split(",")).map(s -> Integer.parseInt(s)).toList();
            Map<Integer, List<Integer>> graph = new HashMap<>();
            int[] indegree = new int[100];
            for (int k = 0; k < numbers.size(); k++) {
                int n1 = numbers.get(k);
                for (int l = k + 1; l < numbers.size(); l++) {
                    int n2 = numbers.get(l);
                    // n2 -> n1
                    if (before.containsKey(n1) && before.get(n1).contains(n2)) {
                        indegree[n1]++;
                        graph.putIfAbsent(n2, new ArrayList<>());
                        graph.get(n2).add(n1);
                    }
                    // n1 -> n2
                    if (after.containsKey(n1) && after.get(n1).contains(n2)) {
                        indegree[n2]++;
                        graph.putIfAbsent(n1, new ArrayList<>());
                        graph.get(n1).add(n2);
                    }
                }
            }

            var sorted = topologicalSort(graph, indegree);
            int middle = sorted.get(sorted.size() / 2);

            if (sorted.equals(numbers)) {
                sum1 += middle;
            } else {
                sum2 += middle;
            }
        }

        System.out.println("Part 1: " + sum1); // 7024
        System.out.println("Part 2: " + sum2); // 4151
    }

    private static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph, int[] indegree) {
        List<Integer> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (Integer key : graph.keySet()) {
            if (indegree[key] == 0) {
                stack.add(key);
            }
        }
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            visited.add(curr);

            for (Integer prev : graph.getOrDefault(curr, new ArrayList<>())) {
                indegree[prev]--;
                if (indegree[prev] == 0) {
                    stack.add(prev);
                }
            }
        }
        return visited;
    }
}
