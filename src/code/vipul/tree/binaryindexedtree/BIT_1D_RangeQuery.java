package code.vipul.tree.binaryindexedtree;

/**
 * 1D Binary indexed tree with support for range updates and range queries
 * https://cp-algorithms.com/data_structures/fenwick.html
 */
public class BIT_1D_RangeQuery {
    private final int n;
    private final int[] bit1;
    private final int[] bit2;

    private BIT_1D_RangeQuery(int n) {
        bit1 = new int[n + 1];
        bit2 = new int[n + 1];
        this.n = n + 1;
    }

    public static BIT_1D_RangeQuery ofSize(int n) {
        return new BIT_1D_RangeQuery(n);
    }

    private void add(int[] b, int idx, int x) {
        while (idx <= n) {
            b[idx] += x;
            idx += idx & -idx;
        }
    }

    public void range_add(int l, int r, int x) {
        add(bit1, l, x);
        add(bit1, r + 1, -x);
        add(bit2, l, x * (l - 1));
        add(bit2, r + 1, -x * r);
    }

    public int range_sum(int l, int r) {
        return prefix_sum(r) - prefix_sum(l - 1);
    }

    private int sum(int[] b, int idx) {
        int total = 0;
        while (idx > 0) {
            total += b[idx];
            idx -= idx & -idx;
        }
        return total;
    }

    private int prefix_sum(int idx) {
        return sum(bit1, idx) * idx - sum(bit2, idx);
    }
}
