package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 08/07/23
 */
public class CourseSchedule {

    public static void solve() {
        int[][] p = new int[][]{{2,0},{1,0},{3,1},{3,2},{1,3}};
        System.out.println(new CourseSchedule().canFinish(4, p));
    }

    // Uses topological sort
    public boolean canFinish(int n, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }
        return !topologicalSort(graph, indegree).isEmpty();
    }

    private List<Integer> topologicalSort(Map<Integer, List<Integer>> graph, int[] indegree) {
        List<Integer> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (Integer key : graph.keySet()) {
            if (indegree[key] == 0) {
                stack.add(key);
            }
        }

        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            visited.add(curr);

            for (Integer prev : graph.get(curr)) {
                indegree[prev]--;
                if (indegree[prev] == 0) {
                    stack.add(prev);
                }
            }
        }

        return visited.size() == graph.size() ? visited : new ArrayList<>();
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> dep = new HashMap<>();
        for (int[] p : prerequisites) {
            dep.putIfAbsent(p[0], new HashSet<>());
            dep.get(p[0]).add(p[1]);
        }

        Set<Integer> complete = new HashSet<>();
        Set<Integer> progress = new HashSet<>();
        for (int c = 0; c < numCourses; c++) {
            if (progress.contains(c)) {
                return false;
            }
            if (complete.size() == numCourses || complete.contains(c)) {
                continue;
            }
            boolean res = dive(c, complete, progress, dep, numCourses);
            if(!res) {
                return false;
            }
        }
        return true;
    }

    public boolean dive(int current,
                        Set<Integer> complete,
                        Set<Integer> progress,
                        Map<Integer, Set<Integer>> dep,
                        int n) {
        if (progress.contains(current)) {
            return false;
        }

        if (complete.size() == n || complete.contains(current)) {
            return true;
        }

        if (!dep.containsKey(current)) {
            complete.add(current);
            return true;
        }
        progress.add(current);

        for (int d : dep.get(current)) {
            if (complete.contains(d)) {
                continue;
            }
            if (progress.contains(d)) {
                return false;
            }
            boolean res = dive(d, complete, progress, dep, n);
            if (!res) {
                return res;
            }
        }
        progress.remove(current);
        complete.add(current);
        return true;
    }
}
