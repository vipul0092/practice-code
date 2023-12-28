package code.vipul.aoc2017;

import java.util.*;

import static code.vipul.aoc2017.inputs.Inputs.DAY_10;

/**
 * Created by vgaur created on 27/12/23
 */
public class Day10 {

    private static String INPUT = """
            1,2,4""";
    private static final int COUNT = 256;

    public static void solve() {
        INPUT = DAY_10;
        List<Integer> lengths = Arrays.stream(Arrays.stream(INPUT.split("\n")).toList()
                .get(0).split(",")).map(s -> Integer.parseInt(s)).toList();

        int[] list = hash(lengths, 1);
        int ans = list[0]*list[1];
        System.out.println("Part 1: " + ans); // 23874
        System.out.println("Part 2: " + getHash(INPUT)); // e1a65bfb5a5ce396025fab5528c25a87
    }

    public static String getHash(String input) {
        List<Integer> newLengths = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            newLengths.add((int)ch);
        }
        newLengths.addAll(List.of(17, 31, 73, 47, 23));
        var list = hash(newLengths, 64);

        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int xor = 0;
            for (int j = 0; j < 16; j++) {
                xor ^= list[(i*16)+j];
            }
            if (xor < 16) {
                hex.append('0');
            }
            hex.append(Integer.toHexString(xor));
        }
        return hex.toString();
    }

    private static int[] hash(List<Integer> lengths, int rounds) {
        int current = 0, skip = 0;
        int[] list = new int[COUNT];
        for (int i = 0; i < COUNT; i++) list[i] = i;
        while(rounds-- > 0) {
            for (int len : lengths) {
                reverse(current, len, list);
                current += (len + skip);
                skip++;
            }
        }
        return list;
    }

    private static void reverse(int start, int len, int[] list) {
        for (int k = 0, l = len-1; k < len/2; k++, l-=2) {
            int s = (start+k)%list.length;
            int e = (s+l)%list.length;

            int tmp = list[s];
            list[s] = list[e];
            list[e] = tmp;
        }
    }
}
