package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_13;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day13 {

    private static int id = 0;
    private static final Map<String, Integer> ids = new HashMap<>();

    private static String INPUT = """
            Alice would gain 54 happiness units by sitting next to Bob.
            Alice would lose 79 happiness units by sitting next to Carol.
            Alice would lose 2 happiness units by sitting next to David.
            Bob would gain 83 happiness units by sitting next to Alice.
            Bob would lose 7 happiness units by sitting next to Carol.
            Bob would lose 63 happiness units by sitting next to David.
            Carol would lose 62 happiness units by sitting next to Alice.
            Carol would gain 60 happiness units by sitting next to Bob.
            Carol would gain 55 happiness units by sitting next to David.
            David would gain 46 happiness units by sitting next to Alice.
            David would lose 7 happiness units by sitting next to Bob.
            David would gain 41 happiness units by sitting next to Carol.
            """;

    private static Map<Integer, Map<Integer, Integer>> connections;

    public static void solve() {
        INPUT = DAY_13;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        connections = new HashMap<>();

        for (String line : lines) {
            String source = line.split(" would ")[0];
            String dest = line.split(" happiness units by sitting next to ")[1];
            dest = dest.substring(0, dest.length()-1);

            int srcid = getId(source), destid = getId(dest);
            connections.putIfAbsent(srcid, new HashMap<>());

            line = line.split(" would ")[1];
            int happiness = Integer.parseInt(line.split(" happiness")[0].split(" ")[1]);
            if (line.startsWith("gain")) {
                connections.get(srcid).put(destid, happiness);
            } else {
                connections.get(srcid).put(destid, -happiness);
            }
        }

        int total = id;
        int answer = recurse(0, 1, total);
        System.out.println("Part 1: " + answer); // 668

        int self = id; id++;
        connections.put(self, new HashMap<>());
        for (int i = 0; i < total; i++) {
            connections.get(self).put(i, 0);
            connections.get(i).put(self, 0);
        }
        total = id;
        answer = recurse(0, 1, total);
        System.out.println("Part 2: " + answer); // 668
    }

    private static int recurse(int current, int taken, int total) {
        if (taken == ((1 << total) - 1)) {
            return connections.get(current).get(0) + connections.get(0).get(current);
        }

        int max = 0;
        for (int i = 0; i < total; i++) {
            if ((taken & (1 << i)) == 0) {
                int newTaken = taken | (1 << i);
                int curr = connections.get(current).get(i) + connections.get(i).get(current)
                        + recurse(i, newTaken, total);
                max = Math.max(curr, max);
            }
        }
        return max;
    }

    private static int getId(String str) {
        if (!ids.containsKey(str)) {
            ids.put(str, id);
            id++;
        }
        return ids.get(str);
    }
}
