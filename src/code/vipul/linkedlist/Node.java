package code.vipul.linkedlist;

/**
 * Created by vgaur on 03/11/19.
 */
public class Node {

    public final String value;
    public Integer intValue = null;
    public Node next;
    public Node prev;

    private Node(String val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return value;
    }

    int intValue() {
        if (intValue == null) {
            intValue = Integer.valueOf(value);
        }
        return intValue;
    }

    static Node of(String val) {
        return new Node(val);
    }

    static Node copyOf(Node node) {
        Node newNode = of(node.value);
        newNode.next = node.next;
        newNode.prev = node.prev;
        return newNode;
    }

    static void printList(Node head) {
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println();
    }
}
