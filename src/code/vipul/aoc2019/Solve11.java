package code.vipul.aoc2019;

import code.vipul.aoc2019.intcode.Computer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * https://adventofcode.com/2019/day/11
 */
public class Solve11 {

    private static String input = "3,8,1005,8,338,1106,0,11,0,0,0,104,1,104,0,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,1,8,10,4,10,102,1,8,28,1,108,6,10,1,3,7,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,1,8,10,4,10,1001,8,0,58,2,5,19,10,1,1008,7,10,2,105,6,10,1,1007,7,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,0,10,4,10,101,0,8,97,1006,0,76,1,106,14,10,2,9,9,10,1006,0,74,3,8,102,-1,8,10,101,1,10,10,4,10,108,1,8,10,4,10,1002,8,1,132,1006,0,0,2,1104,15,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,0,10,4,10,1001,8,0,162,1,1005,13,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,1,8,10,4,10,101,0,8,187,1,1,15,10,2,3,9,10,1006,0,54,3,8,102,-1,8,10,101,1,10,10,4,10,108,0,8,10,4,10,102,1,8,220,1,104,5,10,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,0,10,4,10,102,1,8,247,1,5,1,10,1,1109,2,10,3,8,1002,8,-1,10,101,1,10,10,4,10,1008,8,0,10,4,10,1001,8,0,277,1006,0,18,3,8,1002,8,-1,10,101,1,10,10,4,10,108,1,8,10,4,10,101,0,8,301,2,105,14,10,1,5,1,10,2,1009,6,10,1,3,0,10,101,1,9,9,1007,9,1054,10,1005,10,15,99,109,660,104,0,104,1,21101,0,47677546524,1,21101,0,355,0,1105,1,459,21102,936995299356,1,1,21101,0,366,0,1106,0,459,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21101,0,206312807515,1,21102,1,413,0,1105,1,459,21101,206253871296,0,1,21102,424,1,0,1106,0,459,3,10,104,0,104,0,3,10,104,0,104,0,21102,1,709580554600,1,21102,1,447,0,1105,1,459,21101,0,868401967464,1,21101,458,0,0,1106,0,459,99,109,2,22102,1,-1,1,21102,1,40,2,21101,0,490,3,21102,480,1,0,1106,0,523,109,-2,2105,1,0,0,1,0,0,1,109,2,3,10,204,-1,1001,485,486,501,4,0,1001,485,1,485,108,4,485,10,1006,10,517,1101,0,0,485,109,-2,2105,1,0,0,109,4,2101,0,-1,522,1207,-3,0,10,1006,10,540,21102,0,1,-3,21201,-3,0,1,21202,-2,1,2,21101,0,1,3,21101,0,559,0,1105,1,564,109,-4,2106,0,0,109,5,1207,-3,1,10,1006,10,587,2207,-4,-2,10,1006,10,587,21202,-4,1,-4,1105,1,655,21201,-4,0,1,21201,-3,-1,2,21202,-2,2,3,21102,606,1,0,1105,1,564,22102,1,1,-4,21102,1,1,-1,2207,-4,-2,10,1006,10,625,21102,1,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,647,22101,0,-1,1,21101,0,647,0,106,0,522,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2106,0,0";

    public static void solve() {
        Computer computer = Computer.make();
        computer.loadProgramInMemory(input);

        Map<Grid.Pos, Long> gridColors = new HashMap<>();

        long input;
        Grid.Pos currentPos = Grid.Pos.of(0, 0);
        AsciiGrid.Direction currentDirection = AsciiGrid.Direction.UP;
        while (!computer.hasHalted()) {

            if (gridColors.containsKey(currentPos)) {
                input = gridColors.get(currentPos);
            } else {
                input = 0;
            }

            computer.giveInputAndExecute(Collections.singletonList(input));
            String output = computer.getOutput().getFullOutput();
            computer.getOutput().clear();

            // Update the color at the current position
            long color = output.charAt(0) == '0' ? 0 : 1;
            gridColors.put(currentPos, color);

            // Change the direction
            int directionChange = output.charAt(1) == '0' ? 0 : 1;
            currentDirection = changeDirection(directionChange, currentDirection);

            // Move 1 unit it the current direction
            currentPos = getPositionForDirection(currentPos, currentDirection);
        }

        System.out.println("Panels painted at least once: " + gridColors.size()); // 2720
    }

    public static void solvePart2() {
        Computer computer = Computer.make();
        computer.loadProgramInMemory(input);

        Map<Grid.Pos, Long> gridColors = new HashMap<>();

        long input = 1;
        boolean start = true;
        Grid.Pos currentPos = Grid.Pos.of(0, 0);
        AsciiGrid.Direction currentDirection = AsciiGrid.Direction.UP;

        int maxi = 0, maxj = 0;
        int mini = Integer.MAX_VALUE, minj = Integer.MAX_VALUE;

        while (!computer.hasHalted()) {

            if (gridColors.containsKey(currentPos)) {
                input = gridColors.get(currentPos);
            } else {
                input = start ? input : 0;
            }
            start = false;

            computer.giveInputAndExecute(Collections.singletonList(input));
            String output = computer.getOutput().getFullOutput();
            computer.getOutput().clear();

            // Update the color at the current position
            long color = output.charAt(0) == '0' ? 0 : 1;
            gridColors.put(currentPos, color);

            // Change the direction
            int directionChange = output.charAt(1) == '0' ? 0 : 1;
            currentDirection = changeDirection(directionChange, currentDirection);

            // Move 1 unit it the current direction
            currentPos = getPositionForDirection(currentPos, currentDirection);

            maxi = Math.max(maxi, currentPos.i());
            maxj = Math.max(maxj, currentPos.j());
            mini = Math.min(mini, currentPos.i());
            minj = Math.min(minj, currentPos.j());
        }

        // Prints out JZPJRAGJ
        for (int j = maxj; j >= minj; j--) {
            for (int i = mini; i <= maxi; i++) {
                Grid.Pos pos = Grid.Pos.of(i, j);
                if (gridColors.containsKey(pos)) {
                    long color = gridColors.get(pos);
                    System.out.print(color == 0 ? ' ' : '#');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }

    }

    private static Grid.Pos getPositionForDirection(Grid.Pos currentPos, AsciiGrid.Direction direction) {
        switch (direction) {
            case DOWN:
                return currentPos.moveDown(1);
            case UP:
                return currentPos.moveUp(1);
            case LEFT:
                return currentPos.moveLeft(1);
            case RIGHT:
                return currentPos.moveRight(1);
            default:
                throw new RuntimeException("What direction is that!?");
        }
    }

    // 0 -> Turn Left 90 degrees, 1 -> Turn right 90 degrees
    private static AsciiGrid.Direction changeDirection(int directionChange,
                                                       AsciiGrid.Direction currentDirection) {
        switch (currentDirection) {
            case DOWN:
                return directionChange == 0 ? AsciiGrid.Direction.RIGHT : AsciiGrid.Direction.LEFT;
            case UP:
                return directionChange == 0 ? AsciiGrid.Direction.LEFT : AsciiGrid.Direction.RIGHT;
            case LEFT:
                return directionChange == 0 ? AsciiGrid.Direction.DOWN : AsciiGrid.Direction.UP;
            case RIGHT:
                return directionChange == 0 ? AsciiGrid.Direction.UP : AsciiGrid.Direction.DOWN;
            default:
                throw new RuntimeException("What direction is that!?");
        }
    }
}
