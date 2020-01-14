package code.vipul.aoc2019.day20;

import code.vipul.Pair;
import code.vipul.aoc2019.Grid;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://adventofcode.com/2019/day/20
 */
public class Solve20 {

    static String input;

    static int[][] maze;
    static int maxCols = 0, maxRows = 0;

    static Map<Grid.Pos, String> portals;
    static Map<Grid.Pos, Grid.Pos> portalsLinks;
    static Set<Grid.Pos> outerPortals, innerPortals;
    static Grid.Pos start, end;

    static Map<Grid.Pos, Map<Grid.Pos, Integer>> portalAdjacencyList = new HashMap<>();

    static int answer = Integer.MAX_VALUE;

    public static void solve() {
        input = Inputs.INPUT2;

        parseInput();
        dfs(start, new LinkedHashSet<>(), 0);

        System.out.println("Answer: " + answer);
    }

    public static void solvePart2() {
        input = Inputs.INPUT2;
        parseInput();

        Queue<Grid.Pos> portalsQ = new ArrayDeque<>();
        Queue<Set<Pair<Integer, Grid.Pos>>> portalsTakenQ = new ArrayDeque<>();
        Queue<Integer> levelsQ = new ArrayDeque<>();
        Queue<Integer> stepsUntilNowQ = new ArrayDeque<>();

        portalsQ.add(start);
        portalsTakenQ.add(new HashSet<>());
        levelsQ.add(0);
        stepsUntilNowQ.add(0);

        while (!portalsQ.isEmpty()) {
            Grid.Pos currentPos = portalsQ.remove();
            int currentLevel = levelsQ.remove();
            Set<Pair<Integer, Grid.Pos>> portalsTaken = portalsTakenQ.remove();
            int stepsUntilNow = stepsUntilNowQ.remove();

            if (stepsUntilNow >= answer) {
                continue;
            }

            Pair<Integer, Grid.Pos> portalTakenKey = Pair.of(currentLevel, currentPos);

            if (portalsTaken.contains(portalTakenKey)) {
                continue;
            } else {
                portalsTaken.add(portalTakenKey);
            }

            populateNeighbouringPortals(currentPos);

            for (Map.Entry<Grid.Pos, Integer> neighbourEntry : portalAdjacencyList.get(currentPos).entrySet()) {
                Grid.Pos neighbouringPortal = neighbourEntry.getKey();
                int stepsToPortal = stepsUntilNow + neighbourEntry.getValue();

                // Start/End behave as walls in upper levels
                if (isStartOrEnd(neighbouringPortal) && currentLevel > 0) {
                    continue;
                }

                Pair<Integer, Grid.Pos> neighbouringPortalKey = Pair.of(currentLevel, neighbouringPortal);

                if (portalsLinks.containsKey(neighbouringPortal)) {
                    var newPortalsTakenSet = new LinkedHashSet<>(portalsTaken);
                    // visit the portal
                    newPortalsTakenSet.add(neighbouringPortalKey);

                    // Teleport using the portal, teleportation costs one step

                    if (innerPortals.contains(neighbouringPortal)) {
                        portalsQ.add(portalsLinks.get(neighbouringPortal));
                        levelsQ.add(currentLevel + 1);
                        portalsTakenQ.add(newPortalsTakenSet);
                        stepsUntilNowQ.add(stepsToPortal + 1);
                    } else if (currentLevel > 0) {
                        portalsQ.add(portalsLinks.get(neighbouringPortal));
                        levelsQ.add(currentLevel - 1);
                        portalsTakenQ.add(newPortalsTakenSet);
                        stepsUntilNowQ.add(stepsToPortal + 1);
                    }
                }

                if (neighbouringPortal.equals(end) && currentLevel == 0) {
                    answer = Math.min(answer, stepsToPortal);
                }
            }
        }

        System.out.println("Answer: " + answer); // 5040
    }

    private static void dfs(Grid.Pos currentPos, Set<Grid.Pos> visitedUntilNow, int stepsUntilNow) {
        if (stepsUntilNow > answer) {
            return;
        }

        if (visitedUntilNow.contains(currentPos)) {
            return;
        } else {
            visitedUntilNow.add(currentPos);
        }

        populateNeighbouringPortals(currentPos);

        for (Map.Entry<Grid.Pos, Integer> neighbourEntry : portalAdjacencyList.get(currentPos).entrySet()) {
            Grid.Pos neighbouringPortal = neighbourEntry.getKey();
            int stepsToPortal = stepsUntilNow + neighbourEntry.getValue();

            if (portalsLinks.containsKey(neighbouringPortal)) {
                var newVisitedSet = new LinkedHashSet<>(visitedUntilNow);
                // visit the portal
                newVisitedSet.add(neighbouringPortal);
                // Teleport using the portal, teleportation costs one step
                dfs(portalsLinks.get(neighbouringPortal), newVisitedSet, stepsToPortal + 1);
            }

            if (neighbouringPortal.equals(end)) {
                answer = Math.min(answer, stepsToPortal);
            }
        }
    }

    private static void populateNeighbouringPortals(Grid.Pos currentPos) {
        if (portalAdjacencyList.containsKey(currentPos)) {
            return;
        }
        Map<Grid.Pos, Integer> neighbours = new HashMap<>();
        findNeighbours(currentPos, currentPos, new HashSet<>(), 0, neighbours);
        portalAdjacencyList.put(currentPos, neighbours);
    }

    private static void findNeighbours(Grid.Pos originalPos,
                                       Grid.Pos currentPos,
                                       Set<Grid.Pos> visitedUntilNow,
                                       int currentLength,
                                       Map<Grid.Pos, Integer> neighbours) {
        if (visitedUntilNow.contains(currentPos)) {
            return;
        } else {
            visitedUntilNow.add(currentPos);
        }

        var movableNeighbours = getMovableNeighbours(currentPos);
        for (Grid.Pos neighbour : movableNeighbours) {
            if (neighbour.equals(originalPos)) {
                continue;
            }
            if (portals.containsKey(neighbour) || isStartOrEnd(neighbour)) {
                neighbours.put(neighbour, currentLength + 1);
            } else {
                findNeighbours(originalPos, neighbour, visitedUntilNow, currentLength + 1, neighbours);
            }
        }
    }

    private static void parseInput() {
        String[] rows = input.split("\n");
        maxCols = rows[0].length();
        maxRows = rows.length;
        Grid.setMaxRowsCols(maxRows, maxCols);

        maze = new int[maxRows][maxCols];
        portalsLinks = new HashMap<>();
        innerPortals = new HashSet<>();
        outerPortals = new HashSet<>();
        portals = new HashMap<>();

        Map<String, Grid.Pos> tempPortalMap = new HashMap<>();

        for (int rowCtr = 0; rowCtr < maxRows; rowCtr++) {
            String row = rows[rowCtr];
            for (int colCtr = 0; colCtr < maxCols; colCtr++) {
                var currentPos = Grid.Pos.of(rowCtr, colCtr);
                if (row.charAt(colCtr) == '.') {
                    maze[rowCtr][colCtr] = 1;
                    Pair<Boolean, String> portalWithDirection = getNeighbouringPortal(currentPos, rows);

                    String portal = portalWithDirection.right();
                    Boolean inward = portalWithDirection.left();

                    if (!portal.equals("")) {
                        if (tempPortalMap.containsKey(portal)) {
                            portalsLinks.put(currentPos, tempPortalMap.get(portal));
                            portalsLinks.put(tempPortalMap.get(portal), currentPos);
                            tempPortalMap.remove(portal);
                        } else {
                            tempPortalMap.put(portal, currentPos);
                        }

                        if (portal.equals("AA")) {
                            start = currentPos;
                        } else if (portal.equals("ZZ")) {
                            end = currentPos;
                        } else {
                            portals.put(currentPos, portal);
                            if (inward) {
                                innerPortals.add(currentPos);
                            } else {
                                outerPortals.add(currentPos);
                            }
                        }
                    }
                }
            }
        }
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

    private static Pair<Boolean, String> getNeighbouringPortal(Grid.Pos pos, String[] rows) {
        StringBuilder s = new StringBuilder();

        Grid.Pos pos1 = pos.moveUp();
        Grid.Pos pos2 = pos1.moveUp(); // UP
        if (isAlphabet(getCharAt(pos1, rows)) && isAlphabet(getCharAt(pos2, rows))) {
            return Pair.of(!pos2.isAtTopBoundary(),
                    s.append(getCharAt(pos2, rows)).append(getCharAt(pos1, rows)).toString());
        }

        pos1 = pos.moveDown();
        pos2 = pos1.moveDown(); // DOWN
        if (isAlphabet(getCharAt(pos1, rows)) && isAlphabet(getCharAt(pos2, rows))) {
            return Pair.of(!pos2.isAtBottomBoundary(),
                    s.append(getCharAt(pos1, rows)).append(getCharAt(pos2, rows)).toString());
        }

        pos1 = pos.moveLeft();
        pos2 = pos1.moveLeft(); // LEFT
        if (isAlphabet(getCharAt(pos1, rows)) && isAlphabet(getCharAt(pos2, rows))) {
            return Pair.of(!pos2.isAtLeftBoundary(),
                    s.append(getCharAt(pos2, rows)).append(getCharAt(pos1, rows)).toString());
        }

        pos1 = pos.moveRight();
        pos2 = pos1.moveRight(); // RIGHT
        if (isAlphabet(getCharAt(pos1, rows)) && isAlphabet(getCharAt(pos2, rows))) {
            return Pair.of(!pos2.isAtRightBoundary(),
                    s.append(getCharAt(pos1, rows)).append(getCharAt(pos2, rows)).toString());
        }

        return Pair.of(false, s.toString());
    }

    private static char getCharAt(Grid.Pos pos, String[] rows) {
        return rows[pos.i()].charAt(pos.j());
    }

    private static boolean canMoveTo(Grid.Pos pos) {
        return maze[pos.i()][pos.j()] == 1;
    }

    private static boolean isAlphabet(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    private static boolean isStartOrEnd(Grid.Pos pos) {
        return pos.equals(start) || pos.equals(end);
    }
}
