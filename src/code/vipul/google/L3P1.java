package code.vipul.google;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//AC
/*
The Grandest Staircase Of Them All
==================================

With her LAMBCHOP doomsday device finished, Commander Lambda is preparing for her debut on the galactic stage - but in order to make a grand entrance, she needs a grand staircase! As her personal assistant, you've been tasked with figuring out how to build the best staircase EVER.

Lambda has given you an overview of the types of bricks available, plus a budget. You can buy different amounts of the different types of bricks (for example, 3 little pink bricks, or 5 blue lace bricks). Commander Lambda wants to know how many different types of staircases can be built with each amount of bricks, so she can pick the one with the most options.

Each type of staircase should consist of 2 or more steps.  No two steps are allowed to be at the same height - each step must be lower than the previous one. All steps must contain at least one brick. A step's height is classified as the total amount of bricks that make up that step.
For example, when N = 3, you have only 1 choice of how to build the staircase, with the first step having a height of 2 and the second step having a height of 1: (# indicates a brick)

#
##
21

When N = 4, you still only have 1 staircase choice:

#
#
##
31

But when N = 5, there are two ways you can build a staircase from the given bricks. The two staircases can have heights (4, 1) or (3, 2), as shown below:

#
#
#
##
41

#
##
##
32

Write a function called solution(n) that takes a positive integer n and returns the number of different staircases that can be built from exactly n bricks. n will always be at least 3 (so you can have a staircase at all), but no more than 200, because Commander Lambda's not made of money!

for n = 200, answer is 487067745
 */
public class L3P1 {

    private static Map<Integer, TreeMap<Integer, Integer>> countsPerNumber = new HashMap<>();

    public static void test() {
        int n = 200;
        System.out.println(solution(n));
    }

    private static int solution(int n) {
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        counts.put(0, 1);
        countsPerNumber.put(0, counts);

        counts = new TreeMap<>();
        counts.put(1, 1);
        countsPerNumber.put(1, counts);

        counts = new TreeMap<>();
        counts.put(2, 1);
        countsPerNumber.put(2, counts);

        counts = new TreeMap<>();
        //#
        //##
        counts.put(2, 1);
        //#
        //#
        //#
        counts.put(3, 2);
        countsPerNumber.put(3, counts);

        if (countsPerNumber.containsKey(n)) {
            return countsPerNumber.get(n).lowerEntry(n).getValue(); // Remove the n - 0 combination
        }
        loadAllCountsFor(n);
        return countsPerNumber.get(n).lowerEntry(n).getValue(); // Remove the n - 0 combination
    }

    private static void loadAllCountsFor(int num) {
        if (countsPerNumber.containsKey(num)) {
            return;
        }
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int i = 1; i <= num; i++) {
            int otherHalf = num - i;
            int limit = (i * (i - 1)) / 2;
            if (otherHalf > limit) {
                continue;
            }
            loadAllCountsFor(otherHalf);
            TreeMap<Integer, Integer> cnts = countsPerNumber.get(otherHalf);
            int count = cnts.lowerEntry(i).getValue();

            counts.put(i, counts.getOrDefault(i-1, 0) + count);
        }
        countsPerNumber.put(num, counts);
    }
}
