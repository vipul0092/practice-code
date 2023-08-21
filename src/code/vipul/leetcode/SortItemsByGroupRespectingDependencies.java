package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 20/08/23
 * https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/description/
 */
public class SortItemsByGroupRespectingDependencies {

    public static void solve() {
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(
                8, 2, new int[]{-1, -1, 1, 0, 0, 1, 0, -1},
                List.of(List.of(), List.of(6), List.of(5), List.of(6), List.of(3, 6)
                        , List.of(), List.of(), List.of())
        )));
    }

    public int[] sortItems(int n, int m, int[] groups, List<List<Integer>> beforeItems) {
        int groupId = m;
        Map<Integer, List<Integer>> itemGraph = new HashMap<>();
        Map<Integer, List<Integer>> groupGraph = new HashMap<>();

        // Assign group ids to unassigned items, where the group will have a single item
        for (int i = 0; i < n; ++i) {
            if (groups[i] == -1) {
                groups[i] = groupId++;
            }
            itemGraph.put(i, new ArrayList<>());
            groupGraph.put(groups[i], new ArrayList<>());
        }
        int[] indegreeItems = new int[n];
        int[] indegreeGroups = new int[groupId];


        for (int i = 0; i < n; i++) {
            List<Integer> before = beforeItems.get(i);
            for (int b : before) {
                itemGraph.get(b).add(i);
                indegreeItems[i]++;

                // Add dependency between groups only if they are different
                if (groups[b] != groups[i]) {
                    groupGraph.get(groups[b]).add(groups[i]);
                    indegreeGroups[groups[i]]++;
                }
            }
        }

        List<Integer> sortedItems = topologicalSort(itemGraph, indegreeItems);
        List<Integer> sortedGroups = topologicalSort(groupGraph, indegreeGroups);

        // If the topological sort is not valid for either of these, then we dont have an answer
        if (sortedItems.isEmpty() || sortedGroups.isEmpty()) {
            return new int[]{};
        }

        Map<Integer, List<Integer>> sortedItemsPerGroup = new HashMap<>();
        for (int item : sortedItems) {
            int group = groups[item];
            if (!sortedItemsPerGroup.containsKey(group)) {
                sortedItemsPerGroup.put(group, new ArrayList<>());
            }
            sortedItemsPerGroup.get(group).add(item);
        }

        List<Integer> finalOrder = new ArrayList<>();
        for (int group : sortedGroups) {
            finalOrder.addAll(sortedItemsPerGroup.get(group));
        }
        return finalOrder.stream().mapToInt(i -> i).toArray();
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
}
