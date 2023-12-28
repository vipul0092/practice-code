package code.vipul.aoc2017;

import java.util.Arrays;
import java.util.List;

import static code.vipul.aoc2017.inputs.Inputs.DAY_2;


/**
 * Created by vgaur created on 27/12/23
 */
public class Day2 {

    public static void solve() {
        List<String> lines = Arrays.stream(DAY_2.split("\n")).toList();

        int sum = 0, sum2 = 0;
        for (String line : lines) {
            var str = line.split(" ")[0].split("\t");
            var nums = Arrays.stream(str).map(i -> Integer.parseInt(i.trim())).toList();
            int min = nums.stream().mapToInt(i -> i).min().getAsInt();
            int max = nums.stream().mapToInt(i -> i).max().getAsInt();

            boolean found = false;
            for (int i = 0; i < nums.size()-1; i++) {
                for (int j = i+1; j < nums.size(); j++) {
                    int n1 = nums.get(i), n2 = nums.get(j);
                    if (n1 % n2 == 0 || n2 % n1 == 0) {
                        sum2 += (n1 % n2 == 0 ? n1/n2 : n2/n1);
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            sum += (max-min);
        }

        System.out.println("Part 1: " + sum); // 51139
        System.out.println("Part 2: " + sum2); // 272
    }
}
