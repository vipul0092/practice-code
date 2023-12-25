package code.vipul.aoc2023;

import code.vipul.graph.Edge;
import code.vipul.leetcode.graphs.DisjointSet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static code.vipul.aoc2023.inputs.Inputs.DAY_25;
import static code.vipul.graph.MinCutKarger.minCutKarger;

/**
 * Created by vgaur created on 25/12/23
 */
public class Day25 {

    static Map<Integer, Set<Integer>> adj;
    private static String INPUT = """
            jqt: rhn xhk nvd
            rsh: frs pzl lsr
            xhk: hfx
            cmg: qnr nvd lhk bvb
            rhn: xhk bvb hfx
            bvb: xhk hfx
            pzl: lsr hfx nvd
            qnr: nvd
            ntq: jqt hfx bvb xhk
            nvd: lhk
            lsr: lhk
            rzs: qnr cmg lsr rsh
            frs: qnr lhk lsr
            """;

    public static void solve() {
        INPUT = DAY_25;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        adj = new HashMap<>();
        Map<String, Integer> ids = new HashMap<>();
        int id = 0;

        List<Edge> edges = new ArrayList<>();
        for (String line : lines) {
            var parts = line.split(": ");
            var src = parts[0];
            var dest = parts[1].split(" ");
            if (!ids.containsKey(src)) {
                ids.put(src, id++);
            }
            adj.putIfAbsent(ids.get(src), new HashSet<>());

            for (String d : dest) {
                if (!ids.containsKey(d)) {
                    ids.put(d, id++);
                }
                adj.get(ids.get(src)).add(ids.get(d));
                adj.putIfAbsent(ids.get(d), new HashSet<>());
                adj.get(ids.get(d)).add(ids.get(src));
                edges.add(new Edge(ids.get(parts[0]), ids.get(d)));
            }
        }

        Set<Edge> mincut;
        do {
            mincut = minCutKarger(edges.toArray(new Edge[0]), id);
        } while (mincut.size() != 3);

        for (Edge remove : mincut) {
            adj.get(remove.u()).remove(remove.v());
            adj.get(remove.v()).remove(remove.u());
        }

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(0);
        visited.add(0);

        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int n : adj.getOrDefault(v, Set.of())) {
                if (!visited.contains(n)) {
                    queue.add(n);
                    visited.add(n);
                }
            }
        }

        int ans = visited.size() * (id - visited.size());
        System.out.println("Answer: " + ans); // 547080
    }
}
