package code.vipul.aoc2018;

import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2018/day/15
 */
public class Solve15 {

    private static int elfAttackPower = 3;
    private static final int goblinAttackPower = 3;

    private static int creatureNumber = 0;
    private static TreeMap<Grid.Pos, Integer> creaturePositions;
    private static Map<Integer, Creature> creatures;
    private static int[][] grid;
    private static int rounds = 0;
    private static boolean wasElfKilled = false;

    private static String input;

    public static void solve() {

        // 37 rounds, answer -> 36334
//        input =  "#######\n" +
//                "#G..#E#\n" +
//                "#E#E.E#\n" +
//                "#G.##.#\n" +
//                "#...#E#\n" +
//                "#...E.#\n" +
//                "#######";

        // 46 rounds, answer -> 39514
//        input = "#######\n" +
//                "#E..EG#\n" +
//                "#.#G.E#\n" +
//                "#E.##E#\n" +
//                "#G..#.#\n" +
//                "#..E#.#\n" +
//                "#######";

        // 35 rounds, answer -> 27755
//        input = "#######\n" +
//                "#E.G#.#\n" +
//                "#.#G..#\n" +
//                "#G.#.G#\n" +
//                "#G..#.#\n" +
//                "#...E.#\n" +
//                "#######";

        // 20 rounds, answer -> 18740
//        input = "#########\n" +
//                "#G......#\n" +
//                "#.E.#...#\n" +
//                "#..##..G#\n" +
//                "#...##..#\n" +
//                "#...#...#\n" +
//                "#.G...G.#\n" +
//                "#.....G.#\n" +
//                "#########";

        input = Inputs.DAY15;

        parseInput();
        while (moveCreatures()) ;

        AtomicInteger sumOfScores = new AtomicInteger(0);
        creatures.forEach((num, creature) -> sumOfScores.addAndGet(creature.hitPoints));

        System.out.println("Answer: " + ((rounds - 1) * sumOfScores.intValue())); // 206720
    }

    public static void solvePart2() {
        input = Inputs.DAY15;

        while (true) {
            wasElfKilled = false;
            creatureNumber = 0;
            rounds = 0;
            parseInput();
            boolean atleastOneActiontaken = true;
            while (atleastOneActiontaken) {
                atleastOneActiontaken = moveCreatures();
                if (wasElfKilled) {
                    System.out.println("An elf was killed, increasing elf attack power");
                    break;
                }
            }
            if (!wasElfKilled) {
                break;
            }
            elfAttackPower++;
        }

        AtomicInteger sumOfScores = new AtomicInteger(0);
        creatures.forEach((num, creature) -> sumOfScores.addAndGet(creature.hitPoints));

        System.out.println("Answer: " + ((rounds - 1) * sumOfScores.intValue()));  // 37992

    }

    private static boolean moveCreatures() {
        TreeMap<Grid.Pos, Integer> newPositions = new TreeMap<>();
        boolean atLeastOneActionWasTaken = false;

        var allOneType = creatures.values().stream().map(c -> c.type).collect(Collectors.toSet()).size() == 1;
        if (allOneType) {
            return false;
        }

        for (var entry : creaturePositions.entrySet()) {
            Grid.Pos source = entry.getKey();
            int sourceNumber = entry.getValue();
            if (valueAt(source) == 0 || !creatures.containsKey(sourceNumber)) { // killed off
                continue;
            }
            newPositions.put(source, sourceNumber);
            Creature sourceCreature = creatures.get(sourceNumber);

            Map<Grid.Pos, Creature> creaturesToGoTo = creatures.values().stream()
                    .filter(c -> c.type == sourceCreature.movementTowardsType)
                    .collect(Collectors.toMap(c -> c.currentPos, c -> c));

            // try to attack if we can
            var didAttack = attack(creaturesToGoTo, source, newPositions);
            if (didAttack) {
                atLeastOneActionWasTaken = true;
                continue;
            }

            Set<Grid.Pos> positionsToMoveTo = creaturesToGoTo.values().stream()
                    .map(c -> reachablePositions(c.currentPos, true))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());

            Optional<Grid.Pos> positionToMove = calculateCreatureMovementPos(positionsToMoveTo, source);
            if (positionToMove.isPresent()) {
                newPositions.remove(source);
                newPositions.put(positionToMove.get(), sourceNumber);
                creatures.get(sourceNumber).moveTo(positionToMove.get());
                setGridValue(positionToMove.get(), 2);
                setGridValue(source, 0);

                // Try to attack if we can from the new position
                attack(creaturesToGoTo, positionToMove.get(), newPositions);

                atLeastOneActionWasTaken = true;
            }
        }
        creaturePositions = newPositions;

        rounds++;
        // displayGrid();
        return atLeastOneActionWasTaken;
    }

    private static boolean attack(Map<Grid.Pos, Creature> creaturesToGoTo,
                                  Grid.Pos attackerPos, TreeMap<Grid.Pos, Integer> positions) {
        boolean didAttack = false;

        if (creaturesToGoTo.isEmpty()) {
            return false;
        }

        var posAroundSource = reachablePositions(attackerPos, false);
        TreeMap<Integer, TreeSet<Grid.Pos>> toAttack = new TreeMap<>();
        for (Grid.Pos pos : posAroundSource) {
            if (creaturesToGoTo.containsKey(pos)) {
                toAttack.putIfAbsent(creaturesToGoTo.get(pos).hitPoints, new TreeSet<>());
                toAttack.get(creaturesToGoTo.get(pos).hitPoints).add(pos);
            }
        }

        if (!toAttack.isEmpty()) {
            Grid.Pos creatureToAttackPos = toAttack.firstEntry().getValue().first();
            Creature creature = creaturesToGoTo.get(creatureToAttackPos);
            creature.reduceHitPoints();
            if (creature.isDead()) { // Set grid value to zero and remove from active creature positions
                setGridValue(creature.currentPos, 0);
                positions.remove(creatureToAttackPos);
                creatures.remove(creature.creatureNumber);

                wasElfKilled = wasElfKilled || creature.type == 'E';
            }
            didAttack = true;
        }
        return didAttack;
    }

    private static Optional<Grid.Pos> calculateCreatureMovementPos(Set<Grid.Pos> positionsToMoveTo,
                                                                   Grid.Pos source) {

        if (positionsToMoveTo.isEmpty()) {
            return Optional.empty();
        }

        Queue<Grid.Pos> currentPosQ = new ArrayDeque<>();
        Queue<Grid.Pos> comingFromQ = new ArrayDeque<>();
        Queue<Integer> currentLengthQ = new ArrayDeque<>();
        Set<Grid.Pos> visited = new HashSet<>();
        Set<Grid.Pos> addedNeighBours = new HashSet<>();
        visited.add(source);
        TreeMap<Grid.Pos, TreeSet<Grid.Pos>> movingPositionsToSourcePositions = new TreeMap<>();
        int minimumLength = Integer.MAX_VALUE;

        var posAroundSource = reachablePositions(source, true);
        for (Grid.Pos p : posAroundSource) {
            if (valueAt(p) == 2) {
                continue;
            }
            addedNeighBours.add(p);
            currentPosQ.add(p);
            comingFromQ.add(p);
            currentLengthQ.add(1);

            if (positionsToMoveTo.contains(p)) {
                if (minimumLength > 1) {
                    minimumLength = 1;

                    movingPositionsToSourcePositions.put(p, new TreeSet<>());
                    movingPositionsToSourcePositions.get(p).add(p);
                } else {
                    movingPositionsToSourcePositions.putIfAbsent(p, new TreeSet<>());
                    movingPositionsToSourcePositions.get(p).add(p);
                }
            }
        }

        while (!currentPosQ.isEmpty()) {
            Grid.Pos currentPos = currentPosQ.remove();
            int currentLength = currentLengthQ.remove();
            Grid.Pos comingFrom = comingFromQ.remove();

            visited.add(currentPos);
            var neighbours = reachablePositions(currentPos, false);
            for (Grid.Pos neighbour : neighbours) {
                if (visited.contains(neighbour) || valueAt(neighbour) == 2 || addedNeighBours.contains(neighbour)) {
                    continue;
                }

                if (positionsToMoveTo.contains(neighbour)) {
                    if (minimumLength > currentLength + 1) {
                        minimumLength = currentLength + 1;

                        movingPositionsToSourcePositions.remove(neighbour);
                        movingPositionsToSourcePositions.put(neighbour, new TreeSet<>());
                        movingPositionsToSourcePositions.get(neighbour).add(comingFrom);
                    } else if (minimumLength == currentLength + 1) {
                        movingPositionsToSourcePositions.putIfAbsent(neighbour, new TreeSet<>());
                        movingPositionsToSourcePositions.get(neighbour).add(comingFrom);
                    }
                    continue;
                }

                addedNeighBours.add(neighbour);
                currentPosQ.add(neighbour);
                currentLengthQ.add(currentLength + 1);
                comingFromQ.add(comingFrom);
            }
        }
        return movingPositionsToSourcePositions.isEmpty()
                ? Optional.empty()
                : Optional.of(movingPositionsToSourcePositions.firstEntry().getValue().first());
    }

    private static TreeSet<Grid.Pos> reachablePositions(Grid.Pos source,
                                                        boolean fromCreature) {
        TreeSet<Grid.Pos> positions = new TreeSet<>();
        Grid.Pos down = source.moveDownGrid(1);
        Grid.Pos up = source.moveUpGrid(1);
        Grid.Pos left = source.moveLeftGrid(1);
        Grid.Pos right = source.moveRightGrid(1);

        if (canMoveTo(down) || (!fromCreature && valueAt(down) == 2)) {
            positions.add(down);
        }
        if (canMoveTo(up) || (!fromCreature && valueAt(up) == 2)) {
            positions.add(up);
        }
        if (canMoveTo(right) || (!fromCreature && valueAt(right) == 2)) {
            positions.add(right);
        }
        if (canMoveTo(left) || (!fromCreature && valueAt(left) == 2)) {
            positions.add(left);
        }
        return positions;
    }

    private static boolean canMoveTo(Grid.Pos pos) {
        return grid[pos.i()][pos.j()] == 0;
    }

    private static void setGridValue(Grid.Pos pos, int value) {
        grid[pos.i()][pos.j()] = value;
    }

    private static int valueAt(Grid.Pos pos) {
        return grid[pos.i()][pos.j()];
    }

    private static void parseInput() {
        creaturePositions = new TreeMap<>();
        creatures = new HashMap<>();

        String[] rows = input.split("\n");
        int maxCols = rows[0].length();
        int maxRows = rows.length;
        grid = new int[maxRows][maxCols];

        Grid.setMaxRowsCols(maxRows, maxCols);

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                char ch = rows[i].charAt(j);
                grid[i][j] = ch == '.' ? 0 : 1;

                if (ch == 'E' || ch == 'G') {
                    Grid.Pos pos = Grid.Pos.of(i, j);
                    int number = creatureNumber++;
                    Creature creature = Creature.from(pos, ch, number);

                    creatures.put(number, creature);
                    creaturePositions.put(pos, number);
                    grid[i][j] = 2;
                }
            }
        }
    }

    private static void displayGrid() {
        System.out.println(String.format("After %s rounds", rounds));
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    System.out.print('.');
                } else {
                    Grid.Pos pos = Grid.Pos.of(i, j);
                    if (grid[i][j] == 2) {
                        System.out.print(creatures.get(creaturePositions.get(pos)).type);
                    } else {
                        System.out.print('#');
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Creature {
        private Grid.Pos currentPos;
        private int hitPoints = 200;
        private final char type;
        private final char movementTowardsType;
        private final int creatureNumber;

        private Creature(Grid.Pos pos, char creatureType, int creatureNumber) {
            this.currentPos = pos;
            this.type = creatureType;
            this.movementTowardsType = creatureType == 'E' ? 'G' : 'E';
            this.creatureNumber = creatureNumber;
        }

        static Creature from(Grid.Pos pos, char type, int creatureNumber) {
            return new Creature(pos, type, creatureNumber);
        }

        void reduceHitPoints() {
            hitPoints -= (type == 'E' ? goblinAttackPower : elfAttackPower);
        }

        boolean isDead() {
            return hitPoints <= 0;
        }

        void moveTo(Grid.Pos pos) {
            this.currentPos = pos;
        }

        @Override
        public String toString() {
            return String.format("Type -> %s; HitPoints -> %s; Position -> i: %s, j: %s",
                    type, hitPoints, currentPos.i(), currentPos.j());
        }
    }
}
