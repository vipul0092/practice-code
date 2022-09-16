package code.vipul.leetcode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by vgaur created on 04/06/21
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3767/
 * AC
 */
public class Lock {
    public static void test() {
        String[] boohoo = new String[]{"5557","5553","5575","5535","5755","5355","7555","3555","6655","6455","4655","4455","5665","5445","5645","5465","5566","5544","5564","5546","6565","4545","6545","4565","5656","5454","5654","5456","6556","4554","4556","6554"};
        int answer = openLock(boohoo, "5555");
        System.out.println(answer);
    }

    private static Map<Integer, Integer> powersOfTen;

    public static int openLock(String[] dead, String tgt) {
        powersOfTen = new HashMap<>();
        powersOfTen.put(0, 1);
        powersOfTen.put(1, 10);
        powersOfTen.put(2, 100);
        powersOfTen.put(3, 1000);
        powersOfTen.put(4, 10000);


        int answer = -1;
        Set<Integer> deadends = new HashSet<>();
        for (String d : dead) {
            deadends.add(Integer.parseInt(d));
        }
        int target = Integer.parseInt(tgt);
        int start = 0;

        Map<Integer, Set<Integer>> adjacencyMatrix = new HashMap<>();
        Set<Integer> discovered = new HashSet<>();

        populateNeighboursIfNot(target, adjacencyMatrix, deadends);

        if (adjacencyMatrix.get(target).size() == 0) {
            return -1;
        }

        if (deadends.contains(start)) {
            return -1;
        }

        if (start == target) {
            return 0;
        }

        Queue<Integer> states = new ArrayDeque<>();
        Queue<Integer> lengthUntil = new ArrayDeque<>();
        states.add(start);
        lengthUntil.add(0);

        while(!states.isEmpty()) {
            Integer current = states.remove();
            int currentLen = lengthUntil.remove();
            discovered.add(current);

            populateNeighboursIfNot(current, adjacencyMatrix, deadends);
            Set<Integer> neighBours = adjacencyMatrix.get(current);
            if (neighBours.contains(target)) {
                answer = currentLen + 1;
                break;
            }
            for (Integer n : neighBours) {
                if (!discovered.contains(n)) {
                    discovered.add(n);
                    states.add(n);
                    lengthUntil.add(currentLen + 1);
                }
            }
        }

        return answer;
    }

    private static void populateNeighboursIfNot(int number,
                                                Map<Integer, Set<Integer>> adjacencyMatrix,
                                                Set<Integer> deadends) {
        if (adjacencyMatrix.containsKey(number)) {
            return;
        }
        Set<Integer> neighbours = new HashSet<>();
        Integer neighbour = transform(number, 0, true);
        if (!deadends.contains(neighbour)) {
            neighbours.add(neighbour);
        }
        neighbour = transform(number, 1, true);
        if (!deadends.contains(neighbour)) {
            neighbours.add(neighbour);
        }
        neighbour = transform(number, 2, true);
        if (!deadends.contains(neighbour)) {
            neighbours.add(neighbour);
        }
        neighbour = transform(number, 3, true);
        if (!deadends.contains(neighbour)) {
            neighbours.add(neighbour);
        }
        neighbour = transform(number, 0, false);
        if (!deadends.contains(neighbour)) {
            neighbours.add(neighbour);
        }
        neighbour = transform(number, 1, false);
        if (!deadends.contains(neighbour)) {
            neighbours.add(neighbour);
        }
        neighbour = transform(number, 2, false);
        if (!deadends.contains(neighbour)) {
            neighbours.add(neighbour);
        }
        neighbour = transform(number, 3, false);
        if (!deadends.contains(neighbour)) {
            neighbours.add(neighbour);
        }
        adjacencyMatrix.put(number, neighbours);
    }

    private static int transform(int number, int position, boolean up) {
        int digit = (number % powersOfTen.get(position + 1))/ powersOfTen.get(position);
        if (up) {
            if (digit == 9) {
                return number - (9 * powersOfTen.get(position));
            } else {
                return number + powersOfTen.get(position);
            }
        } else {
            if (digit == 0) {
                return number + (9 * powersOfTen.get(position));
            } else {
                return number - powersOfTen.get(position);
            }
        }
    }
}
