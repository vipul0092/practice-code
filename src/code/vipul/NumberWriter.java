package code.vipul;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vgaur on 02/11/19.
 */
public class NumberWriter {
    private static Map<Integer, String> DigitMap = new HashMap<>();
    private static Map<Integer, String> TensMap = new HashMap<>();
    private static Map<Integer, String> TenToTwentyMap = new HashMap<>();

    static {

        TensMap.put(2, "Twenty");
        TensMap.put(3, "Thirty");
        TensMap.put(4, "Forty");
        TensMap.put(5, "Fifty");
        TensMap.put(6, "Sixty");
        TensMap.put(7, "Seventy");
        TensMap.put(8, "Eighty");
        TensMap.put(9, "Ninety");

        DigitMap.put(1, "One");
        DigitMap.put(2, "Two");
        DigitMap.put(3, "Three");
        DigitMap.put(4, "Four");
        DigitMap.put(5, "Five");
        DigitMap.put(6, "Six");
        DigitMap.put(7, "Seven");
        DigitMap.put(8, "Eight");
        DigitMap.put(9, "Nine");
        TenToTwentyMap.put(10, "Ten");
        TenToTwentyMap.put(11, "Eleven");
        TenToTwentyMap.put(12, "Twelve");
        TenToTwentyMap.put(13, "Thirteen");
        TenToTwentyMap.put(14, "Fourteen");
        TenToTwentyMap.put(15, "Fifteen");
        TenToTwentyMap.put(16, "Sixteen");
        TenToTwentyMap.put(17, "Seventeen");
        TenToTwentyMap.put(18, "Eighteen");
        TenToTwentyMap.put(19, "Nineteen");
    }

    public static String solve(int number) {
        String numberString = lessThanBillion(number);
        System.out.println(number + " -> " + numberString);
        return numberString;
    }

    private static String lessThanBillion(int number) {
        if (number == 0) {
            return "";
        }

        int millions = number / 1000000;

        if (millions == 0) {
            return lessThanMillion(number);
        }
        int thousands = number % 1000000;
        return thousands == 0
                ? String.format("%s Million", lessThanThousand(millions))
                : String.format("%s Million %s", lessThanThousand(millions), lessThanMillion(thousands));
    }

    private static String lessThanMillion(int number) {
        if (number == 0) {
            return "";
        }

        int thousands = number / 1000;

        if (thousands == 0) {
            return lessThanThousand(number);
        }
        int hundreds = number % 1000;
        return hundreds == 0
                ? String.format("%s Thousand", lessThanThousand(thousands))
                : String.format("%s Thousand %s", lessThanThousand(thousands), lessThanThousand(hundreds));
    }

    private static String lessThanThousand(int number) {
        if (number == 0) {
            return "";
        }
        int hundreds = number / 100;

        if (hundreds == 0) {
            return lessThanHundred(number);
        }
        int tens = number % 100;
        return tens == 0
                ? String.format("%s Hundred", lessThanTen(hundreds))
                : String.format("%s Hundred %s", lessThanTen(hundreds), lessThanHundred(tens));
    }

    private static String lessThanHundred(int number) {
        if (number == 0) {
            return "";
        }

        int tens = number / 10;
        if (tens == 0) {
            return lessThanTen(number);
        }

        if (tens > 1) {
            int digit = number % 10;
            return digit == 0
                    ? TensMap.get(tens)
                    : String.format("%s %s", TensMap.get(tens), lessThanTen(digit));
        }

        return TenToTwentyMap.get(number);
    }

    private static String lessThanTen(int number) {
        if (number == 0) {
            return "";
        }
        return DigitMap.get(number);
    }


}
