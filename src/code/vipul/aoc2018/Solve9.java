package code.vipul.aoc2018;

import code.vipul.linkedlist.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * https://adventofcode.com/2018/day/9
 */
public class Solve9 {

    static String input = "464 players; last marble is worth 70918 points";

    public static void solve() {
        solve(1);
    }

    public static void solvePart2() {
        solve(100);
    }

    private static void solve(int multiplier) {
        int players = Integer.parseInt(input.split(" ")[0].trim());
        int iterations = Integer.parseInt(input.split(" ")[6].trim()) * multiplier;

        Node currentNode = Node.of(0);
        currentNode.next = currentNode;
        currentNode.prev = currentNode;

        Map<Integer, Long> playerScores = new HashMap<>();
        long maxScore = 0;

        for (int i = 1; i <= iterations; i++) {
            if (i % 23 == 0) {
                int player = i % players;
                playerScores.putIfAbsent(player, 0L);

                // Take the current node in score
                int currentScore = i;
                Node temp = Node.intCopyOf(currentNode);
                int goBackSteps = 7;

                // Go back 7 steps and add the value to score
                while (goBackSteps-- > 0) {
                    temp = temp.prev;
                }
                currentScore += (temp.intValue);

                // Remove the node
                Node prevTemp = temp.prev;
                Node nextTemp = temp.next;
                prevTemp.next = nextTemp;
                nextTemp.prev = prevTemp;

                long newScore = playerScores.get(player) + currentScore;
                playerScores.put(player, newScore);
                maxScore = Math.max(maxScore, newScore);
                currentNode = temp.next;
                continue;
            }
            Node step = Node.of(i);
            // 0 (4) 2  1  3
            // 0  4  2 (5) 1  3

            Node nextOfCurrent = currentNode.next;
            Node nextOfNextOfCurrent = nextOfCurrent.next;

            nextOfCurrent.next = step;
            step.prev = nextOfCurrent;
            step.next = nextOfNextOfCurrent;
            nextOfNextOfCurrent.prev = step;
            currentNode = step;
        }

        System.out.println("Answer: " + maxScore); // 370210 for part1, 3101176548 for part 2
    }
}
