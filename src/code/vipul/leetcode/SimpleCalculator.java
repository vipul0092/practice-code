package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 30/07/23
 */
public class SimpleCalculator {

    public static void solve() {
        System.out.println(new SimpleCalculator().calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(new SimpleCalculator().calculate(" 2   - 1 +   2"));
        System.out.println(new SimpleCalculator().calculate("-(-2)+4"));
    }

    public int calculate(String s) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                StringBuilder sb = new StringBuilder();
                while(ch >= '0' && ch <= '9') {
                    sb.append(ch);
                    i++;
                    if (i == s.length()) {
                        break;
                    }
                    ch = s.charAt(i);
                }
                if (i != s.length()) i--;
                evaluate(stack, sb.toString());
            } else if (ch == '-' || ch == '+' || ch == '(') {
                stack.push(String.valueOf(ch));
            } else if (ch == ')') {
                String num = stack.pop();
                while(stack.peek().charAt(0) != '(') {
                    evaluate(stack, num);
                    num = stack.pop();
                }
                stack.pop();
                if (!stack.isEmpty() && stack.peek().charAt(0) == '-') {
                    evaluate(stack, num);
                } else {
                    stack.push(num);
                }
            }
        }

        while(stack.size() != 1) {
            String num = stack.pop();
            evaluate(stack, num);
        }

        return Integer.parseInt(stack.pop());
    }

    private void evaluate(Stack<String> stack, String num) {
        if (stack.isEmpty()) {
            stack.push(num);
        } else if (stack.peek().charAt(0) == '+') {
            stack.pop(); // <- the sign goes
            String num2 = stack.pop();
            String res =
                    String.valueOf(Integer.parseInt(num) + Integer.parseInt(num2));
            stack.push(res);
        } else if (stack.peek().charAt(0) == '-') {
            String popped = stack.pop(); // <- the sign goes
            boolean isNum = isNumAtTop(stack);
            String res = isNum
                    ? String.valueOf(Integer.parseInt(stack.pop()) - Integer.parseInt(num))
                    : String.valueOf(-1 * Integer.parseInt(num));
            stack.push(res);
        } else {
            stack.push(num);
        }
    }

    private boolean isNumAtTop(Stack<String> stack) {
        if (stack.isEmpty()) {
            return false;
        }
        String top = stack.peek();
        return top.charAt(top.length() - 1) >= '0'
                && top.charAt(top.length() - 1) <= '9';
    }
}
