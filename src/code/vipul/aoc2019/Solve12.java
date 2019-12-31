package code.vipul.aoc2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * https://adventofcode.com/2019/day/12
 */
public class Solve12 {

    private static String input =
            "<x=16, y=-11, z=2>\n" +
                    "<x=0, y=-4, z=7>\n" +
                    "<x=6, y=4, z=-10>\n" +
                    "<x=-3, y=-2, z=-4>\n";

    public static void solve() {
        List<Moon> moons = parseInputs(input);

        int simulations = 1000;
        int totalEnergy = 0;
        while (simulations-- > 0) {
            applyGravity(moons);
            applyVelocity(moons);
            totalEnergy = moons.stream().map(m -> m.getTotalEnergy()).reduce(0, (total, e) -> total += e);
        }

        System.out.println("Answer: " + totalEnergy); // 10055
    }

    public static void solvePart2() {
//        input = "<x=-8, y=-10, z=0>\n" +
//                "<x=5, y=5, z=10>\n" +
//                "<x=2, y=-7, z=3>\n" +
//                "<x=9, y=-8, z=-3>";


        long simulations = 1000000;

        List<Moon> moons = parseInputs(input);
        long repeatX = getRepeatSimulations(simulations, moons, (m) -> m.x, (m) -> m.vx);

        moons = parseInputs(input);
        long repeatY = getRepeatSimulations(simulations, moons, (m) -> m.y, (m) -> m.vy);

        moons = parseInputs(input);
        long repeatZ = getRepeatSimulations(simulations, moons, (m) -> m.z, (m) -> m.vz);

        long lcmXYZ = lcm(repeatZ, lcm(repeatX, repeatY));

        System.out.println("Answer: " + lcmXYZ); // 374307970285176
    }

    private static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    private static long getRepeatSimulations(long simulations,
                                             List<Moon> moons,
                                             Function<Moon, Integer> posGetter,
                                             Function<Moon, Integer> velocityGetter) {
        Map<CoordinateState, Long> states = new HashMap<>();
        CoordinateState found = null;
        long ctr = 0;
        while (ctr < simulations) {
            CoordinateState x = new CoordinateState();
            x.one = posGetter.apply(moons.get(0));
            x.two = posGetter.apply(moons.get(1));
            x.three = posGetter.apply(moons.get(2));
            x.four = posGetter.apply(moons.get(3));

            x.vone = velocityGetter.apply(moons.get(0));
            x.vtwo = velocityGetter.apply(moons.get(1));
            x.vthree = velocityGetter.apply(moons.get(2));
            x.vfour = velocityGetter.apply(moons.get(3));

            if (states.containsKey(x)) {
                found = x;
                System.out.println("Found it!");
                break;
            }

            states.put(x, ctr);

            applyGravity(moons);
            applyVelocity(moons);
            ctr++;
        }
        return ctr;
    }

    private static void applyGravity(List<Moon> moons) {
        for (int i = 0; i < moons.size(); i++) {
            Moon moonToConsider = moons.get(i);
            for (int j = 0; j < moons.size(); j++) {
                if (i == j) continue;
                Moon affectingMoon = moons.get(j);

                if (affectingMoon.x > moonToConsider.x) {
                    moonToConsider.vx += 1;
                } else if (affectingMoon.x < moonToConsider.x) {
                    moonToConsider.vx -= 1;
                }
                if (affectingMoon.y > moonToConsider.y) {
                    moonToConsider.vy += 1;
                } else if (affectingMoon.y < moonToConsider.y) {
                    moonToConsider.vy -= 1;
                }
                if (affectingMoon.z > moonToConsider.z) {
                    moonToConsider.vz += 1;
                } else if (affectingMoon.z < moonToConsider.z) {
                    moonToConsider.vz -= 1;
                }
            }
        }
    }

    private static void applyVelocity(List<Moon> moons) {
        moons.forEach(moon -> {
            moon.x += moon.vx;
            moon.y += moon.vy;
            moon.z += moon.vz;
        });
    }

    private static final String csvNames = "Io,Europa,Ganymede,Callisto";

    private static List<Moon> parseInputs(String in) {
        in = in.replace(" ", "")
                .replace("<", "")
                .replace(">", "");
        String[] moonInputs = in.split("\n");
        String[] names = csvNames.split(",");


        List<Moon> moons = new ArrayList<>();
        int ctr = 0;
        for (String moonInput : moonInputs) {
            String[] positions = moonInput.split(",");

            Moon moon = new Moon();
            moon.x = Integer.parseInt(positions[0].substring(2));
            moon.y = Integer.parseInt(positions[1].substring(2));
            moon.z = Integer.parseInt(positions[2].substring(2));
            moon.name = names[ctr++];

            moons.add(moon);
        }
        return moons;
    }

    private static class Moon {
        private String name;

        private int x;
        private int y;
        private int z;

        private int vx = 0;
        private int vy = 0;
        private int vz = 0;

        int getTotalEnergy() {
            return (Math.abs(x) + Math.abs(y) + Math.abs(z))
                    * (Math.abs(vx) + Math.abs(vy) + Math.abs(vz));
        }

        @Override
        public String toString() {
            return String.format("Name: %s; < x: %s, y: %s, z: %s > < vx: %s, vy: %s, vz: %s >",
                    name, x, y, z, vx, vy, vz);
        }
    }

    private static final class CoordinateState {
        private int one;
        private int two;
        private int three;
        private int four;
        private int vone;
        private int vtwo;
        private int vthree;
        private int vfour;

        @Override
        public int hashCode() {
            int h = 5381;
            h += (h << 5) + Objects.hashCode(one);
            h += (h << 5) + Objects.hashCode(two);
            h += (h << 5) + Objects.hashCode(three);
            h += (h << 5) + Objects.hashCode(four);
            h += (h << 5) + Objects.hashCode(vone);
            h += (h << 5) + Objects.hashCode(vtwo);
            h += (h << 5) + Objects.hashCode(vthree);
            h += (h << 5) + Objects.hashCode(vfour);
            return h;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof CoordinateState && equalTo((CoordinateState) another);
        }

        private boolean equalTo(CoordinateState another) {
            return Objects.equals(one, another.one)
                    && Objects.equals(two, another.two)
                    && Objects.equals(three, another.three)
                    && Objects.equals(four, another.four)
                    && Objects.equals(vone, another.vone)
                    && Objects.equals(vtwo, another.vtwo)
                    && Objects.equals(vthree, another.vthree)
                    && Objects.equals(vfour, another.vfour);
        }
    }
}
