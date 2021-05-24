package code.vipul.aoc2020;

import java.util.HashMap;
import java.util.HashSet;

import static code.vipul.aoc2020.Inputs.INPUT_6;

/**
 * https://adventofcode.com/2020/day/6
 */
public class Solve6 {

    public static void solve() {
        var rows = INPUT_6.split("\n");

        int count = 0;
        var chars = new HashSet<Character>();

        for(String row: rows) {
            if (row.equals("")) {
                count += chars.size();
                chars.clear();
            }

            for (Character ch: row.toCharArray()) {
                chars.add(ch);
            }
        }

        count += chars.size();
        chars.clear();

        System.out.println("Answer: " + count); //6625
    }

    public static void solvePart2() {
        var rows = INPUT_6.split("\n");

        int count = 0;
        int groupMembers = 0;
        var charGroupCount = new HashMap<Character, Integer>();

        for(String row: rows) {
            if (row.equals("")) {
                for (var entry: charGroupCount.entrySet()) {
                    if (entry.getValue() == groupMembers) { // the question is answered by all
                        count++;
                    }
                }

                charGroupCount.clear();
                groupMembers = 0;
                continue;
            }

            groupMembers++;
            for (Character ch: row.toCharArray()) {
                charGroupCount.putIfAbsent(ch, 0);
                charGroupCount.put(ch, charGroupCount.get(ch) + 1);
            }
        }

        for (var entry: charGroupCount.entrySet()) {
            if (entry.getValue() == groupMembers) { // the question is answered by all
                count++;
            }
        }

        System.out.println("Answer: " + count); //3360
    }
}
