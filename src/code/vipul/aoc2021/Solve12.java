package code.vipul.aoc2021;

import code.vipul.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 31/12/22
 * https://adventofcode.com/2021/day/12
 */
public class Solve12 {

    private static final String INPUT = "fs-end\n" +
            "he-DX\n" +
            "fs-he\n" +
            "start-DX\n" +
            "pj-DX\n" +
            "end-zg\n" +
            "zg-sl\n" +
            "zg-pj\n" +
            "pj-he\n" +
            "RW-he\n" +
            "fs-DX\n" +
            "pj-RW\n" +
            "zg-RW\n" +
            "start-pj\n" +
            "he-WI\n" +
            "zg-he\n" +
            "pj-fs\n" +
            "start-RW";
    private static Map<String, Set<String>> links;
    private static Map<Pair<Pair<String, Boolean>, Map<String, Integer>>, Integer> cache;

    public static void solve() {
        parse(Inputs.INPUT_12);

        Map<String, Integer> visited = new HashMap<>();
        visited.put("start", 1);
        System.out.println(dfs("start", visited, true)); //4720
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_12);

        Map<String, Integer> visited = new HashMap<>();
        visited.put("start", 2);
        System.out.println(dfs("start", visited, false)); //147848
    }

    private static int dfs(String node, Map<String, Integer> visited, boolean hasVisitedSmallOnce) {
        var key = Pair.of(Pair.of(node, hasVisitedSmallOnce), visited);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (node.equals("end")) {
            return 1;
        }

        int count = 0;
        for (String n : links.get(node)) {
            if ((n.charAt(0) >= 'A' && n.charAt(0) <= 'Z') || n.equals("end")) {
                count += dfs(n, visited, hasVisitedSmallOnce);
            } else if (n.charAt(0) >= 'a' && n.charAt(0) <= 'z' && !visited.containsKey(n)) {
                Map<String, Integer> newVisited = new HashMap<>(visited);
                newVisited.putIfAbsent(n, 0);
                newVisited.put(n, newVisited.get(n) + 1);
                count += dfs(n, newVisited, hasVisitedSmallOnce);
            } else if (n.charAt(0) >= 'a' && n.charAt(0) <= 'z'
                    && visited.containsKey(n) && visited.get(n) < 2 && !hasVisitedSmallOnce) {
                Map<String, Integer> newVisited = new HashMap<>(visited);
                newVisited.putIfAbsent(n, 0);
                newVisited.put(n, newVisited.get(n) + 1);
                count += dfs(n, newVisited, true);
            }
        }
        cache.put(key, count);
        return count;
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        links = new HashMap<>();
        for (String in : inputs) {
            String[] parts = in.split("-");
            links.putIfAbsent(parts[0], new HashSet<>());
            links.putIfAbsent(parts[1], new HashSet<>());

            links.get(parts[0]).add(parts[1]);
            links.get(parts[1]).add(parts[0]);
        }
        cache = new HashMap<>();
    }
}
