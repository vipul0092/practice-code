package code.vipul.aoc2018;

import static code.vipul.aoc2018.Inputs2.DAY_23;

/**
 * https://adventofcode.com/2018/day/23
 */
public class Solve23 {

    private static final String INPUT = "pos=<0,0,0>, r=4\n" +
            "pos=<1,0,0>, r=1\n" +
            "pos=<4,0,0>, r=3\n" +
            "pos=<0,2,0>, r=1\n" +
            "pos=<0,5,0>, r=3\n" +
            "pos=<0,0,3>, r=1\n" +
            "pos=<1,1,1>, r=1\n" +
            "pos=<1,1,2>, r=1\n" +
            "pos=<1,3,1>, r=1";

    public static void solve() {
        String[] lines = DAY_23.split("\n");

        int x = -1, y = -1, z = -1;
        int foundRadius = 0;
        for (String line : lines) {
            String[] parts = line.split(">, r=");
            int r = Integer.parseInt(parts[1]);
            String[] xyz = parts[0].substring(5).split(",");
            if (r > foundRadius) {
                foundRadius = r;
                x = Integer.parseInt(xyz[0]);
                y = Integer.parseInt(xyz[1]);
                z = Integer.parseInt(xyz[2]);
            }
        }

        int count = 1;
        for (String line : lines) {
            String[] parts = line.split(">, r=");
            int r = Integer.parseInt(parts[1]);
            String[] xyz = parts[0].substring(5).split(",");
            if (r != foundRadius) {
                int xi = Integer.parseInt(xyz[0]);
                int yi = Integer.parseInt(xyz[1]);
                int zi = Integer.parseInt(xyz[2]);
                int distance = Math.abs(xi - x) + Math.abs(yi - y) + Math.abs(zi - z);

                if (distance <= foundRadius) {
                    count++;
                }
            }
        }

        System.out.println("Answer: " + count); //906
    }
}
