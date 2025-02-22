package code.vipul.aoc2024;


import code.vipul.utils.AoCInputReader;

import java.util.List;


/**
 * https://adventofcode.com/2024/day/13
 */
public class Day13 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day13.class, false);

        long total = 0, total2 = 0;
        for (int i = 0; i < lines.size(); i += 4) {
            var aparts = lines.get(i).split(": ")[1].split(", ");
            int ax = Integer.parseInt(aparts[0].split("\\+")[1]);
            int ay = Integer.parseInt(aparts[1].split("\\+")[1]);

            var bparts = lines.get(i + 1).split(": ")[1].split(", ");
            int bx = Integer.parseInt(bparts[0].split("\\+")[1]);
            int by = Integer.parseInt(bparts[1].split("\\+")[1]);

            var prizeparts = lines.get(i + 2).split(": ")[1].split(", ");
            int px = Integer.parseInt(prizeparts[0].split("=")[1]);
            int py = Integer.parseInt(prizeparts[1].split("=")[1]);

            total += getCost(ax, ay, bx, by, px, py);

            long p1 = 10000000000000L + px;
            long p2 = 10000000000000L + py;
            total2 += getCost(ax, ay, bx, by, p1, p2);
        }

        System.out.println("Part 1: " + total);  // 37686
        System.out.println("Part 2: " + total2); // 77204516023437
    }

    // The equations to solve are:
    // ax*k2 + bx*k2 = p1, ay*k1 + by*k2 = p2, solving for k1 & k2
    // These equations will either have one or no solutions
    // simplifying this by substitution gives us
    // k2 = (p1 - ax*k1) / bx, k1 = (p2*bx - by*p1) / (bx*ay - by*ax)
    // Solve for k1 for an exact int value, and then substitute for solving k2 for an exact int value
    private static long getCost(int ax, int ay, int bx, int by, long p1, long p2) {
        long cost = 0;
        long denomk1 = bx * ay - by * ax;
        if (denomk1 != 0 && bx != 0) { // if these are zero, the values would be undefined
            long k1num = p2 * bx - by * p1;
            if (k1num % denomk1 == 0) { // exact int check
                long k1 = k1num / denomk1;
                long k2num = p1 - (ax * k1);
                if (k2num % bx == 0) { // exact int check
                    long k2 = k2num / bx;
                    cost = k1 * 3 + k2;
                }
            }
        }
        return cost;
    }
}
