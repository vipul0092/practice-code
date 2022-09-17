package code.vipul.aoc2020.day20;

import code.vipul.aoc2019.Grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static code.vipul.aoc2020.Inputs2.INPUT_20;

/**
 * Created by vgaur created on 16/09/22
 * https://adventofcode.com/2020/day/20
 */
public class Solve20 {

    /*
            a
         d     b
            c
     */
    // e -> reverse a, f -> reverse b, g -> reverse c, h -> reverse d
    private static final Set<String> allEdgeOrientations =
            Set.of("abcd", "cfah", "hafc", "fegh", "ghef", "edgb", "bgde", "dcba");
    private static final Map<String, TransformRule> transformRules;

    private static final int M_ROWS = 3;
    private static final int M_COLS = 20;
    private static final Character[][] MONSTER;

    static {
        // Transform rules of transforming edges from original a-b-c-d formation
        transformRules = new HashMap<>();
        transformRules.put("abcd", new TransformRule("abcd", List.of())); // No transform needed for abcd
        transformRules.put("cfah", new TransformRule("cfah", List.of(TransformType.FLIP_180_HORIZONTAL)));
        transformRules.put("hafc", new TransformRule("hafc", List.of(TransformType.ROTATE_90_CLOCKWISE)));
        transformRules.put("fegh", new TransformRule("fegh",
                List.of(TransformType.ROTATE_90_CLOCKWISE, TransformType.FLIP_180_HORIZONTAL)));
        transformRules.put("ghef", new TransformRule("ghef",
                List.of(TransformType.ROTATE_90_CLOCKWISE, TransformType.ROTATE_90_CLOCKWISE)));
        transformRules.put("edgb", new TransformRule("edgb",
                List.of(TransformType.ROTATE_90_CLOCKWISE, TransformType.ROTATE_90_CLOCKWISE,
                        TransformType.FLIP_180_HORIZONTAL)));
        transformRules.put("bgde", new TransformRule("bgde",
                List.of(TransformType.ROTATE_90_CLOCKWISE, TransformType.ROTATE_90_CLOCKWISE,
                        TransformType.ROTATE_90_CLOCKWISE)));
        transformRules.put("dcba", new TransformRule("dcba",
                List.of(TransformType.ROTATE_90_CLOCKWISE, TransformType.ROTATE_90_CLOCKWISE,
                        TransformType.ROTATE_90_CLOCKWISE, TransformType.FLIP_180_HORIZONTAL)));

        MONSTER = new Character[3][20];

        Character[] row1 = new Character[20];
        Character[] row2 = new Character[]{'#', null, null, null, null, '#', '#', null, null, null, null,
                '#', '#', null, null, null, null, '#', '#', '#'};
        Character[] row3 = new Character[]{null, '#', null, null, '#', null, null, '#', null, null, '#',
                null, null, '#', null, null, '#', null, null, null };

        MONSTER[0] = row1;
        MONSTER[1] = row2;
        MONSTER[2] = row3;
    }

    private static Map<Integer, Tile> tiles;
    private static final Map<String, Set<Integer>> tilesPerEdge = new HashMap<>();
    private static TreeSet<Integer> match2 = new TreeSet<>();
    private static TreeSet<Integer> match3 = new TreeSet<>();
    private static TreeSet<Integer> match4 = new TreeSet<>();
    private static int hashCount = 0;

    public static void solve() {
        tiles = new HashMap<>();
        String[] lines = INPUT_20.split("\n");

        Integer currentTile = null;


        for (int i = 0; i < lines.length; ) {
            if (lines[i].startsWith("Tile")) {
                currentTile = Integer.parseInt(lines[i].split(" ")[1].substring(0, 4));
                i++;
            } else if (lines[i].length() > 0) {
                String[] tileContent = new String[10];
                for (int j = 0; j < 10; j++) {
                    tileContent[j] = lines[i + j];
                }
                Tile tile = new Tile(currentTile, tileContent);
                tiles.put(currentTile, tile);
                tile.possibleEdges.values().forEach(e -> {
                    tilesPerEdge.putIfAbsent(e, new HashSet<>());
                    tilesPerEdge.get(e).add(tile.tileId);
                });
                i += 10;
            } else {
                i++;
            }
        }

        for (Map.Entry<Integer, Tile> tileEntry : tiles.entrySet()) {
            Tile tile = tileEntry.getValue();
            int minMatches = Integer.MAX_VALUE;
            // int edgeNum = 0;

            for (String orientation : allEdgeOrientations) {
                int match = tilesPerEdge.get(tile.getEdge(orientation.charAt(0))).size() - 1;
                match += (tilesPerEdge.get(tile.getEdge(orientation.charAt(1))).size() - 1);
                match += (tilesPerEdge.get(tile.getEdge(orientation.charAt(2))).size() - 1);
                match += (tilesPerEdge.get(tile.getEdge(orientation.charAt(3))).size() - 1);

                if (match > 1 && match <= minMatches) {
                    minMatches = match;
                }
            }

            if (minMatches == 2) {
                match2.add(tile.tileId);
            } else if (minMatches == 3) {
                match3.add(tile.tileId);
            } else if (minMatches == 4) {
                match4.add(tile.tileId);
            }
        }

        long product = 1;
        for (int v : match2) {
            product = product * v;
        }
        // PART 1 SOLUTION
        System.out.println(product); // 14986175499719

        // --- PART 2 STARTS ---
        int side = (int) Math.sqrt(tiles.size());
        TileWithOrientation[][] arrangedTiles = null;

        Grid.setMaxRowsCols(side, side);

        // Find all the top left corner candidate tiles
        List<TileWithOrientation> candidates = topLeftCandidates();
        TreeSet<Integer> match2Copy = new TreeSet<>(match2);
        TreeSet<Integer> match3Copy = new TreeSet<>(match3);
        TreeSet<Integer> match4Copy = new TreeSet<>(match4);

        // Try setting each tile candidate in top left and evaluate the matrix
        for (TileWithOrientation candidate : candidates) {
            try {
                arrangedTiles = new TileWithOrientation[side][side];
                match2 = new TreeSet<>(match2Copy);
                match3 = new TreeSet<>(match3Copy);
                match4 = new TreeSet<>(match4Copy);

                // Put the candidate at top left and start finding the solution
                arrangedTiles[0][0] = candidate;
                match2.remove(candidate.tileId);

                for (int i = 0; i < side; i++) {
                    for (int j = 0; j < side; j++) {
                        Grid.Pos pos = Grid.Pos.of(i, j);
                        if (arrangedTiles[pos.i()][pos.j()] != null) {
                            continue;
                        }

                        // Try placing a tile at the current position wrt to already set tiles
                        if (pos.isAtLeftBoundary()) {
                            placeTile(pos, pos.moveUp(), arrangedTiles, pos.isCorner() ? TileType.CORNER : TileType.EDGE,
                                    2, 0);
                        } else if (pos.isCorner()) {
                            placeTile(pos, pos.moveLeft(), arrangedTiles, TileType.CORNER, 1, 3);
                        } else if (pos.isAtTopBoundary() || pos.isAtBottomBoundary() || pos.isAtRightBoundary()) {
                            placeTile(pos, pos.moveLeft(), arrangedTiles, TileType.EDGE, 1, 3);
                        } else { // Internal tiles
                            placeTile(pos, pos.moveLeft(), arrangedTiles, TileType.INNER, 1, 3);
                        }
                    }
                }

                // If the loop successfully completes, then we break because we've found a solution
                break;
            } catch (NoMatchFoundException n) {
                System.out.println(String.format("NoMatchException caught for candidate tile: %s, " +
                        "orientation: %s, message: %s", candidate.tileId, candidate.orientation, n.getMessage()));
            }
        }

        // Transform the contents of the tiles according to what we solved above
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                TileWithOrientation t = arrangedTiles[i][j];
                Tile tile = tiles.get(t.tileId);
                tile.transformToOrientation(t.orientation);
                // tile.display();
            }
        }

        int fi = 0, fj = 0;
        int imageSide = 8 * side;
        char[][] finalImage = new char[imageSide][imageSide];
        // Remove borders from tiles
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                TileWithOrientation t = arrangedTiles[i][j];
                Tile tile = tiles.get(t.tileId);
                char[][] content = tile.currentTileContent;

                fi = i*8;
                for (int a = 1; a <= 8; a++, fi++) {
                    fj = j*8;
                    for (int b = 1; b <= 8; b++, fj++) {
                        finalImage[fi][fj] = content[a][b];
                        if (finalImage[fi][fj] == '#') {
                            hashCount++;
                        }
                    }
                }
            }
        }

        // Print the reduced tile image
        Tile.print(finalImage, imageSide);

        // PART 2 SOLUTION IS 2161

        // Find in normal image
        findMonster(finalImage, imageSide);
        char[][] copied = copy(finalImage, imageSide);

        // Flip and find
        findMonster(Tile.flip(copied, imageSide), imageSide);

        // rotate 90
        copied = Tile.rotate90(copied, imageSide);
        // find
        findMonster(copied, imageSide);
        // Flip and find
        findMonster(Tile.flip(copy(copied, imageSide), imageSide), imageSide);

        // rotate 90
        copied = Tile.rotate90(copied, imageSide);
        // find
        findMonster(copied, imageSide);
        // Flip and find
        findMonster(Tile.flip(copy(copied, imageSide), imageSide), imageSide);

        // rotate 90
        copied = Tile.rotate90(copied, imageSide);
        // find
        findMonster(copied, imageSide);
        // Flip and find
        findMonster(Tile.flip(copy(copied, imageSide), imageSide), imageSide);

    }

    private static void findMonster(char[][] image, int size) {
        // System.out.println("Finding monster in: ");
        // Tile.print(image, size);
        int colIterations = size - M_COLS + 1;
        int rowIterations = size - M_ROWS + 1;

        int foundCount = 0;

        for (int i = 0; i < rowIterations; i++) {
            for (int j = 0; j < colIterations; j++) {

                boolean found = true;
                for (int a = 0; a < M_ROWS; a++) {
                    for (int b = 0; b < M_COLS; b++) {
                        if (MONSTER[a][b] != null && image[i+a][j+b] != MONSTER[a][b]) {
                            found = false;
                            break;
                        }
                    }
                    if (!found) {
                        break;
                    }
                }

                if (found) {
                    foundCount++;
                    System.out.println("MONSTER FOUND!");
                }
            }
        }

        if (foundCount > 0) {
            System.out.println("Answer: " + (hashCount - (foundCount * 15)));
            throw new RuntimeException("Monster found, throwing exception to stop the program!");
        }
    }

    private static char[][] copy(char[][] original, int size) {
        char[][] copied = new char[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(original[i], 0, copied[i], 0, size);
        }
        return copied;
    }

    private static void placeTile(Grid.Pos pos, Grid.Pos toLook, TileWithOrientation[][] arrangedTiles,
                                  TileType tileType, int setTileMatchingEdgePos, int toSetTileMatchingEdgePos) {
        TreeSet<Integer> toPickFrom;
        if (tileType == TileType.CORNER) {
            toPickFrom = match2;
        } else if (tileType == TileType.EDGE) {
            toPickFrom = match3;
        } else {
            toPickFrom = match4;
        }
        TileWithOrientation tile = arrangedTiles[toLook.i()][toLook.j()];
        Tile setTile = tiles.get(tile.tileId);
        String edgeToMatch = setTile.getEdge(tile.orientation.charAt(setTileMatchingEdgePos));

        int matchedTile = -1;
        String matchedTileOrientation = null;
        for (String orientation : allEdgeOrientations) {
            for (int potentialMatchingTile : toPickFrom) {
                Tile t = tiles.get(potentialMatchingTile);

                // Check edge of the tile in the current orientation being checked
                String edge = t.getEdge(orientation.charAt(toSetTileMatchingEdgePos));
                if (edge.equals(edgeToMatch)) {
                    matchedTile = potentialMatchingTile;
                    matchedTileOrientation = orientation;
                    break;
                }
            }
            if (matchedTile != -1) {
                break;
            }
        }

        if (matchedTile == -1) {
            throw new NoMatchFoundException(pos);
        }

        toPickFrom.remove(matchedTile);
        tile = new TileWithOrientation(matchedTile, matchedTileOrientation, tileType, pos);
        arrangedTiles[tile.pos.i()][tile.pos.j()] = tile;

        System.out.println(String.format("For pos: %s, matched tile: %s, orientation: %s", pos.toString(),
                matchedTile, matchedTileOrientation));
    }

    private static List<TileWithOrientation> topLeftCandidates() {

        List<TileWithOrientation> candidates = new ArrayList<>();

        int horTile, verTile;
        for (int candidateTile : match2) {
            Tile tile = tiles.get(candidateTile);
            for (String orientation : allEdgeOrientations) {
                String bEdge = tile.getEdge(orientation.charAt(1));
                String cEdge = tile.getEdge(orientation.charAt(2));
                // Both matches must be in the same orientation otherwise we wont have a solution
                horTile = -1;
                verTile = -1;
                for (int match3Tile : match3) {
                    if (match3Tile == horTile || match3Tile == verTile) {
                        continue;
                    }
                    Tile t = tiles.get(match3Tile);

                    for (String o : allEdgeOrientations) {
                        // For side match the edge of the matching tile must be the "d" edge
                        Character sideType = o.charAt(3);
                        String edge = t.getEdge(sideType);
                        if (edge.equals(bEdge)) {
                            horTile = match3Tile;
                            break;
                        }

                        // For bottom match the edge of the matching tile must be the "a" edge
                        Character topType = o.charAt(0);
                        edge = t.getEdge(topType);
                        if (edge.equals(cEdge)) {
                            verTile = match3Tile;
                            break;
                        }
                    }
                }

                if (horTile != -1 && verTile != -1) {
                    candidates.add(new TileWithOrientation(candidateTile, orientation,
                            TileType.CORNER, Grid.Pos.of(0, 0)));
                }
            }
        }
        return candidates;
    }

    public static class Tile {
        private final int tileId;
        public final String[] originalLines;
        private final Map<Character, String> possibleEdges;
        private char[][] currentTileContent;

        public Tile(int tileId, String[] lines) {
            this.tileId = tileId;
            this.originalLines = lines;
            possibleEdges = new HashMap<>();
            currentTileContent = new char[10][10];

            String a = lines[0];
            String c = lines[9];
            StringBuilder bsb = new StringBuilder();
            StringBuilder dsb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                bsb.append(lines[j].charAt(9));
                dsb.append(lines[j].charAt(0));

                for (int k = 0; k < 10; k++) {
                    currentTileContent[j][k] = lines[j].charAt(k);
                }
            }
            String b = bsb.toString();
            String d = dsb.toString();

            possibleEdges.put('a', a);
            possibleEdges.put('b', b);
            possibleEdges.put('c', c);
            possibleEdges.put('d', d);
            possibleEdges.put('e', reverse(a));
            possibleEdges.put('f', reverse(b));
            possibleEdges.put('g', reverse(c));
            possibleEdges.put('h', reverse(d));
        }

        public String getEdge(Character c) {
            return possibleEdges.get(c);
        }

        private static String reverse(String s) {
            StringBuilder sb = new StringBuilder(s);
            sb.reverse();
            return sb.toString();
        }

        public void transformToOrientation(String orientation) {
            // System.out.println("Original Tile: " + tileId);
            // display();
            TransformRule rule = transformRules.get(orientation);

            for (TransformType transform : rule.transforms) {
                if (transform == TransformType.ROTATE_90_CLOCKWISE) {
                    rotate90Clock();
                } else {
                    flipHorizontal();
                }
                // display();
            }
        }

        private void rotate90Clock() {
            currentTileContent = rotate90(currentTileContent, 10);
        }

        public static char[][] rotate90(char[][] current, int size) {
            char[][] afterTransform = new char[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    afterTransform[i][size-1-j] = current[j][i];
                }
            }
            return afterTransform;
        }

        private void flipHorizontal() {
            currentTileContent = flip(currentTileContent, 10);
        }

        public static char[][] flip(char[][] current, int size) {
            char[][] afterTransform = new char[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    afterTransform[size-1-i][j] = current[i][j];
                }
            }
            return afterTransform;
        }

        public void display() {
            print(currentTileContent, 10);
        }

        public static void print(char[][] current, int size) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(current[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static class TileWithOrientation {
        public final int tileId;
        public final String orientation;
        public final TileType type;
        public final Grid.Pos pos;

        public TileWithOrientation(int tileId, String orientation, TileType tileType, Grid.Pos pos) {
            this.tileId = tileId;
            this.orientation = orientation;
            this.type = tileType;
            this.pos = pos;
        }
    }

    public enum TransformType {
        ROTATE_90_CLOCKWISE,
        FLIP_180_HORIZONTAL
    }

    public enum TileType {
        CORNER,
        EDGE,
        INNER
    }

    public static class TransformRule {
        public final String to;
        public final List<TransformType> transforms;

        public TransformRule(String to, List<TransformType> transforms) {
            this.to = to;
            this.transforms = transforms;
        }
    }

    public static class NoMatchFoundException extends RuntimeException {
        public NoMatchFoundException(Grid.Pos pos) {
            super("No matched tile found for pos: " + pos.toString());
        }
    }
}
