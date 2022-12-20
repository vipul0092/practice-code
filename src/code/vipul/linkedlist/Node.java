package code.vipul.linkedlist;

/**
 * Created by vgaur on 03/11/19.
 */
public class Node {

    public final String value;
    public Integer intValue = null;
    public long longVal;
    public Node next;
    public Node prev;

    private Node(String val) {
        this.value = val;
    }

    private Node(int val) {
        this.intValue = val;
        this.value = null;
    }

    private Node(long val) {
        this.longVal = val;
        this.value = null;
    }

    @Override
    public String toString() {
        return value == null ? intValue.toString() : value;
    }

    int intValue() {
        if (intValue == null) {
            intValue = Integer.valueOf(value);
        }
        return intValue;
    }

    public static Node of(String val) {
        return new Node(val);
    }

    public static Node of(long val) {
        return new Node(val);
    }

    public static Node of(int val) {
        return new Node(val);
    }

    static Node copyOf(Node node) {
        Node newNode = of(node.value);
        newNode.next = node.next;
        newNode.prev = node.prev;
        return newNode;
    }

    public static Node intCopyOf(Node node) {
        Node newNode = of(node.intValue);
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
