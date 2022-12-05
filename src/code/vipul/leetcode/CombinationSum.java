package code.vipul.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vgaur created on 22/10/22
 */
public class CombinationSum {

    private static Map<Integer, Set<Map<Integer, Integer>>> cache;

    public static void solve() {
        var l = List.of(2,3,6,7);
        int[] arr = new int[l.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = l.get(i);
        }

        new CombinationSum().combinationSum(arr, 7);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        cache = new HashMap<>();

        Set<Integer> numbers = new HashSet<>();
        for (int can : candidates) {
            numbers.add(can);
        }
        Set<Map<Integer, Integer>> results = find(target, numbers);

        List<List<Integer>> ret = new ArrayList<>();
        for (Map<Integer, Integer> result : results) {
            List<Integer> v = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                int count = entry.getValue();
                while (count-- > 0) {
                    v.add(entry.getKey());
                }
            }
            ret.add(v);
        }
        return ret;
    }

    private static Set<Map<Integer, Integer>> find(int sum, Set<Integer> numbers) {
        if (cache.containsKey(sum)) {
            return cache.get(sum);
        }
        Set<Map<Integer, Integer>> values = new HashSet<>();
        for (int number : numbers) {
            if (sum - number > 0) {
                Set<Map<Integer, Integer>> sub = find(sum - number, numbers);
                if (sub.size() > 0) {
                    for (Map<Integer, Integer> sublist : sub) {
                        Map<Integer, Integer> exact = new HashMap<>(sublist);
                        exact.putIfAbsent(number, 0);
                        exact.put(number, exact.get(number) + 1);
                        values.add(exact);
                    }
                }
            } else if (sum - number == 0) {
                Map<Integer, Integer> exact = new HashMap<>();
                exact.put(number, 1);
                values.add(exact);
            }
        }

        cache.put(sum, values);
        return values;
    }
}
