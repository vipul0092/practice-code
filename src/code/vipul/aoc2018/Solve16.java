package code.vipul.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * https://adventofcode.com/2018/day/16
 */
public class Solve16 {

    public static long[] registers = new long[4];
    private static String input = Inputs.DAY16;
    public static final Map<String, TriFunction<Integer, Integer, Integer>> EVALUATORS;

    private static final Function<String, String> rowParser =
            (in) -> in.substring(in.lastIndexOf("[") + 1, in.lastIndexOf("]"));

    private static Map<Integer, String> opcodeEvaluator = new HashMap<>();

    static {
        EVALUATORS = new HashMap<>();
        EVALUATORS.put("addi", (a, b, c) -> addi(a, b, c));
        EVALUATORS.put("addr", (a, b, c) -> addr(a, b, c));
        EVALUATORS.put("muli", (a, b, c) -> muli(a, b, c));
        EVALUATORS.put("mulr", (a, b, c) -> mulr(a, b, c));
        EVALUATORS.put("banr", (a, b, c) -> banr(a, b, c));
        EVALUATORS.put("bani", (a, b, c) -> bani(a, b, c));
        EVALUATORS.put("borr", (a, b, c) -> borr(a, b, c));
        EVALUATORS.put("bori", (a, b, c) -> bori(a, b, c));
        EVALUATORS.put("setr", (a, b, c) -> setr(a, b, c));
        EVALUATORS.put("seti", (a, b, c) -> seti(a, b, c));
        EVALUATORS.put("gtir", (a, b, c) -> gtir(a, b, c));
        EVALUATORS.put("gtri", (a, b, c) -> gtri(a, b, c));
        EVALUATORS.put("gtrr", (a, b, c) -> gtrr(a, b, c));
        EVALUATORS.put("eqir", (a, b, c) -> eqir(a, b, c));
        EVALUATORS.put("eqri", (a, b, c) -> eqri(a, b, c));
        EVALUATORS.put("eqrr", (a, b, c) -> eqrr(a, b, c));
    }

    public static void solve() {
        List<String> inputs = Arrays.stream(input.split("\n")).filter(s -> !s.equals("")).collect(toList());

        int answer = 0;
        for (int i = 0; i < inputs.size(); ) {
            if (inputs.get(i).startsWith("Before")) {
                String in = rowParser.apply(inputs.get(i));
                String opcodes = inputs.get(i + 1);
                String out = rowParser.apply(inputs.get(i + 2));

                List<Integer> inParsed = parse(in, ",");
                List<Integer> opcodeParsed = parse(opcodes, " ");
                List<Integer> outParsed = parse(out, ",");

                AtomicInteger count = new AtomicInteger(0);

                EVALUATORS.forEach((name, e) -> {
                    setRegisters(inParsed);
                    e.execute(opcodeParsed.get(1), opcodeParsed.get(2), opcodeParsed.get(3));
                    if (verifyRegisters(outParsed)) {
                        count.addAndGet(1);
                    }
                });

                if (count.intValue() >= 3) {
                    answer++;
                }
                i += 3;
            } else {
                i++;
            }
        }

        System.out.println("Answer: " + answer); // 509
    }

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(input.split("\n")).filter(s -> !s.equals("")).collect(toList());

        List<List<Integer>> inParsedList = new ArrayList<>();
        List<List<Integer>> opcodeParsedList = new ArrayList<>();
        List<List<Integer>> outParsedList = new ArrayList<>();
        Map<Integer, Integer> opcodeCounts = new HashMap<>();

        int i = 0;
        for (; i < inputs.size(); ) {
            if (inputs.get(i).startsWith("Before")) {
                String in = rowParser.apply(inputs.get(i));
                String opcodes = inputs.get(i + 1);
                String out = rowParser.apply(inputs.get(i + 2));

                List<Integer> inParsed = parse(in, ",");
                List<Integer> opcodeParsed = parse(opcodes, " ");
                List<Integer> outParsed = parse(out, ",");

                opcodeCounts.putIfAbsent(opcodeParsed.get(0), 0);
                opcodeCounts.put(opcodeParsed.get(0), opcodeCounts.get(opcodeParsed.get(0)) + 1);

                inParsedList.add(inParsed);
                opcodeParsedList.add(opcodeParsed);
                outParsedList.add(outParsed);
                i += 3;
                if (!inputs.get(i).startsWith("Before")) {
                    break;
                }
            }
        }

        Map<String, Set<Integer>> opcodePotentialMaps = new HashMap<>();
        EVALUATORS.forEach((name, e) -> {
            Map<Integer, Integer> countsSuccessful = new HashMap<>();
            for (int j = 0; j < inParsedList.size(); j++) {
                setRegisters(inParsedList.get(j));
                List<Integer> opcodeParsed = opcodeParsedList.get(j);
                e.execute(opcodeParsed.get(1), opcodeParsed.get(2), opcodeParsed.get(3));
                if (verifyRegisters(outParsedList.get(j))) {
                    countsSuccessful.putIfAbsent(opcodeParsed.get(0), 0);
                    countsSuccessful.put(opcodeParsed.get(0), countsSuccessful.get(opcodeParsed.get(0)) + 1);
                }
            }

            System.out.print(String.format("%s matched with: ", name));
            countsSuccessful.forEach((opcode, count) -> {
                if (count.equals(opcodeCounts.get(opcode))) {
                    System.out.print(opcode + ", ");
                    opcodePotentialMaps.putIfAbsent(name, new HashSet<>());
                    opcodePotentialMaps.get(name).add(opcode);
                }
            });
            System.out.println();
        });

        while (true) {
            var entry = opcodePotentialMaps.entrySet().stream()
                    .filter(e -> e.getValue().size() == 1)
                    .findFirst();

            if (entry.isEmpty()) {
                break;
            }

            int opcode = entry.get().getValue().iterator().next();
            opcodeEvaluator.put(opcode, entry.get().getKey());
            opcodePotentialMaps.forEach((o, values) -> values.remove(opcode));
        }

        registers = new long[4];

        System.out.println();
        System.out.println("Running Program Now...");
        for (; i < inputs.size(); i++) {
            List<Integer> opcodes = parse(inputs.get(i), " ");
            EVALUATORS.get(opcodeEvaluator.get(opcodes.get(0))).execute(opcodes.get(1), opcodes.get(2), opcodes.get(3));
        }

        System.out.println("Answer: " + registers[0]); // 496
    }

    private static List<Integer> parse(String in, String separator) {
        return Arrays.stream(in.split(separator)).map(v -> Integer.parseInt(v.trim())).collect(toList());
    }

    private static void setRegisters(List<Integer> values) {
        registers[0] = values.get(0);
        registers[1] = values.get(1);
        registers[2] = values.get(2);
        registers[3] = values.get(3);
    }

    private static boolean verifyRegisters(List<Integer> values) {
        return registers[0] == values.get(0) && registers[1] == values.get(1)
                && registers[2] == values.get(2) && registers[3] == values.get(3);
    }

    private static void addi(int a, int b, int c) {
        registers[c] = registers[a] + b;
    }

    private static void addr(int a, int b, int c) {
        registers[c] = registers[a] + registers[b];
    }

    private static void muli(int a, int b, int c) {
        registers[c] = (registers[a] * (long) b);
    }

    private static void mulr(int a, int b, int c) {
        registers[c] = registers[a] * registers[b];
    }

    private static void bani(int a, int b, int c) {
        registers[c] = registers[a] & b;
    }

    private static void banr(int a, int b, int c) {
        registers[c] = registers[a] & registers[b];
    }

    private static void bori(int a, int b, int c) {
        registers[c] = registers[a] | b;
    }

    private static void borr(int a, int b, int c) {
        registers[c] = registers[a] | registers[b];
    }

    private static void seti(int a, int b, int c) {
        registers[c] = a;
    }

    private static void setr(int a, int b, int c) {
        registers[c] = registers[a];
    }

    private static void gtir(int a, int b, int c) {
        registers[c] = a > registers[b] ? 1 : 0;
    }

    private static void gtri(int a, int b, int c) {
        registers[c] = registers[a] > b ? 1 : 0;
    }

    private static void gtrr(int a, int b, int c) {
        registers[c] = registers[a] > registers[b] ? 1 : 0;
    }

    private static void eqir(int a, int b, int c) {
        registers[c] = a == registers[b] ? 1 : 0;
    }

    private static void eqri(int a, int b, int c) {
        registers[c] = registers[a] == b ? 1 : 0;
    }

    private static void eqrr(int a, int b, int c) {
        registers[c] = registers[a] == registers[b] ? 1 : 0;
    }

    @FunctionalInterface
    public interface TriFunction<a, b, c> {
        void execute(a a, b b, c c);
    }
}
