package code.vipul.aoc2015;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static code.vipul.aoc2015.inputs.Inputs.DAY_9;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day9 {

    private static int id = 0;
    private static final Map<String, Integer> ids = new HashMap<>();
    private static Map<Integer, Map<Integer, Integer>> connections;

    public static void solve() {
        List<String> lines = Arrays.stream(DAY_9.split("\n")).toList();
        connections = new HashMap<>();

        for (String line : lines) {
            var parts = line.split(" = ");
            int distance = Integer.parseInt(parts[1]);
            var fromTo = parts[0].split(" to ");
            int fromId = getId(fromTo[0]), toId = getId(fromTo[1]);

            connections.putIfAbsent(fromId, new HashMap<>());
            connections.putIfAbsent(toId, new HashMap<>());
            connections.get(fromId).put(toId, distance);
            connections.get(toId).put(fromId, distance);
        }

        int total = id;
        System.out.println("Part 1: " + findLength(total, Integer.MAX_VALUE, Math::min)); // 251
        System.out.println("Part 2: " + findLength(total, 0, Math::max)); // 898
    }

    private static int findLength(int total, int initialValue, BiFunction<Integer, Integer, Integer> aggregator) {
        int allSelected = (1 << total) - 1;
        int answer = initialValue;
        for (int i = 0; i < total; i++) {
            int len = recurse(i, 1 << i, allSelected, initialValue, aggregator);
            answer = aggregator.apply(answer, len);
        }
        return answer;
    }

    private static int recurse(int current, int selected, int allSelected,
                               int initialValue, BiFunction<Integer, Integer, Integer> aggregator) {
        if (selected == allSelected) {
            return 0;
        }
        int answer = initialValue;

        for (var nbors : connections.get(current).entrySet()) {
            int nbor = nbors.getKey(), len = nbors.getValue();
            if ((selected & (1 << nbor)) == 0) {
                int curlen = len + recurse(nbor, selected | (1 << nbor),
                        allSelected, initialValue, aggregator);
                answer = aggregator.apply(answer, curlen);
            }
        }
        return answer;
    }

    private static int getId(String dest) {
        if (!ids.containsKey(dest)) {
            ids.put(dest, id);
            id++;
        }
        return ids.get(dest);
    };
}
