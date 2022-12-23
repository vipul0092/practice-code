package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 21/12/22
 * https://adventofcode.com/2022/day/21
 */
public class Solve21 {

    private static final String INPUT = "root: pppw + sjmn\n" +
            "dbpl: 5\n" +
            "cczh: sllz + lgvd\n" +
            "zczc: 2\n" +
            "ptdq: humn - dvpt\n" +
            "dvpt: 3\n" +
            "lfqf: 4\n" +
            "humn: 5\n" +
            "ljgn: 2\n" +
            "sjmn: drzm * dbpl\n" +
            "sllz: 4\n" +
            "pppw: cczh / lfqf\n" +
            "lgvd: ljgn * ptdq\n" +
            "drzm: hmdt - zczc\n" +
            "hmdt: 32";

    private static final String ROOT = "root";
    private static final String US = "humn";

    private static Map<String, Monkey> monkeys;
    private static Set<String> marked;

    public static void solve() {
        parse(Inputs.INPUT_21);
        long ans = dive(monkeys.get(ROOT));
        System.out.println(ans);
    }


    public static void solvePart2() {
        parse(Inputs.INPUT_21);
        marked = new HashSet<>();
        Monkey root = monkeys.get(ROOT);
        String rootM1 = root.monkey1;
        String rootM2 = root.monkey2;

        boolean found1 = findAndMark(rootM1);
        long ans;
        if (found1) {
            ans = calc(rootM2, rootM1);
        } else {
            ans = calc(rootM1, rootM2);
        }
        System.out.println(ans);
    }

    private static long calc(String unmarkedChild, String markedChild) {
        long requiredValue = dive(monkeys.get(unmarkedChild));
        Monkey m = monkeys.get(markedChild);
        long current = requiredValue;

        while(!m.hasHumn()) {
            current = evaluate(m, current);
            m = monkeys.get(marked.contains(m.monkey1) ? m.monkey1 : m.monkey2);
        }
        current = evaluate(m, current);
        return current;
    }


    private static long evaluate(Monkey m, long current) {
        Monkey currentUnmarked = monkeys.get(marked.contains(m.monkey1) ? m.monkey2 : m.monkey1);
        Monkey currentMarked = monkeys.get(marked.contains(m.monkey1) ? m.monkey1 : m.monkey2);
        long num = dive(currentUnmarked);
        boolean isSecondMarked = m.monkey2.equals(currentMarked.name);

        switch (m.operation) {
            case '/': return isSecondMarked ? num / current : num * current;
            case '-': return isSecondMarked ? num - current : num + current;
            case '*': return current / num;
            case '+': return current - num;
            default: throw new RuntimeException();
        }
    }

    private static boolean findAndMark(String m) {
        if (m.equals(US)) {
            marked.add(m);
            return true;
        }
        Monkey monkey = monkeys.get(m);
        if (monkey.monkey1 == null) {
            return false;
        }

        boolean m1 = findAndMark(monkey.monkey1);
        boolean m2 = findAndMark(monkey.monkey2);

        if (m1 || m2) {
            marked.add(m);
            return true;
        }
        return false;
    }

    private static void parse(String input) {
        List<String> inputs = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        monkeys = new LinkedHashMap<>();

        for (String in : inputs) {
            String[] parts = in.split(": ");
            String m = parts[0];

            if (parts[1].charAt(0) >= '0' && parts[1].charAt(0) <= '9') {
                Monkey monkey = new Monkey(m, Long.parseLong(parts[1]));
                monkeys.put(m, monkey);
            } else {
                String m1 = parts[1].substring(0, 4);
                String m2 = parts[1].substring(7, 11);
                char opr = parts[1].charAt(5);
                Monkey monkey = new Monkey(m, m1, m2, opr);
                monkeys.put(m, monkey);
            }
        }
    }

    private static long dive(Monkey monkey) {
        if (monkey.monkey1 == null) {
            return monkey.num;
        }

        char opr = monkey.operation;
        long ans = -1;
        switch (opr) {
            case '*': {
                ans = dive(monkeys.get(monkey.monkey1)) *  dive(monkeys.get(monkey.monkey2));
                break;
            }
            case '+': {
                ans = dive(monkeys.get(monkey.monkey1)) +  dive(monkeys.get(monkey.monkey2));
                break;
            }
            case '/': {
                ans = dive(monkeys.get(monkey.monkey1)) /  dive(monkeys.get(monkey.monkey2));
                break;
            }
            case '-': {
                ans = dive(monkeys.get(monkey.monkey1)) -  dive(monkeys.get(monkey.monkey2));
                break;
            }
            default:
                throw new RuntimeException("not supported");
        }
        return ans;
    }

    private static final class Monkey {
        private String name;
        private String monkey1;
        private String monkey2;
        private char operation;
        private Long num;

        public Monkey(String _name, long n) {
            this.name = _name;
            this.num = n;
        }

        public Monkey(String _name, String m1, String m2, char opr) {
            this.name = _name;
            this.monkey1 = m1;
            this.monkey2 = m2;
            this.operation = opr;
        }

        public boolean hasHumn() {
            return US.equals(monkey1) || US.equals(monkey2);
        }
    }
}
