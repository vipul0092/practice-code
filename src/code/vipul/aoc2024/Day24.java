package code.vipul.aoc2024;

import code.vipul.Pair;
import code.vipul.utils.AoCInputReader;

import java.util.*;
import java.util.function.Function;

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

    private static Set<String> swaps;
    private static Map<String, String> outputToOperationMap;
    private static Map<String, String> operationToOutputMap;
    private static TreeSet<String> zouts;
    private static TreeSet<String> xins;
    private static TreeSet<String> yins;
    private static Map<String, Integer> gateValues;

    public static void solve() {
        List<String> lines = AoCInputReader.read(Day24.class, false);

        parse(lines);
        Map<String, Integer> initialValues = new HashMap<>(gateValues);
        System.out.println("Part 1: " + getNumber(zouts)); // 61495910098126

        // The problem is a series of full-adders, and they function in a specific way
        // We iterate from the least significant bit towards the end
        // Each bit has some fixed operations that it needs to do, whose results will be mapped to a gate
        // SUM-bit              ->      x-bit XOR y-bit (the order can be different)
        // CARRY-bit            ->      x-bit AND y-bit (the order can be different)
        // CARRY-WITH-SUM-bit   ->      PREVIOUS-CARRY-bit AND SUM-bit (the order can be different)
        // NEXT-CARRY-bit       ->      CARRY-WITH-SUM-bit OR CARRY-bit (the order can be different)
        // At the end of each bit iteration NEXT-CARRY-bit becomes the PREVIOUS-CARRY-bit
        // If we find a problem finding any operational bit at each stage
        // we find what is expected and then swap with what is currently present
        // and restart the process from that point
        String prevCarry = "";
        swaps = new TreeSet<>();
        for (int i = 0; i < zouts.size() - 1; i++) {
            String xin = "x" + (i < 10 ? "0" + i : i);
            String yin = "y" + (i < 10 ? "0" + i : i);

            String cursum = findOutputGate(List.of(xin + " XOR " + yin, yin + " XOR " + xin));
            String curcarry = findOutputGate(List.of(xin + " AND " + yin, yin + " AND " + xin));

            String carryWithSum = "";
            if (!prevCarry.isEmpty()) {
                carryWithSum = findOutputGate(List.of(prevCarry + " AND " + cursum, cursum + " AND " + prevCarry));
                if (carryWithSum == null) {
                    // Try to find `prevCarry + AND`, `AND + prevCarry`, `cursum + AND`, `AND + cursum`
                    List<String> searches =
                            List.of(prevCarry + " AND ", " AND " + prevCarry, cursum + " AND ", " AND " + cursum);
                    String finalPrevCarry = prevCarry;
                    var swaps = searchAndSwap(searches,
                            search -> search.contains(finalPrevCarry) ? cursum : finalPrevCarry);

                    if (prevCarry.equals(swaps.left())) {
                        prevCarry = swaps.right();
                    } else if (prevCarry.equals(swaps.right())) {
                        prevCarry = swaps.left();
                    }
                    i--;
                    continue;
                }
            }
            String nextCarry = "";
            if (!carryWithSum.isEmpty()) {
                nextCarry = findOutputGate(List.of(carryWithSum + " OR " + curcarry, curcarry + " OR " + carryWithSum));
                if (nextCarry == null) {
                    // try to find `carryWithSum + OR`, `OR + carryWithSum`, `curcarry + OR`, `OR + curcarry`
                    List<String> searches =
                            List.of(carryWithSum + " OR ", " OR " + carryWithSum, curcarry + " OR ", " OR " + curcarry);
                    String finalCarryWithSum = carryWithSum;
                    searchAndSwap(searches,
                            search -> search.contains(curcarry) ? finalCarryWithSum : curcarry);
                    i--;
                    continue;
                }
            } else {
                nextCarry = curcarry;
            }

            // System.out.println("Bit# " + i + ", Sum: " + cursum + ", carry: " + curcarry + ", nextCarry: " + nextCarry);
            prevCarry = nextCarry;
        }
        System.out.println("Part 2: " + String.join(",", swaps));
        // Verify that the result is correct
        gateValues = new HashMap<>(initialValues);
        long expected = getNumber(xins) + getNumber(yins);
        long result = getNumber(zouts);
        assert result == expected;
    }

    private static Pair<String, String> searchAndSwap(List<String> searches,
                                                      Function<String, String> secondSwapGetter) {
        for (String search : searches) {
            var searchRes = findOutputGate(search);
            if (searchRes.isPresent()) {
                String swapOne = searchRes.get();
                String swapTwo = secondSwapGetter.apply(search);

                swaps.add(swapOne);
                swaps.add(swapTwo);
                // System.out.println("Swapping " + swapOne + " and " + swapTwo);

                String val1 = outputToOperationMap.get(swapOne);
                String val2 = outputToOperationMap.get(swapTwo);
                operationToOutputMap.put(val1, swapTwo);
                operationToOutputMap.put(val2, swapOne);
                outputToOperationMap.put(swapOne, val2);
                outputToOperationMap.put(swapTwo, val1);
                return Pair.of(swapOne, swapTwo);
            }
        }
        throw new RuntimeException("Solution not possible via this algorithm!");
    }

    private static Optional<String> findOutputGate(String search) {
        return operationToOutputMap.keySet().stream()
                .filter(s -> s.contains(search))
                .map(s -> s.replaceAll(search, "")).findAny();
    }

    private static int operate(String output) {
        if (gateValues.containsKey(output)) return gateValues.get(output);
        String[] operationParts = outputToOperationMap.get(output).split(" ");
        int val1 = operate(operationParts[0]);
        int val2 = operate(operationParts[2]);
        int res = -1;
        switch (OP.parse(operationParts[1])) {
            case OR -> res = val1 | val2;
            case AND -> res = val1 & val2;
            case XOR -> res = val1 ^ val2;
        }
        gateValues.put(output, res);
        return res;
    }

    private static String findOutputGate(List<String> searches) {
        return searches.stream().map(s -> operationToOutputMap.get(s))
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    private static long getNumber(Set<String> gates) {
        long res = 0, pow2 = 1;
        for (String gate : gates) {
            res += (pow2 * operate(gate));
            pow2 <<= 1;
        }
        return res;
    }

    private static void parse(List<String> lines) {
        int i;
        gateValues = new LinkedHashMap<>();
        yins = new TreeSet<>(); xins = new TreeSet<>();
        for (i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) {
                i++;
                break;
            }
            var parts = line.split(": ");
            gateValues.put(parts[0], Integer.parseInt(parts[1]));
            if (parts[0].charAt(0) == 'y') {
                yins.add(parts[0]);
            } else if (parts[0].charAt(0) == 'x') {
                xins.add(parts[0]);
            }
        }

        outputToOperationMap = new HashMap<>();
        operationToOutputMap = new HashMap<>();
        zouts = new TreeSet<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i);
            var parts = line.split(" -> ");
            if (parts[1].charAt(0) == 'z') {
                zouts.add(parts[1]);
            }
            operationToOutputMap.put(parts[0], parts[1]);
            outputToOperationMap.put(parts[1], parts[0]);
        }
    }
}
