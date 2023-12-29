package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_18;

/**
 * Created by vgaur created on 28/12/23
 * Insanely Good
 */
public class Day18 {

    private static String INPUT = """
            snd 1
            snd 2
            snd p
            rcv a
            rcv b
            rcv c
            rcv d
            """;
    public static final Long SENTINEL = Long.MIN_VALUE;

    public static void solve() {
        INPUT = DAY_18;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Program p = new Program(-1, lines, false);
        p.execute();
        long val1 = -1;
        while(!p.sendQueue.isEmpty()) val1 = p.sendQueue.remove();

        System.out.println("Part 1: " + val1); // 8600

        // ----- Part 2 begins -----
        Program p0 = new Program(0, lines, true);
        Program p1 = new Program(1, lines, true);

        while (true) {
            boolean exit = p0.runExecutionCycle(p1);
            if (exit) break;
            exit = p1.runExecutionCycle(p0);
            if (exit) break;
        }
        System.out.println("Part 2: " + p1.sendTimes); // 7239
    }

    private static class Program {
        private final int id;
        private final List<String> instructions;
        private final long[] registers = new long[26];
        private boolean waitingToReceive = false;
        private char receivingRegister;
        private int add;
        private int sendTimes = 0;
        private final Queue<Long> sendQueue;
        private final boolean communicate;

        public Program(int id, List<String> lines, boolean communicate) {
            this.id = id;
            this.instructions = lines;
            this.sendQueue = new ArrayDeque<>();
            this.add = 0;
            this.receivingRegister = '\0';
            if (communicate) {
                this.registers['p' - 'a'] = id;
            }
            this.communicate = communicate;
        }

        public boolean hasMessagesToSend() {
            return !sendQueue.isEmpty();
        }

        public long getMessageFromQueue() {
            if (!sendQueue.isEmpty()) {
                return sendQueue.remove();
            }
            if (!waitingToReceive) {
                execute();
            }
            if (sendQueue.isEmpty()) {
                return SENTINEL;
            }
            return sendQueue.remove();
        }

        public void sendMessage(long value) {
            if (!waitingToReceive || receivingRegister == '\0') {
                throw new RuntimeException("Program " + id + " not in waiting state");
            }
            waitingToReceive = false;
            setRegister(receivingRegister, registers, value);
            receivingRegister = '\0';
        }

        public void print(String msg) {
            System.out.println("Program: " + id + " - " + msg);
        }

        public boolean runExecutionCycle(Program other) {
            execute();
            if (waitingToReceive) { // we must get the value
                long val = receiveValue(other);
                if (val == SENTINEL) { // we tried to get a value, but received a sentinel instead, then break
                    return true; // signal the end
                }
                sendMessage(val);
            }
            return false;
        }

        private long receiveValue(Program other) {
            if (!other.hasMessagesToSend()) {
                while (!other.hasMessagesToSend()) { // Keep looping until we have some messages from the other program
                    other.execute();
                    // if the other program is waiting for a message, then pass it a message if we have one here
                    if (other.waitingToReceive && hasMessagesToSend()) {
                        other.sendMessage(getMessageFromQueue());
                    }
                    // if the other program is still waiting to receive, that means we can't do anything, return
                    if (other.waitingToReceive) {
                        return SENTINEL;
                    }
                }
            }
            return other.getMessageFromQueue();
        }

        private void execute() {
            if (waitingToReceive) {
                print("Waiting to receive, cant process anything");
                return;
            }
            loop:
            while (add < instructions.size()) {
                String line = instructions.get(add);
                //System.out.println("Executing: " + line);
                var parts = line.split(" ");
                var ins = parts[0];
                long op1 = getOp(parts[1], registers);
                char destRegister = parts[1].charAt(0);
                long op2 = -1;
                if (parts.length > 2) {
                    op2 = getOp(parts[2], registers);
                }
                switch (ins) {
                    case "set" -> setRegister(destRegister, registers, op2);
                    case "add" -> setRegister(destRegister, registers, op1 + op2);
                    case "mul" -> setRegister(destRegister, registers, op1 * op2);
                    case "mod" -> setRegister(destRegister, registers, op1 % op2);
                    case "snd" -> {
                        //System.out.println("Id: " + id + " sending value: " + op1);
                        sendQueue.add(op1);
                        sendTimes++;
                    }
                    case "rcv" -> {
                        waitingToReceive = true;
                        receivingRegister = destRegister;
                        add++;
                        if (communicate || op1 != 0) {
                            break loop;
                        }
                    }
                    case "jgz" -> {
                        if (op1 > 0) {
                            add += op2;
                            add--; // to offset the ++ below
                        }
                    }
                }
                add++;
            }
        }
    }

    private static long getOp(String op, long[] registers) {
        if (op.charAt(0) >= 'a' && op.charAt(0) <= 'z') { // register
            return registers[op.charAt(0) - 'a'];
        } else {
            return Long.parseLong(op);
        }
    }

    private static void setRegister(char dest, long[] registers, long val) {
        registers[dest - 'a'] = val;
    }
}
