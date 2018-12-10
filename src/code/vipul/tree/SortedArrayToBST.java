package code.vipul.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vgaur on 09/12/18.
 */
public class SortedArrayToBST {

    private static int SIZE = 10;

    public static void doStuff() {
        ArrayList<Integer> array = getDummySortedArray(SIZE);

        printArray(array);
        TreeNode tree = getTree(array, 0, SIZE-1);
        TreeUtils.printInOrder(tree);
    }

    public static TreeNode getTree(ArrayList<Integer> array, int start, int end) {
        if(start > end) {
            return null;
        } else if (start == end) {
            return TreeNode.get(array.get(start));
        }

        int current = (start+end)/2;
        TreeNode node = TreeNode.get(array.get(current));

        TreeNode left = getTree(array, start, current -1);
        TreeNode right = getTree(array, current+1, end);
        node.left = left;
        node.right = right;
        return node;
    }

    public static ArrayList<Integer> getDummySortedArray(int size) {
        ArrayList<Integer> array = new ArrayList<>();
        Random random = new Random();
        while (size-- > 0) {
            array.add(random.nextInt(20));
        }
        array.sort(Integer::compareTo);
        return array;
    }

    public static void printArray(List<Integer> array) {
        for(int i =0; i<array.size(); i++) {
            System.out.print(array.get(i) + " ");
        }
        System.out.println();
    }



}
