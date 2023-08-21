package code.vipul.leetcode;
import code.vipul.leetcode.graphs.Edge;
import code.vipul.leetcode.graphs.KruskalMST;

import java.util.*;

/**
 * Created by vgaur created on 19/08/23
 * https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
 */
public class FindCriticalAndNonCriticalEdgesInMST {

    public static void solve() {
        System.out.println(new FindCriticalAndNonCriticalEdgesInMST().findCriticalAndPseudoCriticalEdges(
                6, new int[][]{{0,1,2},{0,2,5},{2,3,5},{1,4,4},{2,5,5},{4,5,2}}
        ));
//        System.out.println(new FindCriticalAndNonCriticalEdgesInMST().findCriticalAndPseudoCriticalEdges(
//                5, new int[][]{{0,1,1},{1,2,1},{2,3,2},{0,3,2},{0,4,3},{3,4,3},{1,4,6}}
//        ));
//        System.out.println(new FindCriticalAndNonCriticalEdgesInMST().findCriticalAndPseudoCriticalEdges(
//                4, new int[][]{{0,1,1},{1,2,1},{2,3,1},{0,3,1}}
//        ));
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] _edges) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < _edges.length; i++) {
            edges.add(new Edge(_edges[i][0], _edges[i][1], _edges[i][2], i));
        }
        edges.sort(Comparator.naturalOrder());
        KruskalMST kruskalMST = new KruskalMST();

        KruskalMST.Result fullMst = kruskalMST.findMST(n, edges, null, null);
        // MST is not possible
        if (fullMst.cost() == Integer.MAX_VALUE) {
            return List.of(List.of(), List.of());
        }

        List<Integer> critical = new ArrayList<>();
        List<Integer> nonCritical = new ArrayList<>();
        for (Edge edge : edges) {
            // try to block the edge and calculate MST,
            // if the cost increases (or MST is not possible at all -> i.e. this is a bridge)
            // that means this is a critical edge
            KruskalMST.Result mstBlock = kruskalMST.findMST(n, edges, edge, null);
            if (fullMst.cost() < mstBlock.cost()) {
                critical.add(edge.id());
            } else {
                // Try to find the MST by including the edge already
                // If we get the same cost as the full mst, that means this edge is part of the MST, but not critical
                KruskalMST.Result mstInclude = kruskalMST.findMST(n, edges, null, edge);
                if (mstInclude.cost() == fullMst.cost()) {
                    nonCritical.add(edge.id());
                }
            }
        }
        return List.of(critical, nonCritical);
    }
}
