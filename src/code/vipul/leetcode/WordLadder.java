package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 10/07/23
 */
public class WordLadder {

    public static void solve() {
        System.out.println(new WordLadder().ladderLength("hit", "cog", List.of(
                "hot","dot","dog","lot","log","cog"
        )));

        System.out.println(new WordLadder().ladderLength("a", "c", List.of(
                "a","b","c","d","e","f"
        )));

        System.out.println(new WordLadder().ladderLength("hot", "dog", List.of(
                "hot", "dot", "dog"
        )));

        System.out.println(new WordLadder().ladderLength("qa", "sq", List.of(
                "si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"
        )));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<Integer> beginDiffs = new HashSet<>();
        Set<Integer> beginZeroDiffs = new HashSet<>();
        int targetIndex = -1;

        Map<Integer, Set<Integer>> mutualDiffs = new HashMap<>();

        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);

            int diff = getDiff(beginWord, word);
            if (diff == 1) {
                beginDiffs.add(i);
            } else if (diff == 0) {
                beginZeroDiffs.add(i);
            }

            diff = getDiff(endWord, word);
            if (diff == 0) {
                targetIndex = i;
            }

            mutualDiffs.putIfAbsent(i, new HashSet<>());
            for (int j = i + 1; j < wordList.size(); j++) {
                String word2 = wordList.get(j);
                diff = getDiff(word2, word);
                if (diff == 1) {
                    mutualDiffs.get(i).add(j);
                    mutualDiffs.putIfAbsent(j, new HashSet<>());
                    mutualDiffs.get(j).add(i);
                }
            }
        }

        if ((beginDiffs.isEmpty() && beginZeroDiffs.isEmpty()) || targetIndex == -1) {
            return 0;
        }

        int minimum = Integer.MAX_VALUE;

        for (int begin : beginZeroDiffs) {
            Set<Integer> visited = new HashSet<>(); visited.add(begin);
            Queue<Integer> queue = new ArrayDeque<>(); queue.add(begin);
            Queue<Integer> len = new ArrayDeque<>(); len.add(0);
            int res = 0;
            boolean found = false;
            while(!queue.isEmpty()) {
                int curr = queue.remove();
                int length = len.remove();
                if (curr == targetIndex) {
                    found = true;
                    res = length;
                    break;
                }
                for (int n : mutualDiffs.getOrDefault(curr, new HashSet<>())) {
                    if (!visited.contains(n)) {
                        visited.add(n);
                        queue.add(n);
                        len.add(length + 1);
                    }
                }
            }

            if (found) {
                minimum = Math.min(res, minimum);
            }
        }

        for (int begin : beginDiffs) {
            Set<Integer> visited = new HashSet<>(); visited.add(begin);
            Queue<Integer> queue = new ArrayDeque<>(); queue.add(begin);
            Queue<Integer> len = new ArrayDeque<>(); len.add(1);
            int res = 0;
            boolean found = false;
            while(!queue.isEmpty()) {
                int curr = queue.remove();
                int length = len.remove();
                if (curr == targetIndex) {
                    found = true;
                    res = length;
                    break;
                }
                for (int n : mutualDiffs.getOrDefault(curr, new HashSet<>())) {
                    if (!visited.contains(n)) {
                        visited.add(n);
                        queue.add(n);
                        len.add(length + 1);
                    }
                }
            }

            if (found) {
                minimum = Math.min(res, minimum);
            }
        }
        return minimum == Integer.MAX_VALUE ? 0 : minimum + 1;
    }

    private int getDiff(String s1, String s2) {
        int diffs = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffs++;
            }
        }
        return diffs;
    }
}
