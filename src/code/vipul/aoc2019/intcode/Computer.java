package code.vipul.aoc2019.intcode;

import java.util.List;

/**
 * Custom IntCode computer implementation
 */
public class Computer {

    private Memory memory;
    private Memory.Location currentPointer;
    private Output output;
    private Input input;
    private CpuState currentState;
    private OpcodeEvaluator.Opcode lastEvaluatedOpcode = null;
    private boolean enableLogging = true;

    private Computer() {
        memory = new Memory();
        output = new Output();
        input = new Input();
        currentState = CpuState.BOOTED;
        logComputerAction("System Booted up.");
    }

    public static Computer make() {
        return new Computer();
    }

    public Computer takeSnapshot() {
        Computer snap = new Computer();
        snap.currentState = this.currentState;
        snap.enableLogging = this.enableLogging;
        snap.lastEvaluatedOpcode = lastEvaluatedOpcode;
        snap.currentPointer = this.currentPointer;
        snap.memory = this.memory.takeSnapshot();
        snap.input = this.input.takeSnapshot();
        snap.output = this.output.takeSnapshot();
        return snap;
    }

    public void resetStateTo(Computer computer) {
        this.memory.resetStateTo(computer.memory);
        this.input.resetStateTo(computer.input);
        this.output.resetStateTo(computer.output);
        this.currentState = computer.currentState;
        this.enableLogging = computer.enableLogging;
        this.lastEvaluatedOpcode = computer.lastEvaluatedOpcode;
        this.currentPointer = computer.currentPointer;
    }

    public void loadProgramInMemory(String input) {
        memory.load(input);
        currentPointer = Memory.Location.initial();
        currentState = CpuState.PROGRAM_LOADED;
        lastEvaluatedOpcode = null;
        logComputerAction("Loaded program into memory");
    }

    public void directlyWriteToMemory(Memory.Location location, long value) {
        memory.write(value, location);
    }

    public Output getOutput() {
        return output;
    }

    public Input getInput() {
        return input;
    }

    public void giveInputAndExecute(List<Long> inputs) {
        input.enqueueInput(inputs);
        execute();
    }

    public void giveInputAndExecute(Long i) {
        input.enqueueInput(i);
        execute();
    }

    public boolean hasHalted() {
        return currentState == CpuState.HALTED;
    }

    public long directlyReadFromMemory(Memory.Location location) {
        return memory.directRead(location);
    }

    public void loadAndExecute(String input) {
        loadProgramInMemory(input);
        executeInternal();
    }

    public void execute() {
        if (currentState != CpuState.EXECUTION_PAUSED && currentState != CpuState.PROGRAM_LOADED) {
            throw new RuntimeException("Cant execute() without loading the computer with a program");
        }
        executeInternal();
    }

    private void executeInternal() {
        logComputerAction(currentState == CpuState.EXECUTION_PAUSED
                ? "Resuming program execution" : "Starting program execution");
        currentState = CpuState.EXECUTING;
        try {
            while (true) {
                long memoryValue = memory.directRead(currentPointer);
                OpcodeEvaluator.OpcodeWithMode opcode = OpcodeEvaluator.parse(memoryValue);
                // System has completed pushing output, now flush the output
                if (lastEvaluatedOpcode == OpcodeEvaluator.Opcode.OUTPUT
                        && opcode.opcode() != OpcodeEvaluator.Opcode.OUTPUT) {
                    output.flush();
                }
                evaluateOperator(opcode);
                lastEvaluatedOpcode = opcode.opcode();
            }
        } catch (HaltException h) {
            output.flush();
            currentState = CpuState.HALTED;
            lastEvaluatedOpcode = null;
            logComputerAction("Program execution finished. System Halted.");
        } catch (Input.NoMoreInputException i) {
            currentState = CpuState.EXECUTION_PAUSED;
            logComputerAction("Program paused. Waiting for input. Provide input and execute()");
        }
    }

    private void evaluateOperator(OpcodeEvaluator.OpcodeWithMode opcodeWithMode) {

        OpcodeEvaluator.Opcode opcode = opcodeWithMode.opcode();
        Memory.Mode[] modes = opcodeWithMode.modes();

        long operand1;
        long operand2;
        Memory.Location writeLocation;
        switch (opcode) {
            case ADD:
                operand1 = memory.read(currentPointer.plus(1), modes[0]);
                operand2 = memory.read(currentPointer.plus(2), modes[1]);
                writeLocation = Memory.Location.of(memory.directRead(currentPointer.plus(3)));
                memory.write(operand1 + operand2, writeLocation, modes[2]);
                currentPointer = currentPointer.plus(4);
                break;
            case MULTIPLY:
                operand1 = memory.read(currentPointer.plus(1), modes[0]);
                operand2 = memory.read(currentPointer.plus(2), modes[1]);
                writeLocation = Memory.Location.of(memory.directRead(currentPointer.plus(3)));
                memory.write(operand1 * operand2, writeLocation, modes[2]);
                currentPointer = currentPointer.plus(4);
                break;
            case LESS_THAN:
                operand1 = memory.read(currentPointer.plus(1), modes[0]);
                operand2 = memory.read(currentPointer.plus(2), modes[1]);
                writeLocation = Memory.Location.of(memory.directRead(currentPointer.plus(3)));
                memory.write(operand1 < operand2 ? 1 : 0, writeLocation, modes[2]);
                currentPointer = currentPointer.plus(4);
                break;
            case EQUALS:
                operand1 = memory.read(currentPointer.plus(1), modes[0]);
                operand2 = memory.read(currentPointer.plus(2), modes[1]);
                writeLocation = Memory.Location.of(memory.directRead(currentPointer.plus(3)));
                memory.write(operand1 == operand2 ? 1 : 0, writeLocation, modes[2]);
                currentPointer = currentPointer.plus(4);
                break;
            case JUMP_IF_FALSE:
                operand1 = memory.read(currentPointer.plus(1), modes[0]);
                operand2 = memory.read(currentPointer.plus(2), modes[1]);
                currentPointer = operand1 == 0 ? Memory.Location.of(operand2) : currentPointer.plus(3);
                break;
            case JUMP_IF_TRUE:
                operand1 = memory.read(currentPointer.plus(1), modes[0]);
                operand2 = memory.read(currentPointer.plus(2), modes[1]);
                currentPointer = operand1 != 0 ? Memory.Location.of(operand2) : currentPointer.plus(3);
                break;
            case ADJUST_RELATIVE_BASE:
                operand1 = memory.read(currentPointer.plus(1), modes[0]);
                memory.adjustRelativeBase(operand1);
                currentPointer = currentPointer.plus(2);
                break;
            case OUTPUT:
                operand1 = memory.read(currentPointer.plus(1), modes[0]);
                output.pushOutput(operand1);
                currentPointer = currentPointer.plus(2);
                break;
            case INPUT:
                writeLocation = Memory.Location.of(memory.directRead(currentPointer.plus(1)));
                long valueToWrite = input.fetchInput();
                memory.write(valueToWrite, writeLocation, modes[0]);
                currentPointer = currentPointer.plus(2);
                break;
            default: // Halt opcode
                throw new HaltException();

        }
    }

    private void logComputerAction(String log) {
        if (enableLogging) {
            System.out.println(log);
        }
    }

    private static class HaltException extends RuntimeException {
    }

}
