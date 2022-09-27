package code.vipul.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs2.INPUT_19;

/**
 * https://adventofcode.com/2020/day/19
 */
public class Solve19 {

    // Answers are 3 and 12
    private static final String INPUT = "42: 9 14 | 10 1\n" +
            "9: 14 27 | 1 26\n" +
            "10: 23 14 | 28 1\n" +
            "1: \"a\"\n" +
            "11: 42 31\n" +
            "5: 1 14 | 15 1\n" +
            "19: 14 1 | 14 14\n" +
            "12: 24 14 | 19 1\n" +
            "16: 15 1 | 14 14\n" +
            "31: 14 17 | 1 13\n" +
            "6: 14 14 | 1 14\n" +
            "2: 1 24 | 14 4\n" +
            "0: 8 11\n" +
            "13: 14 3 | 1 12\n" +
            "15: 1 | 14\n" +
            "17: 14 2 | 1 7\n" +
            "23: 25 1 | 22 14\n" +
            "28: 16 1\n" +
            "4: 1 1\n" +
            "20: 14 14 | 1 15\n" +
            "3: 5 14 | 16 1\n" +
            "27: 1 6 | 14 18\n" +
            "14: \"b\"\n" +
            "21: 14 1 | 1 14\n" +
            "25: 1 1 | 1 14\n" +
            "22: 14 14\n" +
            "8: 42\n" +
            "26: 14 22 | 1 20\n" +
            "18: 15 15\n" +
            "7: 14 5 | 1 21\n" +
            "24: 14 1\n" +
            "\n" +
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa\n" +
            "bbabbbbaabaabba\n" +
            "babbbbaabbbbbabbbbbbaabaaabaaa\n" +
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa\n" +
            "bbbbbbbaaaabbbbaaabbabaaa\n" +
            "bbbababbbbaaaaaaaabbababaaababaabab\n" +
            "ababaaaaaabaaab\n" +
            "ababaaaaabbbaba\n" +
            "baabbaaaabbaaaababbaababb\n" +
            "abbbbabbbbaaaababbbbbbaaaababb\n" +
            "aaaaabbaabaaaaababaa\n" +
            "aaaabbaaaabbaaa\n" +
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa\n" +
            "babaaabbbaaabaababbaabababaaab\n" +
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba";

    private static Map<Integer, List<List<Integer>>> linksPerNode;
    private static Map<Integer, Node> nodeMap;

    public static void solve() {
        linksPerNode = new HashMap<>();
        nodeMap = new HashMap<>();
        String[] lines = INPUT_19.split("\n");
        boolean stringsCheckpointReached = false;

        List<String> stringsToCheck = new ArrayList<>();

        for (String line : lines) {
            if (line.length() == 0) {
                stringsCheckpointReached = true;
                continue;
            } else if (stringsCheckpointReached) {
                stringsToCheck.add(line);
                continue;
            }
            String[] parts = line.split(": ");
            int node = Integer.parseInt(parts[0]);
            if (parts[1].startsWith("\"")) {
                nodeMap.put(node, new Node(node));
                nodeMap.get(node).addLinkedLetters(parts[1].substring(1, parts[1].length() - 1));
            } else {
                linksPerNode.put(node, new ArrayList<>());
                String[] subparts = parts[1].split(" \\| ");
                for (String subpart : subparts) {
                    linksPerNode.get(node).add(Arrays.stream(subpart.split(" "))
                            .map(s -> Integer.parseInt(s))
                            .collect(Collectors.toList())
                    );
                }
            }
        }

        List<Node> zeroLinkedNodes = new ArrayList<>();
        List<Integer> zeroLinks = linksPerNode.get(0).get(0);
        for (Integer zeroLink : zeroLinks) {
            zeroLinkedNodes.add(link(zeroLink));
        }

        //Create a trie from the linked nodes
        List<TrieNode> tries = new ArrayList<>();

        for (Node zeroLinkedNode : zeroLinkedNodes) {
            TrieNode parent = new TrieNode('\0');
            for (int i = 0; i < zeroLinkedNode.linkedStrings.size(); i++) {
                populateTrie(parent, zeroLinkedNode.linkedStrings.get(i));
            }
            tries.add(parent);
        }

        int sum = 0;

        List<String> unmatcheds = new ArrayList<>();
        for (String toMatch : stringsToCheck) {
            boolean valid = match(tries, toMatch);
            if (valid) {
                sum++;
            } else {
                unmatcheds.add(toMatch);
            }
        }
        System.out.println("Answer: " + sum); //205
        // ---- PART 1 ENDS ----

        // ---- PART 2 STARTS ----

        // Now, 8 -> 42 | 42 8 ; This means we can match 42 as many times as we want, say 'x'
        // and, 11 -> 42 31 | 42 11 31 ; This mean we can first match 42 'y' times and then 31 'y' times
        // i.e. the match becomes x42 + y42 + y31, where x and y >= 1
        Map<Integer, TrieNode> trieMap = new HashMap<>(); // this'll store the trie for 42 and 31
        List<Node> _4231nodes = new ArrayList<>();
        _4231nodes.add(link(42));
        _4231nodes.add(link(31));

        int size = -1; // This is the same for both 42 and 31 in the test cases
        // so Im coding taking that assumption
        for (Node linkedNodes : _4231nodes) {
            TrieNode parent = new TrieNode('\0', linkedNodes.linkedStrings.get(0).length());
            for (int i = 0; i < linkedNodes.linkedStrings.size(); i++) {
                populateTrie(parent, linkedNodes.linkedStrings.get(i));
            }
            trieMap.put(linkedNodes.number, parent);
            size = parent.maxlen;
        }

        for (String unmatched : unmatcheds) {
            // Now x42-size + y42-size + y31-size = string length (ls)
            // => x + 2y = ls / size
            // so if ls % size != 0, simply skip that string
            // if that passes, then put values of x and y and keep matching
            if (unmatched.length() % size != 0) continue;
            int xplus2y = unmatched.length() / size;
            boolean isEven = xplus2y % 2 == 0;
            // if x + 2y is even, then x can only take even values because 2y is even
            // if x + 2y is odd, then x can only take odd values because 2y is even
            int x = isEven ? 2 : 1;
            int y = (xplus2y - x) / 2;
            boolean valid;
            // Keep matching the string with the generated tries for different values of x and y
            do {
                List<TrieNode> t = getTrieList(x, y, trieMap);
                valid = match(t, unmatched);
                if (valid) {
                    break;
                }
                x += 2;
                y = (xplus2y - x) / 2;
            } while (y >= 1);

            if (valid) {
                sum++;
            }
        }

        System.out.println("Answer Part 2: " + sum); //329
    }

    private static List<TrieNode> getTrieList(int x, int y, Map<Integer, TrieNode> trieMap) {
        List<TrieNode> tries = new ArrayList<>();
        if (x < 1 || y < 1) {
            return tries;
        }
        // x42 + y42 + y31
        while (x-- > 0) {
            tries.add(trieMap.get(42));
        }

        int temp = y;
        while (temp-- > 0) {
            tries.add(trieMap.get(42));
        }
        while (y-- > 0) {
            tries.add(trieMap.get(31));
        }
        return tries;
    }

    private static boolean match(List<TrieNode> tries, String toMatch) {
        int indexUntil = 0;
        boolean valid = true;
        for (int i = 0; i < tries.size(); i++) {
            indexUntil = matchWithTrie(toMatch, tries.get(i), indexUntil, i == tries.size() - 1);
            if (indexUntil == -1 || (indexUntil == toMatch.length() && i != tries.size() - 1)) {
                valid = false;
                break;
            }
        }
        return valid && indexUntil == toMatch.length();
    }

    private static int matchWithTrie(String str, TrieNode trie, int startFromIndex, boolean isLastTrie) {
        TrieNode current = trie;
        int i = startFromIndex;
        for (; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (current.hasNoLinks()) {
                return i;
            } else if (!current.hasLinkToChar(ch)) {
                return -1;
            } else {
                current = current.getLink(ch);
            }
        }
        return isLastTrie ? (current.hasNoLinks() ? i : -1) : i;
    }

    private static void populateTrie(TrieNode parent, String str) {
        TrieNode prev = null;
        for (char ch : str.toCharArray()) {
            if (prev != null && prev.hasLinkToChar(ch)) {
                prev = prev.getLink(ch);
            } else if (prev != null) {
                TrieNode n = new TrieNode(ch);
                prev.addLinkToChar(ch, n);
                prev = n;
            } else {
                if (parent.hasLinkToChar(ch)) {
                    prev = parent.getLink(ch);
                } else {
                    TrieNode n = new TrieNode(ch);
                    parent.addLinkToChar(ch, n);
                    prev = n;
                }
            }
        }
    }

    private static Node link(int nodenum) {
        if (nodeMap.containsKey(nodenum)) {
            return nodeMap.get(nodenum);
        }
        nodeMap.put(nodenum, new Node(nodenum));

        for (List<Integer> links : linksPerNode.get(nodenum)) {
            boolean hasInitializedCurrent = false;
            List<String> currentIterationLinkedStrings = new ArrayList<>();
            for (Integer link : links) {
                Node childNode = link(link);
                if (!hasInitializedCurrent) {
                    currentIterationLinkedStrings.addAll(childNode.linkedStrings);
                    hasInitializedCurrent = true;
                } else {
                    List<String> newCurrentIterationStrings = new ArrayList<>();
                    for (String childNodeLinkedString : childNode.linkedStrings) {
                        for (String currentLinkedString : currentIterationLinkedStrings) {
                            newCurrentIterationStrings.add(currentLinkedString + childNodeLinkedString);
                        }
                    }
                    currentIterationLinkedStrings = newCurrentIterationStrings;
                }
            }
            nodeMap.get(nodenum).addLinkedLetters(currentIterationLinkedStrings);
        }
        return nodeMap.get(nodenum);
    }

    public static class Node {
        private final int number;
        private final List<String> linkedStrings;

        public Node(int num) {
            this.number = num;
            this.linkedStrings = new ArrayList<>();
        }

        public void addLinkedLetters(String l) {
            this.linkedStrings.add(l);
        }

        public void addLinkedLetters(List<String> l) {
            this.linkedStrings.addAll(l);
        }

        public String toString() {
            return "node number: " + number;
        }
    }

    public static class TrieNode {
        private final char ch;
        private final int maxlen;
        private Map<Character, TrieNode> links;

        TrieNode(char ch) {
            this.ch = ch;
            this.maxlen = -1;
            this.links = new HashMap<>();
        }

        TrieNode(char ch, int maxlen) {
            this.ch = ch;
            this.maxlen = maxlen;
            this.links = new HashMap<>();
        }

        public String toString() {
            return String.valueOf(ch);
        }

        public boolean hasLinkToChar(char ch) {
            return links.containsKey(ch);
        }

        public boolean hasNoLinks() {
            return links.isEmpty();
        }

        public void addLinkToChar(char ch, TrieNode node) {
            this.links.put(ch, node);
        }

        public TrieNode getLink(char ch) {
            return this.links.get(ch);
        }
    }
}
