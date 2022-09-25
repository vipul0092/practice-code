package code.vipul.aoc2020;

import code.vipul.aoc2019.Grid;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;

import static code.vipul.aoc2020.Inputs2.INPUT_12;
import static java.util.stream.Collectors.toMap;

/**
 * https://adventofcode.com/2020/day/12
 */
public class Solve12 {

    private static final String INPUT = "F10\n" +
            "N3\n" +
            "F7\n" +
            "R90\n" +
            "F11";

    private static Direction currentDirection;
    private static Grid.Pos waypointRelativePos;
    private static Grid.Pos shipPos;

    public static void solve() {
        currentDirection = Direction.E;
        Grid.Pos currentPos = Grid.Pos.of(0, 0);

        for (String command : INPUT_12.split("\n")) {
            if (command.startsWith("L") || command.startsWith("R")) {
                changeCurrentDirection(command.charAt(0), Integer.parseInt(command.substring(1)));
            } else if (command.startsWith("F")) {
                currentPos = currentDirection.mover.apply(currentPos, Integer.parseInt(command.substring(1)));
            } else {
                Direction toMove = Direction.byCode(command.charAt(0));
                currentPos = toMove.mover.apply(currentPos, Integer.parseInt(command.substring(1)));
            }
        }

        int answer = Math.abs(currentPos.i()) + Math.abs(currentPos.j());

        System.out.println("Answer: " + answer); //2458
    }

    public static void solvePart2() {
        shipPos = Grid.Pos.of(0, 0);
        waypointRelativePos = Grid.Pos.of(10, 1);

        for (String command : INPUT_12.split("\n")) {
            if (command.startsWith("L") || command.startsWith("R")) {
                rotateWaypoint(command.charAt(0), Integer.parseInt(command.substring(1)));
            } else if (command.startsWith("F")) {
                moveShip(Integer.parseInt(command.substring(1)));
            } else {
                moveWaypoint(command.charAt(0), Integer.parseInt(command.substring(1)));
            }
        }

        int answer = Math.abs(shipPos.i()) + Math.abs(shipPos.j());

        System.out.println("Answer: " + answer); //145117
    }


    private static void changeCurrentDirection(Character rotationType, int degrees) {
        if (rotationType == 'L') {
            degrees = 360 - degrees;
        }
        int rotations = degrees / 90;
        int id = (currentDirection.id + rotations) % 4;
        currentDirection = Direction.byId(id);
    }

    private static void moveShip(int value) {
        shipPos = Grid.Pos.of(
                shipPos.i() + (waypointRelativePos.i() * value),
                shipPos.j() + (waypointRelativePos.j() * value));
    }

    private static void moveWaypoint(char dir, int units) {
        Direction toMove = Direction.byCode(dir);
        waypointRelativePos = toMove.mover.apply(waypointRelativePos, units);
    }

    private static void rotateWaypoint(Character rotationType, int degrees) {
        if (rotationType == 'R') {
            degrees = 360 - degrees;
        }
        int rotations = degrees / 90;

        while (rotations-- > 0) {
            waypointRelativePos = Grid.Pos.of(-waypointRelativePos.j(), waypointRelativePos.i());
        }
    }

    public enum Direction {
        N(0, 'N', (p, s) -> p.moveUp(s)),
        E(1, 'E', (p, s) -> p.moveRight(s)),
        S(2, 'S', (p, s) -> p.moveDown(s)),
        W(3, 'W', (p, s) -> p.moveLeft(s));

        private final int id;
        private final char code;
        private final BiFunction<Grid.Pos, Integer, Grid.Pos> mover;
        private static final Map<Integer, Direction> idMap = Arrays.stream(values()).collect(toMap(d -> d.id, d -> d));
        private static final Map<Character, Direction> codeMap =
                Arrays.stream(values()).collect(toMap(d -> d.code, d -> d));

        Direction(int id, char code, BiFunction<Grid.Pos, Integer, Grid.Pos> mover) {
            this.id = id;
            this.code = code;
            this.mover = mover;
        }

        public static Direction byId(int id) {
            return idMap.get(id);
        }

        public static Direction byCode(char code) {
            return codeMap.get(code);
        }
    }
}
