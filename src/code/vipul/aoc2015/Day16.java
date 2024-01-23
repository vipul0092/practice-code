package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_16;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day16 {

    private static final String VALUES = """
            children: 3
            cats: 7
            samoyeds: 2
            pomeranians: 3
            akitas: 0
            vizslas: 0
            goldfish: 5
            trees: 3
            cars: 2
            perfumes: 1
            """;
    public static void solve() {
        List<String> lines = Arrays.stream(DAY_16.split("\n")).toList();
        System.out.println("Part 1: " + findAunt(lines, false)); // 40
        System.out.println("Part 2: " +  findAunt(lines, true)); // 241
    }

    private static int findAunt(List<String> lines, boolean considerRange) {
        int answer = -1;
        Map<String, Integer> values = new HashMap<>();
        for (String value : VALUES.split("\n")) {
            var parts = value.split(": ");
            values.put(parts[0], Integer.parseInt(parts[1]));
        }

        for (String line : lines) {
            int num = Integer.parseInt(line.split(": ")[0].split(" ")[1]);
            var parts = line.split(", ");
            boolean valid = true;
            for (var part : parts) {
                var split = part.split(": ");
                String type = ""; int val = -1;
                if (split.length == 3) {
                    val = Integer.parseInt(split[2]);
                    type = split[1];
                } else {
                    val = Integer.parseInt(split[1]);
                    type = split[0];
                }

                if (considerRange && (type.equals("cats") || type.equals("trees"))) {
                    valid &= values.get(type) < val;
                } else if (considerRange && (type.equals("pomeranians") || type.equals("goldfish"))) {
                    valid &= values.get(type) > val;
                } else {
                    valid &= values.get(type) == val;
                }
            }
            if (valid) {
                answer = num;
                break;
            }
        }
        return answer;
    }
}
