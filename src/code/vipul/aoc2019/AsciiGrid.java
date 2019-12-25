package code.vipul.aoc2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class AsciiGrid {

    private final int maxRows;
    private final int maxCols;
    private int[][] grid;

    private Direction originalDirection;
    private Direction currentDirection;
    private List<Grid.Pos> intersections = new ArrayList<>();

    private Grid.Pos pathStart;

    private AsciiGrid(String[] rows) {
        maxRows = rows.length;
        maxCols = rows[0].length();
        Grid.setMaxRowsCols(maxRows, maxCols);
        grid = new int[maxRows][maxCols];

        for (int i = 0; i < maxRows; i++) {
            String row = rows[i];
            for (int j = 0; j < maxCols; j++) {
                char ch = row.charAt(j);
                grid[i][j] = ch == '.' ? 0 : (ch == '#' ? 1 : -1);

                if (ch != '.' && ch != '#') {
                    pathStart = Grid.Pos.of(i, j);
                    initializeOriginalDirection(ch);
                }
            }
        }

        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                Grid.Pos currentPos = Grid.Pos.of(i, j);
                Map<Grid.Pos, Integer> neighbours = getNeighbours(currentPos);
                int currentValue = grid[i][j];
                if (currentValue == 1 && neighbours.size() == 4 && neighbours.values().stream().allMatch(n -> n == 1)) {
                    intersections.add(currentPos);
                }
            }
        }
    }

    public void printAlignment() {
        int alignment = 0;
        for (Grid.Pos intersection : intersections) {
            alignment += (intersection.i() * intersection.j());
        }
        System.out.println("Alignment: " + alignment);
    }

    private int steps = 0;
    private StringBuilder movement;

    public String findPathing() {
        currentDirection = originalDirection;
        movement = new StringBuilder();
        steps = 0;

        dfs(pathStart, null);
        if (steps != 0) {
            movement.append(steps);
        }

        System.out.println(movement.toString());
        return movement.toString();
    }

    private void dfs(Grid.Pos currentPos, Grid.Pos prevPos) {
        if (grid[currentPos.i()][currentPos.j()] == 0) {
            return;
        }

        if (prevPos != null) {
            evaluateMovement(currentPos, prevPos);
        }

        Map<Grid.Pos, Integer> neighbours = getNeighbours(currentPos);
        Map<Grid.Pos, Integer> neighBoursToVisit = neighbours.entrySet().stream()
                .filter(neighbour ->
                        (grid[neighbour.getKey().i()][neighbour.getKey().j()] != 0)
                                && !neighbour.getKey().equals(prevPos))
                .collect(toMap(f -> f.getKey(), f -> f.getValue()));

        Grid.Pos neighbourNotBreakingMovement = null;
        for (Map.Entry<Grid.Pos, Integer> neighbour : neighBoursToVisit.entrySet()) {
            Direction potentialDirection = getCurrentDirection(neighbour.getKey(), currentPos);
            if (potentialDirection != null && currentDirection == potentialDirection) {
                neighbourNotBreakingMovement = neighbour.getKey();
                break;
            }
        }

        // Prefer to branch into the neighbor which doesnt break the direction
        if (neighbourNotBreakingMovement != null) {
            dfs(neighbourNotBreakingMovement, currentPos);
            neighBoursToVisit.remove(neighbourNotBreakingMovement);
        } else {
            for (Map.Entry<Grid.Pos, Integer> neighbour : neighBoursToVisit.entrySet()) {
                dfs(neighbour.getKey(), currentPos);
            }
        }
    }

    private Direction getCurrentDirection(Grid.Pos currentPos, Grid.Pos prevPos) {
        Direction currentMovementDirection = null;
        int currI = currentPos.i(), currJ = currentPos.j(), prevI = prevPos.i(), prevJ = prevPos.j();
        if (currI == prevI && prevJ < currJ) {
            currentMovementDirection = Direction.RIGHT;
        } else if (currI == prevI && prevJ > currJ) {
            currentMovementDirection = Direction.LEFT;
        } else if (currJ == prevJ && prevI > currI) {
            currentMovementDirection = Direction.UP;
        } else if (currJ == prevJ && prevI < currI) {
            currentMovementDirection = Direction.DOWN;
        }
        return currentMovementDirection;
    }

    private void evaluateMovement(Grid.Pos currentPos, Grid.Pos prevPos) {
        Direction currentMovementDirection = getCurrentDirection(currentPos, prevPos);
        if (currentMovementDirection == null) {
            throw new RuntimeException("Cant determine the current movement direction. WTF!");
        }
        if (currentDirection != currentMovementDirection) { // Change in direction
            if (steps != 0 && !(movement.length() == 0)) {
                movement.append(steps).append(',');
                steps = 0;
            }
            char change;
            if (currentDirection == Direction.UP) {
                change = currentMovementDirection == Direction.LEFT ? 'L' : 'R';
            } else if (currentDirection == Direction.DOWN) {
                change = currentMovementDirection == Direction.LEFT ? 'R' : 'L';
            } else if (currentDirection == Direction.RIGHT) {
                change = currentMovementDirection == Direction.DOWN ? 'R' : 'L';
            } else { // originalDirection == Direction.LEFT
                change = currentMovementDirection == Direction.DOWN ? 'L' : 'R';
            }
            movement.append(change).append(',');
            currentDirection = currentMovementDirection;
        }
        steps++;
    }

    private Map<Grid.Pos, Integer> getNeighbours(Grid.Pos pos) {
        Map<Grid.Pos, Integer> neighbourValues = new HashMap<>();
        if (!pos.isAtTopBoundary()) { // i - 1 >= 0
            Grid.Pos newPos = pos.moveUp();
            neighbourValues.put(newPos, grid[newPos.i()][newPos.j()]);
        }

        if (!pos.isAtBottomBoundary()) { // i + 1 < ROWS
            Grid.Pos newPos = pos.moveDown();
            neighbourValues.put(newPos, grid[newPos.i()][newPos.j()]);
        }

        if (!pos.isAtLeftBoundary()) { // j - 1 >= 0
            Grid.Pos newPos = pos.moveLeft();
            neighbourValues.put(newPos, grid[newPos.i()][newPos.j()]);
        }

        if (!pos.isAtRightBoundary()) { // j + 1 < COLS
            Grid.Pos newPos = pos.moveRight();
            neighbourValues.put(newPos, grid[newPos.i()][newPos.j()]);
        }
        return neighbourValues;
    }

    private void initializeOriginalDirection(char ch) {
        if (ch == '^') {
            originalDirection = Direction.UP;
        } else if (ch == 'v') {
            originalDirection = Direction.DOWN;
        } else if (ch == '>') {
            originalDirection = Direction.LEFT;
        } else if (ch == '<') {
            originalDirection = Direction.RIGHT;
        }
    }

    public static AsciiGrid newGrid(String stringGrid) {
        String[] rows = stringGrid.split("\n");
        return new AsciiGrid(rows);
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }
}
