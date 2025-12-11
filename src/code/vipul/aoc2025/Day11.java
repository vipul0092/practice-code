package code.vipul.aoc2025;

import code.vipul.utils.AoCInputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://adventofcode.com/2025/day/11
 */
public class Day11 {

    record Result(long neither, long fftonly, long daconly, long fftdac) {}
    private static Map<String, Result> cache;

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day11.class, false);

        Map<String, List<String>> adj = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(": ");
            String from = parts[0];
            String[] tos = parts[1].split(" ");

            adj.putIfAbsent(from, new ArrayList<>());
            for (String to : tos) {
                adj.get(from).add(to);
            }
        }

        int val = countPaths(adj, "you", "");
        System.out.println("Part 1: " + val); // 683

        cache = new HashMap<>();
        Result res = gatherResults(adj, "svr", "");
        System.out.println("Part 2: " + res.fftdac); // 533996779677200
    }

    private static int countPaths(Map<String, List<String>> adj, String node, String prevNode) {
        if (node.equals("out")) {
            return 1;
        }
        int count = 0;
        for (String neighbor : adj.getOrDefault(node, List.of())) {
            if (!neighbor.equals(prevNode)) {
                count += countPaths(adj, neighbor, node);
            }
        }
        return count;
    }

    private static Result gatherResults(Map<String, List<String>> adj, String node, String prev) {
        if (node.equals("out")) {
            // Base case, put it under the neither category
            return new Result(1, 0, 0, 0);
        }
        String key = node + prev;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        // Start with all zeros
        Result res = new Result(0, 0, 0, 0);
        for (String neighbor : adj.getOrDefault(node, List.of())) {
            if (!neighbor.equals(prev)) {
                Result subresult = gatherResults(adj, neighbor, node);
                if (neighbor.equals("fft")) {
                    res = new Result(
                            // neither = current neither + subresult neither
                            res.neither + subresult.neither,
                            // fftonly = current fftonly + subresult fftonly + subresult neither (because right now we are  on fft)
                            res.fftonly + subresult.fftonly + subresult.neither,
                            // daconly = current daconly + subresult daconly
                            res.daconly + subresult.daconly,
                            // fftdac = current fftdac + subresult fftdac + subresult.daconly (because right now we are on fft)
                            res.fftdac + subresult.fftdac + subresult.daconly
                    );
                } else if (neighbor.equals("dac")) {
                    res = new Result(
                            // neither = current neither + subresult neither
                            res.neither + subresult.neither,
                            // fftonly = current fftonly + subresult fftonly
                            res.fftonly + subresult.fftonly,
                            // daconly = current daconly + subresult daconly + subresult neither (because right now we are on dac)
                            res.daconly + subresult.daconly + subresult.neither,
                            // fftdac = current fftdac + subresult fftdac + subresult.fftonly (because right now we are on dac)
                            res.fftdac + subresult.fftdac + subresult.fftonly
                    );
                } else {
                    // If neither fft or dac, just add up the counts normally
                    res = new Result(
                            res.neither + subresult.neither,
                            res.fftonly + subresult.fftonly,
                            res.daconly + subresult.daconly,
                            res.fftdac + subresult.fftdac
                    );
                }
            }
        }
        cache.put(key, res);
        return res;
    }
}
