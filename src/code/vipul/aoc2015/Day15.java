package code.vipul.aoc2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2015.inputs.Inputs.DAY_15;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day15 {

    private static String INPUT = """
            Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
            Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
            """;
    private static long answer = 0;

    record Ing(String name, int cap, int dur, int flav, int tex, int cal) {
    }

    public static void solve() {
        INPUT = DAY_15;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        List<Ing> ings = new ArrayList<>();
        for (String line : lines) {
            String name = line.split(": ")[0];
            var parts = line.split(": ")[1].split(", ");
            ings.add(new Ing(name,
                    Integer.parseInt(parts[0].split(" ")[1]),
                    Integer.parseInt(parts[1].split(" ")[1]),
                    Integer.parseInt(parts[2].split(" ")[1]),
                    Integer.parseInt(parts[3].split(" ")[1]),
                    Integer.parseInt(parts[4].split(" ")[1])
            ));
        }

        answer = 0;
        populateAnswer(0, ings, 100, 0, 0, 0, 0, 0, false);
        System.out.println("Part 1: " + answer); // 21367368

        answer = 0;
        populateAnswer(0, ings, 100, 0, 0, 0, 0, 0, true);
        System.out.println("Part 2: " + answer); // 1766400
    }

    private static void populateAnswer(int current, List<Ing> ings, int left,
                                       int cap, int dur, int flav, int tex, int cal, boolean checkCookie) {
        if (left == 0) {
            return;
        }
        var ing = ings.get(current);
        if (current == ings.size() - 1) {
            int calories = cal + (left * ing.cal);
            if (checkCookie && calories != 500) return;
            long product = 1;
            product *= Math.max(cap + (left * ing.cap), 0);
            product *= Math.max(dur + (left * ing.dur), 0);
            product *= Math.max(flav + (left * ing.flav), 0);
            product *= Math.max( tex + (left * ing.tex), 0);
            answer = Math.max(answer, product);
            return;
        }
        for (int i = 1; i < left; i++) {
            populateAnswer(current + 1, ings, left - i,
                    cap + (i * ing.cap),
                    dur + (i * ing.dur),
                    flav + (i * ing.flav),
                    tex + (i * ing.tex),
                    cal + (i * ing.cal),
                    checkCookie);
        }
    }
}
