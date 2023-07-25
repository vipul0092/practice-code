package code.vipul.aoc2022;

import code.vipul.Pair;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 13/12/22
 * https://adventofcode.com/2022/day/13
 */
public class Solve13 {

    private static String INPUT =
            "[1,1,3,1,1]\n" +
                    "[1,1,5,1,1]\n" +
                    "\n" +
                    "[[1],[2,3,4]]\n" +
                    "[[1],4]\n" +
                    "\n" +
                    "[9]\n" +
                    "[[8,7,6]]\n" +
                    "\n" +
                    "[[4,4],4,4]\n" +
                    "[[4,4],4,4,4]\n" +
                    "\n" +
                    "[7,7,7,7]\n" +
                    "[7,7,7]\n" +
                    "\n" +
                    "[]\n" +
                    "[3]\n" +
                    "\n" +
                    "[[[]]]\n" +
                    "[[]]\n" +
                    "\n" +
                    "[1,[2,[3,[4,[5,6,7]]]],8,9]\n" +
                    "[1,[2,[3,[4,[5,6,0]]]],8,9]";

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_13.split("\n")).collect(Collectors.toList());
        //List<String> inputs = Arrays.stream(INPUT.split("\n")).collect(Collectors.toList());

        List<Pair<Item, Item>> pairs = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        // For part 2
        items.add(parse("[[2]]"));
        items.add(parse("[[6]]"));

        for (int i = 0; i < inputs.size(); i += 3) {
            Item left = parse(inputs.get(i));
            Item right = parse(inputs.get(i + 1));
            items.add(left);
            items.add(right);
            pairs.add(Pair.of(left, right));
        }

        int part1 = 0;
        for (int i = 0; i < pairs.size(); i++) {
            Pair<Item, Item> pair = pairs.get(i);
            Item left = pair.left();
            Item right = pair.right();

            if (left.compareTo(right) < 0) {
                part1 += (i + 1);
            }
        }
        System.out.println(part1);

        List<Item> sorted = items.stream().sorted().collect(Collectors.toList());
        int part2 = 1;
        for (int i = 0; i < sorted.size(); i++) {
            Item item = sorted.get(i);
            if (item.toString().equals("[[2]]") || (item.toString().equals("[[6]]"))) {
                part2 *= (i + 1);
            }
        }
        System.out.println(part2);
    }

    public static Item parse(String in1) {
        Stack<List<Item>> items = new Stack<>();
        List<Item> currentList = new ArrayList<>();
        for (int j = 0; j < in1.length(); j++) {
            char ch = in1.charAt(j);
            if (ch == '[') {
                items.push(currentList);
                currentList = new ArrayList<>();
            } else if (ch >= '0' && ch <= '9') {
                StringBuilder num = new StringBuilder();
                while (ch >= '0' && ch <= '9') {
                    num.append(ch);
                    ch = in1.charAt(++j);
                }
                currentList.add(new Item(Integer.parseInt(num.toString())));
                j--;
            } else if (ch == ']') {
                List<Item> parent = items.pop();
                parent.add(new Item(currentList));
                currentList = parent;
            }
        }
        Item n = new Item();
        n.values = currentList.get(0).values;
        return n;
    }

    public static class Item implements Comparable<Item> {
        private Integer value;
        private List<Item> values;

        public Integer getValue() {
            return value;
        }

        public List<Item> getValues() {
            return values;
        }

        private String str;

        public Item(int v) {
            this.value = v;
        }

        public Item() {
        }

        public Item(List<Item> v) {
            this.values = v;
        }

        public boolean isNum() {
            return value != null;
        }

        @Override
        public String toString() {
            if (str != null) {
                return str;
            }
            if (value != null) {
                str = String.format("%s", value);
            } else {
                StringBuilder sb = new StringBuilder();
                for (Item i : values) {
                    sb.append(i.toString()).append(",");
                }
                str = sb.toString();
                str = String.format("[%s]", str.isEmpty() ? "" : str.substring(0, str.length() - 1));
            }
            return str;
        }

        @Override
        public int compareTo(Item i) {
            Boolean b = compare(this.values, i.values);
            if (b == null) {
                return 0;
            } else if (!b) {
                return 1;
            } else return -1;
        }

        private static Boolean compare(List<Item> left, List<Item> right) {
            Boolean res = null;
            for (int i = 0; i < left.size(); i++) {
                Item l = left.get(i);
                if (i == right.size()) {
                    res = false;
                    break;
                }
                Item r = right.get(i);
                if (l.isNum() && r.isNum()) {
                    if (l.value < r.value) {
                        res = true;
                    } else if (l.value > r.value) {
                        res = false;
                    }
                } else if (!l.isNum() && r.isNum()) {
                    res = compare(l.values, List.of(r));
                } else if (l.isNum() && !r.isNum()) {
                    res = compare(List.of(l), r.values);
                } else {
                    res = compare(l.values, r.values);
                }
                if (res != null) {
                    return res;
                }
            }
            if (res == null) {
                return left.size() < right.size() ? true : null;
            }
            return false;
        }

    }
}
