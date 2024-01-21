package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_8;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day8 {

    private static String INPUT = """
            ""
            "abc"
            "aaa\\"aaa"
            "\\x27"
            """;

    public static void solve() {
        INPUT = DAY_8;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        int total = 0, string = 0, encoded = 0;
        for (String line : lines) {
            total += line.length();

            for (int i = 1; i < line.length()-1;) {
                if (line.charAt(i) == '\\') {
                    i += (line.charAt(i+1) == 'x' ? 4 : 2);
                } else {
                    i++;
                }
                string++;
            }
            encoded += 2; // For the enclosing "" of the string
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '\\' || line.charAt(i) == '"') {
                    encoded += 2;
                } else {
                    encoded++;
                }
            }
        }

        System.out.println("Part 1: " + (total - string)); // 1350
        System.out.println("Part 2: " + (encoded - total)); // 2085
    }
}
