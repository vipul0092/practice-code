package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static code.vipul.aoc2023.inputs.Inputs.DAY_4;

/**
 * Created by vgaur created on 03/12/23
 */
public class Day4 {

    private static String INPUT = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
            """;

    public static void solve() {
        INPUT = DAY_4;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        int sum = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(": ");
            String[] cardstr = parts[0].split(" ");
            int card = Integer.parseInt(cardstr[cardstr.length-1]);
            counts.put(card, counts.getOrDefault(card, 0) + 1);

            Set<Integer> win = new HashSet<>();
            List<Integer> nums = new ArrayList<>();
            String[] numparts = parts[1].split(" \\| ");

            String[] winstr = numparts[1].split(" ");
            for (String w : winstr) {
                if (w.isEmpty()) continue;
                win.add(Integer.parseInt(w.trim()));
            }

            String[] gotstr = numparts[0].split(" ");
            for (String g : gotstr) {
                if (g.isEmpty()) continue;
                nums.add(Integer.parseInt(g.trim()));
            }

            int curr = 0;
            int count = 0;
            for (int num : nums) {
                if (win.contains(num)) {
                    count++;
                    curr = curr == 0 ? 1 : curr * 2;
                }
            }

            if (count > 0) {
                for (int i = 1; i <= count; i++) {
                    counts.putIfAbsent(card + i, 0);
                    counts.put(card+i, counts.get(card) + counts.get(card+i));
                }
            }

            sum += curr;
        }

        System.out.println(sum); // 27059

        int total = 0;
        for (int v : counts.values()) {
            total += v;
        }

        System.out.println(total); // 5744979
    }
}
