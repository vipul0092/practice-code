package code.vipul.aoc2019.day13;

import code.vipul.aoc2019.intcode.CustomDisplay;
import code.vipul.aoc2019.intcode.CustomInput;

import java.util.ArrayList;
import java.util.List;

public class Game13IOHandler implements CustomDisplay, CustomInput {

    private List<Long> outputs = new ArrayList<>();
    private long currentScore = 0;
    private int[][] game = null;
    private int maxi = -1, maxj = -1;
    private int paddleJ = -1;
    private int ballJ = -1;

    long getScore() {
        return currentScore;
    }

    void triggerOutput() {
        if (game == null) {
            game = new int[100][100];
        }
        handleOutput();
        showGrid();
        outputs.clear();
    }

    @Override
    public void acceptOutput(Long value) {
        outputs.add(value);
    }

    private void handleOutput() {
        for (int a = 0; a < outputs.size(); a += 3) {
            int j = outputs.get(a).intValue();
            int i = outputs.get(a + 1).intValue();
            int tile = outputs.get(a + 2).intValue();

            if (j == -1) {
                currentScore = tile;
                continue;
            }

            maxi = Math.max(i, maxi);
            maxj = Math.max(j, maxj);

            if (tile == 4) {
                ballJ = j;
            }

            if (tile == 3) {
                paddleJ = j;
            }
            game[i][j] = tile;
        }
    }

    private void showGrid() {
        for (int i = 0; i <= maxi; i++) {
            for (int j = 0; j <= maxj; j++) {
                char ch = ' ';
                if (game[i][j] == 2) {
                    ch = 'X';
                } else if (game[i][j] == 1) {
                    ch = '*';
                } else if (game[i][j] == 3) {
                    ch = '=';
                } else if (game[i][j] == 4) {
                    ch = 'O';
                } else if (i == maxi) {
                    ch = '-';
                }
                System.out.print(ch);
            }
            System.out.println();
        }
        System.out.println("Current Score: " + currentScore);
        System.out.println();
    }

    @Override
    public long fetchInput() {
        triggerOutput();
//        try {
//            Thread.sleep(500);
//        } catch (Exception e) {
//            // Bleh
//        }
        return Long.compare(ballJ, paddleJ);
    }
}
