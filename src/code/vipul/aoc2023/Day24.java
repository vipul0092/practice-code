package code.vipul.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2023.inputs.Inputs.DAY_24;

/**
 * Created by vgaur created on 24/12/23
 */
public class Day24 {

    private static String INPUT = """
            19, 13, 30 @ -2,  1, -2
            18, 19, 22 @ -1, -1, -2
            20, 25, 34 @ -2, -2, -4
            12, 31, 28 @ -1, -2, -1
            20, 19, 15 @  1, -5, -3
            """;

//    private static final long LEAST = 7;
//    private static final long MOST = 27;

    private static final long LEAST = 200000000000000L;
    private static final long MOST = 400000000000000L;

    record Pos(long x, long y, long z){}
    record Vel(long vx, long vy, long vz){}

    record Stone(Pos pos, Vel vel, Line line){}

    // y = mx + c => mx - y + c = 0 (of the form ax + by + c = 0 )
    record Line(double a, double b, double c){}

    public static void solve() {
        INPUT = DAY_24;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        var stones = new ArrayList<Stone>();
        for (String line : lines) {
            var parts = line.split(" @ ");
            var str = parts[0].split(", ");
            Pos pos = new Pos(Long.parseLong(str[0].trim()), Long.parseLong(str[1].trim()),
                    Long.parseLong(str[2].trim()));
            str = parts[1].split(", ");
            Vel vel =  new Vel(Integer.parseInt(str[0].trim()), Integer.parseInt(str[1].trim()),
                    Integer.parseInt(str[2].trim()));

            long x1 = pos.x + vel.vx;
            long y1 = pos.y + vel.vy;
            double m = (double)(y1 - pos.y) / (double) (x1 - pos.x);
            // c = y - mx, put x1
            double c = pos.y - (m * pos.x);
            stones.add(new Stone(pos, vel, new Line(m, -1, c)));
        }

        int count = 0;
        for (int i = 0; i < stones.size() - 1; i++) {
            for (int j = i+1; j < stones.size(); j++) {
                Stone s1 = stones.get(i);
                Stone s2 = stones.get(j);

                double a1b2minusa2b1 = (s1.line.a * s2.line.b) - (s2.line.a * s1.line.b);
                if (a1b2minusa2b1 == 0) {
                    continue;
                }
                // x = b1c2 - b2c1 / a1b2 - a2b1
                double ix = ((s1.line.b * s2.line.c) - (s2.line.b * s1.line.c)) / a1b2minusa2b1;
                // y = c1a2 - c2a1 / a1b2 - a2b1
                double iy = ((s1.line.c * s2.line.a) - (s2.line.c * s1.line.a)) / a1b2minusa2b1;

                // t > 0
                // x = X + Vt => t = (x - X) / V
                double tx1 = (ix - s1.pos.x) / (double)s1.vel.vx;
                double tx2 = (ix - s2.pos.x) / (double)s2.vel.vx;


                if (tx1 > 0 && tx2 > 0 && ix >= LEAST && ix <= MOST && iy >= LEAST && iy <= MOST) {
                    //System.out.println(ix + ", " + iy);
                    count++;
                }
            }
        }

        System.out.println("Part 1: " + count);

        // Generate SageMath script
        System.out.println();
        System.out.println("var('x y z vx vy vz t1 t2 t3')");
        System.out.println("eq1 = x + (vx * t1) == " + stones.get(0).pos.x + " + (" + stones.get(0).vel.vx + " * t1)");
        System.out.println("eq2 = y + (vy * t1) == " + stones.get(0).pos.y + " + (" + stones.get(0).vel.vy + " * t1)");
        System.out.println("eq3 = z + (vz * t1) == " + stones.get(0).pos.z + " + (" + stones.get(0).vel.vz + " * t1)");
        System.out.println("eq4 = x + (vx * t2) == " + stones.get(1).pos.x + " + (" + stones.get(1).vel.vx + " * t2)");
        System.out.println("eq5 = y + (vy * t2) == " + stones.get(1).pos.y + " + (" + stones.get(1).vel.vy + " * t2)");
        System.out.println("eq6 = z + (vz * t2) == " + stones.get(1).pos.z + " + (" + stones.get(1).vel.vz + " * t2)");
        System.out.println("eq7 = x + (vx * t3) == " + stones.get(2).pos.x + " + (" + stones.get(2).vel.vx + " * t3)");
        System.out.println("eq8 = y + (vy * t3) == " + stones.get(2).pos.y + " + (" + stones.get(2).vel.vy + " * t3)");
        System.out.println("eq9 = z + (vz * t3) == " + stones.get(2).pos.z + " + (" + stones.get(2).vel.vz + " * t3)");
        System.out.println("print(solve([eq1,eq2,eq3,eq4,eq5,eq6,eq7,eq8,eq9],x,y,z,vx,vy,vz,t1,t2,t3))");
        System.out.println();

        // Run the above script in SageMath and sum the values of x, y & z
        System.out.println("Part 2: " + (422521403380479L + 268293246383898L + 153073450808511L));
    }
}
