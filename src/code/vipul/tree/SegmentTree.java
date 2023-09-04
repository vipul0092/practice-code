package code.vipul.tree;

/**
 * Created by vgaur created on 25/08/23
 */
public class SegmentTree {

    public static void solve() {
        int[] arr = new int[]{8,2,5,1,4,5,3,9,6,10};
        SegmentTree t = new SegmentTree(arr);
        System.out.println(t.query(2,7));
        System.out.println(t.query(7,9));
        System.out.println(t.query(3,6));
        t.update(9, 1);
        System.out.println(t.query(7,9));
        t.update(7, 3);
        System.out.println(t.query(7,9));
        t.update(8, 2);
        System.out.println(t.query(7,9));
    }

    private final int[] tree;
    private final int n;

    public SegmentTree(int[] array) {
        this.n = array.length;
        tree = new int[n * 4];
        build(array, 0, 0, n-1);
    }

    public SegmentTree(int n) {
        this.n = n;
        tree = new int[n * 4];
    }

    private void build(int[] array, int tidx, int lo, int hi) {
        if (lo == hi) {
            tree[tidx] = array[lo];
            return;
        }
        int mid = (lo+hi)/2, left = (2*tidx)+1, right = (2*tidx)+2;
        build(array, left, lo, mid);
        build(array, right, mid+1, hi);

        // This is the merge operation -> Depends on what we're storing in the tree (By default this stores the max)
        tree[tidx] = Math.max(tree[left], tree[right]);
    }

    // Query between [lo,hi] interval
    public int query(int lo, int hi) {
        return query(lo, hi, 0, n-1, 0);
    }

    public void update(int idx, int val) {
        update(0, n-1, 0, idx, val);
    }

    private void update(int lo, int hi, int tidx, int idx, int val) {
        if (idx < lo || idx > hi) return;

        if (lo == hi) {
            tree[tidx] = val;
            return;
        }

        int mid = (lo+hi)/2, left = (2*tidx)+1, right = (2*tidx)+2;
        if (idx <= mid) update(lo, mid, left, idx, val);
        else update(mid+1, hi, right, idx, val);

        // This is the merge operation -> Depends on what we're storing in the tree (By default this stores the max)
        tree[tidx] = Math.max(tree[left], tree[right]);
    }

    private int query(int lo, int hi, int tlo, int thi, int idx) {
        if (hi < tlo || lo > thi) return Integer.MIN_VALUE; // <-- This will change according to what the tree stores
        if (lo == tlo && hi == thi) {
            return tree[idx];
        }
        int mid = (tlo+thi)/2;
        // This will change according to what the tree stores
        return Math.max(
                query(lo, Math.min(hi, mid), tlo, mid, (2*idx)+1),
                query(Math.max(lo, mid+1), hi, mid+1, thi, (2*idx)+2)
        );
    }
}
