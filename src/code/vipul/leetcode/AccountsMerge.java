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

    public static class EmailNode {
        private final String email;
        private final List<EmailNode> links;

        public EmailNode(String email) {
            this.email = email;
            this.links = new ArrayList<>();
        }

        public void link(EmailNode node) {
            this.links.add(node);
        }
    }

    public static class Account {

        private final String name;
        private final Map<String, EmailNode> emailNodes;

        public Account(String name) {
            this.name  = name;
            emailNodes = new HashMap<>();
        }

        public void addEmailGroup(List<String> emails) {
            EmailNode last = null;
            for (String email : emails) {
                EmailNode node;
                if (emailNodes.containsKey(email)) {
                    node = emailNodes.get(email);
                } else {
                    node = new EmailNode(email);
                    emailNodes.put(email, node);
                }

                if (last != null) {
                    node.link(last);
                    last.link(node);
                }
                last = node;
            }
        }

        public List<List<String>> getMergedGroups() {
            List<List<String>> merged = new ArrayList<>();
            Set<String> remainingEmails = new HashSet<>(emailNodes.keySet());

            for (String email : emailNodes.keySet()) {
                if (!remainingEmails.contains(email)) {
                    continue;
                }
                Queue<String> queue = new ArrayDeque<>();
                queue.add(email);
                remainingEmails.remove(email);
                Set<String> mergedEmails = new TreeSet<>();
                mergedEmails.add(email);

                while(!queue.isEmpty()) {
                    EmailNode currEmail = emailNodes.get(queue.remove());
                    currEmail.links.stream()
                            .filter(e -> remainingEmails.contains(e.email))
                            .forEach(e -> {
                                remainingEmails.remove(e.email);
                                mergedEmails.add(e.email);
                                queue.add(e.email);
                            });
                }

                List<String> mergedAccount = new ArrayList<>();
                mergedAccount.add(name);
                mergedAccount.addAll(mergedEmails);

                merged.add(mergedAccount);
            }
            return merged;
        }
    }
}
