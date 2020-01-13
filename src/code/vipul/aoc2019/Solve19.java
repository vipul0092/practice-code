package code.vipul.aoc2019;

import code.vipul.aoc2019.intcode.Computer;

/**
 * https://adventofcode.com/2019/day/19
 */
public class Solve19 {

    static String input = "109,424,203,1,21102,11,1,0,1105,1,282,21102,1,18,0,1105,1,259,1201,1,0,221,203,1,21101,0,31,0,1105,1,282,21102,38,1,0,1106,0,259,21001,23,0,2,22101,0,1,3,21101,0,1,1,21102,1,57,0,1105,1,303,2101,0,1,222,21001,221,0,3,20102,1,221,2,21102,259,1,1,21101,80,0,0,1106,0,225,21101,0,111,2,21102,1,91,0,1105,1,303,2102,1,1,223,20101,0,222,4,21102,1,259,3,21102,1,225,2,21102,1,225,1,21101,0,118,0,1105,1,225,20101,0,222,3,21102,148,1,2,21102,1,133,0,1106,0,303,21202,1,-1,1,22001,223,1,1,21102,148,1,0,1106,0,259,2101,0,1,223,20102,1,221,4,21001,222,0,3,21101,0,17,2,1001,132,-2,224,1002,224,2,224,1001,224,3,224,1002,132,-1,132,1,224,132,224,21001,224,1,1,21101,0,195,0,106,0,109,20207,1,223,2,20102,1,23,1,21102,-1,1,3,21101,0,214,0,1105,1,303,22101,1,1,1,204,1,99,0,0,0,0,109,5,2102,1,-4,249,22101,0,-3,1,21202,-2,1,2,21202,-1,1,3,21102,1,250,0,1105,1,225,22102,1,1,-4,109,-5,2106,0,0,109,3,22107,0,-2,-1,21202,-1,2,-1,21201,-1,-1,-1,22202,-1,-2,-2,109,-3,2105,1,0,109,3,21207,-2,0,-1,1206,-1,294,104,0,99,22102,1,-2,-2,109,-3,2106,0,0,109,5,22207,-3,-4,-1,1206,-1,346,22201,-4,-3,-4,21202,-3,-1,-1,22201,-4,-1,2,21202,2,-1,-1,22201,-4,-1,1,21202,-2,1,3,21101,0,343,0,1105,1,303,1105,1,415,22207,-2,-3,-1,1206,-1,387,22201,-3,-2,-3,21202,-2,-1,-1,22201,-3,-1,3,21202,3,-1,-1,22201,-3,-1,2,21201,-4,0,1,21102,384,1,0,1106,0,303,1105,1,415,21202,-4,-1,-4,22201,-4,-3,-4,22202,-3,-2,-2,22202,-2,-4,-4,22202,-3,-2,-3,21202,-4,-1,-2,22201,-3,-2,1,21202,1,1,-4,109,-5,2106,0,0";
    private static int MAX_SIZE = 50;
    private static int[][] grid;

    public static void solve() {
        grid = new int[MAX_SIZE][MAX_SIZE];
        Computer computer = Computer.make();
        computer.setLoggingFlag(false);

        int count = 0;
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                computer.loadProgramInMemory(input);
                computer.giveInputAndExecute((long) i);
                computer.getOutput().clear();
                computer.giveInputAndExecute((long) j);

                int output = Integer.parseInt(computer.getOutput().getOutput());
                computer.getOutput().clear();
                grid[i][j] = output;

                if (output == 1) {
                    count++;
                }
            }
        }

        System.out.println("Answer: " + count); // 141
        showGrid();
    }

    public static void solvePart2() {
        Computer computer = Computer.make();
        computer.setLoggingFlag(false);

        Grid.Pos beamStartPos = null;

        // Find the start position of the beam in the very first row where its encountered first
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                computer.loadProgramInMemory(input);
                computer.giveInputAndExecute((long) i);
                computer.getOutput().clear();
                computer.giveInputAndExecute((long) j);

                int output = Integer.parseInt(computer.getOutput().getOutput());
                computer.getOutput().clear();
                if (output == 1) {
                    beamStartPos = Grid.Pos.of(i, j);
                    break;
                }
            }
        }

        int times = 100;

        // Move 100 rows down so that we are big enough to put the ship in the beam
        while (times-- > 0) {
            Grid.Pos currentPos = beamStartPos.moveDownGrid(1);
            while (true) {
                int output = getOutput(computer, currentPos);
                if (output == 1) {
                    break;
                }
                currentPos = currentPos.moveRightGrid(1);
            }
            beamStartPos = currentPos;
        }


        // Now taking the start pos as the bottom left corner, we calculate the top right corner
        // and check whether its within the beam
        // Keep moving down and checking the corners until we find the solution
        while (true) {
            Grid.Pos bottomLeft = beamStartPos.copy();
            Grid.Pos topRight = bottomLeft.moveRightGrid(99).moveUpGrid(99);

            int output = getOutput(computer, topRight);

            if (output == 1) {
                output = getOutput(computer, topRight.moveRightGrid(1));
                if (output == 0) {
                    break; // VOILA !!!
                }
            }

            Grid.Pos currentPos = beamStartPos.moveDownGrid(1);
            while (true) {
                output = getOutput(computer, currentPos);
                if (output == 1) {
                    break;
                }
                currentPos = currentPos.moveRightGrid(1);
            }
            beamStartPos = currentPos;
        }

        Grid.Pos topLeft = beamStartPos.moveUpGrid(99);
        int ans = (topLeft.i() * 10000) + topLeft.j();
        System.out.println("Answer: " + ans); // 15641348
    }

    private static int getOutput(Computer computer, Grid.Pos pos) {
        computer.loadProgramInMemory(input);
        computer.giveInputAndExecute((long) pos.i());
        computer.getOutput().clear();
        computer.giveInputAndExecute((long) pos.j());

        int output = Integer.parseInt(computer.getOutput().getOutput());
        computer.getOutput().clear();
        return output;
    }

    private static void showGrid() {
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                System.out.print(grid[i][j] == 0 ? '.' : '#');
            }
            System.out.println();
        }
        System.out.println();
    }
}
