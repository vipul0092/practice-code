package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_20;

/**
 * Created by vgaur created on 29/12/23
 */
public class Day20 {

    private static String INPUT = """
            p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>
            p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>
            p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>
            p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>
            """;

    record Particle(long px, long py, long pz, long vx, long vy, long vz, long ax, long ay, long az){}

    public static void solve() {
        INPUT = DAY_20;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        List<Particle> particles = new ArrayList<>();
        for (String line : lines) {
            var parts = line.split(", ");
            var p = parse(parts[0]);
            var v = parse(parts[1]);
            var a = parse(parts[2]);

            var particle = new Particle(p[0], p[1], p[2], v[0], v[1], v[2], a[0], a[1], a[2]);
            particles.add(particle);
        }

        long time = 100_000;
        long min = Long.MAX_VALUE, minpos = -1;
        for (int i = 0; i < particles.size(); i++) {
            var p = particles.get(i);
            long px = getPos(p.px, p.vx, p.ax, time);
            long py = getPos(p.py, p.vy, p.ay, time);
            long pz = getPos(p.pz, p.vz, p.az, time);

            long dis = Math.abs(px) + Math.abs(py) + Math.abs(pz);

            if (dis < min) {
                min = dis;
                minpos = i;
            }
        }
        System.out.println("Part 1: " + minpos); // 308

        // ----- Part 2 Begins -----

        int ticks = 0;
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < particles.size(); i++) {
            remaining.add(i);
        }
        Set<Integer> destroyed = new HashSet<>();
        while(ticks++ < 500) {
            Map<Integer, Long> xs = new HashMap<>();
            Map<Integer, Long> ys = new HashMap<>();
            Map<Integer, Long> zs = new HashMap<>();

            for (int i : remaining) {
                Particle p = particles.get(i);
                xs.put(i, getPos(p.px, p.vx, p.ax, ticks));
                ys.put(i, getPos(p.py, p.vy, p.ay, ticks));
                zs.put(i, getPos(p.pz, p.vz, p.az, ticks));
            }

            for (int i = 0; i < remaining.size()-1; i++) {
                for (int j = i+1; j < remaining.size(); j++) {
                    if (Objects.equals(xs.get(i), xs.get(j))
                            && Objects.equals(ys.get(i), ys.get(j))
                            && Objects.equals(zs.get(i), zs.get(j))) {
                        destroyed.add(i);
                        destroyed.add(j);
                    }
                }
            }
            List<Integer> newrem = new ArrayList<>();
            for (int rem : remaining) {
                if (!destroyed.contains(rem)) {
                    newrem.add(rem);
                }
            }
            remaining = newrem;
        }
        System.out.println("Part 2: " + (particles.size() - destroyed.size())); // 504
    }

    private static long[] parse(String str) {
        str = str.split("=<")[1];
        str = str.substring(0, str.length()-1);
        var parts = str.split(",");
        return new long[]{ Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]) };
    }

    // x(t) = x0 + ut + (at^2)/2
    private static long getPos(long p0, long v, long a, long t) {
        long pos = p0;
        pos += (v*t);
        // a*t*(t+1) because of discrete steps
        // https://www.reddit.com/r/adventofcode/comments/7l1766/2017_day_20_part_2_so_stopping_conditions_anyone/driox9i/
        pos += ((a*t*(t+1))/2);
        return pos;
    }
}
