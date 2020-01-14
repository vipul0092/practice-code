package code.vipul.aoc2019;

import code.vipul.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * https://adventofcode.com/2019/day/16
 */
public class Solve16 {

    static String input = "59719896749391372935980241840868095901909650477974922926863874668817926756504816327136638260644919270589305499504699701736406883012172909202912675166762841246709052187371758225695359676410279518694947094323466110604412184843328145082858383186144864220867912457193726817225273989002642178918584132902751560672461100948770988856677526693132615515437829437045916042287792937505148994701494994595404345537543400830028374701775936185956190469052693669806665481610052844626982468241111349622754998877546914382626821708059755592288986918651172943415960912020715327234415148476336205299713749014282618817141515873262264265988745414393060010837408970796104077";
    private static final int MAX_ITERATIONS = 100;

    private static final int _10_6 = 1000000;

    public static void solve() {
        // 0, 1, 0 -1
        int times = input.length();
        List<List<Pair<Integer, Integer>>> sparseMatrix = getSparseMatrix(times);
        int[] arr = getParsedArray();

        int iterations = 0;

        while (iterations++ < MAX_ITERATIONS) {
            int[] newArray = new int[input.length()];

            for (int i = 0; i < newArray.length; i++) {

                var sparseRow = sparseMatrix.get(i);
                int result = 0;
                for (Pair<Integer, Integer> multiplierEntry : sparseRow) {
                    int index = multiplierEntry.left();
                    int multiplier = multiplierEntry.right();

                    result += (arr[index] * multiplier);
                }
                result = Math.abs(result);
                newArray[i] = result % 10;
            }
            arr = newArray;
        }

        StringBuilder ans = new StringBuilder();
        int ctr = 0;
        while (ctr++ < 8) {
            ans.append(arr[ctr - 1]);
        }

        System.out.println("Answer: " + ans.toString()); // 32002835
    }

    public static void solvePart2() {

        //input = "03036732577212944063491565474664";
        int totalLength = input.length() * 10000;
        int[] arr = getParsedArray();
        int ctr = 0;
        int offset = 0;
        int m = _10_6;
        while (ctr++ < 7) {
            offset += (m * arr[ctr - 1]);
            m /= 10;
        }

        int countToCalc = totalLength - offset;

        int[] offsetArray = new int[countToCalc];
        int offsetArrayCtr = countToCalc - 1;
        int arrCounter = arr.length - 1;

        while (offsetArrayCtr >= 0) {
            offsetArray[offsetArrayCtr] = arr[arrCounter];
            if (arrCounter == 0) {
                arrCounter = arr.length;
            }
            offsetArrayCtr--;
            arrCounter--;
        }

        int iterations = 0;
        while (iterations++ < MAX_ITERATIONS) {
            int offsetIndex = countToCalc - 1;
            int[] tempOffsetArr = new int[countToCalc];

            tempOffsetArr[offsetIndex] = offsetArray[offsetIndex];
            int sumUntilNow = offsetArray[offsetIndex];
            offsetIndex--;

            while (offsetIndex >= 0) {
                sumUntilNow += offsetArray[offsetIndex];
                tempOffsetArr[offsetIndex] = sumUntilNow % 10;
                offsetIndex--;
            }
            offsetArray = tempOffsetArr;
        }

        StringBuilder ans = new StringBuilder();
        ctr = 0;
        while (ctr++ < 8) {
            ans.append(offsetArray[ctr - 1]);
        }

        System.out.println("Answer: " + ans.toString()); // 69732268
    }

    private static int[] getParsedArray() {
        int[] arr = new int[input.length()];
        int ctr = 0;

        for (char ch : input.toCharArray()) {
            arr[ctr++] = ch - 48;
        }
        return arr;
    }

    private static List<List<Pair<Integer, Integer>>> getSparseMatrix(int times) {
        List<List<Pair<Integer, Integer>>> sparseMatrix = new ArrayList<>();
        int counter = 1;
        // Precompute the sparse matrix of 1s and -1s
        while (counter <= times) {
            int start = counter - 1;
            List<Pair<Integer, Integer>> row = new ArrayList<>();

            int digitTimes = 0;
            while (digitTimes++ < counter) {
                int tempCtr = start + digitTimes - 1;
                while (tempCtr < times) { // coordinates of 1s
                    row.add(Pair.of(tempCtr, 1));
                    tempCtr += (counter * 4);
                }
            }

            digitTimes = 0;
            start = (3 * counter) - 1;
            while (digitTimes++ < counter) {
                int tempCtr = start + digitTimes - 1;
                while (tempCtr < times) { // coordinates of -1s
                    row.add(Pair.of(tempCtr, -1));
                    tempCtr += (counter * 4);
                }
            }
            sparseMatrix.add(row);
            counter++;
        }

        return sparseMatrix;
    }
}
