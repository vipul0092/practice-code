package code.vipul.aoc2023;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static code.vipul.aoc2023.inputs.Inputs.DAY_9;

/**
 * Created by vgaur created on 09/12/23
 */
public class Day9 {

    private static String INPUT = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
            """;

    public static void solve() {
        INPUT = DAY_9;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        int sum1 = 0, sum2 = 0;
        for (String line : lines) {
            int[] nums = Arrays.stream(line.split(" ")).map(Integer::parseInt).mapToInt(i -> i).toArray();
            sum1 += (getNum(nums, (n, ns) -> n + ns[ns.length - 1]) + nums[nums.length - 1]);
            sum2 += (nums[0] - getNum(nums, (n, ns) -> ns[0] - n));
        }

        System.out.println("Part 1: " + sum1); //1806615041
        System.out.println("Part 2: " + sum2); //1211
    }

    private static int getNum(int[] nums, BiFunction<Integer, int[], Integer> retCalc) {
        int[] newList = new int[nums.length - 1];
        int idx = 0;
        boolean allZeros = true;
        for (int i = 0; i < nums.length - 1; i++) {
            int n = nums[i + 1] - nums[i];
            newList[idx++] = n;
            if (n != 0) allZeros = false;
        }

        if (allZeros) {
            return 0;
        }
        int num = getNum(newList, retCalc);
        return retCalc.apply(num, newList);
    }
}
