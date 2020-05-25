package code.vipul.google;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *Prepare the Bunnies' Escape
 * ===========================
 *
 * You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's bunny prisoners, but once they're free of the prison blocks, the bunnies are going to need to escape Lambda's space station via the escape pods as quickly as possible. Unfortunately, the halls of the space station are a maze of corridors and dead ends that will be a deathtrap for the escaping bunnies. Fortunately, Commander Lambda has put you in charge of a remodeling project that will give you the opportunity to make things a little easier for the bunnies. Unfortunately (again), you can't just remove all obstacles between the bunnies and the escape pods - at most you can remove one wall per escape pod path, both to maintain structural integrity of the station and to avoid arousing Commander Lambda's suspicions.
 *
 * You have maps of parts of the space station, each starting at a prison exit and ending at the door to an escape pod. The map is represented as a matrix of 0s and 1s, where 0s are passable space and 1s are impassable walls. The door out of the prison is at the top left (0,0) and the door into an escape pod is at the bottom right (w-1,h-1).
 *
 * Write a function solution(map) that generates the length of the shortest path from the prison door to the escape pod, where you are allowed to remove one wall as part of your remodeling plans. The path length is the total number of nodes you pass through, counting both the entrance and exit nodes. The starting and ending positions are always passable (0). The map will always be solvable, though you may or may not need to remove a wall. The height and width of the map can be from 2 to 20. Moves can only be made in cardinal directions; no diagonal moves are allowed.
 */
// AC
public class L3P2 {

    public static final String MAP1 = // Answer: 16
                    "..#.........\n" +
                    "#.##....###.\n" +
                    "#....##.....\n" +
                    "#.#...###.#.\n" +
                    "######.#....";

    public static final String MAP2 = // Answer: 11
                    "......\n" +
                    "#####.\n" +
                    "......\n" +
                    ".#####\n" +
                    ".#####\n" +
                    "......";

    public static final String MAP3 = // Answer: 16
                    ".###########\n" +
                    "#..........#\n" +
                    "#....#.....#\n" +
                    "#..........#\n" +
                    "##########..";

    private static int[][] currentMap;
    private static int rows;
    private static int cols;
    private static int answer = Integer.MAX_VALUE;
    private static Map<Pos, Integer> valuesUntilFromTop = new TreeMap<>();
    private static Map<Pos, Integer> valuesUntilFromBottom = new TreeMap<>();

    public static void solve() {
        int val = solution(convert(MAP1));

        System.out.println(val);
    }

    private static int solution(int[][] map) {
        currentMap = map;
        rows = map.length;
        cols = map[0].length;

        Pos start = Pos.of(0, 0);
        valuesUntilFromTop.put(start, 1);
        walkDown(start, new HashSet<>());

        Pos end = Pos.of(rows - 1, cols - 1);
        answer = Math.min(answer, valuesUntilFromTop.getOrDefault(end, Integer.MAX_VALUE));

        valuesUntilFromBottom.put(end, 1);
        walkUp(end, new HashSet<>());

        for(Pos fromBottomPos : valuesUntilFromBottom.keySet()) {
            List<Pos> allNeighbours = getNeighbours(fromBottomPos);
            Set<Pos> pts = allNeighbours.stream().filter(n -> cellValue(n) == 1)
                    .map(n -> getNeighbours(n))
                    .flatMap(Collection::stream)
                    .filter(n -> valuesUntilFromTop.containsKey(n))
                    .collect(Collectors.toSet());

            for (Pos pt : pts) {
                int currentAns = valuesUntilFromBottom.get(fromBottomPos) + valuesUntilFromTop.get(pt) + 1;
                answer = Math.min(answer, currentAns);
            }
        }

        return answer;
    }

    private static void walkDown(Pos currentPos, Set<Pos> visited) {
        if (visited.contains(currentPos)) {
            return;
        }
        visited.add(currentPos);
        List<Pos> neighbours = getNeighbours(currentPos).stream()
                .filter(n -> !visited.contains(n) && cellValue(n) == 0)
                .collect(Collectors.toList());

        for (Pos neighbour : neighbours) {
            valuesUntilFromTop.putIfAbsent(neighbour, Integer.MAX_VALUE);
            int existingValue = valuesUntilFromTop.get(neighbour);

            if (existingValue < valuesUntilFromTop.get(currentPos) + 1) {
                valuesUntilFromTop.put(neighbour, existingValue);
            } else {
                valuesUntilFromTop.put(neighbour, valuesUntilFromTop.get(currentPos) + 1);
                walkDown(neighbour, new HashSet<>(visited));
            }
        }
    }

    private static void walkUp(Pos currentPos, Set<Pos> visited) {
        if (visited.contains(currentPos)) {
            return;
        }
        visited.add(currentPos);
        List<Pos> allNeighbours = getNeighbours(currentPos);
        List<Pos> neighbours = allNeighbours.stream()
                .filter(n -> !visited.contains(n) && cellValue(n) == 0)
                .collect(Collectors.toList());

        for (Pos neighbour : neighbours) {
            valuesUntilFromBottom.putIfAbsent(neighbour, Integer.MAX_VALUE);
            int existingValue = valuesUntilFromBottom.get(neighbour);

            if (existingValue < valuesUntilFromBottom.get(currentPos) + 1) {
                valuesUntilFromBottom.put(neighbour, existingValue);
            } else {
                valuesUntilFromBottom.put(neighbour, valuesUntilFromBottom.get(currentPos) + 1);
                walkUp(neighbour, new HashSet<>(visited));
            }
        }
    }

    private static int cellValue(Pos pos) {
        return currentMap[pos.i()][pos.j()];
    }

    private static List<Pos> getNeighbours(Pos pos) {
        List<Pos> neighbours = new ArrayList<>();

        if (!pos.isAtRightBoundary()) {
            neighbours.add(pos.moveRight());
        }

        if (!pos.isAtBottomBoundary()) {
            neighbours.add(pos.moveDown());
        }

        if (!pos.isAtLeftBoundary()) {
            neighbours.add(pos.moveLeft());
        }

        if (!pos.isAtTopBoundary()) {
            neighbours.add(pos.moveUp());
        }
        return neighbours;
    }

    public static final class Pos implements Comparable<Pos> {
        public int i() {
            return i;
        }

        public int j() {
            return j;
        }

        private final int i;
        private final int j;
        private int hash = -1;

        private Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public static Pos of(int i, int j) {
            return new Pos(i, j);
        }

        public Pos moveLeft() {
            return new Pos(i, j - 1);
        }

        public Pos moveRight() {
            return new Pos(i, j + 1);
        }

        public Pos moveUp() {
            return new Pos(i - 1, j);
        }

        public Pos moveDown() {
            return new Pos(i + 1, j);
        }

        public boolean isAtTopBoundary() {
            return i == 0;
        }

        public boolean isAtBottomBoundary() {
            return i == rows - 1;
        }

        public boolean isAtLeftBoundary() {
            return j == 0;
        }

        public boolean isAtRightBoundary() {
            return j == cols - 1;
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(i);
                hash += (hash << 5) + Objects.hashCode(j);
            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof Pos && equalTo((Pos) another);
        }

        @Override
        public String toString() {
            return String.format("i: %s, j: %s", i, j);
        }

        private boolean equalTo(Pos another) {
            return Objects.equals(i, another.i) && Objects.equals(j, another.j);
        }

        @Override
        public int compareTo(Pos p) {
            int ret = this.i - p.i;
            // Equal i so fall back to comparing j.
            if (ret == 0) {
                ret = this.j - p.j;
            }
            return ret;
        }
    }

    private static int[][] convert(String map) {
        String[] rowss = map.split("\n");
        int cols = rowss[0].length();
        int rows = rowss.length;
        int[][] testmap = new int[rows][cols];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                testmap[i][j] = rowss[i].charAt(j) == '.' ? 0 : 1;
            }
        }
        return testmap;
    }
}
