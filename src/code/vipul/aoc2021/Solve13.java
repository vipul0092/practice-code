package code.vipul.aoc2021;

import code.vipul.aoc2018.grid.Posxy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 31/12/22
 * https://adventofcode.com/2021/day/13
 */
public class Solve13 {

    private static final String INPUT = "6,10\n" +
            "0,14\n" +
            "9,10\n" +
            "0,3\n" +
            "10,4\n" +
            "4,11\n" +
            "6,0\n" +
            "6,12\n" +
            "4,1\n" +
            "0,13\n" +
            "10,12\n" +
            "3,4\n" +
            "3,0\n" +
            "8,4\n" +
            "1,10\n" +
            "2,14\n" +
            "8,10\n" +
            "9,0\n" +
            "\n" +
            "fold along y=7\n" +
            "fold along x=5";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_13.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        var grid = new HashSet<Posxy>();
        int from;
        int maxx = Integer.MIN_VALUE, maxy = Integer.MIN_VALUE;
        for (int i = 0; ; i++) {
            String in = inputs.get(i);
            if (in.isEmpty()) {
                from = i + 1;
                break;
            }
            var pos = Posxy.of(Integer.parseInt(in.split(",")[0]), Integer.parseInt(in.split(",")[1]));
            grid.add(pos);
            maxx = Math.max(pos.x(), maxx);
            maxy = Math.max(pos.y(), maxy);
        }

        for (int i = from; i < inputs.size(); i++) {
            String in = inputs.get(i);
            String[] fold = in.split("fold along ")[1].split("=");
            char axis = fold[0].charAt(0);
            int num = Integer.parseInt(fold[1]);

            Set<Posxy> toMove = new HashSet<>();
            grid.stream().filter(p -> axis == 'y' ? p.y() > num : p.x() > num).forEach(p -> toMove.add(p));

            for (Posxy point : toMove) {
                grid.remove(point);
                Posxy newPoint = Posxy.of(
                        axis == 'y' ? point.x() : num - (point.x() - num),
                        axis == 'y' ? num - (point.y() - num) : point.y()
                );
                grid.add(newPoint);
                maxy = axis == 'y' ? num : maxy;
                maxx = axis == 'y' ? maxx : num;
            }
            if (i == from) {
                System.out.println("Part 1: " + grid.size());
            }
        }

        // Prints CEJKLUGJ
        for (int y = 0; y <= maxy; y++) {
            for (int x = 0; x <= maxx; x++) {
                Posxy pos = Posxy.of(x, y);
                if (grid.contains(pos)) {
                    System.out.print('#');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

}
