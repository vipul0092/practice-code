package code.vipul.tree.binaryindexedtree;

/**
 * 2-D Binary indexed tree, supports point updates and range queries
 * https://iq.opengenus.org/2d-fenwick-tree/
 */
public class BIT_2D_RangeQuery {

    private final int m;
    private final int n;
    private final int[][] bit;

    private BIT_2D_RangeQuery(int m, int n) {
        this.m = m + 1;
        this.n = n + 1;
        bit = new int[m + 1][n + 1];
    }

    public static BIT_2D_RangeQuery of(int m, int n) {
        return new BIT_2D_RangeQuery(m, n);
    }

    private int LSB(int x) {
        return x & (-x);
    }

    private int query(int x, int y) {
        int sum = 0;
        for (int x_ = x; x_ > 0; x_ = x_ - LSB(x_)) {
            for (int y_ = y; y_ > 0; y_ = y_ - LSB(y_)) {
                sum = sum + bit[x_][y_];
            }
        }
        return sum;
    }

    public int query(int x1, int y1, int x2, int y2) {
        return (query(x2, y2) - query(x1 - 1, y2) - query(x2, y1 - 1) + query(x1 - 1, y1 - 1));
    }

    public void update(int x, int y, int value) {
        // also update matrix[x][y] if needed.

        for (int x_ = x; x_ < m; x_ = x_ + LSB(x_)) {
            for (int y_ = y; y_ < n; y_ = y_ + LSB(y_)) {
                bit[x_][y_] += value;
            }
        }
    }
}
