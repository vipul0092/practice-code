/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> traversal = new ArrayList<>();
        int val = inorder(root, traversal, k);
        return val;
    }

    public int inorder(TreeNode node, List<Integer> traversal, int k) {
        if (node == null) return -1;
        int id = inorder(node.left, traversal, k);
        if (id != -1) return id;
        traversal.add(node.val);
        if (traversal.size() == k) {
            return node.val;
        }
        id = inorder(node.right, traversal, k);
        if (id != -1) return id;
        return -1;
    }
}
