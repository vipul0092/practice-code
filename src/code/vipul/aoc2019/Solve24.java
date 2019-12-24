package code.vipul.aoc2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://adventofcode.com/2019/day/24
 */
public class Solve24 {

    public static void solve() {
        List<String> rows = new ArrayList<>();
//        rows.add("....#");
//        rows.add("#..#.");
//        rows.add("#..##");
//        rows.add("..#..");
//        rows.add("#...."); // Answer = 2129920

        rows.add("##.##");
        rows.add(".#.##");
        rows.add("##..#");
        rows.add("#.#..");
        rows.add(".###."); // Answer = 23846449

        solve(rows);
    }

    public static void solve(List<String> rows) {
        Grid grid = Grid.parseInput(rows);
        grid.display();

        Set<Integer> scores = new HashSet<>();
        scores.add(grid.getScore());
        int answer = 0;

        while (true) {
            grid = grid.updateGrid();
            grid.display();
            int score = grid.getScore();

            if (scores.contains(score)) {
                answer = score;
                break;
            }
            scores.add(score);
        }

        System.out.println("Answer: " + answer);
    }
}
