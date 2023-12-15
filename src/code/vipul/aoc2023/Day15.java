package code.vipul.aoc2023;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static code.vipul.aoc2023.inputs.Inputs.DAY_15;

/**
 * Created by vgaur created on 15/12/23
 */
public class Day15 {

    private static String INPUT = """
            rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
            """;

    public static void solve() {
        INPUT = DAY_15;
        List<String> lines = Arrays.stream(INPUT.split(",")).toList();

        long sum = 0;
        Map<Integer, Map<String, Integer>> boxes = new HashMap<>();

        for (String line : lines) {
            sum += getHash(line);

            if (line.contains("=")) {
                var parts = line.split("=");
                String label = parts[0];
                int fl = Integer.parseInt(parts[1].trim());
                int box = getHash(label);
                boxes.putIfAbsent(box, new LinkedHashMap<>());
                boxes.get(box).put(label, fl);
            } else {
                String label = line.split("-")[0];
                int box = getHash(label);
                boxes.putIfAbsent(box, new LinkedHashMap<>());
                boxes.get(box).remove(label);
            }
        }

        int sum2 = 0;
        for (int i = 0; i < 256; i++) {
            if (!boxes.containsKey(i)) continue;
            int total = i + 1;
            int idx = 1;
            for (var entry : boxes.get(i).entrySet()) {
                int fl = entry.getValue();
                sum2 += (total * idx * fl);
                idx++;
            }
        }

        System.out.println("Part 1: " + sum); // 510792
        System.out.println("Part 2: " + sum2); // 269410
    }

    private static int getHash(String str) {
        int current = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '\n') continue;
            current += ch;
            current *= 17;
            current %= 256;
        }
        return current;
    }
}
