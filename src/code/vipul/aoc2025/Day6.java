package code.vipul.aoc2025;

import java.util.*;
import code.vipul.utils.AoCInputReader;

/**
 * https://adventofcode.com/2025/day/6
 */
public class Day6 {

    public static void solve() {
        boolean sample = false;
        int numbers = sample ? 3 : 4;
        List<String> lines = AoCInputReader.read(Day6.class, sample);

        List<List<Long>> allNums = new ArrayList<>();

        for (int i = 0; i < numbers; i++) {
            List<Long> nums = Arrays.stream(lines.get(i).split(" "))
                    .filter(l -> !l.isEmpty())
                    .map(s -> Long.parseLong(s))
                    .toList();
            allNums.add(nums);
        }
        List<Character> ops = Arrays.stream(lines.get(numbers).split(" "))
                .filter(l -> !l.isEmpty())
                .map(l -> l.charAt(0)).toList();

        long total = 0, total2 = 0;
        for (int i = 0; i < ops.size(); i++) {
            int finalI = i;
            char op = ops.get(i);
            List<Long> operands = allNums.stream().map(l -> l.get(finalI)).toList();

            long tot = op == '*' ? 1 : 0;
            for (long operand : operands) {
                if (op == '*') {
                    tot *= operand;
                } else {
                    tot += operand;
                }
            }
            total += tot;
        }
        System.out.println(total);

        List<String[]> splits = new ArrayList<>();
        for (String line : lines) {
            splits.add(line.split(" "));
        }

        int current = 0;
        String oplist = lines.get(lines.size()-1);

        for (int i = 0; i < oplist.length() && current < oplist.length(); i++) {
            int len = 1;
            char op = oplist.charAt(current);;
            for (int j = current + 1; j < oplist.length(); j++) {
                if (oplist.charAt(j) == ' ') {
                    len++;
                } else {
                    break;
                }
            }

            if (current == oplist.length()-1) {
                int max = 0;
                for (int k = 0; k < numbers; k++) {
                    max = Math.max(max, lines.get(k).length() - current);
                }
                len = max;
            }

            long tot = op == '*' ? 1 : 0;
            for (int j = 0; j < len; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < numbers; k++) {
                    if (current + j > lines.get(k).length()-1) continue;
                    char pd = lines.get(k).charAt(current+j);
                    if (pd != ' ') {
                        sb.append(pd);
                    }
                }
                if (!sb.isEmpty()) {
                    long num = Long.parseLong(sb.toString());
                    tot = op == '*' ? tot * num : tot + num;
                }
            }
            total2 += tot;

            current += len;
        }

        System.out.println(total2);
    }
}
