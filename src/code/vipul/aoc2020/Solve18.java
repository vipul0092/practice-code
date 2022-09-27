package code.vipul.aoc2020;

import java.util.Stack;

import static code.vipul.aoc2020.Inputs2.INPUT_18;

/**
 * https://adventofcode.com/2020/day/18
 */
public class Solve18 {

    private static final String INPUT = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2";

    public static void solve() {
        long sum = solveInternal(INPUT_18,false);

        System.out.println("Answer: " + sum); //131076645626
    }

    public static void solvePart2() {
        long sum = solveInternal(INPUT_18,true);

        System.out.println("Answer: " + sum); //109418509151782
    }


    private static long solveInternal(String input, boolean plusHasPrecedence) {
        Stack<StackMember> mainStack;
        String[] lines = input.split("\n");

        long sum = 0;
        for (String line : lines) {
            mainStack = new Stack<>();
            for (Character ch : line.toCharArray()) {
                if (ch == ' ') continue;
                if (ch == ')') {
                    Stack<StackMember> temp = new Stack<>();
                    char popped = ')';

                    while(popped != '(') {
                        StackMember poppedMember = mainStack.pop();
                        popped = poppedMember.isNumber() ? (char)(poppedMember.number + 48) : poppedMember.character;
                        if (popped != '(' ) {
                            temp.push(poppedMember);
                        }
                    }

                    long evaluation = evaluateStack(temp, plusHasPrecedence);
                    mainStack.push(new StackMember(evaluation));
                } else {
                    mainStack.push(ch >= 48 && ch <= 57 ? new StackMember(ch - 48) : new StackMember(ch));
                }
            }

            long result = evaluateStack(reverse(mainStack), plusHasPrecedence);
            sum += result;
        }

        return sum;
    }

    private static long evaluateStack(Stack<StackMember> stack, boolean plusHasPrecedence) {
        if (stack.size() == 1) {
            return stack.pop().number;
        }
        long prevNum = -1;
        Character prevOp = null;
        Stack<StackMember> secondIteration = new Stack<>();
        while(!stack.isEmpty()) {
            StackMember t = stack.pop();
            if (t.isNumber()) {
                if (prevNum == -1) {
                    prevNum = t.number;
                } else {
                    long curNum = t.number;
                    prevNum = evaluate(prevNum, curNum, prevOp);
                }
            } else {
                if (t.character == '*' && plusHasPrecedence) {
                    secondIteration.push(new StackMember(prevNum));
                    secondIteration.push(new StackMember((Character.valueOf('*'))));
                    prevNum = -1;
                } else {
                    prevOp = t.character;
                }
            }
        }

        if (plusHasPrecedence) {
            secondIteration.push(new StackMember(prevNum));
            prevNum = -1;
            prevOp = null;
        }
        while (!secondIteration.isEmpty()) {
            StackMember t = secondIteration.pop();
            if (t.isNumber()) {
                if (prevNum == -1) {
                    prevNum = t.number;
                } else {
                    long curNum = t.number;
                    prevNum = evaluate(prevNum, curNum, prevOp);
                }
            } else {
                prevOp = t.character;
            }
        }
        return prevNum;
    }

    private static long evaluate(long prevNum, long curNum, Character op) {
        if (op == '*') {
            return prevNum * curNum;
        } else if (op == '+') {
            return prevNum + curNum;
        } else return 0L;
    }

    private static Stack<StackMember> reverse(Stack<StackMember> stack) {
        Stack<StackMember> rev = new Stack<>();
        while (!stack.isEmpty()) {
            rev.push(stack.pop());
        }
        return rev;
    }

    public static class StackMember {
        private Long number;
        private Character character;

        StackMember(long n) {
            this.number = n;
            this.character = null;
        }

        StackMember(Character ch) {
            this.number = null;
            this.character = ch;
        }

        public boolean isNumber() {
            return number != null;
        }

        public String toString() {
            return isNumber() ? String.format("number: %s", number) : String.format("character: %s", character);
        }
    }

}
