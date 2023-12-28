package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_6;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day6 {

    private static String INPUT = """
            0	2	7	0
            """;

    public static void solve() {
        INPUT = DAY_6;
        List<Integer> nums = new ArrayList<>(Arrays.stream(INPUT.split("\n")[0].split("\t"))
                .map(i -> Integer.parseInt(i.trim())).toList());

        Map<List<Integer>, Integer> set = new HashMap<>();
        set.put(nums, 0);
        int cycles = 0, part2 = -1;
        while(true) {
            int max = -1, idx = -1;
            for (int i = 0; i < nums.size(); i++) {
                if (max < nums.get(i)) {
                    max = nums.get(i);
                    idx = i;
                }
            }
            int val = nums.get(idx);
            List<Integer> newnums = new ArrayList<>(nums);
            newnums.set(idx, 0);
            int times = val / nums.size(); // add this much to all
            int mod = val % nums.size(); // add this much after idx


            for (int i = 0; i < nums.size(); i++) {
                newnums.set(i, newnums.get(i) + times);
                if (mod > 0 && (i < idx && idx+mod+1 > nums.size() && i <= (idx+mod)%nums.size())
                                || (i > idx && (i-idx) <= mod)) {
                    newnums.set(i, newnums.get(i) + 1);
                }
            }
            cycles++;
            if (set.containsKey(newnums)) {
                part2 = cycles - set.get(newnums);
                break;
            }
            set.put(newnums, cycles);
            nums = newnums;
        }



        System.out.println("Part 1: " + cycles); // 5042
        System.out.println("Part 2: " + part2); // 1086
    }
}
