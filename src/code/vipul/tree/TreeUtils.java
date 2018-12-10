package code.vipul.tree;

/**
 * Created by vgaur on 09/12/18.
 */
public class TreeUtils {

    public static void printInOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        printInOrder(node.left);
        System.out.print(node.getValue() + " ");
        printInOrder(node.right);
    }

    public static TreeNode getDummyTree() {
        int size = 14;
        TreeNode[] nodes = new TreeNode[size];

        while (size-- > 0) {
            nodes[size] = TreeNode.get(size);
        }

        nodes[1].right = nodes[5];
        nodes[1].left = nodes[2];
        nodes[5].left = nodes[8];
        nodes[5].right = nodes[9];
        nodes[2].right = nodes[4];
        nodes[2].left = nodes[3];
        nodes[4].left = nodes[7];
        nodes[3].left = nodes[6];
        nodes[7].left = nodes[10];
        nodes[7].right = nodes[11];
        nodes[11].left = nodes[12];
        nodes[11].right = nodes[13];

        return nodes[1];
    }
}
