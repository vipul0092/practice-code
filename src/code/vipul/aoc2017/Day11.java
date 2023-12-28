package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_11;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day11 {

    private static String INPUT = """
            se,sw,se,sw,sw
            """;


    record Coord(int q, int r, int s){

        // Taken from: https://www.redblobgames.com/grids/hexagons/#coordinates-cube
        public Coord move(String dir) {
            return switch (dir) {
                case "se" -> new Coord(q+1, r, s-1);
                case "ne" ->  new Coord(q+1, r-1, s);
                case "sw" ->  new Coord(q-1, r+1, s);
                case "nw" ->  new Coord(q-1, r, s+1);
                case "n" ->  new Coord(q, r-1, s+1);
                case "s" ->  new Coord(q, r+1, s-1);
                default -> throw new RuntimeException("Impossivel");
            };
        }

        public int distanceFromCenter() {
            return (Math.abs(q) + Math.abs(r) + Math.abs(s))/ 2;
        }
    }
    public static void solve() {
        INPUT = DAY_11;
        List<String> directions = Arrays.stream(Arrays.stream(INPUT.split("\n"))
                .toList().get(0).split(",")).toList();
        int max = 0;
        Coord current = new Coord(0, 0, 0);
        for (String dir : directions) {
            current = current.move(dir);
            max = Math.max(max, current.distanceFromCenter());
        }

        System.out.println("Part 1: " + current.distanceFromCenter()); // 675
        System.out.println("Part 2: " + max); // 1424
    }


}
