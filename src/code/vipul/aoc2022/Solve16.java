package code.vipul.aoc2022;

import code.vipul.Pair;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 16/12/22
 */
public class Solve16 {

    private static final String INPUT = "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB\n" +
            "Valve BB has flow rate=13; tunnels lead to valves CC, AA\n" +
            "Valve CC has flow rate=2; tunnels lead to valves DD, BB\n" +
            "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE\n" +
            "Valve EE has flow rate=3; tunnels lead to valves FF, DD\n" +
            "Valve FF has flow rate=0; tunnels lead to valves EE, GG\n" +
            "Valve GG has flow rate=0; tunnels lead to valves FF, HH\n" +
            "Valve HH has flow rate=22; tunnel leads to valve GG\n" +
            "Valve II has flow rate=0; tunnels lead to valves AA, JJ\n" +
            "Valve JJ has flow rate=21; tunnel leads to valve II";

    private static Map<String, Integer> flow;
    private static Map<String, Map<String, Integer>> connections;
    private static Map<String, Integer> ids;
    private static Set<String> zeroValves;
    private static List<String> nonzeroValves;
    private static long fullOpen;

    private static Map<String, Map<String, Integer>> leastDistance;
    private static Map<Pair<String, Pair<Long, Integer>>, Integer> stateCache = new HashMap<>();
    private static Map<Pair<Pair<String, Long>, Pair<Long, Integer>>, Integer> stateCache2 = new HashMap<>();

    public static void solve() {
        parse(Inputs.INPUT_16);
        int MAX_TIME = 30;

        final long startTime = System.nanoTime();
        int val = traverseValves("AA", 0, MAX_TIME, false);
        final long duration = (System.nanoTime() - startTime) / 1000000;

        System.out.println("Runtime(in ms): " + duration);
        System.out.println(val);
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_16);
        int MAX_TIME = 30;
        int part2 = 0;
        int max = 1 << nonzeroValves.size();
        int maxBitSet = max - 1;

        long startTime = System.nanoTime();

        // Populate all the possible variations
        for (int i = 0; i < max; i++) {
            traverseValves("AA", i, MAX_TIME, false);
        }
        long duration = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Runtime(in ms): " + duration);
        startTime = System.nanoTime();

        // Iterator over all item combinations where the selected items are disjoint sets
        for (int i = 1; i < max; i++) {
            for (int j = 1; j < max; j++) {
                if ((i & j) != j) {
                    continue;
                }

                long elephantSelection = (i & ~j);
                long usSelection = j;
                int a = traverseValves("AA", maxBitSet & ~elephantSelection,
                        26, false);

                int b = traverseValves("AA", maxBitSet & ~usSelection,
                        26, false);

                // part2 = Math.max(part2, a + b);
                if (a + b > part2) {
                    System.out.println("m:  " + usSelection + ", n " + elephantSelection + ", value: " + (a + b));
                    part2 = a + b;
                }
            }
        }
        duration = (System.nanoTime() - startTime) / 1000000;

        System.out.println("Runtime(in ms): " + duration);
        System.out.println(part2);
    }

    private static int traverseValves(String valve, long valvesAlreadyOpened, int remainingTime, boolean nonzeroValve) {
        Pair<String, Pair<Long, Integer>> key = Pair.of(valve, Pair.of(valvesAlreadyOpened, remainingTime));
        if (stateCache.containsKey(key)) {
            return stateCache.get(key);
        }

        if (remainingTime <= 0) {
            return 0;
        }

        int val = 0;
        Map<String, Integer> distanceMap = leastDistance.get(valve);

        boolean wentSomewhere = false;
        boolean isValveOpen = nonzeroValve && isBitSet(valvesAlreadyOpened, ids.get(valve));
        long valveOpenBitset = nonzeroValve ? setBit(valvesAlreadyOpened, ids.get(valve)) : valvesAlreadyOpened;
        for (var e : distanceMap.entrySet()) {
            String valveToGo = e.getKey();
            Integer dis = e.getValue();
            if (!isBitSet(valvesAlreadyOpened, ids.get(valveToGo))) {
                wentSomewhere = true;
                if (nonzeroValve && !isValveOpen) {
                    val = Math.max(val, (flow.get(valve) * (remainingTime - 1)) +
                            (remainingTime - 1 - dis > 0 ? traverseValves(valveToGo, valveOpenBitset,
                                    remainingTime - 1 - dis, true) : 0));
                }
                val = Math.max(val, (remainingTime - dis > 0 ? traverseValves(valveToGo, valvesAlreadyOpened,
                        remainingTime - dis, true) : 0));
            }
        }

        if (nonzeroValve && !wentSomewhere && !isBitSet(valvesAlreadyOpened, ids.get(valve))) {
            val = flow.get(valve) * (remainingTime - 1);
        }

        stateCache.put(key, val);
        return val;
    }

    private static Map<String, Integer> bfs(String start) {
        Map<String, Integer> distanceMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        visited.add(start);
        distanceMap.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.remove();
            for (String n : connections.get(current).keySet()) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    distanceMap.put(n, distanceMap.get(current) + 1);
                    queue.add(n);
                }
            }
        }
        for (String s : zeroValves) {
            distanceMap.remove(s);
        }
        distanceMap.remove(start);
        return distanceMap;
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        flow = new LinkedHashMap<>();
        connections = new LinkedHashMap<>();
        int currentId = 0;
        ids = new LinkedHashMap<>();
        nonzeroValves = new ArrayList<>();
        leastDistance = new LinkedHashMap<>();
        zeroValves = new HashSet<>();

        fullOpen = 0;
        for (String in : inputs) {
            String[] parts = in.split(" has flow rate=");
            String source = parts[0].split(" ")[1];
            int rate = Integer.parseInt(parts[1].split(";")[0]);
            Map<String, Integer> dest;
            if (parts[1].contains("tunnels lead to valves")) {
                dest = Arrays.stream(parts[1].split("; tunnels lead to valves ")[1].split(", "))
                        .collect(Collectors.toMap(s -> s, s -> 1));
            } else {
                dest = Arrays.stream(parts[1].split("; tunnel leads to valve ")[1].split(", "))
                        .collect(Collectors.toMap(s -> s, s -> 1));
            }

            flow.put(source, rate);
            connections.put(source, dest);
            if (rate > 0) {
                ids.put(source, currentId++);
                fullOpen = setBit(fullOpen, ids.get(source));
                nonzeroValves.add(source);
            } else {
                zeroValves.add(source);
            }
        }

        for (String valve : nonzeroValves) {
            leastDistance.put(valve, bfs(valve));
        }
        leastDistance.put("AA", bfs("AA"));
    }


    private static boolean isBitSet(long bitSet, int bitPos) {
        return (bitSet & (1L << bitPos)) != 0;
    }

    private static long setBit(long bitSet, int bitPos) {
        return (bitSet | (1L << bitPos));
    }
}
