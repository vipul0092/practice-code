package code.vipul.aoc2021;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by vgaur created on 02/01/23
 * https://adventofcode.com/2021/day/17
 */
public class Solve17 {

    private static final String INPUT = "target area: x=20..30, y=-10..-5";

    private static Map<Integer, Integer> summations;
    private static Map<Integer, Set<Integer>> cache = new HashMap<>();

    public static void solve() {
        String input = Inputs.INPUT_17;
        //String input = INPUT;

        int[] xlimits = new int[2];
        int[] ylimits = new int[2];

        TreeMap<Integer, Integer> sums = new TreeMap<>();
        summations = new LinkedHashMap<>();

        for (int i = 1; i < 500; i++) {
            int sum = (i * (i + 1)) / 2;
            sums.put(sum, i);
            summations.put(i, sum);
        }


        String[] parts = input.split("target area: ")[1].split(", ");
        String[] limits = parts[0].split("=")[1].split("\\.\\.");
        xlimits[0] = Integer.parseInt(limits[0]);
        xlimits[1] = Integer.parseInt(limits[1]);

        limits = parts[1].split("=")[1].split("\\.\\.");
        ylimits[0] = Integer.parseInt(limits[0]);
        ylimits[1] = Integer.parseInt(limits[1]);

        int minSpeedY = ylimits[0];
        int maxSpeedY = Math.abs(ylimits[0]) - 1;
        int minSpeedX = sums.get(sums.ceilingKey(xlimits[0]));
        int maxSpeedX = xlimits[1];

        int maxHeight = (maxSpeedY * (maxSpeedY + 1)) / 2;
        System.out.println("Part 1: " + maxHeight); //5995

        int answer = 0;

        for (int y = minSpeedY; y <= maxSpeedY; y++) {
            int speedy = y;
            if (y > 0) {
                speedy = -(y + 1);
            }
            int ycoor = 0;

            int second = y > 0 ? ((2 * y) + 2) : 1;
            var xset = new HashSet<>();
            for (int t = second; ; t++) {
                ycoor += speedy;
                speedy--;
                if (ycoor < ylimits[0]) {
                    break;
                }
                if (ycoor >= ylimits[0] && ycoor <= ylimits[1]) {
                    xset.addAll(getXSpeedSet(minSpeedX, maxSpeedX, xlimits, t));
                }
            }
            answer += xset.size();
        }

        System.out.println("Part 2: " + answer); //3202
    }

    private static Set<Integer> getXSpeedSet(int minSpeedX, int maxSpeedX, int[] xlimits, int second) {
        if (cache.containsKey(second)) {
            return cache.get(second);
        }
        Set<Integer> xset = new HashSet<>();
        for (int x = minSpeedX; x <= maxSpeedX; x++) {
            // x-coor(t) = Summation(xv) - Summation(xv - t);
            int xcoor = summations.get(x) - ((x - second) <= 0 ? 0 : summations.get(x - second));
            if (xcoor <= 0) {
                break;
            }
            if (xcoor >= xlimits[0] && xcoor <= xlimits[1]) {
                xset.add(x);
            }
        }
        cache.put(second, xset);
        return xset;
    }
}
