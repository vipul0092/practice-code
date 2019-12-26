package code.vipul.aoc2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Int code
 */
public class IntCode {

    private static final int SIZE = 100000;

    private long[] stream;
    private static final int ADD = 1;
    private static final int MULTIPLY = 2;
    private static final int INPUT = 3;
    private static final int OUTPUT = 4;
    private static final int JUMP_IF_TRUE = 5;
    private static final int JUMP_IF_FALSE = 6;
    private static final int LESS_THAN = 7;
    private static final int EQUALS = 8;
    private static final int ADJUST_RELATIVE_BASE = 9;
    private static final int HALT = 99;

    private int relativeBase = 0;

    private final int inputInstructionValue;

    private boolean getInputFromUser = false;

    private int inputArrayCounter = 0;
    private int remainingInputLength = 0;
    private int[] inputArray = null;
    private boolean outputAscii = false;
    private StringBuilder asciiOutput = new StringBuilder();

    private IntCode(String input, int inputInstructionValue) {
        String[] inputs = input.split(",");
        stream = new long[SIZE];
        for (int i = 0; i < inputs.length; i++) {
            stream[i] = Long.parseLong(inputs[i]);
        }
        this.inputInstructionValue = inputInstructionValue;
    }

    private IntCode(String input) {
        this(input, 1);
    }

    public static IntCode getInstance(String input) {
        return new IntCode(input);
    }

    public static IntCode getInstance(String input, int inputInstructionValue) {
        return new IntCode(input, inputInstructionValue);
    }

    public void setInputArray(int[] inputArray) {
        this.inputArray = inputArray;
    }

    public void enableActualInput() {
        getInputFromUser = true;
    }

    public void showAscii() {
        this.outputAscii = true;
    }

    public void initializeOneTwo(int one, int two) {
        stream[1] = one;
        stream[2] = two;
    }

    public long valueAt(int index) {
        return stream[index];
    }

    public void evaluateStream() {
        int index = 0;
        while (true) {
            long operator = stream[index];
            if (operator == HALT) {
                break;
            }
            long operand1 = stream[(int) stream[index + 1]];
            long operand2 = stream[(int) stream[index + 2]];
            long saveIndex = stream[index + 3];

            long result = 0;
            if (operator == ADD) {
                result = operand1 + operand2;
            } else if (operator == MULTIPLY) {
                result = operand1 * operand2;
            }
            stream[(int) saveIndex] = result;
            index += 4;
        }
    }

    public void evaluateStreamWithModes() {
        long index = 0;
        while (true) {
            long operator = stream[(int) index];
            if (operator == HALT) {
                break;
            }
            index = evaluateOperator(operator, index);
        }
    }

    private static final long POS_MODE = 0;
    private static final long IMMEDIATE_MODE = 1;
    private static final long RELATIVE_MODE = 2;

    private long evaluateOperator(long operator, long index) {
        long opcode = operator % 100;
        long modesInput = operator / 100;
        List<Long> operandModes = new ArrayList<>();

        if (opcode == ADD || opcode == MULTIPLY) {
            operandModes.add(modesInput % 10); // Mode for first operand
            modesInput = modesInput / 10;
            operandModes.add(modesInput % 10); // Mode for second operand
            modesInput = modesInput / 10;
            operandModes.add(modesInput % 10); // Mode for third operand

            long operand1 = getValueWithMode(index + 1, operandModes.get(0));
            long operand2 = getValueWithMode(index + 2, operandModes.get(1));
            long saveAddress = stream[(int) index + 3];

            long result = opcode == ADD ? operand1 + operand2 : operand1 * operand2;
            writeWithMode(result, saveAddress, operandModes.get(2));

            return index + 4;
        } else if (opcode == LESS_THAN || opcode == EQUALS) {
            operandModes.add(modesInput % 10); // Mode for first operand
            modesInput = modesInput / 10;
            operandModes.add(modesInput % 10); // Mode for second operand
            modesInput = modesInput / 10;
            operandModes.add(modesInput % 10); // Mode for third operand

            long operand1 = getValueWithMode(index + 1, operandModes.get(0));
            long operand2 = getValueWithMode(index + 2, operandModes.get(1));
            long saveAddress = stream[(int) index + 3];

            int result = ((opcode == LESS_THAN && operand1 < operand2)
                    || (opcode == EQUALS && operand1 == operand2))
                    ? 1 : 0;
            writeWithMode(result, saveAddress, operandModes.get(2));
            return index + 4;
        } else if (opcode == JUMP_IF_FALSE) {
            operandModes.add(modesInput % 10); // Mode for first operand
            modesInput = modesInput / 10;
            operandModes.add(modesInput % 10); // Mode for second operand

            long operand1 = getValueWithMode(index + 1, operandModes.get(0));
            long operand2 = getValueWithMode(index + 2, operandModes.get(1));

            return operand1 == 0 ? operand2 : index + 3;
        } else if (opcode == JUMP_IF_TRUE) {
            operandModes.add(modesInput % 10); // Mode for first operand
            modesInput = modesInput / 10;
            operandModes.add(modesInput % 10); // Mode for second operand

            long operand1 = getValueWithMode(index + 1, operandModes.get(0));
            long operand2 = getValueWithMode(index + 2, operandModes.get(1));

            return operand1 != 0 ? operand2 : index + 3;
        } else if (opcode == OUTPUT) {
            long mode = modesInput % 10;
            long value = getValueWithMode(index + 1, mode);
            if (outputAscii) {
                char ch = (char) value;
                asciiOutput.append(ch);
                System.out.print(ch);
            } else {
                System.out.println("OUTPUT opcode evaluated, value: " + value);
            }
            return index + 2;
        } else if (opcode == ADJUST_RELATIVE_BASE) {
            long mode = modesInput % 10;
            long value = getValueWithMode(index + 1, mode);
            relativeBase += value;
            return index + 2;
        } else if (opcode == INPUT) {
            long mode = modesInput % 10; // Can either be pos mode or relative mode
            long saveAddress = stream[(int) index + 1];
            int valueToWrite = inputInstructionValue;
            if (getInputFromUser && remainingInputLength == 0) {
                Scanner scanner = new Scanner(System.in);
                String s = scanner.nextLine();
                updateArrayWithFunction(s);
            }
            if (inputArray != null) {
                valueToWrite = inputArray[inputArrayCounter++];
                remainingInputLength--;
            }
            writeWithMode(valueToWrite, saveAddress, mode);
            return index + 2;
        }
        throw new RuntimeException("Fuck off!, you are not supposed to be here, opcode: " + opcode);
    }

    private void updateArrayWithFunction(String function) {
        int ctr = 0;
        inputArrayCounter = 0;
        inputArray = new int[500];
        remainingInputLength = function.length() + 1; // all chars + newline
        for (char ch : function.toCharArray()) {
            inputArray[ctr++] = ch;
        }
        inputArray[ctr] = 10; //newline
    }

    public String getAsciiOutput() {
        return asciiOutput.toString();
    }

    private long getValueWithMode(long index, long mode) {
        long address = index; // Immediate mode
        if (mode == RELATIVE_MODE) {
            address = relativeBase + stream[(int) address];
        } else if (mode == POS_MODE) {
            address = stream[(int) address];
        }
        return stream[(int) address];
    }

    private void writeWithMode(long value, long address, long mode) {
        if (mode == IMMEDIATE_MODE) {
            throw new RuntimeException("Cant write to an address with immediate mode!");
        }
        address = mode == RELATIVE_MODE ? relativeBase + address : address;
        stream[(int) address] = value;
    }
}
