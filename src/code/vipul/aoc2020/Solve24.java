package code.vipul.aoc2020;

import code.vipul.aoc2019.Grid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs2.INPUT_24;

/**
 * https://adventofcode.com/2020/day/24
 */
public class Solve24 {

    private static final String INPUT = "sesenwnenenewseeswwswswwnenewsewsw\n" +
            "neeenesenwnwwswnenewnwwsewnenwseswesw\n" +
            "seswneswswsenwwnwse\n" +
            "nwnwneseeswswnenewneswwnewseswneseene\n" +
            "swweswneswnenwsewnwneneseenw\n" +
            "eesenwseswswnenwswnwnwsewwnwsene\n" +
            "sewnenenenesenwsewnenwwwse\n" +
            "wenwwweseeeweswwwnwwe\n" +
            "wsweesenenewnwwnwsenewsenwwsesesenwne\n" +
            "neeswseenwwswnwswswnw\n" +
            "nenwswwsewswnenenewsenwsenwnesesenew\n" +
            "enewnwewneswsewnwswenweswnenwsenwsw\n" +
            "sweneswneswneneenwnewenewwneswswnese\n" +
            "swwesenesewenwneswnwwneseswwne\n" +
            "enesenwswwswneneswsenwnewswseenwsese\n" +
            "wnwnesenesenenwwnenwsewesewsesesew\n" +
            "nenewswnwewswnenesenwnesewesw\n" +
            "eneswnwswnwsenenwnwnwwseeswneewsenese\n" +
            "neswnwewnwnwseenwseesewsenwsweewe\n" +
            "wseweeenwnesenwwwswnew";

    private static final String E = "e";
    private static final String NE = "ne";
    private static final String NW = "nw";
    private static final String W = "w";
    private static final String SW = "sw";
    private static final String SE = "se";

    private static final List<String> DIRECTIONS = List.of(E, NE, NW, W, SW, SE);

    // 0 -> white, 1 -> black
    private static Map<Grid.Pos, Integer> tileStates;

    public static void solve() {
        tileStates = new LinkedHashMap<>();
        Grid.Pos refTilePos = Grid.Pos.of(0, 0);
        tileStates.put(refTilePos, 0);

        flipTiles(INPUT_24);

        long answer = tileStates.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .count();

        System.out.println("Answer: " + answer); //346
    }

    public static void solvePart2() {
        tileStates = new LinkedHashMap<>();
        Grid.Pos refTilePos = Grid.Pos.of(0, 0);
        tileStates.put(refTilePos, 0);

        int days = 100;
        flipTiles(INPUT_24);
        long answer;
        while (days-- > 0) {
            dayEndFlip();
//            answer = tileStates.entrySet().stream()
//                    .filter(e -> e.getValue() == 1)
//                    .count();
        }

        answer = tileStates.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .count();

        System.out.println("Answer: " + answer); //3802
    }

    private static void flipTiles(String in) {
        String[] inputs = in.split("\n");

        Grid.Pos refTilePos = Grid.Pos.of(0, 0);

        for (String input : inputs) {
            Grid.Pos currentPos = refTilePos;
            for (int i = 0; i < input.length(); ) {
                String direction;
                if (input.charAt(i) == 'n') {
                    if (input.charAt(i + 1) == 'e') {
                        direction = NE;
                    } else {
                        direction = NW;
                    }
                    i += 2;
                } else if (input.charAt(i) == 's') {
                    if (input.charAt(i + 1) == 'e') {
                        direction = SE;
                    } else {
                        direction = SW;
                    }
                    i += 2;
                } else if (input.charAt(i) == 'w') {
                    direction = W;
                    i++;
                } else {
                    direction = E;
                    i++;
                }
                currentPos = move(direction, currentPos);
            }

            if (tileStates.containsKey(currentPos)) {
                // If we are coming to the tile again, flip it
                tileStates.put(currentPos, tileStates.get(currentPos) == 0 ? 1 : 0);
            } else {
                // By default each tile is white, coming onto it for the first time flips it to black
                tileStates.put(currentPos, 1);
            }
        }
    }

    private static void dayEndFlip() {
        Map<Grid.Pos, Integer> updatedTiles = new LinkedHashMap<>();
        Map<Grid.Pos, Integer> newTiles = new LinkedHashMap<>();

        for (Map.Entry<Grid.Pos, Integer> tileState : tileStates.entrySet()) {
            Grid.Pos tilePos = tileState.getKey();
            int tileValue = tileState.getValue();
            // Get all neighbours
            List<Grid.Pos> neighbors = DIRECTIONS.stream()
                    .map(d -> move(d, tilePos))
                    .collect(Collectors.toList());
            // add new tiles to the tiles map
            neighbors.forEach(n -> {
                if (!tileStates.containsKey(n)) {
                    newTiles.put(n, 0);
                }
            });
            // get the neighboring black tile count
            long blackTilesCount = neighbors.stream()
                    .map(p -> tileStates.getOrDefault(p, 0))
                    .filter(v -> v == 1).count();

            if (tileValue == 0 && blackTilesCount == 2) { // tile is white and has exact 2 black tiles
                updatedTiles.put(tilePos, 1);
            } else if (tileValue == 1 && (blackTilesCount == 0 || blackTilesCount > 2)) {
                updatedTiles.put(tilePos, 0);
            }
        }
        for (Map.Entry<Grid.Pos, Integer> tileState : newTiles.entrySet()) {
            Grid.Pos tilePos = tileState.getKey();
            int tileValue = tileState.getValue();

            // Get all neighbours
            List<Grid.Pos> neighbors = DIRECTIONS.stream()
                    .map(d -> move(d, tilePos))
                    .collect(Collectors.toList());

            // get the neighboring black tile count
            long blackTilesCount = neighbors.stream()
                    .map(p -> tileStates.getOrDefault(p, 0))
                    .filter(v -> v == 1).count();

            if (tileValue == 0 && blackTilesCount == 2) {
                updatedTiles.put(tilePos, 1);
            } else {
                updatedTiles.put(tilePos, 0);
            }
        }
        tileStates.putAll(updatedTiles);
    }

    private static Grid.Pos move(String direction, Grid.Pos original) {
        switch (direction) {
            case E:
                return Grid.Pos.of(original.i() + 6, original.j());
            case NE:
                return Grid.Pos.of(original.i() + 3, original.j() + 4);
            case NW:
                return Grid.Pos.of(original.i() - 3, original.j() + 4);
            case W:
                return Grid.Pos.of(original.i() - 6, original.j());
            case SE:
                return Grid.Pos.of(original.i() + 3, original.j() - 4);
            case SW:
                return Grid.Pos.of(original.i() - 3, original.j() - 4);
            default:
                throw new RuntimeException("Invalid direction: " + direction);
        }
    }
}
