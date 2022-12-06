package code.vipul.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static code.vipul.aoc2021.Inputs.INPUT_4;

/**
 * Created by vgaur created on 04/06/22
 * https://adventofcode.com/2021/day/4
 */
public class Solve4 {

    private static final String INPUT = "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1\n" +
            "\n" +
            "22 13 17 11  0\n" +
            " 8  2 23  4 24\n" +
            "21  9 14 16  7\n" +
            " 6 10  3 18  5\n" +
            " 1 12 20 15 19\n" +
            "\n" +
            " 3 15  0  2 22\n" +
            " 9 18 13 17  5\n" +
            "19  8  7 25 23\n" +
            "20 11 10 24  4\n" +
            "14 21 16 12  6\n" +
            "\n" +
            "14 21 17 24  4\n" +
            "10 16 15  9 19\n" +
            "18  8 23 26 20\n" +
            "22 11 13  6  5\n" +
            " 2  0 12  3  7";

    public static void solve() {
        System.out.println(getWinner(INPUT_4, false));
    }

    public static void solvePart2() {
        System.out.println(getWinner(INPUT_4, true));
    }

    private static int getWinner(String input, boolean isLast) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());

        List<Integer> numbers = Arrays.stream(inputs.get(0).split(","))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        List<Board> boards = new ArrayList<>();
        Board currentBoard = new Board();
        int currentRow = 0;

        for (int i = 2; i < inputs.size(); i++) {
            if (inputs.get(i).isEmpty()) {
                boards.add(currentBoard);
                currentBoard = new Board();
                currentRow = 0;
                continue;
            }
            List<Integer> inputRow = Arrays.stream(inputs.get(i).split(" "))
                    .filter(s -> !s.isEmpty())
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            currentBoard.addRow(inputRow, currentRow);
            currentRow++;
        }

        if (currentRow > 0) {
            boards.add(currentBoard);
        }

        Integer called = 0;
        Board winner = null;
        Set<UUID> bingos = new HashSet<>();
        for (Integer num : numbers) {
            for (Board board : boards) {
                board.announce(num);

                if (board.checkBingo()) {
                    bingos.add(board.id);
                    if (isLast && bingos.size() == boards.size()) {
                        called = num;
                        winner = board;
                        break;
                    } else if (!isLast) {
                        called = num;
                        winner = board;
                        break;
                    }
                }
            }
            if (winner != null) {
                break;
            }
        }
        return winner.getUnmarkedCount() * called;
    }

    public static final class Board {
        private final int[][] board = new int[5][5];
        private final int[][] marked = new int[5][5];
        public final UUID id = UUID.randomUUID();
        private boolean isBingo = false;

        public Board() {

        }

        public void addRow(List<Integer> row, int rowIndex) {
            int ctr = 0;
            for (Integer r : row) {
                board[rowIndex][ctr++] = r;
            }
        }

        public void announce(Integer num) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == num) {
                        marked[i][j] = 1;
                    }
                }
            }
        }

        public boolean checkBingo() {
            if (isBingo) {
                return true;
            }
            int[] rowcount = new int[5];
            int[] colcount = new int[5];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (marked[i][j] == 1) {
                        rowcount[i]++;
                        colcount[j]++;
                    }
                }
            }
            isBingo = Arrays.stream(rowcount).anyMatch(i -> i == 5) || Arrays.stream(colcount).anyMatch(i -> i == 5);
            return isBingo;
        }

        public int getUnmarkedCount() {
            int count = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (marked[i][j] != 1) {
                        count += (board[i][j]);
                    }
                }
            }
            return count;
        }
    }
}
