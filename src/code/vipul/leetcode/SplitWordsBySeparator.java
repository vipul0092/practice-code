package code.vipul.leetcode;
import java.util.*;

/**
 * Created by vgaur created on 24/07/23
 */
public class SplitWordsBySeparator {

    public static void solve() {
        System.out.println(
                new SplitWordsBySeparator().splitWordsBySeparator(
                        List.of("one.two.three","four.five","six"), '.'
                )
        );
    }

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb = new StringBuilder();
            for (char ch : word.toCharArray()) {
                if (ch == separator) {
                    String str = sb.toString();
                    sb = new StringBuilder();
                    if (!str.isEmpty()) {
                        ret.add(str);
                    }
                } else {
                    sb.append(ch);
                }
            }
            String str = sb.toString();
            if (!str.isEmpty()) {
                ret.add(str);
            }
        }
        return ret;
    }
}
