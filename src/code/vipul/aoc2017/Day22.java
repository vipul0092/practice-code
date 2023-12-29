package code.vipul.aoc2017;

import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2017.inputs.Inputs.DAY_22;

/**
 * Created by vgaur created on 29/12/23
 */
public class Day22 {

    private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    private static String INPUT = """
            ..#
            #..
            ...
            """;

    public static void solve() {
        INPUT = DAY_22;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        System.out.println("Part 1: " + runSimulation(lines, true, 10000)); // 5330
        System.out.println("Part 2: " + runSimulation(lines, false, 10000000)); // 2512103
    }

    private static int runSimulation(List<String> lines, boolean part1, int times) {
        Point current = new Point(lines.size() / 2, lines.get(0).length() / 2);
        State[][] states = new State[1000][1000];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '#') {
                    setState(i, j, states, State.INFECTED);
                }
            }
        }
        State currentState;
        int direction = UP;
        int newinfected = 0;
        while (times-- > 0) {
            currentState = getState(current, states);
            if (currentState == null) {
                currentState = State.CLEAN;
            }
            if (currentState == State.CLEAN) {
                direction = turn(direction, true);
            } else if (currentState == State.INFECTED) {
                direction = turn(direction, false);
            } else if (currentState == State.FLAGGED) {
                direction = reverse(direction);
            }

            if (currentState == State.CLEAN) {
                currentState = part1 ? State.INFECTED : State.WEAKENED;
                if (part1 && getState(current, states) != State.INFECTED) {
                    newinfected++;
                }
            } else if (currentState == State.WEAKENED) {
                currentState = State.INFECTED;
                if (!part1 && getState(current, states) != State.INFECTED) {
                    newinfected++;
                }
            } else if (currentState == State.INFECTED) {
                currentState = part1 ? State.CLEAN : State.FLAGGED;
            } else if (currentState == State.FLAGGED) {
                currentState = State.CLEAN;
            }
            setState(current.x, current.y, states, currentState);
            current = current.move(direction);
        }
        return newinfected;
    }

    static State getState(Point p, State[][] states) {
        var state = states[p.x + 200][p.y + 200];
        return state == null ? State.CLEAN : state;
    }

    static void setState(int x, int y, State[][] states, State state) {
        states[x + 200][y + 200] = state;
    }

    private static int turn(int dir, boolean left) {
        return switch (dir) {
            case UP -> left ? LEFT : RIGHT;
            case DOWN -> left ? RIGHT : LEFT;
            case LEFT -> left ? DOWN : UP;
            case RIGHT -> left ? UP : DOWN;
            default -> throw new RuntimeException("Impossivel");
        };
    }

    private static int reverse(int dir) {
        return switch (dir) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> throw new RuntimeException("Impossivel");
        };
    }

    private enum State {
        INFECTED,
        CLEAN,
        WEAKENED,
        FLAGGED
    }

    record Point(int x, int y) {
        public Point move(int dir) {
            return switch (dir) {
                case UP -> new Point(x - 1, y);
                case DOWN -> new Point(x + 1, y);
                case LEFT -> new Point(x, y - 1);
                case RIGHT -> new Point(x, y + 1);
                default -> throw new RuntimeException("Impossivel");
            };
        }
    }
}
