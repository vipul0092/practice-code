package code.vipul.aoc2021;

import code.vipul.aoc2018.grid.Posxy;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 06/12/22
 */
public class Solve5 {

    private final static String INPUT = "0,9 -> 5,9\n" +
            "8,0 -> 0,8\n" +
            "9,4 -> 3,4\n" +
            "2,2 -> 2,1\n" +
            "7,0 -> 7,4\n" +
            "6,4 -> 2,0\n" +
            "0,9 -> 2,9\n" +
            "3,4 -> 1,4\n" +
            "0,0 -> 8,8\n" +
            "5,5 -> 8,2";

    public static void solve() {
        solveInternal(Inputs.INPUT_5, false);
    }

    public static void solvePart2() {
        solveInternal(Inputs.INPUT_5, true);
    }

    private static void solveInternal(String input, boolean diagonal) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        Map<Posxy, Integer> count = new LinkedHashMap<>();

        for (String in : inputs) {
            String[] parts = in.split(" -> ");
            int[] start = new int[2];
            int[] end = new int[2];

            String[] t = parts[0].split(",");
            start[0] = Integer.parseInt(t[0]);
            start[1] = Integer.parseInt(t[1]);

            t = parts[1].split(",");
            end[0] = Integer.parseInt(t[0]);
            end[1] = Integer.parseInt(t[1]);

            if (start[0] == end[0]) {
                int begins = Math.min(start[1], end[1]);
                int ends = Math.max(start[1], end[1]);

                while (begins <= ends) {
                    Posxy pos = Posxy.of(start[0], begins);
                    count.putIfAbsent(pos, 0);
                    count.put(pos, count.get(pos) + 1);
                    begins++;
                }
            } else if (start[1] == end[1]) {
                int begins = Math.min(start[0], end[0]);
                int ends = Math.max(start[0], end[0]);

                while (begins <= ends) {
                    Posxy pos = Posxy.of(begins, start[1]);
                    count.putIfAbsent(pos, 0);
                    count.put(pos, count.get(pos) + 1);
                    begins++;
                }
            } else if (diagonal && Math.abs(start[1] - end[1]) == Math.abs(start[0] - end[0])) {
                int slope = (start[1] - end[1]) / (start[0] - end[0]);
                if (slope == -1) {
                    int startx = Math.max(start[0], end[0]);
                    int starty = Math.min(start[1], end[1]);
                    int endx = Math.min(start[0], end[0]);

                    while (startx >= endx) {
                        Posxy pos = Posxy.of(startx, starty);
                        count.putIfAbsent(pos, 0);
                        count.put(pos, count.get(pos) + 1);
                        startx--;
                        starty++;
                    }

                } else { // slope is 1
                    int startx = Math.min(start[0], end[0]);
                    int starty = Math.min(start[1], end[1]);
                    int endx = Math.max(start[0], end[0]);

                    while (startx <= endx) {
                        Posxy pos = Posxy.of(startx, starty);
                        count.putIfAbsent(pos, 0);
                        count.put(pos, count.get(pos) + 1);
                        startx++;
                        starty++;
                    }
                }
            }
        }

        long answer = count.entrySet().stream().filter(e -> e.getValue() > 1).count();

        System.out.println(answer);
    }
}
