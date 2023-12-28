package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_7;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day7 {

    private static String INPUT = """
            pbga (66)
            xhth (57)
            ebii (61)
            havc (66)
            ktlj (57)
            fwft (72) -> ktlj, cntj, xhth
            qoyq (66)
            padx (45) -> pbga, havc, qoyq
            tknk (41) -> ugml, padx, fwft
            jptl (61)
            ugml (68) -> gyxo, ebii, jptl
            gyxo (61)
            cntj (57)
            """;

    private static int answer2 = -1;

    public static void solve() {
        INPUT = DAY_7;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> inputs = new HashMap<>();
        Map<String, Integer> weights = new HashMap<>();
        for (String line : lines) {
            String[] dest = new String[0];
            String src = line;
            if (line.contains(" -> ")) {
                var parts = line.split(" -> ");
                dest = parts[1].split(", ");
                src = parts[0];
            }

            var parts = src.split(" ");
            src = parts[0];
            int len = Integer.parseInt(parts[1].substring(1, parts[1].length() - 1));
            weights.put(src, len);
            inputs.merge(src, 0, Integer::sum);
            for (String d : dest) {
                adj.putIfAbsent(src, new ArrayList<>());
                adj.get(src).add(d);
                inputs.merge(d, 1, Integer::sum);
            }
        }

        String ans = "";
        for (var e : inputs.entrySet()) {
            if (e.getValue() == 0) {
                ans = e.getKey();
                break;
            }
        }

        System.out.println("Part 1: " + ans); // xegshds
        try {
            dfs(ans, adj, weights);
        } catch (RuntimeException r) {
            // expected
        }
        System.out.println("Part 2: " + answer2); // 299
    }


    private static Integer dfs(String current, Map<String, List<String>> adj, Map<String, Integer> weights) {
        int sum = weights.get(current);
        Map<Integer, Integer> values = new HashMap<>();
        Map<Integer, String> from = new HashMap<>();
        for (String n : adj.getOrDefault(current, List.of())) {
            int w = dfs(n, adj, weights);
            sum += w;
            values.merge(w, 1, Integer::sum);
            from.put(w, n);
        }
        if (values.size() == 2) {
            var iter = values.keySet().iterator();
            int w1 = iter.next(), w2 = iter.next();
            int c1 = values.get(w1), c2 = values.get(w2);
            if (c1 > c2) { //c2 is mismatched
                int d = w1 - w2;
                answer2 = weights.get(from.get(w2)) + d;
            } else { // c1 is mismatched
                int d = w2 - w1;
                answer2 = weights.get(from.get(w1)) + d;
            }
            throw new RuntimeException("STOP THE COUNT!"); // Since there is just one mismatch, stop the count
        }
        return sum;
    }
}
