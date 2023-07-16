package code.vipul.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vgaur created on 14/07/23
 * Good practice question!!
 */
public class BinaryTreeFromInorderPreorder {

    public static void solve() {
        System.out.println(new BinaryTreeFromInorderPreorder()
                .buildTree(new int[] {3,1,2,5,6,4}, new int[]{1,5,2,6,3,4}));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        Map<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indices.put(inorder[i], i);
        }

        return build(0, preorder.length - 1, preorder, indices, 0);
    }

    private TreeNode build(int start, int end, int[] preorder, Map<Integer, Integer> indices, int idx) {
        // Base case, start index = end index
        if (start == end) {
            return new TreeNode(preorder[idx]);
        } else if (start > end) {
            return null;
        }

        // Preorder can give us the size of left subtree and right subtrees
        // To the left of the value, we have the left subtree
        // To the right of the value, we have the right subtree
        int inorderIndex = indices.get(preorder[idx]);
        int leftSize = inorderIndex - start;
        int rightSize = end - inorderIndex;

        TreeNode node = new TreeNode(preorder[idx]);
        // If there is a left subtree, recursively build it by restricting the inorder array
        // and incrementing the preorder index
        if (leftSize > 0) {
            node.left = build(start, inorderIndex - 1, preorder, indices, idx + 1);
        }

        // Similar to left subtree, build the right subtree
        if (rightSize > 0) {
            node.right = build(inorderIndex + 1, end, preorder, indices, idx + leftSize + 1);
        }
        return node;
    }

 public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

}
