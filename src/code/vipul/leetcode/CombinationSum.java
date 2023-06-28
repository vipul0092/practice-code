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

    static Map<List<Integer>, List<List<Integer>>> cache;
    public static void solve() {
        var l = List.of(2,5,8,4);
        int[] arr = new int[l.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = l.get(i);
        }

        System.out.println(new CombinationSum().combinationSum(arr, 10));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        cache = new HashMap<>();
        return find(0, candidates, target);
    }

    private static List<List<Integer>> find(int idx, int[] candidates, int target) {
        if (idx == candidates.length) {
            return new ArrayList<>();
        }

        List<Integer> key = List.of(idx, target);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        // either take the value at idx or dont
        int val = candidates[idx];

        List<List<Integer>> values;
        if (val > target) { // we cant take val
            values = find(idx + 1, candidates, target);
        } else if (val == target) {
            Set<List<Integer>> distinct = new HashSet<>();
            List<List<Integer>> takenAndSameIndex = List.of(List.of(val));
            List<List<Integer>> notTaken = find(idx + 1, candidates, target);

            for (List<Integer> list : takenAndSameIndex) {
                List<Integer> nl = new ArrayList<>(list);
                distinct.add(nl);
            }
            for (List<Integer> list : notTaken) {
                List<Integer> nl = new ArrayList<>(list);
                distinct.add(nl);
            }

            values = new ArrayList<>(distinct);
        } else {
            Set<List<Integer>> distinct = new HashSet<>();

            List<List<Integer>> takenAndSameIndex = find(idx, candidates, target - val);
            List<List<Integer>> takenAndMoveAhead = find(idx + 1, candidates, target - val);
            List<List<Integer>> notTaken = find(idx + 1, candidates, target);

            for (List<Integer> list : takenAndMoveAhead) {
                List<Integer> nl = new ArrayList<>(list);
                nl.add(val);
                distinct.add(nl);
            }

            for (List<Integer> list : takenAndSameIndex) {
                List<Integer> nl = new ArrayList<>(list);
                nl.add(val);
                distinct.add(nl);
            }

            for (List<Integer> list : notTaken) {
                List<Integer> nl = new ArrayList<>(list);
                distinct.add(nl);
            }

            values = new ArrayList<>(distinct);
        }
        cache.put(key, values);
        return values;
    }
}
