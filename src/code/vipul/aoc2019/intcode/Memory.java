package code.vipul.aoc2019.intcode;

import java.util.HashMap;
import java.util.Map;

public class Memory {

    private static final int SIZE = 100000;

    private int relativeBase = 0;
    private long[] stream;

    public Memory() {
    }

    public void load(String input) {
        String[] inputs = input.split(",");
        stream = new long[SIZE];
        for (int i = 0; i < inputs.length; i++) {
            stream[i] = Long.parseLong(inputs[i]);
        }
    }

    public long directRead(Location location) {
        return read(location, Mode.IMMEDIATE);
    }

    public long read(Location location, Mode mode) {
        long address = location.location; // Immediate mode
        if (mode == Mode.RELATIVE) {
            address = relativeBase + stream[(int) address];
        } else if (mode == Mode.POSITION) {
            address = stream[(int) address];
        }
        return stream[(int) address];
    }

    public void write(long value, Location location) {
        write(value, location, Mode.POSITION);
    }

    public void write(long value, Location location, Mode mode) {
        long address = location.location;
        if (mode == Mode.IMMEDIATE) {
            throw new RuntimeException("Illegal Operation! Cant write to memory in Immediate Mode");
        }
        address = mode == Mode.RELATIVE ? relativeBase + address : address;
        stream[(int) address] = value;
    }

    public void adjustRelativeBase(long value) {
        relativeBase += value;
    }

    public static class Location {
        private final int location;

        private Location(int location) {
            this.location = location;
        }

        public static Location of(long location) {
            return new Location((int) location);
        }

        public static Location of(int location) {
            return new Location(location);
        }

        public static Location initial() {
            return new Location(0);
        }

        public Location plus(int steps) {
            return new Location(location + steps);
        }
    }

    public enum Mode {
        POSITION(0),
        IMMEDIATE(1),
        RELATIVE(2);

        private final Integer value;
        private static final Map<Integer, Mode> CONSTANTS = new HashMap<>();

        static {
            for (Mode c : values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Mode(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.name();
        }

        public static Mode fromValue(int value) {
            Mode mode = CONSTANTS.get(value);
            if (mode == null) {
                throw new RuntimeException("Illegal Memory Mode: " + value);
            }
            return mode;
        }

        public static Mode[] getModes(int modesInput, int numberOfModes) {
            Mode[] modes = new Mode[numberOfModes];
            int counter = 0;
            while (numberOfModes-- > 0) {
                modes[counter++] = fromValue(modesInput % 10);
                modesInput = modesInput / 10;
            }
            return modes;
        }
    }
}
