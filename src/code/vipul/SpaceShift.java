package code.vipul;

/**
 * Created by vgaur on 31/10/19.
 */
public class SpaceShift {

    // O(length)
    public static void solve(String currentStr, int length) {
        char[] arr = currentStr.toCharArray();

        int currentShift = 0;
        int[] elementShifts = new int[length];

        for (int i = 0; i < length; i++) {
            if (arr[i] == ' ') {
                elementShifts[i] = -1;
                currentShift++;
            } else {
                elementShifts[i] = currentShift * 2;
            }
        }

        int lastWorkingIndex = -1;
        for (int i = length - 1; i >= 0; i--) {
            if (elementShifts[i] != -1) {
                lastWorkingIndex = i + elementShifts[i];
                arr[lastWorkingIndex] = arr[i];
            } else {
                arr[lastWorkingIndex - 3] = '%';
                arr[lastWorkingIndex - 2] = '2';
                arr[lastWorkingIndex - 1] = '0';
            }
        }

        String result = String.valueOf(arr);
        System.out.println(result);
        assert result.equals("Mr%20John%20Smith%20Clark");
    }
}
