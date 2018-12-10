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

        boolean isCurrentV1 = node.getValue() == v1;
        boolean isCurrentV2 = node.getValue() == v2;

        if (isCurrentV1 && isCurrentV2) return Result.lca(node.getValue());

        Result right = findLca(node.right, v1, v2);
        if (right.lcaFound()) return right;
        Result left = findLca(node.left, v1, v2);
        if (left.lcaFound()) return left;

        if ((right.isV1() && left.isV2()) || (right.isV2() && left.isV1())) {
            return Result.lca(node.getValue());
        }

        boolean areChildrenV1 = right.isV1() || left.isV1();
        boolean areChildrenV2 = right.isV2() || left.isV2();

        if ((areChildrenV1 && isCurrentV2) || (areChildrenV2 && isCurrentV1)) {
            return Result.lca(node.getValue());
        }

        return Result.result(areChildrenV1 || isCurrentV1, areChildrenV2 || isCurrentV2);
    }

    public static final class Result {

        private final boolean v1;
        private final boolean v2;
        private final Integer value;

        public boolean isV1() {
            return v1;
        }

        public boolean isV2() {
            return v2;
        }

        public Integer getValue() {
            return value;
        }

        public boolean lcaFound() {
            return value != null;
        }

        public static final Result DEFAULT = new Result(false, false, null);

        private Result(boolean v1, boolean v2, Integer value) {
            this.v1 = v1;
            this.v2 = v2;
            this.value = value;
        }

        public static Result result(boolean v1, boolean v2) {
            return new Result(v1, v2, null);
        }

        public static Result lca(Integer lca) {
            return new Result(false, false, lca);
        }
    }
}
