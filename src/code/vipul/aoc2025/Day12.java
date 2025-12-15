package code.vipul.aoc2025;

import java.util.*;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2025/day/12
 */
public class Day12 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day12.class, false);

        int ans = 0;
        for (String line : lines) {
            if (!line.contains("x")) continue;
            String[] parts = line.split(": ");
            String[] dims = parts[0].split("x");
            String[] counts = parts[1].split(" ");

            int r = Integer.parseInt(dims[0]), c = Integer.parseInt(dims[1]);

            int area = r * c;
            int presents = 0;
            for (String cstr : counts) {
                presents += Integer.parseInt(cstr) * 9;
            }

            if (area >= presents) {
                ans++;
            }
        }

        System.out.println(ans); // 550
    }
}
