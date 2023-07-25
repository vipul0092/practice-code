package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 17/07/23
 * https://leetcode.com/problems/smallest-sufficient-team/ (HARD)
 * AC
 */
public class SmallestSufficientTeam {

    public static void solve() {
        System.out.println(Arrays.toString(new SmallestSufficientTeam()
                .smallestSufficientTeam(
                        new String[]{"algorithms","math","java","reactjs","csharp","aws"},
                        List.of(
                                List.of("algorithms","math","java"),
                                List.of("algorithms","math","reactjs"),
                                List.of("java","csharp","aws"),
                                List.of("reactjs","csharp"),
                                List.of("csharp","math"),
                                List.of("aws","java")
                        )
                )));
        System.out.println(Arrays.toString(new SmallestSufficientTeam()
                .smallestSufficientTeam(
                        new String[]{"cdkpfwkhlfbps","hnvepiymrmb","cqrdrqty","pxivftxovnpf","uefdllzzmvpaicyl","idsyvyl"},
                        List.of(
                                List.of(),
                                List.of("hnvepiymrmb"),
                                List.of("uefdllzzmvpaicyl"),
                                List.of(),
                                List.of("hnvepiymrmb","cqrdrqty"),
                                List.of("pxivftxovnpf"),
                                List.of("hnvepiymrmb","pxivftxovnpf"),
                                List.of("hnvepiymrmb"),
                                List.of("cdkpfwkhlfbps"),
                                List.of("idsyvyl"),
                                List.of("cdkpfwkhlfbps","uefdllzzmvpaicyl"),
                                List.of("cdkpfwkhlfbps"),
                                List.of("uefdllzzmvpaicyl"),
                                List.of("pxivftxovnpf","uefdllzzmvpaicyl"),
                                List.of(),
                                List.of("cqrdrqty"),
                                List.of(),
                                List.of("cqrdrqty","pxivftxovnpf","idsyvyl"),
                                List.of("hnvepiymrmb","idsyvyl"),
                                List.of()
                        )
                )));
    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int[] value = new int[people.size()];

        Map<String, Integer> skills = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++) {
            skills.put(req_skills[i], i);
        }

        int maxVal = (1 << req_skills.length) - 1;

        for (int i = 0; i < people.size(); i++) {
            List<String> peopleSkills = people.get(i);

            int val = 0;
            for (String s : peopleSkills) {
                val |= (1 << skills.get(s));
            }
            value[i] = val;
        }

        int n = people.size();
        int[][] dp = new int[n + 1][maxVal + 1];
        int max = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= maxVal; j++) {
                if (j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[i][j] = max;
                } else {
                    int jother = j - (j & value[i-1]);
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][jother] == max ? max : 1 + dp[i-1][jother]);
                }
            }
        }

        List<Integer> indexes = new ArrayList<>();
        int i = n; int j = maxVal;
        while(i > 0 && j > 0) {
            int v1 = dp[i-1][j];
            int jother = j - (j & value[i-1]);
            int v2 = 1 + dp[i-1][jother];

            if (v2 < v1) {
                indexes.add(i - 1);
                i--;
                j = jother;
            } else {
                i--;
            }
        }

        int[] ret = new int[indexes.size()]; int idx = 0;
        for (int index : indexes) {
            ret[idx++] = index;
        }
        return ret;
    }
}
