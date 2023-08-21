package code.vipul.leetcode;

/**
 * Created by vgaur created on 15/08/23
 * https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/description/
 *
 * Hard disguised as a Medium
 */
public class MinimumFlipsToMakeBinaryStringAlternating {

    public static void solve() {
        System.out.println(new MinimumFlipsToMakeBinaryStringAlternating().minFlips("0001100010101000111101000110101111000000101100000001001")); // Answer = 22
    }

    public int minFlips(String s) {
        String str = s + s;
        int k = s.length();
        boolean isLenEven = k%2 == 0;
        int target = k / 2;

        int even = 0, odd = 0;
        int i = 0, j = 0;

        int min = Integer.MAX_VALUE;;
        System.out.println("k = " + k);

        while (j < str.length()) {
            char curr = str.charAt(j);
            if (j - i + 1 < k) {
                if (j%2 == 0 && curr == '1') even++;
                else if (j%2 == 1 && curr == '1') odd++;
                j++;
            } else {
                if (isLenEven && curr == '1') odd++;
                else if (!isLenEven && curr == '1') even++;
                int reqd;
                //Calculate a solution
                if (odd < even) {
                    if (isLenEven) {
                        reqd = odd + (target - even); // odd -> 0, even to full 1
                    } else {
                        reqd = odd + (target + 1 - even);
                    }
                } else {
                    reqd = even + (target - odd);
                }
                if (min > reqd) {
                    min = reqd;
                }
                if (min == 0) {
                    // because you cant go below zero
                    break;
                }


                char remove = str.charAt(i);
                if (remove == '1') even--;
                int t = even; even = odd; odd = t;
                j++;
                i++;
            }
        }
        return min;
    }
}
