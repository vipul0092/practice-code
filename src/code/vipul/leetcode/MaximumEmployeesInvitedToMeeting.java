package code.vipul.leetcode;
import java.util.*;

/**
 * https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/description
 * VERY HARD
 * Explanation: https://www.youtube.com/watch?v=aPBELJa-LM8
 */
public class MaximumEmployeesInvitedToMeeting {

    public static void solve() {
        int[] fav = new int[]{6,4,4,5,0,3,3}; // Answer is 6
        System.out.println(new MaximumEmployeesInvitedToMeeting().maximumInvitations(fav));
    }

    public int maximumInvitations(int[] favorite) {
        int maxCycle = 0, n = favorite.length;
        List<int[]> doubleCycles = new ArrayList<>();

        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] > 0) continue;
            // Look for cycles
            int curr = i;
            int len = 0;
            Set<Integer> curVisited = new HashSet<>();
            while(visited[curr] == 0 && !curVisited.contains(curr)) {
                curVisited.add(curr);
                len++;
                visited[curr] = len;
                curr = favorite[curr];
            }

            if (!curVisited.contains(curr)) continue; // not a cycle

            int cycleLength = len - visited[curr] + 1;
            maxCycle = Math.max(maxCycle, cycleLength);
            if (cycleLength == 2) {
                doubleCycles.add(new int[]{curr, favorite[curr]});
            }
        }

        // Make reverse graph
        Map<Integer, List<Integer>> revgraph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int fav = favorite[i];
            revgraph.putIfAbsent(fav, new ArrayList<>());
            revgraph.get(fav).add(i);
        }

        int doubleCycleChainLengths = 0;
        for (int[] doubleCycle : doubleCycles) {
            int n1 = doubleCycle[0], n2 = doubleCycle[1];

            int len1 = bfs(revgraph, n1, n2);
            int len2 = bfs(revgraph, n2, n1);

            int chainLen = len1+len2+2;
            doubleCycleChainLengths += chainLen;
        }


        return Math.max(doubleCycleChainLengths, maxCycle);
    }

    private int bfs(Map<Integer, List<Integer>> graph, int start, int parent) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start, 0});

        int maxlen = 0;
        while(!q.isEmpty()) {
            int[] ele = q.poll();
            int n = ele[0], len = ele[1];
            maxlen = Math.max(len, maxlen);

            for (int nei : graph.getOrDefault(n, new ArrayList<>())) {
                if (nei == parent) continue;
                q.add(new int[]{nei, len+1});
            }
        }
        return maxlen;
    }
}
