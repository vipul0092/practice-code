package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 20/08/23
 */
public class AccountsMergeUnionFind {

    public static void solve() {
        System.out.println(new AccountsMergeUnionFind().accountsMerge(List.of(
                List.of("Dave", "d0", "d1"),
                List.of("Dave", "d3", "d4"),
                List.of("Dave", "d4", "d5"),
                List.of("Dave", "d2", "d3"),
                List.of("Dave", "d1", "d2")
        )));
    }

    int[] parent = null;
    int[] size = null;
    public List<List<String>> accountsMerge(List<List<String>> _accounts) {
        int n = _accounts.size();
        parent = new int[n];
        size = new int[n];
        Map<String, Integer> emailGroup = new HashMap<>();


        for (int i = 0; i < n; i++) {
            List<String> acct = _accounts.get(i);
            parent[i] = i;
            size[i] = acct.size() - 1;

            for (String email : acct.subList(1, acct.size())) {
                if (emailGroup.containsKey(email)) {
                    union(emailGroup.get(email), i);
                    emailGroup.put(email, find(i));
                } else {
                    emailGroup.put(email, i);
                }
            }
        }

        Map<Integer, Account> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int parent = find(i);
            List<String> acct = _accounts.get(i);
            if (!groups.containsKey(parent)) {
                groups.put(parent, new Account(acct.get(0), acct.subList(1, acct.size())));
            } else {
                groups.get(parent).addEmails(acct.subList(1, acct.size()));
            }
        }

        return groups.values().stream().map(a -> a.getCompleteList()).toList();
    }

    private int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        int res = find(parent[v]);
        parent[v] = res;
        return res;
    }

    private void union(int g1, int g2) {
        int p1 = find(g1);
        int p2 = find(g2);

        if (p1 == p2) return;

        if (size[p1] >= size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
        } else {
            parent[p1] = p2;
            size[p2] += size[p1];
        }
    }

    public static class Account {

        private final String name;
        private final Collection<String> emails;

        public Account(String name) {
            this.name  = name;
            this.emails = new ArrayList<>();
        }

        public Account(String name, Collection<String> emails) {
            this.name  = name;
            this.emails = new ArrayList<>(emails);
        }

        public void addEmails(Collection<String> emails) {
            this.emails.addAll(emails);
        }

        public List<String> getCompleteList() {
            List<String> ret = new ArrayList<>();
            ret.add(name);
            ret.addAll(new TreeSet<>(emails));
            return ret;
        }
    }
}
