package code.vipul.aoc2022;

import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 08/12/22
 */
public class Solve9 {

    private static final String INPUT = "R 5\n" +
            "U 8\n" +
            "L 8\n" +
            "D 3\n" +
            "R 17\n" +
            "D 10\n" +
            "L 25\n" +
            "U 20";

    private static final Map<Character, Function<Grid.Pos, Grid.Pos>> HEAD_MOVEMENT;
    private static final Set<Function<Grid.Pos, Grid.Pos>> DIAGONAL_MOVEMENTS;

    static {
        HEAD_MOVEMENT = new HashMap<>();
        HEAD_MOVEMENT.put('L', p -> p.moveLeft(1));
        HEAD_MOVEMENT.put('R', p -> p.moveRight(1));
        HEAD_MOVEMENT.put('U', p -> p.moveUp(1));
        HEAD_MOVEMENT.put('D', p -> p.moveDown(1));

        DIAGONAL_MOVEMENTS = new HashSet<>();
        DIAGONAL_MOVEMENTS.add(p -> p.moveNE());
        DIAGONAL_MOVEMENTS.add(p -> p.moveNW());
        DIAGONAL_MOVEMENTS.add(p -> p.moveSE());
        DIAGONAL_MOVEMENTS.add(p -> p.moveSW());
    }

    public static void solve() {
        solveInternal(Inputs.INPUT_9, 2);
    }

    public static void solvePart2() {
        solveInternal(Inputs.INPUT_9, 10);
    }

    private static void solveInternal(String input, int items) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        Grid.Pos[] rope = new Grid.Pos[items];
        for (int i = 0; i < items; i++) {
            rope[i] = Grid.Pos.of(0, 0);
        }

        Set<Grid.Pos> positions = new LinkedHashSet<>();
        positions.add(rope[items - 1]); // add tail initial position

        for (String in : inputs) {
            String[] split = in.split(" ");
            int move = Integer.parseInt(split[1]);
            char dir = split[0].charAt(0);
            Function<Grid.Pos, Grid.Pos> moveHead = HEAD_MOVEMENT.get(dir);

            while (move-- > 0) {
                rope[0] = moveHead.apply(rope[0]); // move the head first
                for (int i = 1; i < items; i++) {
                    rope[i] = moveTail(rope[i - 1], rope[i]);
                }
                positions.add(rope[items - 1]);
            }
        }
        System.out.println(positions.size());
    }

    private static Grid.Pos moveTail(Grid.Pos head, Grid.Pos tail) {
        // Same x-coordinate
        if (head.i() == tail.i() && Math.abs(head.j() - tail.j()) > 1) {
            return head.j() > tail.j() ? tail.moveUp(1) : tail.moveDown(1);
            // Same y-coordinate
        } else if (head.j() == tail.j() && Math.abs(head.i() - tail.i()) > 1) {
            return head.i() > tail.i() ? tail.moveRight(1) : tail.moveLeft(1);
            // Diagonal
        } else if (Math.abs(head.j() - tail.j()) > 1 || Math.abs(head.i() - tail.i()) > 1) {
            Grid.Pos movedTail;
            for (Function<Grid.Pos, Grid.Pos> mover : DIAGONAL_MOVEMENTS) {
                movedTail = mover.apply(tail);
                if ((Math.abs(head.j() - movedTail.j()) + Math.abs(head.i() - movedTail.i())) <= 2) {
                    return movedTail;
                }
            }
        }
        return tail;
    }
}
