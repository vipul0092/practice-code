package code.vipul.aoc2017;

import java.util.Arrays;

import static code.vipul.aoc2017.inputs.Inputs.DAY_1;


/**
 * https://adventofcode.com/2017/day/1
 * Created by vgaur created on 26/12/23
 */
public class Day1 {

    private static String INPUT = "123123";

    public static void solve() {
        INPUT = DAY_1;
        String line = Arrays.stream(INPUT.split("\n")).toList().get(0);

        int sum = 0, sum2 = 0;
        for (int i = 0; i < line.length(); i++) {
            int i1 = (i+1) % line.length();
            int i2 = (i + (line.length() / 2)) % line.length();
            if (line.charAt(i) == line.charAt(i1)) {
                sum += (line.charAt(i) - '0');
            }
            if (line.charAt(i) == line.charAt(i2)) {
                sum2 += (line.charAt(i) - '0');
            }
        }

        System.out.println("Part 1: " + sum); // 1203
        System.out.println("Part 2: " + sum2); // 1146
    }
}
