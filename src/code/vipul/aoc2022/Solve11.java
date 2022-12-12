package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 11/12/22
 * https://adventofcode.com/2022/day/11
 */
public class Solve11 {

    private static final String INPUT = "Monkey 0:\n" +
            "  Starting items: 79, 98\n" +
            "  Operation: new = old * 19\n" +
            "  Test: divisible by 23\n" +
            "    If true: throw to monkey 2\n" +
            "    If false: throw to monkey 3\n" +
            "\n" +
            "Monkey 1:\n" +
            "  Starting items: 54, 65, 75, 74\n" +
            "  Operation: new = old + 6\n" +
            "  Test: divisible by 19\n" +
            "    If true: throw to monkey 2\n" +
            "    If false: throw to monkey 0\n" +
            "\n" +
            "Monkey 2:\n" +
            "  Starting items: 79, 60, 97\n" +
            "  Operation: new = old * old\n" +
            "  Test: divisible by 13\n" +
            "    If true: throw to monkey 1\n" +
            "    If false: throw to monkey 3\n" +
            "\n" +
            "Monkey 3:\n" +
            "  Starting items: 74\n" +
            "  Operation: new = old + 3\n" +
            "  Test: divisible by 17\n" +
            "    If true: throw to monkey 0\n" +
            "    If false: throw to monkey 1";

    private static Map<Integer, Long> divisibilityChecks;
    private static boolean trackForEachMonkey = false;

    public static void solve() {
        solveInternal(Inputs.INPUT_11, 20, false);
    }

    public static void solvePart2() {
        solveInternal(Inputs.INPUT_11, 10000, true);
    }

    public static void solveInternal(String inputRows, int rounds, boolean track) {
        trackForEachMonkey = track;
        List<String> inputs = Arrays.stream(inputRows.split("\n")).collect(Collectors.toList());

        Map<Integer, Queue<Item>> items = new HashMap<>();
        Map<Integer, Long> counts = new HashMap<>();
        divisibilityChecks = new HashMap<>();

        Map<Integer, Function<Item, Item>> operations = new HashMap<>();
        Map<Integer, Function<Item, Integer>> throwTest = new HashMap<>();

        int currentMonkey = -1;
        for (int i = 0; i < inputs.size(); i++) {
            String in = inputs.get(i);
            if (in.startsWith("Monkey")) {
                currentMonkey = in.split(" ")[1].charAt(0) - 48;
                items.put(currentMonkey, new ArrayDeque<>());
                counts.put(currentMonkey, 0L);
            } else if (in.contains("Starting")) {
                for (String item : in.split(": ")[1].split(", ")) {
                    items.get(currentMonkey).add(new Item(Long.parseLong(item)));
                }
            } else if (in.contains("Operation")) {
                String[] op = in.split("new = old ")[1].split(" ");
                String val = op[1];
                char operation = op[0].charAt(0);
                if (val.equals("old")) {
                    if (operation == '*') {
                        operations.put(currentMonkey, o -> o.multiply(o));
                    } else if (operation == '+') {
                        operations.put(currentMonkey, o -> o.add(o));
                    }
                } else if (operation == '*') {
                    long v = Long.parseLong(val);
                    operations.put(currentMonkey, o -> o.multiply(v));
                } else if (operation == '+') {
                    long v = Long.parseLong(val);
                    operations.put(currentMonkey, o -> o.add(v));
                }
            } else if (in.contains("Test")) {
                long divisible = Long.parseLong(inputs.get(i).split(" divisible by ")[1]);
                divisibilityChecks.put(currentMonkey, divisible);
                i++;
                int trueThrow = Integer.parseInt(inputs.get(i).split("If true: throw to monkey ")[1]);
                i++;
                int falseThrow = Integer.parseInt(inputs.get(i).split("If false: throw to monkey ")[1]);

                int finalCurrentMonkey = currentMonkey;
                throwTest.put(currentMonkey, item -> item.isDivisible(finalCurrentMonkey) ? trueThrow : falseThrow);
            }
        }

        for (int i = 0; i < rounds; i++) {
            items.forEach((monkey, itemsQueue) -> {
                while (!itemsQueue.isEmpty()) {
                    Item item = (operations.get(monkey).apply(itemsQueue.remove()));
                    counts.put(monkey, counts.get(monkey) + 1);
                    int destinationMonkey = throwTest.get(monkey).apply(item);
                    items.get(destinationMonkey).add(item);
                }
            });
        }

        List<Long> values = counts.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        long ans = values.get(0) * values.get(1);

        System.out.println(ans);
    }

    public static final class Item {
        private final long value;
        private final Map<Integer, Long> track;

        public Item(long v) {
            this.value = v;
            track = new HashMap<>();
        }

        public boolean isDivisible(int monkey) {
            return track.get(monkey) % divisibilityChecks.get(monkey) == 0;
        }

        public Item multiply(Item m) {
            return operate((monkey, value) -> value * m.track.get(monkey));
        }

        public Item multiply(Long l) {
            return operate((monkey, value) -> value * l);
        }

        public Item add(Item m) {
            return operate((monkey, value) -> value + m.track.get(monkey));
        }

        public Item add(Long l) {
            return operate((monkey, value) -> value + l);
        }

        private Item operate(BiFunction<Integer, Long, Long> operator) {
            populate();
            for (Map.Entry<Integer, Long> e : track.entrySet()) {
                int monkey = e.getKey();
                long v = e.getValue();

                long evaluatedValue = operator.apply(monkey, v);
                track.put(monkey, trackForEachMonkey
                        ? evaluatedValue % divisibilityChecks.get(monkey)
                        : evaluatedValue / 3);
            }
            return this;
        }

        private void populate() {
            if (track.isEmpty()) {
                for (Map.Entry<Integer, Long> e : divisibilityChecks.entrySet()) {
                    track.put(e.getKey(), value);
                }
            }
        }
    }
}
