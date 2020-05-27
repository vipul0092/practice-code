package code.vipul.google;

/**
 * Fuel Injection Perfection
 * =========================
 *
 * Commander Lambda has asked for your help to refine the automatic quantum antimatter fuel injection system for her LAMBCHOP doomsday device. It's a great chance for you to get a closer look at the LAMBCHOP - and maybe sneak in a bit of sabotage while you're at it - so you took the job gladly.
 *
 * Quantum antimatter fuel comes in small pellets, which is convenient since the many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a time. However, minions dump pellets in bulk into the fuel intake. You need to figure out the most efficient way to sort and shift the pellets down to a single pellet at a time.
 *
 * The fuel control mechanisms have three operations:
 *
 * 1) Add one fuel pellet
 * 2) Remove one fuel pellet
 * 3) Divide the entire group of fuel pellets by 2 (due to the destructive energy released when a quantum antimatter pellet is cut in half, the safety controls will only allow this to happen if there is an even number of pellets)
 *
 * Write a function called solution(n) which takes a positive integer as a string and returns the minimum number of operations needed to transform the number of pellets to 1. The fuel intake control panel can only display a number up to 309 digits long, so there won't ever be more pellets than you can express in that many digits.
 *
 * For example:
 * solution(4) returns 2: 4 -> 2 -> 1
 * solution(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1
 * solution(6) returns 3: 6 -> 3 -> 2 -> 1
 */
//AC
public class L3P3 {

    public static void solve() {
        System.out.println(solution("15"));
    }

    public static int solution(String x) {
        BigInteger integer = new BigInteger(x);

        int count = 0;
        while (!integer.isOne()) {
            if (integer.isEven()) {
                integer.divideBy2();
            } else if (integer.isThree()) {
                integer.subtract1();
            } else {
                BigInteger i1 = integer.copy();
                BigInteger i2 = integer.copy();

                i1.subtract1();
                i1.divideBy2();

                i2.add1();
                i2.divideBy2();

                if (i1.isEven()) {
                    integer.subtract1();
                } else {
                    integer.add1();
                }
            }
            integer.print();
            count++;
        }
        return count;
    }

    private static final class BigInteger {

        private final int[] digits;
        private int hv = -1; //highest value index

        private BigInteger(String n) {
            digits = new int[400];
            hv = n.length() - 1;

            char[] chars = n.toCharArray();
            for (int i = hv; i >= 0; i--) {
                digits[hv - i] = chars[i] - 48;
            }
        }

        private BigInteger(BigInteger b) {
            digits = new int[b.digits.length];
            hv = b.hv;
            for (int i = 0; i < digits.length; i++) {
                digits[i] = b.digits[i];
            }
        }

        void subtract1() {
            for (int i = 0; i <= hv; i++) {
                if (digits[i] != 0) {
                    digits[i] = digits[i] - 1;
                    break;
                } else {
                    digits[i] = 9;
                }
            }
            normalize();
        }

        BigInteger copy() {
            return new BigInteger(this);
        }

        void add1() {
            boolean areAll9s = true;
            for (int i = 0; i <= hv; i++) {
                if (digits[i] != 9) {
                    areAll9s = false;
                    digits[i] = digits[i] + 1;
                    break;
                } else {
                    digits[i] = 0;
                }
            }
            if (areAll9s) {
                hv++;
                digits[hv] = 1;
            }
            normalize();
        }

        void divideBy2() {
            int current = 0;
            for (int i = hv; i >= 0; i--) {
                int dividend = current + digits[i];
                int remainder = dividend % 2;
                digits[i] = dividend / 2;
                if (remainder != 0) {
                    current = remainder * 10;
                } else {
                    current = 0;
                }
            }
            normalize();
        }

        private void normalize() {
            if (hv > 0 && digits[hv] == 0) {
                hv--;
            }
        }

        boolean isEven() {
            return digits[0] % 2 == 0;
        }

        boolean isOne() {
            return hv == 0 && digits[0] == 1;
        }

        boolean isThree() {
            return hv == 0 && digits[0] == 3;
        }

        void print() {
            for (int i = hv; i >= 0; i--) {
                System.out.print(digits[i]);
            }
            System.out.println();
        }
    }
}
