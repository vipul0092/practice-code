package code.vipul.aoc2018Part2;

import java.util.*;

import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2018/day/13
 */
public class Day13 {

    record Point (int i, int j){}

    record Cart (char dir, Point p, int intersect) {
        Cart move(List<String> lines) {
            Point newPoint = new Point(p.i + MOVES.get(dir)[0], p.j + MOVES.get(dir)[1]);
            char c = get(lines, newPoint);
            int newIntersect = intersect;
            if (c != '\\' && c != '/' && c != '+') {
                return new Cart(dir, newPoint, newIntersect);
            }
            // deflect
            char newDir = change(c, dir, intersect);
            if (c == '+') {
                newIntersect = (intersect + 1) % 3;
            }
            return new Cart(newDir, newPoint, newIntersect);
        }
    }
    private static final Map<Character, int[]> MOVES = Map.of('>', new int[]{0,1}, '<', new int[]{0,-1},
            'v', new int[]{1, 0}, '^', new int[]{-1,0});

    private static final Comparator<Cart> CART_COMPARATOR = (c1, c2) -> {
        if (c1.p.i == c2.p.i) return Integer.compare(c1.p.j, c2.p.j);
        return Integer.compare(c1.p.i, c2.p.i);
    };

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day13.class, false);

        List<Cart> carts = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                Point pt = new Point(i, j);
                char ch = get(lines, pt);
               if (MOVES.containsKey(ch)) {
                   carts.add(new Cart(ch, pt, 0));
               }
            }
        }

        List<Point> points = moveUntilSingleLeft(new ArrayList<>(carts), lines);

        Point firstCollisionPoint = points.getFirst();
        System.out.println("Part 1: " + firstCollisionPoint.j + "," + firstCollisionPoint.i);
        Point lastCartPoint = points.getLast();
        System.out.println("Part 2: " + lastCartPoint.j + "," + lastCartPoint.i);
    }

    private static List<Point> moveUntilSingleLeft(List<Cart> carts, List<String> lines) {
        List<Point> collisionPoints = new ArrayList<>();
        do {
            carts.sort(CART_COMPARATOR);
            Map<Point, Cart> cartPositions = new HashMap<>();
            for (Cart c : carts) cartPositions.put(c.p, c);
            for (Cart cart : carts) {
                cartPositions.remove(cart.p); // remove its own point
                Cart newCart = cart.move(lines);
                if (cartPositions.containsKey(newCart.p)) { // already something at this point, collision occurs
                    cartPositions.remove(newCart.p);
                    collisionPoints.add(newCart.p);
                } else {
                    cartPositions.put(newCart.p, newCart);
                }
            }
            carts = new ArrayList<>(cartPositions.values());
        } while (carts.size() != 1);
        collisionPoints.add(carts.getFirst().p);
        return collisionPoints;
    }

    private static char change(char deflection, char source, int intersect) {
       return switch (deflection) {
            case '/' -> source == '<' || source == '>' ? rotate(source, false) : rotate(source, true);
            case '\\' -> source == '<' || source == '>' ? rotate(source, true) : rotate(source, false);
            case '+' -> switch (intersect) {
                case 0 -> rotate(source, false);
                case 1 -> source;
                case 2 -> rotate(source, true);
                default -> throw new RuntimeException();
            };
           default -> throw new RuntimeException();
       };
    }

    private static char rotate(char source, boolean clockwise) {
        return switch (source) {
            case '^' -> clockwise ? '>' : '<';
            case '<' -> clockwise ? '^' : 'v';
            case 'v' -> clockwise ? '<' : '>';
            case '>' -> clockwise ? 'v' : '^';
            default -> throw new RuntimeException();
        };
    }

    private static char get(List<String> lines, Point p) {
        return lines.get(p.i).charAt(p.j);
    }
}
