package code.vipul.aoc2024;

import java.util.*;

import code.vipul.Pair;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2024/day/24
 */
public class Day24 {

    enum OP {
        OR, AND, XOR;

        static OP parse(String str) {
            return switch (str) {
                case "OR" -> OR;
                case "AND" -> AND;
                case "XOR" -> XOR;
                default -> throw new RuntimeException();
            };
        }
    }

    record LHS(String op1, String op2, OP op){}

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day24.class, false);

        int i;
        Map<String, Integer> values = new LinkedHashMap<>();
        for (i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) {
                i++;
                break;
            }
            var parts = line.split(": ");
            values.put(parts[0], Integer.parseInt(parts[1]));
        }

        Map<String, LHS> operations = new LinkedHashMap<>();
        Map<String, String> map = new HashMap<>();
        Map<String, String> revMap = new HashMap<>();
        TreeSet<String> zouts = new TreeSet<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i);
            var parts = line.split(" -> ");
            var lhsparts = parts[0].split(" ");
            operations.put(parts[1], new LHS(lhsparts[0], lhsparts[2], OP.parse(lhsparts[1])));
            if (parts[1].charAt(0) == 'z') {
                zouts.add(parts[1]);
            }
            revMap.put(parts[0], parts[1]);
            map.put(parts[1], parts[0]);
        }


        long res = 0; long pow2 = 1;
        for (String zout : zouts) {
            res += (pow2 * operate(zout, operations, values));
            pow2 *= 2;
        }

        System.out.println("Part 1: " + res); // 61495910098126

        String prevCarry = "";
        // gdd && z05
        revMap.put("y05 AND x05", "gdd");
        revMap.put("knc XOR wcq", "z05");
        // cwt && z09
        revMap.put("kvg OR sgj", "cwt");
        revMap.put("jnf XOR wgh", "z09");
        // css && jmv
        revMap.put("y20 AND x20", "jmv");
        revMap.put("y20 XOR x20", "css");
        // pqt && z37
        revMap.put("vcr AND nwb", "pqt");
        revMap.put("vcr XOR nwb", "css");

        Set<String> s = new TreeSet<>();
        s.add("gdd"); s.add("z05");
        s.add("cwt"); s.add("z09");
        s.add("css"); s.add("jmv");
        s.add("pqt"); s.add("z37");
        System.out.println("Part 2: " + String.join(",", s));

        for (i = 0; i < 45; i++) {
            String xin = "x" + (i < 10 ? "0" + i : i);
            String yin = "y" + (i < 10 ? "0" + i : i);

            String sum1 = xin + " XOR " + yin; String sum2 = yin + " XOR " + xin;
            String carry1 = xin + " AND " + yin; String carry2 = yin + " AND " + xin;

            String cursum = Optional.ofNullable(revMap.get(sum1)).orElse(revMap.get(sum2));
            String curcarry = Optional.ofNullable(revMap.get(carry1)).orElse(revMap.get(carry2));


            String carryWithSum = "";
            if (!prevCarry.isEmpty()) {
                String nextCarry1 = prevCarry + " AND " + cursum; String nextCarry2 = cursum + " AND " + prevCarry;
                carryWithSum = Optional.ofNullable(revMap.get(nextCarry1)).orElse(revMap.get(nextCarry2));
                if (carryWithSum == null) {
                    System.out.println("Not found carry with sum match with: " + nextCarry1 + " / " + nextCarry2);
                }
            }
            String nextCarry = "";
            if (!carryWithSum.isEmpty()) {
                String nc1 = carryWithSum + " OR " + curcarry; String nc2 = curcarry + " OR " + carryWithSum;
                nextCarry = Optional.ofNullable(revMap.get(nc1)).orElse(revMap.get(nc2));
                if (nextCarry == null) {
                    System.out.println("Not found next carry match with: " + nc1 + " / " + nc2);
                }
            } else {
                nextCarry = curcarry;
            }

            System.out.println("Bit# " + i + ", Sum: " + cursum + ", carry: " + curcarry + ", nextCarry: " + nextCarry);
            prevCarry = nextCarry;
        }
    }

    private static int operate(String output, Map<String, LHS> operations, Map<String, Integer> values) {
        if (values.containsKey(output)) return values.get(output);
        LHS lhs = operations.get(output);
        int val1 = operate(lhs.op1, operations, values);
        int val2 = operate(lhs.op2, operations, values);
        int res = -1;
        switch (lhs.op) {
            case OR -> res = val1 | val2;
            case AND -> res = val1 & val2;
            case XOR -> res = val1 ^ val2;
        }
        values.put(output, res);
        return res;
    }
}
