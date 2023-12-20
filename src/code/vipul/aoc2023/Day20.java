package code.vipul.aoc2023;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static code.vipul.aoc2023.inputs.Inputs.DAY_20;

/**
 * Created by vgaur created on 20/12/23
 */
public class Day20 {

    private static String INPUT = """
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output
            """;

    private static final String BROADCASTER = "broadcaster";
    private static List<String> broadcaster = new ArrayList<>();
    private static Map<String, Boolean> flipFlop = new HashMap<>();
    private static Map<String, String[]> flipFlopDest = new HashMap<>();
    private static Map<String, Map<String, Boolean>> conjunction = new HashMap<>();
    private static Map<String, String[]> conjunctionDest = new HashMap<>();
    private static String inputToRx = "";

    // false -> low, true -> high
    public static void solve() {
        parseInput(DAY_20);
        System.out.println(runSimulation(1000, false, "")); // 912199500

        parseInput(DAY_20);
        System.out.println(runSimulation(20000, true, inputToRx)); // 237878264003759
    }

    private static long runSimulation(int presses, boolean calcCycle, String highCheckModule) {
        int highcnt = 0, lowcnt = 0;
        Map<String, List<Integer>> cycles = new HashMap<>();
        for (int i = 0; i < presses; i++) {
            Queue<Pulse> pulses = new ArrayDeque<>();
            pulses.add(new Pulse("", BROADCASTER, false));

            while (!pulses.isEmpty()) {
                Pulse pulse = pulses.remove();
                String from = pulse.from;
                String module = pulse.to;
                boolean high = pulse.high;

                if (high) highcnt++;
                else lowcnt++;

                // For low input to 'rx', all inputs to "highCheckModule" must be high, so we are tracking those
                // so that we can calculate the cycle in which they turn high
                if (calcCycle && high && module.equals(highCheckModule)) {
                    cycles.putIfAbsent(from, new ArrayList<>());
                    cycles.get(from).add(i + 1);
                }

                if (module.equals("broadcaster")) {
                    for (String d : broadcaster) {
                        pulses.add(new Pulse(module, d, high));
                    }
                } else if (flipFlop.containsKey(module) && !high) { // flip flops ignore high pulse
                    boolean current = flipFlop.get(module);
                    flipFlop.put(module, !current); // flip-flopped
                    for (String d : flipFlopDest.get(module)) {
                        pulses.add(new Pulse(module, d, !current));
                    }
                } else if (conjunction.containsKey(module)) {
                    conjunction.get(module).put(from, high);
                    boolean allHigh = conjunction.get(module).values().stream().allMatch(h -> h);
                    for (String d : conjunctionDest.get(module)) {
                        pulses.add(new Pulse(module, d, !allHigh));
                    }
                }
            }
        }

        if (!calcCycle) {
            return (long) highcnt * lowcnt;
        } else {
            long lcmval = 1;
            for (List<Integer> num : cycles.values()) {
                lcmval = lcm(lcmval, num.get(1) - num.get(0));
            }
            return lcmval;
        }
    }

    private static void parseInput(String input) {
        List<String> lines = Arrays.stream(input.split("\n")).toList();

        broadcaster = new ArrayList<>();
        flipFlop = new HashMap<>();
        flipFlopDest = new HashMap<>();
        conjunction = new HashMap<>();
        conjunctionDest = new HashMap<>();
        for (String line : lines) {
            if (line.startsWith(BROADCASTER)) {
                broadcaster.addAll(List.of(line.split("-> ")[1].split(", ")));
            } else if (line.startsWith("%")) {
                var parts = line.split(" -> ");
                String module = parts[0].substring(1);
                String[] destination = parts[1].split(", ");
                flipFlopDest.put(module, destination);
                flipFlop.put(module, false);
            } else if (line.startsWith("&")) {
                var parts = line.split(" -> ");
                String module = parts[0].substring(1);
                String[] destination = parts[1].split(", ");
                conjunction.put(module, new HashMap<>());
                conjunctionDest.put(module, destination);
                for (String d : destination) {
                    if (d.equals("rx")) {
                        inputToRx = module;
                        break;
                    }
                }
            }
        }

        for (var entry : flipFlopDest.entrySet()) {
            String module = entry.getKey();
            String[] dest = entry.getValue();
            for (String d : dest) {
                if (conjunction.containsKey(d)) {
                    conjunction.get(d).put(module, false);
                }
            }
        }
    }

    private static long gcd(long a, long b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    record Pulse(String from, String to, boolean high) {
    }
}
