package code.vipul.aoc2021;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static code.vipul.aoc2021.Inputs.INPUT_2;

/**
 * Created by vgaur created on 24/12/21
 * https://adventofcode.com/2021/day/2
 */
public class Solve2 {

    private static final String INPUT = "forward 5\n" +
            "down 5\n" +
            "forward 8\n" +
            "up 3\n" +
            "down 8\n" +
            "forward 2";

    public static void solve() {
        AtomicInteger horizontalPos = new AtomicInteger(0);
        AtomicInteger verticalPos = new AtomicInteger(0);

        Arrays.stream(INPUT_2.split("\n")).forEach(action -> {
            String[] split = action.split(" ");
            String actionType = split[0];
            int distance = Integer.parseInt(split[1]);

            if (actionType.equals("forward")) {
                horizontalPos.addAndGet(distance);
            } else if (actionType.equals("down")) {
                verticalPos.addAndGet(distance);
            } else if (actionType.equals("up")) {
                verticalPos.addAndGet(-distance);
            }
        });

        System.out.println("Answer: " + (horizontalPos.intValue() * verticalPos.intValue())); // 1989265
    }

    public static void solvePart2() {
        AtomicInteger horizontalPos = new AtomicInteger(0);
        AtomicInteger verticalPos = new AtomicInteger(0);
        AtomicInteger aim = new AtomicInteger(0);

        Arrays.stream(INPUT_2.split("\n")).forEach(action -> {
            String[] split = action.split(" ");
            String actionType = split[0];
            int distance = Integer.parseInt(split[1]);

            if (actionType.equals("forward")) {
                horizontalPos.addAndGet(distance);
                verticalPos.addAndGet(aim.intValue() * distance);
            } else if (actionType.equals("down")) {
                aim.addAndGet(distance);
            } else if (actionType.equals("up")) {
                aim.addAndGet(-distance);
            }
        });

        System.out.println("Answer: " + (horizontalPos.intValue() * verticalPos.intValue())); // 2089174012
    }
}
