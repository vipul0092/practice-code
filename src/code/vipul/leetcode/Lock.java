package code.vipul.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 04/06/21
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3767/
 * AC
 */
public class Lock {
    public static void solve() {
        String[] boohoo = new String[]{"5557","5553","5575","5535","5755","5355","7555","3555","6655","6455","4655","4455","5665","5445","5645","5465","5566","5544","5564","5546","6565","4545","6545","4565","5656","5454","5654","5456","6556","4554","4556","6554"};
        System.out.println(new Lock().openLock2(boohoo, "5555"));

        boohoo = new String[]{"8888"};
        System.out.println(new Lock().openLock2(boohoo, "0009"));
    }

    private static int[] powers = new int[]{1000, 100, 10, 1};

    // Written in 2023
    public int openLock2(String[] _deadends, String _target) {
        Set<Integer> deadends =
                Arrays.stream(_deadends).map(d -> getNum(d)).collect(Collectors.toSet());
        int target = getNum(_target);

        if (deadends.contains(0)) {
            return -1;
        }

        Queue<Integer> nums = new ArrayDeque<>();
        Queue<Integer> length = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        nums.add(0); length.add(0); visited.add(0);

        int answer = -1;

        while(!nums.isEmpty()) {
            int num = nums.remove();
            int len = length.remove();

            if (num == target) {
                answer = len;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int next = getNext(num, i, true);
                if (!deadends.contains(next) && !visited.contains(next)) {
                    nums.add(next); length.add(len + 1);
                    visited.add(next);
                }

                next = getNext(num, i, false);
                if (!deadends.contains(next) && !visited.contains(next)) {
                    nums.add(next); length.add(len + 1);
                    visited.add(next);
                }
            }
        }
        return answer;
    }

    private int getNum(String str) {
        return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100)
                + ((str.charAt(2) - '0') * 10) + ((str.charAt(3) - '0'));
    }

    private int getNext(int num, int pos, boolean up) {
        int numFromDigit = num % (powers[pos] * 10);

        int digit = numFromDigit / powers[pos];
        int past = numFromDigit % powers[pos];

        int withoutPast = num - numFromDigit;
        int nextDigit = (up ? digit + 11 : digit + 9) % 10;

        return withoutPast + (nextDigit * powers[pos]) + past;
    }

    private static Map<Integer, Integer> powersOfTen;

    // Written in 2021
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
