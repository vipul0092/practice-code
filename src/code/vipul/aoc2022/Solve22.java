package code.vipul.aoc2022;

import code.vipul.Pair;
import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 22/12/22
 * https://adventofcode.com/2022/day/22
 */
public class Solve22 {

    private static final String INPUT =
            "        ...#\n" +
                    "        .#..\n" +
                    "        #...\n" +
                    "        ....\n" +
                    "...#.......#\n" +
                    "........#...\n" +
                    "..#....#....\n" +
                    "..........#.\n" +
                    "        ...#....\n" +
                    "        .....#..\n" +
                    "        .#......\n" +
                    "        ......#.\n" +
                    "\n" +
                    "10R5L5R10L4R5L5";

    public enum Direction {
        RIGHT,
        LEFT,
        DOWN,
        UP;

        public Direction move(char movement) {
            switch (this) {
                case RIGHT:
                    return movement == 'R' ? DOWN : UP;
                case LEFT:
                    return movement == 'R' ? UP : DOWN;
                case UP:
                    return movement == 'R' ? RIGHT : LEFT;
                case DOWN:
                    return movement == 'R' ? LEFT : RIGHT;
                default:
                    throw new RuntimeException();
            }
        }

        public Grid.Pos move(Grid.Pos pos) {
            switch (this) {
                case RIGHT:
                    return pos.moveRight();
                case LEFT:
                    return pos.moveLeft();
                case UP:
                    return pos.moveUp();
                case DOWN:
                    return pos.moveDown();
                default:
                    throw new RuntimeException();
            }
        }

        public int value() {
            switch (this) {
                case RIGHT:
                    return 0;
                case LEFT:
                    return 2;
                case UP:
                    return 3;
                case DOWN:
                    return 1;
                default:
                    throw new RuntimeException();
            }
        }
    }

    private static Map<Integer, TreeSet<Grid.Pos>> rows;
    private static Map<Integer, TreeSet<Grid.Pos>> columns;
    private static Map<Grid.Pos, Character> values;
    private static String movement;

    private static List<EdgeTransition> edgeTransitions;

    public static void solve() {
        parse(Inputs.INPUT_22);

        Grid.Pos position = rows.get(0).first();
        Direction direction = Direction.RIGHT;

        boolean firstMoveDone = false;
        for (int i = 0; i < movement.length(); ) {
            if (!firstMoveDone) {
                StringBuilder sb = new StringBuilder();
                while (!(movement.charAt(i) == 'R' || movement.charAt(i) == 'L')) {
                    sb.append(movement.charAt(i));
                    i++;
                }
                int steps = Integer.parseInt(sb.toString());
                position = move(position, direction, steps);
                firstMoveDone = true;
            } else {
                char mv = movement.charAt(i);
                i++;
                StringBuilder sb = new StringBuilder();
                while (i < movement.length() && !(movement.charAt(i) == 'R' || movement.charAt(i) == 'L')) {
                    sb.append(movement.charAt(i));
                    i++;
                }
                int steps = Integer.parseInt(sb.toString());
                direction = direction.move(mv);
                position = move(position, direction, steps);
            }
        }

        int ans = (1000 * (position.i() + 1)) + (4 * (position.j() + 1) + direction.value());
        System.out.println(ans);
    }

    public static void solvePart2() {
        parse(Inputs.INPUT_22);

        Grid.Pos position = rows.get(0).first();
        Direction direction = Direction.RIGHT;

        boolean firstMoveDone = false;
        populateEdgesAndTransitions();
        for (int i = 0; i < movement.length(); ) {
            if (!firstMoveDone) {
                StringBuilder sb = new StringBuilder();
                while (!(movement.charAt(i) == 'R' || movement.charAt(i) == 'L')) {
                    sb.append(movement.charAt(i));
                    i++;
                }
                int steps = Integer.parseInt(sb.toString());
                var moveResult = movePart2(position, direction, steps);
                position = moveResult.left();
                direction = moveResult.right();
                firstMoveDone = true;
            } else {
                char mv = movement.charAt(i);
                i++;
                StringBuilder sb = new StringBuilder();
                while (i < movement.length() && !(movement.charAt(i) == 'R' || movement.charAt(i) == 'L')) {
                    sb.append(movement.charAt(i));
                    i++;
                }
                int steps = Integer.parseInt(sb.toString());
                direction = direction.move(mv);
                var moveResult = movePart2(position, direction, steps);
                position = moveResult.left();
                direction = moveResult.right();
            }
        }

        int ans = (1000 * (position.i() + 1)) + (4 * (position.j() + 1) + direction.value());
        System.out.println(ans);
    }

    private static Grid.Pos move(Grid.Pos start, Direction direction, int steps) {
        Grid.Pos current = start;
        while (steps-- > 0) {
            Grid.Pos next = direction.move(current);
            if (!values.containsKey(next)) { // point is off the map
                switch (direction) {
                    case RIGHT: { // moving right, wrap around to first point in the row
                        next = rows.get(current.i()).first();
                        break;
                    }
                    case LEFT: { // moving left, wrap around to last point in the row
                        next = rows.get(current.i()).last();
                        break;
                    }
                    case UP: { // moving up, wrap around to last point in the column
                        next = columns.get(current.j()).last();
                        break;
                    }
                    case DOWN: { // moving down, wrap around to first point in the column
                        next = columns.get(current.j()).first();
                        break;
                    }
                    default:
                        throw new RuntimeException();
                }
            }

            char val = values.get(next);
            if (val == '#') { // we cant move forward, return the current position
                break;
            } else {
                current = next;
            }
        }
        return current;
    }

    private static Pair<Grid.Pos, Direction> movePart2(Grid.Pos start, Direction direction, int steps) {
        Grid.Pos current = start;
        Direction currentDir = direction;
        while (steps-- > 0) {
            Grid.Pos next = currentDir.move(current);
            Direction nextDirection = currentDir;
            if (!values.containsKey(next)) {
                // Calculate the next moved position and direction when the point switches from one face to another
                EdgeTransition applicableTransition = getApplicableTransition(current, currentDir);
                next = applicableTransition.transition(current);
                nextDirection = applicableTransition.changedDirection;
            }
            char val = values.get(next);
            if (val == '#') { // we cant move forward, return the current position and direction
                break;
            } else {
                current = next;
                currentDir = nextDirection;
            }
        }
        return Pair.of(current, currentDir);
    }

    // Faces are named like this, we create the edges and link the transitions
    //   |5|6|
    //   |4|
    // |2|1|
    // |3|
    private static void populateEdgesAndTransitions() {
        Edge SIX_RIGHT = new Edge("6 right", 0, 49, 149, 149);
        Edge SIX_TOP = new Edge("6 top ", 0, 0, 100, 149);
        Edge SIX_BOT = new Edge("6 bot", 49, 49, 100, 149);

        Edge FIVE_LEFT = new Edge("5 left", 0, 49, 50, 50);
        Edge FIVE_TOP = new Edge("5 top", 0, 0, 50, 99);

        Edge FOUR_LEFT = new Edge("4 left", 50, 99, 50, 50);
        Edge FOUR_RIGHT = new Edge("4 right", 50, 99, 99, 99);

        Edge ONE_RIGHT = new Edge("1 right", 100, 149, 99, 99);
        Edge ONE_BOT = new Edge("1 bot", 149, 149, 50, 99);

        Edge TWO_LEFT = new Edge("2 left", 100, 149, 0, 0);
        Edge TWO_TOP = new Edge("2 top", 100, 100, 0, 49);

        Edge THREE_RIGHT = new Edge("3 right", 150, 199, 49, 49);
        Edge THREE_LEFT = new Edge("3 left", 150, 199, 0, 0);
        Edge THREE_BOT = new Edge("3 bot", 199, 199, 0, 49);

        edgeTransitions = new ArrayList<>();
        edgeTransitions.add(new EdgeTransition(SIX_RIGHT, Direction.RIGHT, ONE_RIGHT, Direction.LEFT, true));
        edgeTransitions.add(new EdgeTransition(ONE_RIGHT, Direction.RIGHT, SIX_RIGHT, Direction.LEFT, true));
        edgeTransitions.add(new EdgeTransition(FIVE_LEFT, Direction.LEFT, TWO_LEFT, Direction.RIGHT, true));
        edgeTransitions.add(new EdgeTransition(TWO_LEFT, Direction.LEFT, FIVE_LEFT, Direction.RIGHT, true));
        edgeTransitions.add(new EdgeTransition(SIX_TOP, Direction.UP, THREE_BOT, Direction.UP, false));
        edgeTransitions.add(new EdgeTransition(THREE_BOT, Direction.DOWN, SIX_TOP, Direction.DOWN, false));
        edgeTransitions.add(new EdgeTransition(SIX_BOT, Direction.DOWN, FOUR_RIGHT, Direction.LEFT, false));
        edgeTransitions.add(new EdgeTransition(FOUR_RIGHT, Direction.RIGHT, SIX_BOT, Direction.UP, false));
        edgeTransitions.add(new EdgeTransition(FIVE_TOP, Direction.UP, THREE_LEFT, Direction.RIGHT, false));
        edgeTransitions.add(new EdgeTransition(THREE_LEFT, Direction.LEFT, FIVE_TOP, Direction.DOWN, false));
        edgeTransitions.add(new EdgeTransition(FOUR_LEFT, Direction.LEFT, TWO_TOP, Direction.DOWN, false));
        edgeTransitions.add(new EdgeTransition(TWO_TOP, Direction.UP, FOUR_LEFT, Direction.RIGHT, false));
        edgeTransitions.add(new EdgeTransition(ONE_BOT, Direction.DOWN, THREE_RIGHT, Direction.LEFT, false));
        edgeTransitions.add(new EdgeTransition(THREE_RIGHT, Direction.RIGHT, ONE_BOT, Direction.UP, false));
    }

    private static EdgeTransition getApplicableTransition(Grid.Pos point, Direction currentDirection) {
        return edgeTransitions.stream().filter(e -> e.canTransition(point, currentDirection)).findFirst().orElseThrow();
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        rows = new LinkedHashMap<>();
        columns = new LinkedHashMap<>();
        values = new LinkedHashMap<>();

        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            if (in.isEmpty()) {
                movement = inputs.get(i + 1);
                break;
            }
            rows.put(i, new TreeSet<>());
            for (int j = 0; j < in.length(); j++) {
                char ch = in.charAt(j);
                if (ch == '.' || ch == '#') {
                    columns.putIfAbsent(j, new TreeSet<>());
                    Grid.Pos pos = Grid.Pos.of(i, j);
                    rows.get(i).add(pos);
                    columns.get(j).add(pos);
                    values.put(pos, ch);
                }
            }
        }
    }

    private static class EdgeTransition {
        private final Edge from;
        private final Direction initialDirection;
        private final Edge to;
        private final Direction changedDirection;
        private final boolean isAsymmetricTransition;

        public EdgeTransition(Edge from, Direction initialDirection, Edge to, Direction changedDirection,
                              boolean isAsymmetricTransition) {
            this.from = from;
            this.initialDirection = initialDirection;
            this.to = to;
            this.changedDirection = changedDirection;
            this.isAsymmetricTransition = isAsymmetricTransition;
        }

        public boolean canTransition(Grid.Pos point, Direction currentDirection) {
            return initialDirection == currentDirection && from.contains(point);
        }

        public Grid.Pos transition(Grid.Pos position) {
            int finalI = -1, finalJ = -1, diff = -1;
            if (isAsymmetricTransition) {
                diff = from.isHorizontal() ? Math.abs(position.j() - from.jmax) : Math.abs(position.i() - from.imax);
            } else {
                diff = from.isHorizontal() ? Math.abs(position.j() - from.jmin) : Math.abs(position.i() - from.imin);
            }
            if (to.isHorizontal()) {
                finalI = to.imin;
                finalJ = to.jmin + diff;
            } else {
                finalI = to.imin + diff;
                finalJ = to.jmin;
            }
            return Grid.Pos.of(finalI, finalJ);
        }
    }

    public static class Edge {
        private final String name;
        private final int imin;
        private final int imax;
        private final int jmin;
        private final int jmax;

        public Edge(String name, int imin, int imax, int jmin, int jmax) {
            this.name = name;
            this.imin = imin;
            this.imax = imax;
            this.jmin = jmin;
            this.jmax = jmax;
        }

        public boolean isHorizontal() {
            return imax == imin;
        }

        public boolean contains(Grid.Pos pos) {
            return pos.i() >= imin && pos.i() <= imax && pos.j() >= jmin && pos.j() <= jmax;
        }
    }
}
