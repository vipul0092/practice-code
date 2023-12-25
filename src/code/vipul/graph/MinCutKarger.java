package code.vipul.graph;

import code.vipul.leetcode.graphs.DisjointSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by vgaur created on 25/12/23
 */
public class MinCutKarger {

    public static final int MAX_TIMES = 1;

    public static Set<Edge> minCutKarger(Edge[] edges, int V) {
        int times = MAX_TIMES;
        Map<Integer, Set<Edge>> answers = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();
        while (times-- > 0) {
            var res = minCutKargerInternal(edges, V);
            counts.merge(res.size(), 1, Integer::sum);
            answers.put(res.size(), res);
        }

        int maxcount = -1, maxKey = -1;
        for (var entry : counts.entrySet()) {
            if (maxcount < entry.getValue()) {
                maxcount = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return answers.get(maxKey);
    }

    // O(v^2) algo
    static Set<Edge> minCutKargerInternal(Edge[] edges, int V) {
        Random rand = new Random();
        int vertices = V;
        int E = edges.length;

        DisjointSet dsu = new DisjointSet(V);
        for (int i = 0; i < V; i++) {
            dsu.add(i);
        }

        // Iterating till vertices are greater than 2.
        while (vertices > 2) {
            // Getting a random integer in the range [0, E-1].
            int i = rand.nextInt(E);

            // Finding leader element to which edges[i].u belongs.
            int set1 = dsu.find(edges[i].u());
            // Finding leader element to which edges[i].v belongs.
            int set2 = dsu.find(edges[i].v());

            // If they do not belong to the same set.
            if (set1 != set2) {
                // System.out.println("Contracting vertices "+edges[i].u()+" and "+edges[i].v());
                // Merging vertices u and v into one.
                dsu.union(edges[i].u(), edges[i].v());
                // Reducing count of vertices by 1.
                vertices--;
            }
        }

        // System.out.println("Edges needs to be removed - ");
        // Initializing answer (minCut) to 0.
        var ret = new HashSet<Edge>();
        for (Edge edge : edges) {
            // Finding leader element to which edges[i].u belongs.
            int set1 = dsu.find(edge.u());
            // Finding leader element to which edges[i].v belongs.
            int set2 = dsu.find(edge.v());

            // If they are not in the same set.
            if (set1 != set2) {
                //System.out.println(edges[i].u()+" <----> "+edges[i].v());
                // Increasing the ans.
                ret.add(edge);
            }
        }
        return ret;
    }
}
