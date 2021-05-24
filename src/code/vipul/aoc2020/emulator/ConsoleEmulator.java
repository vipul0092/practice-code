package code.vipul.aoc2020.emulator;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by vgaur created on 12/12/20
 */
public class ConsoleEmulator {

    private static final String NOOP = "nop";
    private static final String ACC = "acc";
    private static final String JUMP = "jmp";

    private final String[] instructions;
    private final Set<Integer> instructionsExecuted = new LinkedHashSet<>();

    private boolean breakOnRepeatInstruction = false;
    private int currentInstructionNumber = 0;
    private boolean executionHalted = false;
    private int accumulator = 0;

    public int getAccumulator() {
        return accumulator;
    }

    public boolean isExecutionHalted() {
        return executionHalted;
    }

    public ConsoleEmulator(String[] input) {
        this.instructions = input;
    }

    public void turnOnInstructionRepeatBreak() {
        breakOnRepeatInstruction = true;
    }

    public boolean didNormallyTerminate() {
        return currentInstructionNumber >= instructions.length;
    }

    public void execute() {
        while(!(executionHalted || currentInstructionNumber >= instructions.length)) {
            if ((instructionsExecuted.contains(currentInstructionNumber) && breakOnRepeatInstruction)) {
                executionHalted = true;
                break;
            }
            parseAndHandleNextInstruction();
        }
    }

    private void parseAndHandleNextInstruction() {
        String instructionRow = instructions[currentInstructionNumber];
        var instructionWithParam = instructionRow.split(" ");
        instructionsExecuted.add(currentInstructionNumber);
        handleInstruction(instructionWithParam[0].trim(), Integer.parseInt(instructionWithParam[1].trim()));
    }

    private void handleInstruction(String instruction, int param) {
        switch (instruction) {
            case ACC:
                accumulator += param;
                currentInstructionNumber++;
                break;
            case JUMP:
                currentInstructionNumber += param;
                break;
            case NOOP:
                currentInstructionNumber++;
                break;
            default:
                break;
        }
    }

}
