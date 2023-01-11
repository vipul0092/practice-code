package code.vipul.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 09/01/23
 * https://adventofcode.com/2021/day/22
 */
public class Solve22 {

    private static final String INPUT =
            "on x=-5..47,y=-31..22,z=-19..33\n" +
                    "on x=-44..5,y=-27..21,z=-14..35\n" +
                    "on x=-49..-1,y=-11..42,z=-10..38\n" +
                    "on x=-20..34,y=-40..6,z=-44..1\n" +
                    "off x=26..39,y=40..50,z=-2..11\n" +
                    "on x=-41..5,y=-41..6,z=-36..8\n" +
                    "off x=-43..-33,y=-45..-28,z=7..25\n" +
                    "on x=-33..15,y=-32..19,z=-34..11\n" +
                    "off x=35..47,y=-46..-34,z=-11..5\n" +
                    "on x=-14..36,y=-6..44,z=-16..29\n" +
                    "on x=-57795..-6158,y=29564..72030,z=20435..90618\n" +
                    "on x=36731..105352,y=-21140..28532,z=16094..90401\n" +
                    "on x=30999..107136,y=-53464..15513,z=8553..71215\n" +
                    "on x=13528..83982,y=-99403..-27377,z=-24141..23996\n" +
                    "on x=-72682..-12347,y=18159..111354,z=7391..80950\n" +
                    "on x=-1060..80757,y=-65301..-20884,z=-103788..-16709\n" +
                    "on x=-83015..-9461,y=-72160..-8347,z=-81239..-26856\n" +
                    "on x=-52752..22273,y=-49450..9096,z=54442..119054\n" +
                    "on x=-29982..40483,y=-108474..-28371,z=-24328..38471\n" +
                    "on x=-4958..62750,y=40422..118853,z=-7672..65583\n" +
                    "on x=55694..108686,y=-43367..46958,z=-26781..48729\n" +
                    "on x=-98497..-18186,y=-63569..3412,z=1232..88485\n" +
                    "on x=-726..56291,y=-62629..13224,z=18033..85226\n" +
                    "on x=-110886..-34664,y=-81338..-8658,z=8914..63723\n" +
                    "on x=-55829..24974,y=-16897..54165,z=-121762..-28058\n" +
                    "on x=-65152..-11147,y=22489..91432,z=-58782..1780\n" +
                    "on x=-120100..-32970,y=-46592..27473,z=-11695..61039\n" +
                    "on x=-18631..37533,y=-124565..-50804,z=-35667..28308\n" +
                    "on x=-57817..18248,y=49321..117703,z=5745..55881\n" +
                    "on x=14781..98692,y=-1341..70827,z=15753..70151\n" +
                    "on x=-34419..55919,y=-19626..40991,z=39015..114138\n" +
                    "on x=-60785..11593,y=-56135..2999,z=-95368..-26915\n" +
                    "on x=-32178..58085,y=17647..101866,z=-91405..-8878\n" +
                    "on x=-53655..12091,y=50097..105568,z=-75335..-4862\n" +
                    "on x=-111166..-40997,y=-71714..2688,z=5609..50954\n" +
                    "on x=-16602..70118,y=-98693..-44401,z=5197..76897\n" +
                    "on x=16383..101554,y=4615..83635,z=-44907..18747\n" +
                    "off x=-95822..-15171,y=-19987..48940,z=10804..104439\n" +
                    "on x=-89813..-14614,y=16069..88491,z=-3297..45228\n" +
                    "on x=41075..99376,y=-20427..49978,z=-52012..13762\n" +
                    "on x=-21330..50085,y=-17944..62733,z=-112280..-30197\n" +
                    "on x=-16478..35915,y=36008..118594,z=-7885..47086\n" +
                    "off x=-98156..-27851,y=-49952..43171,z=-99005..-8456\n" +
                    "off x=2032..69770,y=-71013..4824,z=7471..94418\n" +
                    "on x=43670..120875,y=-42068..12382,z=-24787..38892\n" +
                    "off x=37514..111226,y=-45862..25743,z=-16714..54663\n" +
                    "off x=25699..97951,y=-30668..59918,z=-15349..69697\n" +
                    "off x=-44271..17935,y=-9516..60759,z=49131..112598\n" +
                    "on x=-61695..-5813,y=40978..94975,z=8655..80240\n" +
                    "off x=-101086..-9439,y=-7088..67543,z=33935..83858\n" +
                    "off x=18020..114017,y=-48931..32606,z=21474..89843\n" +
                    "off x=-77139..10506,y=-89994..-18797,z=-80..59318\n" +
                    "off x=8476..79288,y=-75520..11602,z=-96624..-24783\n" +
                    "on x=-47488..-1262,y=24338..100707,z=16292..72967\n" +
                    "off x=-84341..13987,y=2429..92914,z=-90671..-1318\n" +
                    "off x=-37810..49457,y=-71013..-7894,z=-105357..-13188\n" +
                    "off x=-27365..46395,y=31009..98017,z=15428..76570\n" +
                    "off x=-70369..-16548,y=22648..78696,z=-1892..86821\n" +
                    "on x=-53470..21291,y=-120233..-33476,z=-44150..38147\n" +
                    "off x=-93533..-4276,y=-16170..68771,z=-104985..-24507";

    public static void solve() {
        System.out.println("Part 1: " + getOnCubes(Inputs.INPUT_22, true));
        System.out.println("Part 2: " + getOnCubes(Inputs.INPUT_22, false));
    }

    // Coordinate compression approach inspired from https://www.reddit.com/r/adventofcode/comments/rlxhmg/2021_day_22_solutions/hpmyuzj/
    private static long getOnCubes(String input, boolean lessOnly) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        List<Cube> cubes = new ArrayList<>();
        TreeSet<Integer> xcoords = new TreeSet<>();
        TreeSet<Integer> ycoords = new TreeSet<>();
        TreeSet<Integer> zcoords = new TreeSet<>();
        for (String in : inputs) {
            Cube cube = Cube.from(in);
            if (lessOnly && (cube.x1 > 50 || cube.x1 < -50)) {
                break;
            }
            cubes.add(cube);
            xcoords.add(cube.x1);
            xcoords.add(cube.x2 + 1);
            ycoords.add(cube.y1);
            ycoords.add(cube.y2 + 1);
            zcoords.add(cube.z1);
            zcoords.add(cube.z2 + 1);
        }

        CompressedCoordinates xcom = new CompressedCoordinates(xcoords);
        CompressedCoordinates ycom = new CompressedCoordinates(ycoords);
        CompressedCoordinates zcom = new CompressedCoordinates(zcoords);
        boolean[][][] onoff = new boolean[xcoords.size()][ycoords.size()][zcoords.size()];

        for (var cube : cubes) {
            int x1 = xcom.getIndex(cube.x1);
            int x2 = xcom.getIndex(cube.x2 + 1) - 1;
            int y1 = ycom.getIndex(cube.y1);
            int y2 = ycom.getIndex(cube.y2 + 1) - 1;
            int z1 = zcom.getIndex(cube.z1);
            int z2 = zcom.getIndex(cube.z2 + 1) - 1;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    for (int z = z1; z <= z2; z++) {
                        onoff[x][y][z] = cube.on;
                    }
                }
            }
        }

        long volume = 0;
        for (int x = 0; x < xcoords.size() - 1; x++) {
            for (int y = 0; y < ycoords.size() - 1; y++) {
                for (int z = 0; z < zcoords.size() - 1; z++) {
                    if (onoff[x][y][z]) {
                        long xx = xcom.getCoord(x + 1) - xcom.getCoord(x);
                        long yy = ycom.getCoord(y + 1) - ycom.getCoord(y);
                        long zz = zcom.getCoord(z + 1) - zcom.getCoord(z);

                        volume += (xx * yy * zz);
                    }
                }
            }
        }
        return volume;
    }

    private static final class CompressedCoordinates {
        private final List<Integer> coords;
        private final Map<Integer, Integer> coord_to_index;

        CompressedCoordinates(Set<Integer> coordset) {
            int index = 0;
            this.coords = new ArrayList<>();
            coord_to_index = new LinkedHashMap<>();
            for (var coord : coordset) {
                coord_to_index.put(coord, index++);
                coords.add(coord);
            }
        }

        public int getIndex(int coord) {
            return coord_to_index.get(coord);
        }

        public int getCoord(int index) {
            return coords.get(index);
        }
    }

    private static final class Cube {
        private final int x1;
        private final int x2;
        private final int y1;
        private final int y2;
        private final int z1;
        private final int z2;
        private final boolean on;

        Cube(int x1, int x2, int y1, int y2, int z1, int z2, boolean on) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.z1 = z1;
            this.z2 = z2;
            this.on = on;
        }

        public static Cube from(String in) {
            boolean on = in.startsWith("on");
            String[] splits = in.split(",");
            String[] xs = splits[0].split("x=")[1].split("\\.\\.");
            int x1 = Integer.parseInt(xs[0]);
            int x2 = Integer.parseInt(xs[1]);

            xs = splits[1].split("y=")[1].split("\\.\\.");
            int y1 = Integer.parseInt(xs[0]);
            int y2 = Integer.parseInt(xs[1]);

            xs = splits[2].split("z=")[1].split("\\.\\.");
            int z1 = Integer.parseInt(xs[0]);
            int z2 = Integer.parseInt(xs[1]);

            return new Cube(x1, x2, y1, y2, z1, z2, on);
        }
    }
}
