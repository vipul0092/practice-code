package code.vipul.leetcode;

/**
 * Created by vgaur created on 22/08/23
 * https://leetcode.com/problems/excel-sheet-column-title/description/
 */
public class ExcelColumnSheetTitle {

    public static void solve() {
        System.out.println(new ExcelColumnSheetTitle().convertToTitle(702));
    }

    public String convertToTitle(int columnNumber) {
        if (columnNumber < 26) {
            return String.valueOf((char)('A' + columnNumber - 1));
        }
        int[] power = new int[7];
        int[] limits = new int[6];

        power[0] = 1;
        for (int i = 1; i < 7; i++) {
            power[i] = (26 * power[i-1]);
        }
        limits[0] = 26;
        for (int i = 1; i < 6; i++) {
            limits[i] = power[i+1] + limits[i-1];
        }

        int maxpow = -1;
        for (int i = 0; i < 6; i++) {
            if (limits[i] >= columnNumber) {
                maxpow = i;
                break;
            }
        }
        if (maxpow == -1) maxpow = 6;

        StringBuilder sb = new StringBuilder();
        while (columnNumber > 26) {
            int div = columnNumber / power[maxpow];
            columnNumber = columnNumber % power[maxpow];

            if (columnNumber == 0) {
                columnNumber = power[maxpow];
                div--;
            }

            char ch = (char)('A' + div - 1);
            sb.append(ch);
            maxpow--;
        }

        char ch = (char)('A' + columnNumber - 1);
        sb.append(ch);
        return sb.toString();
    }
}
