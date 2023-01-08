package code.vipul.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 08/01/23
 * https://adventofcode.com/2021/day/21
 */
public class Solve21 {

    private static final String INPUT = "Player 1 starting position: 4\n" +
            "Player 2 starting position: 8";

    private static int[][] rollmap;

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_21.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int pos1 = Integer.parseInt(inputs.get(0).split("starting position: ")[1]);
        int pos2 = Integer.parseInt(inputs.get(1).split("starting position: ")[1]);
        int score1 = 0;
        int score2 = 0;

        int rolls = 0;
        int current = 1;

        while (true) {
            rolls++;
            int move = current + current + 1 + current + 2;
            pos1 = (pos1 + move) % 10;
            pos1 = pos1 == 0 ? 10 : pos1;
            score1 += pos1;
            if (score1 >= 1000) {
                break;
            }
            current += 3;

            rolls++;
            move = current + current + 1 + current + 2;
            pos2 = (pos2 + move) % 10;
            pos2 = pos2 == 0 ? 10 : pos2;
            score2 += pos2;
            if (score2 >= 1000) {
                break;
            }
            current += 3;
        }
        int ans = (score1 >= 1000 ? score2 : score1) * (rolls * 3);
        System.out.println(ans);
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_21.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());
        rollmap = new int[7][2];
        rollmap[0][0] = 3;
        rollmap[0][1] = 1;
        rollmap[1][0] = 4;
        rollmap[1][1] = 3;
        rollmap[2][0] = 5;
        rollmap[2][1] = 6;
        rollmap[3][0] = 6;
        rollmap[3][1] = 7;
        rollmap[4][0] = 7;
        rollmap[4][1] = 6;
        rollmap[5][0] = 8;
        rollmap[5][1] = 3;
        rollmap[6][0] = 9;
        rollmap[6][1] = 1;

        int pos1 = Integer.parseInt(inputs.get(0).split("starting position: ")[1]);
        int pos2 = Integer.parseInt(inputs.get(1).split("starting position: ")[1]);

        Wins wins = dfs(pos1, pos2, 0, 0, true);
        System.out.println(Math.max(wins.p1, wins.p2));
    }

    private static Wins dfs(int p1, int p2, int s1, int s2, boolean onePlays) {
        Wins wins = new Wins();
        if (onePlays) {
            for (int i = 0; i < rollmap.length; i++) {
                int move = rollmap[i][0];
                int times = rollmap[i][1];
                int pos1 = getPos(p1, move);

                if (s1 + pos1 >= 21) {
                    wins.p1 += times;
                } else {
                    Wins w = dfs(pos1, p2, s1 + pos1, s2, false);
                    w.multiply(times);
                    wins.addWins(w);
                }
            }
        } else {
            for (int i = 0; i < rollmap.length; i++) {
                int move = rollmap[i][0];
                int times = rollmap[i][1];
                int pos2 = getPos(p2, move);

                if (s2 + pos2 >= 21) {
                    wins.p2 += times;
                } else {
                    Wins w = dfs(p1, pos2, s1, s2 + pos2, true);
                    w.multiply(times);
                    wins.addWins(w);
                }
            }
        }
        return wins;
    }

    private static int getPos(int p, int move) {
        int pos = (p + move) % 10;
        return pos == 0 ? 10 : pos;
    }

    private static class Wins {
        private long p1;
        private long p2;

        public Wins() {
            p1 = 0L;
            p2 = 0L;
        }

        public void addWins(Wins wins) {
            this.p1 += wins.p1;
            this.p2 += wins.p2;
        }

        public void multiply(long num) {
            this.p1 *= num;
            this.p2 *= num;
        }
    }
}
