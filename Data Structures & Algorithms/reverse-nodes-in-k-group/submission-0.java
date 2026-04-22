/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// https://neetcode.io/problems/reverse-nodes-in-k-group/question?list=neetcode150
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        return reverse(head, k);
    }

    record RevResult(ListNode head, ListNode start, ListNode nextItem){}

    private ListNode reverse(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode cur = head;
        RevResult reversed = reverseK(cur, k);

        ListNode rest = reverse(reversed.nextItem, k);
        if (reversed.head != null) reversed.head.next = rest;
        return reversed.start;
    }

    private RevResult reverseK(ListNode cur, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode head = cur;
        int count = k;
        while (count-- > 0 && head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.size() != k) {
            return new RevResult(null, cur, null);
        }
        head = stack.pop();
        ListNode nextItem = head.next, start = head;
        while(!stack.isEmpty()) {
            ListNode item = stack.pop();
            head.next = item;
            head = item;
        }
        return new RevResult(head, start, nextItem);
    }
}
