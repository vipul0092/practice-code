package code.vipul.tree;

/**
 * Created by vgaur on 09/12/18.
 */
public class TreeNode {

    private final int value;

    public int getValue() {
        return value;
    }

    public TreeNode right;
    public TreeNode left;

    private TreeNode(int val) {
        this.value = val;
    }

    public static TreeNode get(int val) {
        return new TreeNode(val);
    }
}
