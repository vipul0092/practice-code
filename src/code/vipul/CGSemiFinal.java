package code.vipul;

import java.util.Scanner;

/**
 * Created by vgaur created on 05/07/20
 * The country is fighting with the Corona pandemic and Corona Warriors are at front dealing with all issues. There are P number of patients and D number of doctors available for the treatment. PM Modi knows the fight is long so he wants to make sure the team of doctors is applying the minimum efforts. His slogan is - ”Minimum Efforts, Maximum Results”.
 *
 *
 *
 * Image source: Google
 *
 *
 * For smooth functioning, PM Modi has formed a task force which will make sure the minimum efforts are used. The doctors know the efforts they will have to apply to treat a patient and have shared the data with the task force. The task force will assign the patients to the doctors making sure the minimum efforts are required. There are two main conditions which will help in minimising the total efforts and should be met:
 *
 *
 *     Doctors should treat consecutive patients. This is a request made by doctors to the task force and has to be followed.
 *
 *     The doctors should be chosen to treat patients such that the total efforts are minimum.
 *
 *
 * Example:
 *
 * Number of patients, P = 4
 *
 * Number of doctors, D = 2
 *
 *
 * For Doctor 1,
 * 2 2 2 2
 *
 * For Doctor 2,
 * 3 1 2 3
 *
 *
 * While making assignments, the two stated conditions should be met.
 *
 *
 * Case 1: All the patients are assigned to Doctor 1
 *
 * Efforts = 2 + 2 + 2 + 2 = 8
 *
 *
 * Case 2: All the patients are assigned to Doctor 2
 *
 * Efforts = 3 + 1 + 2 + 3 = 9
 *
 *
 * This case is nullified as the efforts made in Case 1 are less than the efforts made in Case 2.
 *
 *
 * Case 3: Patient P1 is assigned to Doctor 1 and patients P2, P3 and P4 are assigned to Doctor 2
 *
 * Efforts = 2 + 1 + 2 + 3 = 8
 *
 *
 * Case 4: Patients P1, P2, P3 are assigned to Doctor 2 and patient P4 is assigned to Doctor 1
 *
 * Efforts = 3 + 1 + 2 + 2 = 8
 *
 *
 * Case 5: Patients P1 and P2 are assigned to Doctor 2 and patients P3 and P4 are assigned to Doctor 1
 *
 * Efforts = 3 + 1 + 2 + 2 = 8
 *
 *
 * Any other assignments of patients to Doctors will either result in more efforts required or violation of the Conditions.
 *
 *
 * Thus, the minimum efforts required to treat all patients = 8.
 *
 *
 * The task force has assigned the patients to doctors and have calculated the efforts required. They want to cross-check if the efforts they have calculated are minimum or they need to make changes into their assignment. Can you provide the minimum efforts required so that they can validate the assignment?
 *
 *
 * Input Format
 *
 * The first line of input consists of two space-separated integers, number of patients, P, and number of Doctors, D.
 * Next D lines each consists of P space-separated efforts required by the doctor. Di line represents the efforts required by the ith doctor to treat P patients respectively.
 *
 * Constraints
 * 1<= D <=10
 * 0<= Efforts <=1000
 * 1<= P <=20
 *
 *
 * Output Format
 *
 * Print the minimum efforts required to treat the P patients.  Note: There is a new line at the end of the output.
9 10
4 7 5 10 2 8 1 5 3
8 8 6 8 4 2 3 1 9
5 4 4 5 9 9 6 6 9
2 3 8 2 10 10 9 2 7
3 1 3 1 10 6 1 1 2
3 6 4 1 7 9 9 5 8
4 7 6 4 8 3 2 1 6
10 5 1 7 4 9 9 7 6
9 3 9 9 6 8 1 8 5
3 4 3 10 7 9 4 3 9
 *
 */
public class CGSemiFinal {

    private static int answer = -1;
    private static int[][] perdoc;
    private static int p;
    private static int d;

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] dp = s.split(" ");
        p = Integer.parseInt(dp[0]);
        d = Integer.parseInt(dp[1]);

        perdoc = new int[d][p];

        for (int i=0; i < d; i++) {
            String per = scanner.nextLine();
            String[] values = per.split(" ");
            for (int j = 0; j < p; j++) {
                perdoc[i][j] = Integer.parseInt(values[j]);
            }
        }

        for (int i = 0; i < d; i++) {
            recurse(0, i, 0, 0);
        }

        System.out.println(answer);
    }

    private static void recurse(int start, int doc, long docbitmap, int cursum) {
        if (cursum >= answer && answer != -1) {
            return;
        }

        if (start == p) {
            answer = answer == -1 ? cursum : Math.min(cursum, answer);
            return;
        }

        boolean docFound = false;

        for (int i = 0; i < d; i++) {
            if (!isBitSet(docbitmap, i)) {
                int sum = cursum;
                docFound = true;
                boolean isExtendingCurrent = i == doc;
                sum += perdoc[i][start];
                recurse(start + 1, i, isExtendingCurrent ? docbitmap : setBit(docbitmap, doc), sum);
            }
        }

        // This means the last doctor has to attend to all the patients that come afterwards
        if (!docFound) {
            for (int i = start; i < p; i++) {
                cursum += perdoc[doc][i];
            }
            recurse(p, doc, docbitmap, cursum);
        }
    }


    private static boolean isBitSet(long bitSet, int bitPos) {
        return (bitSet & (1L << bitPos)) != 0;
    }

    private static long setBit(long bitSet, int bitPos) {
        return bitSet | (1L << bitPos);
    }

}
