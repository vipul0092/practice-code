package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_14;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day14 {

    private static String INPUT = """
            Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
            Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
            """;

    record Reindeer(int speed, int burstTime, int restTime){}

    public static void solve() {
        INPUT = DAY_14;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        int seconds = 2503; // 2503

        int max = 0;
        List<Reindeer> reindeers = new ArrayList<>();
        for (String line : lines) {
            int restSeconds = Integer.parseInt(line.split(" rest for ")[1].split(" ")[0]);
            int secs = Integer.parseInt(line.split("km/s for ")[1].split(" ")[0]);
            int speed = Integer.parseInt(line.split(" can fly ")[1].split(" ")[0]);
            Reindeer reindeer = new Reindeer(speed, secs, restSeconds);
            max = Math.max(getTotalDistance(reindeer, seconds), max);
            reindeers.add(reindeer);
        }
        System.out.println("Part 1: " + max); // 2655

        Map<Integer, Integer> points = new HashMap<>();
        for (int t = 1; t <= seconds; t++) {
            int maxPoints = 0;
            List<Integer> winners = new ArrayList<>();
            for (int j = 0; j < reindeers.size(); j++) {
                Reindeer reindeer = reindeers.get(j);
                int dis = getTotalDistance(reindeer, t);
                if (dis > maxPoints) {
                    winners = new ArrayList<>();
                    winners.add(j);
                    maxPoints = dis;
                } else if (dis == maxPoints) {
                    winners.add(j);
                }
            }
            for (int win : winners) {
                points.merge(win, 1, Integer::sum);
            }
        }
        max = points.values().stream().mapToInt(i -> i).max().getAsInt(); // 1059
        System.out.println("Part 2: " + max);
    }

    private static int getTotalDistance(Reindeer reindeer, int seconds) {
        int distanceCovered = reindeer.speed * reindeer.burstTime;
        int totalTime = reindeer.restTime +  reindeer.burstTime;
        int time = seconds / totalTime;

        int totalDistance = distanceCovered * time;
        int mod = seconds % totalTime;
        int extraTime = Math.min(mod,  reindeer.burstTime);
        totalDistance += (extraTime * reindeer.speed);
        return totalDistance;
    }
}
