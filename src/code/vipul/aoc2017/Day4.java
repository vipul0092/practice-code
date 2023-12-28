package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_4;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day4 {

    private static String INPUT = """
            aa bb cc dd aaa
            """;

    public static void solve() {
        INPUT = DAY_4;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        int valid = 0;
        for (String line : lines) {
            var parts = line.split(" ");
            var set = new HashSet<>(Arrays.asList(parts));
            if (parts.length == set.size()) {
                valid++;
            }
        }

        int valid2 = 0;
        for (String line : lines) {
            var parts = line.split(" ");
            boolean correct = true;
            for (int i = 0; i < parts.length-1; i++) {
                for (int j = i+1; j < parts.length; j++) {
                    String s1 = parts[i];
                    String s2 = parts[j];

                    Map<Character, Integer> c1 = new HashMap<>(), c2 = new HashMap<>();
                    for (char c : s1.toCharArray()) c1.merge(c, 1, Integer::sum);
                    for (char c : s2.toCharArray()) c2.merge(c, 1, Integer::sum);

                    if (c1.equals(c2)) {
                        correct = false;
                        break;
                    }
                }
                if (!correct) break;
            }

            if (correct) valid2++;
        }

        System.out.println("Part 1: " + valid);
        System.out.println("Part 2: " + valid2);
    }
}
