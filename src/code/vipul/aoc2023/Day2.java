package code.vipul.aoc2023;

import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2023.inputs.Inputs.DAY_2;

/**
 * Created by vgaur created on 02/12/23
 */
public class Day2 {

    private static final String INPUT = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """;


    public static void solve() {
        List<String> lines = Arrays.stream(DAY_2.split("\n")).toList();
        //List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        int sum = 0;

        for (String line : lines) {
            String[] two = line.split(": ");
            int id = Integer.parseInt(two[0].split(" ")[1]);
            String[] rounds = two[1].split("; ");

            boolean possible = true;
            for (String round : rounds) {
                if (!new Counts(round.split(", ")).isValid()) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                sum += id;
            }
        }

        System.out.println(sum);

        solvePart2();
    }

    public static void solvePart2() {
        List<String> lines = Arrays.stream(DAY_2.split("\n")).toList();
        //List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        int sum = 0;

        for (String line : lines) {
            String[] two = line.split(": ");
            String[] rounds = two[1].split("; ");

            int minblue = 0, mingreen = 0, minred = 0;
            for (String round : rounds) {
                var counts = new Counts(round.split(", "));
                minblue = Math.max(minblue, counts.blue);
                mingreen = Math.max(mingreen, counts.green);
                minred = Math.max(minred, counts.red);
            }

            int power = minblue * mingreen * minred;
            sum += power;
        }

        System.out.println(sum);
    }

    private static final class Counts {
        private static final int RED = 12;
        private static final int GREEN = 13;
        private static final int BLUE = 14;

        private int red = 0, green = 0, blue = 0;

        public Counts(String[] cubes) {
            for (String cube : cubes) {
                String[] parts = cube.split(" ");
                int count = Integer.parseInt(parts[0]);
                switch (parts[1]) {
                    case "red" -> red = count;
                    case "green" -> green = count;
                    case "blue" -> blue = count;
                }
            }
        }

        public boolean isValid() {
            return red <= RED && blue <= BLUE && green <= GREEN;
        }
    }
}
