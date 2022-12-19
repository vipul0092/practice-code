package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 19/12/22
 */
public class Solve19 {

    private static final String INPUT = "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.\n" +
            "Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.";

    private static int MAX_TIME = 24;

    private static Robot ORE;
    private static Robot CLAY;
    private static Robot OBSIDIAN;
    private static Robot GEODE;

    private static int maxNeededOre;
    private static int maxNeededClay;
    private static int maxNeededObsidian;

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_19.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int ans = 0;
        for (String in : inputs) {
            int blueprintNum = Integer.parseInt(in.split(": Each ore robot costs")[0].split(" ")[1]);

            // ore robot
            int oreCost = Integer.parseInt(in.split(" ore\\. Each clay robot costs")[0].split("Each ore robot costs ")[1]);
            ORE = new Robot("ore", oreCost, 0, 0);

            // clay robot
            int clayCost = Integer.parseInt(in.split(" ore\\. Each obsidian robot costs ")[0].split("Each clay robot costs ")[1]);
            CLAY = new Robot("clay", clayCost, 0, 0);

            // obsidian robot
            String costString = in.split("\\. Each geode robot costs")[0].split("Each obsidian robot costs ")[1];
            String[] costs = costString.split(" and ");
            OBSIDIAN = new Robot("obsidian", Integer.parseInt(costs[0].split(" ")[0]),
                    Integer.parseInt(costs[1].split(" ")[0]), 0);

            // geode robot
            costString = in.split("Each geode robot costs ")[1].split("\\.")[0];
            costs = costString.split(" and ");
            GEODE = new Robot("geode", Integer.parseInt(costs[0].split(" ")[0]), 0,
                    Integer.parseInt(costs[1].split(" ")[0]));

            answer = Integer.MIN_VALUE;
            maxNeededOre = Math.max(ORE.ore, Math.max(CLAY.ore, Math.max(OBSIDIAN.ore, GEODE.ore)));
            maxNeededClay = Math.max(ORE.clay, Math.max(CLAY.clay, Math.max(OBSIDIAN.clay, GEODE.clay)));
            maxNeededObsidian = Math.max(ORE.obsidian, Math.max(CLAY.obsidian, Math.max(OBSIDIAN.obsidian, GEODE.obsidian)));

            dfs(0, 0, 0, 0, 1, 0, 0, 0, 1);
            System.out.println(String.format("Blueprint: %s, geodes: %s", blueprintNum, answer));
            ans += (answer * blueprintNum);
        }
        System.out.println(ans);
    }

    public static void solvePart2() {
        MAX_TIME = 32;
        List<String> inputs = Arrays.stream(Inputs.INPUT_19.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int ans = 1;
        int iterations = Math.min(inputs.size(), 3);
        for (int i = 0; i < iterations; i++) {
            String in = inputs.get(i);
            int blueprintNum = Integer.parseInt(in.split(": Each ore robot costs")[0].split(" ")[1]);

            // ore robot
            int oreCost = Integer.parseInt(in.split(" ore\\. Each clay robot costs")[0].split("Each ore robot costs ")[1]);
            ORE = new Robot("ore", oreCost, 0, 0);

            // clay robot
            int clayCost = Integer.parseInt(in.split(" ore\\. Each obsidian robot costs ")[0].split("Each clay robot costs ")[1]);
            CLAY = new Robot("clay", clayCost, 0, 0);

            // obsidian robot
            String costString = in.split("\\. Each geode robot costs")[0].split("Each obsidian robot costs ")[1];
            String[] costs = costString.split(" and ");
            OBSIDIAN = new Robot("obsidian", Integer.parseInt(costs[0].split(" ")[0]),
                    Integer.parseInt(costs[1].split(" ")[0]), 0);

            // geode robot
            costString = in.split("Each geode robot costs ")[1].split("\\.")[0];
            costs = costString.split(" and ");
            GEODE = new Robot("geode", Integer.parseInt(costs[0].split(" ")[0]), 0,
                    Integer.parseInt(costs[1].split(" ")[0]));

            answer = Integer.MIN_VALUE;
            maxNeededOre = Math.max(ORE.ore, Math.max(CLAY.ore, Math.max(OBSIDIAN.ore, GEODE.ore)));
            maxNeededClay = Math.max(ORE.clay, Math.max(CLAY.clay, Math.max(OBSIDIAN.clay, GEODE.clay)));
            maxNeededObsidian = Math.max(ORE.obsidian, Math.max(CLAY.obsidian, Math.max(OBSIDIAN.obsidian, GEODE.obsidian)));

            dfs(0, 0, 0, 0, 1, 0, 0, 0, 1);
            System.out.println(String.format("Blueprint: %s, geodes: %s", blueprintNum, answer));
            ans *= answer;
        }
        System.out.println(ans);
    }

    private static int answer = Integer.MIN_VALUE;

    private static void dfs(int ore, int clay, int obsidian, int geode,
                            int oreRobot, int clayRobot, int obsidianRobot, int geodeRobot,
                            int time) {

        int rem = MAX_TIME - time;
        int canProduce = geode;
        int geodePm = geodeRobot;
        while (rem-- >= 0) {
            canProduce += (geodePm);
            geodePm++;
        }

        if (canProduce < answer) {
            return;
        }

        if (time == MAX_TIME) {
            geode += geodeRobot;
            if (geode > 0 && geode > answer) {
                answer = geode;
            }
            return;
        }
        boolean canCreateGeode = canCreate(GEODE, ore, clay, obsidian);
        if (canCreateGeode) {
            ore -= GEODE.ore;
            obsidian -= GEODE.obsidian;
        } else {
            if (obsidianRobot < maxNeededObsidian && canCreate(OBSIDIAN, ore, clay, obsidian)) {
                dfs(ore - OBSIDIAN.ore + oreRobot, clay - OBSIDIAN.clay + clayRobot,
                        obsidian - OBSIDIAN.obsidian + obsidianRobot, geode + geodeRobot,
                        oreRobot, clayRobot, obsidianRobot + 1, geodeRobot, time + 1);
            }

            if (clayRobot < maxNeededClay && canCreate(CLAY, ore, clay, obsidian)) {
                dfs(ore - CLAY.ore + oreRobot, clay - CLAY.clay + clayRobot,
                        obsidian - CLAY.obsidian + obsidianRobot, geode + geodeRobot,
                        oreRobot, clayRobot + 1, obsidianRobot, geodeRobot, time + 1);
            }

            if (oreRobot < maxNeededOre && canCreate(ORE, ore, clay, obsidian)) {
                dfs(ore - ORE.ore + oreRobot, clay - ORE.clay + clayRobot,
                        obsidian - ORE.obsidian + obsidianRobot, geode + geodeRobot,
                        oreRobot + 1, clayRobot, obsidianRobot, geodeRobot, time + 1);
            }
        }
        // Update material
        ore += oreRobot;
        clay += clayRobot;
        obsidian += obsidianRobot;
        geode += geodeRobot;

        if (canCreateGeode) {
            geodeRobot++;
        }

        dfs(ore, clay, obsidian, geode, oreRobot, clayRobot, obsidianRobot, geodeRobot, time + 1);
    }

    private static boolean canCreate(Robot robot, int ore, int clay, int obsidian) {
        return ore >= robot.ore && clay >= robot.clay && obsidian >= robot.obsidian;
    }

    private static class Robot {
        private final String type;
        private final int ore;
        private final int clay;
        private final int obsidian;

        public Robot(String type, int ore, int clay, int obsidian) {
            this.type = type;
            this.ore = ore;
            this.clay = clay;
            this.obsidian = obsidian;
        }
    }
}
