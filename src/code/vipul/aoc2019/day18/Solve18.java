package code.vipul.aoc2019.day18;

import code.vipul.Pair;
import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://adventofcode.com/2019/day/18
 */
public class Solve18 {

    private static String input;
    private static int[][] maze;
    private static Grid.Pos startPos;
    private static Map<Integer, Grid.Pos> keyPositions;
    private static long allKeysTakenBitmap;
    private static Map<Pair<Integer, Long>, Integer> memoizedResults;
    private static Map<Integer, Map<Integer, Integer>> keyToKeyLengths;
    private static Map<Integer, Map<Integer, Long>> keyToKeyBlockingDoors;
    private static Map<Integer, Map<Integer, Long>> keyToKeysFound;
    private static int totalKeys;

    private static int currentRobotNumber = 0;
    private static Robot[] robots;

    public static void solve() {
        input = Inputs.INPUT_PART_1;
        parseInput(false);
        keyToKeyLengths = new HashMap<>();
        keyToKeyBlockingDoors = new HashMap<>();
        memoizedResults = new HashMap<>();
        keyToKeysFound = new HashMap<>();

        final long startTime = System.nanoTime();
        keyPositions.forEach((key, pos) -> {
            Map<Integer, Integer> keyLengths = new HashMap<>();
            Map<Integer, Long> keyBlockingDoors = new HashMap<>();
            Map<Integer, Long> keysFound = new HashMap<>();

            // For each key populate the min distance to all other keys plus doors that come in between, if any
            populateNeighbours(pos, keyLengths, keyBlockingDoors, keysFound);

            keyToKeyLengths.put(key, keyLengths);
            keyToKeyBlockingDoors.put(key, keyBlockingDoors);
            keyToKeysFound.put(key, keysFound);
        });

        Map<Integer, Integer> startPosToKeyLengths = new HashMap<>();
        Map<Integer, Long> startPosToKeyBlockingDoors = new HashMap<>();
        Map<Integer, Long> startPosKeysFound = new HashMap<>();

        // Populate the min distances from start position to all other keys plus doors that come in between, if any
        populateNeighbours(startPos, startPosToKeyLengths, startPosToKeyBlockingDoors, startPosKeysFound);

        int answer = Integer.MAX_VALUE;

        // Approach inspired from https://www.reddit.com/r/adventofcode/comments/ec8090/2019_day_18_solutions/fbd8y0b/

        for (Integer key : startPosToKeyLengths.keySet()) {
            // When we are starting we can only go to those keys which are not blocked by any doors
            if (startPosToKeyBlockingDoors.get(key) == 0) {

                // Go to a key and
                // then recursively calculate the min length to take the keys left
                long keysTakenAlready = (1L << key);
                keysTakenAlready = keysTakenAlready | startPosKeysFound.getOrDefault(key, 0L);
                int keyAnswer = startPosToKeyLengths.get(key) + walk(keysTakenAlready, key);
                answer = Math.min(keyAnswer, answer);
            }
        }
        final long duration = (System.nanoTime() - startTime) / 1000000;

        System.out.println("Runtime(in ms): " + duration); // ~200ms
        System.out.println("Answer: " + answer); //3048
    }

    public static void solvePart2() {
        input = Inputs.INPUT_PART_2;

        robots = new Robot[4]; // Assuming there are always 4 robots
        parseInput(true);
        keyToKeyLengths = new HashMap<>();
        keyToKeyBlockingDoors = new HashMap<>();
        keyToKeysFound = new HashMap<>();
        memoizedResults = new HashMap<>();

        final long startTime = System.nanoTime();
        keyPositions.forEach((key, pos) -> {
            Map<Integer, Integer> keyLengths = new HashMap<>();
            Map<Integer, Long> keyBlockingDoors = new HashMap<>();
            Map<Integer, Long> keysFound = new HashMap<>();

            // For each key populate the min distance to all other keys plus doors that come in between, if any
            populateNeighbours(pos, keyLengths, keyBlockingDoors, keysFound);

            keyToKeyLengths.put(key, keyLengths);
            keyToKeyBlockingDoors.put(key, keyBlockingDoors);
            keyToKeysFound.put(key, keysFound);
        });

        for (int i = 0; i < 4; i++) {
            Robot robot = robots[i];
            populateNeighbours(robot.initialPos, robot.startPosToKeyLengths, robot.startPosToKeyBlockingDoors,
                    robot.startPosToKeysFound);
        }

        int answer = walkWithRobots(0, robots[0], robots[1], robots[2], robots[3]);
        final long duration = (System.nanoTime() - startTime) / 1000000;

        System.out.println("Runtime(in ms): " + duration); // ~200ms
        System.out.println("Answer: " + answer); //1732
    }

    private static int walkWithRobots(long keysTakenAlreadyUntilNow,
                                      Robot robot1, Robot robot2, Robot robot3, Robot robot4) {
        if (keysTakenAlreadyUntilNow == allKeysTakenBitmap) {
            return 0;
        }

        var memoizationKey = Pair.of(getRobotPositionsHash(robot1, robot2, robot3, robot4), keysTakenAlreadyUntilNow);
        if (memoizedResults.containsKey(memoizationKey)) {
            return memoizedResults.get(memoizationKey);
        }

        int answer = Integer.MAX_VALUE;
        answer = Math.min(answer, walkForRobot(robot1, keysTakenAlreadyUntilNow, robot2, robot3, robot4));
        answer = Math.min(answer, walkForRobot(robot2, keysTakenAlreadyUntilNow, robot1, robot3, robot4));
        answer = Math.min(answer, walkForRobot(robot3, keysTakenAlreadyUntilNow, robot2, robot1, robot4));
        answer = Math.min(answer, walkForRobot(robot4, keysTakenAlreadyUntilNow, robot1, robot2, robot3));

        memoizedResults.put(memoizationKey, answer);
        return answer;
    }

    private static int walkForRobot(Robot robot, long keysTakenAlreadyUntilNow,
                                    Robot otherRobot1, Robot otherRobot2, Robot otherRobot3) {
        int keyVal = valueAt(robot.currentPos) - 'a';
        Map<Integer, Integer> keyLengths = robot.isAtInitialPos()
                ? robot.startPosToKeyLengths
                : keyToKeyLengths.get(keyVal);
        Map<Integer, Long> keyBlockingDoors = robot.isAtInitialPos()
                ? robot.startPosToKeyBlockingDoors
                : keyToKeyBlockingDoors.get(keyVal);
        Map<Integer, Long> keysFound = robot.isAtInitialPos()
                ? robot.startPosToKeysFound
                : keyToKeysFound.get(keyVal);

        int answer = Integer.MAX_VALUE;
        for (Integer key : keyLengths.keySet()) {
            // Select the key to go, if it hasn't been selected already
            // and all doors for going to the key are unlocked
            long keysTakenIncludingCurrentKey = keysTakenAlreadyUntilNow | (1L << key);
            keysTakenIncludingCurrentKey = keysTakenIncludingCurrentKey | keysFound.getOrDefault(key, 0L);
            if (!isBitSet(keysTakenAlreadyUntilNow, key)
                    && areAllDoorsUnlocked(keyBlockingDoors.get(key), keysTakenAlreadyUntilNow)) {
                int keyAnswer = keyLengths.get(key) + walkWithRobots(keysTakenIncludingCurrentKey,
                        robot.moveTo(keyPositions.get(key)), otherRobot1, otherRobot2, otherRobot3);
                answer = Math.min(keyAnswer, answer);
            }
        }
        return answer;
    }

    private static int walk(long keysTakenAlreadyUntilNow, int keyVal) {
        if (keysTakenAlreadyUntilNow == allKeysTakenBitmap) {
            return 0;
        }

        var memoizationKey = Pair.of(keyVal, keysTakenAlreadyUntilNow);
        if (memoizedResults.containsKey(memoizationKey)) {
            return memoizedResults.get(memoizationKey);
        }

        Map<Integer, Integer> keyLengths = keyToKeyLengths.get(keyVal);
        Map<Integer, Long> keyBlockingDoors = keyToKeyBlockingDoors.get(keyVal);
        Map<Integer, Long> keysFound = keyToKeysFound.get(keyVal);

        int answer = Integer.MAX_VALUE;
        for (Integer key : keyLengths.keySet()) {
            // Select the key to go, if it hasn't been selected already
            // and all doors for going to the key are unlocked
            long keysTakenIncludingCurrentKey = keysTakenAlreadyUntilNow | (1L << key);
            keysTakenIncludingCurrentKey = keysTakenIncludingCurrentKey | keysFound.getOrDefault(key, 0L);
            if (!isBitSet(keysTakenAlreadyUntilNow, key)
                    && areAllDoorsUnlocked(keyBlockingDoors.get(key), keysTakenAlreadyUntilNow)) {
                int keyAnswer = keyLengths.get(key) + walk(keysTakenIncludingCurrentKey, key);
                answer = Math.min(keyAnswer, answer);
            }
        }

        memoizedResults.put(memoizationKey, answer);
        return answer;
    }

    private static int getRobotPositionsHash(Robot robot1, Robot robot2, Robot robot3, Robot robot4) {
        List<Robot> sortedRobots = Arrays.asList(robot1, robot2, robot3, robot4);
        sortedRobots.sort(Comparator.comparing(r -> r.number));

        int hash = 5381;
        hash += (hash << 5) + sortedRobots.get(0).currentPos.hashCode();
        hash += (hash << 5) + sortedRobots.get(1).currentPos.hashCode();
        hash += (hash << 5) + sortedRobots.get(2).currentPos.hashCode();
        hash += (hash << 5) + sortedRobots.get(3).currentPos.hashCode();
        return hash;
    }

    private static boolean areAllDoorsUnlocked(long doorBitmap, long keysTaken) {
        if (doorBitmap == 0) {
            return true;
        }
        if (keysTaken == 0) {
            return false;
        }

        boolean areAllDoorsUnlocked = true;
        for (int i = 0; i < totalKeys; i++) {
            if (isBitSet(doorBitmap, i)) {
                areAllDoorsUnlocked = areAllDoorsUnlocked && isBitSet(keysTaken, i);
            }
        }
        return areAllDoorsUnlocked;
    }

    private static boolean isBitSet(long bitSet, int bitPos) {
        return (bitSet & (1L << bitPos)) != 0;
    }

    private static void populateNeighbours(Grid.Pos originalPos,
                                           Map<Integer, Integer> neighbourLengthMap,
                                           Map<Integer, Long> blockingDoorMap,
                                           Map<Integer, Long> keysFoundMap) {

        Queue<Grid.Pos> currentPosQ = new ArrayDeque<>();
        Queue<Set<Grid.Pos>> visitedUntilNowQ = new ArrayDeque<>();
        Queue<Integer> currentLengthQ = new ArrayDeque<>();
        Queue<Long> doorsBitmapQ = new ArrayDeque<>();
        Queue<Long> keysBitmapQ = new ArrayDeque<>();

        currentPosQ.add(originalPos);
        visitedUntilNowQ.add(new HashSet<>());
        currentLengthQ.add(0);
        doorsBitmapQ.add(0L);
        keysBitmapQ.add(0L);

        while (!currentPosQ.isEmpty()) {
            Grid.Pos currentPos = currentPosQ.remove();
            Set<Grid.Pos> visitedUntilNow = visitedUntilNowQ.remove();
            int currentLength = currentLengthQ.remove();
            long doorsBitmap = doorsBitmapQ.remove();
            long keysBitmap = keysBitmapQ.remove();

            visitedUntilNow.add(currentPos);

            var movableNeighbours = getMovableNeighbours(currentPos);
            for (Grid.Pos neighbour : movableNeighbours) {
                if (neighbour.equals(originalPos) || visitedUntilNow.contains(neighbour)) {
                    continue;
                }
                int val = valueAt(neighbour);
                long nDoorsBitmap = doorsBitmap;
                long nKeysBitmap = keysBitmap;
                if (isKey(neighbour)) {
                    int keyVal = val - 'a';

                    if (!neighbourLengthMap.containsKey(keyVal)
                            || (neighbourLengthMap.containsKey(keyVal) && neighbourLengthMap.get(keyVal) > currentLength + 1)) {
                        neighbourLengthMap.put(keyVal, currentLength + 1);
                        blockingDoorMap.put(keyVal, nDoorsBitmap);
                        keysFoundMap.put(keyVal, nKeysBitmap);
                        nKeysBitmap |= (1L << keyVal);
                    }
                } else if (isDoor(neighbour)) {
                    nDoorsBitmap |= (1L << (val - 'A'));
                }

                currentPosQ.add(neighbour);
                visitedUntilNowQ.add(visitedUntilNow);
                currentLengthQ.add(currentLength + 1);
                doorsBitmapQ.add(nDoorsBitmap);
                keysBitmapQ.add(nKeysBitmap);
            }
        }
    }

    private static void parseInput(boolean multipleRobots) {
        keyPositions = new HashMap<>();
        String[] rows = input.split("\n");
        int maxRows = rows.length;
        int maxCols = rows[0].length();
        maze = new int[maxRows][maxCols];
        Grid.setMaxRowsCols(maxRows, maxCols);

        totalKeys = 0;
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            char[] charArray = row.toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char ch = charArray[j];
                if (ch >= 'a' && ch <= 'z') {
                    keyPositions.put(ch - 'a', Grid.Pos.of(i, j));
                    totalKeys++;
                }

                if (ch == '.') {
                    maze[i][j] = 0;
                } else if (ch == '#') {
                    maze[i][j] = 1;
                } else if (ch == '@') {
                    if (multipleRobots) {
                        robots[currentRobotNumber] = Robot.of(Grid.Pos.of(i, j), currentRobotNumber);
                        currentRobotNumber++;
                    } else {
                        startPos = Grid.Pos.of(i, j);
                    }
                    maze[i][j] = 0;
                } else {
                    maze[i][j] = ch;
                }
            }
        }
        allKeysTakenBitmap = (1L << totalKeys) - 1;
    }

    private static Set<Grid.Pos> getMovableNeighbours(Grid.Pos currentPos) {
        var neighbours = new HashSet<Grid.Pos>();
        Grid.Pos neighbour = currentPos.moveDown();
        if (canMoveTo(neighbour)) {
            neighbours.add(neighbour);
        }

        neighbour = currentPos.moveUp();
        if (canMoveTo(neighbour)) {
            neighbours.add(neighbour);
        }

        neighbour = currentPos.moveLeft();
        if (canMoveTo(neighbour)) {
            neighbours.add(neighbour);
        }

        neighbour = currentPos.moveRight();
        if (canMoveTo(neighbour)) {
            neighbours.add(neighbour);
        }
        return neighbours;
    }

    private static int valueAt(Grid.Pos pos) {
        return maze[pos.i()][pos.j()];
    }

    private static boolean canMoveTo(Grid.Pos pos) {
        return valueAt(pos) != 1;
    }

    private static boolean isKey(Grid.Pos pos) {
        int val = valueAt(pos);
        return val >= 'a' && val <= 'z';
    }

    private static boolean isDoor(Grid.Pos pos) {
        int val = valueAt(pos);
        return val >= 'A' && val <= 'Z';
    }

    private static class Robot {
        private Grid.Pos currentPos;
        private final Grid.Pos initialPos;
        private final Integer number;
        private Map<Integer, Integer> startPosToKeyLengths = new HashMap<>();
        private Map<Integer, Long> startPosToKeyBlockingDoors = new HashMap<>();
        private Map<Integer, Long> startPosToKeysFound = new HashMap<>();

        private Robot(Grid.Pos currentPos, Grid.Pos initialPos, int number) {
            this.currentPos = currentPos;
            this.initialPos = initialPos;
            this.number = number;
        }

        boolean isAtInitialPos() {
            return currentPos.equals(initialPos);
        }

        public static Robot of(Grid.Pos pos, int number) {
            return new Robot(pos, pos, number);
        }

        Robot moveTo(Grid.Pos pos) {
            Robot r = new Robot(pos, this.initialPos, this.number);
            r.startPosToKeyLengths = this.startPosToKeyLengths;
            r.startPosToKeyBlockingDoors = this.startPosToKeyBlockingDoors;
            r.startPosToKeysFound = this.startPosToKeysFound;
            return r;
        }
    }
}
