package code.vipul.aoc2015;

import code.vipul.aoc2015.utils.MD5;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_4;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day4 {

    private static String INPUT = """
            pqrstuv
            """;

    public static void solve() {
        INPUT = DAY_4;
        String input = Arrays.stream(INPUT.split("\n")).toList().get(0);
        int suffix = 1;
        String hash = "";
        do {
            hash = MD5.hashMd5(input + suffix);
            suffix++;
        } while (!hash.startsWith("00000"));
        System.out.println("Part 1: " + --suffix); // 117946

        hash = ""; suffix = 1;
        do {
            hash = MD5.hashMd5(input + suffix);
            suffix++;
        } while (!hash.startsWith("000000"));
        System.out.println("Part 2: " + --suffix); // 3938038
    }
}
