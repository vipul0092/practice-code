package code.vipul.tree.binaryindexedtree;

/**
 * 1-D Binary indexed tree with support for single point updates, range updates and point queries
 * https://cp-algorithms.com/data_structures/fenwick.html
 */
public class BIT_1D_PointQuery {

    private final int n;
    private final int[] bit;

    private BIT_1D_PointQuery(int n) {
        this.n = n + 1;
        bit = new int[n + 1];
    }

    public static BIT_1D_PointQuery ofSize(int n) {
        return new BIT_1D_PointQuery(n);
    }

    public void add(int idx, int val) {
        for (++idx; idx < n; idx += idx & -idx)
            bit[idx] += val;
    }

    public void range_add(int l, int r, int val) {
        add(l, val);
        add(r + 1, -val);
    }

    public int point_query(int idx) {
        int ret = 0;
        for (++idx; idx > 0; idx -= idx & -idx)
            ret += bit[idx];
        return ret;
    }
}

