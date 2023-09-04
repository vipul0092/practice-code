package code.vipul.leetcode;

/**
 * Created by vgaur created on 23/08/23
 * https://leetcode.com/problems/string-without-aaa-or-bbb/description/
 */
public class StringWithoutAAABBB {

    public static void solve() {
        System.out.println(new StringWithoutAAABBB().strWithout3a3b(4, 1));
    }

    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder();
        solve(a, b, 'a', 'b', sb);
        return sb.toString();
    }

    private void solve(int a, int b, char ca, char cb, StringBuilder sb) {
        if (a == 0 && b == 0) return;
        if (b > a) {
            solve(b, a, cb, ca, sb);
            return;
        }

        int useA = Math.min(a, 2);
        int useB = b == a ? (Math.min(b, 2)) : Math.min(b, 1);
        int tmp = useA;
        while(tmp-- > 0) {
            sb.append(ca);
        }
        tmp = useB;
        while(tmp-- > 0) {
            sb.append(cb);
        }

        solve(a - useA, b - useB, ca, cb, sb);
    }
}
