package code.vipul.linkedlist;

/**
 * Created by vgaur on 03/11/19.
 */
public class PalindromeList {

    // Time complexity - O(n) and memory - O(n)
    public static void solve(Node head) {
        Result result = dive(head, head);

        System.out.println(result.isSuccess);
    }

    static Result dive(Node head, Node node) {
        if (node.next == null) {
            return head.value.equals(node.value)
                    ? new Result(head.next, true)
                    : new Result(null, false);
        }

        Result result = dive(head, node.next);

        if (!result.isSuccess) {
            return result;
        }

        return result.checkNode.value.equals(node.value)
                ? new Result(result.checkNode.next, true)
                : new Result(null, false);
    }

    static final class Result {
        Node checkNode;
        boolean isSuccess;

        Result(Node node, boolean success) {
            this.checkNode = node;
            this.isSuccess = success;
        }
    }

    public static void makeDataAndRun() {
        Node node1 = Node.of("1");
        Node node2 = Node.of("0");
        node1.next = node2;
        Node node3 = Node.of("0");
        node2.next = node3;
        Node node4 = Node.of("1");
        node3.next = node4;
        Node node5 = Node.of("0");
        node4.next = node5;
        Node node6 = Node.of("0");
        node5.next = node6;
        Node node7 = Node.of("1");
        node6.next = node7;

        solve(node1);
    }
}
