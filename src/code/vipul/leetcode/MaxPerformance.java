package code.vipul.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by vgaur created on 06/06/21
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3768/
 * AC
 */
public class MaxPerformance {

    public static final boolean BIG_INPUT = false;

    public static void test() {
        int n = 6;
        int[] speed = new int[]{2, 10, 3, 1, 5, 8};
        int[] efficiency = new int[]{5, 4, 3, 9, 7, 2};
        int k = 4;

        if (BIG_INPUT) {
            n = MaxPerformanceInputs.N;
            k = MaxPerformanceInputs.K;
            speed = new int[n];
            int idx = 0;
            for (String s : MaxPerformanceInputs.SPEEDS.split(",")) {
                speed[idx++] = Integer.parseInt(s);
            }

            efficiency = new int[n];
            idx = 0;
            for (String s : MaxPerformanceInputs.EFFICIENCIES.split(",")) {
                efficiency[idx++] = Integer.parseInt(s);
            }
        }

        int answer = maxPerformance(n, speed, efficiency, k);
        System.out.println(answer);
    }

    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        long mod = (1000 * 1000 * 1000) + 7;
        TreeMap<Integer, Integer> speedMap = new TreeMap<>(Comparator.reverseOrder());
        TreeSet<Integer> setOfEfficiencies = new TreeSet<>(Comparator.reverseOrder());
        Map<Integer, List<Integer>> initialPositions = new HashMap<>();

        long answer = 0;

        for (int i = 0; i < efficiency.length; i++) {
            initialPositions.putIfAbsent(efficiency[i], new ArrayList<>());
            initialPositions.get(efficiency[i]).add(i);
            setOfEfficiencies.add(efficiency[i]);
        }

        int speedCountUntilNow = 0;
        long tempSpeedSum = 0;

        // Go through the reverse sorted efficiency set
        for (int eff : setOfEfficiencies) {
            for (int pos : initialPositions.get(eff)) {
                int incomingSpeed = speed[pos];
                speedMap.putIfAbsent(incomingSpeed, 0);
                speedMap.put(incomingSpeed, speedMap.get(incomingSpeed) + 1);
                speedCountUntilNow++;

                if (speedCountUntilNow <= k) {
                    tempSpeedSum += incomingSpeed;
                } else {
                    int leastSpeed = speedMap.lastKey();
                    if (incomingSpeed > leastSpeed) {
                        tempSpeedSum -= leastSpeed;
                        tempSpeedSum += incomingSpeed;
                    }

                    int cnt = speedMap.get(leastSpeed);
                    if (cnt == 1) {
                        speedMap.remove(leastSpeed);
                    } else {
                        speedMap.put(leastSpeed, speedMap.get(leastSpeed) - 1);
                    }
                }
            }
            long performance = tempSpeedSum * eff;
            answer = Math.max(answer, performance);
        }
        return (int) (answer % mod);
    }
}
