package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_3;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day3 {
    private static String INPUT = "1024";

    private static final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;

    record Pos(int i, int j){
        public Pos move(int dir) {
            return switch (dir) {
                case UP -> new Pos(i-1, j);
                case LEFT -> new Pos(i, j-1);
                case DOWN -> new Pos(i+1, j);
                case RIGHT -> new Pos(i, j+1);
                default -> throw new RuntimeException("Impossivel");
            };
        }
    }

    private static int rotate(int dir) {
        return switch (dir) {
            case UP -> LEFT;
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
            default -> throw new RuntimeException("Impossivel");
        };
    }

    private static final int[][] DIFF = new int[][]{{-1, 0}, {1,0}, {0,1}, {0,-1}, {-1, 1}, {1,1}, {-1,-1}, {1,-1}};

    public static void solve() {
        INPUT = DAY_3;
        int pos = Integer.parseInt(INPUT);

        int size = 0;
        for (int i = 1; i < 1000; i+=2) {
            if (i*i > pos) {
                size = i;
                break;
            }
        }
        int prev_sq = (size-2)*(size-2);
        int diff = pos - prev_sq;
        int newpos = diff % (size-1);

        int ans = newpos < size/2 ? size-newpos-1 : newpos;
        System.out.println("Part 1: " + ans); // 438

        Map<Pos, Integer> values = new HashMap<>();
        Pos current = new Pos(0, 0);
        values.put(current, 1);
        int dir = UP, sq = 3, ans2 = -1;
        boolean found = false;
        while(true) {
            int times = 4;
            current = current.move(RIGHT).move(DOWN);
            while(times-- > 0) {
                int move = sq-1;
                while(move-- > 0) {
                    int sum = 0;
                    current = current.move(dir);
                    for (var d : DIFF) {
                        Pos n = new Pos(current.i + d[0], current.j + d[1]);
                        if (values.containsKey(n)) sum += values.get(n);
                    }
                    if (sum > pos) {
                        found = true;
                        ans2 = sum;
                        break;
                    }
                    values.put(current, sum);
                }
                if (found) break;
                dir = rotate(dir);
            }
            if (found) break;
            sq += 2;
        }

        System.out.println("Part 2: " + ans2); // 266330
    }
}
