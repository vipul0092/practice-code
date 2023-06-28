package code.vipul.codegladitor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by vgaur created on 02/06/23
 */
public class CG2023 {

    public static void solve1() {
        Scanner in = new Scanner(System.in);
        String nxString = in.nextLine();
        String aiString = in.nextLine();

        List<Long> nx = Arrays.stream(nxString.split(" "))
                .map(s -> Long.parseLong(s)).collect(Collectors.toList());
        List<Long> ais = Arrays.stream(aiString.split(" "))
                .map(s -> Long.parseLong(s)).collect(Collectors.toList());

        long n = nx.get(0);
        long x = nx.get(1);

        long answer = -1;

        Map<Long, Long> mapping = new TreeMap<>(Comparator.reverseOrder());
        for(long ai : ais) {
            mapping.merge(ai, 1L, (o, _n) -> o + 1);
        }

        long count = 0;
        for (Map.Entry<Long, Long> e : mapping.entrySet()) {
            long freq = e.getValue();
            long val = e.getKey();

            count += freq;
            if (count > x) {
                break;
            } else if (count == x) {
                answer = val;
                break;
            }
        }

        System.out.println(answer);
    }

    public static void solve2() {

        Scanner in = new Scanner(System.in);

        String nmString = in.nextLine();
        String arrString = in.nextLine();
        String queryString = in.nextLine();

        List<Long> nm = Arrays.stream(nmString.split(" "))
                .map(s -> Long.parseLong(s)).collect(Collectors.toList());
        List<Long> arr = Arrays.stream(arrString.split(" "))
                .map(s -> Long.parseLong(s)).sorted().collect(Collectors.toList());
        List<Long> queries = Arrays.stream(queryString.split(" "))
                .map(s -> Long.parseLong(s)).collect(Collectors.toList());

        long n = nm.get(0);
        long m = nm.get(1);

        Map<Integer, Long> prefixSums = new LinkedHashMap<>();
        Map<Long, Integer> firstIndex = new HashMap<>();
        Map<Long, Integer> lastIndex = new HashMap<>();
        TreeSet<Long> numbers = new TreeSet<>();
        long totalSum = 0;
        for (int i = 0; i < arr.size(); i++) {
            long num = arr.get(i);
            numbers.add(num);
            totalSum += num;
            prefixSums.put(i, totalSum);

            if (!firstIndex.containsKey(num)) {
                firstIndex.put(num, i);
                lastIndex.put(num, i);
            }

            if (lastIndex.containsKey(num)) {
                lastIndex.put(num, i);
            }
        }

        for (long query : queries) {
            Long greater = numbers.higher(query);
            Long smaller = numbers.lower(query);

            long sum = 0;

            if (smaller !=  null) {
                // find the last index of the smaller number and get the sum until that index
                int smallIndex = lastIndex.get(smaller);
                long sumUntil = prefixSums.get(smallIndex);

                // query * num of small numbers - sum of small numbers
                sum += (((smallIndex + 1) * query) - sumUntil);
            }

            if (greater !=  null) {
                // find the first index of the greater number and get the sum from that index until the last
                int greaterIndex = firstIndex.get(greater);
                long sumUntil = greaterIndex == 0 ? totalSum : totalSum - prefixSums.get(greaterIndex - 1);

                // sum of greater numbers - query * num of greater numbers
                int nGreater = arr.size() - greaterIndex;
                sum += (sumUntil - (nGreater * query));
            }

            System.out.print(sum + " ");
        }
    }
}
