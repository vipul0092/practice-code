package code.vipul;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vgaur on 25/12/18.
 */
public class Parenthesis {

    private static int calls = 0;

    public static void solve(int pairs) {
        List<StringBuilder> answers = getString(0, 0,pairs * 2, pairs);
        answers.forEach(System.out::println);
        System.out.println("Calls to recursive function: " + calls + ", no of patterns: " + answers.size());
    }

    private static List<StringBuilder> getString(int openBrackets, int closedBrackets, int total, int maxTypeBrackets) {
        calls++;
        if (total == 0) {
            List<StringBuilder> empty = new ArrayList<>();
            empty.add(new StringBuilder(""));
            return empty;
        }

        boolean canPrefixClosed = openBrackets > closedBrackets;
        boolean canPrefixOpened = openBrackets < maxTypeBrackets;

        List<StringBuilder> closedChild  = new ArrayList<>();
        List<StringBuilder> openChild  = new ArrayList<>();
        List<StringBuilder> answers = new ArrayList<>();

        if (canPrefixOpened) {
            openChild = getString(openBrackets + 1, closedBrackets, total - 1, maxTypeBrackets);
        }
        if (canPrefixClosed) {
            closedChild = getString(openBrackets, closedBrackets + 1,total - 1, maxTypeBrackets);
        }

        openChild.forEach(s -> {
            StringBuilder str = new StringBuilder("(");
            str.append(s);
            answers.add(str);
        });

        closedChild.forEach(s -> {
            StringBuilder str = new StringBuilder(")");
            str.append(s);
            answers.add(str);
        });
        return answers;
    }
}
