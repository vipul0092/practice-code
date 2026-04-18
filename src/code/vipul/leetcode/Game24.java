package code.vipul.leetcode;

import java.util.Stack;

/**
 * Created by vgaur created on 16/04/26
 * https://leetcode.com/problems/24-game/
 * <p>
 * Hard
 * Backtracking
 */
public class Game24 {

    public static void solve() {
        System.out.println(new Game24().judgePoint24(new int[]{8, 1, 6, 6})); // true (6 / (1 - (6 / 8)))
        System.out.println(new Game24().judgePoint24(new int[]{1, 9, 1, 2})); // true (1 + 2)*(9 - 1)
        System.out.println(new Game24().judgePoint24(new int[]{3, 3, 8, 8})); // true (8 ÷ (3 - (8 ÷ 3)))
        System.out.println(new Game24().judgePoint24(new int[]{1, 2, 1, 2})); // false
    }

    private final char[] ops = new char[]{'+', '-', '/', '*'};

    public boolean judgePoint24(int[] cards) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < cards.length; i++) {
            int card = cards[i];
            stack.push((char) ('0' + card));
            cards[i] = 0;

            boolean res = solve(stack, cards);
            if (res) return true;

            cards[i] = card;
            stack.pop();
        }
        return false;
    }

    private boolean solve(Stack<Character> stack, int[] cards) {

        boolean all = true;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == 0) continue;
            all = false;
            int card = cards[i];
            for (char op : ops) {
                stack.push(op);
                stack.push((char) ('0' + card));
                cards[i] = 0;

                boolean res = solve(stack, cards);

                cards[i] = card;
                stack.pop();
                stack.pop();

                if (res) return true;
            }
        }

        if (all) {
            return eval(stack);
        }
        return false;
    }

    private boolean eval(Stack<Character> stack) {
        char[] op = new char[3];
        Fraction[] nums = new Fraction[4];

        int oi = 0, ni = 0;

        boolean number = true;
        for (char ch : stack) {
            if (number) {
                nums[ni++] = new Fraction(ch - '0', 1);
            } else {
                op[oi++] = ch;
            }
            number = !number;
        }

        Fraction firsttwo = operate(nums[0], nums[1], op[0]);
        Fraction midtwo = operate(nums[1], nums[2], op[1]);
        Fraction lasttwo = operate(nums[2], nums[3], op[2]);


        // first two - last two
        Fraction res = operate(firsttwo, lasttwo, op[1]);
        if (res.is24()) return true;

        // (first - midtwo) - last
        res = operate(operate(nums[0], midtwo, op[0]), nums[3], op[2]);
        if (res.is24()) return true;

        // first - (midtwo - last)
        res = operate(nums[0], operate(midtwo, nums[3], op[2]), op[0]);
        if (res.is24()) return true;

        // (firstwo - seclast) - last
        res = operate(operate(firsttwo, nums[2], op[1]), nums[3], op[2]);
        if (res.is24()) return true;

        // firstwo - (seclast - last) => first two - last two, already done

        // first - (second - lasttwo)
        res = operate(nums[0], operate(nums[1], lasttwo, op[1]), op[0]);
        if (res.is24()) return true;

        // (first - second) - lasttwo => first two - last two, already done

        return false;
    }

    private static Fraction operate(Fraction f1, Fraction f2, char op) {
        return switch (op) {
            case '+' -> new Fraction(f1.n * f2.d + f2.n * f1.d, f1.d * f2.d);
            case '-' -> new Fraction(f1.n * f2.d - f2.n * f1.d, f1.d * f2.d);
            case '*' -> new Fraction(f1.n * f2.n, f1.d * f2.d);
            // `/`
            default -> new Fraction(f1.n * f2.d, f1.d * f2.n);
        };
    }

    record Fraction(int n, int d) {
        boolean is24() {
            return d != 0 && n / d == 24 && n % d == 0;
        }
    }
}
