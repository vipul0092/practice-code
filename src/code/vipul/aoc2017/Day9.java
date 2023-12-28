package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_9;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day9 {

    private static String INPUT = """
            {<{o"i!a,<{i<a>}
            """;

    public static void solve() {
        INPUT = DAY_9;
        String line = Arrays.stream(INPUT.split("\n")).toList().get(0);

        int parentGroup = 0, total = 0, currentGroup = -1, garbageCount = 0, starts = 0;
        boolean garbage = false;
        for(int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '{' && !garbage) {
                currentGroup = parentGroup+1;
                parentGroup = currentGroup;
            } else if  (ch == '}' && !garbage) {
                total += currentGroup;
                currentGroup--;
                parentGroup--;
            } else if (ch == '<' && !garbage) {
                garbage = true;
                starts++;
            } else if (ch == '>' && garbage) {
                garbage = false;
            } else if (ch == '!' && garbage) {
                i++; // ignore the next character
            }

            if (garbage && ch != '!') {
                garbageCount++;
            }
        }

        System.out.println("Part 1: " + total);
        System.out.println("Part 2: " + (garbageCount-starts));
    }
}
