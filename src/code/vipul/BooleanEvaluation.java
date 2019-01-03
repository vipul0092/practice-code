package code.vipul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vgaur on 27/12/18.
 */
public class BooleanEvaluation {

    static Map<Expression, Integer> exprCache = new HashMap<>();

    //Correct
    static int countEval(String s, boolean result) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return (s.charAt(0) == '1') == result ? 1 : 0;

        int ways = 0;
        for (int i = 1; i < s.length(); i += 2) {
            char c = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1, s.length());
            // Evaluate each side for each result.
            int leftTrue = countEval(left, true);
            int leftFalse = countEval(left, false);
            int rightTrue = countEval(right, true);
            int rightFalse = countEval(right, false);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);
            int totalTrue = 0;
            if (c == '^') { // required: one true and one false
                totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
            } else if (c == '&') {//required: both true
                totalTrue = leftTrue * rightTrue;
            } else if (c == '|') { // required: anything but both false
                totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
            }
            int subways = result ? totalTrue : total - totalTrue;
            ways += subways;
        }
        return ways;
    }

    //Incorrect
    static void solve(String expr, boolean required) {

        Expression expression = Expression.parseString(expr);
        expression.print();
        int answers = reduce(expression, (byte) (required ? 1 : 0));

        System.out.println("Answers: " + answers);
    }

    private static int reduce(Expression expr, byte required) {
        expr.print();
        if (expr.getExprLength() == 1) {
           return expr.getBitAtPos(0) == required ? 1 : 0;
        }

        int idx = 0; int answers = 0;

        while (idx < expr.operatorCount()) {
            Expression reduced = expr.mergeBitsAtPos(idx);
            answers += reduce(reduced, required);
            idx++;
        }
        return answers;
    }

    static class Expression {
        final List<Byte> bits;
        final List<Operator> operators;
        final int hash;

        Expression(List<Byte> bits, List<Operator> operators) {
            this.bits = bits;
            this.operators = operators;
            int hash = 5381;
            hash += (hash << 5) + bits.hashCode();
            hash += (hash << 5) + operators.hashCode();
            this.hash = hash;
        }

        void print() {
            for(int idx = 0, i=0, j=0; i < bits.size();) {
                if ((idx & 1) == 0) {
                    System.out.print(bits.get(i));
                    i++; idx++;
                } else {
                    System.out.print(operators.get(j).getRepresenation());
                    j++; idx++;
                }
            }
            System.out.println();
        }

        public int hashCode() {
            return hash;
        }

        static Expression parseString(String expr) {
            List<Byte> bits = new ArrayList<>();
            List<Operator> operators = new ArrayList<>();

            for (char c : expr.toCharArray()) {
                switch (c) {
                    case '1':
                        bits.add((byte)1);
                        break;
                    case '0':
                        bits.add((byte)0);
                        break;
                    case '^':
                        operators.add(Operator.XOR);
                        break;
                    case '|':
                        operators.add(Operator.OR);
                        break;
                    case '&':
                        operators.add(Operator.AND);
                        break;
                    default:
                        throw new RuntimeException("Unsupported character " + c);
                }
            }

            return new Expression(bits, operators);
        }

        int getExprLength() {
            return bits.size();
        }

        int operatorCount() {
            return operators.size();
        }

        byte getBitAtPos(int pos) {
            return bits.get(pos);
        }

        Expression mergeBitsAtPos(int pos) {
            int idx = 0;
            List<Byte> newBits = new ArrayList<>();

            while (idx < getExprLength()) {
                //Merge bits at idx and idx + 1
                if (idx == pos) {
                    newBits.add(operators.get(idx).evaluate(bits.get(idx), bits.get(idx+1)));
                    idx +=2;
                } else { //Simply copy over
                    newBits.add(bits.get(idx));
                    idx++;
                }
            }

            List<Operator> newOperators = new ArrayList<>();
            newOperators.addAll(operators);
            newOperators.remove(pos);

            return new Expression(newBits, newOperators);
        }
    }

    static enum Operator {
        AND("&"),
        OR("|"),
        XOR("^");

        private final String represenation;

        public String getRepresenation() {
            return represenation;
        }

        Operator(String r) {
            this.represenation = r;
        }

        byte evaluate(byte b1, byte b2) {
            switch (this) {
                case AND:
                    return (byte) (b1 & b2);
                case OR:
                    return (byte) (b1 | b2);
                case XOR:
                    return (byte) (b1 ^ b2);
                default:
                    throw new RuntimeException("Operator not supported");
            }
        }
    }
}
