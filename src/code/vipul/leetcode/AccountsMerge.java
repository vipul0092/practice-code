package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 10/07/23
 */
public class AccountsMerge {

    public static void solve() {
        System.out.println(new AccountsMerge().accountsMerge(List.of(
                List.of("Dave", "d0", "d1"),
                List.of("Dave", "d3", "d4"),
                List.of("Dave", "d4", "d5"),
                List.of("Dave", "d2", "d3"),
                List.of("Dave", "d1", "d2")
        )));
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Account> accountMap = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            if (!accountMap.containsKey(name)) {
                accountMap.put(name, new Account(name));
            }
            accountMap.get(name).addEmailGroup(account.subList(1, account.size()));
        }

        List<List<String>> merged = new ArrayList<>();
        accountMap.forEach((n, a) -> merged.addAll(a.getMergedGroups()));
        return merged;
    }

    public static class Account {
        private final String name;

        private int code = 0;

        private final Map<Integer, Set<String>> emailGroups;

        public Account(String name) {
            this.name  = name;
            emailGroups = new HashMap<>();
        }

        public void addEmailGroup(List<String> emails) {
            int groupAlreadyFound = -1;

            for (String email : emails) {
                int currentEmailGroup = findGroup(email);

                // If the current email has a group and a different group has already been found, then merge them
                if (currentEmailGroup != -1 && groupAlreadyFound != -1 && currentEmailGroup != groupAlreadyFound) {
                    int mergedGroup = merge(currentEmailGroup, groupAlreadyFound);
                    addToGroup(email, mergedGroup);
                    groupAlreadyFound = mergedGroup;
                // If a group has already been found, simply add to that
                } else if (groupAlreadyFound != -1) {
                    addToGroup(email, groupAlreadyFound);
                // If a group has not been found yet, but we find an already existing group
                } else if (currentEmailGroup != -1) {
                    addToGroup(email, currentEmailGroup);
                    groupAlreadyFound = currentEmailGroup;
                // The email goes to a completely new group
                } else  {
                    groupAlreadyFound = addInNewGroup(email);
                }
            }
        }

        public List<List<String>> getMergedGroups() {
            List<List<String>> merged = new ArrayList<>();
            for (Map.Entry<Integer, Set<String>> emailEntry : emailGroups.entrySet()){
                List<String> account = new ArrayList<>();
                account.add(name);
                account.addAll(emailEntry.getValue());

                merged.add(account);
            }
            return merged;
        }

        private int findGroup(String email) {
            for (Map.Entry<Integer, Set<String>> entry: emailGroups.entrySet()) {
                if (entry.getValue().contains(email)) {
                    return entry.getKey();
                }
            }
            return -1;
        }

        private int addInNewGroup(String email) {
            int curcode = code; code++;
            emailGroups.put(curcode, new TreeSet<>());
            emailGroups.get(curcode).add(email);
            return curcode;
        }

        private void addToGroup(String email, int group) {
            emailGroups.get(group).add(email);
        }

        private int merge(int g1, int g2) {
            emailGroups.get(g1).addAll(emailGroups.get(g2));
            emailGroups.remove(g2);
            return g1;
        }
    }
}
