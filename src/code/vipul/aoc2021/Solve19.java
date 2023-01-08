package code.vipul.aoc2021;

import code.vipul.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 05/01/23
 * https://adventofcode.com/2021/day/19
 */
public class Solve19 {

    private static final String INPUT = "--- scanner 0 ---\n" +
            "404,-588,-901\n" +
            "528,-643,409\n" +
            "-838,591,734\n" +
            "390,-675,-793\n" +
            "-537,-823,-458\n" +
            "-485,-357,347\n" +
            "-345,-311,381\n" +
            "-661,-816,-575\n" +
            "-876,649,763\n" +
            "-618,-824,-621\n" +
            "553,345,-567\n" +
            "474,580,667\n" +
            "-447,-329,318\n" +
            "-584,868,-557\n" +
            "544,-627,-890\n" +
            "564,392,-477\n" +
            "455,729,728\n" +
            "-892,524,684\n" +
            "-689,845,-530\n" +
            "423,-701,434\n" +
            "7,-33,-71\n" +
            "630,319,-379\n" +
            "443,580,662\n" +
            "-789,900,-551\n" +
            "459,-707,401\n" +
            "\n" +
            "--- scanner 1 ---\n" +
            "686,422,578\n" +
            "605,423,415\n" +
            "515,917,-361\n" +
            "-336,658,858\n" +
            "95,138,22\n" +
            "-476,619,847\n" +
            "-340,-569,-846\n" +
            "567,-361,727\n" +
            "-460,603,-452\n" +
            "669,-402,600\n" +
            "729,430,532\n" +
            "-500,-761,534\n" +
            "-322,571,750\n" +
            "-466,-666,-811\n" +
            "-429,-592,574\n" +
            "-355,545,-477\n" +
            "703,-491,-529\n" +
            "-328,-685,520\n" +
            "413,935,-424\n" +
            "-391,539,-444\n" +
            "586,-435,557\n" +
            "-364,-763,-893\n" +
            "807,-499,-711\n" +
            "755,-354,-619\n" +
            "553,889,-390\n" +
            "\n" +
            "--- scanner 2 ---\n" +
            "649,640,665\n" +
            "682,-795,504\n" +
            "-784,533,-524\n" +
            "-644,584,-595\n" +
            "-588,-843,648\n" +
            "-30,6,44\n" +
            "-674,560,763\n" +
            "500,723,-460\n" +
            "609,671,-379\n" +
            "-555,-800,653\n" +
            "-675,-892,-343\n" +
            "697,-426,-610\n" +
            "578,704,681\n" +
            "493,664,-388\n" +
            "-671,-858,530\n" +
            "-667,343,800\n" +
            "571,-461,-707\n" +
            "-138,-166,112\n" +
            "-889,563,-600\n" +
            "646,-828,498\n" +
            "640,759,510\n" +
            "-630,509,768\n" +
            "-681,-892,-333\n" +
            "673,-379,-804\n" +
            "-742,-814,-386\n" +
            "577,-820,562\n" +
            "\n" +
            "--- scanner 3 ---\n" +
            "-589,542,597\n" +
            "605,-692,669\n" +
            "-500,565,-823\n" +
            "-660,373,557\n" +
            "-458,-679,-417\n" +
            "-488,449,543\n" +
            "-626,468,-788\n" +
            "338,-750,-386\n" +
            "528,-832,-391\n" +
            "562,-778,733\n" +
            "-938,-730,414\n" +
            "543,643,-506\n" +
            "-524,371,-870\n" +
            "407,773,750\n" +
            "-104,29,83\n" +
            "378,-903,-323\n" +
            "-778,-728,485\n" +
            "426,699,580\n" +
            "-438,-605,-362\n" +
            "-469,-447,-387\n" +
            "509,732,623\n" +
            "647,635,-688\n" +
            "-868,-804,481\n" +
            "614,-800,639\n" +
            "595,780,-596\n" +
            "\n" +
            "--- scanner 4 ---\n" +
            "727,592,562\n" +
            "-293,-554,779\n" +
            "441,611,-461\n" +
            "-714,465,-776\n" +
            "-743,427,-804\n" +
            "-660,-479,-426\n" +
            "832,-632,460\n" +
            "927,-485,-438\n" +
            "408,393,-506\n" +
            "466,436,-512\n" +
            "110,16,151\n" +
            "-258,-428,682\n" +
            "-393,719,612\n" +
            "-211,-452,876\n" +
            "808,-476,-593\n" +
            "-575,615,604\n" +
            "-485,667,467\n" +
            "-680,325,-822\n" +
            "-627,-443,-432\n" +
            "872,-547,-609\n" +
            "833,512,582\n" +
            "807,604,487\n" +
            "839,-516,451\n" +
            "891,-625,532\n" +
            "-652,-548,-490\n" +
            "30,-46,-14";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_19.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int minScanner = Integer.MAX_VALUE;
        int maxScanner = Integer.MIN_VALUE;
        Map<Integer, List<Point>> beaconMap = new HashMap<>();
        int currentScanner = -1;
        for (String in : inputs) {
            if (in.contains("scanner")) {
                currentScanner = Integer.parseInt(in.split("--- scanner ")[1].split(" ")[0]);
                beaconMap.put(currentScanner, new ArrayList<>());
                minScanner = Math.min(minScanner, currentScanner);
                maxScanner = Math.max(maxScanner, currentScanner);
            } else if (!in.isEmpty()) {
                Point point = Point.of(in);
                beaconMap.get(currentScanner).add(point);
            }
        }

        Map<Integer, Map<List<Integer>, List<Point>>> revDistances = new HashMap<>();
        for (int scanner = minScanner; scanner <= maxScanner; scanner++) {
            List<Point> beacons = beaconMap.get(scanner);
            revDistances.put(scanner, new HashMap<>());
            for (int i = 0; i < beacons.size() - 1; i++) {
                for (int j = i + 1; j < beacons.size(); j++) {
                    Point b1 = beacons.get(i);
                    Point b2 = beacons.get(j);
                    List<Integer> man = b1.manhattanList(b2);
                    revDistances.get(scanner).put(man, List.of(b1, b2));
                }
            }
        }

        Map<Integer, Map<Integer, Transformer>> pointTransformers = new HashMap<>();
        Map<Integer, Map<Integer, Map<Point, Point>>> pointMappings = new HashMap<>();
        for (int s1 = minScanner; s1 <= maxScanner - 1; s1++) {
            Map<List<Integer>, List<Point>> d1 = revDistances.get(s1);
            for (int s2 = s1 + 1; s2 <= maxScanner; s2++) {
                Map<List<Integer>, List<Point>> d2 = revDistances.get(s2);
                Map<Point, Map<Point, Integer>> possibles = new HashMap<>();
                for (List<Integer> distances : d1.keySet()) {
                    if (d2.containsKey(distances)) {
                        List<Point> p1 = d1.get(distances);
                        List<Point> p2 = d2.get(distances);

                        map(possibles, p1.get(0), p2.get(0));
                        map(possibles, p1.get(0), p2.get(1));
                        map(possibles, p1.get(1), p2.get(0));
                        map(possibles, p1.get(1), p2.get(1));
                    }
                }

                if (possibles.size() < 12) {
                    continue;
                }
                Map<Point, Point> finalMapping = new LinkedHashMap<>();
                for (var entry : possibles.entrySet()) {
                    Point p1 = entry.getKey();
                    var matchEntry = entry.getValue().entrySet().stream()
                            .max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow();
                    Point p2 = matchEntry.getKey();
                    finalMapping.put(p1, p2);
                }
                pointMappings.putIfAbsent(s1, new HashMap<>());
                pointMappings.get(s1).put(s2, finalMapping);
            }
        }

        for (var entry : pointMappings.entrySet()) {
            int s1 = entry.getKey();
            var mappings = entry.getValue();
            for (var entry1 : mappings.entrySet()) {
                int s2 = entry1.getKey();
                var points = entry1.getValue();

                Transformer t1t2 = new Transformer(s1, s2);
                Transformer t2t1 = new Transformer(s2, s1);

                List<Axis> axes = Axis.getAxes();
                for (var a1 : axes) {
                    for (var a2 : axes) {
                        var diff = getTransformType(points, a1.provider(), a2.provider());
                        if (diff != null) {
                            t1t2.mapAxes(a1, a2, diff);
                            break;
                        }
                    }
                }

                var revPoints = reverse(points);
                for (var a1 : axes) {
                    for (var a2 : axes) {
                        var diff = getTransformType(revPoints, a1.provider(), a2.provider());
                        if (diff != null) {
                            t2t1.mapAxes(a1, a2, diff);
                            break;
                        }
                    }
                }
                pointTransformers.putIfAbsent(s1, new HashMap<>());
                pointTransformers.putIfAbsent(s2, new HashMap<>());
                pointTransformers.get(s1).put(s2, t1t2);
                pointTransformers.get(s2).put(s1, t2t1);
            }
        }

        Set<Point> allBeaconsWrtZero = new HashSet<>();

        for (int scanner = minScanner; scanner <= maxScanner; scanner++) {
            var points = beaconMap.get(scanner);

            if (scanner == 0) {
                allBeaconsWrtZero.addAll(points);
                continue;
            }

            List<Integer> order = getOrder(pointTransformers, scanner);
            if (order.size() == 0) {
                throw new RuntimeException("Cant transform scanner " + scanner + " to scanner 0");
            }
            List<Point> currentPoints = new ArrayList<>(points);
            for (int i = 0; i < order.size() - 1; i++) {
                List<Point> transformed = new ArrayList<>();
                int from = order.get(i);
                int to = order.get(i + 1);
                Transformer transformer = pointTransformers.get(from).get(to);
                currentPoints.forEach(p -> transformed.add(transformer.transform(p)));
                currentPoints = transformed;
            }
            allBeaconsWrtZero.addAll(currentPoints);
        }
        System.out.println("Part 1: " + allBeaconsWrtZero.size());

        Map<Integer, Point> scanners = new HashMap<>();
        scanners.put(0, new Point(0, 0, 0));
        for (int scanner = 1; scanner <= maxScanner; scanner++) {
            List<Integer> order = getOrder(pointTransformers, scanner);
            Point toConvert = scanners.get(0);
            for (int i = 0; i < order.size() - 1; i++) {
                int from = order.get(i);
                int to = order.get(i + 1);
                Transformer transformer = pointTransformers.get(from).get(to);
                toConvert = transformer.transform(toConvert);
            }
            scanners.put(scanner, toConvert);
        }

        int maxDiff = Integer.MIN_VALUE;
        for (int s1 = minScanner; s1 <= maxScanner - 1; s1++) {
            for (int s2 = s1 + 1; s2 <= maxScanner; s2++) {
                var p1 = scanners.get(s1);
                var p2 = scanners.get(s2);

                maxDiff = Math.max(p1.manhattan(p2), maxDiff);
            }
        }
        System.out.println("Part 2: " + maxDiff);
    }

    private static Pair<DiffType, Integer> getTransformType(Map<Point, Point> points,
                                                            Function<Point, Integer> mapKey,
                                                            Function<Point, Integer> mapValue) {
        Integer p1PlusP2 = null;
        Integer p1MinusP2 = null;

        boolean canBePlus = true;
        boolean canBeMinus = true;

        for (var entry : points.entrySet()) {
            Point p1 = entry.getKey();
            Point p2 = entry.getValue();

            int v1 = mapKey.apply(p1);
            int v2 = mapValue.apply(p2);

            if (p1PlusP2 == null) {
                p1PlusP2 = v1 + v2;
            } else if (canBePlus) {
                if (p1PlusP2 != v1 + v2) {
                    canBePlus = false;
                }
            }

            if (p1MinusP2 == null) {
                p1MinusP2 = v1 - v2;
            } else if (canBeMinus) {
                if (p1MinusP2 != v1 - v2) {
                    canBeMinus = false;
                }
            }

            if (!canBeMinus && !canBePlus) {
                break;
            }
        }

        if (!canBeMinus && !canBePlus) {
            return null;
        } else if (canBeMinus) {
            return Pair.of(DiffType.F_MINUS_S, p1MinusP2);
        } else {
            return Pair.of(DiffType.F_PLUS_S, p1PlusP2);
        }
    }

    private enum Axis {
        X,
        Y,
        Z;

        public Function<Point, Integer> provider() {
            switch (this) {
                case X:
                    return p -> p.x;
                case Y:
                    return p -> p.y;
                case Z:
                    return p -> p.z;
                default:
                    throw new RuntimeException();
            }
        }

        public Point change(Point p, int val) {
            switch (this) {
                case X:
                    return new Point(val, p.y, p.z);
                case Y:
                    return new Point(p.x, val, p.z);
                case Z:
                    return new Point(p.x, p.y, val);
                default:
                    throw new RuntimeException();
            }
        }

        public static List<Axis> getAxes() {
            return List.of(X, Y, Z);
        }
    }

    private static List<Integer> getOrder(Map<Integer, Map<Integer, Transformer>> pointTransformers, int destination) {
        Queue<Integer> q = new ArrayDeque<>();
        Queue<Set<Integer>> vq = new ArrayDeque<>();
        q.add(0);
        Set<Integer> v = new LinkedHashSet<>();
        v.add(0);
        vq.add(v);

        Set<Integer> ordered = null;
        while (!q.isEmpty()) {
            int current = q.remove();
            Set<Integer> visited = vq.remove();

            if (current == destination) {
                ordered = visited;
                break;
            }

            for (Transformer t : pointTransformers.get(current).values()) {
                if (!visited.contains(t.toScanner)) {
                    Set<Integer> nv = new LinkedHashSet<>(visited);
                    nv.add(t.toScanner);
                    q.add(t.toScanner);
                    vq.add(nv);
                }
            }
        }

        var stack = new Stack<Integer>();
        if (ordered == null) {
            return List.of();
        }
        for (Integer o : ordered) {
            stack.push(o);
        }

        List<Integer> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }

    private static class Transformer {
        private final int fromScanner;
        private final int toScanner;
        private final Map<Axis, Axis> axisMapping = new HashMap<>();
        private final Map<Axis, Pair<DiffType, Integer>> axisDiffMapping = new HashMap<>();

        public Transformer(int f, int t) {
            this.fromScanner = f;
            this.toScanner = t;
        }

        public Point transform(Point p) {
            Point converted = new Point(0, 0, 0);
            for (var entry : axisMapping.entrySet()) {
                Axis fromAxis = entry.getKey();
                Axis toAxis = entry.getValue();
                var diff = axisDiffMapping.get(fromAxis);
                converted = toAxis.change(converted, getValue(diff, fromAxis.provider().apply(p)));
            }
            return converted;
        }

        public void mapAxes(Axis f, Axis s, Pair<DiffType, Integer> diff) {
            this.axisMapping.put(f, s);
            this.axisDiffMapping.put(f, diff);
        }

        private int getValue(Pair<DiffType, Integer> diff, int fromCoordinate) {
            DiffType type = diff.left();
            int difference = diff.right();
            if (type == DiffType.F_PLUS_S) {
                return difference - fromCoordinate;
            }
            return fromCoordinate - difference;
        }
    }

    private static void map(Map<Point, Map<Point, Integer>> possible, Point from, Point to) {
        possible.putIfAbsent(from, new HashMap<>());
        possible.get(from).putIfAbsent(to, 0);
        possible.get(from).put(to, possible.get(from).get(to) + 1);
    }

    private enum DiffType {
        F_PLUS_S,
        F_MINUS_S;

        public static List<DiffType> all() {
            return List.of(F_PLUS_S, F_MINUS_S);
        }
    }

    private static Map<Point, Point> reverse(Map<Point, Point> map) {
        return map.entrySet().stream().collect(Collectors.toMap(e -> e.getValue(), e -> e.getKey()));
    }

    private static class Point {
        private final int x;
        private final int y;
        private final int z;
        private int hash = -1;

        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        static Point of(String line) {
            String[] xyz = line.split(",");
            int x = Integer.parseInt(xyz[0]);
            int y = Integer.parseInt(xyz[1]);
            int z = Integer.parseInt(xyz[2]);

            return new Point(x, y, z);
        }

        int manhattan(Point p) {
            return Math.abs(p.x - x) + Math.abs(p.y - y) + Math.abs(p.z - z);
        }

        List<Integer> manhattanList(Point p) {
            var l = new ArrayList<>(List.of(Math.abs(p.x - x), Math.abs(p.y - y), Math.abs(p.z - z)));
            l.sort(Comparator.naturalOrder());
            return l;
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(x);
                hash += (hash << 5) + Objects.hashCode(y);
                hash += (hash << 5) + Objects.hashCode(z);
            }
            return hash;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof Point && equalTo((Point) another);
        }

        @Override
        public String toString() {
            return String.format("x: %s, y: %s, z: %s", x, y, z);
        }

        private boolean equalTo(Point another) {
            return Objects.equals(x, another.x) && Objects.equals(y, another.y) && Objects.equals(z, another.z);
        }
    }
}
