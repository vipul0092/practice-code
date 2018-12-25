package code.vipul;

/**
 * Multiply recursively without using * operator
 * Created by vgaur on 24/12/18.
 */
public class RecursiveMultiply {

    public static int multiply(int number, int multiplier) {

        if (multiplier == 1) {
            return number;
        }

        int halfMultiplier = multiplier >> 1;
        boolean isEven = (multiplier & 1) == 0;

        int halfSum = multiply(number, halfMultiplier);
        return isEven ? halfSum + halfSum : halfSum + halfSum + number;
    }

}
