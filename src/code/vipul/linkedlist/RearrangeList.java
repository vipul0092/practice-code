package code.vipul.linkedlist;

import static code.vipul.linkedlist.Node.copyOf;
import static code.vipul.linkedlist.Node.printList;

/**
 * Created by vgaur on 03/11/19.
 */
public class RearrangeList {

    public static Node solve(Node head, int pivot) {
        Node insertPosition = null;
        Node prev = null;

        Node node = head;
        boolean hasPivotBeenFound = false;
        while (node != null) {
            boolean hasShifted = false;
            if (node.intValue() < pivot) {
                if (!hasPivotBeenFound) {
                    insertPosition = node;
                } else if (insertPosition != null) {
                    Node nextOfIp = insertPosition.next;
                    Node currentNodeCopy = copyOf(node);
                    prev.next = currentNodeCopy.next;
                    insertPosition.next = currentNodeCopy;
                    currentNodeCopy.next = nextOfIp;
                    insertPosition = currentNodeCopy;
                    hasShifted = true;
                } else {
                    Node currentNodeCopy = copyOf(node);
                    currentNodeCopy.next = head;
                    head = currentNodeCopy;
                    insertPosition = currentNodeCopy;
                    hasShifted = true;
                }
            } else {
                hasPivotBeenFound = true;
            }
            prev = hasShifted ? prev : node;
            node = node.next;
        }
        return head;
    }

    public static void makeDataAndRun() {
        Node node1 = Node.of("3");
        Node node2 = Node.of("5"); node1.next = node2;
        Node node3 = Node.of("8"); node2.next = node3;
        Node node4 = Node.of("5"); node3.next = node4;
        Node node5 = Node.of("10"); node4.next = node5;
        Node node6 = Node.of("2"); node5.next = node6;
        Node node7 = Node.of("1"); node6.next = node7;

        printList(copyOf(node1));
        Node modifiedHead = solve(copyOf(node1), 10);
        printList(copyOf(modifiedHead));
    }
}
