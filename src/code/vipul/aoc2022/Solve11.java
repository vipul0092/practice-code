package code.vipul.aoc2022;

import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 11/12/22
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

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_11.split("\n")).collect(Collectors.toList());
        // List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int rounds = 20;
        Map<Integer, Queue<Integer>> items = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();

        Map<Integer, Function<Integer, Integer>> operations = new HashMap<>();
        Map<Integer, Function<Integer, Integer>> throwTest = new HashMap<>();

        int currentMonkey = -1;
        for (int i = 0; i < inputs.size(); ) {
            String in = inputs.get(i);
            if (in.isEmpty()) {
                i++;
                continue;
            }
            if (in.startsWith("Monkey")) {
                currentMonkey = in.split(" ")[1].charAt(0) - 48;
                items.put(currentMonkey, new ArrayDeque<>());
                counts.put(currentMonkey, 0);
                i++;
            } else if (in.contains("Starting")) {
                String[] parts = in.split(": ");
                for (String item : parts[1].split(", ")) {
                    items.get(currentMonkey).add(Integer.parseInt(item));
                }
                i++;
            } else if (in.contains("Operation")) {
                String[] parts = in.split("new = old ");
                String[] op = parts[1].split(" ");
                String val = op[1];
                if (val.equals("old")) {
                    if (op[0].charAt(0) == '*') {
                        operations.put(currentMonkey, o -> o * o);
                    } else if (op[0].charAt(0) == '+') {
                        operations.put(currentMonkey, o -> o + o);
                    }
                } else if (op[0].charAt(0) == '*') {
                    int v = Integer.parseInt(val);
                    operations.put(currentMonkey, o -> o * v);
                } else if (op[0].charAt(0) == '+') {
                    int v = Integer.parseInt(val);
                    operations.put(currentMonkey, o -> o + v);
                }
                i++;
            } else if (in.contains("Test")) {
                int divisible = Integer.parseInt(inputs.get(i).split(" divisible by ")[1]);
                i++;
                int trueThrow = Integer.parseInt(inputs.get(i).split("If true: throw to monkey ")[1]);
                i++;
                int falseThrow = Integer.parseInt(inputs.get(i).split("If false: throw to monkey ")[1]);

                throwTest.put(currentMonkey, value -> value % divisible == 0 ? trueThrow : falseThrow);
                i++;
            }
        }

        for (int i = 0; i < rounds; i++) {
            items.forEach((key, it) -> {
                int monkey = key;

                while (!it.isEmpty()) {
                    int item = it.remove();
                    int worry = (operations.get(monkey).apply(item)) / 3;
                    counts.put(monkey, counts.get(monkey) + 1);
                    int destinationMonkey = throwTest.get(monkey).apply(worry);
                    items.get(destinationMonkey).add(worry);
                }
            });
        }

        List<Integer> values = counts.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int ans = values.get(0) * values.get(1);

        System.out.println(ans);

    }

    private static Map<Integer, Long> div;

    public static void solvePart2() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_11.split("\n")).collect(Collectors.toList());
        // List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        int rounds = 10000;
        Map<Integer, Queue<Item>> items = new HashMap<>();
        Map<Integer, Long> counts = new HashMap<>();
        div = new HashMap<>();

        Map<Integer, Function<Item, Item>> operations = new HashMap<>();
        Map<Integer, Function<Item, Integer>> throwTest = new HashMap<>();

        int currentMonkey = -1;
        for (int i = 0; i < inputs.size(); ) {
            String in = inputs.get(i);
            if (in.isEmpty()) {
                i++;
                continue;
            }
            if (in.startsWith("Monkey")) {
                currentMonkey = in.split(" ")[1].charAt(0) - 48;
                items.put(currentMonkey, new ArrayDeque<>());
                counts.put(currentMonkey, 0L);
                i++;
            } else if (in.contains("Starting")) {
                String[] parts = in.split(": ");
                for (String item : parts[1].split(", ")) {
                    items.get(currentMonkey).add(new Item(Long.parseLong(item)));
                }
                i++;
            } else if (in.contains("Operation")) {
                String[] parts = in.split("new = old ");
                String[] op = parts[1].split(" ");
                String val = op[1];
                if (val.equals("old")) {
                    if (op[0].charAt(0) == '*') {
                        operations.put(currentMonkey, o -> o.multiply(o));
                    } else if (op[0].charAt(0) == '+') {
                        operations.put(currentMonkey, o -> o.add(o));
                    }
                } else if (op[0].charAt(0) == '*') {
                    long v = Long.parseLong(val);
                    operations.put(currentMonkey, o -> o.multiply(v));
                } else if (op[0].charAt(0) == '+') {
                    long v = Long.parseLong(val);
                    operations.put(currentMonkey, o -> o.add(v));
                }
                i++;
            } else if (in.contains("Test")) {
                long divisible = Long.parseLong(inputs.get(i).split(" divisible by ")[1]);
                div.put(currentMonkey, divisible);
                i++;
                int trueThrow = Integer.parseInt(inputs.get(i).split("If true: throw to monkey ")[1]);
                i++;
                int falseThrow = Integer.parseInt(inputs.get(i).split("If false: throw to monkey ")[1]);

                int finalCurrentMonkey = currentMonkey;
                throwTest.put(currentMonkey, item -> item.isDivisible(finalCurrentMonkey) ? trueThrow : falseThrow);
                i++;
            }
        }

        for (int i = 0; i < rounds; i++) {
            items.forEach((monkey, it) -> {
                while (!it.isEmpty()) {
                    Item item = it.remove();
                    Item worry = (operations.get(monkey).apply(item));
                    counts.put(monkey, counts.get(monkey) + 1);
                    int destinationMonkey = throwTest.get(monkey).apply(worry);
                    items.get(destinationMonkey).add(worry);
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
            return track.get(monkey) % div.get(monkey) == 0;
        }

        public Item multiply(Item m) {
            populate();
            for (Map.Entry<Integer, Long> e : m.track.entrySet()) {
                int monkey = e.getKey();
                long v = e.getValue();

                track.put(monkey, (v * track.get(monkey)) % div.get(monkey));
            }
            return this;
        }

        public Item multiply(Long l) {
            populate();
            for (Map.Entry<Integer, Long> e : track.entrySet()) {
                int monkey = e.getKey();
                long v = e.getValue();

                track.put(monkey, (v * l) % div.get(monkey));
            }
            return this;
        }

        public Item add(Item m) {
            populate();
            for (Map.Entry<Integer, Long> e : m.track.entrySet()) {
                int monkey = e.getKey();
                long v = e.getValue();

                track.put(monkey, (v + track.get(monkey)) % div.get(monkey));
            }
            return this;
        }

        public Item add(Long l) {
            populate();
            for (Map.Entry<Integer, Long> e : track.entrySet()) {
                int monkey = e.getKey();
                long v = e.getValue();

                track.put(monkey, (v + l) % div.get(monkey));
            }
            return this;
        }

        private void populate() {
            if (track.isEmpty()) {
                for (Map.Entry<Integer, Long> e : div.entrySet()) {
                    track.put(e.getKey(), value);
                }
            }
        }
    }
}
