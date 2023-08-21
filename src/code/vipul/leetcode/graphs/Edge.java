package code.vipul.leetcode.graphs;

/**
 * Created by vgaur created on 19/08/23
 */
public class Edge implements Comparable<Edge> {

    private static int edgeId = 0;
    private int start;
    private int end;
    private int weight;
    private final int id;

    public int id() {
        return id;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    public int weight() {
        return weight;
    }

    public Edge(int s, int e, int w) {
        this.start = s;
        this.end = e;
        this.weight = w;
        id = edgeId++;
    }

    public Edge(int s, int e, int w, int id) {
        this.start = s;
        this.end = e;
        this.weight = w;
        this.id = id;
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }

    @Override
    public String toString() {
        return String.format("%s <-> %s, weight: %s, id: %s", start, end, weight, id);
    }
}
