package code.vipul.aoc2022;

import code.vipul.Pair;
import code.vipul.aoc2019.Grid;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 22/12/22
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
                case RIGHT: return movement == 'R' ? DOWN : UP;
                case LEFT: return movement == 'R' ? UP : DOWN;
                case UP: return movement == 'R' ? RIGHT : LEFT;
                case DOWN: return movement == 'R' ? LEFT : RIGHT;
                default: throw new RuntimeException();
            }
        }

        public Grid.Pos move(Grid.Pos pos) {
            switch (this) {
                case RIGHT: return pos.moveRight();
                case LEFT: return pos.moveLeft();
                case UP: return pos.moveUp();
                case DOWN: return pos.moveDown();
                default: throw new RuntimeException();
            }
        }

        public int value() {
            switch (this) {
                case RIGHT: return 0;
                case LEFT: return 2;
                case UP: return 3;
                case DOWN: return 1;
                default: throw new RuntimeException();
            }
        }
    }

    private static  Map<Integer, TreeSet<Grid.Pos>> rows;
    private static  Map<Integer, TreeSet<Grid.Pos>> columns;
    private static Map<Grid.Pos, Character> values;
    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_22.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        rows = new LinkedHashMap<>();
        columns = new LinkedHashMap<>();
        values = new LinkedHashMap<>();

        String movement = "";
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            if (in.isEmpty()) {
                movement = inputs.get(i+1);
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

        Grid.Pos position = rows.get(0).first();
        Direction direction = Direction.RIGHT;

        boolean firstMoveDone = false;
        for (int i = 0; i < movement.length();) {
            if (!firstMoveDone) {
                StringBuilder sb = new StringBuilder();
                while(!(movement.charAt(i) == 'R' || movement.charAt(i) == 'L')) {
                    sb.append(movement.charAt(i));
                    i++;
                }
                int steps = Integer.parseInt(sb.toString());
                position = move(position, direction, steps);
                firstMoveDone = true;
            } else {
                char mv = movement.charAt(i); i++;
                StringBuilder sb = new StringBuilder();
                while(i < movement.length() && !(movement.charAt(i) == 'R' || movement.charAt(i) == 'L')) {
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
        List<String> inputs = Arrays.stream(Inputs.INPUT_22.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        rows = new LinkedHashMap<>();
        columns = new LinkedHashMap<>();
        values = new LinkedHashMap<>();

        String movement = "";
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            if (in.isEmpty()) {
                movement = inputs.get(i+1);
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

        Grid.Pos position = rows.get(0).first();
        Direction direction = Direction.RIGHT;

        boolean firstMoveDone = false;
        pop();
        for (int i = 0; i < movement.length();) {
            if (!firstMoveDone) {
                StringBuilder sb = new StringBuilder();
                while(!(movement.charAt(i) == 'R' || movement.charAt(i) == 'L')) {
                    sb.append(movement.charAt(i));
                    i++;
                }
                int steps = Integer.parseInt(sb.toString());
                var moveResult = movePart2(position, direction, steps);
                position = moveResult.left();
                direction = moveResult.right();
                firstMoveDone = true;
            } else {
                char mv = movement.charAt(i); i++;
                StringBuilder sb = new StringBuilder();
                while(i < movement.length() && !(movement.charAt(i) == 'R' || movement.charAt(i) == 'L')) {
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
                    default: throw new RuntimeException();
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
            if (!values.containsKey(next)) { // point is off the map, that means the current point is on an edge
                // Calculate the next moved position and direction when the point switches from one face to another
                var moveResult = moveAcrossEdge(current, currentDir);
                next = moveResult.left();
                nextDirection = moveResult.right();
            }
            char val = values.get(next);
            if (val == '#') { // we cant move forward, return the current position
                break;
            } else {
                current = next;
                currentDir = nextDirection;
            }
        }
        return Pair.of(current, currentDir);
    }

    private static Set<Edge> edges;
    private static Map<Edge, Pair<Edge, Direction>> sameDir;
    private static Map<Edge, Pair<Edge, Direction>> oppDir;

    //   5 6
    //   4
    // 2 1
    // 3
    private static void pop() {
        sameDir = new HashMap<>();
        oppDir = new HashMap<>();
        edges = new HashSet<>();

        Edge SIX_RIGHT = new Edge("6 right", 0, 49, 149, 149);
        Edge SIX_TOP = new Edge("6 top ",0, 0, 100, 149);
        Edge SIX_BOT = new Edge("6 bot", 49, 49, 100, 149);

        Edge FIVE_LEFT = new Edge("5 left", 0, 49, 50, 50);
        Edge FIVE_TOP = new Edge("5 top",0, 0, 50, 99);

        Edge FOUR_LEFT = new Edge("4 left",50, 99, 50, 50);
        Edge FOUR_RIGHT = new Edge("4 right", 50, 99, 99, 99);

        Edge ONE_RIGHT = new Edge("1 right", 100, 149, 99, 99);
        Edge ONE_BOT = new Edge("1 bot", 149, 149, 50, 99);

        Edge TWO_LEFT = new Edge("2 left", 100, 149, 0, 0);
        Edge TWO_TOP = new Edge("2 top", 100, 100, 0, 49);

        Edge THREE_RIGHT = new Edge("3 right", 150, 199, 49, 49);
        Edge THREE_LEFT = new Edge("3 left",150, 199, 0, 0);
        Edge THREE_BOT = new Edge("3 bot", 199, 199, 0, 49);


        sameDir.put(SIX_TOP, Pair.of(THREE_BOT, Direction.UP));
        sameDir.put(THREE_BOT,  Pair.of(SIX_TOP, Direction.DOWN));
        sameDir.put(SIX_BOT,  Pair.of(FOUR_RIGHT, Direction.LEFT));
        sameDir.put(FOUR_RIGHT,  Pair.of(SIX_BOT, Direction.UP));
        sameDir.put(FIVE_TOP,  Pair.of(THREE_LEFT, Direction.RIGHT));
        sameDir.put(THREE_LEFT,  Pair.of(FIVE_TOP, Direction.DOWN));
        sameDir.put(FOUR_LEFT,  Pair.of(TWO_TOP, Direction.DOWN));
        sameDir.put(TWO_TOP,  Pair.of(FOUR_LEFT, Direction.RIGHT));
        sameDir.put(ONE_BOT,  Pair.of(THREE_RIGHT, Direction.LEFT));
        sameDir.put(THREE_RIGHT,  Pair.of(ONE_BOT, Direction.UP));


        oppDir.put(SIX_RIGHT, Pair.of(ONE_RIGHT, Direction.LEFT));
        oppDir.put(ONE_RIGHT, Pair.of(SIX_RIGHT, Direction.LEFT));
        oppDir.put(FIVE_LEFT, Pair.of(TWO_LEFT, Direction.RIGHT));
        oppDir.put(TWO_LEFT, Pair.of(FIVE_LEFT, Direction.RIGHT));

        edges.addAll(sameDir.keySet());
        edges.addAll(oppDir.keySet());
    }




    private static Edge getContainingEdge(Grid.Pos pos, Direction direction) {
        List<Edge> containers = edges.stream().filter(e -> {
            if (e.isHorizontal()) {
                return e.imax == pos.i() && pos.j() >= e.jmin && pos.j() <= e.jmax;
            } else {
                return e.jmax == pos.j() && pos.i() >= e.imin && pos.i() <= e.imax;
            }
        }).collect(Collectors.toList());

        if (containers.size() == 1) {
            return containers.get(0);
        } else {
            Edge container1 = containers.get(0);
            Edge container2 = containers.get(1);

            if (direction == Direction.RIGHT || direction == Direction.LEFT) {
                return container1.isHorizontal() ? container2 : container1;
            } else {
                return container1.isHorizontal() ? container1 : container2;
            }
        }
    }

    private static Pair<Grid.Pos, Direction> moveAcrossEdge(Grid.Pos pos, Direction movingDirection) {
        Edge at = getContainingEdge(pos, movingDirection);

        Edge to;
        if (sameDir.containsKey(at)) {
            var edgeDir = sameDir.get(at);
            to = edgeDir.left();
            int finalI = -1, finalJ = -1;
            int diff;
            if (at.isHorizontal()) {
                diff = Math.abs(pos.j() - at.jmin);
            } else {
                diff = Math.abs(pos.i() - at.imin);
            }

            if (to.isHorizontal()) {
                finalI = to.imin;
                finalJ = to.jmin + diff;
            } else {
                finalI = to.imin + diff;
                finalJ = to.jmin;
            }
            Grid.Pos moved = Grid.Pos.of(finalI, finalJ);
            return Pair.of(moved, edgeDir.right());
        } else {
            var edgeDir = oppDir.get(at);
            to = edgeDir.left();
            int finalI = -1, finalJ = -1;
            int diff;
            if (at.isHorizontal()) {
                diff = Math.abs(pos.j() - at.jmax);
            } else {
                diff = Math.abs(pos.i() - at.imax);
            }

            if (to.isHorizontal()) {
                finalI = to.imin;
                finalJ = to.jmin + diff;
            } else {
                finalI = to.imin + diff;
                finalJ = to.jmin;
            }
            Grid.Pos moved = Grid.Pos.of(finalI, finalJ);
            return Pair.of(moved, edgeDir.right());
        }
    }


    public static class Edge {
        private final String name;
        private final int imin;
        private final int imax;
        private final int jmin;
        private final int jmax;
        private int hash = -1;

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

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(imax);
                hash += (hash << 5) + Objects.hashCode(imin);
                hash += (hash << 5) + Objects.hashCode(jmin);
                hash += (hash << 5) + Objects.hashCode(jmax);

            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof Edge && equalTo((Edge) another);
        }

        private boolean equalTo(Edge another) {
            return Objects.equals(imax, another.imax) && Objects.equals(imin, another.imin)
                    && Objects.equals(jmax, another.jmax) && Objects.equals(jmin, another.jmin);
        }
    }
}
