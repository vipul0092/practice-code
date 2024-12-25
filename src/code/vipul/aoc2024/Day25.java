package code.vipul.aoc2024;

import java.util.*;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2024/day/25
 */
public class Day25 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day25.class, false);

        List<List<Integer>> keys = new ArrayList<>();
        List<List<Integer>> locks = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += 8) {
            boolean isLock = lines.get(i).equals("#####");
            List<Integer> tool = getCurrentTool(lines, i, isLock);

            if (isLock) {
                locks.add(tool);
            } else {
                keys.add(tool);
            }
        }

        int count = 0;
        for (List<Integer> key : keys) {
            for (List<Integer> lock : locks) {
                boolean valid = true;
                for (int i = 0; i < key.size(); i++) {
                    int v1 = key.get(i), v2 = lock.get(i);
                    if (v1 + v2 > 7) {
                        valid = false;
                        break;
                    }
                }
                if (valid) count++;
            }
        }
        System.out.println("Answer: " + count); // 3690
    }

    private static List<Integer> getCurrentTool(List<String> lines, int i, boolean isLock) {
        List<Integer> tool = new ArrayList<>();
        for (int j = 0; j < lines.get(i).length(); j++) {
            char check = isLock ? '#' : '.';
            int count = 0;
            for (int k = 0; k < 7; k++) {
                if (lines.get(i + k).charAt(j) == check) {
                    count++;
                } else {
                    break;
                }
            }

            if (isLock) {
                tool.add(count);
            } else {
                tool.add(7 - count);
            }
        }
        return tool;
    }
}
