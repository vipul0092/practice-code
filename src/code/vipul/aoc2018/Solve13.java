package code.vipul.aoc2018;

import code.vipul.aoc2019.Grid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://adventofcode.com/2018/day/13
 */
public class Solve13 {

    private static final Map<Character, Direction> CART_CHARS;
    private static final Map<Character, Map<Direction, Direction>> DIRECTION_CHANGER;
    private static final Map<Integer, Map<Direction, Direction>> TURN_DIRECTION_CHANGER;

    static {
        CART_CHARS = new HashMap<>(4);
        CART_CHARS.put('^', Direction.UP);
        CART_CHARS.put('>', Direction.RIGHT);
        CART_CHARS.put('v', Direction.DOWN);
        CART_CHARS.put('<', Direction.LEFT);

        DIRECTION_CHANGER = new HashMap<>();
        // For /
        Map<Direction, Direction> directionMap = new HashMap<>();
        directionMap.put(Direction.UP, Direction.RIGHT);
        directionMap.put(Direction.LEFT, Direction.DOWN);
        directionMap.put(Direction.RIGHT, Direction.UP);
        directionMap.put(Direction.DOWN, Direction.LEFT);
        DIRECTION_CHANGER.put('/', directionMap);

        // For \
        directionMap = new HashMap<>();
        directionMap.put(Direction.UP, Direction.LEFT);
        directionMap.put(Direction.LEFT, Direction.UP);
        directionMap.put(Direction.RIGHT, Direction.DOWN);
        directionMap.put(Direction.DOWN, Direction.RIGHT);
        DIRECTION_CHANGER.put('\\', directionMap);

        TURN_DIRECTION_CHANGER = new HashMap<>();
        // For turn % 3 == 0, we turn left
        directionMap = new HashMap<>();
        directionMap.put(Direction.UP, Direction.LEFT);
        directionMap.put(Direction.LEFT, Direction.DOWN);
        directionMap.put(Direction.RIGHT, Direction.UP);
        directionMap.put(Direction.DOWN, Direction.RIGHT);
        TURN_DIRECTION_CHANGER.put(0, directionMap);

        // For turn % 3 == 1, we go straight
        directionMap = new HashMap<>();
        directionMap.put(Direction.UP, Direction.UP);
        directionMap.put(Direction.LEFT, Direction.LEFT);
        directionMap.put(Direction.RIGHT, Direction.RIGHT);
        directionMap.put(Direction.DOWN, Direction.DOWN);
        TURN_DIRECTION_CHANGER.put(1, directionMap);

        // For turn % 3 == 2, we turn right
        directionMap = new HashMap<>();
        directionMap.put(Direction.UP, Direction.RIGHT);
        directionMap.put(Direction.LEFT, Direction.UP);
        directionMap.put(Direction.RIGHT, Direction.DOWN);
        directionMap.put(Direction.DOWN, Direction.LEFT);
        TURN_DIRECTION_CHANGER.put(2, directionMap);
    }

    private static int cartNumber = 0;
    private static String input = Inputs.DAY13;
    private static String[] tracks;
    private static Map<Grid.Pos, Integer> cartPos;
    private static Map<Integer, Integer> cartTurnVal;
    private static TreeMap<Grid.Pos, Direction> cartDirections;
    private static Grid.Pos answer;

    public static void solve() {
        tracks = input.split("\n");
        initializeCartPositions();

        while (!moveCarts(true)) ;

        System.out.println(String.format("Answer: %s,%s", answer.j(), answer.i())); // 41,17
    }

    public static void solvePart2() {
        tracks = input.split("\n");
        initializeCartPositions();

        while (!moveCarts(false)) ;

        System.out.println(String.format("Answer: %s,%s", answer.j(), answer.i())); // 134,117
    }

    private static void initializeCartPositions() {
        cartPos = new HashMap<>();
        cartTurnVal = new HashMap<>();
        cartDirections = new TreeMap<>();
        for (int i = 0; i < tracks.length; i++) {
            for (int j = 0; j < tracks[i].length(); j++) {
                char ch = tracks[i].charAt(j);
                if (CART_CHARS.containsKey(ch)) {
                    int number = cartNumber++;
                    cartTurnVal.put(number, 0);
                    Grid.Pos pos = Grid.Pos.of(i, j);
                    cartPos.put(pos, number);
                    cartDirections.put(pos, CART_CHARS.get(ch));
                }
            }
        }
    }

    private static boolean moveCarts(boolean breakOnCollision) {
        TreeMap<Grid.Pos, Direction> newDirections = new TreeMap<>();
        Map<Grid.Pos, Integer> newCartPos = new HashMap<>();

        if (cartDirections.size() == 1) {
            answer = cartDirections.entrySet().iterator().next().getKey();
            return true;
        }

        boolean endCartMovement = false;
        Set<Grid.Pos> oldDiscardedPositions = new HashSet<>();
        Set<Integer> collidedCarts = new HashSet<>();

        for (Map.Entry<Grid.Pos, Direction> entry : cartDirections.entrySet()) {
            Grid.Pos pos = entry.getKey();
            Direction currentDirection = entry.getValue();
            int number = cartPos.get(pos);

            if (collidedCarts.contains(number)) {
                continue;
            }

            Grid.Pos nextPos = getNextPos(pos, currentDirection);
            char ch = charAt(nextPos);
            Direction newDirection = currentDirection;
            if (DIRECTION_CHANGER.containsKey(ch)) {
                newDirection = DIRECTION_CHANGER.get(ch).get(currentDirection);
            } else if (ch == '+') { // its a turn
                newDirection = TURN_DIRECTION_CHANGER.get(cartTurnVal.get(number) % 3).get(currentDirection);
                cartTurnVal.put(number, cartTurnVal.get(number) + 1); // increment turn number
            }

            // Collision
            if (newDirections.containsKey(nextPos) ||
                    (cartDirections.containsKey(nextPos) && !oldDiscardedPositions.contains(nextPos))) {
                if (breakOnCollision) {
                    answer = nextPos;
                    endCartMovement = true;
                    break;
                } else {
                    if (newDirections.containsKey(nextPos)) {
                        newDirections.remove(nextPos);
                        newCartPos.remove(nextPos);
                    } else {
                        collidedCarts.add(cartPos.get(nextPos));
                    }
                }
            } else {
                newDirections.put(nextPos, newDirection);
                // Update the pos to cart number mapping
                newCartPos.put(nextPos, number);
            }
            oldDiscardedPositions.add(pos);
        }
        cartDirections = newDirections;
        cartPos = newCartPos;
        return endCartMovement;
    }

    private static char charAt(Grid.Pos pos) {
        return tracks[pos.i()].charAt(pos.j());
    }

    private static Grid.Pos getNextPos(Grid.Pos pos, Direction direction) {
        switch (direction) {
            case DOWN:
                return pos.moveDown();
            case UP:
                return pos.moveUp();
            case RIGHT:
                return pos.moveRight();
            case LEFT:
                return pos.moveLeft();
            default:
                throw new RuntimeException("Unsupported direction bruh!");
        }
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }
}
