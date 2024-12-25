package code.vipul.aoc2024;

import java.util.*;

import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2024/day/23
 */
public class Day23 {

    private static Set<String> maxClique = new HashSet<>();

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day23.class, false);

        Map<String, Set<String>> connections = new HashMap<>();
        Set<String> nodes = new HashSet<>();
        for (String line : lines) {
            var parts = line.split("-");
            connections.putIfAbsent(parts[0], new HashSet<>());
            connections.putIfAbsent(parts[1], new HashSet<>());

            connections.get(parts[0]).add(parts[1]);
            connections.get(parts[1]).add(parts[0]);
            nodes.add(parts[0]);
            nodes.add(parts[1]);
        }

        Set<Set<String>> unique = new HashSet<>();
        for (String node : nodes) {
            if (node.charAt(0) != 't') continue;

            Queue<String> q = new ArrayDeque<>();
            Queue<Integer> len = new ArrayDeque<>();
            Queue<Set<String>> path = new ArrayDeque<>();
            Set<String> p = new HashSet<>();
            p.add(node);

            q.add(node); len.add(1); path.add(p);

            while(!q.isEmpty()) {
                String n = q.remove();
                p = path.remove();
                int curlen = len.remove();
                if (curlen > 3) continue;

                for (var tnode : connections.get(n)) {
                    if (tnode.equals(node) && curlen == 3) {
                        unique.add(p);
                    } else {
                        q.add(tnode);
                        Set<String> tmp = new HashSet<>(p);
                        tmp.add(tnode);
                        len.add(curlen + 1);
                        path.add(tmp);
                    }
                }
            }
        }

        System.out.println("Part 1: " + unique.size()); // 893

        bronKerbosch(nodes, new HashSet<>(), new HashSet<>(), connections);
        System.out.println("Part 2: " +
                String.join(",", new TreeSet<>(maxClique))); // cw,dy,ef,iw,ji,jv,ka,ob,qv,ry,ua,wt,xz
    }

    // Finding maximal clique -> https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm
    static void bronKerbosch(Set<String> p, Set<String> r, Set<String> x, Map<String, Set<String>> connections) {
        if (x.isEmpty() && p.isEmpty() && r.size() > maxClique.size()) {
            maxClique = new HashSet<>();
            maxClique.addAll(r);
        }

        for (var v : new HashSet<>(p)) {
            Set<String> newR = new HashSet<>(r);
            newR.add(v);

            bronKerbosch(intersect(p, connections.get(v)), newR, intersect(x, connections.get(v)), connections);
            p.remove(v);
            x.add(v);
        }
    }

    static Set<String> intersect(Set<String> s1, Set<String> s2) {
        Set<String> in = new HashSet<>();
        for (var t1 : s1) {
            if (s2.contains(t1)) {
                in.add(t1);
            }
        }
        return in;
    }

}
