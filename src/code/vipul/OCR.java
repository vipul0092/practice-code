package code.vipul;

import java.util.Stack;

/**
 * Created by vgaur created on 03/06/22
 */
public class OCR {

    public static class Node {
        public Character ch;
        public Integer num;
        public Node next;

        public Node(Character ch) {
            this.ch = ch;
            this.num = null;
        }

        public Node(Integer num) {
            this.num = num;
            this.ch = null;
        }

        @Override
        public String toString() {
            return this.ch == null ? String.valueOf(num) : String.valueOf(ch);
        }
    }

    public static void s() {
        System.out.println(solve());
    }

    public static boolean solve() {
        String one = "10g";
        String two = "a5d3";

        int idx = 0;
        int num = 0;

        int length1 = 0;
        Node n1 = null;
        Node nmove1 = null;
        while (idx < one.length()) {
            Node n = null;
            char c = one.charAt(idx++);
            if (c >= 'a' && c <= 'z') {
                n = new Node(c);
                length1++;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (idx < one.length() && one.charAt(idx) >= '0' && one.charAt(idx) <= '9') {
                    sb.append(one.charAt(idx));
                    idx++;
                }
                num = Integer.parseInt(sb.toString());
                length1 += num;
                n = new Node(num);
            }

            if (nmove1 != null) {
                nmove1.next = n;
            } else {
                n1 = n;
            }
            nmove1 = n;
        }

        idx = 0;
        int length2 = 0;
        Node n2 = null;
        Node nmove2 = null;
        while (idx < two.length()) {
            Node n = null;
            char c = two.charAt(idx++);
            if (c >= 'a' && c <= 'z') {
                n = new Node(c);
                length2++;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (idx < two.length() && two.charAt(idx) >= '0' && two.charAt(idx) <= '9') {
                    sb.append(two.charAt(idx));
                    idx++;
                }
                num = Integer.parseInt(sb.toString());
                length2 += num;
                n = new Node(num);
            }

            if (nmove2 != null) {
                nmove2.next = n;
            } else {
                n2 = n;
            }
            nmove2 = n;
        }

        if (length1 != length2) {
            return false;
        }

        Node ptr1 = n1;
        Node ptr2 = n2;

        boolean isValid = true;

        while(!(ptr1 == null || ptr2 == null)) {
            boolean is1Num = ptr1.num != null;
            boolean is2Num = ptr2.num != null;

            if (!is1Num && !is2Num) {
                if (ptr1.ch != ptr2.ch) {
                    isValid = false;
                    break;
                }
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            } else if (is1Num && is2Num) {
                boolean is1Greater = ptr1.num > ptr2.num;
                if (is1Greater) {
                    ptr1.num = ptr1.num - ptr2.num;
                    ptr2 = ptr2.next;
                    if (ptr1.num == 0) {
                        ptr1 = ptr1.next;
                    }
                } else {
                    ptr2.num = ptr2.num - ptr1.num;
                    ptr1 = ptr1.next;
                    if (ptr2.num == 0) {
                        ptr2 = ptr2.next;
                    }
                }
            } else if (is1Num) {
                ptr1.num -= 1;
                if (ptr1.num == 0) {
                    ptr1 = ptr1.next;
                }
                ptr2 = ptr2.next;
            } else {
                ptr2.num -= 1;
                if (ptr2.num == 0) {
                    ptr2 = ptr2.next;
                }
                ptr1 = ptr1.next;
            }
        }

        return isValid && (ptr1 == null && ptr2 == null);
    }
}
