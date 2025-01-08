package code.vipul.aoc2018Part2;

import code.vipul.utils.AoCInputReader;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2018/day/15
 */
public class Day15 {

    record Point(int i, int j) {}
    record Path(Point start, Point end){}
    record PathWLen(Path path, int len){}
    record Result(Map<Point, Creature> creatureMap, int rounds) {}

    private static final boolean DEBUG = false;
    private static boolean lastCreatureDidNothing = false;
    private static int elfAttackPower = -1;
    private static boolean elfKilled = false;

    private static final Map<Point, List<Point>> NEIGHBOUR_CACHE = new HashMap<>();
    private static final Comparator<Point> POINT_COMPARATOR = Comparator.comparingInt((Point p) -> p.i)
            .thenComparingInt(p -> p.j);
    private static final Comparator<PathWLen> PATH_COMPARATOR = Comparator.comparingInt((PathWLen p) -> p.len)
            .thenComparing(p -> p.path.end, POINT_COMPARATOR)
            .thenComparing(p -> p.path.start, POINT_COMPARATOR);

    record Creature(char c, Point p, int attackPower, int hitPoints, int id) {
        boolean isDead() {
            return hitPoints <= 0;
        }

        Creature attack(Creature other) {
            int power = this.c == 'E' && elfAttackPower != -1 ? elfAttackPower : this.attackPower;
            return new Creature(other.c, other.p, other.attackPower, other.hitPoints - power, other.id);
        }
    }

    private static final int[][] DIFFS = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private static List<String> lines;

    public static void solve() {
        lines = AoCInputReader.read(Day15.class, false);

        Set<Point> walls = new HashSet<>();
        Map<Point, Creature> creatures = new LinkedHashMap<>();
        int index = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char ch = lines.get(i).charAt(j);
                Point pt = new Point(i, j);
                if (ch == '#') walls.add(pt);
                else if (ch == 'E' || ch == 'G') {
                    creatures.put(pt, new Creature(ch, pt, 3, 200, index++));
                }
            }
        }

        Result res = play(walls, creatures, 0, false);
        int total = res.creatureMap.values().stream().map(g -> g.hitPoints).mapToInt(i -> i).sum();

        int rounds = res.rounds;
        if (lastCreatureDidNothing) rounds--;
        System.out.println("Part 1: " + (total * rounds) + " | Rounds: " + rounds + ", HP: " + total); // 188576

        lastCreatureDidNothing = false;
        int minPower = 4, maxPower = 100;
        int val = -1;
        while (minPower < maxPower) {
            int mid = (minPower + maxPower) / 2;
            // System.out.println("Trying power: " + mid);
            elfAttackPower = mid;
            elfKilled = false;
            res = play(walls, creatures, 0, true);
            if (!elfKilled) {
                maxPower = mid - 1;
                total = res.creatureMap.values().stream().map(g -> g.hitPoints).mapToInt(i -> i).sum();

                rounds = res.rounds;
                if (lastCreatureDidNothing) rounds--;
                val = total * rounds;
            } else {
                minPower = mid + 1;
            }
        }

        System.out.println("Part 2: " + val); // 57112
    }

    private static Result play(Set<Point> walls, Map<Point, Creature> creatureMap, int rounds, boolean breakOnElfKill) {
        int types = creatureMap.values().stream().map(c -> c.c).collect(Collectors.toSet()).size();
        if (types == 1) {
            return new Result(creatureMap, rounds);
        }

        List<Integer> creatureOrder = creatureMap.entrySet().stream()
                .sorted((e1, e2) -> POINT_COMPARATOR.compare(e1.getKey(), e2.getKey()))
                .map(c -> c.getValue().id)
                .toList();

        Map<Integer, Creature> currentCreatureMap = new HashMap<>();
        Map<Integer, Point> positionMap = new HashMap<>();
        Map<Point, Integer> reversePositionMap = new HashMap<>();
        for (var entry : creatureMap.entrySet()) {
            currentCreatureMap.put(entry.getValue().id, entry.getValue());
            positionMap.put(entry.getValue().id, entry.getValue().p);
            reversePositionMap.put(entry.getValue().p, entry.getValue().id);
        }

        boolean atleastOneActionWasTaken = false;
        for (int creatureId : creatureOrder) {
            boolean creatureDidNothing = true;
            Creature creature = currentCreatureMap.get(creatureId);
            if (creature == null) continue;
            char otherType = creature.c == 'E' ? 'G' : 'E';
            Point creaturePosition = positionMap.get(creatureId);

            Set<Point> targetPoints = reversePositionMap.keySet().stream()
                    .filter(c -> currentCreatureMap.get(reversePositionMap.get(c)).c == otherType)
                    .collect(Collectors.toSet());

            // Check if we can attack
            boolean didAttack = tryAttack(creaturePosition, creature, otherType, walls,
                    reversePositionMap, positionMap, currentCreatureMap);
            if (didAttack) {
                creatureDidNothing = false;
                lastCreatureDidNothing = false;
                atleastOneActionWasTaken = true;
                continue;
            }

            // Check if we can move
            Optional<Path> targetPath = bfs(creaturePosition, targetPoints, walls, reversePositionMap);

            if (targetPath.isPresent()) {
                Point olderPos = creaturePosition;
                // Move towards the first point
                creaturePosition = targetPath.get().start;
                positionMap.put(creatureId, creaturePosition);
                reversePositionMap.remove(olderPos);
                reversePositionMap.put(creaturePosition, creatureId);
                creatureDidNothing = false;
                lastCreatureDidNothing = false;
                atleastOneActionWasTaken = true;

                tryAttack(creaturePosition, creature, otherType, walls,
                        reversePositionMap, positionMap, currentCreatureMap);
            }

            if (creatureDidNothing) {
                lastCreatureDidNothing = true;
            }
        }

        Map<Point, Creature> newCreatures = new HashMap<>();
        for (var entry : reversePositionMap.entrySet()) {
            Point p = entry.getKey();
            int id = entry.getValue();
            Creature c = currentCreatureMap.get(id);
            Creature updatedCreature = new Creature(c.c, p, c.attackPower, c.hitPoints, id);
            newCreatures.put(p, updatedCreature);
        }
        print(walls, newCreatures);
        if (!atleastOneActionWasTaken || (breakOnElfKill && elfKilled)) { // STOP
            return new Result(newCreatures, rounds);
        }
        return play(walls, newCreatures, rounds + 1, breakOnElfKill);
    }

    private static List<Point> neighbours(Point p, Set<Point> walls) {
        if (NEIGHBOUR_CACHE.containsKey(p)) return NEIGHBOUR_CACHE.get(p);
        List<Point> nbors = Arrays.stream(DIFFS)
                .map(d ->  new Point(p.i + d[0], p.j + d[1]))
                .filter(nb -> !walls.contains(nb)).toList();
        NEIGHBOUR_CACHE.put(p, nbors);
        return nbors;
    }

    private static boolean tryAttack(Point creaturePosition, Creature creature, char otherType, Set<Point> walls,
                                  Map<Point, Integer> reversePositionMap, Map<Integer, Point> positionMap,
                                  Map<Integer, Creature> currentCreatureMap) {

        Optional<Point> attackedCreaturePoint = neighbours(creaturePosition, walls)
                .stream().filter(n -> reversePositionMap.containsKey(n) &&
                        currentCreatureMap.get(reversePositionMap.get(n)).c == otherType)
                .min((p1, p2) -> {
                    int hp1 = currentCreatureMap.get(reversePositionMap.get(p1)).hitPoints,
                            hp2 = currentCreatureMap.get(reversePositionMap.get(p2)).hitPoints;
                    if (hp1 == hp2) return POINT_COMPARATOR.compare(p1, p2);
                    return Integer.compare(hp1, hp2);
                });
        if (attackedCreaturePoint.isEmpty()) return false;
        int attackedCreatureId = reversePositionMap.get(attackedCreaturePoint.get());
        Creature originallyAttackedCreature = currentCreatureMap.get(attackedCreatureId);
        Creature postAttackCreature = creature.attack(originallyAttackedCreature);
        if (postAttackCreature.isDead()) {
            currentCreatureMap.remove(attackedCreatureId);
            if (postAttackCreature.c == 'E') elfKilled = true;
            var prevPos = positionMap.remove(attackedCreatureId);
            reversePositionMap.remove(prevPos);
        } else {
            currentCreatureMap.put(attackedCreatureId, postAttackCreature);
        }
        return true;
    }

    private static Optional<Path> bfs(Point start, Set<Point> endPoints, Set<Point> walls,
                                      Map<Point, Integer> creaturePositions) {
        Set<Point> ends = new HashSet<>();
        for (Point endpoint : endPoints) {
            ends.addAll(neighbours(endpoint, walls));
        }
        Set<Point> starts = new HashSet<>(neighbours(start, walls));
        starts.removeAll(creaturePositions.keySet()); ends.removeAll(creaturePositions.keySet());

        // From all starting neighbours, try reaching all endpoints, collecting the best reachable path along the way
        PathWLen bestReachablePath = null;
        Queue<PathWLen> queue = new ArrayDeque<>();
        Set<Path> visited = new HashSet<>();
        for (Point s :starts) {
            Path p = new Path(s, s);
            queue.add(new PathWLen(p, 0));
            visited.add(p);
        }
        while (!queue.isEmpty()) {
            PathWLen currPwl = queue.remove();
            if (bestReachablePath != null && currPwl.len > bestReachablePath.len) continue;

            if (ends.contains(currPwl.path.end)) {
                if (bestReachablePath == null || PATH_COMPARATOR.compare(bestReachablePath, currPwl) >= 0) {
                    bestReachablePath = currPwl;
                }
                continue;
            }

            for (Point nbor : neighbours(currPwl.path.end, walls)) {
                Path tp = new Path(currPwl.path.start, nbor);
                if (!visited.contains(tp) && !creaturePositions.containsKey(nbor)) {
                    queue.add(new PathWLen(tp, currPwl.len + 1));
                    visited.add(tp);
                }
            }
        }

        return Optional.ofNullable(bestReachablePath).map(p -> p.path);
    }

    private static void print(Set<Point> walls, Map<Point, Creature> creatures) {
        if (!DEBUG) return;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                Point pt = new Point(i, j);
                if (walls.contains(pt)) {
                    System.out.print('#');
                } else if (creatures.containsKey(pt)) {
                    System.out.print(creatures.get(pt).c);
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
}
