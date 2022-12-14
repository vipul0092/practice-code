package code.vipul.aoc2022;

import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 14/12/22
 */
public class Solve14 {

    private static final String INPUT = "498,4 -> 498,6 -> 496,6\n" +
            "503,4 -> 502,4 -> 502,9 -> 494,9";
    private static final Character WATER = '+';

    private static int minI = Integer.MAX_VALUE, minJ = Integer.MAX_VALUE, maxI = 0, maxJ = 0;
    private static char[][] scan;

    private static final Supplier<Integer> getAnswer = () -> {
        int count = 0;
        for (int i = minI; i <= maxI; i++) {
            for (int j = minJ; j <= maxJ; j++) {
                if (scan[i][j] == 'O') {
                    count++;
                }
            }
        }
        return count;
    };

    public static void solve() {
        // PART 1
        parse(Inputs.INPUT_14);
        //parse(INPUT);
        simulate(Grid.Pos.of(0, 500));
        print();
        System.out.println(getAnswer.get());


        // PART 2
        parse(Inputs.INPUT_14);
        //parse(INPUT);
        for (int j = 0; j < 2500; j++) {
            scan[maxI + 2][j] = '#';
        }
        maxI = maxI + 2;
        simulate(Grid.Pos.of(0, 500));
        print();
        System.out.println(getAnswer.get());
    }

    private static void simulate(Grid.Pos source) {
        while(true) {
            if (value(source) == 'O') {
                break;
            }
            // print();
            boolean infinite = false;
            Grid.Pos prev = source.copy();
            while (true) {
                Grid.Pos moved = move(prev);
                if (!isValid(moved)) {
                    infinite = true;
                    break;
                }
                if (moved.equals(prev)) { // cant move anymore
                    scan[moved.i()][moved.j()] = 'O';
                    maxJ = Math.max(maxJ, moved.j());
                    minJ = Math.min(minJ, moved.j());
                    break;
                }
                prev = moved;
            }
            if (infinite) {
                break;
            }
        }
    }

    private static void parse(String input) {
        scan = new char[2500][2500];
        scan[0][500] = WATER;

        minI = 0;
        minJ = 500;
        maxJ = 500;
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        for (String in1 : inputs) {
            int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
            String[] parts = in1.split(" -> ");

            for (int j = 1; j < parts.length; j++) {
                String[] ij = parts[j - 1].split(",");
                i1 = Integer.parseInt(ij[1]);
                j1 = Integer.parseInt(ij[0]);

                ij = parts[j].split(",");
                i2 = Integer.parseInt(ij[1]);
                j2 = Integer.parseInt(ij[0]);

                if (i1 == i2) {
                    minI = Math.min(minI, i1);
                    maxI = Math.max(maxI, i2);
                    int from = Math.min(j1, j2);
                    int to = Math.max(j1, j2);

                    for (int k = from; k <= to; k++) {
                        scan[i1][k] = '#';
                    }
                    minJ = Math.min(minJ, from);
                    maxJ = Math.max(maxJ, to);
                } else if (j1 == j2) {
                    minJ = Math.min(minJ, j1);
                    maxJ = Math.max(maxJ, j1);
                    int from = Math.min(i1, i2);
                    int to = Math.max(i1, i2);

                    for (int k = from; k <= to; k++) {
                        scan[k][j1] = '#';
                    }
                    minI = Math.min(minI, from);
                    maxI = Math.max(maxI, to);
                }
            }
        }
    }

    private static Grid.Pos move(Grid.Pos pos) {
        char down = value(pos.moveDown());
        if (down != '#' && down != 'O') {
            return pos.moveDown();
        }

        char left = value(pos.moveDown().moveLeft());
        if (left != '#' && left != 'O') {
            return pos.moveDown().moveLeft();
        }

        char right = value(pos.moveDown().moveRight());
        if (right != '#' && right != 'O') {
            return pos.moveDown().moveRight();
        }
        return pos;
    }

    private static char value(Grid.Pos pos) {
        return scan[pos.i()][pos.j()];
    }

    private static boolean isValid(Grid.Pos pos) {
        int i = pos.i();
        int j = pos.j();

        return !(i < minI || i > maxI + 2 || j > maxJ + 2 || j < minJ - 2);
    }

    private static void print() {
        for (int i = minI; i <= maxI; i++) {
            for (int j = minJ; j <= maxJ; j++) {
                if (scan[i][j] == '\0') {
                    System.out.print('.');
                } else {
                    System.out.print(scan[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
