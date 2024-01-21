package code.vipul.aoc2015;

import java.util.*;
import java.util.stream.Stream;

import static code.vipul.aoc2015.inputs.Inputs.DAY_2;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day2 {

    private static String INPUT = """
            2x3x4
            """;

    public static void solve() {
        INPUT = DAY_2;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        long total = 0, total2 = 0;
        for (String line : lines) {
            var parts = line.split("x");
            long l1 = Long.parseLong(parts[0]), l2 = Long.parseLong(parts[1]), l3 = Long.parseLong(parts[2]);

            long area = (2 * l1 * l2 + 2 * l2 * l3 + 2 * l1 * l3)
                    + Math.min(l1 * l2, Math.min(l2 * l3, l1 * l3));
            total += area;

            total2 += (l1 * l2 * l3);
            List<Long> sides = Stream.of(l1, l2, l3).sorted().toList();
            total2 += 2 * (sides.get(0) + sides.get(1));
        }

        System.out.println("Part 1: " + total); // 1598415
        System.out.println("Part 2: " + total2); // 3812909
    }
}
