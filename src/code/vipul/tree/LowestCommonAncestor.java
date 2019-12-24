package code.vipul.tree;

import static code.vipul.tree.TreeUtils.getDummyTree;

/**
 * Created by vgaur on 11/12/18.
 */
public class LowestCommonAncestor {


    public static void solve() {
        TreeNode root = getDummyTree();
        int value1 = 8;
        int value2 = 9;

        Result result = findLca(root, value1, value2);
        System.out.println("Solution found: " + result.getValue());
    }

    public static Result findLca(TreeNode node, int v1, int v2) {
        if (node == null) return Result.DEFAULT;

        if (node.getValue() == v1 && node.getValue() == v2) return Result.lca(node.getValue());

        boolean isNodeValue = node.getValue() == v1 || node.getValue() == v2;

        Result right = findLca(node.right, v1, v2);
        if (right.lcaFound()) return right;
        Result left = findLca(node.left, v1, v2);
        if (left.lcaFound()) return left;

        if (right.valueFound() && left.valueFound()) {
            return Result.lca(node.getValue());
        }

        boolean areChildrenValue = right.valueFound() || left.valueFound();

        if (areChildrenValue && isNodeValue) {
            return Result.lca(node.getValue());
        }

        return Result.result(areChildrenValue || isNodeValue);
    }

    public static final class Result {

        private final boolean vFound;
        private final Integer value;

        public boolean valueFound() {
            return vFound;
        }

        public Integer getValue() {
            return value;
        }

        public boolean lcaFound() {
            return value != null;
        }

        public static final Result DEFAULT = new Result(false, null);

        private Result(boolean v, Integer value) {
            this.vFound = v;
            this.value = value;
        }

        public static Result result(boolean v) {
            return new Result(v, null);
        }

        public static Result lca(Integer lca) {
            return new Result(false, lca);
        }
    }
}
