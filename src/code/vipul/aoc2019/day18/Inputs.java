package code.vipul.aoc2019.day18;

public final class Inputs {

    public static final String INPUT_PART_1 =
            "#################################################################################\n" +
                    "#...#...............#...........#.......#.#...........#..z......#...K........r..#\n" +
                    "###.#.#############.#.#.#######.#.###.#.#.#.#####.###.###.#######.#.#############\n" +
                    "#...#.#.....#...#...#.#.#.......#...#.#.#.#.....#.#.....C.#.......#.#.....#.....#\n" +
                    "#.###.#.###.#.#.#.###.#.###########.#.###.#####.#.#####.###.#######.#.###.#.###.#\n" +
                    "#...#.#.#.#b#.#...#...#...........#.#...#.....#.#.....#.#...#.....#.....#...#...#\n" +
                    "###.#.#.#.#.#.#####.###########.###.###.#T#####.#####.###.###P###.#########.#.#R#\n" +
                    "#...#.#...#.#f....#.....#...#...#...#.#.#.#...#.Y.#...#.....#.#.#..p#a....#.#.#.#\n" +
                    "#.#.#.#####.#####.#####.#.#.#.###.###.#.#.#.#.###.#.###.#####.#.###.#.###.#.#.#.#\n" +
                    "#.#.#.....#.#.....#...#...#.#.....#...#.#.#.#.#...#.....#.....#...#.#.#.#.#.#.#.#\n" +
                    "#.#######.#.#.#######.#####.#######.###.#.#.#.#.#########.#######.#.#.#G#.###.#.#\n" +
                    "#..q#.......#.......#.....#.#.......#.U.#.#.#...#....n..#v..#.L...#.#.#.....#.#.#\n" +
                    "#.#.#.#######.#####.#.###.#.#.#####.#.#.#.#.###########.###.###.#.#.#.#####.#.#.#\n" +
                    "#.#...#.....#...#...#.#...#.#.#.....#.#.#...#...........#.#...#.#.#.#.....#...#.#\n" +
                    "#.#####.###.#####.#####.#.#.#.#.#####.#.#.#####.#.#####.#.###.#.###.#.###.#####.#\n" +
                    "#.....#.#...#.....#.....#.#...#...#...#.#.#...#.#...#.#.#.#...#.#...#.#.#...#..g#\n" +
                    "###X###.#.###.#####.#############.#.###.#.#.#.#####.#.#.#.#.###.#.###.#.###.#####\n" +
                    "#...#...#.....#...#...........D...#.#.#.#...#.#.....#...#.#.#.....#...A...#.....#\n" +
                    "#.###.#########.#.#.###########.###.#.#.#####.#.#####.###.#.###.#########.#####.#\n" +
                    "#...#.#.....#...#.....#...#...#.#...#...#.....#.#...#.#...#...#l#...#.#j..#...#.#\n" +
                    "#.###.#.#####.#########.#.#.#.#.#.#####.#.#####.###.#.###.###V#.#.#.#.#.###.#J#.#\n" +
                    "#.#...#.....#.#.....#...#...#.#.#...S.#.#...#.....#.#.....#.#.#...#.#.....#.#...#\n" +
                    "#.#.###.###.#.#.###.#########.#.#####.#.###.#####.#.#####.#.#######H#######.#####\n" +
                    "#.#...#...#.#.#...#...#...#...#.....#.#.#...#.....#.....#.#...#...#........h#...#\n" +
                    "#.###I###.#.#.#.###.#.#.#.#.#########.###.###.#######.#.#.#.#.#.#.#############B#\n" +
                    "#...#...#.#.#.#.#...#.#.#.#.#.......#...#.....#.......#.#.#.#...#...#...#.......#\n" +
                    "###.###.#.#M#.#.#.###.###.#.#.#####.###.#######.#######.#.#.#######.#.#.###.###.#\n" +
                    "#.#.#.#.#.#...#.#...#.....#...#.#...#...#...#.....#...#.#...#.....#...#.....#.#.#\n" +
                    "#.#.#.#.#.#####.###.###########.#.###.###.#.#.###.###.#.#####.#.#############.#.#\n" +
                    "#.#...#.#.....#.#.#...#...........#.#...#.#.#...#.#...#.#...#.#...............#.#\n" +
                    "#.###.#.#####.#.#.###.#.###########.#.#.#.#.###.#.#.###.#.###.#########.#####.#.#\n" +
                    "#...#.#.#.#...#.#...#.#.#.....#...#...#.#.#...#.#.#.....#.#...#.....#.#...#...#.#\n" +
                    "###.#.#.#.#.###.#.###.#.#.###.###.#.###.#####.#.#.#.#####.#.#####.#.#.###.#####.#\n" +
                    "#...#.#.#...#...#...#...#.#.#...#.....#.#.....#.#.#.......#.......#.#...#.......#\n" +
                    "#.#O#.#.#####.#####.#####.#.###.#.#####.#.###.#.#.#################.###.#########\n" +
                    "#.#.#.#...#...#...........#.....#.#...#.#.#...#.#...#.......#.......#.....#.....#\n" +
                    "#.#.#.###.#.###.###########.#######.#.#.#.###.#.###.#.###.###.#######.###.###.#.#\n" +
                    "#.#.#.#.#...#...#.#.......#...#.....#.#.#...#.#...#.#.#.#.#...#.........#.#...#.#\n" +
                    "#.###.#.#######.#.#.#.#######.#.#####.#.#.#.#####.#.#.#.#.#.###########.#.#.###.#\n" +
                    "#.........E.....#...#...........#.........#.......#....e#...............#...#...#\n" +
                    "#######################################.@.#######################################\n" +
                    "#.....#...#.........#...............#.........#...#.............#...#...#...#...#\n" +
                    "#.###.#.#.#######.#.###.###########.###.#.#####.#.#.#.#########.#.#.#.#.#.#.###.#\n" +
                    "#.#.#...#.....#...#...#...#.....#.#.#...#.......#...#.....#.#...#.#...#.#.#.....#\n" +
                    "#.#.#########.#.#####.#.#.#.###.#.#.#.###.###############.#.#.###.#####.#.#####.#\n" +
                    "#.#.#...#...#...#.#...#.#.#...#...#.#...#.#.......#...#...#.#.....#.#...#.#x#...#\n" +
                    "#.#.#.#.#.#.#####.#.#####.#.#.###.#.###.#.#####.#.#.#.#.###.#######.#.###.#.#.###\n" +
                    "#.#...#...#.#...#...#.....#.#...#.#.....#...#...#.#.#.#...#.........#....o#...#.#\n" +
                    "#.#####.###.#.#.#.###.#####.###.#######.###.#.###.#.#####.###.#############.###.#\n" +
                    "#.#...#.#.#.#.#...#.#.....#.#...#.....#s#...#.#.#.#.....#...#...........#.#.....#\n" +
                    "#.#.#.#.#.#.#.#####.#.###.#.#.#.#.###.#.#.###.#.#.###.#.###.#########.#.#.#####.#\n" +
                    "#...#.#.#.#...#...#...#...#.#.#.#...#.#.#.....#.......#.#.#.#.........#.......#.#\n" +
                    "#.###.#.#.#####.#.#####.#####.#.###.#.#.###.###########.#.#.#.#########.#######.#\n" +
                    "#.#.#.#.#.......#.......#.....#.#...#...#...#...#.....#...#.#.#..m......#.....#.#\n" +
                    "#.#.#.#.###.#############.#######.#######.###.#.#.###.#.###.#.#.#####.###.###.#.#\n" +
                    "#...#.#...#.....#.....#.....#...#.#.....#...#.#.#...#...#...#.#.#...#...#...#...#\n" +
                    "###.#.###.#.###.#####.#.###.#.#.#.#.###.#.###.#.#########.###.###.#.#######.#####\n" +
                    "#...#.#.#.#...#.....#.#.#.#...#...#...#.#.#...#.......#...#.......#.......#.Q...#\n" +
                    "#.###.#.#.#.#######.#.#.#.#############.#.#.#########.#.#########.#######.#####.#\n" +
                    "#...#.#...#.#.......#.......#...........#.#.#.......#...#.......#.......#...#...#\n" +
                    "###.#.#.###.#.#####.#######.#.#.#######.#.#.#.#####.#####.#.###.#######.###.#.###\n" +
                    "#...#.#.#.#.#.#.....#.#...#.#.#.......#.#.#.#.....#.#...#.#.#.#.#.....#...#...#.#\n" +
                    "#####.#.#.#.#.###.###.#.#.#.#.#######.###.#.#.#####.#.#.#.###.#.#.#.#.###.#####.#\n" +
                    "#...W.#.#...#...#.#...#.#.#.#.......#...#u#...#.....#.#.#.....#.#.#.#.#.#.#.....#\n" +
                    "#.#####.###.###.###.#####.#.#######.###.#######.###.#.#.#######.###.#.#.#.#.#####\n" +
                    "#.#...#...#.#.#.#...#.....#.#.........#.#...#...#...#.#...#...#.#...#...#.#w....#\n" +
                    "#.#.#.###.#.#.#.#.###.#.###.#.#########.#.#.#.#.#####.###.#.#.#.#.#######.###.#.#\n" +
                    "#.#.#.#.#.#...#.#.#...#.#...#...#.#.....#.#...#.#...#.#.#...#...#...#...#...#.#.#\n" +
                    "#.#.#.#.#.#####.#.#.#####.#####.#.#.###.#.#######.#.#.#.#########.#.#.#.###.###.#\n" +
                    "#.#.#.#.........#.#.....#.#...#...#.#...#.#.....#.#.#.....#.....#.#...#...#...#.#\n" +
                    "#.#.#.###.#######.#####.#.#.#.###.#.###.#.#.###.#.#.#####.#####.#.#######.###F#.#\n" +
                    "#t..#...#.#.......#.....#...#.#...#...#.#.#.#.#.#.#.....#.#.....#...#...#.#...#.#\n" +
                    "#.#####.###.#######.#########.#######.#.#.#.#.#.#.###.#.#.#.#######.#N#.#.#.###.#\n" +
                    "#.....#...#.#...............#.#.......#.#.#.#.#...#.#.#.#.#.......#...#.#...#...#\n" +
                    "#########.#.#####.#####.###.#.#.#######.#.#.#.#####.#.###.#.#####.#####.#####.#.#\n" +
                    "#........y#.....#.#...#.#...#.#.#.......#...#.......#.....#.#.....#...#....i..#.#\n" +
                    "#.#############.#.#.#.#.###.#.#.#.###########.###.#########.#.#####.#.#########.#\n" +
                    "#...#.......#...#.#.#.#...#.#.#.#.#.....#..k#...#.....#c..#.#.....#.#...#.....#.#\n" +
                    "#.#.#.#####.#.###.#.#.###.###.#.#.###.#.#.#####.#####.#.#.#.#####.#.###.#.###.#.#\n" +
                    "#.#.......#...#.....#...#.......#..d..#.#.......Z...#...#.......#.....#.....#...#\n" +
                    "#################################################################################";

    public static final String INPUT_PART_2 =
            "#################################################################################\n" +
                    "#...#...............#...........#.......#.#...........#..z......#...K........r..#\n" +
                    "###.#.#############.#.#.#######.#.###.#.#.#.#####.###.###.#######.#.#############\n" +
                    "#...#.#.....#...#...#.#.#.......#...#.#.#.#.....#.#.....C.#.......#.#.....#.....#\n" +
                    "#.###.#.###.#.#.#.###.#.###########.#.###.#####.#.#####.###.#######.#.###.#.###.#\n" +
                    "#...#.#.#.#b#.#...#...#...........#.#...#.....#.#.....#.#...#.....#.....#...#...#\n" +
                    "###.#.#.#.#.#.#####.###########.###.###.#T#####.#####.###.###P###.#########.#.#R#\n" +
                    "#...#.#...#.#f....#.....#...#...#...#.#.#.#...#.Y.#...#.....#.#.#..p#a....#.#.#.#\n" +
                    "#.#.#.#####.#####.#####.#.#.#.###.###.#.#.#.#.###.#.###.#####.#.###.#.###.#.#.#.#\n" +
                    "#.#.#.....#.#.....#...#...#.#.....#...#.#.#.#.#...#.....#.....#...#.#.#.#.#.#.#.#\n" +
                    "#.#######.#.#.#######.#####.#######.###.#.#.#.#.#########.#######.#.#.#G#.###.#.#\n" +
                    "#..q#.......#.......#.....#.#.......#.U.#.#.#...#....n..#v..#.L...#.#.#.....#.#.#\n" +
                    "#.#.#.#######.#####.#.###.#.#.#####.#.#.#.#.###########.###.###.#.#.#.#####.#.#.#\n" +
                    "#.#...#.....#...#...#.#...#.#.#.....#.#.#...#...........#.#...#.#.#.#.....#...#.#\n" +
                    "#.#####.###.#####.#####.#.#.#.#.#####.#.#.#####.#.#####.#.###.#.###.#.###.#####.#\n" +
                    "#.....#.#...#.....#.....#.#...#...#...#.#.#...#.#...#.#.#.#...#.#...#.#.#...#..g#\n" +
                    "###X###.#.###.#####.#############.#.###.#.#.#.#####.#.#.#.#.###.#.###.#.###.#####\n" +
                    "#...#...#.....#...#...........D...#.#.#.#...#.#.....#...#.#.#.....#...A...#.....#\n" +
                    "#.###.#########.#.#.###########.###.#.#.#####.#.#####.###.#.###.#########.#####.#\n" +
                    "#...#.#.....#...#.....#...#...#.#...#...#.....#.#...#.#...#...#l#...#.#j..#...#.#\n" +
                    "#.###.#.#####.#########.#.#.#.#.#.#####.#.#####.###.#.###.###V#.#.#.#.#.###.#J#.#\n" +
                    "#.#...#.....#.#.....#...#...#.#.#...S.#.#...#.....#.#.....#.#.#...#.#.....#.#...#\n" +
                    "#.#.###.###.#.#.###.#########.#.#####.#.###.#####.#.#####.#.#######H#######.#####\n" +
                    "#.#...#...#.#.#...#...#...#...#.....#.#.#...#.....#.....#.#...#...#........h#...#\n" +
                    "#.###I###.#.#.#.###.#.#.#.#.#########.###.###.#######.#.#.#.#.#.#.#############B#\n" +
                    "#...#...#.#.#.#.#...#.#.#.#.#.......#...#.....#.......#.#.#.#...#...#...#.......#\n" +
                    "###.###.#.#M#.#.#.###.###.#.#.#####.###.#######.#######.#.#.#######.#.#.###.###.#\n" +
                    "#.#.#.#.#.#...#.#...#.....#...#.#...#...#...#.....#...#.#...#.....#...#.....#.#.#\n" +
                    "#.#.#.#.#.#####.###.###########.#.###.###.#.#.###.###.#.#####.#.#############.#.#\n" +
                    "#.#...#.#.....#.#.#...#...........#.#...#.#.#...#.#...#.#...#.#...............#.#\n" +
                    "#.###.#.#####.#.#.###.#.###########.#.#.#.#.###.#.#.###.#.###.#########.#####.#.#\n" +
                    "#...#.#.#.#...#.#...#.#.#.....#...#...#.#.#...#.#.#.....#.#...#.....#.#...#...#.#\n" +
                    "###.#.#.#.#.###.#.###.#.#.###.###.#.###.#####.#.#.#.#####.#.#####.#.#.###.#####.#\n" +
                    "#...#.#.#...#...#...#...#.#.#...#.....#.#.....#.#.#.......#.......#.#...#.......#\n" +
                    "#.#O#.#.#####.#####.#####.#.###.#.#####.#.###.#.#.#################.###.#########\n" +
                    "#.#.#.#...#...#...........#.....#.#...#.#.#...#.#...#.......#.......#.....#.....#\n" +
                    "#.#.#.###.#.###.###########.#######.#.#.#.###.#.###.#.###.###.#######.###.###.#.#\n" +
                    "#.#.#.#.#...#...#.#.......#...#.....#.#.#...#.#...#.#.#.#.#...#.........#.#...#.#\n" +
                    "#.###.#.#######.#.#.#.#######.#.#####.#.#.#.#####.#.#.#.#.#.###########.#.#.###.#\n" +
                    "#.........E.....#...#...........#......@#@#.......#....e#...............#...#...#\n" +
                    "#################################################################################\n" +
                    "#.....#...#.........#...............#..@#@....#...#.............#...#...#...#...#\n" +
                    "#.###.#.#.#######.#.###.###########.###.#.#####.#.#.#.#########.#.#.#.#.#.#.###.#\n" +
                    "#.#.#...#.....#...#...#...#.....#.#.#...#.......#...#.....#.#...#.#...#.#.#.....#\n" +
                    "#.#.#########.#.#####.#.#.#.###.#.#.#.###.###############.#.#.###.#####.#.#####.#\n" +
                    "#.#.#...#...#...#.#...#.#.#...#...#.#...#.#.......#...#...#.#.....#.#...#.#x#...#\n" +
                    "#.#.#.#.#.#.#####.#.#####.#.#.###.#.###.#.#####.#.#.#.#.###.#######.#.###.#.#.###\n" +
                    "#.#...#...#.#...#...#.....#.#...#.#.....#...#...#.#.#.#...#.........#....o#...#.#\n" +
                    "#.#####.###.#.#.#.###.#####.###.#######.###.#.###.#.#####.###.#############.###.#\n" +
                    "#.#...#.#.#.#.#...#.#.....#.#...#.....#s#...#.#.#.#.....#...#...........#.#.....#\n" +
                    "#.#.#.#.#.#.#.#####.#.###.#.#.#.#.###.#.#.###.#.#.###.#.###.#########.#.#.#####.#\n" +
                    "#...#.#.#.#...#...#...#...#.#.#.#...#.#.#.....#.......#.#.#.#.........#.......#.#\n" +
                    "#.###.#.#.#####.#.#####.#####.#.###.#.#.###.###########.#.#.#.#########.#######.#\n" +
                    "#.#.#.#.#.......#.......#.....#.#...#...#...#...#.....#...#.#.#..m......#.....#.#\n" +
                    "#.#.#.#.###.#############.#######.#######.###.#.#.###.#.###.#.#.#####.###.###.#.#\n" +
                    "#...#.#...#.....#.....#.....#...#.#.....#...#.#.#...#...#...#.#.#...#...#...#...#\n" +
                    "###.#.###.#.###.#####.#.###.#.#.#.#.###.#.###.#.#########.###.###.#.#######.#####\n" +
                    "#...#.#.#.#...#.....#.#.#.#...#...#...#.#.#...#.......#...#.......#.......#.Q...#\n" +
                    "#.###.#.#.#.#######.#.#.#.#############.#.#.#########.#.#########.#######.#####.#\n" +
                    "#...#.#...#.#.......#.......#...........#.#.#.......#...#.......#.......#...#...#\n" +
                    "###.#.#.###.#.#####.#######.#.#.#######.#.#.#.#####.#####.#.###.#######.###.#.###\n" +
                    "#...#.#.#.#.#.#.....#.#...#.#.#.......#.#.#.#.....#.#...#.#.#.#.#.....#...#...#.#\n" +
                    "#####.#.#.#.#.###.###.#.#.#.#.#######.###.#.#.#####.#.#.#.###.#.#.#.#.###.#####.#\n" +
                    "#...W.#.#...#...#.#...#.#.#.#.......#...#u#...#.....#.#.#.....#.#.#.#.#.#.#.....#\n" +
                    "#.#####.###.###.###.#####.#.#######.###.#######.###.#.#.#######.###.#.#.#.#.#####\n" +
                    "#.#...#...#.#.#.#...#.....#.#.........#.#...#...#...#.#...#...#.#...#...#.#w....#\n" +
                    "#.#.#.###.#.#.#.#.###.#.###.#.#########.#.#.#.#.#####.###.#.#.#.#.#######.###.#.#\n" +
                    "#.#.#.#.#.#...#.#.#...#.#...#...#.#.....#.#...#.#...#.#.#...#...#...#...#...#.#.#\n" +
                    "#.#.#.#.#.#####.#.#.#####.#####.#.#.###.#.#######.#.#.#.#########.#.#.#.###.###.#\n" +
                    "#.#.#.#.........#.#.....#.#...#...#.#...#.#.....#.#.#.....#.....#.#...#...#...#.#\n" +
                    "#.#.#.###.#######.#####.#.#.#.###.#.###.#.#.###.#.#.#####.#####.#.#######.###F#.#\n" +
                    "#t..#...#.#.......#.....#...#.#...#...#.#.#.#.#.#.#.....#.#.....#...#...#.#...#.#\n" +
                    "#.#####.###.#######.#########.#######.#.#.#.#.#.#.###.#.#.#.#######.#N#.#.#.###.#\n" +
                    "#.....#...#.#...............#.#.......#.#.#.#.#...#.#.#.#.#.......#...#.#...#...#\n" +
                    "#########.#.#####.#####.###.#.#.#######.#.#.#.#####.#.###.#.#####.#####.#####.#.#\n" +
                    "#........y#.....#.#...#.#...#.#.#.......#...#.......#.....#.#.....#...#....i..#.#\n" +
                    "#.#############.#.#.#.#.###.#.#.#.###########.###.#########.#.#####.#.#########.#\n" +
                    "#...#.......#...#.#.#.#...#.#.#.#.#.....#..k#...#.....#c..#.#.....#.#...#.....#.#\n" +
                    "#.#.#.#####.#.###.#.#.###.###.#.#.###.#.#.#####.#####.#.#.#.#####.#.###.#.###.#.#\n" +
                    "#.#.......#...#.....#...#.......#..d..#.#.......Z...#...#.......#.....#.....#...#\n" +
                    "#################################################################################";
}
