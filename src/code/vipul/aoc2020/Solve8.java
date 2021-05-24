package code.vipul.aoc2020;

import code.vipul.aoc2020.emulator.ConsoleEmulator;

import java.util.Arrays;

import static code.vipul.aoc2020.Inputs.INPUT_8;

/**
 * https://adventofcode.com/2020/day/8
 */
public class Solve8 {

    static String INPUT = "nop +0\n" +
            "acc +1\n" +
            "jmp +4\n" +
            "acc +3\n" +
            "jmp -3\n" +
            "acc -99\n" +
            "acc +1\n" +
            "jmp -4\n" +
            "acc +6";

    public static void solve() {
        var instructions = INPUT_8.split("\n");
        ConsoleEmulator emulator = new ConsoleEmulator(instructions);
        emulator.turnOnInstructionRepeatBreak();

        emulator.execute();

        System.out.println("Answer: " + emulator.getAccumulator()); //1200
    }

    public static void solvePart2() {
        var instructions = INPUT_8.split("\n");
        var instructionsCopy = Arrays.copyOf(instructions, instructions.length);
        var answer = -1;

        for (int i = 0; i < instructions.length; i++) {
            if (instructions[i].startsWith("jmp") || instructions[i].startsWith("nop")) {
                var oldInstruction = instructions[i];

                if (oldInstruction.startsWith("jmp")) {
                    instructionsCopy[i] = oldInstruction.replace("jmp", "nop");
                } else {
                    instructionsCopy[i] = oldInstruction.replace("nop", "jmp");
                }

                ConsoleEmulator emulator = new ConsoleEmulator(instructionsCopy);
                emulator.turnOnInstructionRepeatBreak();
                emulator.execute();

                if (emulator.didNormallyTerminate()) {
                    answer = emulator.getAccumulator();
                    break;
                }
                instructionsCopy[i] = oldInstruction;
            }
        }

        System.out.println("Answer: " + answer); //1023
    }
}
