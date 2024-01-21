package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_6;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day6 {

    // 100-600 - 501
    // 100-499 - 400
    // 100-399, 500-800 - 300 + 301 = 601
    // 100-800 - 701
    // 100-699, 801-900 - 600 + 100 = 700
    private static String INPUT = """
            turn on 0,100 through 0,600
            turn off 0,500 through 0,700
            toggle 0,400 through 0,800
            turn on 0,300 through 0,500
            toggle 0,700 through 0,900
            """;

    public static void solve() {
        INPUT = DAY_6;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Set<Integer> turnedOn = new HashSet<>();
        Map<Integer, Integer> brightness = new HashMap<>();
        for (String line : lines) {
            if (line.startsWith("turn")) {
                var parts = line.substring(5).split(" ");
                boolean on = parts[0].equals("on");
                var x1y1 = parts[1].split(",");
                int x1 = Integer.parseInt(x1y1[0]), y1 = Integer.parseInt(x1y1[1]);
                var x2y2 = parts[3].split(",");
                int x2 = Integer.parseInt(x2y2[0]), y2 = Integer.parseInt(x2y2[1]);

                for (int i = x1; i <= x2; i++) {
                    for (int j = y1; j <= y2; j++) {
                        int num = (1000 * i) + j;
                        if (on) {
                            turnedOn.add(num);
                            brightness.put(num, brightness.getOrDefault(num, 0) + 1);
                        } else {
                            turnedOn.remove(num);
                            if (brightness.getOrDefault(num, 0) > 0) {
                                brightness.put(num, brightness.get(num) - 1);
                            }
                        }
                    }
                }

            } else {
                var parts = line.substring(7).split(" ");
                var x1y1 = parts[0].split(",");
                int x1 = Integer.parseInt(x1y1[0]), y1 = Integer.parseInt(x1y1[1]);
                var x2y2 = parts[2].split(",");
                int x2 = Integer.parseInt(x2y2[0]), y2 = Integer.parseInt(x2y2[1]);

                for (int i = x1; i <= x2; i++) {
                    for (int j = y1; j <= y2; j++) {
                        int num = (1000 * i) + j;
                        if (turnedOn.contains(num)) {
                            turnedOn.remove(num);
                        } else {
                            turnedOn.add(num);
                        }
                        brightness.put(num, brightness.getOrDefault(num, 0) + 2);
                    }
                }
            }
        }

        System.out.println("Part 1: " + turnedOn.size()); // 377891
        System.out.println("Part 2: " + brightness.values().stream().mapToInt(i -> i).sum()); // 14110788
    }
}
