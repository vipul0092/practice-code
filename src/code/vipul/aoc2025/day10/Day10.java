package code.vipul.aoc2025.day10;

import java.util.*;

import code.vipul.Pair;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2025/day/10
 */
public class Day10 {

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day10.class, false);

        int total = 0, total2 = 0;
        for (String line : lines) {
            String[] parts = line.split(" ");
            String pt = parts[0];

            // Extract the pattern into a bitmask
            int pattern = 0;
            int plen = pt.length() - 2;
            for (int i = 1, j = plen-1; i < pt.length() - 1; i++, j--) {
                char ch = pt.charAt(i);
                pattern |= (ch == '.' ? 0 : 1 << (i-1));
            }

            // Extract the buttons into a bitmask
            List<Integer> buttons = new ArrayList<>();
            int[][] groups = new int[parts.length-2][];
            for (int i = 0; i < parts.length-2; i++) {
                String button = parts[i+1].substring(1, parts[i+1].length() - 1);
                String[] nums = button.split(",");

                int val = 0, idx = 0;
                int[] vals = new int[nums.length];
                for (String num : nums) {
                    int n = Integer.parseInt(num);
                    vals[idx++] = n;
                    val |= (1 << n);
                }
                groups[i] = vals;
                buttons.add(val);
            }

            // Joltages extraction
            String[] sparts = parts[parts.length - 1].substring(1, parts[parts.length - 1].length() - 1).split(",");
            int[] sums = new int[sparts.length];
            int maxsum = 0;
            for (int i = 0; i < sparts.length; i++) {
                sums[i] = Integer.parseInt(sparts[i]);
                maxsum = Math.max(maxsum, sums[i]);
            }

            // Part 1: BFS to find minimum presses
            Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();
            for (int b : buttons) {
                q.add(new Pair<>(b, 1));
                visited.add(b);
            }

            int min1 = 0;
            while(true) {
                Pair<Integer, Integer> cur = q.remove();
                int mask = cur.left(), presses = cur.right();
                if (mask == pattern) {
                    min1 = presses;
                    break;
                }

                for (int b : buttons) {
                    int nmask = mask ^ b;
                    if (!visited.contains(nmask)) {
                        visited.add(nmask);
                        q.add(new Pair<>(nmask, presses + 1));
                    }
                }
            }
            total += min1;

            // GG solver that I do not understand :D
            int part2 = ButtonPressesSolver.solveProblem(groups, sums);
            total2 += part2;
        }

        System.out.println("Part 1: " + total); // 498
        System.out.println("Part 2: " + total2); // 17133
    }
}
