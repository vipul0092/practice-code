package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_11;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day11 {

    private static String INPUT = "abcdefgh";

    public static void solve() {
        INPUT = DAY_11;
        String passstr = Arrays.stream(INPUT.split("\n")).toList().get(0);
        char[] pass = passstr.toCharArray();
        String valid = getValid(pass, 0, false);
        System.out.println("Part 1: " + valid); // hxbxxyzz

        pass = valid.toCharArray();
        changeNext(pass, 0);
        valid = getValid(pass, 0, false);
        System.out.println("Part 2: " + valid); // hxcaabcc
    }

    private static boolean changeNext(char[] pass, int idx) {
        boolean lastIteration = idx == pass.length-1;
        boolean wentNext = lastIteration || changeNext(pass, idx+1);
        if (wentNext) {
            wentNext = pass[idx] == 'z';
            pass[idx] = wentNext ? 'a' : (char)(pass[idx]+1);
        }
        return wentNext;
    }

    private static String getValid(char[] pass, int idx, boolean skipToFirst) {
        if (skipToFirst) {
            pass[idx] = 'a';
        }
        boolean last = idx == pass.length-1;
        do {
            if (pass[idx] == 'i' || pass[idx] == 'o' || pass[idx] == 'l') {
                skipToFirst = true;
                pass[idx]++;
            }
            String res = last
                    ? (valid(pass) ? new String(pass) : "")
                    : getValid(pass, idx+1, skipToFirst);
            if (!res.isEmpty()) return res;
            pass[idx] = pass[idx] == 'z' ? 'a' : (char)(pass[idx]+1);
        } while(pass[idx] != 'a');
        return "";
    }

    private static boolean valid(char[] pass) {
        boolean passesTriplet = false;
        for (int i = 0; i < pass.length-2; i++) {
            if (pass[i+2] == pass[i+1] + 1 && pass[i+1] == pass[i] + 1) {
                passesTriplet = true;
                break;
            }
            if ((pass[i] == 'i' || pass[i] == 'l' || pass[i] == 'o')
                || (pass[i+1] == 'i' || pass[i+1] == 'l' || pass[i+1] == 'o')
                || (pass[i+2] == 'i' || pass[i+2] == 'l' || pass[i+2] == 'o')){
                return false;
            }
        }
        if (!passesTriplet) {
            return false;
        }

        Set<Character> pairs = new HashSet<>();
        for (int i = 0; i < pass.length-1; i++) {
            if (pass[i+1] == pass[i]) {
                pairs.add(pass[i]);
            }
        }
        return pairs.size() >= 2;
    }
}
