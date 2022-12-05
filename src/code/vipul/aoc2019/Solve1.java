package code.vipul.aoc2019;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2019/day/1
 */
public class Solve1 {

    private static String input = "129880\n" +
            "115705\n" +
            "118585\n" +
            "124631\n" +
            "81050\n" +
            "138183\n" +
            "61173\n" +
            "95354\n" +
            "130788\n" +
            "89082\n" +
            "75554\n" +
            "110104\n" +
            "140528\n" +
            "71783\n" +
            "125889\n" +
            "126602\n" +
            "73089\n" +
            "76822\n" +
            "51774\n" +
            "85940\n" +
            "81004\n" +
            "149584\n" +
            "145921\n" +
            "105570\n" +
            "142370\n" +
            "80823\n" +
            "147779\n" +
            "115651\n" +
            "70250\n" +
            "67763\n" +
            "128192\n" +
            "51298\n" +
            "134963\n" +
            "73510\n" +
            "90976\n" +
            "141216\n" +
            "65134\n" +
            "140468\n" +
            "143998\n" +
            "101711\n" +
            "88477\n" +
            "53335\n" +
            "138328\n" +
            "141186\n" +
            "149804\n" +
            "64950\n" +
            "53107\n" +
            "54648\n" +
            "97557\n" +
            "85927\n" +
            "125038\n" +
            "80514\n" +
            "64912\n" +
            "140591\n" +
            "114229\n" +
            "57089\n" +
            "123464\n" +
            "127572\n" +
            "137169\n" +
            "146550\n" +
            "51138\n" +
            "115504\n" +
            "128034\n" +
            "147244\n" +
            "108107\n" +
            "101205\n" +
            "51498\n" +
            "136829\n" +
            "140171\n" +
            "59441\n" +
            "144489\n" +
            "139384\n" +
            "145841\n" +
            "96771\n" +
            "116821\n" +
            "88599\n" +
            "126780\n" +
            "65012\n" +
            "67621\n" +
            "129699\n" +
            "149639\n" +
            "97590\n" +
            "147527\n" +
            "117462\n" +
            "146709\n" +
            "60527\n" +
            "107643\n" +
            "92956\n" +
            "72177\n" +
            "92285\n" +
            "62475\n" +
            "63099\n" +
            "66904\n" +
            "77268\n" +
            "62945\n" +
            "134364\n" +
            "106924\n" +
            "117842\n" +
            "130016\n" +
            "123712\n";

    public static void solve() {

        List<Integer> fuelValues = Arrays.stream(input.split("\n"))
                .map(in -> Integer.parseInt(in))
                .collect(Collectors.toList());
        fuelValues.sort(Comparator.naturalOrder());

        int answer = 0;
        for (Integer val : fuelValues) {
            answer += ((val / 3) - 2);
        }

        System.out.println("Answer: " + answer); // 3478233
    }

    public static void solvePart2() {
        List<Integer> fuelValues = Arrays.stream(input.split("\n"))
                .map(in -> Integer.parseInt(in))
                .collect(Collectors.toList());
//        List<Integer> fuelValues = new ArrayList<>();
//        fuelValues.add(100756);

        int answer = 0;
        for (Integer val : fuelValues) {
            int reduced = val;
            reduced = (reduced / 3) - 2;
            while (reduced >= 0) {
                answer += reduced;
                reduced = (reduced / 3) - 2;
            }
        }
        System.out.println("Answer: " + answer); // 5214475
    }
}
