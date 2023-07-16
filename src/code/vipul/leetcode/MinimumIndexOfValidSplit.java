package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 16/07/23
 * https://leetcode.com/contest/weekly-contest-354/problems/minimum-index-of-a-valid-split/
 */
public class MinimumIndexOfValidSplit {

    public static void solve() {
        System.out.println(new MinimumIndexOfValidSplit().minimumIndex(List.of(1,2,2,2)));
    }

    public int minimumIndex(List<Integer> nums) {
        TreeMap<Integer, Set<Integer>> counts1 = new TreeMap<>(Comparator.reverseOrder());
        TreeMap<Integer, Set<Integer>> counts2 = new TreeMap<>(Comparator.reverseOrder());
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> second = new HashMap<>();
        int n = nums.size();


        for (int num : nums) {
            second.merge(num, 1, (v1, v2) -> v1 + v2);
        }

        for (Map.Entry<Integer, Integer> entry : second.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (!counts2.containsKey(count)) {
                counts2.put(count, new HashSet<>());
            }
            counts2.get(count).add(num);
        }

        int index = -1;
        for (int i = 0; i < n - 1; i++) {
            int numToMove = nums.get(i);
            int countInSecond = second.get(numToMove);

            first.merge(numToMove, 1, (v1, v2) -> v1 + v2);
            int countInFirst = first.get(numToMove);
            if (!counts1.containsKey(countInFirst)) {
                counts1.put(countInFirst, new HashSet<>());
            }
            counts1.get(countInFirst).add(numToMove);

            if (countInSecond == 1) {
                second.remove(numToMove);
            } else {
                second.put(numToMove, countInSecond - 1);
                if (!counts2.containsKey(countInSecond - 1)) {
                    counts2.put(countInSecond - 1, new HashSet<>());
                }
                counts2.get(countInSecond - 1).add(numToMove);
            }
            counts2.get(countInSecond).remove(numToMove);
            if (counts2.get(countInSecond).size() == 0) {
                counts2.remove(countInSecond);
            }

            int l1 = i + 1;
            int l2 = n - i - 1;

            if ((2 * counts1.firstKey()) > l1 && (2 * counts2.firstKey()) > l2) {
                int v1 = counts1.firstEntry().getValue().iterator().next();
                int v2 = counts2.firstEntry().getValue().iterator().next();

                if (v1 == v2) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
