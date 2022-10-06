package code.vipul.aoc2020;


import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static code.vipul.aoc2020.Inputs2.INPUT_22;

/**
 * https://adventofcode.com/2020/day/22
 */
public class Solve22 {

    private static final String INPUT = "Player 1:\n" +
            "9\n" +
            "2\n" +
            "6\n" +
            "3\n" +
            "1\n" +
            "\n" +
            "Player 2:\n" +
            "5\n" +
            "8\n" +
            "4\n" +
            "7\n" +
            "10";


    private static Queue<Integer> player1;
    private static Queue<Integer> player2;

    public static void solve() {
        parseInput(INPUT_22);

        int winner = playRecursive(player1, player2, false);

        Queue<Integer> q = winner == 1 ? player1 : player2;
        int size = q.size();
        int answer = 0;
        System.out.println(hash(q));
        while (!q.isEmpty()) {
            answer += (size * q.remove());
            size--;
        }
        System.out.println("Answer: " + answer); //33400
    }

    public static void solvePart2() {

        parseInput(INPUT_22);

        int winner = playRecursive(player1, player2, true);

        Queue<Integer> q = winner == 1 ? player1 : player2;
        int size = q.size();
        int answer = 0;
        System.out.println(hash(q));
        while (!q.isEmpty()) {
            answer += (size * q.remove());
            size--;
        }
        System.out.println("Answer: " + answer); //33745
    }

    private static int gamenum = 0;

    private static int playRecursive(Queue<Integer> p1, Queue<Integer> p2, boolean recurse) {
        Set<String> states1 = new LinkedHashSet<>();
        Set<String> states2 = new LinkedHashSet<>();

        String hash1 = hash(p1);
        String hash2 = hash(p2);
        int rounds = 0;

//        System.out.println();
//        System.out.println("Game Number: " + ++gamenum);

        while (!p1.isEmpty() && !p2.isEmpty()) {
            if (states1.contains(hash1) || states2.contains(hash2)) {
                return 1;
            }
//            System.out.println("Round Number: " + ++rounds);
            states1.add(hash1);
            states2.add(hash2);
//            System.out.println("Player 1 deck: " + hash1);
//            System.out.println("Player 2 deck: " + hash2);

            int card1 = p1.remove();
            int card2 = p2.remove();

//            System.out.println("Player 1 plays: " + card1);
//            System.out.println("Player 2 plays: " + card2);

            int winner = -1;
            if (card1 > p1.size() || card2 > p2.size() || !recurse) {
                winner = card1 > card2 ? 1 : 2;
            } else {
                Queue<Integer> nextDeck1 = new LinkedList<>();
                Queue<Integer> nextDeck2 = new LinkedList<>();

                p1.stream().limit(card1).forEach(nextDeck1::add);
                p2.stream().limit(card2).forEach(nextDeck2::add);
                winner = playRecursive(nextDeck1, nextDeck2, true);
            }

//            System.out.println("Player " + winner + " wins");
//            System.out.println();

            if (winner == 1) {
                p1.add(card1);
                p1.add(card2);
                hash1 = adjustWinningHash(hash1, card1, card2);
                hash2 = adjustLosingHash(hash2);
            } else {
                p2.add(card2);
                p2.add(card1);

                hash2 = adjustWinningHash(hash2, card2, card1);
                hash1 = adjustLosingHash(hash1);
            }
        }

        return p1.isEmpty() ? 2 : 1;
    }

    private static String adjustWinningHash(String hash, int card1, int card2) {
        StringBuilder s = new StringBuilder(hash.substring(hash.indexOf(',') + 1));
        return s.append(",").append(card1).append(",").append(card2).toString();
    }

    private static String adjustLosingHash(String hash) {
        return hash.substring(hash.indexOf(',') + 1);
    }

    private static String hash(Queue<Integer> q) {
        StringBuilder b = new StringBuilder();
        for (Integer i : q) {
            b.append(i).append(",");
        }
        b.deleteCharAt(b.length() - 1);
        return b.toString();
    }

    private static void parseInput(String input) {
        player1 = new LinkedList<>();
        player2 = new LinkedList<>();

        String[] lines = input.split("\n");

        Queue<Integer> q = null;
        for (String line : lines) {
            if (line.startsWith("Player 1")) {
                q = player1;
            } else if (line.startsWith("Player 2")) {
                q = player2;
            } else if (line.length() > 0) {
                q.add(Integer.parseInt(line));
            }
        }
    }
}
