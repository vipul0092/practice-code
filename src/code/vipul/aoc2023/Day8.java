package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static code.vipul.aoc2023.inputs.Inputs.DAY_8;

/**
 * Created by vgaur created on 08/12/23
 */
public class Day8 {

    public static void solve() {
        List<String> lines = Arrays.stream(DAY_8.split("\n")).toList();

        Map<String, String[]> paths = new HashMap<>();
        String directions = lines.get(0);
        Set<String> starts = new HashSet<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.substring(0, line.length() - 1).split(" = \\(");
            String src = parts[0];
            String[] dest = parts[1].split(", ");
            if (src.endsWith("A")) {
                starts.add(src);
            }
            paths.put(src, dest);
        }
        System.out.println("Part 1: " + getCount((curr) -> !curr.equals("ZZZ"), "AAA", directions, paths));

        List<Long> counts = new ArrayList<>();
        for (String start : starts) {
            counts.add((long) getCount((curr) -> !curr.endsWith("Z"), start, directions, paths));
        }
        System.out.println("Part 2: " + lcm(counts));
    }

    private static int getCount(Predicate<String> loopCheck, String start, String directions,
                                Map<String, String[]> paths) {
        int dir = 0, steps = 0;
        String current = start;
        while (loopCheck.test(current)) {
            if (dir == directions.length()) {
                dir = 0;
            }
            char direction = directions.charAt(dir);
            String[] go = paths.get(current);
            current = direction == 'R' ? go[1] : go[0];
            dir++;
            steps++;
        }
        return steps;
    }

    private static long lcm(List<Long> numbers) {
        return numbers.stream().reduce(1L, (x, y) -> (x * y) / gcd(x, y));
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
