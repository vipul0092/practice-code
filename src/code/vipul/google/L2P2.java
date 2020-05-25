package code.vipul.google;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

//AC
/*
Ion Flux Relabeling
===================

Oh no! Commander Lambda's latest experiment to improve the efficiency of her LAMBCHOP doomsday device has backfired spectacularly. She had been improving the structure of the ion flux converter tree, but something went terribly wrong and the flux chains exploded. Some of the ion flux converters survived the explosion intact, but others had their position labels blasted off. She's having her henchmen rebuild the ion flux converter tree by hand, but you think you can do it much more quickly - quickly enough, perhaps, to earn a promotion!

Flux chains require perfect binary trees, so Lambda's design arranged the ion flux converters to form one. To label them, she performed a post-order traversal of the tree of converters and labeled each converter with the order of that converter in the traversal, starting at 1. For example, a tree of 7 converters would look like the following:

   7
 3   6
1 2 4 5

Write a function solution(h, q) - where h is the height of the perfect tree of converters and q is a list of positive integers representing different flux converters - which returns a list of integers p where each element in p is the label of the converter that sits on top of the respective converter in q, or -1 if there is no such converter.  For example, solution(3, [1, 4, 7]) would return the converters above the converters at indexes 1, 4, and 7 in a perfect binary tree of height 3, which is [3, 6, -1].

The domain of the integer h is 1 <= h <= 30, where h = 1 represents a perfect binary tree containing only the root, h = 2 represents a perfect binary tree with the root and two leaf nodes, h = 3 represents a perfect binary tree with the root, two internal nodes and four leaf nodes (like the example above), and so forth.  The lists q and p contain at least one but no more than 10000 distinct integers, all of which will be between 1 and 2^h-1, inclusive.

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

Input:
Solution.solution(5, {19, 14, 28})
Output:
    21,15,29

Input:
Solution.solution(3, {7, 3, 5, 1})
Output:
    -1,7,6,3
 */
public final class L2P2 {

    private static Map<Integer, Integer> powersOfTwo = new HashMap<>();

    public static void test() {
        int h = 4;
        int[] a = Stream.of(1, 9, 10).mapToInt(i->i).toArray();
        int[] ans = solution(h, a);
        System.out.println(String.format("%s, %s, %s", ans[0], ans[1], ans[2]));
    }

    private static int[] solution(int h, int[] q) {
        int power = 1;
        for (int i = 0; i <= h; i++) {
            powersOfTwo.put(i, power);
            power *= 2;
        }

        int[] ans = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            ans[i] = search(h, q[i], 0, -1);
        }

        return ans;
    }

    private static int search(int level, int toSearch, int change, int parent) {
        int currentNumber = powersOfTwo.get(level) - 1;

        if (currentNumber == toSearch) {
            return parent + change;
        }

        int leftChild = (currentNumber - 1)/ 2;
        int rightChild = leftChild + powersOfTwo.get(level - 1) - 1;

        if (rightChild == toSearch) {
            return currentNumber + change;
        }

        if (toSearch > leftChild) { // displace to the left tree
            int displacement = powersOfTwo.get(level - 1) - 1;
            change += displacement;
            toSearch -= displacement;
        }
        return search(level - 1, toSearch, change, currentNumber);
    }
}
