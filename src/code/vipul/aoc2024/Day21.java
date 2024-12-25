package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.*;

/**
 * https://adventofcode.com/2024/day/21
 */
public class Day21 {

    record Point(int i, int j) {}
    private static final Map<Character, int[]> MOVES;
    private static final Map<Character, Map<Character, List<String>>> NUMPAD_PATHS_CACHE = new HashMap<>();
    private static final Map<Character, Map<Character, List<String>>> DIRPAD_PATHS_CACHE = new HashMap<>();
    private static final Map<Character, Point> DIRPAD_POINTS;
    private static final Map<Character, Point> NUMPAD_POINTS;
    private static final Map<Character, Integer> DIRPAD_IDS;
    private static long[][] dp;
    private static long[][][] dp2;

    static {
        MOVES = new HashMap<>();
        MOVES.put('^', new int[]{-1, 0});
        MOVES.put('>', new int[]{0, 1});
        MOVES.put('<', new int[]{0, -1});
        MOVES.put('v', new int[]{1, 0});
        DIRPAD_POINTS = new HashMap<>();
        DIRPAD_POINTS.put('^', new Point(0, 1));
        DIRPAD_POINTS.put('A', new Point(0, 2));
        DIRPAD_POINTS.put('<', new Point(1, 0));
        DIRPAD_POINTS.put('v', new Point(1, 1));
        DIRPAD_POINTS.put('>', new Point(1, 2));
        DIRPAD_IDS = new HashMap<>();
        int idx = 0;
        for (var key : DIRPAD_POINTS.keySet()) {
            DIRPAD_IDS.put(key, idx++);
        }
        NUMPAD_POINTS = new HashMap<>();
        NUMPAD_POINTS.put('7', new Point(0, 0));
        NUMPAD_POINTS.put('8', new Point(0, 1));
        NUMPAD_POINTS.put('9', new Point(0, 2));
        NUMPAD_POINTS.put('4', (new Point(1, 0)));
        NUMPAD_POINTS.put('5', (new Point(1, 1)));
        NUMPAD_POINTS.put('6', new Point(1, 2));
        NUMPAD_POINTS.put('1', new Point(2, 0));
        NUMPAD_POINTS.put('2', new Point(2, 1));
        NUMPAD_POINTS.put('3', new Point(2, 2));
        NUMPAD_POINTS.put('0', new Point(3, 1));
        NUMPAD_POINTS.put('A', new Point(3, 2));
    }

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day21.class, false);

        dp = new long[150][30];
        System.out.println("Part 1: " + getPossible(lines, 2)); // 154208
        System.out.println("Part 2: " + getPossible(lines, 25)); // 188000493837892

        // Alternate solution
        dp2 = new long[5][5][30];
        System.out.println("Part 1: " + getPossible2(lines, 2));
        System.out.println("Part 2: " + getPossible2(lines, 25));
    }

    private static long getPossible(List<String> lines, int middleRobots) {
        List<Character> numpadCharacters = new ArrayList<>(NUMPAD_POINTS.keySet());
        Set<String> allPossiblesSet = new HashSet<>();
        // Get all possible movements between the numpad points
        for (char d1 : numpadCharacters) {
            for (char d2 : numpadCharacters) {
                List<String> possibles = bfs(NUMPAD_POINTS, d1, d2, NUMPAD_PATHS_CACHE);
                allPossiblesSet.addAll(possibles);
            }
        }

        List<String> allPossibles = new ArrayList<>(allPossiblesSet);
        // There are only a limited number of possible movements found, index them
        Map<String, Integer> index = new HashMap<>();
        Map<Integer, String> reverseIndex = new HashMap<>();
        for (int i = 0; i < allPossibles.size(); i++) {
            index.put(allPossibles.get(i), i);
            reverseIndex.put(i, allPossibles.get(i));
        }

        // This is the compressed representation of a possible movement
        // key is index of the movement, value is the list of (list of constituent part indices)
        // For example: ^A<A>>^A (mapping X) => ^A (mapping A) + <A (mapping B) + >>^A (mapping C);
        // then the map contains: X => [A,B,C]
        Map<Integer, List<List<Integer>>> mapping = new HashMap<>();
        // For each possible path found above
        for (String possible : allPossibles) {
            // Find all possible inputs that are generated if we try to generate it from a dir pad
            Set<String> possibleForCurrent = getAllPossibleInputs(possible, DIRPAD_POINTS, DIRPAD_PATHS_CACHE);

            mapping.putIfAbsent(index.get(possible), new ArrayList<>());
            for (String poss : possibleForCurrent) {
                // Try to break down each possibility into a concatenation of base possibles
                // So that we have a link between one movement to others
                // and keep storing the mapping of the current possibility into concatenation of other possibilities
                List<Integer> indices = new ArrayList<>();
                while (!poss.isEmpty()) {
                    for (String tp : allPossibles) {
                        if (poss.startsWith(tp)) {
                            indices.add(index.get(tp));
                            poss = poss.substring(tp.length());
                            break;
                        }
                    }
                }
                // Store the mapping
                mapping.get(index.get(possible)).add(indices);
            }
        }

        // Start evaluating the input
        long total = 0;
        for (String line : lines) {
            // Get the possibilities for generating the line on the numpad
            Set<String> possibleForCurrent = getAllPossibleInputs(line, NUMPAD_POINTS, NUMPAD_PATHS_CACHE);

            long minlen = Long.MAX_VALUE;
            for (String poss : possibleForCurrent) {
                List<Integer> indices = new ArrayList<>();
                // Break each possibility down to the constituent base possibilities, and record the indices
                while (!poss.isEmpty()) {
                    for (String tp : allPossibles) {
                        if (poss.startsWith(tp)) {
                            indices.add(index.get(tp));
                            poss = poss.substring(tp.length());
                            break;
                        }
                    }
                }

                long len = 0;
                // Now that we have all the indices, recursively calculate the length
                for (Integer idx : indices) {
                    len += getLength(idx, middleRobots, mapping, reverseIndex);
                }
                minlen = Math.min(len, minlen);
            }

            int val = Integer.parseInt(line.substring(0, line.length() - 1));
            total += (minlen * val);
        }
        return total;
    }

    // Get the length of the given indexed possibility for the given number of rounds
    // Since the only thing changing here is curr and rounds, and it recursively calls itself with changing those values
    // We do a memoization optimization
    private static long getLength(int curr, int rounds,
                                  Map<Integer, List<List<Integer>>> mapping,
                                  Map<Integer, String> reverseIndex) {
        if (dp[curr][rounds] != 0) {
            return dp[curr][rounds];
        }
        // if only one round is left, then simply get all the possible mappings
        // calculate their lengths, and return the minimum
        if (rounds == 1) {
            List<List<Integer>> mappings = mapping.get(curr);
            List<Long> lengths = new ArrayList<>();
            for (List<Integer> mapp : mappings) {
                long len = 0;
                for (Integer idx : mapp) {
                    len += reverseIndex.get(idx).length();
                }
                lengths.add(len);
            }
            long v = lengths.stream().min(Comparator.naturalOrder()).orElseThrow();
            dp[curr][rounds] = v;
            return v;
        }

        // Get all the mappings, and recursively calculate the length from each constituent index
        List<List<Integer>> mappings = mapping.get(curr);
        List<Long> lengths = new ArrayList<>();
        for (List<Integer> mapp : mappings) {
            long len = 0;
            for (Integer idx : mapp) {
                len += getLength(idx, rounds - 1, mapping, reverseIndex);
            }
            lengths.add(len);
        }
        long v = lengths.stream().min(Comparator.naturalOrder()).orElseThrow();
        dp[curr][rounds] = v;
        return v;
    }


    private static long getPossible2(List<String> lines, int rounds) {
        long total = 0;
        for (String line : lines) {
            // Get the possibilities for generating the line on the numpad
            Set<String> possibleForCurrent = getAllPossibleInputs(line, NUMPAD_POINTS, NUMPAD_PATHS_CACHE);

            long minlen = Long.MAX_VALUE;
            for (String poss : possibleForCurrent) {
                // For each possible movement, recursively calculate the minimum length possible
                // by going down the middle robots, defined as `rounds` here
                char current = 'A';
                long curlen = 0;
                for (char ch : poss.toCharArray()) {
                    curlen += getMin(current, ch, rounds);
                    current = ch;
                }
                minlen = Math.min(minlen, curlen);
            }

            int val = Integer.parseInt(line.substring(0, line.length() - 1));
            total += (minlen * val);
        }
        return total;
    }

    // Recursively calculate the minimum length of base inputs to go from `from` to `to`
    private static long getMin(char from, char to, int roundsLeft) {
        int fromId = DIRPAD_IDS.get(from), toId = DIRPAD_IDS.get(to);
        if (dp2[fromId][toId][roundsLeft] != 0) {
            return dp2[fromId][toId][roundsLeft];
        }
        if (roundsLeft == 1) { // in the last round, just return the min length of from -> to
            List<String> possibles = bfs(DIRPAD_POINTS, from, to, DIRPAD_PATHS_CACHE);
            long v = possibles.stream().map(p -> p.length()).min(Comparator.naturalOrder()).orElseThrow();
            dp2[fromId][toId][roundsLeft]= v;
            return v;
        }

        List<String> possibles = bfs(DIRPAD_POINTS, from, to, DIRPAD_PATHS_CACHE);
        long minlen = Long.MAX_VALUE;
        for (String poss : possibles) {
            char current = 'A';
            long curlen = 0;
            for (char ch : poss.toCharArray()) {
                curlen += getMin(current, ch, roundsLeft - 1);
                current = ch;
            }
            minlen = Math.min(minlen, curlen);
        }
        dp2[fromId][toId][roundsLeft]= minlen;
        return minlen;
    }

    // Get all possible inputs if we want to generate the given `path` string from a given pad, starting from 'A'
    // The pad is defined by the passed `pad` parameter
    private static Set<String> getAllPossibleInputs(String path,
                                                    Map<Character, Point> pad,
                                                    Map<Character, Map<Character, List<String>>> cache) {
        char curr = 'A';
        Set<String> possibleForCurrent = new HashSet<>();
        for (char ch : path.toCharArray()) {
            List<String> paths = bfs(pad, curr, ch, cache);

            Set<String> tmp = new HashSet<>();
            if (possibleForCurrent.isEmpty()) {
                tmp.addAll(paths);
            } else {
                for (String pfc : possibleForCurrent) {
                    for (String p : paths) {
                        tmp.add(pfc + p);
                    }
                }
            }
            possibleForCurrent = tmp;
            curr = ch;
        }
        return possibleForCurrent;
    }

    record QueueEntry(Point p, String path){}
    // BFS between 2 points giving the best paths
    private static List<String> bfs(Map<Character, Point> pts, char start, char end,
                                    Map<Character, Map<Character, List<String>>> pathCache) {
        if (pathCache.containsKey(start) && pathCache.get(start).containsKey(end)) {
            return pathCache.get(start).get(end);
        }
        Map<Point, Character> revPts = getRevMap(pts);

        Queue<QueueEntry> queue = new ArrayDeque<>();
        Map<Point, Integer> visited = new HashMap<>();
        queue.add(new QueueEntry(pts.get(start), ""));
        visited.put(pts.get(start), 0);

        List<String> paths = new ArrayList<>();
        int minlen = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            var queueEntry = queue.remove();
            Point p = queueEntry.p;
            String path = queueEntry.path;
            if (revPts.get(p) == end) {
                path += "A";
                int curlen = path.length();
                if (curlen < minlen) {
                    paths = new ArrayList<>();
                    paths.add(path);
                    minlen = curlen;
                } else if (curlen == minlen) {
                    paths.add(path);
                }
                continue;
            }

            for (var entry : MOVES.entrySet()) {
                int[] d = entry.getValue();
                char mv = entry.getKey();

                Point t = new Point(p.i + d[0], p.j + d[1]);
                if (revPts.containsKey(t) && (!visited.containsKey(t) || visited.get(t) >= path.length())) {
                    queue.add(new QueueEntry(t, path + mv));
                    visited.put(t, path.length());
                }
            }
        }
        pathCache.putIfAbsent(start, new HashMap<>());
        pathCache.get(start).put(end, paths);
        return paths;
    }

    private static Map<Point, Character> getRevMap(Map<Character, Point> pts) {
        Map<Point, Character> rev = new LinkedHashMap<>();
        for (var entry : pts.entrySet()) {
            rev.put(entry.getValue(), entry.getKey());
        }
        return rev;
    }
}
