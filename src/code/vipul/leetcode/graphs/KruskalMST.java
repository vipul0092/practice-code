package code.vipul.leetcode.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vgaur created on 19/08/23
 */
public class KruskalMST {

    public Result findMST(int n, List<Edge> edges, Edge block, Edge include) {
        DisjointSet djs = new DisjointSet(n);
        for (int i = 0; i < n; i++) djs.add(i);

        List<Edge> mst = new ArrayList<>();
        // Include the edge in the mst, and unionize its vertices
        if (include != null) {
            mst.add(include);
            djs.union(include.start(), include.end());
        }

        for (Edge edge : edges) {
            if ((block != null && edge.id() == block.id())
                    || (include != null && edge.id() == include.id())) {
                continue;
            }
            if (djs.find(edge.start()) != djs.find(edge.end())) {
                mst.add(edge);
                djs.union(edge.start(), edge.end());
            }
        }

        // Make sure that all the vertices are joined together
        for (int i = 0; i < n; i++) {
            // If we find any 2 vertices not connected, then we dont have a valid MST
            if (djs.find(i) != djs.find(0)) {
                mst = new ArrayList<>();
                break;
            }
        }
        return new Result(mst);
    }

    public static class Result {
        private final List<Edge> edges;
        private final int cost;

        public List<Edge> edges() {
            return edges;
        }

        public int cost() {
            return cost;
        }

        public Result(List<Edge> edges) {
            if (edges.isEmpty()) {
                cost = Integer.MAX_VALUE;
            } else {
                cost = edges.stream().mapToInt(e -> e.weight()).sum();
            }
            this.edges = edges;
        }
    }
}
