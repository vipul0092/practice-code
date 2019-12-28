package code.vipul.aoc2019.intcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class OpcodeEvaluator {

    public static OpcodeWithMode parse(long memoryValue) {
        long opcodeValue = memoryValue % 100;
        long modesInput = memoryValue / 100;

        Opcode opcode = Opcode.fromValue((int) opcodeValue);
        Memory.Mode[] modes = Memory.Mode.getModes((int) modesInput, opcode.operands);
        return new OpcodeWithMode(opcode, modes);
    }

    public static class OpcodeWithMode {

        public Opcode opcode() {
            return opcode;
        }

        public Memory.Mode[] modes() {
            return modes;
        }

        private final Opcode opcode;
        private final Memory.Mode[] modes;

        private OpcodeWithMode(Opcode opcode, Memory.Mode[] modes) {
            this.opcode = opcode;
            this.modes = modes;
        }
    }

    public enum Opcode {
        ADD(1, 3),
        MULTIPLY(2, 3),
        INPUT(3, 1),
        OUTPUT(4, 1),
        JUMP_IF_TRUE(5, 2),
        JUMP_IF_FALSE(6, 2),
        LESS_THAN(7, 3),
        EQUALS(8, 3),
        ADJUST_RELATIVE_BASE(9, 1),
        HALT(99, 0);

        private final Integer value;

        public Integer operands() {
            return operands;
        }

        private final Integer operands;
        private static final Map<Integer, Opcode> CONSTANTS = new HashMap<>();

        static {
            for (Opcode c : values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Opcode(Integer value, Integer operands) {
            this.value = value;
            this.operands = operands;
        }

        @Override
        public String toString() {
            return this.name();
        }

        public static Opcode fromValue(int value) {
            Opcode opcode = CONSTANTS.get(value);
            if (opcode == null) {
                throw new RuntimeException("Illegal Opcode: " + value);
            }
            return opcode;
        }
    }
}
