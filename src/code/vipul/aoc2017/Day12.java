package code.vipul.aoc2017;

import code.vipul.leetcode.graphs.DisjointSet;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_12;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day12 {

    private static String INPUT = """
            0 <-> 2
            1 <-> 1
            2 <-> 0, 3, 4
            3 <-> 2, 4
            4 <-> 2, 3, 6
            5 <-> 6
            6 <-> 4, 5
            """;

    public static void solve() {
        INPUT = DAY_12;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<Integer, List<Integer>> adj = new HashMap<>();
        int max = 0;
        for (String line : lines) {
            var parts = line.split(" <-> ");
            int src = Integer.parseInt(parts[0]);
            adj.putIfAbsent(src, new ArrayList<>());
            var dstr = parts[1].split(", ");
            max = Math.max(src, max);
            for (String ds : dstr) {
                int d = Integer.parseInt(ds);
                adj.putIfAbsent(d, new ArrayList<>());
                adj.get(src).add(d);
                adj.get(d).add(src);
                max = Math.max(d, max);
            }
        }

        DisjointSet dsu = new DisjointSet(max+1);
        for (int i = 0; i <= max; i++) {
            dsu.add(i);
        }

        for (var entry : adj.entrySet()) {
            int key = entry.getKey();
            for (int d : entry.getValue()) {
                dsu.union(key, d);
            }
        }

        int p1 = dsu.find(0);
        int count = 0;
        Set<Integer> groups = new HashSet<>();
        for (int i = 0; i <= max; i++) {
            if (dsu.find(i) == p1) {
                count++;
            }
            groups.add(dsu.find(i));
        }

        System.out.println("Part 1: " + count); // 239
        System.out.println("Part 2: " + groups.size()); // 215
    }
}
