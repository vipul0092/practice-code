package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_16;

/**
 * Created by vgaur created on 28/12/23
 */
public class Day16 {

    private static String INPUT = "s1,x3/4,pe/b";

    public static void solve() {
        INPUT = DAY_16;
        String input = Arrays.stream(INPUT.split("\n")).toList().get(0);
        String[] ins = input.split(",");

        System.out.println("Part 1: " + getOutput(ins, 1)); // iabmedjhclofgknp
        System.out.println("Part 2: " + getOutput(ins, 1000000000)); // oildcmfeajhbpngk
    }

    private static String getOutput(String[] ins, int totalDances) {
        int dances = 0;
        int TOTAL = 16;
        char[] programs = new char[TOTAL];
        for (int i = 0; i < TOTAL; i++) {
            programs[i] = (char)('a' + i);
        }
        Map<String, Integer> danceCounts = new HashMap<>();
        danceCounts.put(new String(programs), dances);


        long firstDetect = -1, cycle = -1;
        while (dances < totalDances) {
            for (String in : ins) {
                programs = handleInstruction(programs, in);
            }
            dances++;
            String t = new String(programs);
            if (danceCounts.containsKey(t)) {
                firstDetect = danceCounts.get(t);
                cycle = dances - firstDetect;
                break;
            } else {
                danceCounts.put(t, dances);
            }
        }

        if (totalDances == 1) {
            return new String(programs);
        }

        long run = totalDances % cycle;
        while(run-- > 0) {
            for (String in : ins) {
                programs = handleInstruction(programs, in);
            }
        }
        return new String(programs);
    }

    private static char[] handleInstruction(char[] programs, String ins) {
        char s = ins.charAt(0);
        ins = ins.substring(1);
        if (s == 's') {
            int count = Integer.parseInt(ins);
            char[] newp = new char[programs.length];
            int j = 0;
            for (int i = programs.length-count; j < count;) {
                newp[j++] = programs[i++];
            }
            for (int i = 0; i < programs.length-count;) {
                newp[j++] = programs[i++];
            }
            return newp;
        } else if (s == 'x') {
            var parts = ins.split("/");
            int i1 = Integer.parseInt(parts[0]), i2 = Integer.parseInt(parts[1]);
            char t = programs[i1];
            programs[i1] = programs[i2];
            programs[i2] = t;
            return programs;
        } else if (s == 'p') {
            var parts = ins.split("/");
            char c1 = parts[0].charAt(0), c2 = parts[1].charAt(0);
            int i1 = -1, i2 = -1;
            for (int i = 0; i < programs.length; i++) {
                if (programs[i] == c1) {
                    i1 = i;
                } else if (programs[i] == c2) {
                    i2 = i;
                }
            }
            programs[i1] = c2;
            programs[i2] = c1;
            return programs;
        }
        throw new RuntimeException("Impossivel");
    }

}
