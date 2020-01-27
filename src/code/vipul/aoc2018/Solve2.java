package code.vipul.aoc2018;

/**
 * https://adventofcode.com/2018/day/2
 */
public class Solve2 {

    private static String input = Inputs.DAY2;

    public static void solve() {
        int twos = 0;
        int threes = 0;

        for (String box : input.split("\n")) {
            int[] charCounts = new int[26];

            for (char ch : box.toCharArray()) {
                charCounts[ch - 'a']++;
            }
            boolean hasTwo = false, hasThree = false;

            for (int i = 0; i < 26; i++) {
                if (!hasTwo && charCounts[i] == 2) {
                    hasTwo = true;
                    twos++;
                }

                if (!hasThree && charCounts[i] == 3) {
                    hasThree = true;
                    threes++;
                }
            }
        }

        System.out.println("Answer: " + (twos * threes)); // 4940
    }

    public static void solvePart2() {
        String[] boxes = input.split("\n");
        int foundPos = -1;

        int i, j;
        for (i = 0; i < boxes.length - 1; i++) {
            for (j = i + 1; j < boxes.length; j++) {
                int differences = 0;
                int differingPos = -1;
                for (int k = 0; k < boxes[i].length(); k++) {
                    if (boxes[i].charAt(k) != boxes[j].charAt(k)) {
                        differences++;
                        differingPos = k;
                    }
                    if (differences > 1) {
                        break;
                    }
                }

                if (differences == 1) {
                    foundPos = differingPos;
                    break;
                }
            }

            if (foundPos != -1) {
                break;
            }
        }

        String answer = boxes[i].substring(0, foundPos) + boxes[i].substring(foundPos + 1);
        System.out.println("Answer: " + answer); // wrziyfdmlumeqvaatbiosngkc
    }
}
