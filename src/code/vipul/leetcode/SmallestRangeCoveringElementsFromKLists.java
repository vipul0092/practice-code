package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 14/08/23
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 *
 * Sliding window
 */
public class SmallestRangeCoveringElementsFromKLists {

    public static void solve() {
        System.out.println(Arrays.toString(new SmallestRangeCoveringElementsFromKLists().smallestRange(
                List.of(List.of(4, 10, 15, 24, 26), List.of(0, 9, 12, 20), List.of(5, 18, 22, 30))
        )));
    }

    // AC
    public int[] smallestRange(List<List<Integer>> nums) {
        List<int[]> allNumbers = new ArrayList<>();
        int totalLists = nums.size();

        for (int i = 0; i < nums.size(); i++) {
            for (int num : nums.get(i)) {
                allNumbers.add(new int[]{num, i});
            }
        }
        allNumbers.sort((n1, n2) -> n1[0] - n2[0]);

        int i = 0, j = 0;
        int minLength = Integer.MAX_VALUE, minStart = -1, minEnd = -1;
        int[] countPerList = new int[nums.size()];
        int listsIncluded = 0;

        while(j < allNumbers.size()) {
            int rangeEnd = allNumbers.get(j)[0];
            int endParentList = allNumbers.get(j)[1];

            countPerList[endParentList]++;
            // `endParentList` has appeared for the first time
            if (countPerList[endParentList] == 1) listsIncluded++;


            while(i <= j && listsIncluded == totalLists) {
                int rangeStart = allNumbers.get(i)[0];
                int startParentList = allNumbers.get(i)[1];
                if (rangeEnd - rangeStart < minLength) {
                    minLength = rangeEnd - rangeStart;
                    minStart = rangeStart;
                    minEnd = rangeEnd;
                } else if (rangeEnd - rangeStart == minLength && rangeStart < minStart) {
                    minStart = rangeStart;
                    minEnd = rangeEnd;
                }
                countPerList[startParentList]--;
                // `startParentList` has been removed from the range
                if (countPerList[startParentList] == 0) listsIncluded--;
                i++;
            }
            j++;
        }
        return new int[]{minStart, minEnd};
    }

    // TLE
    public int[] smallestRange2(List<List<Integer>> nums) {
        List<Integer> all = new ArrayList<>();
        List<TreeSet<Integer>> lists = new ArrayList<>();
        for (List<Integer> num: nums) {
            all.addAll(num);
            TreeSet<Integer> list = new TreeSet<>();
            list.addAll(num);
            lists.add(list);
        }
        all.sort(Comparator.naturalOrder());

        int i = 0, j = 0;
        int minLength = Integer.MAX_VALUE;
        int minStart = -1, minEnd = -1;

        while(j < all.size()) {
            int start = all.get(i);
            int end = all.get(j);

            boolean has = contains(lists, start, end);
            while(i <= j && has) {
                if (end - start < minLength) {
                    minLength = end - start;
                    minStart = start;
                    minEnd = end;
                } else if (end - start == minLength && start < minStart) {
                    minStart = start;
                    minEnd = end;
                }
                i++;
                if (i == all.size()) break;
                start = all.get(i);
                has = contains(lists, start, end);
            }
            j++;
        }
        return new int[]{minStart, minEnd};
    }

    private boolean contains(List<TreeSet<Integer>> lists, int start, int end) {
        for (TreeSet<Integer> list : lists) {
            Integer c = list.ceiling(start);
            if (c == null) return false;
            Integer f = list.floor(end);
            if (f == null) return false;
            if (c > f) return false;
        }
        return true;
    }
}
