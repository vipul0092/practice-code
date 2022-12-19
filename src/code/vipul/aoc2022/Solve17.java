package code.vipul.aoc2022;

import code.vipul.Pair;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vgaur created on 17/12/22
 */
public class Solve17 {

    private static final String INPUT = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";

    private static final Map<Integer, char[][]> rocks;
    private static final int PLUS_ROCK = 1;
    private static final int L_ROCK = 2;

    static {
        rocks = new LinkedHashMap<>();
        char[][] rock = new char[1][4];
        rock[0] = "####".toCharArray();
        rocks.put(0, rock);

        rock = new char[3][3];
        rock[0] = "\0#\0".toCharArray();
        rock[1] = "###".toCharArray();
        rock[2] = "\0#\0".toCharArray();
        rocks.put(PLUS_ROCK, rock);

        rock = new char[3][3];
        rock[0] = "\0\0#".toCharArray();
        rock[1] = "\0\0#".toCharArray();
        rock[2] = "###".toCharArray();
        rocks.put(L_ROCK, rock);

        rock = new char[4][1];
        rock[0][0] = '#';
        rock[1][0] = '#';
        rock[2][0] = '#';
        rock[3][0] = '#';
        rocks.put(3, rock);

        rock = new char[2][2];
        rock[0] = "##".toCharArray();
        rock[1] = "##".toCharArray();
        rocks.put(4, rock);
    }

    private static char[][] chamber;

    private static int MAX_SIZE = 10001;

    public static void solve() {
        //String jetspray = INPUT;
        String jetspray = Inputs.INPUT_17;
        simulate(jetspray, 2022);
        simulate(jetspray, 1000000000000L);
    }

    private static void simulate(String jetspray, long rounds) {
        chamber = new char[MAX_SIZE][7];
        for (int j = 0; j < 7; j++) {
            chamber[MAX_SIZE - 1][j] = '#';
        }
        int rockNum = 0;
        int currentJetNum = 0;
        int rockCount = 0;
        int currentHeight = MAX_SIZE - 1;
        Map<Pair<String, Pair<Integer, Integer>>, Integer> states = new LinkedHashMap<>();
        Map<Pair<String, Pair<Integer, Integer>>, Integer> heights = new LinkedHashMap<>();
        long initialPreRepeat = 0;
        long repeat = 0;
        long initialPreRepeatHeight = 0;
        long repeatHeight = 0;
        long remainderRounds = 10000;
        long totalHeight = 0;

        long initialHeight = -1;
        while (remainderRounds-- > 0) {
            int heightToStartFrom = currentHeight - 4;
            char[][] rock = rocks.get(rockNum);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 17; i++) {
                if (currentHeight + i >= MAX_SIZE) {
                    break;
                }
                sb.append(chamber[currentHeight + i]);
            }
            var key = Pair.of(sb.toString(), Pair.of(rockNum, currentJetNum));
            if (states.containsKey(key)) {
                initialPreRepeat = states.get(key);
                repeat = rockCount - initialPreRepeat;
                initialPreRepeatHeight = heights.get(key);
                repeatHeight = (MAX_SIZE - 1 - currentHeight) - initialPreRepeatHeight;

                totalHeight = initialPreRepeatHeight;
                rounds -= initialPreRepeat;
                long divisor = rounds / repeat;
                totalHeight += (divisor * repeatHeight);
                remainderRounds = rounds % repeat;
                initialHeight = currentHeight;
                states = new LinkedHashMap<>();
                heights = new LinkedHashMap<>();
                continue;
            }
            states.put(key, rockCount);
            heights.put(key, MAX_SIZE - 1 - currentHeight);

            introduceRock(chamber, rock, heightToStartFrom, 2);
            //print(currentHeight);

            int currentRockHeight = heightToStartFrom;
            int currentRockLeftDis = 2;
            while (true) {
                // Try moving to the side
                char movement = jetspray.charAt(currentJetNum);
                currentJetNum = (currentJetNum + 1) % jetspray.length();

                currentRockLeftDis = trySideMove(chamber, rockNum, currentRockLeftDis, currentRockHeight, movement);
                //print(currentHeight);
                int newCurrentRockHeight = tryDownMove(chamber, rockNum, currentRockLeftDis, currentRockHeight);
                //print(currentHeight);
                if (newCurrentRockHeight == currentRockHeight) {
                    // we were not able to move down, stop processing this rock
                    // Update the current height
                    for (int i = newCurrentRockHeight; ; i--) {
                        boolean found = false;
                        for (int j = 0; j < 7; j++) {
                            if (chamber[i][j] == '#') {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            currentHeight = i + 1;
                            break;
                        }
                    }
                    break;
                }
                currentRockHeight = newCurrentRockHeight;
                //print(currentHeight);
            }
            rockCount++;
            rockNum = rockCount % 5;
        }

        long diff = initialHeight - currentHeight;

        System.out.println((totalHeight + diff));
    }

    private static void introduceRock(char[][] chamber, char[][] rock, int heightToStartFrom, int colToStartFrom) {
        for (int i = 0, h = rock.length - 1; i < rock.length; i++, h--) {
            for (int j = 0; j < rock[i].length; j++) {
                if (chamber[heightToStartFrom - h][j + colToStartFrom] == '\0') {
                    chamber[heightToStartFrom - h][j + colToStartFrom] = rock[i][j];
                }
            }
        }
    }

    private static void eraseRock(char[][] chamber, char[][] rock, int heightToStartFrom, int colToStartFrom) {
        for (int i = 0, h = rock.length - 1; i < rock.length; i++, h--) {
            for (int j = 0; j < rock[i].length; j++) {
                if (rock[i][j] == '#') {
                    chamber[heightToStartFrom - h][j + colToStartFrom] = '\0';
                }
            }
        }
    }

    private static int trySideMove(char[][] chamber, int rockNum, int rockLeftDis, int rockHeight, char movement) {
        char[][] rock = rocks.get(rockNum);
        int chamberColumnToCheck = movement == '>' ? rockLeftDis + rock[0].length : rockLeftDis - 1;
        int chamberColumnToCheckNext = movement == '>' ? rockLeftDis + rock[0].length - 1 : rockLeftDis;
        boolean canMove = chamberColumnToCheck >= 0 && chamberColumnToCheck < 7;
        if (!canMove) {
            return rockLeftDis;
        }
        int rockTop = rockHeight - rock.length + 1;
        int rockColumnToCheck = movement == '>' ? rock[0].length - 1 : 0;
        for (int i = 0; i < rock.length; i++) {
            if (rockNum == PLUS_ROCK) {
                if ((rock[i][rockColumnToCheck] == '\0' && chamber[rockTop + i][chamberColumnToCheckNext] == '#')
                        || (chamber[rockTop + i][chamberColumnToCheck] == '#' && rock[i][rockColumnToCheck] == '#')) {
                    canMove = false;
                    break;
                }
            } else if (rockNum == L_ROCK && movement == '<') {
                if ((rock[i][1] == '\0' && chamber[rockTop + i][chamberColumnToCheck + 2] == '#')
                        || (chamber[rockTop + i][chamberColumnToCheck] == '#' && rock[i][rockColumnToCheck] == '#')) {
                    canMove = false;
                    break;
                }
            } else {
                if (chamber[rockTop + i][chamberColumnToCheck] == '#' && rock[i][rockColumnToCheck] == '#') {
                    canMove = false;
                    break;
                }
            }
        }
        if (!canMove) {
            return rockLeftDis;
        }
        int newRockLeftDis = movement == '>' ? rockLeftDis + 1 : rockLeftDis - 1;
        eraseRock(chamber, rock, rockHeight, rockLeftDis);
        introduceRock(chamber, rock, rockHeight, newRockLeftDis);
        return newRockLeftDis;
    }

    private static int tryDownMove(char[][] chamber, int rockNum, int rockLeftDis, int rockHeight) {
        char[][] rock = rocks.get(rockNum);
        boolean canMove = true;
        int newRockHeight = rockHeight + 1;
        for (int j = 0; j < rock[0].length; j++) {
            if (rockNum == PLUS_ROCK) {
                if ((chamber[newRockHeight][j + rockLeftDis] == '#' && rock[rock.length - 1][j] == '#')
                        || (rock[2][j] == '\0' && chamber[rockHeight][j + rockLeftDis] == '#')) {
                    canMove = false;
                    break;
                }
            } else {
                if (chamber[newRockHeight][j + rockLeftDis] == '#' && rock[rock.length - 1][j] == '#') {
                    canMove = false;
                    break;
                }
            }
        }
        if (!canMove) {
            return rockHeight;
        }
        eraseRock(chamber, rock, rockHeight, rockLeftDis);
        introduceRock(chamber, rock, newRockHeight, rockLeftDis);
        return newRockHeight;
    }

    private static void print(int height) {
        for (int i = height - 10; i < (Math.min(height + 20, MAX_SIZE)); i++) {
            System.out.print('|');
            for (int j = 0; j < 7; j++) {
                if (chamber[i][j] == '\0') {
                    System.out.print('.');
                } else {
                    System.out.print(chamber[i][j]);
                }
            }
            System.out.print('|');
            System.out.println();
        }
        System.out.println();
    }
}
