package code.vipul.leetcode;

import java.util.*;

/**
 * Created by vgaur created on 25/06/23
 */
public class ThreeSum {

    public static void test() {
        int[] arr = new int[]{0,0,0,0,0,0};
        var l = threeSum(arr);
        System.out.println(l);
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        Map<Integer, Set<Integer>> indicesMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            indicesMap.putIfAbsent(nums[i], new HashSet<>());
            indicesMap.get(nums[i]).add(i);
        }

        Set<List<Integer>> triplets = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                int toFind = -sum;

                if (!indicesMap.containsKey(toFind)) continue;

                Set<Integer> indices = indicesMap.get(toFind);
                if (((indices.contains(i) && indices.contains(j)) && indices.size() < 3) ||
                        (indices.contains(i) || indices.contains(j)) && indices.size() < 2) continue;

                List<Integer> tripletNos = new ArrayList<>();
                tripletNos.add(toFind);
                tripletNos.add(nums[i]);
                tripletNos.add(nums[j]);
                tripletNos.sort(Comparator.naturalOrder());

                triplets.add(tripletNos);
            }
        }
        return new ArrayList<>(triplets);
    }
}
