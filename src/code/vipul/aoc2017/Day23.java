package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_23;

/**
 * Created by vgaur created on 29/12/23
 */
public class Day23 {

    private static String INPUT = "";

    private static long[] registers;
    private static int part2 = 0;

    public static void solve() {
        INPUT = DAY_23;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        registers = new long[8];
        System.out.println("Part 1: " + execute(lines, false)); // 3969

        registers = new long[8];
        registers[0] = 1;
        // The assembly basically finds the non-prime numbers from register b value to register c value
        // in increments of 17, Took me 75 minutes to figure that out lol
        execute(lines, true);
        System.out.println("Part 2: " + part2); // 917
    }

    private static int execute(List<String> instructions, boolean breakOut) {
        int add = 0;
        int times = 0;
        while (add < instructions.size()) {
            String line = instructions.get(add);

            if (breakOut && registers['f'-'a'] != 0) {
                break;
            }
            var parts = line.split(" ");
            var ins = parts[0];
            long op1 = getOp(parts[1], registers);
            char destRegister = parts[1].charAt(0);
            long op2 = -1;
            if (parts.length > 2) {
                op2 = getOp(parts[2], registers);
            }
            if (ins.equals("mul")) {
                times++;
            }
            switch (ins) {
                case "set" -> setRegister(destRegister, registers, op2);
                case "sub" -> setRegister(destRegister, registers, op1 - op2);
                case "mul" -> setRegister(destRegister, registers, op1 * op2);
                //case "mod" -> setRegister(destRegister, registers, op1 % op2);
                case "jnz" -> {
                    if (op1 != 0) {
                        add += op2;
                        add--; // to offset the ++ below
                    }
                }
            }
            add++;
        }

        for (long i = registers[1]; breakOut && i <= registers[2]; i+=17) {
            if (!isPrime((int)i)) {
                part2++;
            }
        }

        return times;
    }

    private static boolean isPrime(int n) {
        // Corner case
        if (n <= 1) return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0) return false;
        return true;
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
