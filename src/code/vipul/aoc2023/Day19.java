package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static code.vipul.aoc2023.inputs.Inputs.DAY_19;

/**
 * Created by vgaur created on 19/12/23
 */
public class Day19 {

    private static String INPUT = """
            px{a<2006:qkq,m>2090:A,rfg}
            pv{a>1716:R,A}
            lnx{m>1548:A,A}
            rfg{s<537:gd,x>2440:R,A}
            qs{s>3448:A,lnx}
            qkq{x<1416:A,crn}
            crn{x>2662:A,R}
            in{s<1351:px,qqz}
            qqz{s>2770:qs,m<1801:hdj,R}
            gd{a>3333:R,R}
            hdj{m>838:A,pv}
                        
            {x=787,m=2655,a=1222,s=2876}
            {x=1679,m=44,a=2067,s=496}
            {x=2036,m=264,a=79,s=2244}
            {x=2461,m=1339,a=466,s=291}
            {x=2127,m=1623,a=2188,s=1013}
            """;

    public static void solve() {
        INPUT = DAY_19;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<String, Workflow[]> workflows = new HashMap<>();
        List<Part> allparts = new ArrayList<>();

        boolean partstart = false;
        for (String line : lines) {
            if (line.isEmpty()) {
                partstart = true;
            } else if (!partstart) {
                Workflow.populate(line, workflows);
            } else {
                allparts.add(Part.parse(line));
            }
        }

        int sum = 0;
        for (Part part : allparts) {
            Map<Character, Range> ranges = new HashMap<>();
            overrideRange(ranges, 'x', part.x, part.x);
            overrideRange(ranges, 'm', part.m, part.m);
            overrideRange(ranges, 'a', part.a, part.a);
            overrideRange(ranges, 's', part.s, part.s);
            long ways = evaluateWays(workflows, "in", ranges);

            if (ways > 0) {
                sum += part.total();
            }
        }

        Map<Character, Range> ranges = new HashMap<>();
        overrideRange(ranges, 'x', 1, 4000);
        overrideRange(ranges, 'm', 1, 4000);
        overrideRange(ranges, 'a', 1, 4000);
        overrideRange(ranges, 's', 1, 4000);
        long ans2 = evaluateWays(workflows, "in", ranges);

        System.out.println("Part 1: " + sum); // 330820
        System.out.println("Part 2: " + ans2); // 123972546935551
    }

    private static long evaluateWays(Map<String, Workflow[]> allWorkflows, String workflow,
                                     Map<Character, Range> ranges) {
        if (!allWorkflows.containsKey(workflow)) {
            return workflow.equals("A") ? getAllWays(ranges) : 0;
        }
        Workflow[] workflows = allWorkflows.get(workflow);

        long sum = 0;
        for (Workflow w : workflows) {
            if (w.isComparing()) {
                char sign = w.sign;
                char prop = w.property;
                int value = w.value;
                String dest = w.dest;
                if (w.sign == '>') {
                    Range r = ranges.get(prop);
                    if (r.end <= value) {
                        continue;
                    } else if (r.start > value) {
                        sum += evaluateWays(allWorkflows, dest, ranges);
                        break;
                    } else {
                        Map<Character, Range> newRange =
                                overrideRange(new HashMap<>(ranges), prop, value + 1, ranges.get(prop).end);
                        sum += evaluateWays(allWorkflows, dest, newRange);

                        overrideRange(ranges, prop, ranges.get(prop).start, value);
                    }
                } else if (sign == '<') {
                    Range r = ranges.get(prop);
                    if (r.start >= value) {
                        continue;
                    } else if (r.end < value) {
                        sum += evaluateWays(allWorkflows, dest, ranges);
                        break;
                    } else {
                        Map<Character, Range> newRange =
                                overrideRange(new HashMap<>(ranges), prop, ranges.get(prop).start, value - 1);
                        sum += evaluateWays(allWorkflows, dest, newRange);

                        overrideRange(ranges, prop, value, ranges.get(prop).end);
                    }
                }
            } else {
                if (w.isTerminating()) {
                    sum += (w.isAccepting() ? getAllWays(ranges) : 0);
                } else {
                    sum += evaluateWays(allWorkflows, w.dest, ranges);
                }
                break;
            }
        }
        return sum;
    }

    private static Map<Character, Range> overrideRange(Map<Character, Range> ranges, char prop, int start, int end) {
        ranges.put(prop, new Range(start, end));
        return ranges;
    }

    private static long getAllWays(Map<Character, Range> ranges) {
        return ranges.values().stream().mapToLong(Range::count).reduce(1L, (v1, v2) -> v1 * v2);
    }

    record Part(int x, int m, int a, int s) {
        public static Part parse(String line) {
            line = line.substring(1, line.length() - 1);
            var parts = line.split(",");
            int x = Integer.parseInt(parts[0].substring(2));
            int m = Integer.parseInt(parts[1].substring(2));
            int a = Integer.parseInt(parts[2].substring(2));
            int s = Integer.parseInt(parts[3].substring(2));
            return new Part(x, m, a, s);
        }

        public int total() {
            return x + m + a + s;
        }
    }

    record Workflow(String name, char property, char sign, int value, String dest) {
        public static void populate(String line, Map<String, Workflow[]> workflows) {
            var parts = line.split("\\{");
            String name = parts[0];
            parts[1] = parts[1].substring(0, parts[1].length() - 1);
            String[] ops = parts[1].split(",");
            workflows.put(name, new Workflow[ops.length]);

            for (int i = 0; i < ops.length; i++) {
                var op = ops[i];
                if (op.contains(">") || op.contains("<")) {
                    parts = op.split(":");
                    String dest = parts[1];
                    char sign = parts[0].charAt(1);
                    char prop = parts[0].charAt(0);
                    int value = Integer.parseInt(parts[0].substring(2));
                    workflows.get(name)[i] = new Workflow(name, prop, sign, value, dest);
                } else {
                    workflows.get(name)[i] = new Workflow(name, '\0', '\0', -1, op);
                }
            }
        }

        public boolean isTerminating() {
            return name.length() == 1;
        }

        public boolean isAccepting() {
            return name.charAt(0) == 'A';
        }

        public boolean isComparing() {
            return sign != '\0';
        }
    }

    record Range(int start, int end) {
        public long count() {
            return end - start + 1;
        }
    }
}
