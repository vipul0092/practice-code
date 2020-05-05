package code.vipul;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Code gladiator online round 1 2020
 */
public class CG2020 {

    public static void solve1() {
        Scanner in = new Scanner(System.in);
        String nString = in.nextLine();
        String indString = in.nextLine();
        String totSting = in.nextLine();

        long n = Long.parseLong(nString);
        List<Long> individual = Arrays.stream(indString.split(" "))
                .map(s -> Long.parseLong(s)).collect(Collectors.toList());
        List<Long> total = Arrays.stream(totSting.split(" "))
                .map(s -> Long.parseLong(s)).collect(Collectors.toList());

        long min = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            long val = total.get(i) / individual.get(i);
            min = Math.min(val, min);
        }

        System.out.println(min);

    }

    public static void solve2() {
        Scanner in = new Scanner(System.in);
        long t = Long.parseLong(in.nextLine());

        while (t-- > 0) {
            long n = Long.parseLong(in.nextLine());
            TreeSet<Long> gforseSet = new TreeSet<>();
            Map<Long, Integer> values = new HashMap<>();
            Arrays.stream(in.nextLine().split(" "))
                    .map(s -> Long.parseLong(s))
                    .forEach(p -> {
                        gforseSet.add(p);
                        values.putIfAbsent(p, 0);
                        values.put(p, values.get(p) + 1);
                    });
            List<Long> other = Arrays.stream(in.nextLine().split(" "))
                    .map(s -> Long.parseLong(s)).collect(Collectors.toList());

            int count = 0;

            for (Long v : other) {
                Long found = gforseSet.higher(v);
                if (found != null) {
                    count++;
                    values.put(found, values.get(found) - 1);

                    if (values.get(found) == 0) {
                        gforseSet.remove(found);
                        values.remove(found);
                    }
                }
            }

            System.out.println(count);
        }

    }
}
