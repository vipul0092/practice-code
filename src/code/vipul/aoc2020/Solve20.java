package code.vipul.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import static code.vipul.aoc2020.Inputs2.INPUT_20;

/**
 * Created by vgaur created on 11/09/22
 * https://adventofcode.com/2020/day/20
 */
public class Solve20 {

    private static final String INPUT = "Tile 2311:\n" +
            "..##.#..#.\n" +
            "##..#.....\n" +
            "#...##..#.\n" +
            "####.#...#\n" +
            "##.##.###.\n" +
            "##...#.###\n" +
            ".#.#.#..##\n" +
            "..#....#..\n" +
            "###...#.#.\n" +
            "..###..###\n" +
            "\n" +
            "Tile 1951:\n" +
            "#.##...##.\n" +
            "#.####...#\n" +
            ".....#..##\n" +
            "#...######\n" +
            ".##.#....#\n" +
            ".###.#####\n" +
            "###.##.##.\n" +
            ".###....#.\n" +
            "..#.#..#.#\n" +
            "#...##.#..\n" +
            "\n" +
            "Tile 1171:\n" +
            "####...##.\n" +
            "#..##.#..#\n" +
            "##.#..#.#.\n" +
            ".###.####.\n" +
            "..###.####\n" +
            ".##....##.\n" +
            ".#...####.\n" +
            "#.##.####.\n" +
            "####..#...\n" +
            ".....##...\n" +
            "\n" +
            "Tile 1427:\n" +
            "###.##.#..\n" +
            ".#..#.##..\n" +
            ".#.##.#..#\n" +
            "#.#.#.##.#\n" +
            "....#...##\n" +
            "...##..##.\n" +
            "...#.#####\n" +
            ".#.####.#.\n" +
            "..#..###.#\n" +
            "..##.#..#.\n" +
            "\n" +
            "Tile 1489:\n" +
            "##.#.#....\n" +
            "..##...#..\n" +
            ".##..##...\n" +
            "..#...#...\n" +
            "#####...#.\n" +
            "#..#.#.#.#\n" +
            "...#.#.#..\n" +
            "##.#...##.\n" +
            "..##.##.##\n" +
            "###.##.#..\n" +
            "\n" +
            "Tile 2473:\n" +
            "#....####.\n" +
            "#..#.##...\n" +
            "#.##..#...\n" +
            "######.#.#\n" +
            ".#...#.#.#\n" +
            ".#########\n" +
            ".###.#..#.\n" +
            "########.#\n" +
            "##...##.#.\n" +
            "..###.#.#.\n" +
            "\n" +
            "Tile 2971:\n" +
            "..#.#....#\n" +
            "#...###...\n" +
            "#.#.###...\n" +
            "##.##..#..\n" +
            ".#####..##\n" +
            ".#..####.#\n" +
            "#..#.#..#.\n" +
            "..####.###\n" +
            "..#.#.###.\n" +
            "...#.#.#.#\n" +
            "\n" +
            "Tile 2729:\n" +
            "...#.#.#.#\n" +
            "####.#....\n" +
            "..#.#.....\n" +
            "....#..#.#\n" +
            ".##..##.#.\n" +
            ".#.####...\n" +
            "####.#.#..\n" +
            "##.####...\n" +
            "##..#.##..\n" +
            "#.##...##.\n" +
            "\n" +
            "Tile 3079:\n" +
            "#.#.#####.\n" +
            ".#..######\n" +
            "..#.......\n" +
            "######....\n" +
            "####.#..#.\n" +
            ".#...#.##.\n" +
            "#.#####.##\n" +
            "..#.###...\n" +
            "..#.......\n" +
            "..#.###...";

    private static Map<Integer, List<Edges>> edgesPerTile;
    private static Map<String, Set<TileWithOrientation>> tilesPerEdge;
    private static Map<Integer, TileContent> tileContents;

    public static void solve() {
        edgesPerTile = new HashMap<>();
        tilesPerEdge = new HashMap<>();
        tileContents = new HashMap<>();
        String[] lines = INPUT_20.split("\n");

        Integer currentTile = null;
        for (int i = 0; i < lines.length;) {
            if (lines[i].startsWith("Tile")) {
                currentTile = Integer.parseInt(lines[i].split(" ")[1].substring(0, 4));
                edgesPerTile.put(currentTile, new ArrayList<>());
                i++;
            } else if (lines[i].length() > 0) {
                String e1 = lines[i];
                String e3 = lines[i+9];

                StringBuilder e2sb = new StringBuilder();
                StringBuilder e4sb = new StringBuilder();
                String[] tileContent = new String[10];
                for (int j = 0; j < 10; j++) {
                    e2sb.append(lines[i+j].charAt(9));
                    e4sb.append(lines[i+j].charAt(0));
                    tileContent[j] = lines[i+j];
                }
                tileContents.put(currentTile, new TileContent(tileContent));
                String e2 = e2sb.toString();
                String e4 = e4sb.toString();

                addEdges(currentTile, new Edges(e1, e2, e3, e4), 0);

                // First rotation
                String te1 = reverse(e4);
                String te2 = e1;
                String te3 = reverse(e2);
                String te4 = e3;
                addEdges(currentTile, new Edges(te1, te2, te3, te4), 1);
                e1 = te1;
                e2 = te2;
                e3 = te3;
                e4 = te4;

                // Second rotation
                te1 = reverse(e4);
                te2 = e1;
                te3 = reverse(e2);
                te4 = e3;
                addEdges(currentTile, new Edges(te1, te2, te3, te4), 2);
                e1 = te1;
                e2 = te2;
                e3 = te3;
                e4 = te4;

                // Third rotation
                te1 = reverse(e4);
                te2 = e1;
                te3 = reverse(e2);
                te4 = e3;
                addEdges(currentTile, new Edges(te1, te2, te3, te4), 3);
                e1 = te1;
                e2 = te2;
                e3 = te3;
                e4 = te4;

                // Fourth rotation -> back to original
                te1 = reverse(e4);
                te2 = e1;
                te3 = reverse(e2);
                te4 = e3;
                e1 = te1;
                e2 = te2;
                e3 = te3;
                e4 = te4;

                // horizontal edge flip
                addEdges(currentTile, new Edges(e3, reverse(e2), e1, reverse(e4)), 4);

                // vertical edge flip
                addEdges(currentTile, new Edges(reverse(e1), e4, reverse(e3), e2), 5);
                i+= 10;
            } else {
                i++;
            }
        }

        List<Integer> match2 = new ArrayList<>();
        List<Integer> match3 = new ArrayList<>();
        List<Integer> match4 = new ArrayList<>();
        for(Map.Entry<Integer, List<Edges>> tileEntry: edgesPerTile.entrySet()) {
            int tile = tileEntry.getKey();
            int minMatches = Integer.MAX_VALUE;
            // int edgeNum = 0;

            for (Edges edge : tileEntry.getValue()) {
                int match = tilesPerEdge.get(edge.e1).size() - 1; // one is for the same tile
                match += (tilesPerEdge.get(edge.e2).size() - 1);
                match += (tilesPerEdge.get(edge.e3).size() - 1);
                match += (tilesPerEdge.get(edge.e4).size() - 1);

                if (match > 1 && match <= minMatches) {
                    minMatches = match;
                }
                // edgeNum++;
            }

            if (minMatches == 2) {
                match2.add(tile);
            } else if (minMatches == 3) {
                match3.add(tile);
            } else if (minMatches == 4) {
                match4.add(tile);
            }
        }

        long product = 1;
        for (int v : match2) {
            product = product * v;
        }
        System.out.println(product); //14986175499719

//
//        int topLeft = 0, topRight = 0, bottomLeft = 0, bottomRight = 0;
//        List<Edges> edges = edgesPerTile.get(match2.get(0));
//        Edges foundCombo = null;
//        TileWithOrientation e2Match = null;
//        TileWithOrientation e3Match = null;
//        for (int o = 0; o < edges.size(); o++) {
//            Edges edgeCombo = edges.get(o);
//            Set<TileWithOrientation> matchingTilesWithE1 = tilesPerEdge.get(edgeCombo.e1);
//            Set<TileWithOrientation> matchingTilesWithE2 = tilesPerEdge.get(edgeCombo.e2);
//            Set<TileWithOrientation> matchingTilesWithE3 = tilesPerEdge.get(edgeCombo.e3);
//            Set<TileWithOrientation> matchingTilesWithE4 = tilesPerEdge.get(edgeCombo.e4);
//
//            int finalO = o;
//            if (matchingTilesWithE2.size() > 1 && matchingTilesWithE3.size() > 1
//                && matchingTilesWithE2.stream().anyMatch(t -> t.tile == match2.get(0) && t.orientation == finalO)
//                && matchingTilesWithE3.stream().anyMatch(t -> t.tile == match2.get(0) && t.orientation == finalO)) {
//                foundCombo = edgeCombo;
//                e2Match = matchingTilesWithE2.stream().filter(t -> t.tile != match2.get(0)).findFirst().orElseThrow();
//                e3Match = matchingTilesWithE3.stream().filter(t -> t.tile != match2.get(0)).findFirst().orElseThrow();
//            }
//        }
//
//        System.out.println("LULW");
    }

    private static String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return sb.toString();
    }

    private static void addEdges(int tile, Edges edges, int orientation) {
        edgesPerTile.get(tile).add(edges);
        tilesPerEdge.putIfAbsent(edges.e1, new HashSet<>());
        tilesPerEdge.putIfAbsent(edges.e2, new HashSet<>());
        tilesPerEdge.putIfAbsent(edges.e3, new HashSet<>());
        tilesPerEdge.putIfAbsent(edges.e4, new HashSet<>());

        tilesPerEdge.get(edges.e1).add(new TileWithOrientation(tile, orientation));
        tilesPerEdge.get(edges.e2).add(new TileWithOrientation(tile, orientation));
        tilesPerEdge.get(edges.e3).add(new TileWithOrientation(tile, orientation));
        tilesPerEdge.get(edges.e4).add(new TileWithOrientation(tile, orientation));
    }

    /**
            e1
        e4      e2
            e3
     */
    public static class Edges {
        public final String e1;
        public final String e2;
        public final String e3;
        public final String e4;

        public Edges(String e1, String e2, String e3, String e4) {
            this.e1 = e1;
            this.e2 = e2;
            this.e3 = e3;
            this.e4 = e4;
        }
    }

    public static class TileWithOrientation {
        public final int tile;
        public final int orientation; // 0 - 5 -> actual, one rot, two rot, three rot, horizontal flip, vertical flip
        public static Map<Integer, String> orientationExplanation;

        static {
            orientationExplanation = new HashMap<>();
            orientationExplanation.put(0, "actual");
            orientationExplanation.put(1, "one rotation");
            orientationExplanation.put(2, "two rotation");
            orientationExplanation.put(3, "three rotation");
            orientationExplanation.put(4, "horizontal flip");
            orientationExplanation.put(5, "vertical flip");
        }

        public TileWithOrientation(int tile, int orientation) {
            this.tile = tile;
            this.orientation = orientation;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TileWithOrientation that = (TileWithOrientation) o;
            return tile == that.tile;
        }

        @Override
        public int hashCode() {
            return Objects.hash(tile);
        }

        public String toString() {
            return String.format("Tile #%s, Orientation: %s", tile, orientationExplanation.get(orientation));
        }
    }

    public static class TileContent {
        public final String[] content;

        public TileContent(String[] content) {
            this.content = content;
        }
    }
}
