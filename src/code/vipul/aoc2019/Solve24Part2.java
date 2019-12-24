package code.vipul.aoc2019;

import java.util.ArrayList;
import java.util.List;

/**
 * https://adventofcode.com/2019/day/24#part2
 */
public class Solve24Part2 {

    public static void solve() {
        List<String> rows = new ArrayList<>();
        int iteration = -1;
//        rows.add("....#");
//        rows.add("#..#.");
//        rows.add("#..##");
//        rows.add("..#..");
//        rows.add("#....");
//        iteration = 10; // Answer = 99,

        rows.add("##.##");
        rows.add(".#.##");
        rows.add("##..#");
        rows.add("#.#..");
        rows.add(".###.");
        iteration = 200; // Answer = 1934

        solve(rows, iteration);
    }

    public static void solve(List<String> rows, int iterations) {
        Grid baseGrid = Grid.parseInput(rows);

        List<Grid> parentGrids = new ArrayList<>();
        List<Grid> childGrids = new ArrayList<>();


        int count = 200;
        while(count-- > 0) {
            parentGrids.add(Grid.newGrid());
        }
        count = 200;
        while(count-- > 0) {
            childGrids.add(Grid.newGrid());
        }

        for (int i = 0; i < iterations; i++) {

            List<Grid> newParentGrids = new ArrayList<>();
            List<Grid> newChildGrids = new ArrayList<>();

            // for first parent
            Grid firstParent = parentGrids.get(0);
            firstParent = firstParent.updateGrid(parentGrids.get(1), baseGrid);
            newParentGrids.add(firstParent);

            // update all other parents
            for (int j = 1; j <= 198; j++) {
                Grid current = parentGrids.get(j);
                current = current.updateGrid(parentGrids.get(j+1), parentGrids.get(j-1));
                newParentGrids.add(current);
            }
            newParentGrids.add(parentGrids.get(199));

            // for first child
            Grid firstChild = childGrids.get(0);
            firstChild = firstChild.updateGrid(baseGrid, childGrids.get(1));
            newChildGrids.add(firstChild);

            // update all other childs
            for (int j = 1; j <= 198; j++) {
                Grid current = childGrids.get(j);
                current = current.updateGrid(childGrids.get(j-1), childGrids.get(j+1));
                newChildGrids.add(current);
            }
            newChildGrids.add(childGrids.get(199));

            // Update the base grid
            baseGrid = baseGrid.updateGrid(parentGrids.get(0), childGrids.get(0));

            parentGrids = newParentGrids;
            childGrids = newChildGrids;
        }

        int answer = 0;
        answer += baseGrid.getOnes();
        for (int i = 0; i <= 199; i++) {
            answer += (parentGrids.get(i).getOnes());
            answer += (childGrids.get(i).getOnes());
        }

        System.out.println("Answer: " + answer);
    }
}
