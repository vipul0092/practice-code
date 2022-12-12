package code.vipul.aoc2022;

import code.vipul.aoc2022.emulator.VideoSystem;
import code.vipul.aoc2022.inputs.Inputs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 09/12/22
 * https://adventofcode.com/2022/day/10
 */
public class Solve10 {

    public static void solve() {
        List<String> inputs = Arrays.stream(Inputs.INPUT_10.split("\n")).collect(Collectors.toList());

        VideoSystem videoSystem = new VideoSystem();
        videoSystem.executeProgram(inputs);

        //Part 1
        System.out.println(videoSystem.getSum());

        // Part 2
        videoSystem.displayCrt();
    }
}
