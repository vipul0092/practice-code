package code.vipul.leetcode.graphs;

/**
 * Created by vgaur created on 19/08/23
 */
public class DisjointSet {

    // Stores the representative of a set/tree
    private final int[] parent;

    // Stores the size of the set/descendants of the parent of a tree
    private final int[] size;

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
    }

    /**
     * Add an item as a single set which represents itself
     */
    public void add(int item) {
        parent[item] = item;
        size[item] = 1; // by default each item is a single set
    }

    /**
     * Which set is the item part of, returns the representative of the set
     */
    public int find(int item) {
        if (item == parent[item]) {
            return item;
        }
        int res = find(parent[item]);
        parent[item] = res;
        return res;
    }

    /**
     * Merge the sets that the items are a part of
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        // They are already in the same set
        if (p1 == p2) return;

        int size1 = size[p1];
        int size2 = size[p2];
        // Make the item having higher tree size the parent of the one having lower size
        if (size1 <= size2) {
            parent[p1] = p2;
            size[p2] += size[p1];
        } else {
            parent[p2] = p1;
            size[p1] += size[p2];
        }
    }
}
