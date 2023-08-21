package code.vipul.leetcode;

import java.util.*;

/**
 * Created by vgaur created on 06/06/21
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/603/week-1-june-1st-june-7th/3769/
 * AC
 */
public class LongestConsecutiveSequence {

    public static void solve() {
        int[] nums = new int[]{9,1,4,7,3,-1,0,5,8,-1,6};
        System.out.println(new LongestConsecutiveSequence().longestConsecutive3(nums));
    }

    int[] parent = null;
    int[] size = null;
    // Uses disjoint set union-find
    public int longestConsecutive3(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) return n;
        int tid = 0;
        Map<Integer, Integer> numToId = new HashMap<>();
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (!numToId.containsKey(num)) {
                numToId.put(num, tid++);
                numbers.add(num);
            }
        }
        parent = new int[numbers.size()];
        size = new int[numbers.size()];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            if (numToId.containsKey(number-1)) {
                union(i, numToId.get(number-1));
            }
            if (numToId.containsKey(number+1)) {
                union(i, numToId.get(number+1));
            }
        }

        Map<Integer, Integer> groupSizes = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            groupSizes.merge(find(i), 1, (v1,v2) -> v1+v2);
        }
        return groupSizes.values().stream().mapToInt(i -> i).max().getAsInt();
    }

    private int find(int i) {
        if (i == parent[i]) return i;
        parent[i] = find(parent[i]);
        return parent[i];
    }

    private void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) return;

        if (size[p1] >= size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
        } else {
            parent[p1] = p2;
            size[p2] += size[p1];
        }
    }

    //O n
    public int longestConsecutiveTry2(int[] nums) {
        int largestLength = 0;
        Set<Integer> numbers = new HashSet<>(nums.length);
        for (int num : nums) {
            numbers.add(num);
        }

        for (int num : numbers) {
            if (!numbers.contains(num - 1)) {
                int curlen = 1;
                int curnum = num + 1;
                while (numbers.contains(curnum)) {
                    curlen++;
                    curnum++;
                }
                largestLength = Math.max(largestLength, curlen);
            }
        }
        return largestLength;
    }

    //O n logn
    public int longestConsecutive(int[] nums) {
        Set<Integer> numbers = new TreeSet<>();
        for (int num : nums) {
            numbers.add(num);
        }

        int largestLength = 0;
        int currentLength = 0;
        Integer prevNum = null;

        for (int num : numbers) {
            if (prevNum == null) {
                prevNum = num;
                currentLength = 1;
            } else if (prevNum == num - 1) {
                currentLength++;
                prevNum = num;
            } else {
                prevNum = num;
                largestLength = Math.max(largestLength, currentLength);
                currentLength = 1;
            }
        }
        largestLength = Math.max(largestLength, currentLength);

        return largestLength;
    }
}
