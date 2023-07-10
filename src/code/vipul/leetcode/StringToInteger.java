package code.vipul.leetcode;

/**
 * Created by vgaur created on 30/06/23
 */
public class StringToInteger {

    public static void solve() {
        System.out.println(new StringToInteger().myAtoi("9223372036854775808"));
    }

    public int myAtoi(String s) {
        long lowerLimit = -1 * (1L << 31);
        long upperLimit = (1L << 31) - 1;

        boolean isNegative = false;
        boolean hasSign = false;
        boolean digitDetected = false;
        long number = 0;

        for (char ch : s.toCharArray()) {
            if (ch == ' ' && !digitDetected && !hasSign) {
                // space is expected
            } else if ((ch == '-' || ch == '+') && !hasSign && !digitDetected)  {
                isNegative = ch == '-';
                hasSign = true;
            } else if (ch >= '0' && ch <= '9') {
                digitDetected = true;
                long digit = ch - 48;
                number = (number * 10) + digit;

                long n = number;
                if (isNegative) {
                    n *= -1;
                }

                if (n < lowerLimit) {
                    break;
                } else if (n > upperLimit) {
                    break;
                }

            } else {
                break;
            }
        }

        if (isNegative) {
            number *= -1;
        }

        if (number < lowerLimit) {
            number = lowerLimit;
        } else if (number > upperLimit) {
            number = upperLimit;
        }

        return (int) number;
    }
}
